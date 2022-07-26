package Servlets;

import Logica.Controladora;
import Logica.Huesped;
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


@WebServlet(name = "SvEditarHuesped", urlPatterns = {"/SvEditarHuesped"})
public class SvEditarHuesped extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        int id_hue = Integer.parseInt(request.getParameter("id_hue"));
        String dni_huesped = request.getParameter("dni_huesped");
        String nombre_huesped = request.getParameter("nombre_huesped");
        String apellido_huesped = request.getParameter("apellido_huesped");
        Date fecha_nac_hue = new Date();
        
        String fechaDateStrHue = request.getParameter("fecha_nac_huesped");
        SimpleDateFormat fn = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fecha_nac_hue = fn.parse(fechaDateStrHue);
        } catch (ParseException ex) {
            Logger.getLogger(SvEditarHuesped.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String direccion_huesped = request.getParameter("direccion_huesped");
        String profesion_huesped = request.getParameter("profesion_huesped");
            
        Controladora cont = new Controladora();
        Huesped unH = cont.traerHuespedPorId(id_hue);
        
        unH.setNombre(nombre_huesped);
        unH.setApellido(apellido_huesped);
        unH.setFecha_nac(fecha_nac_hue);
        unH.setDireccion(direccion_huesped);
        unH.setProfesion(profesion_huesped);
        
        cont.actualizarHuesped(unH);
        
        request.getSession().setAttribute("huespedes_registrados", cont.traerHuespedes());
        response.sendRedirect("ListadoHuespedes.jsp");
        
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        int id_hue = Integer.parseInt(request.getParameter("id_hue"));
        
        Controladora cont = new Controladora();
        Huesped elHuespedAEditar = cont.traerHuespedPorId(id_hue);
             
        request.getSession().setAttribute("elHuespedAEditar", elHuespedAEditar);
        
        response.sendRedirect("EditarHuesped.jsp");
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
