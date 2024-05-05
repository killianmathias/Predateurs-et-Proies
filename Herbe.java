public class Herbe extends Case {
    private int longueur;

    Herbe(Position position){
        super(position);
        longueurHerbeAleatoire();
    }
    Herbe(Position position, Grille grille){
        super(position,grille);
        longueurHerbeAleatoire();
    }

    public void longueurHerbeAleatoire(){
        this.longueur = (int)(Math.random()*3);
        symboleLongueur();
    }
    public void symboleLongueur(){
        if (this.longueur==0){
            this.setSymbole(' ');
        }else if (this.longueur==1){
            this.setSymbole('.');
        }else {
            this.setSymbole('|');
        }
    }
    public void pousseHerbe(){
        if (this.longueur<2){
            this.longueur = this.longueur+1;
            symboleLongueur();
        }
    }
    public void diminueHerbe(){
        if (this.longueur>0){
            this.longueur =  this.longueur-1;
            symboleLongueur();
        }
    }
    public int getLongueur(){
        return this.longueur;
    }
    public void redirige(Personnage perso) {
        if (perso instanceof Lapin || perso instanceof Chasseur){
            this.diminueHerbe();
            Ecran.afficher("\nL'herbe diminue" + this.getPosition().afficherPosition());
        }
        getGrille().retirerCase(perso.getPosition());
        getGrille().ajouterCase(perso.getCaseActuelle());
        perso.setPosition(this.getPosition());
        perso.setCaseActuelle(this);
        perso.setADejaBouge(true);
        getGrille().retirerCase(this.getPosition());
        getGrille().ajouterCase(perso);
    }
}