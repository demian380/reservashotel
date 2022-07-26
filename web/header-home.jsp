<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<!--[if lt IE 9]>
<script src="js/html5.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="css/ie.css">
<![endif]-->
</head>
<body>
    <% 
        HttpSession misesion = request.getSession();
        String usu = (String) misesion.getAttribute("nombre_empleado");
        if (usu == null){
            response.sendRedirect("Login.jsp");
        }
        else{
        %>
    
<div class="main">
  <!--==============================header=================================-->
  <header>
      
    <div>
      <h1><a href="index.html"><img src="images/logo.png" alt=""></a></h1>
      
      <div class="social-icons"> <span> Bienvenido: <span style="color: red"><%= usu %> </span></span> <span>Follow Us:</span> <a href="#" class="icon-3"></a> <a href="#" class="icon-2"></a> <a href="#" class="icon-1"></a> </div>
      <div id="slide">
        <div class="slider">
          <ul class="items">
            <li><img src="images/slider-1.jpg" alt=""></li>
            <li><img src="images/slider-2.jpg" alt=""></li>
            <li><img src="images/slider-3.jpg" alt=""></li>
          </ul>
        </div>
        <a href="#" class="prev"></a><a href="#" class="next"></a> </div>
      <nav>
        <ul class="menu">
          <li><a href="Principal.jsp">Principal</a></li>
          <li><a href="AltaReserva.jsp">Reserva</a></li>
          <li><a href="Consultas.jsp">Consultas</a></li>
          <li><a href="AltaHabitacion.jsp">Alta Habitacion</a></li>
          <li><a href="AltaEmpleado.jsp">Alta Empleado</a></li>
        </ul>
      </nav>
    </div>
  </header>