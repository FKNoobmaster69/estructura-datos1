/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funcionestienda;

/**
 *
 * @author josue
 */
public class ColaCircular<T> {
    private T[] cola;
    private int frente;
    private int atras;
    private int max;
    private int cantidad;

    @SuppressWarnings("unchecked")
    public ColaCircular(int max) {
        this.max = max;
        cola = (T[]) new Object[max];
        frente = 0;
        atras = -1;
        cantidad = 0;
    }

    public void agregar(T elemento) {
        if (cantidad < max) {
            atras = (atras + 1) % max;
            cola[atras] = elemento;
            cantidad++;
        } else {
            System.out.println("Cola llena. No se puede agregar.");
        }
    }

    public T eliminar() {
        if (cantidad > 0) {
            T elemento = cola[frente];
            frente = (frente + 1) % max;
            cantidad--;
            return elemento;
        } else {
            System.out.println("Cola vacia.");
            return null;
        }
    }

    public int getCantidad() {
        return cantidad;
    }
}