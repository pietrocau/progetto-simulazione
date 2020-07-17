import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.function.DoubleToIntFunction;

public class Individuo {

    private int id;                     //numero identificativo unico dell'individuo
    private int stato = Individuo.SANO; //numero rappresentante lo stato di salute dell'individuo (vedi sotto)
    private float decorsoMalattia;      //(numero da 0 a 1) indica a che punto nel decorso della malattia si trova l'individuo (0 = sano, 1 = guarito/morto)
    private float giornoInfezione;     //momento (giorno) nel quale l'individuo ha contratto il virus;
    private Ambiente ambiente;          //ambiente in cui l'individuo si trova attualmente
    private Vettore pos;                //posizione RELATIVA ALL'AMBIENTE dell'individuo
    private Vettore dir;                //vettore normalizzato della direzione in cui l'individuo si sta muovendo
    private boolean inMovimento = true;   //indica se l'individuo si sta muovendo meno
    private boolean bloccato = false;    //true se l'individuo stato bloccato per seguire la strategia
    private boolean checkSintomatico = false; //indica se l'individuo è già stato sottoposto alla probabilità di presentare sintomi
    private boolean checkMorto = false; //indica se l'individuo è già stato sottoposto alla probabilità di morire
    private boolean isPazienteZero = false; //indica se l'individuo è il primo infetto dal virus

    //possibili stati dell'individuo
    public static final int SANO = 0;           //individuo sano (non ha mai avuto il virus, negativo al tampone)
    public static final int INFETTO = 1;        //individuo infetto (ha il virus, non è ne contagioso, ne sintomatico, negativo al tampone)
    public static final int ASINTOMATICO = 2;   //individuo asintomatico (ha il virus, è contagioso, non è sintomatico, positivo al tampone)
    public static final int SINTOMATICO = 3;    //individuo sintomatico (ha il virus, è contagioso, è sintomatico, visibilmente infetto e positivo al tampone)
    public static final int IMMUNE = 4;         //individuo immune (ha avuto il virus, è guarito, non può ammalarsi di nuovo, negativo al tampone)
    public static final int MORTO = 5;          //individuo morto (ha avuto il virus, è morto, positivo al tampone)

    public static float VELOCITA = 1;   //velocità a cui si muove un individuo
    public static final float SIZE = 10;        //diametro in pixel del cerchio rappresentante l'individuo
    public static final Color VERDE = new Color(39, 174, 96);
    public static final Color GIALLO = new Color(242, 201, 76);
    public static final Color ROSSO = new Color(235, 87, 87);
    public static final Color BLU = new Color(45, 156, 219);
    public static final Color NERO = new Color(130, 130, 130);
    public static final Color[] COLORI = {VERDE, VERDE, GIALLO, ROSSO, BLU, NERO}; //in questa array le posizioni dei colori corrispondo alle costanti rapprententanti lo stato dell'individuo

    private Virus virus;    //il virus con cui l'individuo è o è stato infettato

    public Individuo(int id) { //costruttore individuo
        this.id = id;
    }

    public void draw(Graphics2D g2d) {  //disegna il cerchio dell'individuo in g2d
        Shape shape = new Ellipse2D.Float(pos.x + ambiente.getPos().x-(Individuo.SIZE/2), pos.y + ambiente.getPos().y-(Individuo.SIZE/2), Individuo.SIZE, Individuo.SIZE); //un cerchio di diametro size
        g2d.setColor(getColore(stato)); //ottengo il colore da dare al cerchio
        g2d.fill(shape);
        g2d.draw(shape);
    }

    public void update() {  //chiamato ripetutamente ogni frame
		if (stato == Individuo.MORTO || stato == Individuo.SINTOMATICO || bloccato){
			inMovimento = false;
		}
		else{
			inMovimento = true;	
		}

        if (inMovimento) {
            pos = pos.piu(dir.per(Individuo.VELOCITA)); //spostiamo l'individuo
        }
		else if(stato!=Individuo.MORTO && Simulazione.frame%Simulazione.fxGiorno==0){    //i morti non consumano risorse
			consumaRisorse();
		}

		if(virus!=null && decorsoMalattia < 1){
            decorsoMalattia = (Simulazione.giorno-giornoInfezione)/virus.getDurata();
            if(decorsoMalattia>=virus.getDurataSintomi() && !checkMorto){
                checkMorto();
            }
            else if(decorsoMalattia>=virus.getDurataAsintomatico() && !checkSintomatico){
                checkSintomatico();
            }
            else if(decorsoMalattia>=virus.getDurataIncubazione() && !checkSintomatico){
                this.stato = Individuo.ASINTOMATICO;
            }
        }
		else if(decorsoMalattia >= 1 && stato != Individuo.MORTO){
		    stato = Individuo.IMMUNE;
        }
    }

    private void checkSintomatico(){
        checkSintomatico = true;
        float rnd = (float) Math.random();
        if(rnd<virus.getSintomaticita() || isPazienteZero){
            this.stato = Individuo.SINTOMATICO;
        }
    }

    private void checkMorto(){
        checkMorto = true;
        float rnd = (float) Math.random();
        if(rnd<virus.getLetalita()){
            this.stato = Individuo.MORTO;
            decorsoMalattia = 1;
            virus = null;
        }

    }

    public void consumaRisorse() {   //metodo chiamato 1 volta al giorno se l'individuo non	si muove ed è vivo
        ambiente.spendiRisorse(1);
    }

    public void infetta(Virus virus){
        this.stato = Individuo.INFETTO;
        this.virus = virus;
        this.giornoInfezione = Simulazione.giorno;
    }

    public static Color getColore(int stato) throws IllegalArgumentException {  //restituisce il colore di un individuo in base al suo stato
        if (stato < 0 || stato > 5) throw new IllegalArgumentException();
        return COLORI[stato];
    }

    public void blocca(){
        this.bloccato = true;
    }

    public void sblocca(){
        this.bloccato = false;
    }

    public boolean getBloccato(){
        return bloccato;
    }

    public void rimbalza(float a){ //modifica la direzione in cui l'individuo si muove simulando un impatto con un corpo che lo colpisce ad un angolo di "a" rad
        float c = (float) Math.cos(a);
        float s = (float) Math.sin(a);
        dir.x *= -1*c;
        //dir.y *= -1*s;
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

    public Vettore getPos() {
        return pos;
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(Ambiente nuovoAmbiente) {
        this.ambiente = nuovoAmbiente;
    }

	public void setPos(Vettore pos){
		this.pos = pos;	
	}

	public void setDir(Vettore dir){
		this.dir = dir;
	}

    public Vettore getDir() {
        return dir;
    }

    public Virus getVirus() {
        return virus;
    }

    public void setPazienteZero(boolean pazienteZero) {
        isPazienteZero = pazienteZero;
    }
}
