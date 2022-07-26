<%@page import="Logica.Empleado"%>
<%@page import="Logica.Habitacion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<title>Hotel - Alta Reserva</title>
<%@include file="header-page.jsp" %>
    <!--==============================content================================-->
      <section id="content">
        <div class="container_12">
           <div class="grid_8"> 
                <h2 class="top-1 p3">Datos de la Reserva</h2>
                
                <%
                     
                    double precio_t = (Double) misesion.getAttribute("precio_total");
                    Habitacion laHab = (Habitacion) misesion.getAttribute("laHab");
                    Empleado elEmp = (Empleado) session.getAttribute("elEmpleado"); 
                    String nombre_emp = elEmp.getNombre() + " " + elEmp.getApellido();
                    if(laHab != null){
                        
                    
                %>
                <%-- <form action="SvAltaReserva2" method="POST">  --%>
                    <p class="p2">Empleado: <span style="color: #28a745"><%= nombre_emp %> </span></p>
                    <p class="p2">Cantidad de personas: <span style="color: #28a745"><%= session.getAttribute("cant") %> </span></p>
                    <p class="p2">Fecha de entrada: <span style="color: #28a745"><%= session.getAttribute("fechaInStr") %> </span></p>
                    <p class="p2">Fecha de salida: <span style="color: #28a745"><%= session.getAttribute("fechaOutStr") %> </span></p>
                    <p class="p2">Habitacion: <span style="color: #28a745"><%= laHab.getNombre() %> </span></p>
                    <p class="p2">Tipo de habitacion: <span style="color: #28a745"><%= laHab.getTipo() %> </span></p>
                    <p class="p2">Cantidad de Personas Máxima de la Habitacion: <span style="color: #28a745"><%= laHab.getCapacidad_max() %> </span></p>
                    <p class="p2">Precio por noche: <span style="color: #28a745">$ <%= laHab.getPrecio() %> </span></p>
                    <p class="p2">Precio total: <span style="color: #28a745">$ <%= precio_t %> </span></p>
                    
                    <h2 class="top-1 p3">Huesped</h2>
                
                <form action="SvBuscarHuesped" method="POST">    
                    <p class="p2">DNI: <input type="text" name="dni_huesped" placeholder="DNI"> </p>
                    <%-- <p class="p2">Nombre: <input type="text" name="nombre_huesped" placeholder="Nombre"> </p>
                    <p class="p2">Apellido: <input type="text" name="apellido_huesped" placeholder="Apellido"> </p>
                    <p class="p2">Fecha de Nacimiento: <input type="date" name="fecha_nac_huesped"></p>
                    <p class="p2">Dirección: <input type="text" name="direccion_huesped" placeholder="Dirección"> </p>
                    <p class="p2">Profesion: <input type="text" name="profesion_huesped" placeholder="Profesion"> </p> --%>
                    
                    <input class="button" type="submit" value="Buscar huesped"/>

                    
                </form>
                    <% }else{ %>
                    
                        <script> alert ('No se selecciono ninguna Habitabion.') </script>
                    
                    <% } %>

           </div>
            <div class="grid_4">
            <div class="left-1">
              <h2 class="top-1 p3"></h2>
              
              <h2 class="p3">Nuestras Sucursales</h2>
              <img src="images/page1-img4.png" alt="">
              <div class="lists">
                <ul class="list-1">
                  <li><a href="#">Asia</a></li>
                  <li><a href="#">Australia</a></li>
                  <li><a href="#">Africa</a></li>
                </ul>
                <ul class="list-1 last">
                  <li><a href="#">North America</a></li>
                  <li><a href="#">Europe</a></li>
                  <li><a href="#">Latin America</a></li>
                </ul>
              </div>
            </div>
          </div>
            <div class="clear"></div>
        </div>
      </section>
    </div>
    <!--==============================footer=================================-->
<%@include file="footer.jsp" %>