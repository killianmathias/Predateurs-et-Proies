public class Bord extends Case{
    char vue = 'X';
    void setCase(Position pos) {
        super.setCase(pos,this.vue);
    }
    
}