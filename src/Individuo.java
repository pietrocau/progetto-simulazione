public class Individuo {

    private int id;                     //numero identificativo unico dell'individuo
    private int stato = Individuo.SANO; //numero rappresentante lo stato di salute dell'individuo (vedi sotto)
    private float decorsoMalattia;      //(numero da 0 a 1) indica a che punto nel decorso della malattia si trova l'individuo (0 = sano, 1 = guarito/morto)
    private Ambiente ambiente;          //ambiente in cui l'individuo si trova attualmente
    private float posX;                 //posizione dell'indiviudo nell'ambiente sull'asse x
    private float posY;                 //posizione dell'individuo nell'ambiente sull'asse y

    //possibili stati dell'individuo
    public static final int SANO = 0;           //individuo sano (non ha mai avuto il virus, negativo al tampone)
    public static final int INFETTO = 1;        //individuo infetto (ha il virus, non è ne contagioso, ne sintomatico, negativo al tampone)
    public static final int ASINTOMATICO = 2;   //individuo asintomatico (ha il virus, è contagioso, non è sintomatico, positivo al tampone)
    public static final int SINTOMATICO = 3;    //individuo sintomatico (ha il virus, è contagioso, è sintomatico, visibilmente infetto e positivo al tampone)
    public static final int MORTO = 4;          //individuo morto (ha avuto il virus, è morto, negativo al tampone)
    public static final int IMMUNE = 5;         //individuo immune (ha avuto il virus, è guarito, non può ammalarsi di nuovo, negativo al tampone)

    public Individuo (int id, Ambiente ambiente, float posX, float posY){
        this.id = id;
        this.ambiente = ambiente;
        this.posX = posX;
        this.posY = posY;
    }

    public int getId() {
        return id;
    }

    public int getStato() {
        return stato;
    }

    public float getDecorsoMalattia() {
        return decorsoMalattia;
    }

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public void setAmbiente (Ambiente nuovoAmbiente) {
        this.ambiente = nuovoAmbiente;
    }
}
