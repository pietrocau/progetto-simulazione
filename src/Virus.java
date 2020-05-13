public class Virus {

    private String nome;            //nome del virus/malattia
    private float infettivita;      //(numero da 0 a 1) la probabilità che un individuo che entra in contatto con il virus venga contagiato
    private float sintomaticita;    //(numero da 0 a 1) la probabilità che un individuo contagiato con il virus sviluppi sintomi
    private float letalita;         //(numero da 0 a 1) la probabilità che un individuo che ha sviluppato sintomi muoia del virus
    private float durata;           //il numero di giorni che intercorrono fra il momento del contagio e quello della guarigione.
    private float durataIncubazione; //(numero da 0 a 1) la percentuale della durata della malattia che deve trascorrere prima che l'individuo infetto possa diventare contagioso
    private float durataAsintomatico;     //(numero da 0 a 1) la percentuale della durata della malattia che deve trascorrere prima che l'individuo infetto possa diventare sintomatico

    public Virus(String nome, float infettivita, float sintomaticita, float letalita, float durata, float durataIncubazione, float durataAsintomatico){     //costruttore Virus
        this.nome = nome;
        this.infettivita = infettivita;
        this.sintomaticita = sintomaticita;
        this.letalita = letalita;
        this.durata = durata;
        this.durataIncubazione =  durataIncubazione;
        this.durataAsintomatico = durataAsintomatico;
    }

    public String getNome() {
        return nome;
    }

    public float getInfettivita() {
        return infettivita;
    }

    public float getSintomaticita() {
        return sintomaticita;
    }

    public float getLetalita() {
        return letalita;
    }

    public float getDurata() {
        return durata;
    }

    public float getDurataIncubazione() {
        return durataIncubazione;
    }

    public float getDurataAsintomatico() {
        return durataAsintomatico;
    }
}
