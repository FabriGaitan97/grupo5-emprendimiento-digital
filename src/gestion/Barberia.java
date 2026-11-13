package gestion;

import java.time.LocalDateTime;
import java.util.List;

import modelo.Atencion;
import modelo.Cliente;
import modelo.Servicio;
import modelo.Turno;
import modelo.Operacion;
import modelo.TipoOperacion;

/**
 * Coordina el sistema: el menú solo habla con esta clase, y esta clase
 * llama a los gestores que corresponden y apila las operaciones que se
 * pueden deshacer. Los gestores no se conocen entre sí.
 *
 * Los pasos de cada método están en docs/diseño-de-clases.md,
 * sección "Qué hace cada clase en cada opción del menú".
 */
public class Barberia {

    private final GestorClientes clientes;   // Martina – Set<Cliente>
    private final GestorReservas reservas;   // Martina – Set<Turno>
    private final Agenda agenda;             // Kaiken  – PriorityQueue<Turno>
    private final Historial historial;       // Fabrizio – atenciones y pila de operaciones

    public Barberia() {
        this.clientes = new GestorClientes();
        this.reservas = new GestorReservas();
        this.agenda = new Agenda();
        this.historial = new Historial();
    }

    /** Opción 1. Registra un cliente. Devuelve false si el celular ya existe o los datos no son válidos. */
    public boolean registrarCliente(String nombre, String apellido, String celular) {
        Cliente nuevo = new Cliente(nombre, apellido, celular);
        return clientes.registrar(nuevo);
    }
    /** Opción 6. Busca un cliente por celular. Devuelve null si no existe. */
    public Cliente consultarCliente(String celular) {
        return clientes.buscar(celular);
    }
    
    /** Opción 2. Reserva un turno. Devuelve false si el horario es inválido u ocupado. */
    public boolean reservarTurno(String celular, Servicio servicio, LocalDateTime fechaHora) {
        // 1. El cliente tiene que estar registrado (si no, el menú lo registra antes)
        Cliente cliente = clientes.buscar(celular);
        if (cliente == null) {
            return false;
        }
        // 2. Martes a sábado, de 10:00 a 19:30, minutos 00 o 30
        if (!reservas.esHorarioValido(fechaHora)) {
            return false;
        }
        // 3. El Set de reservas rechaza el turno si ese día y hora ya están tomados
        Turno turno = new Turno(fechaHora, cliente, servicio);
        if (!reservas.reservar(turno)) {
            return false;
        }
        // 4. Se agrega a la agenda (PriorityQueue) y se apila para poder deshacerlo
        agenda.agregar(turno);
        historial.apilar(new Operacion(TipoOperacion.RESERVAR_TURNO, turno));
        return true;
    }
    
    /** Opción 3. Cancela el turno de ese día y hora. Devuelve false si no existe. */
    public boolean cancelarTurno(LocalDateTime fechaHora) {
        // 1. Se busca el turno de ese día y hora en la agenda
        Turno turno = agenda.buscar(fechaHora);
        if (turno == null) {
            return false;
        }
        // 2. Se saca de la agenda (PriorityQueue) y se libera el horario en el Set
        agenda.quitar(turno);
        reservas.liberar(turno);
        // 3. Se apila para poder deshacer la cancelación
        historial.apilar(new Operacion(TipoOperacion.CANCELAR_TURNO, turno));
        return true;
    }
    
    /** Opción 5. Devuelve los turnos pendientes ordenados por día y hora. */
    public List<Turno> verAgenda() {
        return agenda.listarOrdenado();
    }

    /** Opción 4 (primer paso). Muestra el próximo turno sin sacarlo de la agenda. Devuelve null si no hay. */
    public Turno proximoTurno() {
        return agenda.proximo();
    }

    /** Opción 4 (segundo paso). Atiende el próximo turno y registra la atención. Devuelve null si no hay turnos. */
    public Atencion atenderSiguiente(String descripcionCorte) {
        // 1. Se saca de la agenda el turno más próximo (poll de la PriorityQueue)
        Turno turno = agenda.siguiente();
        if (turno == null) {
            return null;
        }
        // 2. El horario ya se usó: se libera del Set de reservas
        reservas.liberar(turno);
        // 3. Se registra la atención en el historial y en la pila de cortes del cliente
        Atencion atencion = new Atencion(turno, descripcionCorte);
        historial.registrarAtencion(atencion);
        turno.getCliente().registrarCorte(atencion);
        return atencion;
    }

    /** Opción 7. Devuelve las atenciones de la más reciente a la más antigua. */
    public List<Atencion> listarHistorial() {
        return historial.listarAtenciones();
    }

    /** Opción 7. Arma el texto del reporte: cantidad de atenciones, total facturado y servicio más pedido. */
    public String reporte() {
        Servicio masPedido = historial.servicioMasPedido();
        String nombreServicio;
        if (masPedido == null) {
            nombreServicio = "-";
        } else {
            nombreServicio = masPedido.getNombre();
        }
        return "Atenciones realizadas: " + historial.cantidadAtenciones()
                + "\nTotal facturado: $" + (int) historial.totalFacturado()
                + "\nServicio más pedido: " + nombreServicio;
    }

    /** Opción 8. Deshace la última reserva o cancelación. Devuelve un mensaje con lo que se hizo. */
    public String deshacer() {
        // 1. Se saca la última operación de la pila (pop)
        Operacion op = historial.desapilar();
        if (op == null) {
            return "No hay operaciones para deshacer.";
        }
        Turno turno = op.getTurno();

        // 2a. Si fue una reserva: se quita el turno y se libera el horario
        if (op.getTipo() == TipoOperacion.RESERVAR_TURNO) {
            // Si el turno ya no está en la agenda, es porque ya fue atendido
            if (!agenda.quitar(turno)) {
                return "Última operación: RESERVAR TURNO - " + turno
                        + "\nNo se puede deshacer: el turno ya fue atendido.";
            }
            reservas.liberar(turno);
            return "Última operación: RESERVAR TURNO - " + turno
                    + "\nTurno quitado de la agenda. El horario quedó libre.";
        }

        // 2b. Si fue una cancelación: se vuelve a reservar el horario, si sigue libre
        if (!reservas.reservar(turno)) {
            return "Última operación: CANCELAR TURNO - " + turno
                    + "\nNo se puede deshacer: el horario ya fue ocupado por otro turno.";
        }
        agenda.agregar(turno);
        return "Última operación: CANCELAR TURNO - " + turno
                + "\nTurno devuelto a la agenda.";
    }
}
