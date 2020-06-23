import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Ambiente {

	//Concettualmente, un ambiente è il posto dove gli individui della simulazione vivono e si spostano.
	//una simulazione può contenere più ambienti, gli ambienti sono completamente separati e indipendenti.
	//si può tuttavia spostare individui da un ambiente all'altro.
	//Inoltre un ambiente può contenere altri ambienti, in tal caso, tutti gli ambienti che sono all'interno di un ambiente,
	//o all'interno di un ambiente che è all interno di un ambiente,  e così via, condividono le risorse dell'ambiente principale
	//che li contiene tutti.

	private String nome;    //il nome dell'ambiente
	private int risorse;    //la quantità di risorse a disposizione di un ambiente.
	private float posX;     //la posizione sull'asse x dell'ambiente all'interno del componente grafico della simulazione
	private float posY;     //la posizione sull'asse y dell'ambiente all'interno del componente grafico della simulazione
	private float larghezza;    //la larghezza (pixel) dell'ambiente
	private float altezza;      //l'altezza (pixel) dell'ambiente
	private ArrayList<Individuo> individui; //lista  di individui in un ambiente
	private float scala; //IDEA: modifica la dimensione degli individui per ragioni di spazio

	private ArrayList<Ambiente> sottoambienti; //lista di ambienti all'interno dell'ambiente
	private Ambiente ambienteRadice; //l'ambiente radice dell'albero di ambienti di questo ambiente
	private boolean collassato = false;

	public Ambiente(String nome,int risorse,  float posX, float posY, float larghezza, float altezza, float scala){ //costruttore ambiente
		this.nome = nome;
		this.posX = posX;
		this.posY = posY;
		this.risorse = risorse;
		this.larghezza = larghezza;
		this.altezza = altezza;
		this.scala = scala;
		this.individui = new ArrayList<>();
		this.sottoambienti = new ArrayList<>();
		this.ambienteRadice = this;
	}

	public Ambiente(String nome,int risorse, float posX, float posY, float larghezza, float altezza){ //overloading costruttore senza scala
		this(nome,risorse, posX, posY, larghezza,altezza,1);
	}

	public void draw(Graphics2D g2d){
		Shape shape = new Rectangle2D.Float(posX,posY,larghezza,altezza);
		g2d.setColor(new Color(242,242,242));
		g2d.fill(shape);
		g2d.setColor(new Color(0,0,0));
		g2d.setStroke(new BasicStroke(1f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND));
		g2d.draw(shape);
		drawIndividui(g2d);
		drawSottoambienti(g2d);
	}

	public void update(){
		if(!collassato){
			updateIndividui();
			updateSottoambienti();
		}
	}

	private void drawIndividui(Graphics2D g2d){
		for(Individuo individuo : individui){
			individuo.draw(g2d);
		}
	}

	private void updateIndividui(){
		for(Individuo individuo : individui){
			individuo.update();
		}
	}

	private void drawSottoambienti(Graphics2D g2d){
		for(Ambiente ambiente : sottoambienti){
			ambiente.draw(g2d);
		}
	}

	private void updateSottoambienti(){
		for(Ambiente ambiente : sottoambienti){
			ambiente.update();
		}
	}

	//aumenta di n la quantità di risorse dell'ambiente radice
	public void guadagnaRisorse(int n){
		if(!collassato){
			if(ambienteRadice == this){	//controllo se questo è l'ambienteRadice
				risorse += n; //se sì incremento le risorse
			}
			else{
				ambienteRadice.guadagnaRisorse(n); //altrimenti chiamo il metodo dell'ambiete radice
			}
		}
	}

	//riduce di n la quantità di risorse dell'ambiente radice
	public void spendiRisorse(int n){
		if(ambienteRadice == this){	//controllo se questo è l'ambienteRadice
			risorse -= n; //se sì decremento le risorse
			if(risorse <= 0){ //quando le risorse vanno sotto 0 l'ambiente collassa
				risorse = 0;
				collassato = true;
			}
		}
		else{
			ambienteRadice.spendiRisorse(n); //altrimenti chiamo il metodo dell'ambiete radice
		}
	}

	public void aggiungiIndividuo(Individuo individuo){
		individui.add(individuo);
		individuo.setAmbiente(this);
		individuo.setPos(getPosRandom());
		individuo.setDir(Vettore.direzioneRandom());
	}

	public void rimuoviIndividuo(Individuo individuo){
		individui.remove(individuo);
	}

	public void aggiungiAmbiente(Ambiente ambiente){
		sottoambienti.add(ambiente);
		ambiente.ambienteRadice = this.ambienteRadice;
	}

    public Vettore getPosRandom(){ //restituisce un vettore rappresentante una posizione random all'interno di questo ambiente
        float x;
        float y;
        x = (float)(Math.random()*larghezza);
        y = (float)(Math.random()*altezza);
        return new Vettore((float) x, (float) y);
    }


	public boolean collisione(Vettore pos){ //restituisce true se non c'è nessun individuo in quel punto dell'ambiente, false altrimenti
		for (Individuo individuo : individui) { //controlliamo se lo spazio è occupato da qualche altro individuo
			if(Vettore.distanza(pos,individuo.getPos())<Individuo.SIZE){
				return false;
			}
		}
		for(Ambiente ambiente : sottoambienti){
			//todo:complete
			//if()
		}
		return true;
	}

	public String getNome() {
		return nome;
	}

	public float getPosX() {
		return posX;
	}

	public float getPosY() {
		return posY;
	}

	public float getLarghezza() {
		return larghezza;
	}

	public float getAltezza() {
		return altezza;
	}

	public float getScala() {
		return scala;
	}

	public ArrayList<Individuo> getIndividui() {
		return individui;
	}

	public boolean getCollassato(){
		System.out.println("Ambiente collassato");
		return collassato;
	}

}
