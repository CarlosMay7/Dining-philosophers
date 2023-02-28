import java.util.concurrent.Semaphore;

public class Main {

    protected final static int numFilosofos = 5;
    
    protected final static int[][] palFilosofo = {
        {0, 4},                                                                 
        {1, 0},                                                                 
        {2, 1},                                                                 
        {3, 2},                                                                 
        {4, 3}                                                                  
    };

    protected final static Semaphore[] palSemaforo = new Semaphore[numFilosofos];

    protected static void iniciarHilos() {
        for (int i = 0; i < numFilosofos; i++) {                                
            palSemaforo[i] = new Semaphore(1);                                  
        }
        for (int i = 0; i < numFilosofos; i++) {                                
            new Filosofo(i, palSemaforo, palFilosofo).start();                  
            Interfaz.setEstado(i, "pensando");                                  
            Interfaz.setMano(i, "empty");
        }
    }
    
    public static void main(String[] args) {
        Interfaz interfaz = new Interfaz();
    }
}