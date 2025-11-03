package Service;

import Exeptions.ContactNotFoundException;
import Model.Contacto;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

//La forma de modificar es buscamos por nombre apellido id o telefono nos dara una lista de las
// similitudes y despues deberemos seleccionar el ID para que de forma segura modifquemos esta onda*/
/* Buscar -> Mostrar -> Seleccionar ID -> Ejecutar */
public class AgendaService {
    private final List<Contacto> contactos;
    //Esto establece la capacidad maxima de nuestra agenda
    private final int maxCapacity;

    public AgendaService(int maxCapacity) {
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a 0");
        }
        this.maxCapacity = maxCapacity;
        this.contactos = new ArrayList<>(maxCapacity);
    }

    //Añadir contacto
    public void addContact(Contacto contacto) {
        if (contacto == null) {
            throw new IllegalArgumentException("No se puede añadir un contacto sin datos.");
        }
        if (contactos.size() >= this.maxCapacity) {
            throw new IllegalStateException(
                    "Agenda llena. No se puede añadir el contacto. Límite: " + this.maxCapacity
            );
        }
        contactos.add(contacto);
        System.out.println("¡Contacto añadido! Quedan " + getCapacityRest() + " espacios para añadir");
    }


     //Modifica un contacto existente, buscándolo por su ID.
    public void modifyContact(int id, Contacto datosNuevos) {
        Contacto contactoExistente = findById(id);
        updateData(contactoExistente, datosNuevos);
    }

    //Borra por su ID
    public void deleteByID(int id) {
        Contacto contacto = findById(id);
        contactos.remove(contacto);
        System.out.println("Se elimino el contacto con ID: " + id);
    }

    //Ver todos los contactos de la lista
    public List<Contacto> getContactos() {
        return new ArrayList<>(contactos);
    }

    // Busqueda Múltiple para encontrar todas las similitudes

    public List<Contacto> findAllByName(String name) {
        return findAll(contacto -> contacto.getNombre().equals(name));
    }

    public List<Contacto> findAllByPhone(String telefono) {
        return findAll(contacto -> contacto.getTelefono().equals(telefono));
    }

    public List<Contacto> findAllByApellido(String apellido) {
        return findAll(contacto -> contacto.getApellido().equals(apellido));
    }

    // Busqueda unica de id

    public Contacto findById(int id) {
        return find(
                contacto -> contacto.getId() == id,
                () -> new ContactNotFoundException("No existe un contacto con el ID = " + id)
        );
    }

    //busqueda unica de nombre
    public Contacto findByName(String nombre) {
        return find(
                contacto -> contacto.getNombre().equals(nombre),
                () -> new ContactNotFoundException("No existe un contacto con el nombre = " + nombre)
        );
    }

    //busqueda unica de telefono
    public Contacto findByPhone(String telefono) {
        return find(
                contacto -> contacto.getTelefono().equals(telefono),
                () -> new ContactNotFoundException("No existe un contacto con el telefono = " + telefono)
        );
    }

    //obtener la capacidad maxima de la agenda
    public int getMaxCapacity() {
        return this.maxCapacity;
    }

    //obtener el numero de espacios disponibles en la agenda
    public int getCapacityRest() {
        return this.maxCapacity - this.contactos.size();
    }

    //Actualizar datos
    private void updateData(Contacto contactoExistente, Contacto newData) {
        if (newData == null) {
            throw new IllegalArgumentException("Los 'datos Nuevos' no pueden ser nulos.");
        }
        contactoExistente.setNombre(newData.getNombre());
        contactoExistente.setApellido(newData.getApellido());
        contactoExistente.setTelefono(newData.getTelefono());
        System.out.println("¡Contacto con ID " + contactoExistente.getId() + " modificado exitosamente!");
    }

    // metodo para encontrar la primer coincidencia al buscar
    private Contacto find(Predicate<Contacto> criterio, Supplier<ContactNotFoundException> exceptionSupplier) {
        return contactos.stream()
                .filter(criterio)
                .findFirst()
                .orElseThrow(exceptionSupplier);
    }

    //Metodo para encontrar todos los contactos dependiendo el criterio seleccionado
    private List<Contacto> findAll(Predicate<Contacto> criterio) {
        return contactos.stream()
                .filter(criterio)
                .collect(Collectors.toList());
    }
}