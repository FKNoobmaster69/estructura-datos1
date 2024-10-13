
public class Cajav1 {
    private double dineroCaja;

    public Cajav1() {
        this.dineroCaja = 0;
    }

    public void depositar(double[] denominaciones) {
        for (int i = 0; i < denominaciones.length; i++) {
            dineroCaja += denominaciones[i];
        }
        System.out.println("Dinero depositado: " + dineroCaja);
    }

    public void retirar(double monto) {
        if (dineroCaja >= monto) {
            dineroCaja -= monto;
            System.out.println("Retiro exitoso. Dinero restante en caja: " + dineroCaja);
        } else {
            System.out.println("Fondos insuficientes en la caja.");
        }
    }

    public double getDineroCaja() {
        return dineroCaja;
    }
}