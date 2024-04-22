public class Case extends Grille{
    private Position position;
    private char symbole;

    public Case(Position position, char symbole){
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
        System.out.print(" "+ this.symbole+ " ");
    }
    public Position getPosition(){
        return position;
    }
    public void setPosition(Position newPosition){
        this.position=newPosition;
    }
    public void setSymbole(char symbole){
        this.symbole = symbole;
    }
}