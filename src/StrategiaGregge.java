import java.util.ArrayList;

public class StrategiaGregge implements Strategia {

    Simulazione simulazione;

    public StrategiaGregge(Simulazione simulazione){
        this.simulazione = simulazione;
    }

    @Override
    public void comincia(){
    }

    @Override
    public void applica() {
        System.out.println("La strategia immunità di gregge è stata applicata. Con essa continua il normale svolgimento della vita quotidiana senza restrizioni o quant' altro");
    }
}
