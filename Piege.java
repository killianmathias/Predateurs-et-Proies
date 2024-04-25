public class Piege extends Case {
    private boolean ouvert;
    Piege(Position position){
        super(position, 'X');
        this.ouvert =  true;
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
