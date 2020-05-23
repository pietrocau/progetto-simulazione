import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class Simulazione extends JComponent {

    //semplice simulazione con un solo ambiente

    Ambiente ambiente;
    Ambiente ambiente1;

    public Simulazione(){ //costruttore simulazione
        ambiente = new Ambiente("",1000,0,0,400,400);
		for(int i = 0; i < 10; i++){
			ambiente.aggiungiIndividuo(new Individuo(0,ambiente,ambiente.getPosRandom(),ambiente.getDirRandom()));
		}
    }

    public void paint(Graphics g){ //override del metodo in JComponent che permette di "disegnare" delle forme
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); //impostazioni antialasing
        ambiente.draw(g2d); //disegnamo l'ambiente
    }

    public void updateSimulazione(){ //funzione chiamata ripetutamente (ogni frame)
        ambiente.update();  //update dell'ambiente
        this.repaint(); //ri-disegnamo tutta la simulazione aggiornata dall'update
    }
}
