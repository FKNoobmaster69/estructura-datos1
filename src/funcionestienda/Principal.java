/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funcionestienda;
import java.util.Scanner;
public class Principal {
    public static void main(String[] args) {
        Maquina maquina = new Maquina();
        maquina.agregarProducto(0, "cheetos", 10);
        maquina.agregarProducto(1, "marimba", 5);
        maquina.agregarProducto(2, "aciditos", 2);

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menu:");
            System.out.println("1. Comprar producto");
            System.out.println("2. Agregar moneda");
            System.out.println("3. Imprimir estado de la maquina");
            System.out.println("4. Agregar/Editar producto");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Productos disponibles:");
                    for (int i = 0; i < maquina.productos.length; i++) {
                        if (maquina.productos[i] != null) {
                            System.out.println("Codigo: " + i + ", Descripcion: " + maquina.productos[i].descripcion + ", Precio: " + maquina.productos[i].precio);
                        }
                    }
                    System.out.print("Ingrese el codigo del producto: ");
                    int codigo = scanner.nextInt();
                    maquina.comprarProducto(codigo);
                    break;
                case 2:
                    System.out.print("Ingrese la denominacion de la moneda (10, 5, 2, 1, 0.50): ");
                    double denominacion = scanner.nextDouble();
                    maquina.agregarMoneda(denominacion);
                    break;
                case 3:
                    maquina.imprimirEstado();
                    break;
                case 4:
                    System.out.print("Ingrese el codigo del producto (0-9): ");
                    int codProducto = scanner.nextInt();
                    System.out.print("Ingrese la descripcion del producto: ");
                    String descripcion = scanner.next();
                    System.out.print("Ingrese el precio del producto: ");
                    double precio = scanner.nextDouble();
                    maquina.agregarProducto(codProducto, descripcion, precio);
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        } while (opcion != 5);

        scanner.close();
    }
}