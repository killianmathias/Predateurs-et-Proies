public class Piege extends Case {
    private boolean ouvert;
    Piege(Position position,Grille grille){
        super(position,grille);
        this.ouvert =  true;
        symboleOuvert();
    }
    public boolean getOuvert(){
        return this.ouvert;
    }
    public void setOuvert(){
        if (this.ouvert){
            this.ouvert = false;
        }else{
            this.ouvert = true;
        }
    }
    public void symboleOuvert(){
        if (this.ouvert){
            this.setSymbole('X');
        }else{
            this.setSymbole('x');
        }
    }
    public void redirige(Personnage perso) {
        getGrille().retirerCase(perso.getPosition());
        getGrille().ajouterCase(perso.getCaseActuelle());
        perso.setPosition(this.getPosition());
        perso.setCaseActuelle(this);
        perso.setADejaBouge(true);
        getGrille().retirerCase(this.getPosition());
        getGrille().ajouterCase(perso);
        if (this.ouvert){
            perso.setBlocked(true);
            Ecran.afficher("\nPersonnage bloqué"+perso.getPosition().afficherPosition());
            this.setOuvert();
        }
    }
}
