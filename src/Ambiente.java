import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Ambiente {

    //Concettualmente, un ambiente è il posto dove gli individui della simulazione vivono e si spostano.
    //una simulazione può contenere più ambienti, gli ambienti sono completamente separati e indipendenti.
    //si può tuttavia spostare individui da un ambiente all'altro.

    private String nome;    //il nome dell'ambiente
    private int risorse;    //la quantita di risorse a disposizione di un ambiente.
    private float posX;     //la posizione sull'asse x dell'ambiente all'interno del componenete grafico della simulazione
    private float posY;     //la posizione sull'asse y dell'ambiente all'interno del componenete grafico della simulazione
    private float larghezza;    //la larghezza (pixel) dell'ambiente
    private float altezza;      //l'altezza (pixel) dell'ambiente
    private ArrayList<Individuo> individui; //lista  di individui in un ambiente
    private float scala; //IDEA: modifica la dimensione degli individui per ragioni di spazio

    public Ambiente(String nome,int risorse,  float posX, float posY, float larghezza, float altezza, float scala){ //costruttore ambiente
        this.nome = nome;
        this.posX = posX;
        this.posY = posY;
        this.risorse = risorse;
        this.larghezza = larghezza;
        this.altezza = altezza;
        this.scala = scala;
        this.individui = new ArrayList<>();
    }

    public void draw(Graphics2D g2d){
        Shape shape = new Rectangle2D.Float(posX,posY,larghezza,altezza);
        g2d.setColor(new Color(242,242,242));
        g2d.fill(shape);
        g2d.setColor(new Color(0,0,0));
        g2d.setStroke(new BasicStroke(1f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND));
        g2d.draw(shape);
        drawIndividui(g2d);
    }

    private void drawIndividui(Graphics2D g2d){
       for(Individuo ind : individui){
           ind.draw(g2d);
       }
    }

    public Ambiente(String nome,int risorse, float posX, float posY, float larghezza, float altezza){ //overloading costruttore senza scala
        this(nome,risorse, posX, posY, larghezza,altezza,1);
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
