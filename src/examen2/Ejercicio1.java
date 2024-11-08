/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen2;

/**
 *
 * @author josue
 */
import java.util.*;

public class Ejercicio1 {
    public static class NumericNodo {
        private NumericNodo enlace;
        private Integer valor;

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

    public static double calcularMedia(NumericPriorityQueue cola) {
        double suma = 0;
        int count = 0;
        NumericNodo t = cola.getFront();
        while (t != null) {
            suma += t.getValor();
            count++;
            t = t.getEnlace();
        }
        return suma / count;
    }

    public static int calcularModa(NumericPriorityQueue cola) {
        HashMap<Integer, Integer> contador = new HashMap<>();
        NumericNodo t = cola.getFront();
        while (t != null) {
            contador.put(t.getValor(), contador.getOrDefault(t.getValor(), 0) + 1);
            t = t.getEnlace();
        }
        return contador.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();
    }

    public static double calcularMediana(NumericPriorityQueue cola) {
        ArrayList<Integer> lista = new ArrayList<>();
        NumericNodo t = cola.getFront();
        while (t != null) {
            lista.add(t.getValor());
            t = t.getEnlace();
        }
        Collections.sort(lista);
        int n = lista.size();
        if (n % 2 == 0) {
            return (lista.get(n / 2 - 1) + lista.get(n / 2)) / 2.0;
        } else {
            return lista.get(n / 2);
        }
    }

    public static void main(String[] args) {
        NumericPriorityQueue cola = new NumericPriorityQueue();
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            int numero = random.nextInt(100) + 1;
            cola.enqueue(numero);
        }

        System.out.println("Lista de numeros: " + cola);
        System.out.println("Media aritmetica: " + calcularMedia(cola));
        System.out.println("Moda: " + calcularModa(cola));
        System.out.println("Mediana: " + calcularMediana(cola));
    }
}
