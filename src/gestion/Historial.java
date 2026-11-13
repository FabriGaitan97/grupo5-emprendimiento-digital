package gestion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import modelo.Atencion;
import modelo.Operacion;
import modelo.Servicio;

/**
 * Guarda las atenciones realizadas (para el historial y el reporte)
 * y la pila de operaciones (para deshacer la última reserva o cancelación).
 */
public class Historial {

    // Todas las atenciones en orden de registro (estructura de apoyo, no es del tema).
    private final List<Atencion> atenciones = new ArrayList<>();
    
    // Pila de operaciones: la última operación queda arriba (LIFO).
    // Se usa la clase Stack de Java, como en la Clase 8 (Sets, Queues y Stacks).
    private final Stack<Operacion> operaciones = new Stack<>();

    /** Agrega una atención al historial. */
    public void registrarAtencion(Atencion a) {
        // TODO (Día 4): agregar a la lista
    }

    /** Devuelve las atenciones de la más reciente a la más antigua. */
    public List<Atencion> listarAtenciones() {
        // TODO (Día 4): copiar la lista e invertir el orden
        return new ArrayList<>();
    }

    /** Guarda una operación en la pila (push). */
    public void apilar(Operacion op) {
        operaciones.push(op);
    }

    /** Saca la última operación de la pila (pop). Devuelve null si está vacía. */
    public Operacion desapilar() {
        if (operaciones.isEmpty()) {
            return null;
        }
        return operaciones.pop();
    }
    
    /** Indica si hay operaciones para deshacer. */
    public boolean hayOperaciones() {
    	return !operaciones.isEmpty();
    }

    /** Cantidad de atenciones realizadas (para el reporte). */
    public int cantidadAtenciones() {
        // TODO (Día 5)
        return 0;
    }

    /** Suma de lo cobrado en todas las atenciones (para el reporte). */
    public double totalFacturado() {
        // TODO (Día 5): sumar getPrecioCobrado() de cada atención
        return 0;
    }

    /** Servicio que más veces se atendió. Devuelve null si no hay atenciones. */
    public Servicio servicioMasPedido() {
        // TODO (Día 5): contar cuántas veces aparece cada servicio y devolver el mayor
        return null;
    }
}
