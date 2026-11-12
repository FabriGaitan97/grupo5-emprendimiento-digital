package modelo;

import java.util.Stack;

public class Cliente {

    private String celular; // identificamos y diferenciamos así al cliente, no modificamos
    private String nombre;
    private String apellido;
    private Stack<Atencion> cortes; // stack de cortes: el último queda arriba

    public Cliente(String nombre, String apellido, String celular) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.cortes = new Stack<>();
    }

    public String getCelular() {
        return celular;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
  
    public void registrarCorte(Atencion a) {
    }

    // Devuelve el último corte o null, si todavía no tiene cortes
    public Atencion ultimoCorte() {
        return null;
    }

    // Dos clientes son iguales si tienen el mismo celular. Usamos @Override en equals y hashCode para que dos clientes con el mismo celular sean considerados iguales, y así el HashSet no permita registrarlos dos veces
    @Override
    public boolean equals(Object obj) {
        return false;
    }

    // Se calcula con el celular, igual que equals
    @Override
    public int hashCode() {  
        return 0;
    }

    // Ejemplo: Juan Pérez (3515551234)
    @Override
    public String toString() {
        return "";
    }
}
