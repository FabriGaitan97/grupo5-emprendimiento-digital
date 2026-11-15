package modelo;

/** Ayuda de claude. enum defina una lista cerrada de opciones. Con string podrian existir errores de tipeo

/**
 * Tipos de operacion que se pueden deshacer.
 * Solo se deshacen reservas y cancelaciones de turnos.
 */
public enum TipoOperacion {
    RESERVAR_TURNO,
    CANCELAR_TURNO
}
