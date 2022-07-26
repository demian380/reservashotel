<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Logica.Huesped"%>
<%@page import="Logica.Empleado"%>
<%@page import="Logica.Habitacion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<title>Hotel - Editar Huesped</title>
<%@include file="header-page.jsp" %>
    <!--==============================content================================-->
      <section id="content">
        <div class="container_12">
           <div class="grid_8"> 
                <h2 class="top-1 p3">Datos del Huesped</h2>
                
                <%
                    Huesped elHuesped = (Huesped) misesion.getAttribute("elHuespedAEditar");
                %>

                <form action="SvEditarHuesped" method="get">    
                    <h2 class="top-1 p3">Huesped</h2>

                        <%
                            SimpleDateFormat fn = new SimpleDateFormat("yyyy-MM-dd");
                            String fecha_nac = fn.format(elHuesped.getFecha_nac());
                        %>
                        
                    
                        <p class="p2">DNI: <span style="color: #FFF"> <%= elHuesped.getDni() %> </span></p>
                        <input type="hidden" name="dni_huesped" value="<%= elHuesped.getDni() %>">
                        <p class="p2">Nombre: <input type="text" name="nombre_huesped" placeholder="Nombre" value="<%= elHuesped.getNombre() %>"> </p>
                        <p class="p2">Apellido: <input type="text" name="apellido_huesped" placeholder="Apellido" value="<%= elHuesped.getApellido() %>"> </p>
                        <p class="p2">Fecha de Nacimiento: <input type="date" name="fecha_nac_huesped" value="<%= fecha_nac %>"></p>
                        <p class="p2">Dirección: <input type="text" name="direccion_huesped" placeholder="Dirección" value="<%= elHuesped.getDireccion() %>"> </p>
                        <p class="p2">Profesion: <input type="text" name="profesion_huesped" placeholder="Profesion" value="<%= elHuesped.getProfesion() %>"> </p>
                        
                        <input type="hidden" name="id_hue" value="<%= elHuesped.getId() %>">
                    
                        <input class="button" type="submit" value="Modificar" onclick="alert('Datos modificados!')"/>
              
 
                </form>
                    
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
