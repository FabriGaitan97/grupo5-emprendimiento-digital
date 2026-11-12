package gestion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import modelo.Turno;

/**
 * Turnos pendientes, ordenados por día y hora con una PriorityQueue.
 * Responsable: Kaiken
 */
public class Agenda {

    // La cola ordena sola los turnos usando Turno.compareTo (por día y hora)
    private PriorityQueue<Turno> turnos = new PriorityQueue<>();

    // Agrega un turno a la cola
    public void agregar(Turno t) {
        turnos.add(t);
    }

    // Saca un turno puntual. Lo busca con equals (mismo día y hora). Devuelve false si no estaba
    public boolean quitar(Turno t) {
        return turnos.remove(t);
    }

    // Busca el turno de ese día y hora. Devuelve null si no hay ninguno
    public Turno buscar(LocalDateTime fechaHora) {
        for (Turno t : turnos) {
            if (t.getFechaHora().equals(fechaHora)) {
                return t;
            }
        }
        return null;
    }

    // Muestra el turno más próximo sin sacarlo de la cola
    public Turno proximo() {
        return turnos.peek();
    }

    // Saca de la cola el turno más próximo. Si no hay turnos, devuelve null
    public Turno siguiente() {
        return turnos.poll();
    }

    // La cola no se recorre en orden, por eso se copia a una lista y se ordena
    public List<Turno> listarOrdenado() {
        // Ayuda de IA (Claude): new ArrayList<>(turnos) crea una lista nueva con una copia de todos los turnos de la cola
        List<Turno> copia = new ArrayList<>(turnos);
        Collections.sort(copia);
        return copia;
    }

    // true si no hay turnos pendientes
    public boolean estaVacia() {
        return turnos.isEmpty();
    }
}
