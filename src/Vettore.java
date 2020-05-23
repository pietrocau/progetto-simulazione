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

	public static float distanza(Vettore a,Vettore b){
		return (a.meno(b)).lunghezza();
	}

	public static Vettore direzioneRandom(){
        double x;
        double y;
        double p = Math.PI*2;
        double rand = Math.random()*p;
        x = Math.cos(rand);
        y = Math.sin(rand);
        return new Vettore((float) x, (float) y);
	}
	public String toString(){
		return "("+x+","+y+")";
	}
}
