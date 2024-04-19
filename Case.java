public class Case extends Grille{
    Position pos;

    void setCase (Position pos){
        this.pos = pos;
    }
    Case(){
        this.pos = new Position();
    }
}