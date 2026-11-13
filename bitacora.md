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
- Martina Noel Fernandez: análisis del módulo 2 (gestión de turnos), actualización del README con el problema que resuelve, reorganización del reparto del trabajo junto con Fabrizio, actualización de sus tarjetas del tablero y captura del tablero del día 2.
- Kaiken Luciano Garcia Segovia: análisis del módulo 3 (servicios y atención).
- Paul Ezequiel Ferreyra Jaime: propuesta inicial del módulo de clientes (reglas de negocio y validaciones), que se usó como base del análisis del módulo 1, y registro del tema en la planilla del curso.

Qué nos trabó:
- Paul Ferreyra Jaime dejó la materia; reorganizamos el trabajo entre los tres integrantes que seguimos.
- El primer diseño era demasiado grande para el tiempo disponible; lo recortamos a un MVP y dejamos el resto como mejoras futuras.

Qué sigue:
- Día 3 (diseño): subir a /docs el diagrama de flujo y el diagrama de clases definitivos, y crear el esqueleto del proyecto en /src.
- Ajustar el análisis del módulo 3 al alcance del MVP (servicios fijos).

Captura del tablero: kanban/dia-2.png

## Día 3 - 11/11/2026
Qué hicimos:
- Definimos el diseño del sistema: diagrama de clases con los paquetes modelo, gestion y ui, qué clases participan en cada opción del menú y cómo se reparte el código.
- Armamos el modelo de datos: atributos de cada clase, estructura elegida para cada dato (Set, PriorityQueue y pilas) y criterio de comparación.
- Dibujamos los diagramas de flujo del menú principal, de reservar turno, de atender siguiente y de deshacer.
- Hicimos los bocetos de lo que se ve y se escribe en la consola en las opciones de turnos (reservar, cancelar, atender siguiente, ver agenda y deshacer), con sus mensajes de error.
- Creamos la estructura del proyecto en /src (paquetes modelo, gestion y ui) y el esqueleto de las 13 clases: cada integrante subió el de sus clases, con atributos y métodos declarados y la lógica pendiente para los días 4 y 5.
- Completamos las tarjetas del Día 3 en Trello con descripción, responsable y condición de terminada.
- Aclaramos en el README que Paul dejó la materia y corregimos la bitácora del día 2.

Quién hizo qué:
- Fabrizio Gaitán: diagrama de clases, modelo de datos y diagramas de flujo (docs/diseño-de-clases.md, docs/diseño-de-datos.md y docs/diseño-flujo.md), esqueleto de TipoOperacion, Operacion, Atencion, Historial y Barberia, y corrección del nombre de la captura dia-2.png.
- Martina Noel Fernandez: bocetos del menú de consola para las opciones de turnos (docs/diseño-bocetos-menu.md); esqueleto de Cliente, Turno, GestorClientes y GestorReservas (src/modelo y src/gestion), ajustado a lo visto en la clase 8 (por ejemplo, la pila de cortes con Stack); completar las tarjetas del Día 3 en el tablero; aclaración en el README sobre Paul y corrección de la bitácora del día 2.
- Kaiken Luciano Garcia Segovia: estructura del proyecto en /src (paquetes modelo, gestion y ui) y esqueleto de Servicio, Agenda, Main y Menu.

Qué nos trabó:
- Tuvimos algunos inconvenientes con el lenguaje (Java) y para ponernos de acuerdo en cómo subir los archivos al repositorio. Lo tenemos en cuenta para los próximos días: vamos a acordar de antemano quién sube cada archivo para no pisarnos.
- Nuestros horarios son distintos, lo que dificulta trabajar en sincronización. Lo vamos coordinando por el grupo del equipo y lo tenemos presente para organizar el trabajo del Día 4.
- El trabajo quedó repartido entre tres integrantes, porque Paul dejó la materia, por lo que tocó reorganizarnos y repartir temas a desarrollar. 

Qué sigue:
- Día 4 (desarrollo I): seguir trabajando el código en sincronización. Completar primero Cliente y Turno (equals, hashCode y compareTo), porque las usan todas las demás clases; después, la lógica de los gestores, Historial y el menú según el reparto de docs/diseño-de-clases.md.
- Acordar entre los tres cómo y cuándo subimos los archivos, para que el repositorio quede ordenado.
- Ajustar el análisis del módulo 3 al alcance del MVP (servicios fijos). 

Captura del tablero: kanban/dia-3.png

