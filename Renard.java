public class Renard extends Predateurs{
    private int sexe;
    public Renard(Position position, Direction direction){
        super(position,'R',direction);
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
