package gestion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Collections;


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
        atenciones.add(a);
    }
    
    /** Devuelve las atenciones de la más reciente a la más antigua. */
    public List<Atencion> listarAtenciones() {
        List<Atencion> copia = new ArrayList<>(atenciones);
        Collections.reverse(copia);
        return copia;
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
        return atenciones.size();
    }

    /** Suma de lo cobrado en todas las atenciones (para el reporte). */
    public double totalFacturado() {
        double total = 0;
        for (Atencion a : atenciones) {
            total += a.getPrecioCobrado();
        }
        return total;
    }

    /** Servicio que más veces se atendió. Devuelve null si no hay atenciones. */
    public Servicio servicioMasPedido() {
        Servicio masPedido = null;
        int maximo = 0;
        for (Servicio s : Servicio.values()) {
            int cantidad = 0;
            for (Atencion a : atenciones) {
                if (a.getTurno().getServicio() == s) {
                    cantidad++;
                }
            }
            if (cantidad > maximo) {
                maximo = cantidad;
                masPedido = s;
            }
        }
        return masPedido;
    }
}
