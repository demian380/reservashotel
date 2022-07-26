<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="es">
<head>
<title>Hotel - Alta Empleado</title>
<%@include file="header-page.jsp" %>
    <!--==============================content================================-->
      <section id="content">
        <div class="container_12">
           <div class="grid_8"> 
            <h2 class="top-1 p3">Formulario de alta de nueva habitacion</h2>
                <form action="SvAltaHabitacion" method="POST">
                    <p class="p2">Piso: <input type="number" name="piso" placeholder="Ingrese el piso" min="1" max="20"> </p>
                    <p class="p2">Nombre: <input type="text" name="nombre" placeholder="Nombre"> </p>
                    <p class="p2">Tipo de Habitación: <input type="radio" name="tipo" value="Single">Single
                                           <input type="radio" name="tipo" value="Doble">Doble
                                           <input type="radio" name="tipo" value="Triple">Triple
                                           <input type="radio" name="tipo" value="Multiple">Múltiple </p>
                    <p class="p2">Precio: <input type="number" name="precio" placeholder="Precio en pesos ARG"> </p>
                    <p class="p2">Disponibilidad: <input type="text" name="disponibilidad" value="Disponible" readonly> </p>
                    <p class="p2">Capacidad Máxima de Personas: <select name="capacidad"> </p>
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                        <option>6</option>
                        <option>7</option>
                        <option>8</option>
                    </select> <br><br>
                    <input class="button" type="submit" value="Enviar" onclick="alert('Alta realizada con éxito!')"/>  

                </form>
            </div>
         <%@include file="sidebar.jsp" %>
         <div class="clear"></div>
     </div>
   </section>
 </div>
    <!--==============================footer=================================-->
<%@include file="footer.jsp" %>
