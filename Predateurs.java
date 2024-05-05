public class Predateurs extends Personnage{
    public Predateurs(Position position,char symbole, Direction direction,Grille grille){
        super(position,symbole, direction,grille);
    }
    public void tuer(Case caseSuivante){
        if (caseSuivante!= null){
            Case tmp = caseSuivante.getCaseActuelle();
            getGrille().retirerCase(caseSuivante.getPosition());
            getGrille().ajouterCase(tmp);
            tmp.redirige(this);
        }
    }
}
