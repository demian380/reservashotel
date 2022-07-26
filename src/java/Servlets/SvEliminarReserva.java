package Servlets;

import Logica.Controladora;
import Logica.Reserva;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SvEliminarReserva", urlPatterns = {"/SvEliminarReserva"})
public class SvEliminarReserva extends HttpServlet {

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
        processRequest(request, response);
        
        int id_res = Integer.parseInt(request.getParameter("id_res"));
        String dni = (String) request.getSession().getAttribute("dni_hues");
        
        SimpleDateFormat fdh = new SimpleDateFormat("yyyy-MM-dd");

        Date fecha_desde = (Date) request.getSession().getAttribute("fecha_desde");
        
        Date fecha_hasta = (Date) request.getSession().getAttribute("fecha_hasta");
        
        Controladora cont = new Controladora();
        
        
        cont.eliminarReserva(id_res);
        cont.eliminarReservaDeLosObjetos(id_res);
        
        //cont.eliminarReservaDeEmpleado(id_res);
        //cont.eliminarReservaDeHabitacion(id_res);
        
        
        //List<Reserva> reservas_huesped = cont.traerReservasDeHuesped(dni, fecha_desde, fecha_hasta);

        //request.getSession().setAttribute("reservas_huesped", reservas_huesped);
        
        //response.sendRedirect("ListadoReservasHuesped.jsp");
        response.sendRedirect("Consultas.jsp");
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
