import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;
import java.util.Timer;

public class App extends JFrame{
	//questa classe contiene il main e rappresenta l'applicazione dal punto di vista grafico
    // (la finestra, infatti estende jFrame)

    private Simulazione simulazione;

    //costruttore dell'app
    public App(){

        //testing-----------------------------

        //------------------------------------

        simulazione = new SimulazioneTest();
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
