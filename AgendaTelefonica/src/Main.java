import Model.Contacto;
import Service.Agenda;
import Util.ConsoleStyle;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Agenda agenda = new Agenda();
        int opcion = -1;

        do {
            try {
                ConsoleStyle.clearScreen();
                ConsoleStyle.printHeader("AGENDA TELEFÓNICA");
                mostrarMenu();
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1 -> agregarContacto(sc, agenda);
                    case 2 -> agenda.listarContactos();
                    case 3 -> buscarContacto(sc, agenda);
                    case 4 -> eliminarContacto(sc, agenda);
                    case 5 -> ConsoleStyle.success("Saliendo");
                    default -> ConsoleStyle.warning("Opción inválida");
                }
                ConsoleStyle.pause();

            } catch (NumberFormatException e) {
                ConsoleStyle.error("Entrada no válida. Usa solo números.");
                ConsoleStyle.pause();
            } catch (Exception e) {
                ConsoleStyle.error("Error inesperado: " + e.getMessage());
                ConsoleStyle.pause();
            }
        } while (opcion != 5);
        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("""
            1. Añadir Contacto
            2. Listar Contactos
            3. Buscar Contacto
            4. Eliminar Contacto
            5. Salir
            """);
        System.out.print("Selecciona una opción: ");
    }

    private static void agregarContacto(Scanner sc, Agenda agenda) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Teléfono: ");
        String telefono = sc.nextLine().trim();
        try {
            agenda.añadirContacto(new Contacto(nombre, telefono));
        } catch (Exception e) {
            ConsoleStyle.error(e.getMessage());
        }
    }

    private static void buscarContacto(Scanner sc, Agenda agenda) {
        System.out.print("Nombre a buscar: ");
        agenda.buscarContacto(sc.nextLine());
    }

    private static void eliminarContacto(Scanner sc, Agenda agenda) {
        System.out.print("Nombre a eliminar: ");
        agenda.eliminarContacto(new Contacto(sc.nextLine(), ""));
    }
}
