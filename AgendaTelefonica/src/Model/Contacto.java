package Model;

import java.util.Objects;

public class Contacto {

    private int nextId = 1;
    public int id;
    private String nombre;
    private String apellido;
    private String telefono;

    // Constructor
    public Contacto (String nombre, String apellido, String telefono){
        this.id = nextId++;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    // --- Métodos Getters y Setters ---
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

    // --- Define si dos objetos Contacto son iguales.
    // --- La igualdad se basa en que el nombre y el apellido sean iguales (sin importar mayúsculas/minúsculas).
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Contacto contacto = (Contacto) obj;
        return nombre.equalsIgnoreCase(contacto.nombre) && apellido.equalsIgnoreCase(contacto.apellido);
    }

    // Usa los campos clave (nombre y apellido) convertidos a minúsculas para garantizar que el hash sea el mismo si equals() devuelve true.
    @Override
    public int hashCode(){
        return Objects.hash(nombre.toLowerCase(), apellido.toLowerCase());
        }

   // --- toString
    @Override public String toString() {
        return "Contacto [Nombre: " + nombre + ", Apellido: " + apellido + ", Teléfono: " + telefono + "]";
    }
}
