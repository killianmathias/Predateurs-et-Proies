public class Position {
    private int row;
    private int col;
    
    Position(int row, int col){
        this.row = row;
        this.col = col;
    }
    Position(){
        this(0,0);
    }
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    public Position genererPositionAleatoire(int row, int col){
        int x = (int)(1+Math.random()*(col-2));
        Ecran.afficher("x: "+x+"\n");
        int y = (int)(1+Math.random()*(row-2));
        Ecran.afficher("y: "+y+'\n');
        Position pos = new Position(y, x);
        return pos;
    }
    
}
