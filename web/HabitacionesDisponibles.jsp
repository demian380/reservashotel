<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Logica.Habitacion"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<title>Hotel - Habitaciones Disponibles</title>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="fonts/icomoon/style2.css">

    <link rel="stylesheet" href="css/owl.carousel.min.css">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    
    <!-- Style -->
    <link rel="stylesheet" href="css/style2.css">
<link rel="stylesheet" type="text/css" media="screen" href="css/reset.css">
<link rel="stylesheet" type="text/css" media="screen" href="css/style.css">
<link rel="stylesheet" type="text/css" media="screen" href="css/grid_12.css">
<link rel="stylesheet" type="text/css" media="screen" href="css/slider.css">
<link rel="stylesheet" type="text/css" media="screen" href="css/jqtransform.css">
<script src="js/jquery-1.7.min.js"></script>
<script src="js/jquery.easing.1.3.js"></script>
<script src="js/cufon-yui.js"></script>
<script src="js/vegur_400.font.js"></script>
<script src="js/Vegur_bold_700.font.js"></script>
<script src="js/cufon-replace.js"></script>
<script src="js/tms-0.4.x.js"></script>
<script src="js/jquery.jqtransform.js"></script>
<script src="js/FF-cash.js"></script>
<script src="js/validarpass.js"> </script>
<script>
$(document)
    .ready(function () {
    $('.form-1')
        .jqTransform();
    $('.slider')
        ._TMS({
        show: 0,
        pauseOnHover: true,
        prevBu: '.prev',
        nextBu: '.next',
        playBu: false,
        duration: 1000,
        preset: 'fade',
        pagination: true,
        pagNums: false,
        slideshow: 7000,
        numStatus: false,
        banners: false,
        waitBannerAnimation: false,
        progressBar: false
    })
});

</script>
<script>
document.addEventListener("DOMContentLoaded", function() {
document.getElementById("formulario").addEventListener('submit', validarFormulario); 
});
function validarFormulario(evento) {
  evento.preventDefault();
  var sel = document.getElementById('seleccion').value;
  if(sel.length == 0) {
    alert('Debe Seleccionar una Habitacion.');
    return;
  }
  this.submit();
}
</script>
<!--[if lt IE 9]>
<script src="js/html5.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="css/ie.css">
<![endif]-->
</head>
<body>
    <% 
    HttpSession misesion = request.getSession();
    String usu = (String) misesion.getAttribute("uss");
    if (usu == null){
        response.sendRedirect("Login.jsp");
    }
    else{
    %>
    <div class="main">
    <!--==============================header=================================-->
    <header>

      <div>
        <h1><a href="Principal.jsp"><img src="images/logo.png" alt=""></a></h1>

        <div class="social-icons"> <span> Usuario activo: <span style="color: red"><%= usu %> </span></span> <span>Follow Us:</span> <a href="#" class="icon-3"></a> <a href="#" class="icon-2"></a> <a href="#" class="icon-1"></a> </div>
        <div id="slide">
            <div class="slider" style="background-image: url('images/slider-1.jpg');">
 
          </div>
        <nav>
          <ul class="menu">
            <li class="current"><a href="Principal.jsp">Principal</a></li>
            <li><a href="AltaReserva.jsp">Reserva</a></li>
            <li><a href="Consultas.jsp">Consultas</a></li>
            <li><a href="AltaHabitacion.jsp">Alta Habitacion</a></li>
            <li><a href="AltaEmpleado.jsp">Alta Empleado</a></li>
          </ul>
        </nav>
      </div>
    </header>
    <!--==============================content================================-->
        <div class="content">

          <div class="container">
            <h2 class="top-1 p3">Habitaciones Disponibles </h2>
            <% 
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");  
                String fecha_in = formato.format(misesion.getAttribute("checkin"));
                String fecha_out = formato.format(misesion.getAttribute("checkout"));
            %>
            <h3>Desde: <span style="color: #28a745"> <%= fecha_in %> </span></h3>
            <h3>Hasta: <span style="color: #28a745"> <%= fecha_out %> </span></h3>
            
            <div class="table-responsive custom-table-responsive">
                <form action="SvAltaReserva" id="formulario" method="POST">
                
                <table class="table custom-table">
                  <thead>
                    <tr>  

                      <th scope="col">

                        </label>
                      </th>

                      <th scope="col">Cant. Máx.</th>
                      <th scope="col">Piso</th>
                      <th scope="col">Nombre</th>
                      <th scope="col">Tipo</th>
                      <th scope="col">Precio p/ Noche</th>
                      <th scope="col">Total</th>
                    </tr>
                  </thead>
                  <tbody>
                      
                      <% 
                          List<Habitacion> habitaciones = (List) misesion.getAttribute("habitaciones_disp"); 
                          long cant_d = Long.valueOf(String.valueOf(misesion.getAttribute("cant_dias")));
                          if (!habitaciones.isEmpty()){
                              
                          
                            for (Habitacion hab : habitaciones){

                      %>
                                <tr scope="row">
                                  <th scope="row">
                                    <label class="control control--checkbox">
                                        <input type="radio" name="seleccion" id="seleccion" value="<%= hab.getId() %>"/>
                                      <div class="control__indicator"></div>
                                    </label>
                                  </th>
                                  <td>
                                    <%= hab.getCapacidad_max() %>
                                    <input type="hidden" name="id_hab" id="id_h" value="<%= hab.getId() %>"
                                  </td>
                                  <td><a href="#"><%= hab.getPiso() %></a></td>
                                  <td>
                                    <%= hab.getNombre() %>
                                    <small class="d-block">Habitaciones confortables con la mejor tecnología existente.</small>
                                  </td>
                                  <td><%= hab.getTipo() %></td>
                                  <td>$ <%= hab.getPrecio() %>
                                  <% 
                                  double precio_total = cant_d * hab.getPrecio();

                                  %>
                                  </td>
                                  <td>$ <%= precio_total %>
                                  <input type="hidden" name="precio_total" value="<%= precio_total %>"
                                  </td>
                                </tr>
                                <tr class="spacer"><td colspan="100"></td></tr>


                        <% } %> <%-- Cierra el For --%>
                            </tbody>
                        </table>
                    <input class="button" type="submit" value="Reservar"/>
                    <% } else { %>
                                <script> alert ('No hay Habitaciones Disponibles!') </script>
                            </tbody>
                        </table>
                    <input class="button" type="hidden" value="Reservar"/>
                    <% } %>
                  
              </form>
              
            </div>
          </div>
        </div>
                        
      <div class="clear"></div>
    </div>
                        
    
    <!--==============================footer=================================-->
    <footer>
                        
      <p>© 2021 Hard Rock Hotel</p>
      <p>Website Template by <a target="_blank" href="http://www.templatemonster.com/">TemplateMonster.com</a></p>
      
    </footer>
    <% }  %>
    <script>Cufon.now();</script>
                        
    
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/main2.js"></script>
</body>
</html>
