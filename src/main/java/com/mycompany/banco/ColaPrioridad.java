/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.banco;
import java.util.PriorityQueue;

/**
 *
 * @author josue
 */
import java.util.PriorityQueue;

public class ColaPrioridad {
    private PriorityQueue<Cliente> cola;

    public ColaPrioridad() {
        cola = new PriorityQueue<>((c1, c2) -> {
            if (c1.getPrioridad().getValor() != c2.getPrioridad().getValor()) {
                return Integer.compare(c1.getPrioridad().getValor(), c2.getPrioridad().getValor());
            } else {
                return Long.compare(c1.getTimestamp(), c2.getTimestamp());
            }
        });
    }

    public void agregarCliente(Cliente cliente) {
        cola.add(cliente);
    }

    public Cliente atenderCliente() {
        return cola.poll();
    }

    public boolean estaVacia() {
        return cola.isEmpty();
    }
}
