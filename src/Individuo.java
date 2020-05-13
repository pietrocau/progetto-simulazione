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

    public float spostX(float posX){                                //ho creato uno spostamento random degli individui
        posX = (float)(Math.random() * 99);                           // l'ho fatto in x e y separati perche non riesco a capire come ritornare
        return posX;                                                // i due vaalori insieme :(
    }

    public float spostY(float posY){
        posY = (float)(Math.random() * 99);                        //crea un numero random da 0 a 100
        return posY;
    }

}
