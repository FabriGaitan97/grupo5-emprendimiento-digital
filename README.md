# Barbería Digital – Proyecto Integrador AED2

**Integrantes:**
- Martina Noel Fernandez – usuario de GitHub: MartinaNoel
- Fabrizio Gaitán – usuario de GitHub: FabriGaitan97
- Kaiken Luciano Garcia Segovia – usuario de GitHub: KaikenGS
- Paul Ferreyra Jaime – dejó la materia el 10/11/2026

**Tablero Kanban:** https://trello.com/b/83efp2ba/grupo5-emprendimiento-digital
**Repositorio:** https://github.com/FabriGaitan97/grupo5-emprendimiento-digital

## Información general

- **Materia:** Algoritmos y Estructuras de Datos II (AED2)
- **Proyecto:** Mi Emprendimiento Digital
- **Grupo:** 5
- **Docente:** Prof. Silvana Burgos
- **Fecha de inicio:** 09/11/2026
- **Entrega y defensa:** Semana del 16/11/2026

## Descripción del negocio

Barbería Digital es un emprendimiento ficticio de una barbería con un solo barbero. El sistema, desarrollado en Java y usado por consola, organiza los clientes y los turnos. El barbero atiende de martes a sábado, de 10:00 a 19:30, en franjas de 30 minutos, y ofrece tres servicios: corte ($8000), barba ($5000) y corte y barba ($12000). Los clientes son personas que reservan turno en la barbería, diferenciadas por su número de celular.

## Problema que resuelve

Llevar la barbería "de memoria" o en un cuaderno genera problemas concretos:

- Dos clientes reservados en el mismo horario.
- Clientes registrados más de una vez.
- Turnos que se atienden en el orden en que se reservaron y no según su día y hora.
- No recordar cuál fue el último corte de cada cliente.
- Errores al reservar o cancelar un turno que no se pueden corregir fácilmente.

El programa organiza la agenda sin superposiciones, mantiene los clientes sin duplicados, atiende los turnos en el orden correcto y permite deshacer la última reserva o cancelación.

## Tema elegido

**Clase 8: Sets, Queues y Stacks.**

Lo elegimos porque el problema de una barbería se resuelve de forma natural con esas tres estructuras:

**Set:**
- Clientes sin celular repetido (`Set<Cliente>`).
- Turnos sin horarios superpuestos (`Set<Turno>`).
**Queue (PriorityQueue):** atender los turnos según su día y hora, no según el orden en que se reservaron (`PriorityQueue<Turno>`).
**Stack:**
- Deshacer la última reserva o cancelación de un turno (pila de operaciones).
- Recordar "lo de siempre": el último corte de cada cliente (pila de cortes).

## Tecnologías

- Java (JDK 26 en el equipo de Martina; el código usa solo clases estándar y funciona desde Java 8)
- Eclipse IDE
- Git y GitHub
- Trello (gestión Kanban)

No se usan librerías externas ni Maven.

## Cómo ejecutar el proyecto

1. Clonar el repositorio:
   ```
   git clone https://github.com/FabriGaitan97/grupo5-emprendimiento-digital.git
   cd grupo5-emprendimiento-digital
   ```
2. Compilar (desde la carpeta del proyecto):
   ```
   javac -encoding UTF-8 -d bin src/modelo/*.java src/gestion/*.java src/ui/*.java
   ```
3. Ejecutar:
   ```
   java -cp bin ui.Main
   ```

En Eclipse: importar la carpeta como proyecto Java, marcar `src` como carpeta de código fuente y ejecutar la clase `ui.Main`.

## Funcionalidades

El programa muestra un menú de consola con estas opciones:

