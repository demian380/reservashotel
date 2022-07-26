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

@WebServlet(urlPatterns = {"/SvConsultaReservas"})
public class SvConsultaReservas extends HttpServlet {


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
        
        //Obteniendo los datos del JSP.
        String fechaConsultaDateStr = request.getParameter("fecha_consulta");
        SimpleDateFormat fn = new SimpleDateFormat("yyyy-MM-dd");
        //Inicializando el Date porque necesita un valor inicial por si llegara a fallar la asignación dentro del try
        Date fecha_consulta = null;
        try {
            fecha_consulta = fn.parse(fechaConsultaDateStr);
        } catch (ParseException ex) {
            Logger.getLogger(SvConsultaReservas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Seteando las variables en la sesión para poder ocuparlos en cualquier otro JSP de ser necesario
        request.getSession().setAttribute("fecha_consulta", fecha_consulta);
        request.getSession().setAttribute("fecha_consultaStr", fechaConsultaDateStr);
        
        //Conectando con la lógica
        Controladora cont = new Controladora();
        System.out.println("Fecha a consultar: " + fecha_consulta);
        List<Reserva> reservas_por_fecha = cont.traerReservas(fecha_consulta);
        
        //Setear el listado de reservas en la sesion
        request.getSession().setAttribute("reservas_por_fecha", reservas_por_fecha);
        
        
        //Armado de la Respuesta
        response.sendRedirect("ListadoReservasPorFecha.jsp");
        
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
