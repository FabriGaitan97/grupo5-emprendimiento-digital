# Diseño – Modelo de datos

Qué datos guarda cada clase, en qué estructura se guardan y con qué criterio se comparan. Todos los datos viven en memoria mientras el programa está abierto.

## 1. Entidades

### Cliente
| Atributo | Tipo | Descripción | Reglas |
| --- | --- | --- | --- |
| `celular` | `String` | Identificador del cliente | Obligatorio, único, no se modifica. Se guarda sin espacios ni guiones; 8 a 15 dígitos, con "+" opcional al inicio |
| `nombre` | `String` | Nombre | Obligatorio, no vacío |
| `apellido` | `String` | Apellido | Obligatorio, no vacío |
| `cortes` | `Deque<Atencion>` | Pila con las atenciones del cliente | Empieza vacía; el último corte queda arriba |

- **Igualdad:** `equals()` y `hashCode()` por `celular`.

### Servicio (enum)
| Constante | `nombre` | `precio` |
| --- | --- | --- |
| `CORTE` | "Corte" | 8000 |
| `BARBA` | "Barba" | 5000 |
| `COMBO` | "Corte y barba" | 12000 |

- Los precios son valores de ejemplo; se cambian en un solo lugar.

### Turno
| Atributo | Tipo | Descripción | Reglas |
| --- | --- | --- | --- |
| `fechaHora` | `LocalDateTime` | Día y hora del turno | Martes a sábado; de 10:00 a 19:30; minutos 00 o 30 |
| `cliente` | `Cliente` | Cliente que reservó | Debe existir en `GestorClientes` |
| `servicio` | `Servicio` | Servicio reservado | Uno de los del enum |

- **Igualdad:** `equals()` y `hashCode()` por `fechaHora` (un solo barbero: no puede haber dos turnos en el mismo día y hora).
- **Orden:** implementa `Comparable<Turno>`; `compareTo()` compara por `fechaHora` (el más temprano primero).

### Atencion
| Atributo | Tipo | Descripción |
| --- | --- | --- |
| `turno` | `Turno` | Turno atendido (cliente, servicio, día y hora) |
| `descripcionCorte` | `String` | Breve descripción del corte que hizo el barbero |
| `precioCobrado` | `double` | Precio del servicio al momento de atender |
| `fechaAtencion` | `LocalDateTime` | Momento en que se registró la atención |

- El precio se copia al atender, así un cambio futuro de precios no altera las atenciones ya registradas.

### Operacion
| Atributo | Tipo | Descripción |
| --- | --- | --- |
| `tipo` | `TipoOperacion` | `RESERVAR_TURNO` o `CANCELAR_TURNO` |
| `turno` | `Turno` | Turno afectado, necesario para revertir la operación |

## 2. Estructuras de datos

| Clase | Atributo | Interfaz | Implementación | Qué guarda | Criterio | Operaciones principales |
| --- | --- | --- | --- | --- | --- | --- |
| `GestorClientes` | `clientes` | `Set<Cliente>` | `HashSet` | Clientes registrados | Igualdad por celular | `add`, `contains`, recorrer para buscar |
| `GestorReservas` | `reservados` | `Set<Turno>` | `HashSet` | Horarios tomados | Igualdad por día y hora | `add` (devuelve `false` si está ocupado), `remove` |
| `Agenda` | `turnos` | `Queue<Turno>` | `PriorityQueue` | Turnos pendientes | Orden por día y hora (`compareTo`) | `add`, `peek`, `poll`, `remove` |
| `Historial` | `operaciones` | `Deque<Operacion>` (pila) | `ArrayDeque` | Reservas y cancelaciones | LIFO | `push`, `pop`, `isEmpty` |
| `Cliente` | `cortes` | `Deque<Atencion>` (pila) | `ArrayDeque` | Cortes del cliente | LIFO | `push`, `peek` |
| `Historial` | `atenciones` | `List<Atencion>` | `ArrayList` | Todas las atenciones | Orden de registro | `add`, recorrer (apoyo, no es del tema) |

## 3. Detalles de implementación a tener en cuenta

- **`equals()` y `hashCode()` siempre juntos.** Sin los dos, el `HashSet` no detecta duplicados.
- **El celular y la fecha del turno no se modifican** mientras el objeto está en un `Set`: si cambiaran, cambiaría su `hashCode()` y el `Set` dejaría de encontrarlo.
- **La `PriorityQueue` no se recorre en orden.** `peek()` y `poll()` siempre dan el turno más próximo, pero un `for` sobre la cola no sale ordenado. Para "Ver agenda", `listarOrdenado()` copia los turnos a una lista y la ordena.
- **`Agenda.quitar()` usa `remove(Object)`,** que busca por `equals()`: alcanza con un turno con la misma fecha y hora.
- **Pilas con `ArrayDeque`** en lugar de la clase `Stack`, como recomienda la documentación de Java.
- **Deshacer una cancelación** vuelve a llamar a `reservas.reservar(turno)`: si devuelve `false` (otro turno ocupó ese horario), se informa que no se puede deshacer.

## 4. Ejemplo de datos en memoria

Después de reservar tres turnos y atender uno:

```
GestorClientes.clientes  = { Juan Pérez (3515551234), Ana Díaz (3514440000) }
GestorReservas.reservados = { Mar 11/11 10:30, Vie 14/11 15:00 }
Agenda.turnos (próximo primero) = [ Mar 11/11 10:30 Ana – Barba, Vie 14/11 15:00 Juan – Combo ]
Historial.atenciones      = [ Mar 11/11 10:00 Juan – Corte – "degradé bajo" – $8000 ]
Historial.operaciones (tope arriba) = [ RESERVAR Vie 15:00, RESERVAR Mar 10:30, RESERVAR Mar 10:00 ]
Juan.cortes (tope arriba) = [ "degradé bajo" ]
```
