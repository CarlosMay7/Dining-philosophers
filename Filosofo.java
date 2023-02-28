import java.util.Random;
import java.util.concurrent.Semaphore;
 

public class Filosofo extends Thread {
  
    private final int nFilosofo;

    private final int[][] palFilosofo;

    private final Semaphore[] palSemaforo;

    private final int palIzquierdo;

    private final int palDerecho;

    protected Filosofo(int nFilosofo, Semaphore[] palSemaforo, int[][] palFilosofo) {
        this.nFilosofo = nFilosofo;
        this.palFilosofo = palFilosofo;
        this.palSemaforo = palSemaforo;
        this.palIzquierdo = palFilosofo[nFilosofo][0];
        this.palDerecho = palFilosofo[nFilosofo][1];
    }

    protected void comer() {
        if (palSemaforo[palIzquierdo].tryAcquire()) {                           
            if (palSemaforo[palDerecho].tryAcquire()) {                         
                //System.out.println("Filósofo " + nFilosofo + " está comiendo.");
                Interfaz.setEstado(nFilosofo, "comiendo");                      
                Interfaz.setMano(palIzquierdo, "mano");                         
                Interfaz.setMano(palDerecho, "mano");
                try {
                    sleep(new Random().nextInt(2000) + 1000);                   
                } catch (InterruptedException ex) {
                    System.out.println("Error en método comer: "+ex.toString());
                }
                //System.out.println("Filósofo " + nFilosofo + " ha terminado de comer. Suelta los palillos " + palIzquierdo + " y " + palDerecho);
                Interfaz.setEstado(nFilosofo, "servido");                       
                Interfaz.setMano(palIzquierdo, "empty");
                Interfaz.setMano(palDerecho, "empty");
                palSemaforo[palDerecho].release();                              
            }
            palSemaforo[palIzquierdo].release();                                
        } else { 
            //System.out.println("Filósofo " + nFilosofo + " está esperando."); 
            Interfaz.setEstado(nFilosofo, "esperando");                         
        }
    }
 
    protected void pensar() {
        try {   //System.out.println("Filósofo " + nFilosofo + " está pensando.");
            Filosofo.sleep(new Random().nextInt(2000) + 1000);                  
            Interfaz.setEstado(nFilosofo, "pensando");                          
        } catch (InterruptedException ex) {
            System.out.println("Error en método pensar: "+ex.toString());
        }
    }
 
    @Override
    public void run() {
        while (Interfaz.sw) {                                                   
            pensar();
            comer();
        }
    }
}