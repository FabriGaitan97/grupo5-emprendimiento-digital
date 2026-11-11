# Bitácora diaria - Grupo 5 
## Día 1 - 09/11/2026
Qué hicimos:
- Creamos el repositorio en GitHub y sumamos a todos los integrantes como colaboradores.
- Armamos el tablero Kanban en Trello (Por hacer → En curso → Hecho) y cargamos las tarjetas del Día 1 con sus descripciones.
- Elegimos el tema: **Clase 8 – Sets, Queues y Stacks.**
- Elegimos el emprendimiento: **barbería**.
- Redactamos el README inicial (con el enlace al tablero) y enviamos los enlaces del repositorio y del tablero a la docente.

Quién hizo qué:
- Fabrizio Gaitán: creó el repositorio en GitHub y el tablero Kanban en Trello.
- Martina Noel Fernandez: sumó a los colaboradores al repositorio y propuso el tema a abordar. 
- Paul Ezequiel Ferreyra Jaime: redactó el README inicial y envió los enlaces a la docente; intentó anotar el tema en la planilla del curso.
- Todo el grupo: elegimos el emprendimiento (barbería) en conjunto.

Qué nos trabó:
- No tuvimos acceso a la planilla del curso para anotar nuestro tema y emprendimiento, así que esa tarea quedó pendiente (el tema está elegido, falta el registro).

Qué sigue:
- Conseguir acceso a la planilla y registrar el tema (clase 8) y el emprendimiento (barbería) del grupo.
- Día 2 (análisis): definir requerimientos e historias de usuario, y subir el análisis a /docs.
- Repartir las tarjetas del Día 2 entre los cuatro integrantes, para que todos tengan tarjetas y subidas propias.
Captura del tablero: kanban/dia-1.png

Nota adicional: esta entrada se completó el 10/11, al iniciar el Día 2. El trabajo que se describe se hizo el 09/11 y está registrado en las tarjetas de Trello.

## Día 2 - 10/11/2026
Qué hicimos:
- Definimos el sistema completo y lo recortamos a una versión mínima (MVP): un solo usuario (el barbero), solo turnos con reserva, horario de martes a sábado de 10 a 20 h en franjas de 30 minutos y datos en memoria.
- Definimos el uso de las estructuras del tema: Set para clientes (por celular) y para turnos (sin horarios superpuestos), PriorityQueue para atender los turnos por día y hora, y pilas para deshacer reservas/cancelaciones y para "lo de siempre" de cada cliente.
- Escribimos y subimos el análisis de los cuatro módulos en /docs.
- Armamos un borrador del diagrama de flujo y del diagrama de clases.
- Repartimos el trabajo de programación en paquetes según el diagrama de flujo.
- Actualizamos el README con el problema que resuelve y las estructuras del tema.
- Reorganizamos el tablero Kanban con las tarjetas de los días 4 y 5.
- Registramos el tema (Clase 8) y el emprendimiento en la planilla del curso.

Quién hizo qué:
- Fabrizio Gaitán: análisis del módulo 4 (historial y reportes), adaptación y subida del análisis del módulo 1 (clientes), borradores de los diagramas de flujo y de clases, reparto de paquetes de código, corrección del README y reorganización del tablero.
- Martina Noel Fernandez: análisis del módulo 2 (gestión de turnos) adaptación y subida del análisis del módulo 1 (clientes), borradores de los diagramas de flujo y de clases,  y actualización del README con el problema que resuelve.
- Kaiken Luciano Garcia Segovia: análisis del módulo 3 (servicios y atención).
- Paul Ezequiel Ferreyra Jaime: propuesta inicial del módulo de clientes (reglas de negocio y validaciones), que se usó como base del análisis del módulo 1, y registro del tema en la planilla del curso.

Qué nos trabó:
- Paul Ferreyra Jaime dejó la materia; reorganizamos el trabajo entre los tres integrantes que seguimos.
- El primer diseño era demasiado grande para el tiempo disponible; lo recortamos a un MVP y dejamos el resto como mejoras futuras.

Qué sigue:
- Día 3 (diseño): subir a /docs el diagrama de flujo y el diagrama de clases definitivos, y crear el esqueleto del proyecto en /src.
- Ajustar el análisis del módulo 3 al alcance del MVP (servicios fijos).
Captura del tablero: kanban/dia-2.png
