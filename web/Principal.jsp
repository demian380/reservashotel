<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
<title>Hotel - Página Principal</title>
<%@include file="header-home.jsp" %>
  <!--==============================content================================-->
  <section id="content">
    <div class="container_12">
      <div class="grid_8">
        <h2 class="top-1 p3">Bienvenido <%= usu %></h2>
        <p class="p2">Recuerda que contamos con habitaciones Múltiples con un máximo de 8 personas. </p>
        <p class="line-1">Todos los servicios se encuentran disponibles para los clientes. El horario del desayuno se cambió, nuevo horario de 8 a 10hs. </p>
        
      </div>
      <%@include file="sidebar.jsp" %>
      <div class="clear"></div>
    </div>
  </section>
</div>
<!--==============================footer=================================-->
<%@include file="footer.jsp" %>

