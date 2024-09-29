/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funcionestienda;
import java.util.Scanner;
/**
 *
 * @author josue
 */
public class Maquina {
    Producto[] productos;
    int[] monedas;
    double totalMonedas;
    int maxProductos = 10;
    int maxMonedasPorDenominacion = 15;

    public Maquina() {
        productos = new Producto[maxProductos];
        monedas = new int[5];
        totalMonedas = 0;
    }

    public void agregarProducto(int codigo, String descripcion, double precio) {
        if (codigo >= 0 && codigo < maxProductos && productos[codigo] == null) {
            productos[codigo] = new Producto(descripcion, precio);
        }
    }

    public void agregarMoneda(double denominacion) {
        int index = -1;
        if (denominacion == 10) index = 0;
        else if (denominacion == 5) index = 1;
        else if (denominacion == 2) index = 2;
        else if (denominacion == 1) index = 3;
        else if (denominacion == 0.5) index = 4;

        if (index != -1 && monedas[index] < maxMonedasPorDenominacion) {
            monedas[index]++;
            totalMonedas += denominacion;
            System.out.println("Moneda de " + denominacion + " agregada. Total insertado: " + totalMonedas);
        } else {
            System.out.println("No se puede agregar mas monedas de esta denominacion.");
        }
    }

    public void comprarProducto(int codigo) {
        if (codigo >= 0 && codigo < productos.length && productos[codigo] != null) {
            Producto producto = productos[codigo];
            System.out.println("Producto seleccionado: Codigo: " + codigo + ", Descripcion: " + producto.descripcion + ", Precio: " + producto.precio);
            System.out.println("Ingrese las monedas para comprar el producto:");

            double precio = producto.precio;
            double totalInsertado = 0;
            Scanner scanner = new Scanner(System.in);
            while (totalInsertado < precio) {
                System.out.print("Ingrese la denominacion de la moneda (10, 5, 2, 1, 0.50): ");
                double denominacion = scanner.nextDouble();
                if (denominacion == 10 || denominacion == 5 || denominacion == 2 || denominacion == 1 || denominacion == 0.5) {
                    agregarMoneda(denominacion);
                    totalInsertado += denominacion;
                } else {
                    System.out.println("Denominacion no valida.");
                }
            }

            if (totalInsertado >= precio) {
                System.out.println("Producto comprado: " + producto.descripcion);
                double cambio = totalInsertado - precio;
                devolverCambio(cambio);
                productos[codigo] = null;
            }
        } else {
            System.out.println("Producto no disponible.");
        }
    }

    public void devolverCambio(double cambio) {
        System.out.println("Cambio a devolver: " + cambio);
        // Logica para devolver el cambio en monedas
        // Se puede implementar un algoritmo que devuelva las monedas adecuadas
    }

    public void imprimirEstado() {
        System.out.println("Estado de la maquina:");
        System.out.println("Total monedas en la maquina: " + totalMonedas);
        System.out.println("Cantidad de monedas 10: " + monedas[0]);
        System.out.println("Cantidad de monedas 5: " + monedas[1]);
        System.out.println("Cantidad de monedas 2: " + monedas[2]);
        System.out.println("Cantidad de monedas 1: " + monedas[3]);
        System.out.println("Cantidad de monedas 0.50: " + monedas[4]);
        System.out.println("Estado de los productos:");
        for (int i = 0; i < productos.length; i++) {
            Producto producto = productos[i];
            if (producto != null) {
                System.out.println("Codigo: " + i + ", Descripcion: " + producto.descripcion + ", Precio: " + producto.precio);
            }
        }
    }
}