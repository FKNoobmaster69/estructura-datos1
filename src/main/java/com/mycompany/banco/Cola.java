/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.banco;
import java.util.LinkedList;
import java.util.Queue;
/**
 *
 * @author josue
 */
public class Cola {
    private Queue<Cliente> clientes;
    private int capacidad;

    public Cola(int capacidad) {
        this.capacidad = capacidad;
        clientes = new LinkedList<>();
    }

    public void agregarCliente(Cliente cliente) {
        if (clientes.size() < capacidad) {
            clientes.add(cliente);
        } else {
            System.out.println("Cola llena");
        }
    }

    public boolean estaVacia() {
        return clientes.isEmpty();
    }

    public void atenderCliente() {
        if (!clientes.isEmpty()) {
            Cliente cliente = clientes.poll();
            if (cliente != null) {
                System.out.println("Atendiendo cliente con prioridad " + cliente.getPrioridad().getValor());
            }
        } else {
            System.out.println("No hay clientes en la cola");
        }
    }
}
