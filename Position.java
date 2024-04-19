public class Position {
    int row;
    int col;
    
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
}
