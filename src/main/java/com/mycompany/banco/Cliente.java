/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.banco;

/**
 *
 * @author josue
 */
public class Cliente {
    private Prioridad prioridad;
    private long timestamp;

    public Cliente(Prioridad prioridad) {
        this.prioridad = prioridad;
        this.timestamp = System.currentTimeMillis();
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
