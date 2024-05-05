public class Huile extends Case{
    Huile(Position position,Grille grille){
        super(position,'~',grille);
    }
    public void redirige(Personnage perso) {
        Ecran.afficher("\nLe personnage glisse"+perso.getPosition().afficherPosition());
        getGrille().retirerCase(perso.getPosition());
        getGrille().ajouterCase(perso.getCaseActuelle());
        perso.setPosition(this.getPosition());
        perso.setCaseActuelle(this);
        getGrille().retirerCase(this.getPosition());
        getGrille().ajouterCase(perso);
        Case caseSuivante = getGrille().getCase(new Position(perso.getPosition().getRow()+ perso.getDirection().getRowDir(),perso.getPosition().getCol() + perso.getDirection().getColDir()));
        perso.agiSur(caseSuivante);
    }
}
