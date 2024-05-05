public class Rocher extends Case{
    Rocher(Position position,Grille grille){
        super(position, 'O',grille);
    }
    public void redirige(Personnage perso) {
        perso.getDirection().genererDirectionAleatoire();
        perso.setADejaBouge(true);
    }
}
