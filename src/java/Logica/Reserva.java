
package Logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.eclipse.persistence.annotations.CascadeOnDelete;

@Entity
public class Reserva implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    @Temporal(TemporalType.DATE)
    private Date fecha_reserva;
    @Temporal(TemporalType.DATE)
    private Date fecha_in;
    @Temporal(TemporalType.DATE)
    private Date fecha_out;
    @Basic
    private double precio_total;
    private int cant_personas;
    @ManyToOne
    private Empleado unEmpleado;
    @ManyToOne
    private Habitacion unaHabitacion;

    public Reserva(int id, Date fecha_reserva, Date fecha_in, Date fecha_out, double precio_total, int cant_personas, Empleado unEmpleado, Habitacion unaHabitacion) {
        this.id = id;
        this.fecha_reserva = fecha_reserva;
        this.fecha_in = fecha_in;
        this.fecha_out = fecha_out;
        this.precio_total = precio_total;
        this.cant_personas = cant_personas;
        this.unEmpleado = unEmpleado;
        this.unaHabitacion = unaHabitacion;
    }

    public Habitacion getUnaHabitacion() {
        return unaHabitacion;
    }

    public void setUnaHabitacion(Habitacion unaHabitacion) {
        this.unaHabitacion = unaHabitacion;
    }

    public Reserva() {
    }

    public Empleado getUnEmpleado() {
        return unEmpleado;
    }

    public void setUnEmpleado(Empleado unEmpleado) {
        this.unEmpleado = unEmpleado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(Date fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    public Date getFecha_in() {
        return fecha_in;
    }

    public void setFecha_in(Date fecha_in) {
        this.fecha_in = fecha_in;
    }

    public Date getFecha_out() {
        return fecha_out;
    }

    public void setFecha_out(Date fecha_out) {
        this.fecha_out = fecha_out;
    }

    public double getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(double precio_total) {
        this.precio_total = precio_total;
    }

    public int getCant_personas() {
        return cant_personas;
    }

    public void setCant_personas(int cant_personas) {
        this.cant_personas = cant_personas;
    }

}
