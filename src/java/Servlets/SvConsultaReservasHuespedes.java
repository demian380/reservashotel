package Servlets;

import Logica.Controladora;
import Logica.Reserva;
import java.io.IOException;
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


@WebServlet(name = "SvConsultaReservasHuespedes", urlPatterns = {"/SvConsultaReservasHuespedes"})
public class SvConsultaReservasHuespedes extends HttpServlet {


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
        
        String dni = request.getParameter("dni_huesped");
        
        String fechaDesdeDateStr = request.getParameter("fecha_desde");
        SimpleDateFormat fn = new SimpleDateFormat("yyyy-MM-dd");
        //Inicializando el Date porque necesita un valor inicial por si llegara a fallar la asignación dentro del try
        Date fecha_desde = null;

        try {
            fecha_desde = fn.parse(fechaDesdeDateStr);
        } catch (ParseException ex) {
            Logger.getLogger(SvConsultaReservasHuespedes.class.getName()).log(Level.SEVERE, null, ex);
        }

        String fechaHastaDateStr = request.getParameter("fecha_hasta");
        Date fecha_hasta = null;

        try {
            fecha_hasta = fn.parse(fechaHastaDateStr);
        } catch (ParseException ex) {
            Logger.getLogger(SvConsultaReservasHuespedes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Controladora cont = new Controladora();
        List<Reserva> reservas_huesped = cont.traerReservasDeHuesped(dni, fecha_desde, fecha_hasta);

        request.getSession().setAttribute("reservas_huesped", reservas_huesped);
        
        //Seteo el DNI Huesped, fechaDesde y fechaHasta para ocuparlos nuevamente desde de Eliminar la reserva
        request.getSession().setAttribute("dni_hues", dni);
        request.getSession().setAttribute("fecha_desde", fecha_desde);
        request.getSession().setAttribute("fecha_hasta", fecha_hasta);
        
        response.sendRedirect("ListadoReservasHuesped.jsp");
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
