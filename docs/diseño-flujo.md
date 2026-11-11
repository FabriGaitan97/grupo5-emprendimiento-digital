# Diseño – Diagramas de flujo

Flujos del MVP. Un solo usuario (el barbero), turnos con reserva, horario de martes a sábado de 10 a 20 h en franjas de 30 minutos.

## 1. Menú principal

```mermaid
flowchart LR
    A([Inicio]) --> B[Cargar servicios fijos:<br/>corte, barba, combo]
    B --> M{{Menú principal}}
    M -->|1| O1[Registrar cliente]
    M -->|2| O2[Reservar turno]
    M -->|3| O3[Cancelar turno]
    M -->|4| O4[Atender siguiente]
    M -->|5| O5[Ver agenda]
    M -->|6| O6[Consultar cliente<br/>y 'lo de siempre']
    M -->|7| O7[Historial y reporte]
    M -->|8| O8[Deshacer]
    M -->|0| Z([Salir])
    O1 & O2 & O3 & O4 & O5 & O6 & O7 & O8 --> V([Vuelve al menú])
```

## 2. Reservar turno

```mermaid
flowchart TD
    S([Reservar turno]) --> C[Ingresar celular]
    C --> E{¿Cliente existe<br/>en el Set?}
    E -->|No| R[Pedir nombre y apellido<br/>y registrar cliente]
    R --> SV
    E -->|Sí| SV[Elegir servicio]
    SV --> F[Ingresar día y hora]
    F --> V{¿Martes a sábado,<br/>10 a 20 h y franja<br/>de 30 min?}
    V -->|No| X1[/Horario inválido/] --> F
    V -->|Sí| SET{"turnos.add(turno)"}
    SET -->|false| X2[/Horario ocupado/] --> F
    SET -->|true| PQ["agenda.add(turno)<br/>(PriorityQueue)"]
    PQ --> AP2[Apilar: RESERVAR_TURNO]
    AP2 --> OK[/Turno confirmado/]
    OK --> FIN([Volver al menú])
```

## 3. Atender siguiente

```mermaid
flowchart TD
    S([Atender siguiente]) --> P["turno = agenda.peek()"]
    P --> V{¿Agenda vacía?}
    V -->|Sí| X[/No hay turnos/] --> FIN([Volver al menú])
    V -->|No| L[Mostrar cliente, servicio y<br/>último corte: 'lo de siempre']
    L --> D[El barbero escribe una<br/>descripción del corte]
    D --> Q["agenda.poll()<br/>liberar el horario del Set"]
    Q --> H[Registrar atención en el Historial]
    H --> PC["Guardar corte en la pila<br/>del cliente (push)"]
    PC --> FIN
```

## 4. Deshacer

```mermaid
flowchart TD
    S([Deshacer]) --> V{¿Pila de<br/>operaciones vacía?}
    V -->|Sí| X[/No hay operaciones<br/>para deshacer/] --> FIN([Volver al menú])
    V -->|No| P["op = operaciones.pop()"]
    P --> T{Tipo de operación}
    T -->|RESERVAR_TURNO| B[Deshacer reserva:<br/>quitar el turno del Set<br/>y de la agenda]
    T -->|CANCELAR_TURNO| C[Deshacer cancelación:<br/>volver a agregar el turno<br/>al Set y a la agenda]
    B & C --> M[/Mostrar qué se deshizo/] --> FIN
```

## Reglas que muestran los flujos

- **Reservar:** si el cliente no existe, se registra en el mismo paso (registrar no se puede deshacer). El horario se valida primero (día, rango y franja) y después se intenta agregar al `Set<Turno>`: si `add()` devuelve `false`, el horario está ocupado.
- **Atender siguiente:** primero `peek()` de la `PriorityQueue` para mostrar el turno más próximo y "lo de siempre" (`peek()` de la pila de cortes del cliente); después de cargar la descripción, `poll()` lo saca de la agenda.
- **Cancelar:** se busca el turno por día y hora, se quita del Set y de la agenda, y se apila `CANCELAR_TURNO`.
- **Deshacer:** revierte solo la última operación, y solo hay dos posibles:
  - Deshacer reserva → quitar el turno.
  - Deshacer cancelación → volver a agregar el turno.
- Atender y registrar cliente no se pueden deshacer.
