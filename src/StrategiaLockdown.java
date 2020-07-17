import java.util.ArrayList;

public class StrategiaLockdown implements Strategia {

    Simulazione simulazione;
    float perc; // numbero da 0 a 1, percentuale della popolazione in lockdown

    public StrategiaLockdown(float perc){
        this.perc = perc;
    }

    @Override
    public void comincia(){
        ArrayList<Individuo> individui = simulazione.ambienti.get(0).getIndividui();
        int n = (int) (individui.size()*perc);
        for (int i = 0; i < n; i++) {
            individui.get(i).blocca();
        }
    }

    @Override
    public void applica() { }

    @Override
    public void setSim(Simulazione sim) {
        this.simulazione = sim;
    }

}
