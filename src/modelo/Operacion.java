package modelo;

/**
 * Una operación realizada sobre un turno que se guarda en la pila
 * del historial para poder deshacerla.
 */
public class Operacion {

    private final TipoOperacion tipo;  // qué se hizo: reservar o cancelar
    private final Turno turno;         // turno afectado, necesario para revertir

    public Operacion(TipoOperacion tipo, Turno turno) {
        this.tipo = tipo;
        this.turno = turno;
    }

    public TipoOperacion getTipo() {
        return tipo;
    }

    public Turno getTurno() {
        return turno;
    }

    @Override
    public String toString() {
        return tipo + " - " + turno;
    }
}
