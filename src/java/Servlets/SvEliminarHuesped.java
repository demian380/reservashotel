package Servlets;

import Logica.Controladora;
import Logica.Huesped;
import Logica.Reserva;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SvEliminarHuesped", urlPatterns = {"/SvEliminarHuesped"})
public class SvEliminarHuesped extends HttpServlet {


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
        
        int id_hue = Integer.parseInt(request.getParameter("id_hue"));
        
        Controladora cont = new Controladora();
        
        //Traer reservas del huesped, buscar esas reservas entre las Reservas, Empleados y Habitaciones (Serian todas las Reservas del sistema) y eliminar (destroy) cada objeto
        List<Reserva> reservas_del_huesped = cont.traerReservasDelHuesped(id_hue);
        
        cont.eliminarHuesped(id_hue);
        
        cont.actualizarReservasDelHuesped(reservas_del_huesped); //Elimina todos los obj Reserva de las Reservas que tenia el Huesped del sistema
        
        //HICE ESTOS DOS METODOS SIGUIENTES DUDANDO YA QUE PUSE EL CASCADE DEL ONETOMANY EN EMPLEADO Y EN HABITACION LUEGO DE HABER CREADO LA BD Y ESTAR PROBANDO 
        cont.actualizarReservasDelHuespedDeEmpleados(reservas_del_huesped); //Elimina todos los obj Reserva de los Empleados que tenia el Huesped
        cont.actualizarReservasDelHuespedDeHabitaciones(reservas_del_huesped); //Elimina todos los obj Reserva de las Habitaciones que tenia el Huesped
        
        List<Huesped> huespedes_registrados = cont.traerHuespedes();

        request.getSession().setAttribute("huespedes_registrados", huespedes_registrados);
        
        response.sendRedirect("ListadoHuespedes.jsp");
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
