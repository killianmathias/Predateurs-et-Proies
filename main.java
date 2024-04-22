public class Main{
    public static Personnage creerPersonnage(String type, Position position, Direction direction, Grille grille) {
        switch (type) {
            case "Poule":
                Poule poule = new Poule(position, direction);
                Case caseActuellePoule = grille.getCase(position);
                if (caseActuellePoule instanceof Herbe) {
                    poule.setCaseActuelle((Herbe) caseActuellePoule);
                }else {
                    poule.setCaseActuelle(((Personnage)caseActuellePoule).getCaseActuelle());
                }
                return poule;
            case "Renard":
                Renard renard = new Renard(position, direction);
                Case caseActuelleRenard = grille.getCase(position);
                if (caseActuelleRenard instanceof Herbe) {
                    renard.setCaseActuelle((Herbe) caseActuelleRenard);
                }else {
                    renard.setCaseActuelle(((Personnage)caseActuelleRenard).getCaseActuelle());
                }
                return renard;
            case "Lapin":
                Lapin lapin = new Lapin(position, direction);
                Case caseActuelleLapin = grille.getCase(position);
                if (caseActuelleLapin instanceof Herbe) {
                    lapin.setCaseActuelle((Herbe) caseActuelleLapin);
                }else {
                    lapin.setCaseActuelle(((Personnage)caseActuelleLapin).getCaseActuelle());
                }
                return lapin;
            case "Chasseur":
                Chasseur chasseur = new Chasseur(position, direction);
                Case caseActuelleChasseur = grille.getCase(position);
                if (caseActuelleChasseur instanceof Herbe) {
                    chasseur.setCaseActuelle((Herbe) caseActuelleChasseur);
                }else {
                    chasseur.setCaseActuelle(((Personnage)caseActuelleChasseur).getCaseActuelle());
                }
                return chasseur;
            default:
                throw new IllegalArgumentException("Type de personnage non pris en charge : " + type);
        }
    }
        public static void main(String[] args) {
        final int ROWS = 12;
        final int COLS = 12;
        final int TOURS_MAX = 15;
        final String[] types = { "Poule", "Renard", "Lapin", "Chasseur" };
        int tour_actuel=1;
        Grille grille = new Grille(ROWS, COLS);
        grille.initialiserGrille();
        // Boucle pour créer et ajouter chaque personnage à la grille
        for (int j = 0; j<2; j++){
            for (int i = 0; i < types.length; i++) {
                Position position = new Position().genererPositionAleatoire(ROWS, COLS);
                Direction direction = new Direction().genererDirectionAleatoire();
                
                // Création du personnage en fonction du type
                Personnage personnage = creerPersonnage(types[i], position, direction, grille);

                // Ajout du personnage à la grille
                switch (types[i]) {
                    case "Poule":
                        grille.ajouterCase((Poule)personnage);
                        break;
                    case "Lapin":
                        grille.ajouterCase((Lapin)personnage);
                        break;
                    case "Renard":
                        grille.ajouterCase((Renard)personnage);
                        break;
                    case "Chasseur":
                        grille.ajouterCase((Chasseur)personnage);
                        break;
                    default:
                        break;
                }
                
            }
        }
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
                        }else if (caseCourante instanceof Herbe){
                            Herbe herbeTemp = (Herbe) caseCourante;
                            double nb = Math.random();
                            if (nb>0.85){
                                herbeTemp.pousseHerbe();
                            }
                        }
                    }else{
                        
                    }
                }
                Ecran.sautDeLigne();
            }
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    Case caseCourante = grille.getCase(new Position(i, j));
                    if (caseCourante != null) {
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
                                        }else if (caseSuivante instanceof Herbe){
                                            Herbe herbeTemp = (Herbe) caseSuivante;
                                            if (perso instanceof Lapin && herbeTemp.getLongueur()>0){
                                                herbeTemp.diminueHerbe();
                                                grille.retirerCase(perso.getPosition());
                                                grille.ajouterCase(perso.getCaseActuelle());
                                                perso.seDeplacer();
                                                perso.setCaseActuelle(herbeTemp);
                                                grille.ajouterCase(perso);
                                                perso.setADejaBouge(true);
                                            }else if (perso instanceof Poule){
                                                grille.retirerCase(perso.getPosition());
                                                grille.ajouterCase(perso.getCaseActuelle());
                                                perso.seDeplacer();
                                                perso.setCaseActuelle(herbeTemp);
                                                grille.ajouterCase(perso);
                                                perso.setADejaBouge(true);
                                            }else{
                                                double nb = Math.random();
                                                if (nb > 0.5){
                                                    perso.getDirection().inverserDirX();
                                                }else{
                                                    perso.getDirection().inverserDirY();
                                                }
                                                perso.setADejaBouge(true);
                                            }
                                            
                                        };
                                    }else if (perso instanceof Predateurs){
                                        if (perso instanceof Renard){
                                            if (caseSuivante instanceof Renard || caseSuivante instanceof Chasseur){
                                                perso.setDirection(perso.getDirection().genererDirectionAleatoire());
                                                perso.setADejaBouge(true);
                                            }else if (caseSuivante instanceof Personnage){
                                                Personnage persoSuivant = (Personnage)caseSuivante;
                                                grille.retirerCase(caseSuivante.getPosition());
                                                grille.retirerCase(perso.getPosition());
                                                grille.ajouterCase(perso.getCaseActuelle());
                                                perso.setCaseActuelle(persoSuivant.getCaseActuelle());
                                                perso.seDeplacer();
                                                grille.ajouterCase(perso);
                                                perso.setADejaBouge(true);
                                            }else if (caseSuivante instanceof Herbe){
                                                Herbe herbeTemp = (Herbe) caseSuivante;
                                                grille.retirerCase(perso.getPosition());
                                                grille.ajouterCase(perso.getCaseActuelle());
                                                perso.seDeplacer();
                                                perso.setCaseActuelle(herbeTemp);
                                                grille.ajouterCase(perso);
                                                perso.setADejaBouge(true);
                                            }
                                        }else if (perso instanceof Chasseur){
                                            double nb = Math.random();
                                            /* Faible probabilité pour un chasseur de se tuer lui même */
                                            if((caseSuivante instanceof Chasseur && nb >0.10) || caseSuivante instanceof Poule){
                                                perso.setDirection(perso.getDirection().genererDirectionAleatoire());
                                                perso.setADejaBouge(true);
                                            }else if (caseSuivante instanceof Personnage){
                                                Personnage persoSuivant = (Personnage)caseSuivante;
                                                grille.retirerCase(caseSuivante.getPosition());
                                                grille.retirerCase(perso.getPosition());
                                                grille.ajouterCase(perso.getCaseActuelle());
                                                perso.setCaseActuelle(persoSuivant.getCaseActuelle());
                                                perso.seDeplacer();
                                                grille.ajouterCase(perso);
                                                perso.setADejaBouge(true);
                                            }else if (caseSuivante instanceof Herbe){
                                                Herbe herbeTemp = (Herbe) caseSuivante;
                                                herbeTemp.diminueHerbe();
                                                grille.retirerCase(perso.getPosition());
                                                grille.ajouterCase(perso.getCaseActuelle());
                                                perso.seDeplacer();
                                                perso.setCaseActuelle(herbeTemp);
                                                grille.ajouterCase(perso);
                                                perso.setADejaBouge(true);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }  
            // Réinitialisez l'indicateur aDejaBouge pour tous les personnages
            String c ="";
            while(!(c.equals("o")||c.equals("q"))){
                Ecran.afficher("Voulez-vous passer au tour suivant (Oui (o) / Non (n) / Quitter(q))\n");
                c = Clavier.saisirString();
            }
            if (c.equals("q")){
                tour_actuel = TOURS_MAX+1;
            }else{
                tour_actuel++;
            }
        }
        Ecran.afficher("\nLa partie est terminée");
    }        
    }
