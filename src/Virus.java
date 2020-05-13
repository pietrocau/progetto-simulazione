public class Virus {
    private double infettività;
    private double presenza_sintomi;
    private double morte;
    private int guarigione;


    public Virus(double infettività, double presenza_sintomi, double morte, int guarigione ){       //ogni virus ha la probabilità che due
        this.infettività = infettività;                                                             //individui si contagino, la probabilità di
        this.presenza_sintomi = presenza_sintomi;                                                   //avere sintomi, la prob di morte e i giorni
        this.morte = morte;                                                                         //di guarigione.
        this.guarigione = guarigione;
    }


}
