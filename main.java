public class Main{
    /* Action qui va afficher le plateau à chaque tour et remet à zéro la variable setADejaBouge de chaque personnage */
    /* Action qui déplace une entité sur le plateau */
    // 
    
        public static void main(String[] args) {
        Ecran.afficher("Veuillez saisir un nombre de lignes :");
        final int ROWS = Clavier.saisirInt() + 2;
        Ecran.afficher("\nVeuillez saisir un nombre de colonnes :");
        final int COLS = Clavier.saisirInt() + 2;
        Ecran.afficher("\nVeuillez saisir un nombre de tours:");
        final int TOURS_MAX = Clavier.saisirInt();
        Grille grille = new Grille(ROWS, COLS,TOURS_MAX);
        grille.Jouer();
        
        
        }      
    }
