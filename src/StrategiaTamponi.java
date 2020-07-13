/*import java.util.ArrayList;

public class StrategiaTamponi implements Strategia {

    Simulazione simulazione;
    int T;                                  //costo di un tampone
    int C = 3 * T;                          //costo della cura

    //risalire allo stato dell' individuo
    int risorse = simulazione.ambienti.get(0).getRisorse();
    ArrayList<Individuo> individui = simulazione.ambienti.get(0).getIndividui();

    public StrategiaTamponi(Simulazione simulazione) {
        this.simulazione = simulazione;
    }

    public int cura(int stato) {                        //cura il paziente
        individui.stato == individui.SANO;
        risorse = risorse - C;
        return risorse;
    }
    @Override
    public void comincia() {
        if (risorse > 0) {
            for (Individuo i: individui) {
                if (i.stato == i.INFETTO) {
                    i.blocca();
                    i.cura(int stato);
                }
            }
        }
        else {
            System.out.println("Noooooo siamo collassati!!!");
        }
    }

    @Override
    public void applica() {
    }

}

 */