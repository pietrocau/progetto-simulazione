import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;

public class App extends JFrame{
	//questa classe contiene il main e rappresenta l'applicazione dal punto di vista grafico
    // (la finestra, infatti estende jFrame)

    private Simulazione simulazione;

    //costruttore dell'app
    public App(String[] args){

        int pop = Integer.parseInt(args[0]); //popolazione
        int ris = Integer.parseInt(args[1]); //risorse
        String nomeVirus = args[2]; //virus
        String nomeStrat = args[3]; //strategia

        Virus virus = null;
        if(nomeVirus.equals("covid")){
            virus = new Virus("covid",0.6f,0.5f,0.2f,10,0.33f,0.66f, 0.9f);
        }
        else if(nomeVirus.equals("raffreddore")){
            virus = new Virus("raffreddore",0.99f,0.01f,0f,8,0.1f,0.5f, 1f);
        }
        else if(nomeVirus.equals("ebola")){
            virus = new Virus("ebola",0.6f,0.9f,0.9f,10,0.1f,0.2f, 0.3f);
        }

        Strategia strategia = null;
        if(nomeStrat.equals("gregge")){
            strategia = new StrategiaGregge();
        }
        else if(nomeStrat.equals("lockdown")){
            strategia = new StrategiaLockdown(Float.parseFloat(args[4])/100);
        }
        else if(nomeStrat.equals("tamponi")){
            strategia = new StrategiaTamponi(Integer.parseInt(args[4]));
        }

        Ambiente a1 = new Ambiente("A1",ris, pop,new Vettore(),400,400);
        ArrayList<Ambiente> ambienti = new ArrayList<Ambiente>();
        ambienti.add(a1);
        simulazione = new Simulazione(ambienti,virus,strategia);

        initGUI(simulazione);
        TimerTask updateTask = new TimerTask() {
            @Override
            public void run() {
                update();
            }
        };
        final int FPS = 30;  //numero di update al secondo
        Timer timer = new Timer();
        timer.schedule(updateTask,0,(1000/FPS));

        simulazione.infettaIndividuoRandom();
    }

    private void update(){ //chiamato ripetutamente ogni frame
        simulazione.updateSimulazione();
    }

    private void initGUI(Simulazione sim){  //inizializza l'interfaccia grafica dell'applicazione
        this.setSize(400,440);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(220,221,221));
        this.setTitle("Simulazione");
        this.add(sim,BorderLayout.CENTER);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new App(args);
    }

}
