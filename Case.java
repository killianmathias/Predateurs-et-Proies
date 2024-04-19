public class Case extends Grille{
    private Position position;
    private char symbole;

    public Case (Position position, char symbole){
        this.position = position;
        this.symbole = symbole;
    }
    Case(Position position){
        this(position,'.');
    }
    Case(){
        this(new Position());
    }
    void afficherCase(){
        Ecran.afficher(" "+ this.symbole+ " ");
    }
}