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
        int nouvelleLigne = getPosition().getRow() + direction.getRowDir();
        int nouvelleColonne = getPosition().getCol() + direction.getColDir();
        // Ecran.afficher(nouvelleColonne);
        setPosition(new Position(nouvelleLigne, nouvelleColonne));
        setADejaBouge(true);
    }
    public void setADejaBouge(boolean bool){
        this.aDejabouge=bool;
    }
    public boolean getADejaBouge(){
        return aDejabouge;
    }
    
    

    

    
    
    
}