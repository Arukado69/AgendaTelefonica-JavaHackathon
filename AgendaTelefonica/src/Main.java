import Model.Contacto;
import Service.Agenda;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Agenda agenda = new Agenda();
        int opcion;

        do{
            System.out.print("Bienvenido a Agenda Telefónica");
            System.out.print("1. Añadir Contactos");
            System.out.print("2. Listar Contactos");
            System.out.print("3. Buscar Contacto");
            System.out.print("4. Eliminar Contacto");
            System.out.print("5. Salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1:
                    System.out.print("Introduce el nombre del Contacto: ");
                    String nombre = sc.nextLine();
                    System.out.print("Introduce el teléfono: ");
                    String telefono = sc.nextLine();
                    agenda.añadirContacto(new Contacto(nombre, telefono));
                    break;
                case 2:
                    agenda.listarContactos();
                    break;
                case 3:
                    System.out.print("Introduce el nombre a buscar: ");
                    agenda.buscarContacto(sc.nextLine());
                    break;
                case 4:
                    System.out.print("Introduce el nombre a eliminar: ");
                    agenda.eliminarContato(new Contacto(sc.nextLine(), ""));
                    break;
                case 5:
                    System.out.print("Saliendo, gracias por utilizar el programa:)");
                    break;
                default:
                    System.out.print("Opción inválida");
            }
        } while (opcion != 999);
    }
}