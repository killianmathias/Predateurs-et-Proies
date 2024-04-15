public class Predateur extends Personnage{
    int type;
    void setCase(Position pos, Direction dir, int type) {
        super.setCase(pos, dir);
        this.type = 1;
    }
}
