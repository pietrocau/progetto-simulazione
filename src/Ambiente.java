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
	private int risorse;    //la quantita di risorse a disposizione di un ambiente.
	private Vettore pos;     //
	private float larghezza;    //la larghezza (pixel) dell'ambiente
	private float altezza;      //l'altezza (pixel) dell'ambiente
	private ArrayList<Individuo> individui; //lista  di individui in un ambiente
	private float scala; //IDEA: modifica la dimensione degli individui per ragioni di spazio

	private ArrayList<Ambiente> sottoambienti; //lista di ambienti all'interno dell'ambiente
	private Ambiente ambienteRadice; //l'ambiente radice dell'albero di ambienti di questo ambiente
	private boolean collassato = false;

	public Ambiente(String nome,int risorse,  Vettore pos, float larghezza, float altezza, float scala){ //costruttore ambiente
		this.nome = nome;
		this.pos = pos;
		this.risorse = risorse;
		this.larghezza = larghezza;
		this.altezza = altezza;
		this.scala = scala;
		this.individui = new ArrayList<>();
		this.sottoambienti = new ArrayList<>();
		this.ambienteRadice = this;
	}

	public Ambiente(String nome,int risorse, Vettore pos, float larghezza, float altezza){ //overloading costruttore senza scala
		this(nome,risorse, pos, larghezza,altezza,1);
	}

	public void draw(Graphics2D g2d){
		Shape shape = new Rectangle2D.Float(pos.x, pos.y, larghezza, altezza);
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
			checkCollisioniIndividuoMuro();
			checkCollisioniIndividuoIndividuo();
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
        x = (float) (Individuo.SIZE/2 + (Math.random()*(larghezza-Individuo.SIZE)));
        y = (float)(Individuo.SIZE/2 + (Math.random()*(altezza-Individuo.SIZE)));
        return new Vettore((float) x, (float) y);
    }

    private void checkCollisioniIndividuoIndividuo(){
		for (Individuo i1 : individui) {
			for (Individuo i2 : individui){
				if(i1 != i2 && Vettore.distanza(i1.getPos(),i2.getPos())<Individuo.SIZE){
					//collisione tra 2 individui from https://spicyyoghurt.com/tutorials/html5-javascript-game-development/collision-detection-physics

					//rimbalzo
					Vettore dirAB = i2.getPos().meno(i1.getPos()).direzione();
					i1.setDir(i1.getDir().meno(dirAB).direzione());
					i2.setDir(i2.getDir().piu(dirAB).direzione());

					checkInfetto(i1, i2);
					checkInfetto(i2, i1);
				}
			}
		}
	}

	private void checkCollisioniIndividuoMuro(){
		for (Individuo i : individui) {
			Vettore p = i.getPos();
			if(p.x-Individuo.SIZE/2 <= 0){
				//i.rimbalza((float)Math.PI);
				i.getDir().x = -i.getDir().x;
			}
			else if(p.x+Individuo.SIZE/2 >= larghezza){
				//i.rimbalza(0);
				i.getDir().x = -i.getDir().x;
			}
			else if(p.y-Individuo.SIZE/2 <= 0){
				//i.rimbalza((float)Math.PI/2);
				i.getDir().y = -i.getDir().y;
			}
			else if(p.y+Individuo.SIZE/2 >= altezza){
				//i.rimbalza((float)(3*Math.PI/4));
				i.getDir().y = -i.getDir().y;
			}
		}
	}

	private void checkCollisioniIndividuoSottoambiente(){
		for (Individuo i : individui) {
			Vettore p = i.getPos();
			for (Ambiente a : sottoambienti) {
				Vettore ap = a.getPos();
				float r = Individuo.SIZE/2;
				if(p.inRect(ap,a.larghezza,a.altezza)
						|| intersecaCerchio(ap.x,ap.y,ap.x,ap.y+a.altezza,p,r)
						|| intersecaCerchio(ap.x+a.larghezza, ap.y, ap.x+a.larghezza,ap.y+a.altezza,p,r)
						|| intersecaCerchio(ap.x,ap.y,ap.x+a.larghezza,ap.y,p,r)
						|| intersecaCerchio(ap.x, ap.y+a.altezza, ap.x+a.larghezza,ap.y+a.altezza,p,r)){
					//collisione individuo ambiente
				}
			}
		}
	}

	private void checkInfetto(Individuo i1, Individuo i2){ //decide se l'individuo i1 infetta l'individuo i2
		if(i1.getStato() == Individuo.SINTOMATICO || i1.getStato() == Individuo.ASINTOMATICO && i2.getStato() == Individuo.SANO){
			float rnd = (float) Math.random();
			if(rnd<i1.getVirus().getInfettivita()){
				i2.infetta(i1.getVirus());
			}
		}
	}

	public boolean collisione(Vettore pos){ //restituisce true se non c'è nessun individuo in quel punto dell'ambiente, false altrimenti
		for (Individuo individuo : individui) { //controlliamo se lo spazio è occupato da qualche altro individuo
			if(Vettore.distanza(pos,individuo.getPos())<Individuo.SIZE/2){
				return false;
			}
		}
		for(Ambiente ambiente : sottoambienti){
			//todo:complete
			//if()
		}
		return true;
	}

	//todo: funzionerà???
	private boolean intersecaCerchio(float x1, float y1, float x2, float y2, Vettore c, float r) { //restituisce true se il il segmento che va da (x1,y1) a (x2,y2) interseca o è tangente al cerchio di centro (x,y) e raggio r
		Vettore a = new Vettore(x1,y1);
		Vettore b = new Vettore(x2,y2);
		Vettore abDir = b.meno(a).direzione();
		float t = abDir.x*(c.x-a.x) + abDir.y*(c.y-a.y);
		Vettore e = (abDir.piu(a)).per(t);
		float dist = Vettore.distanza(e,c);
		if(dist <= r && (a.x <= c.x && c.x <= b.x && a.y <= c.y && c.y <= b.y)){
			return true;
		}
		return false;
	}

	public String getNome() {
		return nome;
	}

	public Vettore getPos() {
		return pos;
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

	public int getRisorse(){
		return this.risorse;
	}
}
