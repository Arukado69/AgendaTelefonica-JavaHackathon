package Util;

/**
 * Clase utilitaria para dar estilo a la consola:
 * colores, encabezados, limpieza de pantalla y pausas.
 * Evita repetir código visual dentro de Main.
 */
public class ConsoleStyle {

    // 🎨 Códigos ANSI para colores en consola
    public static final String RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED = "\u001B[31m";

    /**
     * Limpia la consola (funciona en la mayoría de terminales).
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Imprime un encabezado centrado dentro de un recuadro azul.
     */
    public static void printHeader(String title) {
        System.out.println(BLUE + "╔══════════════════════════════════════════════╗" + RESET);
        System.out.printf(BLUE + "║ %-44s ║%n" + RESET, title);
        System.out.println(BLUE + "╚══════════════════════════════════════════════╝" + RESET);
    }

    /**
     * Pausa el flujo para que el usuario pueda leer los mensajes antes de continuar.
     */
    public static void pause() {
        System.out.println(YELLOW + "\nPresiona Enter para continuar..." + RESET);
        try {
            System.in.read();
        } catch (Exception e) {
            // Se ignora el error de lectura.
        }
    }

    /**
     * Imprime un mensaje de éxito (verde).
     */
    public static void success(String msg) {
        System.out.println(GREEN + "✅ " + msg + RESET);
    }

    /**
     * Imprime un mensaje de advertencia (amarillo).
     */
    public static void warning(String msg) {
        System.out.println(YELLOW + "⚠️  " + msg + RESET);
    }

    /**
     * Imprime un mensaje de error (rojo).
     */
    public static void error(String msg) {
        System.out.println(RED + "❌ " + msg + RESET);
    }
}

