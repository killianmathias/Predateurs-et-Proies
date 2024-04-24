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
    public void initialiserGrille(){
        // Ajout des bords
        for (int i = 0; i < this.rows; i++) {
            this.ajouterCase(new Bord(new Position(i, 0)));
            this.ajouterCase(new Bord(new Position(i, this.cols-1)));
            this.ajouterCase(new Bord(new Position(0, i)));
            this.ajouterCase(new Bord(new Position(this.rows-1, i)));
        }
        for (int i = 1; i < this.rows-1; i++){
            for (int j=1; j < this.cols-1;j++){
                double nb = Math.random();
                if (nb > 0.15){
                    this.ajouterCase(new Herbe(new Position(i,j)));
                }else if (nb > 0.1 && nb < 0.15){
                    this.ajouterCase(new Rocher(new Position(i,j)));
                }else if ( nb > 0.05 && nb <0.1){
                    this.ajouterCase(new Piege(new Position(i,j), false));
                }else{
                    this.ajouterCase(new Huile(new Position(i,j)));
                }
            }
        }

        
    }
}
