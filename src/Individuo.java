import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Individuo {

    private int id;                     //numero identificativo unico dell'individuo
    private int stato = Individuo.SANO; //numero rappresentante lo stato di salute dell'individuo (vedi sotto)
    private float decorsoMalattia;      //(numero da 0 a 1) indica a che punto nel decorso della malattia si trova l'individuo (0 = sano, 1 = guarito/morto)
    private Ambiente ambiente;          //ambiente in cui l'individuo si trova attualmente
    private float posX;                 //posizione dell'indiviudo nell'ambiente sull'asse x
    private float posY;                 //posizione dell'individuo nell'ambiente sull'asse y
    private float dirX;                 //componente x della direzione in cui si muove l'individuo
    private float dirY;                 //componente y della direzione in cui si muove l'individuo
    private boolean inMovimento = true;   //indica se l'individuo può muoversi o meno

    //possibili stati dell'individuo
    public static final int SANO = 0;           //individuo sano (non ha mai avuto il virus, negativo al tampone)
    public static final int INFETTO = 1;        //individuo infetto (ha il virus, non è ne contagioso, ne sintomatico, negativo al tampone)
    public static final int ASINTOMATICO = 2;   //individuo asintomatico (ha il virus, è contagioso, non è sintomatico, positivo al tampone)
    public static final int SINTOMATICO = 3;    //individuo sintomatico (ha il virus, è contagioso, è sintomatico, visibilmente infetto e positivo al tampone)
    public static final int IMMUNE = 4;         //individuo immune (ha avuto il virus, è guarito, non può ammalarsi di nuovo, negativo al tampone)
    public static final int MORTO = 5;          //individuo morto (ha avuto il virus, è morto, negativo al tampone)

    public static float VELOCITA = 5;   //velocità a cui si muove un individuo
    public static final float SIZE = 10;        //diametro in pixel del cerchio rappresentante l'individuo
    public static final Color VERDE = new Color(39, 174, 96);
    public static final Color GIALLO = new Color(242, 201, 76);
    public static final Color ROSSO = new Color(235, 87, 87);
    public static final Color BLU = new Color(45, 156, 219);
    public static final Color NERO = new Color(130, 130, 130);
    public static final Color[] COLORI = {VERDE, VERDE, GIALLO, ROSSO, BLU, NERO}; //in questa array le posizioni dei colori corrispondo alle costanti rapprententanti lo stato dell'individuo

    public Individuo(int id, Ambiente ambiente, float posX, float posY, float dirX, float dirY) { //costruttore individuo
        this.id = id;
        this.ambiente = ambiente;
        this.posX = posX;
        this.posY = posY;
        this.dirX = dirX;
        this.dirY = dirY;
    }

    public void draw(Graphics2D g2d) {  //disegna il cerchio dell'individuo in g2d
        Shape shape = new Ellipse2D.Float(posX + ambiente.getPosX(), posY + ambiente.getPosY(), Individuo.SIZE, Individuo.SIZE); //un cerchio di diametro size
        g2d.setColor(getColore(stato)); //ottengo il colore da dare al cerchio
        g2d.fill(shape);
        g2d.draw(shape);
    }

    public void update() {
        if (inMovimento) {
            posX += Individuo.VELOCITA * dirX;
            posY += Individuo.VELOCITA * dirY;
        }
    }

    public void produciRisorse() {   //metodo chiamato 1 volta al giorno se l'individuo si muove
        ambiente.guadagnaRisorse(1);
    }

    public static Color getColore(int stato) throws IllegalArgumentException {  //restituisce il colore di un individuo in base al suo stato
        if (stato < 0 || stato > 5) throw new IllegalArgumentException();
        return COLORI[stato];
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

    public void setAmbiente(Ambiente nuovoAmbiente) {
        this.ambiente = nuovoAmbiente;
    }
}
