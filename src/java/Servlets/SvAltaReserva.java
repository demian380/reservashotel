package Servlets;

import Logica.Controladora;
import Logica.Habitacion;
import java.io.IOException;
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
import javax.swing.JOptionPane;


@WebServlet(name = "SvAltaReserva", urlPatterns = {"/SvAltaReserva"})
public class SvAltaReserva extends HttpServlet {


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
        
        if(request.getParameter("seleccion") != null){
            //Obteniendo los datos del JSP. Se almacenan en variables para utilizarlos en la conexión con la lógica más abajo
            int id_hab = Integer.parseInt(request.getParameter("seleccion"));    

            Date checkin = (Date) request.getSession().getAttribute("checkin"); 
            Date checkout = (Date) request.getSession().getAttribute("checkout");

            //Seteando las variables en la sesión para poder ocuparlos en cualquier otro JSP de ser necesario
            request.getSession().setAttribute("id_hab", id_hab);
            
            //Conectando con la lógica
            Controladora cont = new Controladora();
            Habitacion laHab = cont.traerHabitacion(id_hab);
            if(laHab != null){
                double precio_total = cont.calcularPrecioTotal(laHab, checkin, checkout);

                request.getSession().setAttribute("laHab", laHab);
                request.getSession().setAttribute("precio_total", precio_total);

                //Armado de la Respuesta
                response.sendRedirect("AltaReserva2.jsp");
            } else {
                //Armado de la Respuesta
                response.sendRedirect("HabitacionesDisponibles.jsp");
            }
        
        } else {
            response.sendRedirect("HabitacionesDisponibles.jsp"); //Si no seleccionó ninguna habitacion disponible volverá a la misma sección de seleccion de habitacion
        }
  
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
