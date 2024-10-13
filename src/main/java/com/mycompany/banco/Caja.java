/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.banco;

/**
 *
 * @author josue
 */
public class Caja {
    private double dinero;

    public Caja() {
        this.dinero = 2000.0;
    }

    public double getDinero() {
        return dinero;
    }

    public void agregarDinero(double cantidad) {
        this.dinero += cantidad;
    }

    public boolean retirarDinero(double cantidad) {
        if (cantidad <= dinero) {
            dinero -= cantidad;
            return true;
        }
        return false;
    }
}
