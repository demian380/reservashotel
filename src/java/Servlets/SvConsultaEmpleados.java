package Servlets;

import Logica.Controladora;
import Logica.Reserva;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvConsultaEmpleados", urlPatterns = {"/SvConsultaEmpleados"})
public class SvConsultaEmpleados extends HttpServlet {

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
        
        int id_emp = Integer.parseInt(request.getParameter("empleado"));
 
        //Conectando con la lógica
        Controladora cont = new Controladora();
        List<Reserva> reservas_emp = cont.traerReservasDeEmpleado(id_emp);
        
        request.getSession().setAttribute("reservas_de_empleado", reservas_emp);
        
        response.sendRedirect("ListadoReservasEmpleado.jsp");
                
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
