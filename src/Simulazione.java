import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.function.Function;

public class Simulazione extends JComponent {

    public static int frame = 0; //contiene il frame corrente della simulazione.
    public static int fxGiorno = 10; //Un giorno è definito come il passaggio di questo numero di frames.
    public static float giorno = 0; //contiene il giorno corrente della simulazione.
    ArrayList<Ambiente> ambienti = new ArrayList<Ambiente>();   //Nel costruttore inserire qui tutti gli ambienti radice
    Virus virus;    //riferimento al virus specifico alla simulazione
    Strategia strategia;
    boolean strategiaAttiva = false;  //true se la strategia è in atto
    boolean simulazioneFinita = false; //true se la simulazione ha ragginto il termine (collasso, Virus debellato)
    Individuo pazienteZero;

    public Simulazione(ArrayList<Ambiente> ambienti, Virus virus, Strategia strategia){
        this.ambienti = ambienti;
        this.virus = virus;
        this.strategia = strategia;
        this.strategia.setSim(this);
    }

    @Override
    public void paint(Graphics g){ //override del metodo in JComponent che permette di "disegnare" delle forme
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); //impostazioni antialasing
        for(Ambiente ambiente : ambienti){
            ambiente.draw(g2d); //disegnamo l'ambiente
        }
    }

    public void updateSimulazione(){ //funzione chiamata ripetutamente (ogni frame)
        giorno += 1f/fxGiorno;
        frame++;
        for(Ambiente ambiente : ambienti){
            if(!simulazioneFinita){
                ambiente.update();  //update dell'ambiente
            }
        }

        if(strategiaAttiva){
            strategia.applica();
        }
        else if(pazienteZero.getStato() == Individuo.SINTOMATICO){
            strategiaAttiva = true;
            strategia.comincia();
        }

        if(frame%fxGiorno==0 && !simulazioneFinita){ //una volta al giorno stampo le statistiche
            int nMorti = 0;
            int nMalati = 0;
            int nGuariti = 0;
            int ris = ambienti.get(0).getRisorse();
            for(Individuo i : ambienti.get(0).getIndividui()){
                int s = i.getStato();
                if(s == Individuo.MORTO){
                    nMorti++;
                }
                else if(s == Individuo.INFETTO || s == Individuo.SINTOMATICO || s == Individuo.ASINTOMATICO){
                    nMalati++;
                }
                else if(s == Individuo.IMMUNE){
                    nGuariti++;
                }
            }

            if(nMalati == 0 && strategiaAttiva){
                System.out.println("Virus debellato con " + nMorti + " individui morti e " + nGuariti + " individui guariti." );
                simulazioneFinita=true;
            }
            else{
                System.out.println("Giorno " + (int)(giorno) + ": "  + nMorti + " morti, " + nMalati + " malati, " + nGuariti + " guariti, " + ris + " risorse");
            }
        }
        else if(!simulazioneFinita && ambienti.get(0).getCollassato()){
            System.out.println("Ambiente collassato.");
            simulazioneFinita = true;
        }

        this.repaint(); //ri-disegnamo tutta la simulazione aggiornata dall'update
    }

    public void infettaIndividuoRandom(){
        int aRand = (int) Math.random() * ambienti.size();
        Ambiente a = ambienti.get(aRand);
        int iRand = (int) Math.random() * a.getIndividui().size();
        Individuo i = a.getIndividui().get(iRand);
        pazienteZero = i;
        pazienteZero.setPazienteZero(true);
        i.infetta(virus);
    }
}
