package modelo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

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
	@Column(name = "ticket_id")
	private int ticketid;
    //private double costoTarifa;
    
    private Date fecha;
    private  Time hora_entrada,hora_salida;
    //relacion empleado da muchos tickets
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    //relacion tarifa 
    @OneToOne
    @JoinColumn(name = "tarifa_id")
    private Tarifa tarifa;
    
    
    @OneToOne
    @JoinColumn(name = "lugar_id")
    private Lugar lugar;
    
    @OneToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;
    
    /*@OneToOne
    @JoinColumn(name = "factura_id")
    private Factura factura;*/
    
    
    
	public Ticket(int ticketid, Date fecha, Time hora_entrada, Time hora_salida) {

		this.ticketid = ticketid;
		this.fecha = fecha;
		this.hora_entrada = hora_entrada;
		this.hora_salida = hora_salida;
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
	public Time getHora_entrada() {
		return hora_entrada;
	}
	public void setHora_entrada(Time hora_entrada) {
		this.hora_entrada = hora_entrada;
	}
	public Time getHora_salida() {
		return hora_salida;
	}
	public void setHora_salida(Time hora_salida) {
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
	
	@Override
	public String toString() {
		return "Ticket [ticketid=" + ticketid + ", fecha=" + fecha + ", hora_entrada=" + hora_entrada + ", hora_salida="
				+ hora_salida + ", usuario=" + usuario + ", tarifa=" + tarifa + ", lugar=" + lugar + ", vehiculo="
				+ vehiculo + "]";
	}
	
	
	
	
    
    
    

}
