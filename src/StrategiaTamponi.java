import java.util.ArrayList;

public class StrategiaTamponi implements Strategia {

    Simulazione simulazione;
    int tamponixGiorno;

    public StrategiaTamponi(int tamponixGiorno){
        this.tamponixGiorno = tamponixGiorno;
    }

    @Override
    public void applica() {
        if(Simulazione.frame%Simulazione.fxGiorno==0){
            ArrayList<Individuo> individui = simulazione.ambienti.get(0).getIndividui();
            for (int i = 0; i < tamponixGiorno; i++){
                Individuo individuoRand = individui.get((int) (Math.random() * individui.size()));
                //Individuo individuoRand = individui.get(i);
                boolean infetto = tampone(individuoRand);
                if(infetto){
                    individuoRand.blocca();
                }
                else {
                    individuoRand.sblocca(); //sblocchiamo i guariti
                }
            }
        }
    }

    @Override
    public void comincia() {

    }

    @Override
    public void setSim(Simulazione sim) {
        this.simulazione = sim;
    }
}
