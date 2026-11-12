package gestion;

import java.time.LocalDateTime;
import java.util.List;
import java.util.PriorityQueue;

import modelo.Turno;

/**
 * Turnos pendientes, ordenados por día y hora con una PriorityQueue.
 * Responsable: Kaiken
 */
public class Agenda {

    private PriorityQueue<Turno> turnos = new PriorityQueue<>();

    public void agregar(Turno t) {
        // TODO: turnos.add(t)
    }

    public boolean quitar(Turno t) {
        // TODO: turnos.remove(t)
        return false;
    }

    public Turno buscar(LocalDateTime fechaHora) {
        // TODO: recorrer la cola y devolver el turno con esa fecha y hora, o null
        return null;
    }

    public Turno proximo() {
        // TODO: turnos.peek()
        return null;
    }

    public Turno siguiente() {
        // TODO: turnos.poll()
        return null;
    }

    public List<Turno> listarOrdenado() {
        // TODO: copiar a un ArrayList, ordenar con Collections.sort y devolverlo
        return null;
    }

    public boolean estaVacia() {
        // TODO: turnos.isEmpty()
        return false;
    }
}
