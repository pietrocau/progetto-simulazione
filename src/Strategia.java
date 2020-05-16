public interface Strategia {

    void applica(); //chiamato ogni frame dell'applicazione

    private boolean tampone(Ambiente ambiente,Individuo individuo) throws IllegalArgumentException{
        if(ambiente.getIndividui().contains(individuo)){
            ambiente.spendiRisorse(1); //todo: numero di risorse per il tampone
            //if(individuo.getStato() == Individuo.ASINTOMATICO || individuo.getStato() == Individuo.SINTOMATICO){
                return true;
            //}
            //return false;
        }
        //throw new IllegalArgumentException("L'individuo " + individuo.getId() + "non è presente nell'ambiente" + ambiente.getNome());
        return true;
    }



}
