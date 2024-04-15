public class Personnage extends Case{
    Direction dir;
    void setCase(Position pos,Direction dir){
        super.setCase(pos);
        this.dir = dir;
    }
    
}