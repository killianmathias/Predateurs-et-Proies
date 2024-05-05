public class Chasseur extends Predateurs{
   public Chasseur(Position position, Direction direction,Grille grille){
      super(position,'C',direction,grille);
  }
  public void seDeplacer(Personnage perso){
      perso.redirige(this);
  }
  public void redirige(Personnage perso){
      
  };
  public void agiSur(Case caseSuivante){
    if (caseSuivante != null && !this.getADejaBouge() && !getBlocked()){
        double nb = Math.random();
        if (caseSuivante instanceof Poule || (caseSuivante instanceof Chasseur && nb>0.15)) {
            this.getDirection().genererDirectionAleatoire();
            this.setADejaBouge(true);
        }else if (caseSuivante instanceof Personnage){
            this.tuer(caseSuivante);
        }else {
            caseSuivante.redirige(this);
        }
    }else if (getBlocked()){
        setBlocked(false);
    }
  }
}
