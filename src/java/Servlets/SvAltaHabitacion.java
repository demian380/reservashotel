package Servlets;

import Logica.Controladora;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SvAltaHabitacion", urlPatterns = {"/SvAltaHabitacion"})
public class SvAltaHabitacion extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Obteniendo los datos del JSP. Se almacenan en variables para utilizarlos en la conexión con la lógica más abajo
        int piso = Integer.parseInt(request.getParameter("piso"));
        String nombre = request.getParameter("nombre");
        String tipo = request.getParameter("tipo");
        double precio = Double.parseDouble(request.getParameter("precio"));
        String disponibilidad = request.getParameter("disponibilidad");
        int capacidad = Integer.parseInt(request.getParameter("capacidad"));
        
        //Sesión y asignación a variables para futura utilización en cualquier otro JSP("atributo", variable)
        request.getSession().setAttribute("piso", piso);
        request.getSession().setAttribute("nombre", nombre);
        request.getSession().setAttribute("tipo", tipo);
        request.getSession().setAttribute("precio", precio);
        request.getSession().setAttribute("disponibilidad", disponibilidad);
        request.getSession().setAttribute("capacidad", capacidad);
        
        //Conectando con la lógica
        Controladora cont = new Controladora();
        cont.crearHabitacion(piso, nombre, tipo, precio, disponibilidad, capacidad);
        
        //Armado de la Respuesta
        response.sendRedirect("AltaHabitacion.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Servlet utilizado para capturar los datos del formulario de Alta de Habitaciones y realizar la comunicación con la persistencia para el almacenado de la nueva habitación en la Base de Datos.";
    }// </editor-fold>

}
