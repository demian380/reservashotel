package Servlets;

import Logica.Controladora;
import Logica.Empleado;
import Logica.Habitacion;
import Logica.Huesped;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SvAltaReserva2", urlPatterns = {"/SvAltaReserva2"})
public class SvAltaReserva2 extends HttpServlet {

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
        
        Date checkin = (Date) request.getSession().getAttribute("checkin");
        Date checkout = (Date) request.getSession().getAttribute("checkout");
        double precio_t = (Double) request.getSession().getAttribute("precio_total");
        int cantidad_personas = (Integer) request.getSession().getAttribute("cant");
        Empleado elEmpleado = (Empleado) request.getSession().getAttribute("elEmpleado");
        Habitacion laHab = (Habitacion) request.getSession().getAttribute("laHab");
        Huesped elHuesped = (Huesped) request.getSession().getAttribute("elHuesped");
        
        //Obteniendo los datos del Huesped del JSP
        String dni_huesped = request.getParameter("dni_huesped");
        String nombre_huesped = request.getParameter("nombre_huesped");
        String apellido_huesped = request.getParameter("apellido_huesped");
        
        //Inicializando el Date porque necesita un valor inicial por si llegara a fallar la asignación dentro del try
        Date fecha_nac_hue = new Date();
        
        String fechaDateStrHue = request.getParameter("fecha_nac_huesped");
        //Estableciendo un formato de fecha compatible con la DB
        SimpleDateFormat fn = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            //Convirtiendo el String recibido del JSP con la instancia de la clase SimpleDateFormat y asignandolo al objeto Date.
            fecha_nac_hue = fn.parse(fechaDateStrHue);
            
        } catch (ParseException ex) {
            Logger.getLogger(SvAltaReserva2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String direccion_huesped = request.getParameter("direccion_huesped");
        String profesion_huesped = request.getParameter("profesion_huesped");
        
        
        //Conectando con la lógica
        Controladora cont = new Controladora();
        Date fecha_reserva = new Date(); //Fecha del dia en que se cree la reserva
        
        if(elHuesped == null){ //Si no existe el huesped en el sistema que se cree uno nuevo, si ya existe (ya seteado en la sesion) que se agregue la reserva en ese
            Huesped unHuesped = cont.crearHuesped(dni_huesped, nombre_huesped, apellido_huesped, fecha_nac_hue, direccion_huesped, profesion_huesped);
            cont.crearReserva(fecha_reserva, checkin, checkout, precio_t, cantidad_personas, unHuesped, elEmpleado, laHab);
        }else{
            cont.crearReserva(fecha_reserva, checkin, checkout, precio_t, cantidad_personas, elHuesped, elEmpleado, laHab);
        }
        
        //Armado de la Respuesta
        response.sendRedirect("Principal.jsp");
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
