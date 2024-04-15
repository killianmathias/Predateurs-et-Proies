public class Direction {
    int rowDir;
    int colDir;

    Direction(int rowDir, int colDir){
        this.colDir = colDir;
        this.rowDir = rowDir;
    }
    Direction(){
        this(0,0);
    }
}