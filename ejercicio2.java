import java.util.Comparator;
import java.util.PriorityQueue;
//importe comparator para comparar las palabras y las vocales, no me mande a extra :)

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ejercicio2 {
    public static void main(String[] args) {

        String[] palabras = {
                "Menos", "Nabucodonosor", "Bebida", "Angelópolis", "Corleone",
                "Avión", "Planta", "Centro", "comercial", "Ana", "Cthulhu"
        };


        PriorityQueue<String> colaPrioridad = new PriorityQueue<>(new Comparator<String>() {

            public int compare(String o1, String o2) {

                int vocalesO1 = contarVocales(o1);
                int vocalesO2 = contarVocales(o2);


                return Integer.compare(vocalesO2, vocalesO1);
            }
        });


        for (String palabra : palabras) {
            colaPrioridad.offer(palabra);
        }


        System.out.println("Cola de prioridad ordenada por número de vocales:");
        while (!colaPrioridad.isEmpty()) {
            String palabra = colaPrioridad.poll();
            System.out.println(palabra + " (Vocales: " + contarVocales(palabra) + ")");
        }
    }


    private static int contarVocales(String palabra) {
        int contador = 0;
        String vocales = "aeiouAEIOU";
        for (char c : palabra.toCharArray()) {
            if (vocales.indexOf(c) != -1) {
                contador++;
            }
        }
        return contador;
    }
}