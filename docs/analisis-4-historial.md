# Análisis – Módulo 4: Historial y reportes

**Responsable:** Fabrizio Gaitán

## 1. Objetivo del módulo
Registrar todo lo que pasa en la barbería: las atenciones realizadas (para consultarlas y generar reportes) y las operaciones del sistema (para poder deshacer la última si hubo un error).

## 2. Requerimientos funcionales
| ID | Requerimiento |
| --- | --- |
| RF4.1 | Registrar cada atención realizada: cliente, servicio, precio, fecha y hora. |
| RF4.2 | Consultar el historial de atenciones, de la más reciente a la más antigua. |
| RF4.3 | Generar un reporte con: cantidad de atenciones, total facturado y servicio más pedido. |
| RF4.4 | Registrar cada operación del sistema (registrar cliente, anotar en la espera, atender). |
| RF4.5 | Deshacer la última operación realizada. |

## 3. Requerimientos no funcionales
| ID | Requerimiento |
| --- | --- |
| RNF4.1 | El sistema funciona por consola. |
| RNF4.2 | Los datos se guardan en memoria mientras el programa está abierto. |
| RNF4.3 | Ningún error de uso (historial vacío, nada para deshacer) debe cortar el programa. |

## 4. Historias de usuario

**HU4.1 – Registrar atención**
Como barbero, quiero que cada atención quede registrada para saber qué trabajos hice.
Criterio de aceptación: al atender a un cliente, la atención queda guardada con cliente, servicio, precio y fecha.

**HU4.2 – Consultar historial**
Como dueño, quiero ver el historial de atenciones para controlar el trabajo del día.
Criterio de aceptación: se listan las atenciones de la más reciente a la más antigua. Si no hay, se informa "No hay atenciones registradas".

**HU4.3 – Reporte de atención**
Como dueño, quiero un reporte con la cantidad de atenciones, el total facturado y el servicio más pedido.
Criterio de aceptación: el reporte muestra los tres datos. Si no hay atenciones, muestra todo en cero.

**HU4.4 – Deshacer última operación**
Como recepcionista, quiero deshacer la última operación para corregir un error de carga.
Criterio de aceptación: se revierte solo la última operación. Si no hay operaciones, se informa "No hay operaciones para deshacer".

## 5. Justificación de la estructura: Stack (pila)
Deshacer siempre revierte la **última** operación realizada: la última que entra es la primera que sale (LIFO). Ese es justamente el comportamiento de una pila.

Con un Stack se usan dos operaciones: `push()` para guardar cada operación y `pop()` para sacar la última al deshacer. No hace falta manejar índices.

Una List permitiría acceder o borrar cualquier posición, algo que el negocio no necesita y que podría generar errores (por ejemplo, deshacer una operación vieja en lugar de la última). Por eso elegimos un Stack.

Se implementa con `Deque<Operacion>` y `ArrayDeque`, que es lo que recomienda la documentación de Java en lugar de la clase `Stack`.

## 6. Reglas de negocio y validaciones
- No se puede deshacer si no hay operaciones registradas.
- Deshacer revierte una sola operación por vez.
- El reporte con historial vacío no da error: muestra cantidades en cero.
- Solo se registran atenciones de clientes que fueron efectivamente atendidos.

## 7. Dependencias con otros módulos
- **Módulo 1 (Clientes):** avisa cada vez que registra un cliente, para apilar la operación.
- **Módulo 2 (Turnos):** avisa cuando anota a un cliente en la espera y cuando lo atiende.
- **Módulo 3 (Servicios):** aporta el servicio y el precio de cada atención.
