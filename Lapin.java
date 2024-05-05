public class Lapin extends Proies {
    private int sexe;
    public Lapin(Position position, Direction direction,Grille grille, int sexe){
        super(position,'L',direction,grille);
        this.sexe = sexe;
    }
    public Lapin(Position position, Direction direction,Grille grille){
        super(position,'L',direction,grille);
        sexeAleatoire();
    }
    public int getSexe(){
        return this.sexe;
    }
    public void setSexe(int nb){
        /* sexe=0 : Femme / sexe=1 : Homme  */
        if (nb==0 || nb ==1){
            this.sexe =nb;
        }
    }
    public void sexeAleatoire(){
        double nb = Math.random();
        if (nb >0.5){
            this.sexe = 1;
        }else{
            this.sexe = 0;
        }
    }
    public void seReproduire() {
        Grille grille = getGrille();
        Lapin l  = new Lapin(new Position(), new Direction().genererDirectionAleatoire(),grille);
        do{
            l.setPosition(new Position().genererPositionAleatoire(grille.getRows(), grille.getCols()));
        }while(!(grille.getCase(l.getPosition()) instanceof Herbe));
        Case tmp = getGrille().getCase(l.getPosition());
        grille.retirerCase(tmp.getPosition());
        l.setCaseActuelle(tmp);
        grille.ajouterCase(l);
        Ecran.afficher("\nDes lapins se reproduisent: ",l.getPosition().afficherPosition());
        this.setADejaBouge(true);
    }
    
    
    
    public void agiSur(Case caseSuivante){
        if (caseSuivante != null && !this.getADejaBouge() && !getBlocked()){
            if (caseSuivante instanceof Lapin && caseSuivante.getSexe()!=this.getSexe()){
                this.seReproduire();
                
            }else if (caseSuivante instanceof Personnage){
                this.fuir();
            }else if (caseSuivante instanceof Herbe && caseSuivante.getLongueur()>0){
                caseSuivante.redirige(this);
            }else {
                int compteur = 0;
                while(caseSuivante.getLongueur()<=0 && compteur <8){
                    this.getDirection().genererDirectionAleatoire();
                    caseSuivante = getGrille().getCase(new Position(this.getPosition().getRow()+ this.getDirection().getRowDir(),this.getPosition().getCol() + this.getDirection().getColDir()));
                    compteur++;
                }
                this.setADejaBouge(true);
            }
        }else if (getBlocked()){
            setBlocked(false);
        }
    }
}
