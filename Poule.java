public class Poule extends Proies {
    public Poule(Position position, Direction direction, Grille grille){
        super(position,'P',direction,grille);
    }
    public void agiSur(Case caseSuivante){
        if (caseSuivante != null && !this.getADejaBouge()&& !getBlocked()){
            if (caseSuivante instanceof Personnage){
                this.fuir();
            }else{
                caseSuivante.redirige(this);
            }
        }else if (getBlocked()){
            setBlocked(false);
        }
    }

}
