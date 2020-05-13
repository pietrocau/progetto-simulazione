public class Individuo {
    private String salute;
    private float posX;
    private float posY;
    private boolean inmovimento;

    public Individuo (String salute, float posX, float posY, boolean inmovimento ){   //ogni individuo ha una salute(verde,giallo,rosso ..),
        this.posX = posX;                                                             // e false se è fermo)
        this.posY = posY;
        this.inmovimento = inmovimento;
    }


}
