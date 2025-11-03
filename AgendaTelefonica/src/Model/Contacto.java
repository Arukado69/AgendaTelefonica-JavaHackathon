package Model;

public class Contacto {

    private int nextId = 1;
    public int id;
    private String nombre;
    private String apellido;
    private String telefono;

    public Contacto (String nombre, String apellido, String telefono){
        this.id = nextId++;
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
    public String toString() {
        return "Contacto->" +
                "id: " + id +
                ", nombre: '" + nombre + '\'' +
                ", apellido: '" + apellido +
                ", telefono: '" + telefono;
    }
}
