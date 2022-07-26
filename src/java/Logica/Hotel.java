
package Logica;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Hotel {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    @Basic
    private String nombre;
    @OneToMany
    private List<Habitacion> habitaciones = new ArrayList<Habitacion> ();
    @OneToMany
    private List<Reserva> reservas = new ArrayList<Reserva> ();
    @OneToMany
    private List<Huesped> huespedes = new ArrayList<Huesped> ();

    public Hotel() {
    }

    public Hotel(int id, String nombre, List<Habitacion> habitaciones, List<Reserva> reservas, List<Huesped> huespedes) {
        this.id = id;
        this.nombre = nombre;
        this.habitaciones = habitaciones;
        this.reservas = reservas;
        this.huespedes = huespedes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
    
    public void agregarHabitacion(Habitacion unaHab){
        this.habitaciones.add(unaHab);
    }

    public List<Huesped> getHuespedes() {
        return huespedes;
    }

    public void setHuespedes(List<Huesped> huespedes) {
        this.huespedes = huespedes;
    }
    
    

}
