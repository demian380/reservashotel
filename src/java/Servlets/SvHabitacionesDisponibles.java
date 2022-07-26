package Servlets;

import Logica.Controladora;
import Logica.Habitacion;
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

@WebServlet(name = "SvHabitacionesDisponibles", urlPatterns = {"/SvHabitacionesDisponibles"})
public class SvHabitacionesDisponibles extends HttpServlet {


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
        
        //Obteniendo los datos del JSP. Se almacenan en variables para utilizarlos en la conexión con la lógica más abajo
        int cantidad = Integer.parseInt(request.getParameter("cant"));
        
        String fechaInDateStr = request.getParameter("checkin");
        SimpleDateFormat fni = new SimpleDateFormat("yyyy-MM-dd");
        //Inicializando el Date porque necesita un valor inicial por si llegara a fallar la asignación dentro del try
        Date fechain = null;
        try {
            //Convirtiendo el String recibido del JSP con la instancia de la clase SimpleDateFormat y asignandolo al objeto Date.  
            fechain = fni.parse(fechaInDateStr);
        } catch (ParseException ex) {
            Logger.getLogger(SvIntermediarioReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String fechaOutDateStr = request.getParameter("checkout");
        //Estableciendo un formato de fecha compatible con la DB
        SimpleDateFormat fno = new SimpleDateFormat("yyyy-MM-dd");
        //Inicializando el Date porque necesita un valor inicial por si llegara a fallar la asignación dentro del try
        Date fechaout = null;
        try {
            //Convirtiendo el String recibido del JSP con la instancia de la clase SimpleDateFormat y asignandolo al objeto Date.  
            fechaout = fno.parse(fechaOutDateStr);
        } catch (ParseException ex) {
            Logger.getLogger(SvIntermediarioReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String tipo = request.getParameter("tipo");

        //Seteando las variables en la sesión para poder ocuparlos en cualquier otro JSP de ser necesario
        request.getSession().setAttribute("cant", cantidad);
        request.getSession().setAttribute("checkin", fechain);
        request.getSession().setAttribute("checkout", fechaout);
        request.getSession().setAttribute("fechaInStr", fechaInDateStr);
        request.getSession().setAttribute("fechaOutStr", fechaOutDateStr);
        request.getSession().setAttribute("tipo", tipo);
        
        //Mediante la controladora logica buscar dentro de las habitaciones de ese tipo las que no tengan reservas en ese rango de fechas
        //Conectando con la lógica
        Controladora cont = new Controladora();
        List<Habitacion> habitaciones_disp = cont.buscarHabitaciones(cantidad, tipo, fechain, fechaout);
        
        long cant_dias = cont.calcularCantDias(fechain, fechaout);
        
        request.getSession().setAttribute("cant_dias", cant_dias);
        request.getSession().setAttribute("habitaciones_disp", habitaciones_disp);
        
        //Armado de la Respuesta
        response.sendRedirect("HabitacionesDisponibles.jsp");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
