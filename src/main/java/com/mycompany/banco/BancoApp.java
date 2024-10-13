/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.banco;
import java.util.Scanner;
/**
 *
 * @author josue
 */
public class BancoApp {
    private ColaPrioridad cola;
    private Caja[] cajas;

    public BancoApp() {
        cola = new ColaPrioridad();
        cajas = new Caja[4];
        for (int i = 0; i < 4; i++) {
            cajas[i] = new Caja();
        }
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarOpciones();
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    agregarClienteConCuenta(scanner);
                    break;
                case 2:
                    agregarClienteSinCuenta();
                    break;
                case 3:
                    atenderCliente(scanner);
                    break;
                case 4:
                    ingresarDineroACaja(scanner);
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 5);
    }

    private void mostrarOpciones() {
        System.out.println("1. Agregar cliente con cuenta (ingresa prioridad 042, 022 o 011)");
        System.out.println("2. Agregar cliente sin cuenta");
        System.out.println("3. Atender cliente");
        System.out.println("4. Ingresar dinero a caja");
        System.out.println("5. Salir");
        System.out.print("Elige una opcion: ");
    }

    private void agregarClienteConCuenta(Scanner scanner) {
        System.out.print("Ingresa la prioridad del cliente (042, 022 o 011): ");
        String prioridadStr = scanner.next();
        Prioridad prioridad = new Prioridad(prioridadStr);
        Cliente cliente = new Cliente(prioridad);
        cola.agregarCliente(cliente);
        System.out.println("Cliente con cuenta agregado.");
    }

    private void agregarClienteSinCuenta() {
        Cliente cliente = new Cliente(new Prioridad("0"));
        cola.agregarCliente(cliente);
        System.out.println("Cliente sin cuenta agregado a la cola.");
    }

    private void atenderCliente(Scanner scanner) {
    if (!cola.estaVacia()) {
        Cliente clienteAtendido = cola.atenderCliente();
        if (clienteAtendido.getPrioridad().getValor() == Integer.MAX_VALUE) {
            System.out.println("Atendiendo cliente sin prioridad.");
        } else {
            System.out.println("Atendiendo cliente con prioridad: " + clienteAtendido.getPrioridad().getValor());
        }

        System.out.println("Desea ingresar (1) o retirar (2) dinero? (0 para no hacer nada): ");
        int accion = scanner.nextInt();

        switch (accion) {
            case 1:
                ingresarDineroACaja(scanner);
                break;
            case 2:
                retirarDineroDeCaja(scanner);
                break;
            case 0:
                System.out.println("No se realiza ninguna operacion de dinero.");
                break;
            default:
                System.out.println("Opcion no valida. No se realiza ninguna operacion de dinero.");
        }

        mostrarDineroCajas();
    } else {
        System.out.println("No hay clientes en la cola.");
    }
}

    private void ingresarDineroACaja(Scanner scanner) {
        System.out.print("Elige caja (1-4): ");
        int cajaIndex = scanner.nextInt() - 1;
        if (cajaIndex >= 0 && cajaIndex < cajas.length) {
            System.out.println("Ingresa las denominaciones (termina con un valor no numerico): ");
            double total = 0;
            while (true) {
                String input = scanner.next();
                try {
                    double cantidad = Double.parseDouble(input);
                    total += cantidad;
                } catch (NumberFormatException e) {
                    break;
                }
            }
            cajas[cajaIndex].agregarDinero(total);
            System.out.println("Dinero depositado: " + total);
            mostrarDineroCajas();
        } else {
            System.out.println("Caja no valida.");
        }
    }

    private void retirarDineroDeCaja(Scanner scanner) {
        System.out.print("Elige caja (1-4) de la que desea retirar dinero: ");
        int cajaIndex = scanner.nextInt() - 1;
        if (cajaIndex >= 0 && cajaIndex < cajas.length) {
            System.out.print("Ingrese la cantidad a retirar: ");
            double cantidad = scanner.nextDouble();
            if (cajas[cajaIndex].getDinero() >= cantidad) {
                cajas[cajaIndex].retirarDinero(cantidad);
                System.out.println("Dinero retirado: " + cantidad);
            } else {
                System.out.println("Fondos insuficientes en la caja.");
            }
        } else {
            System.out.println("Caja no valida.");
        }
    }

    private void mostrarDineroCajas() {
        for (int i = 0; i < cajas.length; i++) {
            System.out.println("Dinero en caja " + (i + 1) + ": " + cajas[i].getDinero());
        }
    }

    public static void main(String[] args) {
        BancoApp app = new BancoApp();
        app.iniciar();
    }
}