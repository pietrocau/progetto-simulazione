import javax.swing.*;
import java.awt.*;

public class App extends JFrame{

    public App(){
        Simulazione simulazione = new Simulazione();
        initGUI(simulazione);
    }

    private void initGUI(Simulazione sim){  //inizializza l'interfaccia grafica dell'applicazione
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(220,221,221));
        this.setTitle("Simulazione");
        this.add(sim,BorderLayout.CENTER);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }

}
