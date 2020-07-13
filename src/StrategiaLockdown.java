import java.util.ArrayList;

public class StrategiaLockdown implements Strategia {

    Simulazione simulazione;
    ArrayList<Individuo> individui = simulazione.ambienti.get(0).getIndividui();
    int conta_individui = 0;
    Individuo i;
    public StrategiaLockdown(Simulazione simulazione){
        this.simulazione = simulazione;
    }

    @Override
    public void comincia(){
        int non_lavoratori;
        for (Individuo x: individui) {
            conta_individui += 1;           //numero di invidui
        }
        non_lavoratori = (conta_individui * 85) / 100;      //l'85% delle persone è rimasta a casa,
        for (int j = 0; j <= non_lavoratori; j ++) {        //solo medici, alcuni tipi di aziende per
            i.blocca();                                     //beni primari hanno continuato a lavorare
        }
    }

    @Override
    public void applica() {
         System.out.println("La strategia lockdown è stata applicata. Con essa solo il perosnale medico e alcuni dipendenti di  aziende per beni primari hanno continuato a lavorare");

    }
}
