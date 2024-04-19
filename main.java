public class Main{
    static Position genererPositionAleatoire(int row, int col){
        int x = (int)(1+Math.random()*(col-2));
        Ecran.afficher("x: "+x+"\n");
        int y = (int)(1+Math.random()*(row-2));
        Ecran.afficher("y: "+y+'\n');
        Position pos = new Position(x, y);
        return pos;
    }
    static Direction genererDirectionAleatoire(){
        int rowDir = (int)(Math.random()*3)-1;
        int colDir = (int)(Math.random()*3)-1;
        Direction dir = new Direction(rowDir, colDir);
        return dir;
    }
        public static void main(String[] args) {
        final int ROWS = 10;
        final int COLS = 10;
        final int TOURS_MAX = 15;
        int tour_actuel=1;
        Grille grille = new Grille(COLS, ROWS);

        // Ajout des bords
        for (int i = 0; i < 10; i++) {
            grille.ajouterCase(new Bord(new Position(i, 0)));
            grille.ajouterCase(new Bord(new Position(i, 9)));
            grille.ajouterCase(new Bord(new Position(0, i)));
            grille.ajouterCase(new Bord(new Position(9, i)));
        }

        // Ajout des autres éléments
        grille.ajouterCase(new Poule(genererPositionAleatoire(ROWS,COLS), genererDirectionAleatoire()));
        grille.ajouterCase(new Renard(genererPositionAleatoire(ROWS,COLS), genererDirectionAleatoire()));
        grille.ajouterCase(new Lapin(genererPositionAleatoire(ROWS,COLS), genererDirectionAleatoire()));
        grille.ajouterCase(new Chasseur(genererPositionAleatoire(ROWS,COLS), genererDirectionAleatoire()));

        while (tour_actuel <= TOURS_MAX) {
            Ecran.afficher("\nTour numéro ", tour_actuel, "\n\n");
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    Case caseCourante = grille.getCase(new Position(i, j));
                    if (caseCourante != null) {
                        if (caseCourante instanceof Personnage) {
                            Position tmp = caseCourante.getPosition();
                            Personnage perso = (Personnage) caseCourante;
                            if (!perso.getADejaBouge()) {
                                if (perso.getPosition().getRow() > 1 && perso.getPosition().getRow() < ROWS - 2 && perso.getPosition().getCol() > 1 && perso.getPosition().getCol() < COLS - 1) {
                                    perso.seDeplacer();
                                    perso.setADejaBouge(true);
                                    grille.retirerCase(tmp);
                                    grille.ajouterCase(perso);
                                }
                            }
                        }
                        caseCourante.afficherCase();
                    } else {
                        Ecran.afficher(" . ");
                    }
                }
                Ecran.sautDeLigne(); // Nouvelle ligne
            }
        
            // Réinitialisez l'indicateur aDejaBouge pour tous les personnages
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    Case caseCourante = grille.getCase(new Position(i, j));
                    if (caseCourante instanceof Personnage) {
                        Personnage perso = (Personnage) caseCourante;
                        perso.setADejaBouge(false);
                    }
                }
            }
        
            tour_actuel++;
        }
    }        
    }
