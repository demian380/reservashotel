# reservashotel
Trabajo Práctico Integrador de Java Web con JSP y JPA

Herramientas en uso:
Wamp Server 3.2.0
NetBeans 8.2
EclipseLink (JPA 2.1)
javax.persistence-2.0.0.jar
mysql-connector-java-5.1.23-bin.jar
Windows 10 64 bits

Base de Datos:
Nombre: hotel_bd
Usuario: root
Constraseña: (sin contraseña)
MariaDB

Supuestos:
Alta habitacion:
Habitaciones Multiples: Capacidad: Desde 4 hasta 8 personas como máximo (se establece el límite de cada una al dar de alta una nueva Habitacion y está limitado mediante un select con valores fijos). El campo Disponible quedó sin efecto por lo pronto pero al crear una habitación se setea en "Disponible" (no cambia su valor durante el uso del sistema actual).

Alta Empleado:
Se crea un Empleado y al mismo tiempo se debe crear un Usuario nuevo que quedará asociado a ese empleado. No hay restricciones en cuanto al nombre de usuario ni contraseña. Se controla mediante Javascript que se hayan ingresado correctamente las contraseñas.

Consultas
Consultas de reservas por fecha:
Se introduce la fecha de búsqueada y funciona correctamente.

Consultas -> Huespedes
En esta sección se listan los huespedes registrados en el sistema y se encuentra el ABML (el borrado, modificado y listado).

Eliminar Huesped:
 Al eliminar un huesped se elimina correctamente de la base de datos conjuntamente las reservas del mismo de las tablas "reserva", "reserva_huesped", "empleado_reserva" y habitacion_reserva".

Reserva:
Se buscan habitaciones del tipo seleccionado en  los options. Se introduce la cantidad de personas para hacer la verificación de que no superen la capacidad máxima de la habitacion sobre todo en las de tipo "Múltiple". Al traer las habitaciones disponibles se hacen estas comprobaciones. El empleado puede seleccionar una habitacion triple o multiple habiendo ingresado 1 o 2 personas solamente en el campo "Cantidad de Personas" e igualmente el sistema traería las habitaciones triple o multiple disponibles en el rango de fechas ya que la capacidad máxima lo permite.

Luego de seleccionar una habitacion disponible se solicita ingresar el DNI del Huesped; en caso de encontrar el dni en la base de datos entre los huespedes registrados, se mostraran los datos de la reserva y del Huesped para luego confirmar la reserva. En cambio, en caso de no encontrar el dni entre los usuarios registrados en el sistema, se mostraran los datos de la reserva y se permitira registrar los datos del nuevo Huesped bloqueando unicamente la posibilidad de modificar el dni ingresado, ya que puede haber ocurrido un error de tipeo y corresponda a un huesped ya registrado; en ese caso deberia retroceder y modificar el dni.
