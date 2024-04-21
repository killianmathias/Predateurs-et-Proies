public class Personnage extends Case{
    private Direction direction;
    private boolean aDejabouge;
    
    public Personnage (Position position, char symbole, Direction direction){
        super(position,symbole);
        this.direction = direction;
        this.aDejabouge = false;
    }
    public Personnage(){
        super();
        this.direction = new Direction();
    }
    public void seDeplacer(){
        // Ecran.afficher(getPosition().getCol());
        int nouvelleLigne = getPosition().getRow() + this.direction.getRowDir();
        int nouvelleColonne = getPosition().getCol() +this.direction.getColDir();
        // Ecran.afficher(nouvelleColonne);
        setPosition(new Position(nouvelleLigne, nouvelleColonne));
    }
    public Direction getDirection(){
        return this.direction;
    }
    public void inverserY(){
        this.direction = new Direction(this.direction.getRowDir(),-1*(this.direction.getColDir()));
    }
    public void inverserX(){
        this.direction = new Direction(-1*(this.direction.getRowDir()),this.direction.getColDir());
    }
    public void fuir(){
        inverserX();
        inverserY();
    }
    public void setADejaBouge(boolean bool){
        this.aDejabouge=bool;
    }
    public boolean getADejaBouge(){
        return aDejabouge;
    }
    public void setDirection(Direction newDirection){
        this.direction = newDirection;
    }
}