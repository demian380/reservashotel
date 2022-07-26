
package Logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
//import java.util.function.Predicate;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.eclipse.persistence.annotations.CascadeOnDelete;

@Entity
public class Empleado extends Persona{
    @OneToOne
    private Usuario unUsuario;
    @Basic
    private String cargo;
    @OneToMany (cascade = CascadeType.PERSIST)
    private List<Reserva> reservas  = new ArrayList<Reserva> ();
    
    public Empleado() {
    }

    public Empleado(Usuario unUsuario, String cargo, int id, String dni, String nombre, String apellido, Date fecha_nac, String direccion) {
        super(id, dni, nombre, apellido, fecha_nac, direccion);
        this.unUsuario = unUsuario;
        this.cargo = cargo;
    }

    public Usuario getUnUsuario() {
        return unUsuario;
    }

    public void setUnUsuario(Usuario unUsuario) {
        this.unUsuario = unUsuario;
    }
    
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
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
