# Casos de prueba: atención, historial y deshacer

Responsable: Fabrizio Gaitán
Fecha de las pruebas: 13/11/2026
Opciones del menú que se prueban: 4 (atender siguiente), 6 ("lo de siempre"), 7 (historial y reporte) y 8 (deshacer).

Datos que usé en casi todas las pruebas:

- Cliente Juan Pérez, celular 3515551234
- Cliente Ana Díaz, celular 3514440000
- Turnos del martes 17/11/2026 a las 10:00, 10:30 y 11:00

Cada prueba arranca con el programa recién abierto, salvo que diga otra cosa en los datos de entrada.

| N° | Qué pruebo | Datos de entrada | Resultado esperado | Resultado obtenido | ¿Pasó? |
| --- | --- | --- | --- | --- | --- |
| 1 | Atender con la agenda vacía | Opción 4 sin turnos cargados | Avisa que no hay turnos y vuelve al menú | "No hay turnos." | Sí |
| 2 | Deshacer sin operaciones | Opción 8 sin haber reservado ni cancelado nada | Avisa que no hay nada para deshacer | "No hay operaciones para deshacer." | Sí |
| 3 | Reporte sin atenciones | Opción 7 sin atenciones | Muestra todo en cero y no se corta | "Todavía no hay atenciones registradas." Reporte: 0 atenciones, $0, servicio más pedido "-" | Sí |
| 4 | "Lo de siempre" de un cliente que nunca se atendió | Opción 6, celular 3515551234 (Juan recién registrado) | Muestra que no tiene cortes | "Lo de siempre: Sin cortes registrados" | Sí |
| 5 | Atender respeta el orden por día y hora | Reservo primero Ana 17/11 10:30 barba y después Juan 17/11 10:00 corte. Opción 4 | Atiende primero a Juan (10:00) aunque se reservó después | Próximo turno: "Mar 17/11 10:00 - Juan Pérez - Corte". Atención registrada, $8000 | Sí |
| 6 | "Lo de siempre" después de atender | Sigue de la prueba 5. Descripción del corte: "degradé bajo". Opción 6 con 3515551234 | Muestra el último corte de Juan | "Lo de siempre: degradé bajo" | Sí |
| 7 | Historial de la más reciente a la más antigua | Sigue de la prueba 6. Atiendo a Ana con "barba prolija". Opción 7 | Primero Ana, después Juan | 1. Ana Díaz, Barba, barba prolija, $5000. 2. Juan Pérez, Corte, degradé bajo, $8000 | Sí |
| 8 | Reporte con atenciones | Sigue de la prueba 7. Opción 7 | 2 atenciones, $13000, servicio más pedido Corte (empate, gana el primero del enum) | "Atenciones realizadas: 2 / Total facturado: $13000 / Servicio más pedido: Corte" | Sí |
| 9 | Deshacer la reserva de un turno que ya se atendió | Sigue de la prueba 8. Opción 8 | No deja deshacer porque el turno ya no está en la agenda | "No se puede deshacer: el turno ya fue atendido." | Sí |
| 10 | Deshacer una reserva | Reservo a Juan 17/11 11:00 corte y barba. Opción 8 | El turno sale de la agenda y el horario queda libre | "Turno quitado de la agenda. El horario quedó libre." La agenda queda vacía y pude reservar las 11:00 para Ana | Sí |
| 11 | Deshacer una cancelación | Sigue de la prueba 10. Cancelo el turno de Ana de las 11:00. Opción 8 | El turno de Ana vuelve a la agenda en el mismo horario | "Turno devuelto a la agenda." Agenda: "Mar 17/11 11:00 - Ana Díaz - Corte" | Sí |
| 12 | Deshacer saca primero lo último que se hizo (pila) | Reservo Juan 10:00 y después Ana 10:30. Opción 8 tres veces | 1° deshace Ana, 2° deshace Juan, 3° avisa que no hay más | 1° "RESERVAR TURNO - Mar 17/11 10:30 - Ana Díaz". 2° "RESERVAR TURNO - Mar 17/11 10:00 - Juan Pérez". 3° "No hay operaciones para deshacer." | Sí |
| 13 | Deshacer después de cancelar y volver a ocupar el horario | Reservo Juan 10:00, lo cancelo y reservo Ana 10:00. Opción 8 | Deshace la reserva de Ana, que fue lo último | "RESERVAR TURNO - Mar 17/11 10:00 - Ana Díaz - Barba. Turno quitado de la agenda." | Sí |

## Cosas que encontramos usando el programa

Estas no son fallas de la parte de atención e historial, pero salieron al recorrer el menú completo y las anotamos para el grupo:

1. Se pueden reservar turnos en fechas que ya pasaron. Como la agenda atiende por día y hora, un turno viejo queda primero y "atender siguiente" lo toma antes que los de la semana.
2. Si me equivoco de opción o cargo mal el celular, no hay forma de volver al menú sin terminar la carga.
3. Para cancelar hay que escribir la fecha y la hora exactas del turno. Sería más cómodo ver la agenda antes y elegir el turno por número.
4. Cargar la fecha completa (dd/MM/yyyy HH:mm) en cada reserva es lento.
5. El texto "lo de siempre" confunde un poco: muestra el último corte, y eso no siempre es lo que el cliente se hace habitualmente.
