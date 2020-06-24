import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class SimulazioneTest extends Simulazione {

    //semplice simulazione con un solo ambiente

    Ambiente ambiente;
    Virus coronavirus = new Virus("covid",0,0,0,0,0,0);

    public SimulazioneTest(){ //costruttore simulazione
        ambiente = new Ambiente("",1000,new Vettore(),0,400,400);
        ambienti.add(ambiente); //aggiungiamo tutti gli ambienti in ambienti
		for(int i = 0; i < 10; i++){
			ambiente.aggiungiIndividuo(new Individuo(i));
		}
    }

    public void updateSimulazione(){
        super.updateSimulazione(); //chiamiamo il metodo della classe Simulazione che si occupa di chiamare l'update su tutti gli ambienti
        //qui inseriamo il codice specifico a questa simulazione
    }

    public void infettaIndividuoRandom(){
        int aRand = (int) Math.random() * ambienti.size();
        Ambiente a = ambienti.get(aRand);
        int iRand = (int) Math.random() * a.getIndividui().size();
        Individuo i = a.getIndividui().get(iRand);
        i.infetta();
    }
}
