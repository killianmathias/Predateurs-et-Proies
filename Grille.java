public class Grille {
    private int rows;
    private int cols;
    private final int tour;
    private Case[][] plateau;

    public Grille(int rows, int cols, int tour){
        this.rows = rows;
        this.cols = cols;
        this.tour = tour;
        this.plateau = new Case[rows][cols];
    }
    public Grille(){
        this(0,0,0);
    }
    public void ajouterCase(Case box){
        int row = box.getPosition().getRow();
        int col = box.getPosition().getCol();
        // if (row >= 0 && row < this.rows && col >= 0 && col < this.cols){
            plateau[row][col] = box;
        // }
    }
    public Case getCase(Position position){
        // Ecran.afficher("\n\n("+this.getRows()+","+this.getCols()+")");
        // Ecran.afficher("\n("+position.getRow()+","+position.getCol()+")");
        if (position.getCol()>=0 && position.getRow()>=0){
            return plateau[position.getRow()][position.getCol()];
        }else{
            return null;
        }
    }
    public Grille getGrille(){
        return this;
    }
    public void retirerCase(Position pos) {
        if (pos.getCol() >= 0 && pos.getCol() < cols && pos.getRow() >= 0 && pos.getRow() < rows) {
            plateau[pos.getRow()][pos.getCol()] = null;
        } else {
        }
    }
    
    public void afficherGrille(int nb){
        Ecran.afficher("\nTour numéro ",nb , "\n\n");
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                Case caseCourante = this.getCase(new Position(i, j));
                if (caseCourante!=null){
                    caseCourante.afficherCase();
                }
            }
            Ecran.sautDeLigne();
        }
    }
    public void reinitialiserGrille(){
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                Case caseCourante = this.getCase(new Position(i, j));
                if (caseCourante!=null){
                    double nb = Math.random();
                    if (caseCourante instanceof Herbe){
                        if (nb<0.3){
                            caseCourante.pousseHerbe();
                        }
                    }else if (caseCourante instanceof Piege){
                        if (nb<0.3){
                            caseCourante.setOuvert();
                            caseCourante.symboleOuvert();
                        }
                    }else{
                        caseCourante.setADejaBouge(false);
                    }
                }
            }
        }
    }
    public void deplacerEntite(){
        for (int i = 0; i < this.getRows()-1; i++) {
            for (int j = 0; j < this.getCols()-1; j++) {
                Case caseCourante = this.getCase(new Position(i, j));
                if (caseCourante!=null && !caseCourante.getADejaBouge()){
                    Case caseSuivante = this.getCase(new Position(caseCourante.getPosition().getRow()+ caseCourante.getDirection().getRowDir(),caseCourante.getPosition().getCol() + caseCourante.getDirection().getColDir()));
                    caseCourante.agiSur(caseSuivante);
                }
            }
        }
    }
    public void Jouer(){
        initialiserGrille();
        int nb=1;
        afficherGrille(nb);
        String input="";
        while (nb <= this.tour){
            do{
                Ecran.afficher("\nVoulez-vous passer au tour suivant ? (o/n/q)\n");
                input=Clavier.saisirString();
            }while(!input.equals("o")&& !input.equals("q"));
            if (input.equals("o")){
                nb++;
            }else if(input.equals("q")){
                nb = this.tour+1;
            }
            deplacerEntite();
            reinitialiserGrille();
            afficherGrille(nb);
            ;
        }
        Ecran.afficher("\nLa partie est terminée");
    }
    public void initialiserGrille(){
        // Ajout des bords
        for (int i = 0; i < this.rows; i++) {
            this.ajouterCase(new Bord(new Position(i, 0),this));
            this.ajouterCase(new Bord(new Position(i, this.cols-1),this));
        }
        for (int i=0; i<this.cols;i++){
            this.ajouterCase(new Bord(new Position(0, i),this));
            this.ajouterCase(new Bord(new Position(this.rows-1, i),this));
        }
        for (int i = 1; i < this.rows-1; i++){
            for (int j=1; j < this.cols-1;j++){
                double nb = Math.random();
                if (nb > 0 && nb < 0.1 && i>2 && j>2 && j<this.cols-2 && i<this.rows-2){
                    this.ajouterCase(new Rocher(new Position(i,j),this));
                }else if ( nb > 0.1 && nb <0.2 && i>2 && j>2 && j<this.cols-2 && i<this.rows-2){
                    this.ajouterCase(new Piege(new Position(i,j),this));
                }else if( nb > 0.2 && nb <0.3 && i>2 && j>2 && j<this.cols-2 && i<this.rows-2){
                    this.ajouterCase(new Huile(new Position(i,j),this));
                }else{
                    this.ajouterCase(new Herbe(new Position(i,j),this));
            }
        }
    }
        int nb_entite = 1+ (int) (this.rows-2)*(this.cols-2)/20;
        for (int n=0; n < nb_entite; n++){
            Lapin l  = new Lapin(new Position(), new Direction().genererDirectionAleatoire(),this);
            do{
                l.setPosition(new Position().genererPositionAleatoire(rows, cols));
            }while((getCase(l.getPosition()) instanceof Personnage));
            Case tmp = getCase(l.getPosition());
            retirerCase(tmp.getPosition());
            l.setCaseActuelle(tmp);
            ajouterCase(l);
            Ecran.afficher("\nLapin: ",l.getPosition().afficherPosition()+" "+l.getDirection().afficherDirection());
        }
        for (int n=0; n< nb_entite; n++){
            Chasseur c  = new Chasseur(new Position(), new Direction().genererDirectionAleatoire(),this);
            do{
                c.setPosition(new Position().genererPositionAleatoire(rows, cols));
            }while((getCase(c.getPosition()) instanceof Personnage));
            Case tmp = getCase(c.getPosition());
            retirerCase(tmp.getPosition());
            c.setCaseActuelle(tmp);
            ajouterCase(c);
            Ecran.afficher("\nChasseur:",c.getPosition().afficherPosition()+" "+c.getDirection().afficherDirection());

        }
        for (int n=0; n< nb_entite; n++){
            Poule p  = new Poule(new Position(), new Direction().genererDirectionAleatoire(),this);
            do{
                p.setPosition(new Position().genererPositionAleatoire(rows, cols));
            }while((getCase(p.getPosition()) instanceof Personnage));
            Case tmp = getCase(p.getPosition());
            retirerCase(tmp.getPosition());
            p.setCaseActuelle(tmp);
            ajouterCase(p);
            Ecran.afficher("\nPoule:",p.getPosition().afficherPosition()+" "+p.getDirection().afficherDirection());

        }
        for (int n=0; n< nb_entite; n++){
            Renard r  = new Renard(new Position(), new Direction().genererDirectionAleatoire(),this);
            do{
                r.setPosition(new Position().genererPositionAleatoire(rows, cols));
            }while(!(getCase(r.getPosition()) instanceof Herbe));
            Case tmp = getCase(r.getPosition());
            retirerCase(tmp.getPosition());
            r.setCaseActuelle(tmp);
            ajouterCase(r);
            Ecran.afficher("\nRenard:",r.getPosition().afficherPosition()+" "+r.getDirection().afficherDirection());

        }
        
    }
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
    public int getTour(){
        return tour;
    }
    
}
