package Servlets;

import Logica.Controladora;
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


@WebServlet(name = "SvAltaEmpleado", urlPatterns = {"/SvAltaEmpleado"})
public class SvAltaEmpleado extends HttpServlet {


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
        
        //Obteniendo los datos del JSP (de la solicitud/request q hace el cliente al servidor). Se almacenan en variables para utilizarlos en la conexión con la lógica más abajo
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        
        String fechaDateStr = request.getParameter("fecha_nac");
        //Estableciendo un formato de fecha compatible con la DB
        SimpleDateFormat fn = new SimpleDateFormat("yyyy-MM-dd");
        //Inicializando el Date porque necesita un valor inicial por si llegara a fallar la asignación dentro del try
        Date fecha_nac = null;
        try {
            //Convirtiendo el String recibido del JSP con la instancia de la clase SimpleDateFormat y asignandolo al objeto Date.  
            fecha_nac = fn.parse(fechaDateStr);
        } catch (ParseException ex) {
            Logger.getLogger(SvAltaEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String direccion = request.getParameter("direccion");
        String cargo = request.getParameter("cargo");
        String uss = request.getParameter("nombre_uss");
        String pass = request.getParameter("pass");
        
        //Seteando las variables en la sesión para poder ocuparlos en cualquier otro JSP de ser necesario
        request.getSession().setAttribute("dni", dni);
        request.getSession().setAttribute("nombre", nombre);
        request.getSession().setAttribute("apellido", apellido);
        request.getSession().setAttribute("fecha_nac", fecha_nac);
        request.getSession().setAttribute("direccion", direccion);
        request.getSession().setAttribute("cargo", cargo);
        
        request.getSession().setAttribute("nombre_uss", uss);
        request.getSession().setAttribute("pass", pass);
        
        //Conectando con la lógica
        Controladora cont = new Controladora();
        cont.crearEmpleado(dni, nombre, apellido, fecha_nac, direccion, cargo, uss, pass);
        
        //Armado de la Respuesta
        response.sendRedirect("AltaEmpleado.jsp");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
