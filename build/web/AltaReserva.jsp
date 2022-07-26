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
                <h2 class="top-1 p3">Alta Reserva</h2>

                <form action="SvHabitacionesDisponibles" method="POST">
                    
                    <p class="p2">Cantidad de personas: <input type="number" name="cant" value="<%= session.getAttribute("cantidad") %>" min="1" max="8">&nbsp; Cant. Máx.: 8</p>
                    <p class="p2">Fecha de entrada: <input type="date" name="checkin" value="<%= session.getAttribute("fechaInStr") %>"> </p>
                    <p class="p2">Fecha de salida: <input type="date" name="checkout" value="<%= session.getAttribute("fechaOutStr") %>"> </p>
                    <p>Tipo de Habitación: <input type="radio" name="tipo" value="Single"> Single
                                           <input type="radio" name="tipo" value="Doble"> Doble
                                           <input type="radio" name="tipo" value="Triple"> Triple
                                           <input type="radio" name="tipo" value="Multiple"> Múltiple </p>
                    
                    <input class="button" type="submit" value="Buscar Disponibilidad"/>
                    
                    
                    <%--<p class="p2">Precio total: <span>AR$ <%= //usu %> .-</span></p> --%>
                    
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

