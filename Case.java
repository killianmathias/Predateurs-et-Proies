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
        if (this instanceof Chasseur){
            System.out.print("\u001B[34m"+ " " + this.symbole+ " ");
        }else if (this instanceof Renard){
            System.out.print("\u001B[38;5;208m"+ " " + this.symbole+ " ");
        }else if (this instanceof Poule){
            System.out.print("\u001B[35m"+ " " + this.symbole+ " ");
        }else if (this instanceof Lapin){
            System.out.print("\u001B[38;5;240m"+ " " + this.symbole+ " ");
        }else if (this instanceof Herbe){
            System.out.print("\u001B[32m"+ " " + this.symbole+ " ");
        }else{
            System.out.print("\u001B[0m"+" "+ this.symbole+ " ");
        }
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