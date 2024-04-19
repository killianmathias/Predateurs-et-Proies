public class Grille {
    private int rows;
    private int cols;
    private Case[][] plateau;

    public Grille(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.plateau = new Case[rows][cols];
    }
    public Grille(){
        this(0,0);
    }
    public void ajouterCase(Case box){
        plateau[box.getPosition().getRow()][box.getPosition().getCol()] = box;
    }
    public Case getCase(Position position){
        return plateau[position.getRow()][position.getCol()];
    }
    public void retirerCase(Position pos){
        plateau[pos.getRow()][pos.getCol()] = null;
    }
}
