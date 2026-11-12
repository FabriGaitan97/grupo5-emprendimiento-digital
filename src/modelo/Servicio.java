package modelo;

/**
 * Servicios fijos de la barbería y su precio.
 * Responsable: Kaiken
 */
public enum Servicio {

    CORTE("Corte", 8000),
    BARBA("Barba", 5000),
    COMBO("Corte y barba", 12000);

    private final String nombre;
    private final double precio;

    Servicio(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}
