package Servlets;

import Logica.Controladora;
import Logica.Huesped;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SvConsultaHuespedes", urlPatterns = {"/SvConsultaHuespedes"})
public class SvConsultaHuespedes extends HttpServlet {


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
        
        Controladora cont = new Controladora();
        List<Huesped> huespedes_registrados = cont.traerHuespedes();
        
        request.getSession().setAttribute("huespedes_registrados", huespedes_registrados);
        
        response.sendRedirect("ListadoHuespedes.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
