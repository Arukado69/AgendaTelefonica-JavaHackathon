package Model;

import java.util.Objects;

public class Contacto {

    private int id = 0;
    private String nombre;
    private String apellido;
    private String telefono;

    public Contacto (int id, String nombre, String apellido, String telefono){
        this.id = id++;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;

    }

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

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Contacto contacto = (Contacto) obj;
        return nombre.equalsIgnoreCase(contacto.nombre) && apellido.equalsIgnoreCase(contacto.apellido);
    }

    @Override
    public int hashCode(){
        return Objects.hash(nombre.toLowerCase(), apellido.toLowerCase());
        }

    @Override public String toString() {
        return "Contacto [Nombre: " + nombre + ", Apellido: " + apellido + ", Teléfono: " + telefono + "]";
    }
}
