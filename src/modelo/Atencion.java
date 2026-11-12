package modelo;

import java.time.LocalDateTime;

/**
 * Una atención ya realizada: qué turno se atendió, qué corte se hizo
 * y cuánto se cobró.
 */
public class Atencion {

    private final Turno turno;               // turno atendido (cliente, servicio, día y hora)
    private final String descripcionCorte;   // lo que escribió el barbero, ej. "degradé bajo"
    private final double precioCobrado;      // precio del servicio al momento de atender
    private final LocalDateTime fechaAtencion; // momento en que se registró la atención

    public Atencion(Turno turno, String descripcionCorte) {
        this.turno = turno;
        this.descripcionCorte = descripcionCorte;
        // Se copia el precio actual: si después cambia el precio del servicio,
        // las atenciones ya registradas no se modifican.
        this.precioCobrado = turno.getServicio().getPrecio();
        this.fechaAtencion = LocalDateTime.now();
    }

    public Turno getTurno() {
        return turno;
    }

    public String getDescripcionCorte() {
        return descripcionCorte;
    }

    public double getPrecioCobrado() {
        return precioCobrado;
    }

    public LocalDateTime getFechaAtencion() {
        return fechaAtencion;
    }

    @Override
    public String toString() {
        // TODO (Día 4): formato "Mar 11/11 10:00 – Juan Pérez – Corte – degradé bajo – $8000"
        return turno + " – " + descripcionCorte + " – $" + precioCobrado;
    }
}
