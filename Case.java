public class Case{
    Position pos;
    Direction dir;

    Case (Position pos, Direction dir){
        this.pos = pos;
        this.dir = dir;
    }
    Case(){
        this.pos = new Position();
        this.dir = new Direction();
    }
}