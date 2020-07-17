import java.util.ArrayList;

public class StrategiaGregge implements Strategia {

    Simulazione simulazione;

    public StrategiaGregge(){ }

    @Override
    public void comincia(){
    }

    @Override
    public boolean tampone(Individuo individuo) throws IllegalArgumentException {
        return false;
    }

    @Override
    public void setSim(Simulazione sim) {
        this.simulazione = sim;
    }

    @Override
    public void applica() {
    }
}
