package modelo;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Ticket implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ticketid")
	private int ticketid;
    //private double costoTarifa;
    
    private Date fecha;
    private LocalTime hora_entrada;
    private LocalTime hora_salida;
    private String estado;
    //relacion empleado da muchos tickets
    @OneToOne
    @JoinColumn(name = "usuarioid")
    private Usuario usuario;
    //relacion tarifa 
    @OneToOne
    @JoinColumn(name = "tarifaid")
    private Tarifa tarifa;
    
    
    @OneToOne
    @JoinColumn(name = "lugarid")
    private Lugar lugar;
    
    @OneToOne
    @JoinColumn(name = "vehiculoid")
    private Vehiculo vehiculo;
    
    /*@OneToOne
    @JoinColumn(name = "factura_id")
    private Factura factura;*/
    
    
    
	public Ticket(int ticketid, Date fecha, LocalTime hora_entrada, LocalTime hora_salida,String estado) {

		this.ticketid = ticketid;
		this.fecha = fecha;
		this.hora_entrada = hora_entrada;
		this.hora_salida = hora_salida;
		setEstado(estado); // Usa el método setter para asegurar la validación del campo "tipo"
	}
	public Ticket() {
	}
	public int getTicketid() {
		return ticketid;
	}
	public void setTicketid(int ticketid) {
		this.ticketid = ticketid;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public LocalTime getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(LocalTime hora_entrada) {
        this.hora_entrada = hora_entrada;
    }
    public LocalTime getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(LocalTime hora_salida) {
        this.hora_salida = hora_salida;
    }
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Tarifa getTarifa() {
		return tarifa;
	}
	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}
	
	public Lugar getLugar() {
		return lugar;
	}
	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	
	public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        // Realiza una validación para permitir solo los valores "P" o "C" pendiente o cancelado
        if (estado.equalsIgnoreCase("P") || estado.equalsIgnoreCase("C")) {
            this.estado = estado;
        } else {
            throw new IllegalArgumentException("El estado debe ser 'P' o 'C'");
        }
    }
	
	@Override
	public String toString() {
		return "Ticket [ticketid=" + ticketid + ", fecha=" + fecha + ", hora_entrada=" + hora_entrada + ", hora_salida="
				+ hora_salida + ", usuario=" + usuario + ", tarifa=" + tarifa + ", lugar=" + lugar + ", vehiculo="
				+ vehiculo + "]";
	}
	
	
	
	
    
    
    

}