## Día 4 - 12/11/2026
Qué hicimos:
Martina
- Programamos la lógica del módulo 2 (gestión de turnos) que habíamos dejado como esqueleto el Día 3: Turno, GestorReservas, Cliente y GestorClientes.
- Turno: atributos, constructor, orden por fecha y hora (compareTo) para la PriorityQueue, y equals, hashCode y toString. Dos turnos se consideran el mismo si tienen el mismo día y hora.
- GestorReservas: reservar y liberar turnos con un Set<Turno> (no permite dos turnos en el mismo horario) y validación del horario: no se atiende lunes ni domingo, de 10:00 a 19:30, solo en horas en punto o y media. Para eso declaramos constantes (LUNES = 1, DOMINGO = 7, HORA_APERTURA y HORA_CIERRE) en lugar de dejar comentarios.
- Cliente: pila de cortes con Stack (registrarCorte, ultimoCorte), tal como se vio en la clase 8, y equals, hashCode y toString. Dos clientes son el mismo si tienen el mismo celular.
- GestorClientes: registrar con validaciones (nombre y apellido no vacíos, celular de 8 a 15 dígitos con "+" opcional, celular no repetido gracias al Set), buscar por celular, buscar por nombre y apellido (para avisar si puede haber un cliente duplicado) y listar.
- Probamos el código compilando todo el proyecto y con una serie de pruebas de comportamiento (24 verificaciones: celulares inválidos, horarios fuera de rango, turnos duplicados, orden de la PriorityQueue). Pasan todas.
- Subimos cada clase al repositorio con un mensaje de commit descriptivo.
  
Fabrizio
- Historial: pila de operaciones con Stack (apilar, desapilar, hayOperaciones), registrar y listar atenciones (de la más reciente a la más antigua) y reporte (cantidad de atenciones, total facturado y servicio más pedido, recorriendo los servicios del enum, sin usar Map).
- Atencion: formato de toString usando el toString de Turno.
- Barberia completa: coordina las opciones del menú llamando a los gestores. En reservarTurno se usan las tres estructuras del tema: el Set de clientes, el Set de turnos, la PriorityQueue de la agenda y la pila de operaciones. También cancelar, atender siguiente, ver agenda, consultar cliente, historial, reporte y deshacer.
- Simulamos pruebas de Barberia con las clases del grupo: registrar, reservar, horario ocupado, cancelar, atender, "lo de siempre", reporte y deshacer.
- Actualizamos la documentación de diseño y el análisis del módulo 4 para reflejar el cambio a Stack.
Kaiken
(COMPLETAR)

Quién hizo qué:
- Martina Noel Fernandez: programó y subió Turno, GestorReservas, Cliente y GestorClientes (src/modelo y src/gestion), con 8 commits en el repositorio. Usó Claude (IA) como apoyo para escribir y revisar el código; lo que no estaba en la clase 8 quedó marcado en los comentarios con "Ayuda de IA (Claude)".
- Fabrizio Gaitán: programó y subió Historial, Barberia y el toString de Atencion (src/gestion y src/modelo), con 3 commits en el repositorio. Usó Claude para probar el código con una prueba que simula el uso del sistema, porque el menú todavía no estaba terminado.
- Kaiken Luciano Garcia Segovia: (completar)

Qué nos trabó:
- Seguimos con inconvenientes para acordar el uso de GitHub y con horarios distintos entre los integrantes, así que cada uno sube solo sus archivos para no pisarnos.
Martina
- Hubo mucha búsqueda de términos y métodos de Java que hacían falta para que el código fuera más simple y correcto: manejo de fecha y hora (LocalDateTime, getDayOfWeek, getHour, getMinute), Comparable y compareTo, hashCode, y métodos de texto (startsWith, charAt, Character.isDigit, trim, isEmpty, equalsIgnoreCase). Para no apartarnos de la clase usamos Stack y las estructuras vistas, y marcamos con comentarios lo que surgió de la IA como herramienta de apoyo.
- Hubo prueba y error al compilar. Corregimos: declaraciones, errores de tipeo y sintaxis (un punto de más en un return, un paréntesis faltante en equals, "stratsWith" en vez de "startsWith"), llaves mal cerradas en registrar y buscar, y una línea que se perdió en buscarPorNombre (la lista de coincidencias). Cada corrección quedó como commit propio.
Fabrizio
- Al probar Barberia encontramos un error: deshacer la reserva de un turno que ya había sido atendido mostraba un mensaje falso ("Turno quitado de la agenda"). Lo corregimos usando el resultado de agenda.quitar(): si el turno ya no está en la agenda, se informa que no se puede deshacer.
- Como el menú todavía no está programado, no pudimos probar el sistema completo desde la consola; probamos Barberia con una prueba aparte que simula el uso.
- Unificamos las dos pilas del proyecto con Stack (como en la Clase 8), porque el esqueleto de Historial usaba ArrayDeque.
Kaiken
(COMPLETAR)

Qué sigue:
- Día 5: conectar las opciones del menú con Barberia, que ya está completa junto con Agenda, y hacer las pruebas de punta a punta con el programa funcionando.
- Agregar al README la sección de uso de herramientas de IA, de acuerdo con el grupo.

Captura del tablero: kanban/dia-4.png
