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
    public Direction genererDirectionAleatoire(){
        int rowDir = (int)(Math.random()*3)-1;
        int colDir = (int)(Math.random()*3)-1;
        Direction dir = new Direction(rowDir, colDir);
        return dir;
    }
    public void inverserDirY(){
        this.colDir = (this.colDir)*(-1);
    }
    public void inverserDirX(){
        this.rowDir = (this.rowDir)*(-1);
    }
}