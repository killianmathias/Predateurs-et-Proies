public class Renard extends Predateurs{
    private int sexe;
    public Renard(Position position, Direction direction, Grille grille){
        super(position,'R',direction,grille);
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
    public void seReproduire(){
        double nb = Math.random();
        if (nb>0.25){
            Grille grille = getGrille();
            Renard l  = new Renard(new Position(), new Direction().genererDirectionAleatoire(),grille);
            do{
                l.setPosition(new Position().genererPositionAleatoire(grille.getRows(), grille.getCols()));
            }while(!(grille.getCase(l.getPosition()) instanceof Herbe));
            Case tmp = getGrille().getCase(l.getPosition());
            grille.retirerCase(tmp.getPosition());
            l.setCaseActuelle(tmp);
            grille.ajouterCase(l);
            Ecran.afficher("\nDes renards se reproduisent: ",l.getPosition().afficherPosition());
            this.setADejaBouge(true);
        }else{
            setADejaBouge(true);
        }
    };
    public void agiSur(Case caseSuivante){
        if (caseSuivante != null && !this.getADejaBouge() && !getBlocked()){
            if (caseSuivante instanceof Renard && caseSuivante.getSexe()!=this.getSexe()){
                this.seReproduire();
            }else if (caseSuivante instanceof Chasseur){
                this.getDirection().genererDirectionAleatoire();
                setADejaBouge(true);
            }else if (caseSuivante instanceof Personnage){
                this.tuer(caseSuivante);
            }else{
                caseSuivante.redirige(this);
            }
        }else if (getBlocked()){
            setBlocked(false);
        }
    }
}
