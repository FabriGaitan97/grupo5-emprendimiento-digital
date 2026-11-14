# Casos de prueba: clientes, reservas, cancelar y agenda

Responsable: Martina Noel Fernandez
Fecha de las pruebas: 14/11/2026
Opciones del menú que se prueban: 1 (registrar cliente), 2 (reservar turno), 3 (cancelar turno) y 5 (ver agenda). Se prueban también las clases de mi módulo: Cliente, Turno, GestorClientes y GestorReservas.

Cómo se hicieron: se corrió el programa completo (`ui.Main`) y se cargaron los datos por consola, uno por uno, con un guion de entradas para repetir cada caso siempre igual. Armé los casos con ayuda de Claude (IA) para automatizar la carga.

Datos que usé en casi todas las pruebas:

- Cliente Juan Pérez, celular 3515551234
- Cliente Ana Díaz, celular 3514440000
- Turnos de la semana del 16/11/2026 (martes 17/11, miércoles 18/11)

Cada prueba arranca con el programa recién abierto, salvo que diga otra cosa en los datos de entrada. Cuando dice "Juan registrado" significa que antes se hizo la opción 1 con Juan Pérez.

## Casos normales

| N° | Qué pruebo | Datos de entrada | Resultado esperado | Resultado obtenido | ¿Pasó? |
| --- | --- | --- | --- | --- | --- |
| 1 | Registrar un cliente válido | Opción 1: celular 3515551234, nombre Juan, apellido Pérez. Después opción 6 con ese celular | Lo registra y después lo encuentra | "Cliente registrado: Juan Pérez". Opción 6: "Cliente: Juan Pérez", último corte "Sin cortes registrados" | Sí |
| 2 | Reservar turno a un cliente registrado | Juan registrado. Opción 2: celular 3515551234, servicio 1 (Corte), 17/11/2026 10:00. Opción 5 | Confirma el turno y aparece en la agenda | "Cliente encontrado: Juan Pérez". "Turno confirmado: Mar 17/11 10:00 - Juan Pérez - Corte". Agenda: 1 turno | Sí |
| 3 | Reservar turno a un cliente que no estaba registrado | Opción 2: celular 3514440000. Pide nombre y apellido (Ana, Díaz), servicio 2 (Barba), 17/11/2026 10:30 | Lo registra en el momento y reserva el turno | "El cliente no está registrado. Ingrese sus datos." "Cliente registrado: Ana Díaz". "Turno confirmado: Mar 17/11 10:30 - Ana Díaz - Barba" | Sí |
| 4 | Reservar en el último horario del día | Juan registrado. Opción 2, Corte, 17/11/2026 19:30 | Lo acepta (la barbería cierra a las 20:00) | "Turno confirmado: Mar 17/11 19:30 - Juan Pérez - Corte" | Sí |
| 5 | La agenda queda ordenada por día y hora | Juan registrado. Reservo en este orden: 18/11 15:00, 17/11 10:00 y 17/11 11:30. Opción 5 | Aparecen ordenados: 17/11 10:00, 17/11 11:30, 18/11 15:00 | 1. Mar 17/11 10:00, 2. Mar 17/11 11:30, 3. Miér 18/11 15:00. "Total: 3 turnos" | Sí |
| 6 | Cancelar un turno | Juan registrado con turno 17/11 10:00. Opción 3, elijo el número 1. Después opción 5 | Cancela el turno y la agenda queda vacía | "Turno cancelado: Mar 17/11 10:00 - Juan Pérez - Corte. El horario quedó libre." Agenda: "No hay turnos." | Sí |
| 7 | Reservar de nuevo un horario que se canceló | Sigue de la prueba 6. Opción 2, Barba, 17/11/2026 10:00 | El horario vuelve a estar disponible | "Turno confirmado: Mar 17/11 10:00 - Juan Pérez - Barba" | Sí |
| 8 | Ver agenda sin turnos | Opción 5 recién abierto el programa | Avisa que no hay turnos | "No hay turnos." | Sí |

## Casos de error

