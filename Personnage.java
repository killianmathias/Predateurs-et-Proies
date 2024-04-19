public class Personnage extends Case{
    private Direction direction;
    
    public Personnage (Position position, char symbole, Direction direction){
        super(position,symbole);
        this.direction = direction;
    }
    public Personnage(){
        super();
        this.direction = new Direction();
    }
    

    

    
    
    
}