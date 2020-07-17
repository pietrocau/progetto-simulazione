/*
LOCKDOWN: 
Una certa percentuale degli individui non si può muovere.

Tamponi:
Eseguiamo un numero di tamponi relativo alle risorse, e chi dovesse risultare positivo, viene bloccato.

"App":
Stessa cosa di tamponi. Ma invece che a random, Dà precedenza ai contatti dell'individuo.

Zona rossa:
Eseguiamo tamponi nelle vicinanze di un tampone positivo.

Immunità di gregge:
Zero tamponi, nessuo fermo.

Social distancing:
Effetto repulsivo, gli individi si respingono.

Isolamento geografico:
Separazione fra ambienti radice.
*/
public interface Strategia {

    void applica(); //chiamato ogni frame dell'applicazione, quando la strategia è in atto
    void comincia();// chiamato una sola volta all'inizio della strategia (serve??)

    default boolean tampone(Individuo individuo){
        individuo.getAmbiente().spendiRisorse(10);
        int s = individuo.getStato();
        if(s == Individuo.ASINTOMATICO || s == Individuo.SINTOMATICO){
            return true;
        }
        return false;
    }

    public void setSim(Simulazione sim);


}
