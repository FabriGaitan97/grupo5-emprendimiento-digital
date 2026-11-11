# Análisis – Módulo 2: Gestión de turnos

**Responsable:** Martina Noel Fernandez

## 1. Objetivo del módulo
Organizar los turnos de la barbería: tomar reservas sin superponer horarios, mantener la agenda ordenada por día y hora, e indicar y atender al próximo turno. La barbería atiende **solo con turno reservado**; no hay atención por orden de llegada.

## 2. Requerimientos funcionales
| ID | Requerimiento |
| --- | --- |
| RF2.1 | Reservar un turno indicando cliente (por celular), servicio, día y hora. |
| RF2.2 | Si el cliente no está registrado, registrarlo en el mismo paso de la reserva. |
| RF2.3 | Validar que el turno caiga de martes a sábado, entre las 10 y las 20 h y al inicio de una franja de 30 minutos. |
| RF2.4 | Rechazar la reserva si el día y la hora ya están tomados. |
| RF2.5 | Mantener la agenda ordenada por día y hora, sin importar el orden en que se reservaron los turnos. |
| RF2.6 | Consultar cuál es el próximo turno sin sacarlo de la agenda. |
| RF2.7 | Atender al próximo turno: retirarlo de la agenda y liberar el horario. |
| RF2.8 | Ver la agenda completa en orden. |
| RF2.9 | Cancelar un turno reservado. |

## 3. Requerimientos no funcionales
| ID | Requerimiento |
| --- | --- |
| RNF2.1 | El sistema funciona por consola. |
| RNF2.2 | Los horarios de atención se definen en un solo lugar (constantes), para poder cambiarlos sin tocar la lógica. |
| RNF2.3 | Ningún dato inválido (fecha mal escrita, horario fuera de rango) debe cortar el programa. |
| RNF2.4 | Los datos se guardan en memoria mientras el programa está abierto. |

## 4. Historias de usuario

**HU2.1 – Reservar turno**
Como barbero, quiero reservar un turno para un cliente, para organizar mi día.
Criterio de aceptación: el turno queda en la agenda con cliente, servicio, día y hora, y se muestra "Turno confirmado".

**HU2.2 – Evitar superposición**
Como barbero, quiero que el sistema no permita dos turnos en el mismo horario, para no tener clientes superpuestos.
Criterio de aceptación: si el día y la hora ya están tomados, se informa "Horario ocupado" y no se guarda el turno.

**HU2.3 – Validar horario de atención**
Como barbero, quiero que solo se puedan reservar turnos dentro del horario de la barbería.
Criterio de aceptación: un turno un lunes, un domingo, antes de las 10, desde las 20 o fuera de una franja de 30 minutos se rechaza con "Horario inválido".

**HU2.4 – Próximo turno**
Como barbero, quiero ver quién es el próximo turno, para prepararme.
Criterio de aceptación: se muestra el turno con el día y la hora más próximos. Si no hay turnos, se informa "No hay turnos".

**HU2.5 – Atender al siguiente**
Como barbero, quiero atender al próximo turno y que salga de la agenda.
Criterio de aceptación: el turno atendido deja de figurar en la agenda y su horario queda libre.

**HU2.6 – Cancelar turno**
Como barbero, quiero cancelar un turno cuando el cliente avisa que no viene.
Criterio de aceptación: el turno sale de la agenda y el horario queda libre para otra reserva.

## 5. Reglas de negocio y validaciones
- Solo se reservan turnos de martes a sábado, de 10 a 20 h, al inicio de una franja de 30 minutos (10:00, 10:30, ..., 19:30). Son valores provisorios y configurables.
- La agenda es semanal.
- En esta versión hay un solo barbero, que gestiona todo el sistema.
- No puede haber dos turnos en el mismo día y hora.
- La agenda se atiende por día y hora del turno, no por orden de reserva.
- Atender o consultar con la agenda vacía no da error: se informa que no hay turnos.
- Solo se puede cancelar un turno que exista en la agenda.

## 6. Justificación de las estructuras

### Set<Turno> – evitar horarios superpuestos
Dos turnos se consideran iguales si tienen el mismo **día y hora** ('equals()' y 'hashCode()' por fecha y hora). Al reservar, 'add()' devuelve 'false' si el horario ya está tomado, así que el Set controla la superposición sin lógica extra.
Con una List habría que recorrer todos los turnos para comprobar si el horario está libre.

### PriorityQueue<Turno> – atender en orden de agenda
Los turnos se reservan en cualquier orden, pero se atienden según su día y hora. La PriorityQueue ordena los turnos por fecha y hora ('compareTo()'), y 'peek()' / 'poll()' devuelven siempre el más próximo.
Una Queue común respetaría el orden de reserva: si alguien reserva primero para el viernes y después otro para el martes, se atendería primero el viernes, lo cual es incorrecto. Una List obligaría a ordenarla cada vez.

## 7. Dependencias con otros módulos
- **Módulo 1 (Clientes):** buscar el cliente por celular y registrarlo si no existe.
- **Módulo 3 (Servicios y atención):** el servicio elegido para el turno.
- **Módulo 4 (Historial y reportes):** cada reserva y cada cancelación se registran en la pila de operaciones para poder deshacerlas. Al atender, la atención se registra en el historial.

## 8. Fuera de alcance (mejoras futuras)
- Varios barberos (el turno tendría un campo barbero).
- Clientes sin turno con una cola por orden de llegada.
- Marcar que el cliente no vino.
- Hora simulada para controlar en qué momento se puede atender un turno.
