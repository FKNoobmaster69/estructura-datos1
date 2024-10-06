/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.banco;

/**
 *
 * @author josue
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class BancoApp {
    private Caja[] cajas;
    private Cola colaConCuenta;
    private Cola colaSinCuenta;
    private Scanner sc;

    public BancoApp() {
        cajas = new Caja[4];
        for (int i = 0; i < 4; i++) {
            cajas[i] = new Caja();
        }
        colaConCuenta = new Cola(10);
        colaSinCuenta = new Cola(10);
        sc = new Scanner(System.in);
    }

    public void ejecutar() {
        boolean ejecutando = true;

        while (ejecutando) {
            System.out.println("1. Agregar cliente con cuenta");
            System.out.println("2. Agregar cliente sin cuenta");
            System.out.println("3. Atender cliente");
            System.out.println("4. Ingresar dinero a caja");
            System.out.println("5. Salir");
            System.out.print("Elige una opcion: ");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    colaConCuenta.agregarCliente();
                    break;
                case 2:
                    colaSinCuenta.agregarCliente();
                    break;
                case 3:
                    atenderClientes();
                    break;
                case 4:
                    ingresarDineroEnCaja();
                    break;
                case 5:
                    ejecutando = false;
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        }
        sc.close();
    }

    private void atenderClientes() {
        for (int i = 0; i < cajas.length; i++) {
            if (!colaConCuenta.estaVacia()) {
                System.out.println("Atendiendo cliente con cuenta en caja " + i);
                procesarTransaccion(cajas[i]);
                colaConCuenta.atenderCliente();
            } else if (!colaSinCuenta.estaVacia()) {
                System.out.println("Atendiendo cliente sin cuenta en caja " + i);
                procesarTransaccion(cajas[i]);
                colaSinCuenta.atenderCliente();
            }
        }
    }

    private void procesarTransaccion(Caja caja) {
        System.out.println("1. Retiro");
        System.out.println("2. Deposito");
        int opcion = sc.nextInt();
        if (opcion == 1) {
            System.out.print("Monto a retirar: ");
            double monto = sc.nextDouble();
            caja.retirar(monto);
        } else if (opcion == 2) {
            ingresarDinero(caja);
        } else {
            System.out.println("Opcion invalida");
        }
    }

    private void ingresarDineroEnCaja() {
        System.out.print("Elige caja (0-3): ");
        int cajaSeleccionada = sc.nextInt();
        ingresarDinero(cajas[cajaSeleccionada]);
    }

    private void ingresarDinero(Caja caja) {
        System.out.println("Ingresa las denominaciones (termina con un valor no numerico): ");
        double[] denominaciones = new double[10];
        int index = 0;

        while (true) {
            try {
                double denominacion = sc.nextDouble();
                if (index < denominaciones.length) {
                    denominaciones[index] = denominacion;
                    index++;
                } else {
                    System.out.println("Maximo de denominaciones alcanzado");
                    break;
                }
            } catch (InputMismatchException e) {
                
                System.out.println("Entrada no numerica detectada, terminando ingreso.");
                sc.next();  
                break;
            }
        }
        caja.depositar(denominaciones);
    }
}
