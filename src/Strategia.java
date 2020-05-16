public interface Strategia {

    void applica(); //chiamato ogni frame dell'applicazione

    private boolean tampone(Individuo individuo) throws IllegalArgumentException{
            individuo.getAmbiente().spendiRisorse(1); //todo: numero di risorse per il tampone
            if(individuo.getStato() == Individuo.ASINTOMATICO || individuo.getStato() == Individuo.SINTOMATICO){
                return true;
            }
            return false;
    }



}
