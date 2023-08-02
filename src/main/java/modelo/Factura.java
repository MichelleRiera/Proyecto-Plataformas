package modelo;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Factura implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "facturaid")
	private int facturaId;
	
	private Date fecha;
	private double iva,subtotal, total;
	
	@ManyToOne
    @JoinColumn(name = "personaid")
    private Persona persona;
	
	
	@OneToOne
    @JoinColumn(name = "ticketid")
    private Ticket ticket;
	
	

	public Factura(int facturaId, Date fecha, double subtotal, double total, double iva) {
		this.facturaId = facturaId;
		this.fecha = fecha;
		this.subtotal = subtotal;
		this.total = total;
		this.iva= iva;
	}
	
	
	

	public Factura() {
	}




	public int getFacturaId() {
		return facturaId;
	}

	public void setFacturaId(int facturaId) {
		this.facturaId = facturaId;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}




	public Ticket getTicket() {
		return ticket;
	}




	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
	public double getIva() {
		return iva;
	}




	public void setIva(double iva) {
		this.iva = iva;
	}




	@Override
	public String toString() {
		return "Factura [facturaId=" + facturaId + ", fecha=" + fecha + ", subtotal=" + subtotal + ", total=" + total
				+ ", persona=" + persona + ", ticket=" + ticket + "]";
	}




	
	
	
	
	
	

}
