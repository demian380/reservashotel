package Logica;

import Persistencia.ControladoraPersistencia;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Controladora {
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    
    
    public void crearHabitacion (int piso, String nombre, String tipo, double precio, String disp, int capacidad){
        
        Habitacion unaHab = new Habitacion();
        
        unaHab.setPiso(piso);
        unaHab.setNombre(nombre);
        unaHab.setTipo(tipo);
        unaHab.setPrecio(precio);
        unaHab.setDisponibilidad(disp);
        unaHab.setCapacidad_max(capacidad);
        
        controlPersis.crearHabitacion(unaHab);

    }
    
    public boolean verificarUsuario(String uss, String pass){
        
        List<Usuario> listaUsuarios = controlPersis.traerUsuarios();
        if(listaUsuarios != null){
            
            for(Usuario usu : listaUsuarios){
                
                if(usu.getNombre().equals(uss) && usu.getContrasenia().equals(pass)){
                    
                    return true;
                }
            }
        }
        return false;
    }

    public void crearEmpleado(String dni, String nombre, String apellido, Date fecha_nac, String direccion, String cargo, String uss, String pass) {
        //Creando y seteando los atributos del usuario nuevo con los valores recibidos del JSP
        Usuario us = new Usuario();
        us.setNombre(uss);
        us.setContrasenia(pass);
        
        //Creando y seteando los valores del nuevo empleado a cargar en la DB con los datos recibidos del JSP
        Empleado emp = new Empleado();
        emp.setDni(dni);
        emp.setNombre(nombre);
        emp.setApellido(apellido);
        emp.setFecha_nac(fecha_nac);
        emp.setDireccion(direccion);
        emp.setUnUsuario(us);
        emp.setCargo(cargo);
        
        //Creando el nuevo usuario y empleado en la DB mediante la controladora de la Persistencia
        controlPersis.crearUsuario(us);
        controlPersis.crearEmpleado(emp);
        
    }

    public Empleado getEmpleadoPorNombreUsuario(String uss) {
        List<Usuario> usuarios = controlPersis.traerUsuarios();
        if(usuarios != null){
            
            for(Usuario usu : usuarios){
                
                if(usu.getNombre().equals(uss)){
                    
                    List<Empleado> empleados = controlPersis.traerEmpleados();
                    if(empleados != null){

                        for(Empleado emp : empleados){

                            if(emp.getUnUsuario().getId() == usu.getId()){

                                return emp;
                            }
                        }
                    }
                    return null;
                }
            }
        }
        return null;
    }
    
    public boolean estaEnElRango(Date fecha_prueba, Date checkin, Date checkout) {
            return !(fecha_prueba.before(checkin) || fecha_prueba.after(checkout));
    }

    public List<Habitacion> buscarHabitaciones(int cantidad, String tipo, Date fechain, Date fechaout) {
        
        List<Habitacion> habitaciones_disponibles = new ArrayList<Habitacion>();
        List<Habitacion> habitaciones = controlPersis.traerHabitaciones();
        if (habitaciones != null){
            
            for (Habitacion hab : habitaciones){
                 
                if (cantidad <= hab.getCapacidad_max()){  //Comprobando que la cantidad de personas seleccionada no supere la cap max de la habitacion
                    
                    if (hab.getTipo().equals(tipo)){ //Comprobando que sea del tipo de habitacion seleccionada
                        
                        List<Reserva> reservas = hab.getReservas(); //Traigo las reservas de esa habitacion
                        
                        if (!reservas.isEmpty()){ //Si tiene reservas hechas se analizan los rangos de fechas para ver que no esté reservada
                                
                            for (Reserva res : reservas){
                                
                                boolean esta_reservada_fechain = this.estaEnElRango(res.getFecha_in(), fechain, fechaout); //Se analiza si la fecha de ingreso está en un rango de fechas de alguna reserva de la habitacion
                                if (!esta_reservada_fechain){

                                    boolean esta_reservada_fechaout = this.estaEnElRango(res.getFecha_out(), fechain, fechaout); //Se analiza si la fecha de salida está en un rango de fechas de alguna reserva de la habitacion
                                    if (!esta_reservada_fechaout){

                                        habitaciones_disponibles.add(hab);
                                        
                                    }

                                }
                            }

                        }else{ //Si no tiene reservas realizadas se agrega al listado de habitaciones disponibles
                            
                            habitaciones_disponibles.add(hab);
                            
                        }

                    }

                }
                
            }
            
        }else{
            System.out.println("No se encontraron habitaciones creadas.");
        }
        
        return habitaciones_disponibles;

    }

    public long calcularCantDias(Date fechain, Date fechaout) {
        long diffEnMinutos = Math.abs(fechaout.getTime() - fechain.getTime()); 
        long diff = TimeUnit.DAYS.convert(diffEnMinutos, TimeUnit.MILLISECONDS);
        
        return diff;
    }

    public Habitacion traerHabitacion(int id_hab) { 
        List<Habitacion> habitaciones = controlPersis.traerHabitaciones(); //Creo que aca podia haber usado el findHabitacion del JPA (pa la próxima)
        if(habitaciones != null){
            
            for(Habitacion hab : habitaciones){
                
                if(hab.getId() == id_hab){
                    return hab;
                }
            }
        }
        return null;
    }

    public double calcularPrecioTotal(Habitacion laHab, Date checkin, Date checkout) {
        long cant_dias = this.calcularCantDias(checkin, checkout);
        double precio_t = laHab.getPrecio() * cant_dias;
        return precio_t;
    }

    public Huesped crearHuesped(String dni_huesped, String nombre_huesped, String apellido_huesped, Date fecha_nac_hue, String direccion_huesped, String profesion_huesped) {
        
        Huesped unH = new Huesped();
        unH.setDni(dni_huesped);
        unH.setNombre(nombre_huesped);
        unH.setApellido(apellido_huesped);
        unH.setFecha_nac(fecha_nac_hue);
        unH.setDireccion(direccion_huesped);
        unH.setProfesion(profesion_huesped);
        
        controlPersis.crearHuesped(unH);
        return unH;
    }
    
    public void crearReserva(Date fecha_reserva, Date checkin, Date checkout, double precio_t, int cantidad_personas, Huesped unHuesped, Empleado unEmp, Habitacion unaHab) {
        
        Reserva unaReserva = new Reserva();
        unaReserva.setFecha_reserva(fecha_reserva);
        unaReserva.setFecha_in(checkin);
        unaReserva.setFecha_out(checkout);
        unaReserva.setPrecio_total(precio_t);
        unaReserva.setCant_personas(cantidad_personas);
        unaReserva.setUnEmpleado(unEmp);
        unaReserva.setUnaHabitacion(unaHab);
        
        unHuesped.agregarReserva(unaReserva);
        unEmp.agregarReserva(unaReserva);
        unaHab.agregarReserva(unaReserva);
        
                
        controlPersis.crearReserva(unaReserva);
        
        controlPersis.actualizarHuesped(unHuesped);
        controlPersis.actualizarEmpleado(unEmp);
        controlPersis.actualizarHabitacion(unaHab);
    }

    public Huesped traerHuesped(String dni_h) {
        List<Huesped> huespedes = controlPersis.traerHuespedes();
        if(huespedes != null){
            
            for(Huesped unHuesped : huespedes){
                
                if(unHuesped.getDni().equals(dni_h)){
                    
                    return unHuesped;
                }
            }
        }
        return null;
    }

    public List<Reserva> traerReservas(Date fecha_consulta) {
        List<Reserva> reservas_en_fecha = new ArrayList<Reserva> ();
        List<Reserva> reservas = controlPersis.traerReservas();
        if(reservas != null){
            
            for(Reserva unaReserva : reservas){
                
                if(unaReserva.getFecha_reserva().equals(fecha_consulta)){
                
                    reservas_en_fecha.add(unaReserva);
                }  
            }
        }
        return reservas_en_fecha;
    }

    public List<Empleado> traerEmpleados() {
        return controlPersis.traerEmpleados();
    }

    public List<Huesped> traerHuespedes() {
        return controlPersis.traerHuespedes();
    }

    public List<Reserva> traerReservasDeEmpleado(int id) {
        
        Empleado emp = controlPersis.traerEmpleado(id);
        if (emp != null){
            
            return emp.getReservas();
            
        }
        
        return null;
    }

    public List<Reserva> traerReservasDeHuesped(String dni, Date fecha_desde, Date fecha_hasta) {
        List<Reserva> reservas_huesped_en_rango = new ArrayList<Reserva> ();
        List<Reserva> reservas_huesped = new ArrayList<Reserva> ();
        List<Huesped> huespedes = controlPersis.traerHuespedes();
        if(huespedes != null){
            
            for(Huesped unHuesped : huespedes){
                
                if(unHuesped.getDni().equals(dni)){
                    
                    reservas_huesped = unHuesped.getReservas();
                    
                    for(Reserva res : reservas_huesped){
                        
                        if(this.estaEnElRango(res.getFecha_reserva(), fecha_desde, fecha_hasta)){
                            
                            reservas_huesped_en_rango.add(res);
                            
                        }
                        
                    }
                    return reservas_huesped_en_rango;
                }
            }
            
        }
        return null;

    }

    public void eliminarReserva(int id_res) {
        controlPersis.eliminarReserva(id_res);
    }

    public void eliminarReservaDeLosObjetos(int id_res) {
        List<Reserva> reservas_huesped = new ArrayList<Reserva> ();
        List<Reserva> reservas_empleado = new ArrayList<Reserva> ();
        List<Reserva> reservas_habitacion = new ArrayList<Reserva> ();
        List<Huesped> huespedes = controlPersis.traerHuespedes();
        List<Empleado> empleados = controlPersis.traerEmpleados();
        List<Habitacion> habitaciones = controlPersis.traerHabitaciones();
        if(huespedes != null){
            
            for(Huesped unHuesped : huespedes){
                
                reservas_huesped = unHuesped.getReservas();
                
                for (Reserva unaRe : reservas_huesped){
                    
                    if(unaRe.getId() == id_res){
                        unHuesped.eliminarReserva(id_res);
                        controlPersis.actualizarHuesped(unHuesped);
                        for(Empleado unEmp : empleados){
                
                            reservas_empleado = unEmp.getReservas();

                            for (Reserva unaRe2 : reservas_empleado){

                                if(unaRe2.getId() == id_res){
                                    unEmp.eliminarReserva(id_res);
                                    controlPersis.actualizarEmpleado(unEmp);
                                    for(Habitacion unaHab : habitaciones){
                
                                        reservas_habitacion = unaHab.getReservas();

                                        for (Reserva unaRe3 : reservas_habitacion){

                                            if(unaRe3.getId() == id_res){
                                                unaHab.eliminarReserva(id_res);
                                                controlPersis.actualizarHabitacion(unaHab);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void eliminarReservaDeEmpleado(int id_res) {
        List<Reserva> reservas_empleado = new ArrayList<Reserva> ();
        List<Empleado> empleados = controlPersis.traerEmpleados();
        if(empleados != null){
            
            for(Empleado unEmp : empleados){
                
                reservas_empleado = unEmp.getReservas();
                
                for (Reserva unaRe : reservas_empleado){
                    
                    if(unaRe.getId() == id_res){
                        unEmp.eliminarReserva(id_res);
                        controlPersis.actualizarEmpleado(unEmp);
                    }
                }
            }
        }
    }

    public void eliminarReservaDeHabitacion(int id_res) {
        List<Reserva> reservas_habitacion = new ArrayList<Reserva> ();
        List<Habitacion> habitaciones = controlPersis.traerHabitaciones();
        if(habitaciones != null){
            
            for(Habitacion unaHab : habitaciones){
                
                reservas_habitacion = unaHab.getReservas();
                
                for (Reserva unaRe : reservas_habitacion){
                    
                    if(unaRe.getId() == id_res){
                        unaHab.eliminarReserva(id_res);
                        controlPersis.actualizarHabitacion(unaHab);
                    }
                }
            }
        }
    }

    public void eliminarHuesped(int id_hue) {
        controlPersis.eliminarHuesped(id_hue);
    }

    public Huesped traerHuespedPorId(int id_hue) {
        return controlPersis.traerHuespedPorId(id_hue);
    }

    public List<Reserva> traerReservasDelHuesped(int id_hue) {
        List<Huesped> huespedes = controlPersis.traerHuespedes();
        if(huespedes != null){
            
            for(Huesped unHue : huespedes){
                
                if(unHue.getId() == id_hue){
                    
                    return unHue.getReservas();
                    
                }
                
            }
        }
        return null;
    }

    public void actualizarReservasDelHuesped(List<Reserva> reservas_del_huesped) {
        List<Reserva> reservas = controlPersis.traerReservas();
        
        if(reservas_del_huesped != null){
            
            for(Reserva unaResHuesped : reservas_del_huesped){
                
                //identiticar dentro de las Reservas Generales las reservas del Huesped y destroy el objeto
                for(Reserva unaResGral : reservas){
                    
                    if(unaResGral.getId() == unaResHuesped.getId()){
                        
                        //controlPersis.eliminarObjReserva(unaResGral);
                        unaResGral.getUnEmpleado().getReservas().remove(unaResGral);
                        unaResGral.getUnaHabitacion().getReservas().remove(unaResGral);
                        controlPersis.eliminarReserva(unaResGral.getId());
                        
                        //Faltaria actualizar el Empleado y la Habitacion porque no me elimina de las tablas asociadas empleado_reserva ni habitacion_reserva
                        controlPersis.actualizarEmpleado(unaResGral.getUnEmpleado());
                        controlPersis.actualizarHabitacion(unaResGral.getUnaHabitacion());
                    }
                }
            }
        }
        
        
    }

    public void actualizarReservasDelHuespedDeEmpleados(List<Reserva> reservas_del_huesped) {
        List<Empleado> empleados = controlPersis.traerEmpleados();
        List<Reserva> reservas_empleado; 
        if(reservas_del_huesped != null){
            System.out.println("Reservas del Huesped en actualizarReservasDelHuespedDeEmpleados de Controladora: " + reservas_del_huesped);
            for(Reserva unaResHuesped : reservas_del_huesped){
                
                for (Empleado unEmp : empleados){
                    
                    reservas_empleado = unEmp.getReservas();
                    
                    for (Reserva unaRe : reservas_empleado){
                        
                        if (unaRe.getId() == unaResHuesped.getId()){
                            
                            //unEmp.eliminarReserva(unaRe.getId());
                            
                            controlPersis.eliminarObjReserva(unaRe);
                            
                            System.out.println("Encontro la reserva Dentro del Empleado en Controladora del Huesped eliminado. Reserva ID general: " + unaRe.getId());
                        }
                        
                    }
                    unEmp.setReservas(reservas_empleado);
                    controlPersis.actualizarEmpleado(unEmp);
                    
                }
                
            }

        }
        
    }

    public void actualizarReservasDelHuespedDeHabitaciones(List<Reserva> reservas_del_huesped) {
        List<Habitacion> habitaciones = controlPersis.traerHabitaciones();
        List<Reserva> reservas_habitacion; 
        if(reservas_del_huesped != null){
            
            for(Reserva unaResHuesped : reservas_del_huesped){
                
                for (Habitacion unaHab : habitaciones){
                    
                    reservas_habitacion = unaHab.getReservas();
                    
                    for (Reserva unaReHab : reservas_habitacion){
                        
                        if (unaReHab.getId() == unaResHuesped.getId()){
                            
                            controlPersis.eliminarObjReserva(unaReHab);
                        }
                        
                    }
                    
                }
                
            }

        }
    }

    public void actualizarHuesped(Huesped unH) {
        controlPersis.actualizarHuesped(unH);
    }


    
}
