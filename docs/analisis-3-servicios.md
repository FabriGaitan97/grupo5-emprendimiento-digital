# Análisis - Módulo 3: Servicios y atención

**Responsable:** Kaiken Garcia

## 1. Objetivo del módulo
 Administrar los servicios que ofrece la barbería. Permitir registrar, consultar, cambiar y eliminar servicios. También preparar la información de cada atención realizada con su precio y total.

## 2. Requerimientos funcionales
| ID | Requerimientos |
| --- | --- |
| RF3.1 | Registrar nuevos servicios de barbería con su nombre y precio. |
| RF3.2 | Consultar y mostrar los servicios disponibles. |
| RF3.3 | Consultar el precio de un servicio usando su nombre |
| RF3.4 | Cambiar el precio de un servicio que ya existe | 
| RF3.5 | Comprobar si hay un servicio para poder usarlo en gestión de turnos | 
| RF3.6 | Eliminar servicios del catálogo solo después de confirmarlo |
| RF3.7 | Evitar que se borren servicios que aún tienen turnos pentdientes |
| RF3.8 | Crear los datos de una atención usando la info del turno atendido |
| RF3.9 | Anota el precio y el total para cada atención |

## 3. Requerimientos no funcionales
| ID | Requerimientos |
| --- | --- | 
| RNF3.1 | El catálogo de servicios se maneja usando una List en Java. |
| RNF3.2 | Los datos que se ingresan deben validarse antes de que se registren. |
| RNF3.3 | Los errores al ingresar los precios no deben detener la ejecución del programa. |
| RNF3.4 | El precio de una atención debe mantenerse aunque después cambie el precio del servicio. |

## 4 Historias de usuario

**HU3.1 - Registrar servicio**
 Como barbero, quiero registrar nuevos servicios con su precio para mantener el catálogo al día. Criterio de aceptación: se registra el servicio solo si tiene un nombre válido y un precio mayor que cero.

 **HU3.2 - Consultar servicios**
  Como barbero, quiero ver los servicios disponibles y sus precios para saber qué opciones hay. Criterio de aceptación: se muestra el catálogo con los nombres y precios.

**HU3.3, Modificar precio**
 Como barbero, quiero cambiar el precio de un servicio para mantener los valores actuales. Criterio de aceptación: solo se cambia el precio y se revisa que sea mayor que cero.

**HU3.4 - Eliminar servicio**
 Como barbero, quiero borrar servicios del catálogo cuando ya no se ofrecen. Criterio de aceptación: se pide confirmación y no se puede borrar servicios que tengan turnos pendientes.

**HU3.5 - Registrar atención**
 Como barbero, quiero asociar un servicio a un turno atendido para registrar el trabajo realizado y su importe. Criterio de aceptación: la atención guarda el cliente, servicio, fecha, hora, precio y total correspondiente.

## 5 Justificación de la estructura
 Se usará una `List<Servicio>` para manejar el catálogo de servicios.

Esta estructura es adecuada porque el catálogo es pequeño. Así, se pueden revisar todos sus elementos para consultar, mostrar o buscar servicios.

Como una `List` permite elementos duplicados, primero se buscará para ver si ya existe otro servicio con el mismo nombre antes de registrarlo.

No hace falta usar una `Queue` para el catálogo porque no gestiona el orden de atención. Tampoco se necesita un `Stack` porque no hay que deshacer operaciones.

## 6 Reglas de negocio y validaciones

- El nombre del servicio es obligatorio.

- El precio debe ser mayor que cero.

- No se permiten servicios con nombres duplicados.

- El nombre de un servicio no se puede modificar; solamente su precio.

- Si el precio ingresado no es un número válido, se solicita nuevamente.

- Se puede consultar el catálogo y verificar servicios por nombre.

- No se puede eliminar un servicio que tenga turnos pendientes.

- La eliminación requiere confirmación.

- El precio de la atención se obtiene del catálogo al momento de atender, no al reservar.

- Una modificación posterior del precio no debe alterar las atenciones ya registradas.

 ## 7 Dependencias de otros módulos
- Módulo 1 (Clientes): permite identificar al cliente relacionado con la atención.

- Módulo 2 (Turnos): pide el cliente, servicio, fecha y hora del turno atendido. También necesita revisar si un servicio existe.

- Módulo 4 (Historial y reportes): recibe toda la información de la atención. Incluye el servicio, el precio y el total.

## 8 Organización propuesta
- Servicio: muestra cada servicio de la barbería con su nombre y precio.

- `List<Servicio>`: guarda el catálogo de servicios.

- Atencion: muestra los datos del cliente, servicio, fecha, hora, precio y total.

- GestionServicios maneja las altas, consultas, cambios, bajas y revisiones del catálogo. También prepara los datos de las atenciones.

