public class Main{
    static Position genererPositionAleatoire(int row, int col){
        int x = (int)(1+Math.random()*(col-2));
        Ecran.afficher("x: "+x+"\n");
        int y = (int)(1+Math.random()*(row-2));
        Ecran.afficher("y: "+y+'\n');
        Position pos = new Position(y, x);
        return pos;
    }
    static Direction genererDirectionAleatoire(){
        int rowDir = (int)(Math.random()*3)-1;
        Ecran.afficher("row : ", rowDir+ "\n");
        int colDir = (int)(Math.random()*3)-1;
        Ecran.afficher("col: ", colDir+"\n");
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
                    if (caseCourante!=null){
                        caseCourante.afficherCase();
                        if (caseCourante instanceof Personnage) {
                            Personnage perso = (Personnage) caseCourante;
                            perso.setADejaBouge(false);
                        }
                    }else{
                        Ecran.afficher(" . ");
                    }
                }
                Ecran.sautDeLigne();
            }
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    Case caseCourante = grille.getCase(new Position(i, j));
                    if (caseCourante != null) {
                        // caseCourante.afficherCase();
                        
                        if (caseCourante instanceof Personnage) {
                            Personnage perso = (Personnage) caseCourante;
                            Case caseSuivante = grille.getCase(new Position(perso.getPosition().getRow()+ perso.getDirection().getRowDir(),perso.getPosition().getCol() + perso.getDirection().getColDir()));
                            if (!perso.getADejaBouge()) {
                                Position positionY = new Position(perso.getPosition().getRow(),perso.getPosition().getCol() + perso.getDirection().getColDir());
                                Position positionX = new Position(perso.getPosition().getRow()+ perso.getDirection().getRowDir(),perso.getPosition().getCol());
                                if (grille.getCase(positionY) instanceof Bord) {
                                    perso.inverserY();
                                    perso.setADejaBouge(true);
                                }else if (grille.getCase(positionX) instanceof Bord){
                                    perso.inverserX();
                                    perso.setADejaBouge(true);
                                }else{
                                    
                                        if (perso instanceof Proies){
                                            if (caseSuivante instanceof Personnage){
                                                perso.fuir();
                                            }else{
                                                grille.retirerCase(perso.getPosition());
                                                perso.seDeplacer();
                                                grille.ajouterCase(perso);
                                                perso.setADejaBouge(true);
                                            };
                                        }else if (perso instanceof Predateurs){
                                            if (perso instanceof Renard){
                                                if (caseSuivante instanceof Renard || caseSuivante instanceof Chasseur){
                                                    perso.setDirection(genererDirectionAleatoire());
                                                    perso.setADejaBouge(true);
                                                }else if (caseSuivante instanceof Personnage){
                                                    grille.retirerCase(caseSuivante.getPosition());
                                                    grille.retirerCase(perso.getPosition());
                                                    perso.seDeplacer();
                                                    grille.ajouterCase(perso);
                                                    perso.setADejaBouge(true);
                                                }else{
                                                    grille.retirerCase(perso.getPosition());
                                                    perso.seDeplacer();
                                                    grille.ajouterCase(perso);
                                                    perso.setADejaBouge(true);
                                                }
                                            }else if (perso instanceof Chasseur){
                                                if(caseSuivante instanceof Chasseur || caseSuivante instanceof Renard){
                                                    perso.setDirection(genererDirectionAleatoire());
                                                    perso.setADejaBouge(true);
                                                    
                                                }else if (caseSuivante instanceof Personnage){
                                                    grille.retirerCase(caseSuivante.getPosition());
                                                    grille.retirerCase(perso.getPosition());
                                                    perso.seDeplacer();
                                                    grille.ajouterCase(perso);
                                                    perso.setADejaBouge(true);
                                                }else {
                                                    grille.retirerCase(perso.getPosition());
                                                    perso.seDeplacer();
                                                    grille.ajouterCase(perso);
                                                    perso.setADejaBouge(true);
                                                }
                                            }
                                        }
                                    }
                                }
                            
                        }
                    } else {
                        
                    }
                }
                // Ecran.sautDeLigne(); // Nouvelle ligne
            }
        
            // Réinitialisez l'indicateur aDejaBouge pour tous les personnages
            
            String c ="";
            while(!c.equals("o")){
                Ecran.afficher("Voulez-vous passer au tour suivant (o/n/q)\n");
                c = Clavier.saisirString();
            }
            tour_actuel++;
        }
    }        
    }
