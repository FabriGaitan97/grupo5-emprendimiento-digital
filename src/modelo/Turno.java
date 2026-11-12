package modelo;

import java.time.LocalDateTime;

public class Turno implements Comparable<Turno> {

    private LocalDateTime fechaHora;
    private Cliente cliente;
    private Servicio servicio;

    public Turno(LocalDateTime fechaHora, Cliente cliente, Servicio servicio) {
        this.fechaHora = fechaHora;
        this.cliente = cliente;
        this.servicio = servicio;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Servicio getServicio() {
        return servicio;
    }

    // Ordena los turnos por día y hora (el más temprano primero), para la PriorityQueue
    @Override
    public int compareTo(Turno otro) {
        return 0;
    }

    // Dos turnos son iguales si tienen el mismo día y hora.
    @Override
    public boolean equals(Object obj) {
        return false;
    }

    // Se calcula con la fecha y hora, igual que equals
    @Override
    public int hashCode() {
        return 0;
    }

    // Ejemplo: Mar 17/11 10:30 - Juan Pérez - Corte
    @Override
    public String toString() {
        return "";
    }
}
