# Análisis – Módulo 1: Gestión de clientes

**Proyecto:** Mi Emprendimiento Digital – AED2
**Grupo:** 5
**Emprendimiento:** Barbería Digital
**Responsable del módulo:** Paul Ferreyra Jaime
**Tema técnico relacionado:** Clase 8 – Sets, Queues y Stacks

## 1. Objetivo

Permitir que la barbería registre y consulte a sus clientes de manera ordenada, sin que una misma persona quede registrada dos veces. Los clientes registrados son los que pueden reservar turnos.

## 2. Datos de cada cliente

| Dato | Descripción |
| --- | --- |
| Celular | Identificador único del cliente. No se repite entre clientes. |
| Nombre | Nombre del cliente. |
| Apellido | Apellido del cliente. |
| Cortes | Historial de cortes del cliente, usado para "lo de siempre" (ver Módulo 4). |

El celular identifica al cliente: no puede haber dos clientes con el mismo número y, una vez registrado, no se modifica.

## 3. Requerimientos funcionales

| ID | Requerimiento |
| --- | --- |
| RF1.1 | Registrar un cliente con nombre, apellido y celular. |
| RF1.2 | Registrar al cliente en el mismo paso de una reserva si todavía no existe. |
| RF1.3 | Rechazar el registro si el celular ya pertenece a otro cliente. |
| RF1.4 | Avisar si ya existe un cliente con el mismo nombre y apellido (posible duplicado). |
| RF1.5 | Consultar un cliente por su celular. |
| RF1.6 | Listar los clientes registrados. |

## 4. Requerimientos no funcionales

| ID | Requerimiento |
| --- | --- |
| RNF1.1 | El sistema funciona por consola. |
| RNF1.2 | Los datos se guardan en memoria mientras el programa está abierto. |
| RNF1.3 | Ningún dato inválido debe cortar el programa: se informa el error y se vuelve a pedir el dato. |

## 5. Historias de usuario

**HU1.1 – Registrar cliente**
Como barbero, quiero registrar a un cliente con sus datos, para poder darle turnos.
Criterio de aceptación: el cliente queda registrado y se muestra "Cliente registrado".

**HU1.2 – Evitar clientes duplicados**
Como barbero, quiero que el sistema no permita registrar dos veces el mismo celular, para no tener clientes repetidos.
Criterio de aceptación: si el celular ya existe, se informa "El cliente ya está registrado" y se muestran sus datos.

**HU1.3 – Aviso de posible duplicado**
Como barbero, quiero que el sistema me avise si ya hay alguien con el mismo nombre y apellido, para verificar que no sea la misma persona con otro número.
Criterio de aceptación: se muestran los clientes que coinciden (nombre, apellido y celular) y se pregunta si se quiere continuar con el registro.

**HU1.4 – Consultar cliente**
Como barbero, quiero buscar a un cliente por su celular para ver sus datos.
Criterio de aceptación: se muestran nombre, apellido y celular. Si no existe, se informa "Cliente no encontrado".

## 6. Reglas de negocio

### 6.1. Identificador único
- Cada cliente se identifica por su **celular**.
- No puede haber dos clientes con el mismo celular.
- Una vez registrado, el celular no se modifica.

### 6.2. Prevención de clientes duplicados
- El `Set` rechaza automáticamente un celular repetido.
- Además, antes de registrar, se buscan coincidencias por nombre y apellido. Si las hay, se muestran para que el barbero verifique si es la misma persona. El sistema no decide solo, porque dos personas distintas pueden llamarse igual.
- Si se confirma que es otra persona (mismo nombre, otro celular), se registra normalmente.

### 6.3. Relación con los turnos
- Solo un cliente registrado puede tener turnos.
- Si al reservar el cliente no existe, se lo registra en ese momento con las mismas validaciones.

## 7. Validaciones

### 7.1. Nombre y apellido
Son obligatorios. No se permite guardar un campo vacío ni uno que contenga solamente espacios en blanco.

### 7.2. Celular
Es obligatorio. Se permite ingresarlo con espacios, guiones o prefijo internacional (+54); antes de guardarlo se quitan espacios y guiones, para que "351 555-1234" y "3515551234" se reconozcan como el mismo número. El número resultante debe tener solo dígitos (con un "+" opcional al inicio) y entre 8 y 15 dígitos.

## 8. Uso de Set en Java

Para administrar los clientes se utiliza un `Set<Cliente>`.

La igualdad entre dos objetos `Cliente` se determina por su **celular**: la clase `Cliente` implementa `equals()` y `hashCode()` tomando como referencia ese dato. Así, `add()` devuelve `false` si se intenta registrar un celular que ya existe, y el `Set` evita el duplicado sin lógica extra.

Con una `List` habría que recorrer todos los clientes antes de cada registro para comprobar que el celular no esté usado.

El celular no puede cambiar mientras el cliente forma parte del conjunto: si cambiara, el `Set` dejaría de encontrarlo, porque cambiaría su `hashCode()`.

El `Set` no detecta que dos registros con celulares distintos correspondan a la misma persona; para eso está el aviso por nombre y apellido (6.2).

## 9. Consultas que necesitan otros módulos

- **Módulo 2 (Gestión de turnos):** buscar un cliente por celular, saber si existe y registrarlo si no existe, sin manejar directamente la colección de clientes.
- **Módulo 4 (Historial y reportes):** cada cliente guarda su pila de cortes, que el Módulo 4 actualiza al registrar una atención y consulta para mostrar "lo de siempre".

## 10. Fuera de alcance (mejoras futuras)

Estas ideas se analizaron y quedan para una versión posterior:

- **Estado activo / inactivo y reactivación**, conservando los datos e historial de quienes dejan de venir.
- **Actualizar datos** (nombre, apellido o celular; este último sacando y volviendo a agregar al cliente en el Set).
- **Fotografía** opcional del cliente.
- **Fecha de alta** del cliente.
- **Guardar los datos** al cerrar el programa (archivos o base de datos).
- **Recuperación de clientes:** identificar a quienes llevan tiempo sin venir (por la fecha de su última atención) y ofrecerles promociones.

## 11. Resumen de responsabilidades

- **Clase `Cliente`:** representa los datos de cada cliente; igualdad por celular.
- **`Set<Cliente>`:** almacena los clientes y evita celulares repetidos.
- **Gestión de clientes:** registra, valida, busca por celular, avisa coincidencias por nombre y lista clientes.
