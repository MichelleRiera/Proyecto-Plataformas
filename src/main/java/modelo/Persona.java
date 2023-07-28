package modelo;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personas_id")
    private int personaid;

    private String cedula, nombre, apellido, telefono, direccion, tipo;

    public Persona(int personaid, String cedula, String nombre, String apellido, String telefono, String direccion,
            String tipo) {
        this.personaid = personaid;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        setTipo(tipo); // Usa el método setter para asegurar la validación del campo "tipo"
    }

    public Persona() {

    }

    public int getPersonaid() {
        return personaid;
    }

    public void setPersonaid(int personaid) {
        this.personaid = personaid;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        // Realiza una validación para permitir solo los valores "E" o "C"
        if (tipo.equalsIgnoreCase("E") || tipo.equalsIgnoreCase("C")) {
            this.tipo = tipo;
        } else {
            throw new IllegalArgumentException("El tipo debe ser 'E' o 'C'");
        }
    }

    @Override
    public String toString() {
        return "Persona [personaid=" + personaid + ", cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido
                + ", telefono=" + telefono + ", direccion=" + direccion + ", tipo=" + tipo + "]";
    }
}
