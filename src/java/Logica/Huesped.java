
package Logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
//import java.util.function.Predicate;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.eclipse.persistence.annotations.CascadeOnDelete;

@Entity
public class Huesped extends Persona implements Serializable{
    @Basic
    private String profesion;
    @OneToMany (cascade = CascadeType.PERSIST)
    private List<Reserva> reservas = new ArrayList<Reserva> ();
    
    public Huesped() {
    }

    public Huesped(String profesion) {
        this.profesion = profesion;
    }

    public Huesped(String profesion, int id, String dni, String nombre, String apellido, Date fecha_nac, String direccion) {
        super(id, dni, nombre, apellido, fecha_nac, direccion);
        this.profesion = profesion;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }
    
    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
    
    public void agregarReserva(Reserva unaReserva){
        this.reservas.add(unaReserva);
    }

    public void eliminarReserva(int id_res) {
 
        //List<Reserva> reservas = this.getReservas();
        //Predicate<Reserva> condition = reserva -> reserva.getId() == id_res;
        //reservas.removeIf(condition);
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
