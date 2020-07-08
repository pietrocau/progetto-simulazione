import java.util.ArrayList;

public class StrategiaLockdown implements Strategia {

    Simulazione simulazione;

    public StrategiaLockdown(Simulazione simulazione){
        this.simulazione = simulazione;
    }

    @Override
    public void comincia(){
        ArrayList<Individuo> individui = simulazione.ambienti.get(0).getIndividui();
        for (Individuo i: individui) {
            i.blocca();
        }
    }

    @Override
    public void applica() {
        System.out.println("Strategia applicata");
    }
}
