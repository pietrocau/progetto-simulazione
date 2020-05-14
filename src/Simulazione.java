import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class Simulazione extends JComponent {

    Ambiente ambiente;

    public Simulazione(){

        ambiente = new Ambiente("",1000,0,0,400,400);
        ambiente.aggiungiIndividuo(new Individuo(0,ambiente,50,50));
        ambiente.aggiungiIndividuo(new Individuo(1,ambiente,50,100));
        ambiente.aggiungiIndividuo(new Individuo(2,ambiente,300,300));
        //int popolazione = 1;
        //for (int i = 0; i < popolazione; i++) {
        //   ambiente.aggiungiIndividuo(new Individuo(i,ambiente,50,50));
        //}
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        ambiente.draw(g2d);

        for(Individuo i : ambiente.getIndividui()){
           //Shape shapeIndividuo = i.getShape();
        }
    }

}
