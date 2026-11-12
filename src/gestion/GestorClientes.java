package gestion;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import modelo.Cliente;

public class GestorClientes {
    private Set<Cliente> clientes; // no permite celulares repetidos
    public GestorClientes() {
        this.clientes = new HashSet<>();
    }

    // Valida los datos y registra el cliente. Devuelve false si el celular ya existe
    public boolean registrar(Cliente c) {
        return false;
    }

    // Busca por celular. Devuelve null si no existe
    public Cliente buscar(String celular) {
        return null;
    }

    // Clientes con el mismo nombre y apellido (da aviso de que puede estar duplicado)
    public List<Cliente> buscarPorNombre(String nombre, String apellido) {
        return null;
    }

    // Todos los clientes registrados listados
    public List<Cliente> listar() {
        return null;
    }
}
