public class FilosofosComensales {

    public static void main(String[] args) throws Exception {

        Filosofo[] filosofos = new Filosofo[5];
        Object[] palillos = new Object[filosofos.length];

        for (int palilloActual = 0; palilloActual < palillos.length; palilloActual++) {
            palillos[palilloActual] = new Object();
        }

        for (int filosofoActual = 0; filosofoActual < filosofos.length; filosofoActual++) {

            Object palilloIzquierdo = palillos[filosofoActual];
            Object palilloDerecho = palillos[(filosofoActual + 1) % palillos.length];

            if (filosofoActual == filosofos.length - 1) {
                filosofos[filosofoActual] = new Filosofo(palilloDerecho, palilloIzquierdo); // The last philosopher picks up the right fork first
            } else {
                filosofos[filosofoActual] = new Filosofo(palilloIzquierdo, palilloDerecho);
            }

            Thread hilo = new Thread(filosofos[filosofoActual], "Filosofo " + (filosofoActual + 1));
            hilo.start();
        }
    }
}