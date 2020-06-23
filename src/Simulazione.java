import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Simulazione extends JComponent {

    ArrayList<Ambiente> ambienti = new ArrayList<Ambiente>();   //Nel costruttore inserire qui tutti gli ambienti radice

    public void paint(Graphics g){ //override del metodo in JComponent che permette di "disegnare" delle forme
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); //impostazioni antialasing
        for(Ambiente ambiente : ambienti){
            ambiente.draw(g2d); //disegnamo l'ambiente
        }
    }

    public void updateSimulazione(){ //funzione chiamata ripetutamente (ogni frame)
        for(Ambiente ambiente : ambienti){
            ambiente.update();  //update dell'ambiente
        }
        this.repaint(); //ri-disegnamo tutta la simulazione aggiornata dall'update
    }

}
