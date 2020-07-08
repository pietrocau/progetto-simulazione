import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Simulazione extends JComponent {

    public static int fxGiorno = 60; //Un giorno è definito come il passaggio di questo numero di frames.
    public static float giorno = 0; //contiene il giorno corrente della simulazione.
    ArrayList<Ambiente> ambienti = new ArrayList<Ambiente>();   //Nel costruttore inserire qui tutti gli ambienti radice
    Virus virus;    //riferimento al virus specifico alla simulazione
    Strategia strategia;
    boolean strategiaAttiva = false;
    Individuo pazienteZero;

    public void paint(Graphics g){ //override del metodo in JComponent che permette di "disegnare" delle forme
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); //impostazioni antialasing
        for(Ambiente ambiente : ambienti){
            ambiente.draw(g2d); //disegnamo l'ambiente
        }
    }

    public void updateSimulazione(){ //funzione chiamata ripetutamente (ogni frame)
        giorno += 1f/fxGiorno;
        for(Ambiente ambiente : ambienti){
            ambiente.update();  //update dell'ambiente
        }

        if(strategiaAttiva){
            strategia.applica();
        }
        else if(pazienteZero.getStato() == Individuo.SINTOMATICO){
            strategiaAttiva = true;
            strategia.comincia();
        }

        this.repaint(); //ri-disegnamo tutta la simulazione aggiornata dall'update
    }

    public void infettaIndividuoRandom(){
        int aRand = (int) Math.random() * ambienti.size();
        Ambiente a = ambienti.get(aRand);
        int iRand = (int) Math.random() * a.getIndividui().size();
        Individuo i = a.getIndividui().get(iRand);
        pazienteZero = i;
        i.infetta(virus);
    }
}
