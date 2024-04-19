public class Direction {
    private int rowDir;
    private int colDir;

    public Direction(int rowDir, int colDir){
        this.colDir = colDir;
        this.rowDir = rowDir;
    }
    public Direction(){
        this(0,0);
    }
    public int getRowDir(){
        return rowDir;
    }
    public int getColDir(){
        return colDir;
    }
}