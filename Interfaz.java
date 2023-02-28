import java.awt.Color;
import javax.swing.*;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.border.LineBorder;

public class Interfaz extends JFrame {
    
    private JMenuItem[] mi = new JMenuItem[3];
    
    private JLabel estado;
    
    private static final String ruta = "recursos/";
    
    private static final JLabel[] estados = new JLabel[5];
    
    private static final JLabel[] manos = new JLabel[5];
    
    private final static int coordE[][] = {
        {337, 35},
        {50, 257},
        {145, 592},
        {529, 592},
        {615, 260}
    };

    private final static int coordM[][] = {
        {230, 215},
        {170, 400},
        {325, 515},
        {475, 400},
        {415, 215}
    };
    
    protected static boolean sw = false;    
    
    protected Interfaz() {
        JPanel panel = new JPanel(null);                                       
        JMenuBar mbar = new JMenuBar();                                        
        JMenu menu = new JMenu("Opciones");                                     
        String nomMI[] = {"Comenzar", "Detener", "Salir"};

        JLabel escenario = new JLabel(new ImageIcon(                             
                Interfaz.class.getResource(ruta + "escenario.png")));           
        escenario.setBorder(new LineBorder(Color.black, 1, true));
        for (int i = 0; i < Main.numFilosofos; i++) {
            estados[i] = new JLabel();                                          
            panel.add(estados[i]).setBounds(coordE[i][0], coordE[i][1], 30, 30);
            manos[i] = new JLabel();                                            
            panel.add(manos[i]).setBounds(coordM[i][0], coordM[i][1], 50, 50);
        }
        estado = new JLabel(new ImageIcon(
                Interfaz.class.getResource(ruta + "empty.png")));
        panel.add(estado).setBounds(322, 346, 60, 60);
        panel.add(escenario).setBounds(2, 2, 700, 700);
        
        for (int i = 0; i < nomMI.length; i++) {
            mi[i] = new JMenuItem(nomMI[i]);                                    
            menu.add(mi[i]).setAccelerator(KeyStroke.getKeyStroke(              
                    nomMI[i].charAt(0), KeyEvent.CTRL_DOWN_MASK));
            mi[i].addActionListener(al);
        }
        mbar.add(menu);
        mi[1].setEnabled(false);                                                
        

        JFrame frame = new JFrame("Filósofos Comensales");                      
        frame.add(panel);                                                       
        frame.setJMenuBar(mbar);                                                
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(Toolkit.getDefaultToolkit().createImage(
                Interfaz.class.getResource(ruta + "logo.png")));                
        frame.setSize(710, 756);                                                
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
   
    ActionListener al = (ActionEvent evt) -> {                                  
        switch (evt.getActionCommand()) {                                       
            case "Comenzar":                                                    
                //System.out.println("Ejecución comenzada");
                sw = true;                                                      
                Main.iniciarHilos();                                            
                estado.setIcon(new ImageIcon(
                        Interfaz.class.getResource(ruta + "loading.gif")));
                mi[0].setEnabled(false);                                        
                mi[1].setEnabled(true);                                         
                break;
            case "Detener":
                //System.out.println("Ejecución detenida");
                sw = false;                                                     
                estado.setIcon(new ImageIcon(
                        Interfaz.class.getResource(ruta + "empty.png")));
                mi[0].setEnabled(true);
                mi[1].setEnabled(false);
                break;
            default:
                System.exit(0);                                                 
        }
    };

    protected static void setEstado(int n, String nombre) {
        estados[n].setIcon(new ImageIcon(
                Interfaz.class.getResource(ruta + nombre + ".png")));
    }
    
    protected static void setMano(int n, String nombre) {
        manos[n].setIcon(new ImageIcon(
                Interfaz.class.getResource(ruta + nombre + ".png")));
    }
}