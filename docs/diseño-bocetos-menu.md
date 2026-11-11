# Diseño – Bocetos del menú de consola (opciones de turnos)

Bocetos de lo que ve y escribe el barbero en la consola para las opciones de turnos. Siguen los flujos de `diseño-flujo.md` y las clases de `diseño-de-clases.md`. Los datos de ejemplo son inventados.

**Convenciones**
- Lo que escribe el barbero va después del signo `>`.
- Fecha y hora se ingresan como `dd/MM/yyyy HH:mm` (por ejemplo, `17/11/2026 10:30`).
- Los turnos se muestran como: `Mar 17/11 10:30 – Juan Pérez – Corte`.
- Ante un dato inválido, el programa informa el error y vuelve a pedir el dato; nunca se corta.

## 1. Menú principal

```text

        BARBERÍA DIGITAL

 1. Registrar cliente
 2. Reservar turno
 3. Cancelar turno
 4. Atender siguiente
 5. Ver agenda
 6. Consultar cliente y "lo de siempre"
 7. Historial y reporte
 8. Deshacer
 0. Salir
---------------------------------------
Elija una opción: > _
```

Si la opción no es un número del menú:

```text
Elija una opción: > abc
Opción inválida. Ingrese un número del menú.
Elija una opción: > _
```

## 2. Reservar turno (opción 2)

**Cliente que ya existe**

```text
- RESERVAR TURNO -
Celular del cliente: > 351 555-1234
Cliente encontrado: Juan Pérez

Servicios disponibles:
 1. Corte ($8000)
 2. Barba ($5000)
 3. Corte y barba ($12000)
Elija un servicio: > 1

Día y hora (dd/MM/yyyy HH:mm): > 17/11/2026 10:30

Turno confirmado: Mar 17/11 10:30 – Juan Pérez – Corte
```

**Cliente que no existe (se registra en el mismo paso)**

```text
- RESERVAR TURNO -
Celular del cliente: > 3514440000
El cliente no está registrado. Ingrese sus datos.
Nombre: > Ana
Apellido: > Díaz
Cliente registrado: Ana Díaz

Servicios disponibles:
 1. Corte ($8000)
 2. Barba ($5000)
 3. Corte y barba ($12000)
Elija un servicio: > 2

Día y hora (dd/MM/yyyy HH:mm): > 18/11/2026 15:00

Turno confirmado: Mié 18/11 15:00 – Ana Díaz – Barba
```

**Errores posibles**

```text
Celular del cliente: > 12
Celular inválido. Debe tener entre 8 y 15 dígitos.
Celular del cliente: > _
```

```text
Elija un servicio: > 7
Servicio inválido. Elija una opción de la lista.
Elija un servicio: > _
```

```text
Día y hora (dd/MM/yyyy HH:mm): > mañana a la tarde
Formato inválido. Use dd/MM/yyyy HH:mm (ej.: 17/11/2026 10:30).
Día y hora (dd/MM/yyyy HH:mm): > _
```

```text
Día y hora (dd/MM/yyyy HH:mm): > 16/11/2026 10:30
Horario inválido. Atendemos de martes a sábado, de 10:00 a 19:30, en franjas de 30 minutos.
Día y hora (dd/MM/yyyy HH:mm): > _
```

```text
Día y hora (dd/MM/yyyy HH:mm): > 17/11/2026 10:45
Horario inválido. Atendemos de martes a sábado, de 10:00 a 19:30, en franjas de 30 minutos.
Día y hora (dd/MM/yyyy HH:mm): > _
```

```text
Día y hora (dd/MM/yyyy HH:mm): > 17/11/2026 10:30
Horario ocupado. Elija otro día u hora.
Día y hora (dd/MM/yyyy HH:mm): > _
```

## 3. Cancelar turno (opción 3)

**Turno que existe**

```text
- CANCELAR TURNO -
Día y hora del turno (dd/MM/yyyy HH:mm): > 17/11/2026 10:30

Turno cancelado: Mar 17/11 10:30 – Juan Pérez – Corte
El horario quedó libre.
```

**Turno que no existe**

```text
- CANCELAR TURNO -
Día y hora del turno (dd/MM/yyyy HH:mm): > 19/11/2026 12:00

No hay ningún turno reservado en ese horario.
```

