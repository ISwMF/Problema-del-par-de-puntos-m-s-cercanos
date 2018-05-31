
import javax.swing.JOptionPane;


public class main {

    public static void main(String[] args) {
        
        int tamaño = Integer.parseInt(JOptionPane.showInputDialog("Número de puntos"));
        
        Mostrados M = new Mostrados(tamaño);
        M.setVisible(true);

    }
}
