/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen2;

/**
 *
 * @author josue
 */
 import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

public class Ejercicio3 {

    
    public static class NumericNodo {
        private NumericNodo enlace;
        private Integer valor;

        public NumericNodo() {}

        public NumericNodo(Integer valor) {
            this.valor = valor;
            this.enlace = null;
        }

        public NumericNodo getEnlace() {
            return enlace;
        }

        public void setEnlace(NumericNodo enlace) {
            this.enlace = enlace;
        }

        public Integer getValor() {
            return valor;
        }

        public void setValor(Integer valor) {
            this.valor = valor;
        }
    }

   
    public static class NumericPriorityQueue {
        private NumericNodo front;
        private NumericNodo rear;
        private int size;

        public NumericPriorityQueue() {
            front = null;
            rear = null;
            size = 0;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void enqueue(Integer value) {
            NumericNodo n = new NumericNodo(value);
            if (isEmpty()) {
                front = n;
                rear = n;
            } else {
                rear.setEnlace(n);
                rear = n;
            }
            size++;
        }

        public Object dequeue() {
            if (!isEmpty()) {
                Object value = front.getValor();
                front = front.getEnlace();
                size--;
                return value;
            }
            return null;
        }

        public String toString() {
            StringBuilder s = new StringBuilder();
            NumericNodo t = front;
            while (t != null) {
                s.append(t.getValor()).append(" < ");
                t = t.getEnlace();
            }
            return s.toString();
        }

        public NumericNodo getFront() {
            return front;
        }
    }

    
    public static void main(String[] args) {
        NumericPriorityQueue cola = new NumericPriorityQueue();
        Random random = new Random();
        HashSet<Integer> set = new HashSet<>();
        LinkedList<Integer> listaNoRepetidos = new LinkedList<>();

        
        for (int i = 0; i < 50; i++) {
            int numero = random.nextInt(50) + 1; 
            cola.enqueue(numero);

            
            if (!set.contains(numero)) {
                listaNoRepetidos.add(numero);
                set.add(numero);
            }
        }

       
        System.out.println("la cola final es : " + cola);
        System.out.println("los numeros que no se repetieron fueron : " + listaNoRepetidos);
    }
}
