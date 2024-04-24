public class Lapin extends Proies {
    private int sexe;
    public Lapin(Position position, Direction direction, int sexe){
        super(position,'L',direction);
        this.sexe = sexe;
    }
    public Lapin(Position position, Direction direction){
        super(position,'L',direction);
    }
    public int getSexe(){
        return this.sexe;
    }
    public void setSexe(int nb){
        /* sexe=0 : Femme / sexe=1 : Homme  */
        if (nb==0 || nb ==1){
            this.sexe =nb;
        }
    }
    public void sexeAleatoire(){
        double nb = Math.random();
        if (nb >0.5){
            this.sexe = 1;
        }else{
            this.sexe = 0;
        }
    }
}