| N° | Qué pruebo | Datos de entrada | Resultado esperado | Resultado obtenido | ¿Pasó? |
| --- | --- | --- | --- | --- | --- |
| 9 | Registrar un celular que ya existe | Juan registrado. Opción 1 con celular 3515551234 | No lo registra otra vez | "Ya existe un cliente registrado con ese celular." | Sí |
| 10 | Celular demasiado corto | Opción 1: celular 12345 | Rechaza el celular y lo vuelve a pedir | "Celular inválido. Debe tener entre 8 y 15 dígitos." Se pudo volver con 0 | Sí |
| 11 | Celular con letras | Opción 1: celular 35155abc12 | Rechaza el celular | "Celular inválido. Debe tener entre 8 y 15 dígitos." | Sí |
| 12 | Nombre vacío | Opción 1: celular 3515551234, nombre vacío (solo Enter), después Juan y Pérez | Rechaza el nombre vacío y lo vuelve a pedir | "El dato no puede estar vacío." y después "Cliente registrado: Juan Pérez" | Sí |
| 13 | Volver al menú con 0 | Opción 1 y escribo 0 como celular | Vuelve al menú sin registrar nada | "Volviendo al menú." | Sí |
| 14 | Reservar un horario ya ocupado | Juan registrado con turno 17/11 10:00. Opción 2 otra vez a las 17/11 10:00, y después a las 10:30 | No deja tomar el horario ocupado y deja elegir otro | "Horario inválido u ocupado. Atendemos de martes a sábado, de 10:00 a 19:30..." y después acepta 10:30 | Sí |
| 15 | Reservar un lunes | Juan registrado. Opción 2, 16/11/2026 10:00 | Rechaza el turno (no se atiende los lunes) | "Horario inválido u ocupado..." | Sí |
| 16 | Reservar un domingo | Juan registrado. Opción 2, 22/11/2026 10:00 | Rechaza el turno | "Horario inválido u ocupado..." | Sí |
| 17 | Reservar antes de la apertura | Juan registrado. Opción 2, 17/11/2026 09:30 | Rechaza el turno | "Horario inválido u ocupado..." | Sí |
| 18 | Reservar a la hora de cierre | Juan registrado. Opción 2, 17/11/2026 20:00 | Rechaza el turno (el último horario es 19:30) | "Horario inválido u ocupado..." | Sí |
| 19 | Minutos fuera de la franja de 30 minutos | Juan registrado. Opción 2, 17/11/2026 10:15 | Rechaza el turno | "Horario inválido u ocupado..." | Sí |
| 20 | Fecha que no existe | Juan registrado. Opción 2, fecha 31/02/2026 10:00 | Rechaza la fecha | "Formato inválido. Use dd/MM/yyyy HH:mm (ej.: 17/11/2026 10:30)." | Sí |
| 21 | Fecha con formato incorrecto | Juan registrado. Opción 2, fecha "mañana a las 10" | Rechaza el texto y vuelve a pedir la fecha | "Formato inválido. Use dd/MM/yyyy HH:mm..." | Sí |
| 22 | Reservar en una fecha pasada | Juan registrado. Opción 2, 10/11/2026 10:00 | No deja reservar en el pasado | "No se pueden reservar fechas pasadas. Elija otro horario." | Sí |
| 23 | Servicio inválido | Juan registrado. Opción 2 y elijo el servicio 7 | Rechaza el servicio y lo vuelve a pedir | "Servicio inválido. Elija una opción de la lista." | Sí |
| 24 | Cancelar sin turnos | Opción 3 recién abierto el programa | Avisa que no hay turnos | "No hay turnos." | Sí |
| 25 | Cancelar con un número que no está en la lista | Juan registrado con un turno. Opción 3 y escribo el número 9 | Rechaza el número y lo vuelve a pedir | "Número inválido. Elija un turno de la lista." | Sí |
| 26 | Opción del menú inválida | Escribo 9 y después abc | No se corta el programa | "Opción inválida. Ingrese un número del menú." y "Opción inválida. Ingrese un número entero." | Sí |

Resultado: 26 casos que pasaron.

## Cosas que encontramos usando el programa

Estas no hacen fallar los casos, pero las anoto para el grupo:

1. **No se avisa de clientes con el mismo nombre y apellido.** `GestorClientes.buscarPorNombre` está programado, pero el menú no lo usa. Si registro a Juan Pérez con dos celulares distintos, los dos se registran sin ningún aviso (probado). Es un posible duplicado que el sistema podría avisar.
2. **El celular con "+" se rechaza en el menú.** `GestorClientes` acepta un "+" al inicio (por ejemplo +5493515551234), pero la lectura del celular en `Menu` solo acepta dígitos. Probado: el "+" se rechaza con "Celular inválido".
3. **El mensaje de horario ocupado y de horario inválido es el mismo.** Si el horario está tomado o si es un lunes, el menú dice "Horario inválido u ocupado..." y no distingue cuál de los dos es.
4. **Detalle de texto:** con un solo turno, la agenda dice "Total: 1 turnos".
5. Cargar la fecha completa (dd/MM/yyyy HH:mm) en cada reserva es lento. Ya lo había anotado Fabrizio.
