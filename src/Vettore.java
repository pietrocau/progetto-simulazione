public class Vettore {

	public float x; //componente x
	public float y; //componente y

	public Vettore(float x, float y){
		this.x = x;
		this.y = y;
	}

	public Vettore(){ //ovveride del costruttore, crea un vettore origine
		this(0,0);
	}

	public Vettore piu(Vettore v){
		return new Vettore(x+v.x,y+v.y);
	}

	public Vettore per(float f){
		return new Vettore(x*f,y*f);
	}

	public float lunghezza(){
		return (float) Math.sqrt(x*x+y*y);
	}

	public Vettore direzione(){
		float l = lunghezza();
		return new Vettore(x/l,y/l);
	}

	public Vettore inverso(){
		return new Vettore(-x,-y);
	}

	public Vettore meno(Vettore v){
		return this.piu(v.inverso());
	}

	public boolean uguale(Vettore v){
		if(this.x == v.x && this.y == v.y){
			return true;
		}
		return false;
	}

	public static float distanza(Vettore a,Vettore b){
		return (a.meno(b)).lunghezza();
	}

	public static Vettore direzioneRandom(){ //restituisce un vettore normalizzato (cos(x)^2+sin(y)^2=1) casuale
        double x;
        double y;
        double p = Math.PI*2;
        double rand = Math.random()*p;
        x = Math.cos(rand);
        y = Math.sin(rand);
        return new Vettore((float) x, (float) y);
	}

	public boolean inRect(Vettore pos,float l,float a){ //restituisce true se il vettore (inteso come posizione) è all'interno dell'rettangolo in posizione pos (x,y) di larghezza l e altrezza a
		if(this.x >= pos.x && this.x <= pos.x+l && this.y >= pos.y && this.y <= pos.y+a){
			return true;
		}
		return false;
	}

	public String toString(){
		return "("+x+","+y+")";
	}
}
