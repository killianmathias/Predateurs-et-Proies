public class Case{
    private Position position;
    private char symbole;
    private Grille grille;

    Case(Position position, char symbole){
        this.position = position;
        this.symbole = symbole;
    }
    Case(Position position, char symbole, Grille grille){
        this.position = position;
        this.symbole = symbole;
        this.grille= grille;
    }
    Case(Position position, Grille grille){
        this.position = position;
        this.grille= grille;
    }
    Case(Position position){
        this(position,'.');
    }
    Case(){
        this(new Position());
    }
    Case getCaseActuelle(){
        return null;
    }
    public Direction getDirection(){
        return new Direction();
    }
    public boolean getADejaBouge(){
        return true;
    }
    public void agiSur(Case caseSuivante){

    }
    public void setADejaBouge(boolean b){}
    void afficherCase(){
        if (this instanceof Chasseur){
            System.out.print("\u001B[34m"+ " " + this.symbole+ " ");
        }else if (this instanceof Renard){
            System.out.print("\u001B[38;5;208m"+ " " + this.symbole+ " ");
        }else if (this instanceof Poule){
            System.out.print("\u001B[35m"+ " " + this.symbole+ " ");
        }else if (this instanceof Lapin){
            System.out.print("\u001B[38;5;240m"+ " " + this.symbole+ " ");
        }else if (this instanceof Herbe){
            System.out.print("\u001B[32m"+ " " + this.symbole+ " ");
        }else{
            System.out.print("\u001B[0m"+" "+ this.symbole+ " ");
        }
    }
    public Position getPosition(){
        return position;
    }
    public void setPosition(Position newPosition){
        this.position=newPosition;
    }
    public void setSymbole(char symbole){
        this.symbole = symbole;
    }
    public void seDeplacer(Case caseSuivante){
        
    }
    public void redirige(Personnage perso){
        // perso.setPosition(this.getPosition());
    }
    public Grille getGrille(){
        return this.grille;
    }
    public int getSexe(){
        return -1;
    }
    public void pousseHerbe(){
    }
    public void setOuvert(){}
    public void symboleOuvert(){}
    public int getLongueur(){
        return -1;
    }
}