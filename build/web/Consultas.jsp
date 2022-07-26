<%@page import="Logica.Empleado"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
<title>Hotel - Alta Empleado</title>
<%@include file="header-page.jsp" %>
    <!--==============================content================================-->
      <section id="content">
        <div class="container_12">
           <div class="grid_8"> 
                <h2 class="top-1 p3">Consulta de reservas:</h2> <%-- Todas las reservas realizadas en un determinado día --%>
                <form action="SvConsultaReservas" method="POST">
                    
                    <p class="p2">Fecha a consultar: <input type="date" name="fecha_consulta"></p>
                    <input class="button" type="submit" value="Consultar"/>
                    
                </form>
                
                <p class="line-1"></p>
                <h2 class="top-1 p3">Consulta de huespedes registrados:</h2> <%-- Todos los huéspedes registrados en el sistema --%>
                <form action="SvConsultaHuespedes" method="POST">
                    
                    <input class="button" type="submit" value="Consultar Huespedes"/>
                    
                </form>
                
                <p class="line-1"></p>
                <h2 class="top-1 p3">Reservas de empleados:</h2>
                <form action="SvConsultaEmpleados" method="POST">
                    <% List<Empleado> empleados = (List) misesion.getAttribute("empleados"); 
                        if (!empleados.isEmpty()){

                    %>
                    <p class="p2">Seleccione el empleado: 
                        
                        <select name="empleado"> </p> 
                            
                                <% for(Empleado emp : empleados){ %>

                                <option value="<%= emp.getId() %>"><%= emp.getNombre() %></option>

                                <% } %>    
                            
                            </select>
                       <%
                        } %>
                    <input class="button" type="submit" value="Consultar Reservas"/>
                    
                </form>
                
                <p class="line-1"></p>
                <h2 class="top-1 p3">Reservas de Huespedes:</h2>
                <form action="SvConsultaReservasHuespedes" method="POST">

                    <p class="p2">DNI del huesped a Consultar: <input type="text" name="dni_huesped" placeholder="DNI Huesped"> </p>
                    <p class="p2">Fecha desde: <input type="date" name="fecha_desde"></p>
                    <p class="p2">Fecha hasta: <input type="date" name="fecha_hasta"></p>

                    <input class="button" type="submit" value="Consultar Reservas"/>

                </form>

                </div>
            <%@include file="sidebar.jsp" %>
            <div class="clear"></div>
        </div>
      </section>
    </div>
    <!--==============================footer=================================-->
<%@include file="footer.jsp" %>