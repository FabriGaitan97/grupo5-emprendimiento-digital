# Análisis – Módulo 4: Historial y reportes

**Responsable:** Fabrizio Gaitán

## 1. Objetivo del módulo
Registrar lo que pasa en la barbería: las atenciones realizadas (para consultarlas, generar reportes y recordar "lo de siempre" de cada cliente) y las operaciones sobre turnos (para poder deshacer la última si hubo un error).

## 2. Requerimientos funcionales
| ID | Requerimiento |
| --- | --- |
| RF4.1 | Registrar cada atención realizada: cliente, servicio, día y hora, y una breve descripción del corte. |
| RF4.2 | Consultar el historial de atenciones, de la más reciente a la más antigua. |
| RF4.3 | Consultar "lo de siempre": el último corte de un cliente. |
| RF4.4 | Generar un reporte con: cantidad de atenciones, total facturado y servicio más pedido. |
| RF4.5 | Registrar cada reserva y cada cancelación de turno como operación. |
| RF4.6 | Deshacer la última operación: si fue una reserva, quitar el turno; si fue una cancelación, volver a agregar el turno. |

## 3. Requerimientos no funcionales
| ID | Requerimiento |
| --- | --- |
| RNF4.1 | El sistema funciona por consola. |
| RNF4.2 | Los datos se guardan en memoria mientras el programa está abierto. |
| RNF4.3 | Ningún caso vacío (sin atenciones, sin operaciones) debe cortar el programa. |

## 4. Historias de usuario

**HU4.1 – Registrar atención**
Como barbero, quiero que al terminar cada atención quede registrada con una descripción del corte, para tener el historial de mi trabajo.
Criterio de aceptación: al finalizar la atención, queda guardada con cliente, servicio, día y hora y la descripción ingresada.

**HU4.2 – Consultar historial**
Como barbero, quiero ver el historial de atenciones para controlar lo que hice.
Criterio de aceptación: se listan las atenciones de la más reciente a la más antigua. Si no hay, se informa "No hay atenciones registradas".

**HU4.3 – "Lo de siempre"**
Como barbero, quiero ver el último corte de un cliente, para hacerle "lo de siempre" sin preguntarle de nuevo.
Criterio de aceptación: al consultar un cliente se muestra la descripción de su último corte. Si nunca fue atendido, se informa "Sin cortes registrados".

**HU4.4 – Reporte**
Como barbero, quiero un reporte con la cantidad de atenciones, el total facturado y el servicio más pedido.
Criterio de aceptación: el reporte muestra los tres datos. Si no hay atenciones, muestra cantidades en cero.

**HU4.5 – Deshacer reserva**
Como barbero, quiero deshacer la última reserva si la cargué mal.
Criterio de aceptación: el turno reservado sale de la agenda y el horario queda libre.

**HU4.6 – Deshacer cancelación**
Como barbero, quiero deshacer la última cancelación si cancelé el turno equivocado.
Criterio de aceptación: el turno vuelve a la agenda en su mismo día y hora.

## 5. Reglas de negocio y validaciones
- Solo se pueden deshacer **reservas y cancelaciones de turnos**. Atender y registrar clientes no se deshacen.
- Deshacer revierte solo la **última** operación, una por vez.
- Si no hay operaciones, se informa "No hay operaciones para deshacer".
- Deshacer una cancelación solo es posible si el horario sigue libre; si mientras tanto se reservó otro turno en ese horario, se informa que no se puede deshacer.
- Una atención se registra solo cuando el turno fue efectivamente atendido.
- El reporte con historial vacío no da error.

## 6. Justificación de las estructuras

### Pila de operaciones (Stack) – deshacer
Deshacer siempre revierte la **última** operación realizada: la última que entra es la primera que sale (LIFO). Ese es justamente el comportamiento de una pila: `push()` guarda cada operación y `pop()` saca la última al deshacer.
Una List permitiría acceder o borrar cualquier posición, cosa que el negocio no necesita y que podría generar errores (por ejemplo, deshacer una operación vieja en lugar de la última).
Se implementa con la clase `Stack<Operacion>` de Java, la estructura de pila vista en la Clase 8.

### Pila de cortes por cliente (Stack) – "lo de siempre"
Cada cliente guarda sus cortes en una pila: al atenderlo se hace `push()` del corte, y "lo de siempre" es `peek()`, el último corte, sin perder los anteriores.
Una sola variable `ultimoCorte` perdería el historial del cliente; la pila conserva todos los cortes y deja siempre el más reciente arriba.

### Historial de atenciones (List)
Las atenciones se guardan en una List porque se recorren completas para listarlas y para calcular el reporte. No forma parte de las estructuras del tema; se usa como apoyo.

## 7. Dependencias con otros módulos
- **Módulo 1 (Clientes):** cada cliente tiene su pila de cortes, que se actualiza al registrar una atención.
- **Módulo 2 (Gestión de turnos):** avisa cada vez que reserva o cancela un turno, para apilar la operación. Para deshacer se usan sus métodos de quitar y volver a agregar un turno.
- **Módulo 3 (Servicios y atención):** aporta el servicio y el precio de cada atención (necesarios para el reporte).

## 8. Fuera de alcance (mejoras futuras)
- Deshacer otras operaciones (registrar cliente, atender).
- Rehacer (volver a aplicar lo que se deshizo).
- Reportes por barbero o por período.
