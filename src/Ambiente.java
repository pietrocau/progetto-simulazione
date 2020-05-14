import java.util.ArrayList;

public class Ambiente {

    //Concettualmente, un ambiente è il posto dove gli individui della simulazione vivono e si spostano.
    //una simulazione può contenere più ambienti, gli ambienti sono completamente separati e indipendenti.
    //si può tuttavia spostare individui da un ambiente all'altro.

    private String nome;    //il nome dell'ambiente
    private float larghezza;    //la larghezza (pixel) dell'ambiente
    private float altezza;  //l'altezza (pixel) dell'ambiente
    private ArrayList<Individuo> individui; //lista  di individui in un ambiente
    private float scala; //IDEA: modifica la dimensione degli individui per ragioni di spazio

    public Ambiente(String nome,float larghezza, float altezza, float scala){ //costruttore ambiente
        this.nome = nome;
        this.larghezza = larghezza;
        this.altezza = altezza;
        this.scala = scala;
        this.individui = new ArrayList<>();
    }

    public Ambiente(String nome,float larghezza, float altezza){ //overloading costruttore senza scala
        this(nome,larghezza,altezza,1);
    }

    public void aggiungiIndividuo(Individuo individuo){
        individui.add(individuo);
    }

    public  void rimuoviIndividuo(Individuo individuo){
        individui.remove(individuo);
    }

    public String getNome() {
        return nome;
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
}