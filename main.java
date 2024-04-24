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
                renard.sexeAleatoire();
                Case caseActuelleRenard = grille.getCase(position);
                if (caseActuelleRenard instanceof Herbe) {
                    renard.setCaseActuelle((Herbe) caseActuelleRenard);
                }else {
                    renard.setCaseActuelle(((Personnage)caseActuelleRenard).getCaseActuelle());
                }
                return renard;
            case "Lapin":
                Lapin lapin = new Lapin(position, direction);
                lapin.sexeAleatoire();
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
    public static void afficherPlateau(int tour_actuel, int ROWS, int COLS,Grille grille){
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
    }
    public static void seDeplace(Personnage perso, Grille grille, Case caseActuelle){
        grille.retirerCase(perso.getPosition());
        grille.ajouterCase(perso.getCaseActuelle());
        perso.seDeplacer();
        perso.setCaseActuelle(caseActuelle);
        grille.ajouterCase(perso);
        perso.setADejaBouge(true);
    }
    
        public static void main(String[] args) {
        Ecran.afficher("Veuillez saisir un nombre de lignes :");
        final int ROWS = Clavier.saisirInt() + 2;
        Ecran.afficher("\nVeuillez saisir un nombre de colonnes :");
        final int COLS = Clavier.saisirInt() + 2;
        int nb_entite = 1+ (int) (ROWS-2)*(COLS-2)/10;
        final int TOURS_MAX = 15;
        final String[] types = { "Poule", "Renard", "Lapin", "Chasseur" };
        int tour_actuel=1;
        Grille grille = new Grille(ROWS, COLS);
        grille.initialiserGrille();
        
        // Boucle pour créer et ajouter chaque personnage à la grille
        for (int j = 0; j<2; j++){
            for (int i = 0; i < types.length; i++) {
                Position position = new Position();
                do{
                    position = new Position().genererPositionAleatoire(ROWS, COLS);
                }while(!(grille.getCase(position) instanceof Herbe));
                Direction direction = new Direction().genererDirectionAleatoire();
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

        /* Boucle de jeu principale */
        while (tour_actuel <= TOURS_MAX) {
            afficherPlateau(tour_actuel, ROWS, COLS, grille);
            String str = "";
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    Case caseCourante = grille.getCase(new Position(i, j));
                    if (caseCourante != null) {
                        if (caseCourante instanceof Personnage) {
                            Personnage perso = (Personnage) caseCourante;
                            Case caseSuivante = grille.getCase(new Position(perso.getPosition().getRow()+ perso.getDirection().getRowDir(),perso.getPosition().getCol() + perso.getDirection().getColDir()));
                            // if (caseSuivante instanceof Huile){
                            //     caseSuivante = grille.getCase(new Position(perso.getPosition().getRow()+ 2*(perso.getDirection().getRowDir()),perso.getPosition().getCol() + 2*(perso.getDirection().getColDir())));
                            //     str += "\nLe personnage"+perso.getPosition().afficherPosition()+" glisse vers "+ caseSuivante.getPosition().afficherPosition();
                            //     perso.setBoost(true);
                            // }
                            
                            
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
                                            if (perso instanceof Lapin && caseSuivante instanceof Lapin){
                                                if (((Lapin)perso).getSexe() != ((Lapin)caseSuivante).getSexe()){
                                                    Position position = new Position();
                                                    do{
                                                        position= new Position().genererPositionAleatoire(ROWS, COLS);
                                                    }while(!(grille.getCase(position) instanceof Herbe));
                                                    Direction direction = new Direction().genererDirectionAleatoire();
                                                    Personnage personnage = creerPersonnage("Lapin", position, direction, grille);
                                                    grille.ajouterCase((Lapin)personnage);
                                                    perso.setADejaBouge(true);
                                                    str += "Des lapins se reproduisent\nPosition du bébé : ("+position.getCol()+","+position.getRow()+")\nPosition du parent1 : ("+perso.getPosition().getCol()+","+perso.getPosition().getRow()+")\nPosition du parent2 : ("+caseSuivante.getPosition().getCol()+","+caseSuivante.getPosition().getRow()+")\n";
                                                }
                                            }else{
                                                perso.fuir();
                                            }
                                        }else if (caseSuivante instanceof Herbe){
                                            Herbe herbeTemp = (Herbe) caseSuivante;
                                            if (perso instanceof Lapin && herbeTemp.getLongueur()>0){
                                                herbeTemp.diminueHerbe();
                                                seDeplace(perso, grille, herbeTemp);
                                            }else if (perso instanceof Poule){
                                                seDeplace(perso, grille, herbeTemp);
                                            }else{

                                                int compteur = 0;
                                                while(herbeTemp.getLongueur()<=0 && compteur <8){
                                                    perso.getDirection().genererDirectionAleatoire();
                                                    herbeTemp = (Herbe) grille.getCase(new Position(perso.getPosition().getRow()+ perso.getDirection().getRowDir(),perso.getPosition().getCol() + perso.getDirection().getColDir()));
                                                    compteur++;
                                                }
                                                perso.setADejaBouge(true);
                                            }
                                            
                                        };
                                    }else if (perso instanceof Predateurs){
                                        if (perso instanceof Renard){
                                            if (caseSuivante instanceof Renard){
                                                if (((Renard)perso).getSexe() != ((Renard)caseSuivante).getSexe()){
                                                    double proba = Math.random();
                                                    if (proba >0.25){
                                                        Position position = new Position();
                                                        do{
                                                            position= new Position().genererPositionAleatoire(ROWS, COLS);
                                                        }while(!(grille.getCase(position) instanceof Herbe));
                                                        Direction direction = new Direction().genererDirectionAleatoire();
                                                        Personnage personnage = creerPersonnage("Renard", position, direction, grille);
                                                        grille.ajouterCase((Renard)personnage);
                                                        perso.setADejaBouge(true);
                                                        str += "Des renards se reproduisent\nPosition du bébé : ("+position.getCol()+","+position.getRow()+")\nPosition du parent1 : ("+perso.getPosition().getCol()+","+perso.getPosition().getRow()+")\nPosition du parent2 : ("+caseSuivante.getPosition().getCol()+","+caseSuivante.getPosition().getRow()+")\n";
                                                    }
                                                }
                                            }else if(caseSuivante instanceof Chasseur){
                                                perso.setDirection(perso.getDirection().genererDirectionAleatoire());
                                                perso.setADejaBouge(true);
                                            }else if (caseSuivante instanceof Personnage){
                                                Personnage persoSuivant = (Personnage)caseSuivante;
                                                grille.retirerCase(caseSuivante.getPosition());
                                                seDeplace(perso, grille, persoSuivant);
                                            }else if (caseSuivante instanceof Herbe){
                                                Herbe herbeTemp = (Herbe) caseSuivante;
                                                seDeplace(perso, grille, herbeTemp);
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
                                                seDeplace(perso, grille, persoSuivant);
                                            }else if (caseSuivante instanceof Herbe){
                                                Herbe herbeTemp = (Herbe) caseSuivante;
                                                herbeTemp.diminueHerbe();
                                                seDeplace(perso, grille, herbeTemp);
                                            }
                                        }
                                    }else if (caseSuivante instanceof Rocher){
                                        perso.fuir();
                                        perso.setADejaBouge(true);
                                    }else if (caseSuivante instanceof Piege){
                                        Piege piegeTemp = (Piege) caseSuivante;
                                        piegeTemp.setOuvert();
                                        str += "\nLe personnage "+ perso.getPosition().afficherPosition()+ "est piégé";
                                        seDeplace(perso, grille, piegeTemp);
        
                                    }else if (perso.getCaseActuelle() instanceof Piege){
                                        Piege piege = ((Piege)perso.getCaseActuelle());
                                        if (piege.getOuvert()==true){
                                            piege.setOuvert();
                                            perso.setCaseActuelle(piege);
                                        }else{
                                            perso.seDeplacer();
                                        }
                                        
                                        perso.setADejaBouge(true);
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
            Ecran.afficher(str);
        }
        Ecran.afficher("\nLa partie est terminée");
    }        
    }
