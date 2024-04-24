public class Piege extends Case {
    private boolean ouvert;
    Piege(Position position, boolean ouvert){
        super(position, 'X');
        this.ouvert = ouvert;
    }
    public boolean getOuvert(){
        return this.ouvert;
    }
    public void setOuvert(){
        if (this.ouvert){
            this.ouvert = false;
        }else{
            this.ouvert = true;
        }
    }
}
