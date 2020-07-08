import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class SimulazioneTest extends Simulazione {

    //semplice simulazione con un solo ambiente

    Ambiente ambiente;
    Virus coronavirus = new Virus("covid",1,0.5f,0.1f,10,0.1f,0.5f, 0.9f);

    public SimulazioneTest(){ //costruttore simulazione
        int p = 1000;
        int c = 10;
        float d = coronavirus.getDurata();
        float f1 = 10*p*c;
        float f2 = p*d;
        int R = (int) Math.min(f1,f2);
        this.strategia =   new StrategiaGregge(this);
        this.virus = coronavirus;
        ambiente = new Ambiente("",10000,new Vettore(),400,400,400);
        ambienti.add(ambiente); //aggiungiamo tutti gli ambienti in ambienti
		for(int i = 0; i < 100; i++){
			ambiente.aggiungiIndividuo(new Individuo(i));
		}
    }

    public void updateSimulazione(){
        super.updateSimulazione(); //chiamiamo il metodo della classe Simulazione che si occupa di chiamare l'update su tutti gli ambienti
        //qui inseriamo il codice specifico a questa simulazione
        // todo: System.out.println(ambiente.getRisorse());
    }


}
