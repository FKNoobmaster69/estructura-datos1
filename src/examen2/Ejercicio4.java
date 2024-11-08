/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen2;

/**
 *
 * @author josue
 */
import java.util.ArrayList;
import java.util.List;

import java.util.LinkedList;

public class Ejercicio4 {
    public static void main(String[] args) {
        String palabra = "SuS";
        LinkedList<Character> lista = new LinkedList<>();

        for (int i = 0; i < palabra.length(); i++) {
            lista.add(palabra.charAt(i));
        }

        boolean esPalindromo = true;
        while (lista.size() > 1) {
            if (lista.removeFirst() != lista.removeLast()) {
                esPalindromo = false;
                break;
            }
        }

        System.out.println(esPalindromo ? "si es  palindromo XD" : "no es palindromo XD´nT");
    }
}
