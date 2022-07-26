<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <script type="text/javascript"> 

    function comprobarPasswords(){
        // Obtengo los valores de los campos de contraseñas 
        pass1 = document.getElementById('pass');
        pass2 = document.getElementById('pass2');
        
        // Verificamos si las constraseñas no coinciden
        if (pass1.value != pass2.value) {
            alert ('Las contraseñas ingresadas NO coinciden.')
            return false;
        }

        else {
            alert('Alta realizada con éxito!')
            return true;
        }
    }

    </script>
<title>Hotel - Alta Empleado</title>
<%@include file="header-page.jsp" %>
    <!--==============================content================================-->
      <section id="content">
        <div class="container_12">
           <div class="grid_8"> 
                <h2 class="top-1 p3">Alta Empleado</h2>

                <form action="SvAltaEmpleado" method="POST">
                    <p class="p2">DNI: <input type="text" name="dni" placeholder="DNI"> </p>
                    <p class="p2">Nombre: <input type="text" name="nombre" placeholder="Nombre"> </p>
                    <p class="p2">Apellido: <input type="text" name="apellido" placeholder="Apellido"> </p>
                    <p class="p2">Fecha de Nacimiento: <input type="date" name="fecha_nac"></p>
                    <p class="p2">Dirección: <input type="text" name="direccion" placeholder="Dirección"> </p>
                    <p class="p2">Cargo: <input type="text" name="cargo" placeholder="Cargo"> </p>

                    <h2 class="top-1 p3">Crear Usuario</h2>
                    <p class="p2">Nombre usuario: <input type="text" name="nombre_uss"> </p>
                    <p class="p2">Contraseña: <input type="password" id="pass" name="pass"> </p>
                    <p class="p2">Repita la Contraseña: <input type="password" id="pass2" name="pass2" onfocusout="validarpass()"> </p>

                    <input class="button" type="submit" value="Enviar" onclick="return comprobarPasswords()"/>  

                </form>
           </div>
            <%@include file="sidebar.jsp" %>
            <div class="clear"></div>
        </div>
      </section>
    </div>
    <!--==============================footer=================================-->
<%@include file="footer.jsp" %>
