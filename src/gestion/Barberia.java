package gestion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import modelo.Atencion;
import modelo.Cliente;
import modelo.Servicio;
import modelo.Turno;

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
        // TODO (Día 5): crear Cliente y llamar a clientes.registrar(...)
        return false;
    }

    /** Opción 6. Busca un cliente por celular. Devuelve null si no existe. */
    public Cliente consultarCliente(String celular) {
        // TODO (Día 5): clientes.buscar(celular)
        return null;
    }

    /** Opción 2. Reserva un turno. Devuelve false si el horario es inválido u ocupado. */
    public boolean reservarTurno(String celular, Servicio servicio, LocalDateTime fechaHora) {
        // TODO (Día 5): buscar cliente → validar horario → reservas.reservar → agenda.agregar
        //               → historial.apilar(new Operacion(RESERVAR_TURNO, turno))
        return false;
    }

    /** Opción 3. Cancela el turno de ese día y hora. Devuelve false si no existe. */
    public boolean cancelarTurno(LocalDateTime fechaHora) {
        // TODO (Día 5): agenda.buscar → agenda.quitar → reservas.liberar
        //               → historial.apilar(new Operacion(CANCELAR_TURNO, turno))
        return false;
    }

    /** Opción 5. Devuelve los turnos pendientes ordenados por día y hora. */
    public List<Turno> verAgenda() {
        // TODO (Día 5): agenda.listarOrdenado()
        return new ArrayList<>();
    }

    /** Opción 4 (primer paso). Muestra el próximo turno sin sacarlo de la agenda. Devuelve null si no hay. */
    public Turno proximoTurno() {
        // TODO (Día 5): agenda.proximo()
        return null;
    }

    /** Opción 4 (segundo paso). Atiende el próximo turno y registra la atención. Devuelve null si no hay turnos. */
    public Atencion atenderSiguiente(String descripcionCorte) {
        // TODO (Día 5): agenda.siguiente() → reservas.liberar → new Atencion
        //               → historial.registrarAtencion → cliente.registrarCorte
        return null;
    }

    /** Opción 7. Devuelve las atenciones de la más reciente a la más antigua. */
    public List<Atencion> listarHistorial() {
        // TODO (Día 5): historial.listarAtenciones()
        return new ArrayList<>();
    }

    /** Opción 7. Arma el texto del reporte: cantidad de atenciones, total facturado y servicio más pedido. */
    public String reporte() {
        // TODO (Día 5)
        return "";
    }

    /** Opción 8. Deshace la última reserva o cancelación. Devuelve un mensaje con lo que se hizo. */
    public String deshacer() {
        // TODO (Día 5): historial.desapilar() → según el tipo:
        //   RESERVAR_TURNO → agenda.quitar + reservas.liberar
        //   CANCELAR_TURNO → reservas.reservar (si da false, no se puede deshacer) + agenda.agregar
        return "";
    }
}
