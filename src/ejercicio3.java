import java.util.Scanner;
import java.util.Stack;
//use stack para la cola xd

public class ejercicio3 {
//Diseña un programa en Java que permita a un usuario realizar acciones y luego tener la capacidad de
//deshacer las últimas acciones realizadas. Además, el usuario podrá rehacer acciones previamente
//deshechas si así lo desea. El programa simulará un sistema básico de edición de texto en el que el usuario
//puede agregar palabras a un documento y utilizar las opciones de deshacer y rehacer. 1.5pt

public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String documento = "";
    Stack<String> pilaDeshacer = new Stack<>();
    Stack<String> pilaRehacer = new Stack<>();
    boolean salir = false;

    while (!salir) {
        System.out.println("\nDocumento actual: \"" + documento + "\"");
        System.out.println("Opciones:");
        System.out.println("1. Agregar texto");
        System.out.println("2. Deshacer");
        System.out.println("3. Rehacer");
        System.out.println("4. Salir");
        System.out.print("Elige una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Introduce el texto a agregar: ");
                String nuevoTexto = scanner.nextLine();
                pilaDeshacer.push(documento);
                documento += nuevoTexto;
                pilaRehacer.clear();
                break;

            case 2:
                if (!pilaDeshacer.isEmpty()) {
                    pilaRehacer.push(documento);
                    documento = pilaDeshacer.pop();
                } else {
                    System.out.println("No hay acciones para deshacer.");
                }
                break;

            case 3:
                if (!pilaRehacer.isEmpty()) {
                    pilaDeshacer.push(documento);
                    documento = pilaRehacer.pop();
                } else {
                    System.out.println("No hay acciones para rehacer.");
                }
                break;

            case 4:
                salir = true;
                break;

            default:
                System.out.println("Opción no válida. Intenta nuevamente.");
                break;
        }
    }

    scanner.close();
    System.out.println("\nPrograma terminado. el Documento final: \"" + documento + "\"");
}
}



