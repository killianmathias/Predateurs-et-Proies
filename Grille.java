public class Grille {
    private Case[][] plateau;

    Grille(int largeur, int hauteur){
        this.plateau = new Case[largeur][hauteur];
        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                this.plateau[i][j] = new Case();
            }
        }
    }
    Grille(){
        this(0,0);
    }
    
}
