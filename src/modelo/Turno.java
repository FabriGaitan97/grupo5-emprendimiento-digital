package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        return fechaHora.compareTo(otro.fechaHora);
    }

    // Dos turnos son iguales si tienen el mismo día y hora.
    @Override
    public boolean equals(Object obj) {
        if (this==obj) return true;
        if (obj==null || getClass()!=obj.getClass()) return false;
        Turno turno=(Turno) obj;
        return fechaHora.equals(turno.fechaHora);
    }

    // Se calcula con la fecha y hora, igual que equals
    @Override
    public int hashCode() {
        return fechaHora.hashCode();
    }

    // Ejemplo: Mar 17/11 10:30 - Juan Pérez - Corte
    @Override
    public String toString() {
        String[]dias={"Lun", "Mar", "Miér", "Jue", "Vie", "Sáb", "Dom"};
        String dia=dias[fechaHora.getDayOfWeek().getValue()-1];
        String fecha=fechaHora.format(DateTimeFormatter.ofPattern("dd/MM HH:mm"));
        return dia+" "+fecha+" - "+cliente.getNombre()+" "+cliente.getApellido()+" - "+servicio.getNombre();
    }
}
