public class Bord extends Case{
    public Bord(Position position,Grille grille){
        super(position,'#',grille);
    }
    public void redirige(Personnage perso) {
        Ecran.afficher("\nLe personnage rencontre un bord "+perso.getPosition().afficherPosition()+this.getPosition().afficherPosition());
        Position positionY =new Position(perso.getPosition().getRow(),perso.getPosition().getCol()+perso.getDirection().getColDir());
        Position positionX =new Position(perso.getPosition().getRow()+perso.getDirection().getRowDir(),perso.getPosition().getCol());
        if (!perso.getADejaBouge()){
            if (this.getPosition().getRow()==positionY.getRow() && positionY.getRow()!=0) {
                Ecran.afficher("\nAncienne direction"+perso.getDirection().afficherDirection());
                perso.inverserY();
                Ecran.afficher("\nNouvelle direction"+perso.getDirection().afficherDirection());
                perso.setADejaBouge(true);
            }else if (this.getPosition().getRow()==positionX.getRow() && positionX.getCol()!=0){
                Ecran.afficher("\nAncienne direction"+perso.getDirection().afficherDirection());
                perso.inverserX();
                Ecran.afficher("\nNouvelle direction"+perso.getDirection().afficherDirection());
                perso.setADejaBouge(true);
            }
        }
    }
}