1. **Registrar cliente:** valida nombre, apellido y celular (8 a 15 dígitos) y no permite celulares repetidos.
2. **Reservar turno:** elige servicio, día y hora. Solo se aceptan martes a sábado de 10:00 a 19:30 en horas en punto o y media, sin fechas pasadas y sin horarios ocupados. Si el cliente no está registrado, se registra en el momento.
3. **Cancelar turno:** se elige de la lista por número y el horario queda libre.
4. **Atender siguiente:** atiende el turno más próximo por día y hora, muestra el último corte del cliente y registra la atención.
5. **Ver agenda:** turnos pendientes ordenados por día y hora.
6. **Consultar cliente y "lo de siempre":** muestra los datos del cliente y su último corte.
7. **Historial y reporte:** atenciones de la más reciente a la más antigua, cantidad de atenciones, total facturado y servicio más pedido.
8. **Deshacer:** deshace la última reserva o cancelación.

Organización del código (`/src`):

- `modelo`: `Cliente`, `Turno`, `Servicio`, `Atencion`, `Operacion`, `TipoOperacion`.
- `gestion`: `GestorClientes`, `GestorReservas`, `Agenda`, `Historial`, `Barberia`.
- `ui`: `Main`, `Menu`.

## Capturas de pantalla

Salida real del programa en consola.

**Registrar clientes, reservar turnos, atender, consultar "lo de siempre" y ver el reporte:**

```
=======================================
           BARBERÍA DIGITAL
=======================================
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
Elija una opción: 1
- REGISTRAR CLIENTE -
Celular (0 para volver): 3515551234
Nombre (0 para volver): Juan
Apellido (0 para volver): Pérez
Cliente registrado: Juan Pérez

Elija una opción: 2
- RESERVAR TURNO -
Celular del cliente (0 para volver): 3514440000
El cliente no está registrado. Ingrese sus datos.
Nombre (0 para volver): Ana
Apellido (0 para volver): Díaz
Cliente registrado: Ana Díaz

Servicios disponibles:
 1. Corte ($8000)
 2. Barba ($5000)
 3. Corte y barba ($12000)
Elija un servicio (0 para volver): 2

Atendemos de martes a sábado, de 10:00 a 19:30, en franjas de 30 minutos.
Día y hora (dd/MM/yyyy HH:mm, ej.: 17/11/2026 10:30, 0 para volver): 17/11/2026 10:30
Turno confirmado: Mar 17/11 10:30 - Ana Díaz - Barba

Elija una opción: 2
- RESERVAR TURNO -
Celular del cliente (0 para volver): 3515551234
Cliente encontrado: Juan Pérez

Servicios disponibles:
 1. Corte ($8000)
 2. Barba ($5000)
 3. Corte y barba ($12000)
Elija un servicio (0 para volver): 1

Atendemos de martes a sábado, de 10:00 a 19:30, en franjas de 30 minutos.
Horarios ya reservados:
 1. Mar 17/11 10:30 - Ana Díaz - Barba

Día y hora (dd/MM/yyyy HH:mm, ej.: 17/11/2026 10:30, 0 para volver): 17/11/2026 10:00
Turno confirmado: Mar 17/11 10:00 - Juan Pérez - Corte

Elija una opción: 5
- AGENDA (turnos pendientes) -
 1. Mar 17/11 10:00 - Juan Pérez - Corte
 2. Mar 17/11 10:30 - Ana Díaz - Barba

Total: 2 turnos

Elija una opción: 4
- ATENDER SIGUIENTE -
Próximo turno: Mar 17/11 10:00 - Juan Pérez - Corte
Último corte (lo de siempre): Sin cortes registrados
Descripción del corte realizado (0 para volver): degradé bajo

Atención registrada.
 Cliente:  Juan Pérez
 Servicio: Corte
 Precio:   $8000
Turno retirado de la agenda.

Elija una opción: 6
- CONSULTAR CLIENTE -
Celular del cliente (0 para volver): 3515551234
Cliente: Juan Pérez
Celular: 3515551234
Último corte (lo de siempre): degradé bajo

Elija una opción: 7
- HISTORIAL Y REPORTE -
1. Mar 17/11 10:00 - Juan Pérez - Corte - degradé bajo - $8000

REPORTE
Atenciones realizadas: 1
Total facturado: $8000
Servicio más pedido: Corte

Elija una opción: 0
Gracias por utilizar Barbería Digital.
```

