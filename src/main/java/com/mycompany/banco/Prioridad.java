/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.banco;

/**
 *
 * @author josue
 */
public class Prioridad {
    private int valor;

    public Prioridad(String prioridad) {
        switch (prioridad) {
            case "042":
                this.valor = 1;
                break;
            case "022":
                this.valor = 2;
                break;
            case "011":
                this.valor = 3;
                break;
            default:
                this.valor = Integer.MAX_VALUE;
                break;
        }
    }

    public int getValor() {
        return valor;
    }
}
