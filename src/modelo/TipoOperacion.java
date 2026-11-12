package modelo;

/**
 * Tipos de operación que se pueden deshacer.
 * Solo se deshacen reservas y cancelaciones de turnos.
 */
public enum TipoOperacion {
    RESERVAR_TURNO,
    CANCELAR_TURNO
}
