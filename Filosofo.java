public class Filosofo implements Runnable {

    private final Object palilloIzq;
    private final Object palilloDer;

    Filosofo(Object izquierdo, Object derecho) {
        this.palilloIzq = izquierdo;
        this.palilloDer = derecho;
    }

    private void accion(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + action);
        Thread.sleep(3000);
    }

    @Override
    public void run() {
        try {
            while (true) {
                accion("Filosofando");
                synchronized (palilloIzq) {
                    accion("Tomando palillo izquierdo");
                    synchronized (palilloDer) {
                        accion("Tomando el palillo derecho, toca comer");
                        accion("Deja el palillo derecho");
                    }
                    accion("Dejando el palillo izquierdo, otra vez a filosofar");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}