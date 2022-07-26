package Servlets;

import Logica.Controladora;
import Logica.Empleado;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "SvUsuario", urlPatterns = {"/SvUsuario"})
public class SvUsuario extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Obteniendo los datos del JSP
        String uss = request.getParameter("username");
        String pass = request.getParameter("pass");
        
        Controladora control = new Controladora();
        boolean autorizado = control.verificarUsuario(uss, pass);
        
        if (autorizado == true){
            String nombre_empleado = control.getEmpleadoPorNombreUsuario(uss).getNombre();
            Empleado elEmpleado = control.getEmpleadoPorNombreUsuario(uss);
            List<Empleado> empleados = control.traerEmpleados();
            //Obtengo la sesion y le asigno el usuario y contraseña para validar
            HttpSession miSesion = request.getSession(true);
            miSesion.setAttribute("uss", uss);
            miSesion.setAttribute("pass", pass);
            miSesion.setAttribute("nombre_empleado", nombre_empleado);
            miSesion.setAttribute("elEmpleado", elEmpleado);
            miSesion.setAttribute("empleados", empleados); //Se usa en Consultas.jsp para cargar los empleados existentes
            
            response.sendRedirect("Principal.jsp");
        }
        else{
            response.sendRedirect("Login.jsp");
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
