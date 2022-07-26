
package Logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//import java.util.function.Predicate;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.eclipse.persistence.annotations.CascadeOnDelete;

@Entity
public class Habitacion implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    @Basic
    private int piso;
    private String nombre;
    private String tipo;
    private double precio;
    private String disponibilidad;
    private int capacidad_max;
    @OneToMany (cascade = CascadeType.PERSIST)
    private List<Reserva> reservas = new ArrayList<Reserva> ();

    public Habitacion() {
    }

    public Habitacion(int id, int piso, String nombre, String tipo, double precio, String disponibilidad, int capacidad_max) {
        this.id = id;
        this.piso = piso;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
        this.capacidad_max = capacidad_max;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public int getCapacidad_max() {
        return capacidad_max;
    }

    public void setCapacidad_max(int capacidad_max) {
        this.capacidad_max = capacidad_max;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
    
    public void agregarReserva(Reserva unaRe){
        this.reservas.add(unaRe);
    }
    
    public void eliminarReserva(int id_res) {
        
        List<Reserva> reservas = this.getReservas();
        
        Iterator<Reserva> recorrer = reservas.iterator();
        Reserva res;
        while (recorrer.hasNext())
        {
          res = recorrer.next();
          if (res.getId() == id_res) {
            recorrer.remove();
          }
        }
    }

}