**Casos de error y deshacer (celular inválido, lunes sin atención, cancelar y deshacer la cancelación):**

```
=======================================
           BARBERÍA DIGITAL
=======================================
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
Elija una opción: 1
- REGISTRAR CLIENTE -
Celular (0 para volver): 12345
Celular inválido. Debe tener entre 8 y 15 dígitos.
Celular (0 para volver): 3515551234
Nombre (0 para volver): Juan
Apellido (0 para volver): Pérez
Cliente registrado: Juan Pérez

Elija una opción: 2
- RESERVAR TURNO -
Celular del cliente (0 para volver): 3515551234
Cliente encontrado: Juan Pérez

Servicios disponibles:
 1. Corte ($8000)
 2. Barba ($5000)
 3. Corte y barba ($12000)
Elija un servicio (0 para volver): 1

Atendemos de martes a sábado, de 10:00 a 19:30, en franjas de 30 minutos.
Día y hora (dd/MM/yyyy HH:mm, ej.: 17/11/2026 10:30, 0 para volver): 16/11/2026 10:00
Horario inválido u ocupado. Atendemos de martes a sábado, de 10:00 a 19:30, en franjas de 30 minutos. Elija otro horario.
Día y hora (dd/MM/yyyy HH:mm, ej.: 17/11/2026 10:30, 0 para volver): 17/11/2026 10:00
Turno confirmado: Mar 17/11 10:00 - Juan Pérez - Corte

Elija una opción: 3
- CANCELAR TURNO -
 1. Mar 17/11 10:00 - Juan Pérez - Corte

Número del turno a cancelar (0 para volver): 1
Turno cancelado: Mar 17/11 10:00 - Juan Pérez - Corte
El horario quedó libre.

Elija una opción: 8
- DESHACER -
Última operación: CANCELAR TURNO - Mar 17/11 10:00 - Juan Pérez - Corte
Turno devuelto a la agenda.

Elija una opción: 0
Gracias por utilizar Barbería Digital.
```

Las capturas del tablero de cada día están en `/kanban` (`dia-1.png` a `dia-7.png`).

## Pruebas realizadas

Se probó el programa completo desde la consola con 39 casos, y pasaron todos. Cada integrante probó las opciones de su parte:

- `pruebas/casos-fabrizio.md`: 13 casos de atender siguiente, "lo de siempre", historial, reporte y deshacer.
- `pruebas/casos-martina.md`: 26 casos de registrar cliente, reservar turno, cancelar turno y ver agenda (8 normales y 18 de error).

Al probar anotamos mejoras posibles, que están detalladas al final de cada archivo de pruebas.

## Uso de herramientas de IA

Usamos herramientas de IA como apoyo para entender conceptos y para escribir y revisar partes del código y de la documentación (Claude en el módulo de clientes y turnos, el historial y las pruebas; ChatGPT en el menú).
## Conclusiones

(Borrador: lo revisamos entre los tres.)

- Aprendimos a elegir cada estructura según el problema: el Set evita duplicados de clientes y de horarios, la PriorityQueue ordena los turnos por día y hora y la pila permite deshacer y recordar el último corte.
- Para que `HashSet` y `PriorityQueue` funcionen con nuestras propias clases tuvimos que programar `equals`, `hashCode` y `compareTo`.
- Dificultades: pasamos de cuatro a tres integrantes cuando Paul dejó la materia, tuvimos horarios distintos y algunos inconvenientes con Java y con la subida de archivos a GitHub; los fuimos resolviendo con commits chicos y repartiendo los archivos para no pisarnos.
- Qué mejoraríamos: avisar en el menú si hay un cliente con el mismo nombre y apellido, permitir celulares con "+" y distinguir en el mensaje si un horario está ocupado o es inválido, y guardar los datos en archivos (hoy viven en memoria).
