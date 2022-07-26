package Persistencia;

import Logica.Empleado;
import Logica.Habitacion;
import Logica.Huesped;
import Logica.Reserva;
import Logica.Usuario;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControladoraPersistencia {
    
    HabitacionJpaController habJPA = new HabitacionJpaController();
    UsuarioJpaController usuJPA = new UsuarioJpaController();
    EmpleadoJpaController empJPA = new EmpleadoJpaController();
    PersonaJpaController perJPA = new PersonaJpaController();  
    HuespedJpaController hueJPA = new HuespedJpaController();
    ReservaJpaController resJPA = new ReservaJpaController();
    
    
    //Haciendo uso de los controladores de Persistencia para manejo de la DB
    public void crearHabitacion (Habitacion unaHab){
        habJPA.create(unaHab);
    }
    
    public List<Usuario> traerUsuarios (){
        return usuJPA.findUsuarioEntities();
    }
    
    public void crearUsuario (Usuario unUsuario){
        usuJPA.create(unUsuario);
    }
    
    public void crearEmpleado (Empleado unEmp){
        empJPA.create(unEmp);
    }
    
    public List<Empleado> traerEmpleados (){
        return empJPA.findEmpleadoEntities();
    }
    
    public void crearHuesped (Huesped unHuesped){
        hueJPA.create(unHuesped);
    }
    
    public void crearReserva (Reserva unaReserva){
        resJPA.create(unaReserva);
    }

    public List<Habitacion> traerHabitaciones() {
        return habJPA.findHabitacionEntities();
    }

    public void actualizarHuesped(Huesped unHuesped) {
        try {
            hueJPA.edit(unHuesped);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizarEmpleado(Empleado unEmp) {
        try {
            empJPA.edit(unEmp);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizarHabitacion(Habitacion unaHab) {
        try {
            habJPA.edit(unaHab);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Huesped> traerHuespedes() {
        return hueJPA.findHuespedEntities();
    }

    public List<Reserva> traerReservas() {
        return resJPA.findReservaEntities();
    }

    public Empleado traerEmpleado(int id) {
        return empJPA.findEmpleado(id);
    }

    public void eliminarReserva(int id_res) {
        try {
            resJPA.destroy(id_res);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarHuesped(int id_hue) {
        try {
            hueJPA.destroy(id_hue);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Huesped traerHuespedPorId(int id_hue) {
        return hueJPA.findHuesped(id_hue);
    }

    public void eliminarObjReserva(Reserva unaResGral) {
        try {
            resJPA.destroy(unaResGral.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
