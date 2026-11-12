package gestion;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import modelo.Turno;

public class GestorReservas {
    // Horario de atención: martes a sábado, de 10 a 20 h, en franjas de 30 minutos
    public static final int HORA_APERTURA = 10;
    public static final int HORA_CIERRE = 20;
    public static final int MINUTOS_FRANJA = 30;

    private Set<Turno> reservados; // turnos ya tomados
    public GestorReservas() {
        this.reservados = new HashSet<>();
    }

    // Martes a sábado, entre las 10:00 y las 19:30, y minutos 00 o 30
    public boolean esHorarioValido(LocalDateTime fechaHora) {
        return false;
    }

    // Agrega el turno al Set. Devuelve false si el horario ya está tomado
    public boolean reservar(Turno t) {
        return false;
    }

    // Saca el turno del Set y deja libre ese rango horario
    public void liberar(Turno t) {
    }
}
