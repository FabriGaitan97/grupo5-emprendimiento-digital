package ui;

import java.time.LocalDateTime;
import java.util.Scanner;

import gestion.Barberia;

/**
 * Muestra el menú, lee los datos por consola y llama a Barberia.
 * Responsable: Kaiken
 */
public class Menu {

    private Barberia barberia;
    private Scanner scanner;

    public Menu(Barberia barberia) {
        // TODO: guardar la barberia y crear el Scanner(System.in)
    }

    public void iniciar() {
        // TODO: bucle que muestra el menú, lee la opción y llama a Barberia, hasta elegir 0
    }

    public String leerTexto(String mensaje) {
        // TODO: pedir un texto no vacío; si está vacío, volver a pedirlo
        return null;
    }

    public int leerEntero(String mensaje) {
        // TODO: pedir un número; si no es válido, volver a pedirlo (try/catch)
        return 0;
    }

    public LocalDateTime leerFechaHora(String mensaje) {
        // TODO: pedir fecha y hora en formato dd/MM/yyyy HH:mm; si es inválido, volver a pedirla
        return null;
    }
}