## 4. Atender siguiente (opción 4)

**Cliente que ya vino antes**

```text
- ATENDER SIGUIENTE -
Próximo turno: Mar 17/11 10:30 – Juan Pérez – Corte
Lo de siempre: degradé bajo

Descripción del corte realizado: > degradé bajo con barba perfilada

Atención registrada.
 Cliente:  Juan Pérez
 Servicio: Corte
 Precio:   $8000
Turno retirado de la agenda.
```

**Cliente que viene por primera vez**

```text
- ATENDER SIGUIENTE -
Próximo turno: Mié 18/11 15:00 – Ana Díaz – Barba
Lo de siempre: Sin cortes registrados

Descripción del corte realizado: > barba corta, contorno marcado

Atención registrada.
 Cliente:  Ana Díaz
 Servicio: Barba
 Precio:   $5000
Turno retirado de la agenda.
```

**Agenda vacía**

```text
- ATENDER SIGUIENTE -
No hay turnos.
```

## 5. Ver agenda (opción 5)

```text
- AGENDA (turnos pendientes) -
 1. Mar 17/11 10:30 – Juan Pérez – Corte
 2. Mié 18/11 15:00 – Ana Díaz – Barba
 3. Vie 20/11 17:00 – Juan Pérez – Corte y barba

Total: 3 turnos
```

Con la agenda vacía:

```text
- AGENDA (turnos pendientes) -
No hay turnos.
```

## 6. Deshacer reserva o cancelación (opción 8)

```text
- DESHACER -
Última operación: RESERVAR TURNO – Vie 20/11 17:00 – Juan Pérez – Corte y barba
Turno quitado de la agenda. El horario quedó libre.
```

```text
- DESHACER -
Última operación: CANCELAR TURNO – Mar 17/11 10:30 – Juan Pérez – Corte
Turno devuelto a la agenda.
```

Si en el medio otro cliente reservó ese horario:

```text
- DESHACER -
Última operación: CANCELAR TURNO – Mar 17/11 10:30 – Juan Pérez – Corte
No se puede deshacer: el horario ya fue ocupado por otro turno.
```

Sin operaciones:

```text
- DESHACER -
No hay operaciones para deshacer.
```

## 7. Qué requerimiento y qué clase cubre cada pantalla

| Pantalla | Requerimientos (Módulo 2) | Clases que participan |
| --- | --- | --- |
| Reservar turno | RF2.1, RF2.2, RF2.3, RF2.4, RF2.5 | `Menu`, `Barberia`, `GestorClientes`, `GestorReservas`, `Agenda`, `Turno` |
| Cancelar turno | RF2.9 | `Menu`, `Barberia`, `Agenda`, `GestorReservas`, `Historial` |
| Atender siguiente | RF2.6, RF2.7 | `Menu`, `Barberia`, `Agenda`, `GestorReservas`, `Historial`, `Cliente` |
| Ver agenda | RF2.8 | `Menu`, `Barberia`, `Agenda` (`listarOrdenado()`) |
| Deshacer | (Módulo 4: RF4.6) | `Menu`, `Barberia`, `Historial`, `GestorReservas`, `Agenda` |

## 8. Mensajes de error y su origen

| Mensaje | Cuándo aparece | Quién lo detecta |
| --- | --- | --- |
| Celular inválido | Celular con menos de 8 o más de 15 dígitos, o con caracteres no válidos | `Menu` / `GestorClientes` |
| Formato inválido | Día y hora que no cumplen `dd/MM/yyyy HH:mm` | `Menu` (`leerFechaHora`) |
| Horario inválido | Lunes o domingo, fuera de 10:00–19:30 o fuera de una franja de 30 minutos | `GestorReservas.esHorarioValido()` |
| Horario ocupado | `Set<Turno>.add()` devuelve `false` | `GestorReservas.reservar()` |
| No hay ningún turno reservado en ese horario | Cancelar un turno que no existe | `Agenda.buscar()` devuelve `null` |
| No hay turnos | Atender o ver la agenda vacía | `Agenda.estaVacia()` |
| No hay operaciones para deshacer | Pila de operaciones vacía | `Historial` |
