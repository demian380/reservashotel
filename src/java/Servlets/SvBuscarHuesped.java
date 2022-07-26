package Servlets;

import Logica.Controladora;
import Logica.Huesped;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvBuscarHuesped", urlPatterns = {"/SvBuscarHuesped"})
public class SvBuscarHuesped extends HttpServlet {


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
        
        //Obteniendo los datos del Huesped del JSP
        String dni_huesped = request.getParameter("dni_huesped");
        
        //Conectando con la lógica
        Controladora cont = new Controladora();
        Huesped elHuesped = cont.traerHuesped(dni_huesped);
        
        request.getSession().setAttribute("elHuesped", elHuesped);
        request.getSession().setAttribute("dni_huesped_en_reserva", dni_huesped);
        
        //Armado de la Respuesta
        response.sendRedirect("AltaReserva3.jsp");
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
