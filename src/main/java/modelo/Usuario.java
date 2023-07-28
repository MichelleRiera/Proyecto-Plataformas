package modelo;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Usuario implements Serializable{
	private static final long serialVersionUID= 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "usuario_id")
    private int UsuarioId;
	
	
	private String cargo, usuario, contraseña;
	
	
	//agregar relacion uno a uno
  	@OneToOne
  	@JoinColumn(name = "persona_id")
  	private Persona persona;


	public Persona getPersona() {
		return persona;
	}


	public void setPersona(Persona persona) {
		this.persona = persona;
	}


	public Usuario(int usuarioId, String cargo, String usuario, String contraseña) {
		UsuarioId = usuarioId;
		this.cargo = cargo;
		this.usuario = usuario;
		this.contraseña = contraseña;
	}


	public Usuario() {
	}


	public int getUsuarioId() {
		return UsuarioId;
	}


	public void setUsuarioId(int usuarioId) {
		UsuarioId = usuarioId;
	}


	public String getCargo() {
		return cargo;
	}


	public void setCargo(String cargo) {
        if ("A".equalsIgnoreCase(cargo) || "G".equalsIgnoreCase(cargo)) {
            this.cargo = cargo.toUpperCase(); // Convertir a mayúsculas por convención.
        } else {
            throw new IllegalArgumentException("El valor del cargo debe ser 'A' (Administrador) o 'G' (General).");
        }
    }


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getContraseña() {
		return contraseña;
	}


	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	


	@Override
	public String toString() {
		return "Usuario [UsuarioId=" + UsuarioId + ", cargo=" + cargo + ", usuario=" + usuario + ", contraseña="
				+ contraseña + ", persona=" + persona + "]";
	}
	
	
	
	
	
	
	
	

}
