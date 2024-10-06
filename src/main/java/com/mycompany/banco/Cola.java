/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.banco;

/**
 *
 * @author josue
 */
public class Cola {
    private int[] cola;
    private int inicio;
    private int fin;
    private int capacidad;

    public Cola(int capacidad) {
        this.cola = new int[capacidad];
        this.inicio = 0;
        this.fin = 0;
        this.capacidad = capacidad;
    }

    public void agregarCliente() {
        if ((fin + 1) % capacidad == inicio) {
            System.out.println("La cola esta llena");
        } else {
            cola[fin] = 1;
            fin = (fin + 1) % capacidad;
            System.out.println("Cliente agregado a la cola");
        }
    }

    public void atenderCliente() {
        if (estaVacia()) {
            System.out.println("No hay clientes en la cola");
        } else {
            inicio = (inicio + 1) % capacidad;
            System.out.println("Cliente atendido");
        }
    }

    public boolean estaVacia() {
        return inicio == fin;
    }
}
