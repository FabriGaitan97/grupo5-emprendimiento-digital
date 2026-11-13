
package ui;

import java.time.LocalDateTime; // Ayudado por IA
import java.time.format.DateTimeFormatter; // Ayudado por IA
import java.time.format.ResolverStyle; // Ayudado por IA
import java.time.format.DateTimeParseException; // Ayudado por IA
import java.util.List;
import java.util.Scanner;

import gestion.Barberia;
import modelo.Atencion;
import modelo.Cliente;
import modelo.Servicio;
import modelo.Turno;

public class Menu {

    private Barberia barberia;
    private Scanner scanner;

    private static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm") // Ayudado por IA: creación de un formato personalizado.
                    .withResolverStyle(ResolverStyle.STRICT); // Ayudado por IA: impide aceptar fechas inexistentes.

    public Menu(Barberia barberia) {
        this.barberia = barberia;
        this.scanner = new Scanner(System.in);
    }

    // Mantiene el programa funcionando hasta elegir 0
    public void iniciar() {
        int opcion = -1;

        while (opcion != 0) {
            mostrarMenu();
            opcion = leerEntero("Elija una opción: ");

            switch (opcion) {
                case 1:
                    registrarCliente();
                    break;
                case 2:
                    reservarTurno();
                    break;
                case 3:
                    cancelarTurno();
                    break;
                case 4:
                    atenderSiguiente();
                    break;
                case 5:
                    verAgenda();
                    break;
                case 6:
                    consultarCliente();
                    break;
                case 7:
                    mostrarHistorialYReporte();
                    break;
                case 8:
                    deshacer();
                    break;
                case 0:
                    System.out.println("Gracias por utilizar Barbería Digital.");
                    break;
                default:
                    System.out.println("Opción inválida. Ingrese un número del menú.");
                    break;
            }

            if (opcion != 0) {
                System.out.println();
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("=======================================");
        System.out.println("           BARBERÍA DIGITAL");
        System.out.println("=======================================");
        System.out.println(" 1. Registrar cliente");
        System.out.println(" 2. Reservar turno");
        System.out.println(" 3. Cancelar turno");
        System.out.println(" 4. Atender siguiente");
        System.out.println(" 5. Ver agenda");
        System.out.println(" 6. Consultar cliente y \"lo de siempre\"");
        System.out.println(" 7. Historial y reporte");
        System.out.println(" 8. Deshacer");
        System.out.println(" 0. Salir");
        System.out.println("---------------------------------------");
    }

    // OPCIÓN 1: Registrar cliente
    private void registrarCliente() {
        System.out.println("- REGISTRAR CLIENTE -");

        String celular = leerCelular("Celular: ");

        if (barberia.consultarCliente(celular) != null) {
            System.out.println("Ya existe un cliente registrado con ese celular.");
            return;
        }

        String nombre = leerTexto("Nombre: ");
        String apellido = leerTexto("Apellido: ");

        if (barberia.registrarCliente(nombre, apellido, celular)) {
            System.out.println("Cliente registrado: " + nombre + " " + apellido);
        } else {
            System.out.println("No se pudo registrar el cliente. Verifique los datos.");
        }
    }

    // OPCIÓN 2: Reservar turno
    private void reservarTurno() {
        System.out.println("- RESERVAR TURNO -");

        String celular = leerCelular("Celular del cliente: ");
        Cliente cliente = barberia.consultarCliente(celular);

        if (cliente == null) {
            System.out.println("El cliente no está registrado. Ingrese sus datos.");

            String nombre = leerTexto("Nombre: ");
            String apellido = leerTexto("Apellido: ");

            if (barberia.registrarCliente(nombre, apellido, celular)) {
                System.out.println("Cliente registrado: " + nombre + " " + apellido);
            } else {
                cliente = barberia.consultarCliente(celular);

                if (cliente == null) {
                    System.out.println("No se pudo registrar el cliente.");
                    return;
                }
            }

            cliente = barberia.consultarCliente(celular);

        } else {
            System.out.println("Cliente encontrado: "
                    + cliente.getNombre() + " " + cliente.getApellido());
        }

        Servicio servicio = elegirServicio();

        boolean reservado = false;

        while (!reservado) {
            LocalDateTime fechaHora = leerFechaHora(
                    "Día y hora (dd/MM/yyyy HH:mm): ");

            reservado = barberia.reservarTurno(
                    celular, servicio, fechaHora);

            if (reservado) {
                Turno turno = buscarTurnoEnAgenda(fechaHora);
                System.out.println("Turno confirmado: " + turno);
            } else {
                System.out.println("Horario inválido u ocupado. "
                        + "Atendemos de martes a sábado, de 10:00 a 19:30, "
                        + "en franjas de 30 minutos. Elija otro horario.");
            }
        }
    }

    private Servicio elegirServicio() {
        System.out.println();
        System.out.println("Servicios disponibles:");
        System.out.println(" 1. Corte ($8000)");
        System.out.println(" 2. Barba ($5000)");
        System.out.println(" 3. Corte y barba ($12000)");

        int opcion;

        do {
            opcion = leerEntero("Elija un servicio: ");

            if (opcion < 1 || opcion > 3) {
                System.out.println("Servicio inválido. Elija una opción de la lista.");
            }

        } while (opcion < 1 || opcion > 3);

        switch (opcion) {
            case 1:
                return Servicio.CORTE;
            case 2:
                return Servicio.BARBA;
            default:
                return Servicio.COMBO;
        }
    }

    // Busca un turno en la agenda
    private Turno buscarTurnoEnAgenda(LocalDateTime fechaHora) {
        List<Turno> turnos = barberia.verAgenda();

        for (int i = 0; i < turnos.size(); i++) {
            Turno turno = turnos.get(i);

            if (turno.getFechaHora().equals(fechaHora)) {
                return turno;
            }
        }

        return null;
    }

    // OPCIÓN 3: Cancelar turno
    private void cancelarTurno() {
        System.out.println("- CANCELAR TURNO -");

        LocalDateTime fechaHora = leerFechaHora(
                "Día y hora del turno (dd/MM/yyyy HH:mm): ");

        Turno turno = buscarTurnoEnAgenda(fechaHora);

        if (barberia.cancelarTurno(fechaHora)) {
            System.out.println("Turno cancelado: " + turno);
            System.out.println("El horario quedó libre.");
        } else {
            System.out.println("No hay ningún turno reservado en ese horario.");
        }
    }

    // OPCIÓN 4: Atender siguiente
    private void atenderSiguiente() {
        System.out.println("- ATENDER SIGUIENTE -");

        Turno turno = barberia.proximoTurno();

        if (turno == null) {
            System.out.println("No hay turnos.");
            return;
        }

        System.out.println("Próximo turno: " + turno);

        Cliente cliente = turno.getCliente();
        Atencion ultimoCorte = cliente.ultimoCorte();

        if (ultimoCorte == null) {
            System.out.println("Lo de siempre: Sin cortes registrados");
        } else {
            System.out.println("Lo de siempre: "
                    + ultimoCorte.getDescripcionCorte());
        }

        String descripcion = leerTexto("Descripción del corte realizado: ");

        Atencion atencion = barberia.atenderSiguiente(descripcion);

        if (atencion != null) {
            System.out.println();
            System.out.println("Atención registrada.");
            System.out.println(" Cliente:  " + cliente.getNombre()
                    + " " + cliente.getApellido());
            System.out.println(" Servicio: " + turno.getServicio().getNombre());
            System.out.println(" Precio:   $" + (int) atencion.getPrecioCobrado());
            System.out.println("Turno retirado de la agenda.");
        }
    }

    // OPCIÓN 5: Ver agenda
    private void verAgenda() {
        System.out.println("- AGENDA (turnos pendientes) -");

        List<Turno> turnos = barberia.verAgenda();

        if (turnos.isEmpty()) {
            System.out.println("No hay turnos.");
            return;
        }

        for (int i = 0; i < turnos.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + turnos.get(i));
        }

        System.out.println();
        System.out.println("Total: " + turnos.size() + " turnos");
    }

    // OPCIÓN 6: Consultar cliente y lo de siempre
    private void consultarCliente() {
        System.out.println("- CONSULTAR CLIENTE -");

        String celular = leerCelular("Celular del cliente: ");
        Cliente cliente = barberia.consultarCliente(celular);

        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        System.out.println("Cliente: " + cliente.getNombre()
                + " " + cliente.getApellido());
        System.out.println("Celular: " + cliente.getCelular());

        Atencion ultimoCorte = cliente.ultimoCorte();

        if (ultimoCorte == null) {
            System.out.println("Lo de siempre: Sin cortes registrados");
        } else {
            System.out.println("Lo de siempre: "
                    + ultimoCorte.getDescripcionCorte());
        }
    }

    // OPCIÓN 7: Historial y reporte
    private void mostrarHistorialYReporte() {
        System.out.println("- HISTORIAL Y REPORTE -");

        List<Atencion> atenciones = barberia.listarHistorial();

        if (atenciones.isEmpty()) {
            System.out.println("Todavía no hay atenciones registradas.");
        } else {
            for (int i = 0; i < atenciones.size(); i++) {
                System.out.println((i + 1) + ". " + atenciones.get(i));
            }
        }

        System.out.println();
        System.out.println("REPORTE");
        System.out.println(barberia.reporte());
    }

    // OPCIÓN 8: Deshacer
    private void deshacer() {
        System.out.println("- DESHACER -");
        System.out.println(barberia.deshacer());
    }

    // MÉTODOS DE LECTURA Y VALIDACIÓN

    public String leerTexto(String mensaje) {
        String texto;

        do {
            System.out.print(mensaje);
            texto = scanner.nextLine().trim();

            if (texto.isEmpty()) {
                System.out.println("El dato no puede estar vacío.");
            }

        } while (texto.isEmpty());

        return texto;
    }

    public int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine();

            try {
                return Integer.parseInt(entrada.trim());

            } catch (NumberFormatException e) { // Ayudado por IA: captura específica de errores al convertir texto en números.
                System.out.println("Opción inválida. Ingrese un número entero.");
            }
        }
    }

    public LocalDateTime leerFechaHora(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();

            try {
                return LocalDateTime.parse(entrada, FORMATO_FECHA); // Ayudado por IA: conversión de texto a fecha y hora.

            } catch (DateTimeParseException e) { // Ayudado por IA: captura específica de errores al interpretar fechas.
                System.out.println("Formato inválido. Use dd/MM/yyyy HH:mm "
                        + "(ej.: 17/11/2026 10:30).");
            }
        }
    }

    private String leerCelular(String mensaje) {
        while (true) {
            String celular = leerTexto(mensaje);

            if (celular.length() >= 8 && celular.length() <= 15) {
                boolean valido = true;

                for (int i = 0; i < celular.length(); i++) {
                    if (!Character.isDigit(celular.charAt(i))) { // Ayudado por IA: método para comprobar si un carácter es un dígito.
                        valido = false;
                    }
                }

                if (valido) {
                    return celular;
                }
            }

            System.out.println("Celular inválido. Debe tener entre 8 y 15 dígitos.");
        }
    }
}
