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

	public String toString(){
		return "("+x+","+y+")";
	}
}
