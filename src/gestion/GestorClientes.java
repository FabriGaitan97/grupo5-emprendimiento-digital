package gestion;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import modelo.Cliente;

public class GestorClientes {
    private Set<Cliente> clientes; // no permite celulares repetidos
    public GestorClientes() {
        this.clientes = new HashSet<>();
    }

    // Valida los datos y registra el cliente. Devuelve false si el celular ya existe
    public boolean registrar(Cliente c) {
        if(c==null || estaVacio(c.getNombre()) || estaVacio(c.getApellido()) || !celularValido(c.getCelular())){
        return false;
        }
        return clientes.add(c); // si hay un cliente con el mismo teléfono, el add devuelve false
    }
    // Busca por celular. Devuelve null si no existe
    public Cliente buscar(String celular) {
        for(Cliente c:clientes){
            if(c.getCelular().equals(celular)){
               return c;
            }
        }
        return null;
    }

    // Clientes con el mismo nombre y apellido (da aviso de que puede estar duplicado)
    public List<Cliente> buscarPorNombre(String nombre, String apellido) {
        List<Cliente> coincidencias=new ArrayList<>();
        for(Cliente c:clientes){
            if(c.getNombre().equalsIgnoreCase(nombre)&& c.getApellido().equalsIgnoreCase(apellido)){
                coincidencias.add(c);
            }
        }
        return coincidencias;
    }

    // Todos los clientes registrados listados
    public List<Cliente> listar() {
        return new ArrayList<>(clientes);
    }
    private boolean estaVacio(String texto){
    //Ayuda de IA (Claude): trim() saca los espacios del principio y del final; isEmpty() pregunta si quedó vacío.
        return texto==null || texto.trim().isEmpty();
    }
    //Ayuda de IA (Claude): startsWith, charAt y Character.isDigit son métodos básicos de Java
    private boolean celularValido(String celular){
        if(celular==null){
            return false;
        }
        int inicio=0;
        if(celular.startsWith("+")){
            inicio=1;
        }
        int cantidad=celular.length()-inicio;
        if(cantidad<8 || cantidad>15){
            return false;
        }
        for(int i=inicio; i<celular.length(); i++){
            if(!Character.isDigit(celular.charAt(i))){
                return false;
            }
        }
        return true;
    }
    
}
