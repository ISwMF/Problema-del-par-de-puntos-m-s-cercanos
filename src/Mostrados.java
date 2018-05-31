
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class Mostrados extends JFrame {

    public Mostrados(int tamaño) {
        JPanel PanelIntervalo = new JPanel(new GridLayout(tamaño, 3));
        JPanel PanelBoton = new JPanel(new GridLayout(0, 1));
        JPanel PanelTodo = new JPanel();
        PanelTodo.setLayout(new BoxLayout(PanelTodo, BoxLayout.PAGE_AXIS));

        SpinnerNumberModel[][] Modelos = new SpinnerNumberModel[tamaño][2];
        JSpinner[][] Intervalos = new JSpinner[tamaño][2];

        for (int i = 0; i < tamaño; i++) {
            Modelos[i][0] = new SpinnerNumberModel(0.0, -1000.0, 1000.0, 0.1);
            Intervalos[i][0] = new JSpinner(Modelos[i][0]);
            Modelos[i][1] = new SpinnerNumberModel(0.0, -1000.0, 1000.0, 0.1);
            Intervalos[i][1] = new JSpinner(Modelos[i][1]);
            PanelIntervalo.add(new JLabel("Punto " + (i + 1)));
            PanelIntervalo.add(Intervalos[i][0]);
            PanelIntervalo.add(Intervalos[i][1]);
        }

        JButton boton = new JButton("Ojalá no se rompa el programa");
        boton.addActionListener((ActionEvent e) -> {
            Double[][] Puntos = new Double[tamaño][2];
            for (int i = 0; i < Intervalos.length; i++) {
                Puntos[i][0] = (Double) Intervalos[i][0].getValue();
                Puntos[i][1] = (Double) Intervalos[i][1].getValue();
            }
            Encontrador I = new Encontrador();
            JOptionPane.showMessageDialog(null, "Punto más cercano: " + I.resolver(Puntos));

        });
        PanelBoton.add(boton);

        PanelTodo.add(PanelIntervalo);
        PanelTodo.add(PanelBoton);
        this.add(PanelTodo);
        this.setSize(300, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
