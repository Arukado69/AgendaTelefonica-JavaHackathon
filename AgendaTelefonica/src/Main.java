import Model.Contacto;
import Service.AgendaService;
import Util.ConsoleStyle;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AgendaService agenda = new AgendaService(100);
        int opcion = -1;

        do {
            try {
                ConsoleStyle.clearScreen();
                ConsoleStyle.printHeader("AGENDA TELEFÓNICA");
                mostrarMenu();
                opcion = Integer.parseInt(sc.nextLine().trim());

                switch (opcion) {
                    case 1 -> agregarContacto(sc, agenda);
                    case 2 -> listarContactos(agenda);
                    case 3 -> buscarContacto(sc, agenda);
                    case 4 -> eliminarContacto(sc, agenda);
                    case 5 -> modificarContacto(sc, agenda);
                    case 6 -> ConsoleStyle.success("Saliendo...");
                    default -> ConsoleStyle.warning("Opción inválida. Debe ser un número entre 1 y 6.");
                }

                if (opcion != 6) ConsoleStyle.pause();

            } catch (NumberFormatException e) {
                ConsoleStyle.error("Entrada no válida. Usa solo números.");
                ConsoleStyle.pause();
            } catch (Exception e) {
                ConsoleStyle.error("Error inesperado: " + e.getMessage());
                ConsoleStyle.pause();
            }
        } while (opcion != 6);
        sc.close();
    }

    // ---- MENÚ PRINCIPAL ----
    private static void mostrarMenu() {
        System.out.println("""
            1. Añadir Contacto
            2. Listar Contactos
            3. Buscar Contacto
            4. Eliminar Contacto
            5. Modificar Contacto
            6. Salir
            """);
        System.out.print("Selecciona una opción: ");
    }

    // ---- AÑADIR CONTACTO ----
    private static void agregarContacto(Scanner sc, AgendaService agenda) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Apellido: ");
        String apellido = sc.nextLine().trim();

        String telefono;
        while (true) {
            System.out.print("Teléfono: ");
            telefono = sc.nextLine().trim();
            if (telefono.matches("\\d+")) break;
            ConsoleStyle.warning("El teléfono solo debe contener números. Intenta nuevamente.");
        }

        try {
            agenda.addContact(new Contacto(nombre, apellido, telefono));
        } catch (Exception e) {
            ConsoleStyle.error(e.getMessage());
        }
    }

    // ---- LISTAR CONTACTOS ----
    private static void listarContactos(AgendaService agenda) {
        List<Contacto> contactos = agenda.getContactos();
        if (contactos.isEmpty()) {
            ConsoleStyle.warning("No hay contactos registrados.");
        } else {
            ConsoleStyle.success("📋 Lista de contactos:");
            contactos.forEach(System.out::println);
        }
    }

    // ---- BUSCAR CONTACTO ----
    private static void buscarContacto(Scanner sc, AgendaService agenda) {
        boolean continuar = true;

        while (continuar) {
            try {
                System.out.println("""
                    ¿Cómo deseas buscar el contacto?
                    1. Por ID
                    2. Por nombre
                    3. Por apellido
                    4. Por teléfono
                    5. Regresar al menú principal
                    """);
                System.out.print("Selecciona una opción: ");
                int tipoBusqueda = Integer.parseInt(sc.nextLine().trim());

                if (tipoBusqueda < 1 || tipoBusqueda > 5) {
                    ConsoleStyle.warning("Opción inválida. Elige un número entre 1 y 5.");
                    continue;
                }

                if (tipoBusqueda == 5) return;

                if (tipoBusqueda == 1) {
                    System.out.print("Ingresa el ID a buscar: ");
                    int id = Integer.parseInt(sc.nextLine());
                    try {
                        Contacto contacto = agenda.findById(id);
                        ConsoleStyle.success("Contacto encontrado:");
                        System.out.println(contacto);
                    } catch (Exception e) {
                        ConsoleStyle.warning("No existe un contacto con ese ID.");
                    }
                    return;
                }

                System.out.print("Ingresa el valor a buscar: ");
                String valor = sc.nextLine().trim();

                List<Contacto> resultados = switch (tipoBusqueda) {
                    case 2 -> agenda.findAllByName(valor);
                    case 3 -> agenda.findAllByApellido(valor);
                    case 4 -> agenda.findAllByPhone(valor);
                    default -> List.of();
                };

                if (resultados.isEmpty()) {
                    ConsoleStyle.warning("No se encontraron contactos.");
                    System.out.print("¿Deseas intentar de nuevo? (s/n): ");
                    if (!sc.nextLine().trim().equalsIgnoreCase("s")) return;
                } else {
                    ConsoleStyle.success("Resultados encontrados:");
                    resultados.forEach(System.out::println);
                    continuar = false;
                }

            } catch (NumberFormatException e) {
                ConsoleStyle.error("Por favor ingresa un número válido.");
            } catch (Exception e) {
                ConsoleStyle.error("Error al buscar: " + e.getMessage());
            }
        }
    }

    // ---- ELIMINAR CONTACTO ----
    private static void eliminarContacto(Scanner sc, AgendaService agenda) {
        System.out.println("""
            ¿Qué deseas hacer?
            1. Eliminar por ID
            2. Eliminar por nombre
            3. Eliminar por apellido
            4. Eliminar por teléfono
            5. Borrar todos los contactos
            6. Regresar al menú principal
            """);
        System.out.print("Selecciona una opción: ");
        int tipo = Integer.parseInt(sc.nextLine().trim());

        if (tipo < 1 || tipo > 6) {
            ConsoleStyle.warning("Opción inválida. Elige entre 1 y 6.");
            return;
        }

        if (tipo == 6) return;

        // Borrar todos los contactos
        if (tipo == 5) {
            System.out.print("⚠️ ¿Seguro que deseas eliminar TODOS los contactos? (si/no): ");
            String confirm = sc.nextLine().trim().toLowerCase();
            if (confirm.equals("si")) {
                agenda.getContactos().forEach(c -> agenda.deleteByID(c.getId()));
                ConsoleStyle.success("Todos los contactos fueron eliminados.");
            } else {
                ConsoleStyle.warning("Operación cancelada. Regresando a eliminar contacto...");
                eliminarContacto(sc, agenda);
            }
            return;
        }

        // Eliminar por ID directo
        if (tipo == 1) {
            System.out.print("Ingresa el ID del contacto a eliminar: ");
            int id = Integer.parseInt(sc.nextLine());
            try {
                agenda.deleteByID(id);
                ConsoleStyle.success("Contacto eliminado correctamente.");
            } catch (Exception e) {
                ConsoleStyle.error("No existe un contacto con ese ID.");
            }
            return;
        }

        // Eliminación múltiple (nombre, apellido o teléfono)
        System.out.print("Ingresa el valor: ");
        String valor = sc.nextLine().trim();

        List<Contacto> encontrados = switch (tipo) {
            case 2 -> agenda.findAllByName(valor);
            case 3 -> agenda.findAllByApellido(valor);
            case 4 -> agenda.findAllByPhone(valor);
            default -> List.of();
        };

        if (encontrados.isEmpty()) {
            ConsoleStyle.warning("No se encontraron contactos con ese valor.");
            return;
        }

        ConsoleStyle.success("Contactos encontrados:");
        encontrados.forEach(System.out::println);

        System.out.print("¿Deseas eliminar estos contactos? (si/no): ");
        String confirm = sc.nextLine().trim().toLowerCase();

        if (confirm.equals("si")) {
            encontrados.forEach(c -> agenda.deleteByID(c.getId()));
            ConsoleStyle.success("Contactos eliminados correctamente.");
        } else {
            ConsoleStyle.warning("Eliminación cancelada. Volviendo a la opción 4...");
            eliminarContacto(sc, agenda);
        }
    }

    // ---- MODIFICAR CONTACTO ----
    private static void modificarContacto(Scanner sc, AgendaService agenda) {
        boolean repetir = true;

        while (repetir) {
            System.out.println("""
                ¿Cómo deseas buscar el contacto a modificar?
                1. Por ID
                2. Por nombre
                3. Por apellido
                4. Por teléfono
                5. Regresar al menú principal
                """);
            System.out.print("Selecciona una opción: ");
            int tipo = Integer.parseInt(sc.nextLine().trim());

            if (tipo < 1 || tipo > 5) {
                ConsoleStyle.warning("Opción inválida. Elige entre 1 y 5.");
                continue;
            }

            if (tipo == 5) return;

            // Modificar por ID directo
            if (tipo == 1) {
                System.out.print("Ingresa el ID del contacto: ");
                int id = Integer.parseInt(sc.nextLine());
                try {
                    modificarDatosContacto(sc, agenda, id);
                } catch (Exception e) {
                    ConsoleStyle.error("No existe un contacto con ese ID.");
                }
                return;
            }

            System.out.print("Ingresa el valor a buscar: ");
            String valor = sc.nextLine().trim();

            List<Contacto> encontrados = switch (tipo) {
                case 2 -> agenda.findAllByName(valor);
                case 3 -> agenda.findAllByApellido(valor);
                case 4 -> agenda.findAllByPhone(valor);
                default -> List.of();
            };

            if (encontrados.isEmpty()) {
                ConsoleStyle.warning("No se encontraron contactos. ¿Intentar de nuevo? (s/n): ");
                if (!sc.nextLine().trim().equalsIgnoreCase("s")) return;
                continue;
            }

            ConsoleStyle.success("Contactos encontrados:");
            encontrados.forEach(System.out::println);

            System.out.print("Ingresa el ID del contacto que deseas modificar: ");
            int id = Integer.parseInt(sc.nextLine());

            try {
                modificarDatosContacto(sc, agenda, id);
                repetir = false;
            } catch (Exception e) {
                ConsoleStyle.error("Error al modificar: " + e.getMessage());
                System.out.print("¿Intentar de nuevo? (s/n): ");
                if (!sc.nextLine().trim().equalsIgnoreCase("s")) return;
            }
        }
    }

    // ---- MÉTODO AUXILIAR PARA MODIFICAR CONTACTOS ----
    private static void modificarDatosContacto(Scanner sc, AgendaService agenda, int id) {
        Contacto original = agenda.findById(id);

        System.out.print("Nuevo nombre (Enter para mantener): ");
        String nuevoNombre = sc.nextLine().trim();
        if (nuevoNombre.isEmpty()) nuevoNombre = original.getNombre();

        System.out.print("Nuevo apellido (Enter para mantener): ");
        String nuevoApellido = sc.nextLine().trim();
        if (nuevoApellido.isEmpty()) nuevoApellido = original.getApellido();

        String nuevoTelefono;
        while (true) {
            System.out.print("Nuevo teléfono (Enter para mantener): ");
            nuevoTelefono = sc.nextLine().trim();
            if (nuevoTelefono.isEmpty()) {
                nuevoTelefono = original.getTelefono();
                break;
            }
            if (nuevoTelefono.matches("\\d+")) break;
            ConsoleStyle.warning("El teléfono solo debe contener números.");
        }

        Contacto nuevosDatos = new Contacto(nuevoNombre, nuevoApellido, nuevoTelefono);
        agenda.modifyContact(id, nuevosDatos);
    }
}
