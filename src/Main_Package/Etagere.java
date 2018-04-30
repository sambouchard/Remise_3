/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author SABOU350
 */
public class Etagere implements java.io.Serializable{

    /**
     * @return the epaisseurTriple
     */
    public double getEpaisseurTriple() {
        return epaisseurTriple;
    }

    /**
     * @param epaisseurTriple the epaisseurTriple to set
     */
    public void setEpaisseurTriple(double epaisseurTriple) {
        this.epaisseurTriple = epaisseurTriple;
    }

    /**
     * @return the epaisseurDouble
     */
    public double getEpaisseurDouble() {
        return epaisseurDouble;
    }

    /**
     * @param epaisseurDouble the epaisseurDouble to set
     */
    public void setEpaisseurDouble(double epaisseurDouble) {
        this.epaisseurDouble = epaisseurDouble;
    }

    /**
     * @return the Liste_Piece_Etage_Horizontale
     */
    public List<Piece> getListe_Piece_Etage_Horizontale() {
        return Liste_Piece_Etage_Horizontale;
    }

    /**
     * @param Liste_Piece_Etage_Horizontale the Liste_Piece_Etage_Horizontale to
     * set
     */
    public void setListe_Piece_Etage_Horizontale(List<Piece> Liste_Piece_Etage_Horizontale) {
        this.Liste_Piece_Etage_Horizontale = Liste_Piece_Etage_Horizontale;
    }

    /**
     * @return the Liste_piece
     */
    public List<Piece> getListe_piece() {
        return Liste_piece;
    }

    /**
     * @param Liste_piece the Liste_piece to set
     */
    public void setListe_piece(List<Piece> Liste_piece) {
        this.Liste_piece = Liste_piece;
    }
    private double hauteur;
    private double largeur;
    private double profondeur;
    private int nb_etages;
    private Etage[] listeetages;
    private boolean estferme;
    private boolean piecedepasse;
    private List<Piece> Liste_piece;
    private List<Piece> Liste_Piece_Etage_Horizontale;
    private boolean perimetretriple;
    private double epaisseurTriple = 0.5 * 2.54;
    private double epaisseurDouble = 0.75 * 2.54;

    /**
     * @param hauteur
     * @param largeur
     * @param profondeur
     * @param nb_etages
     * @param estfermee
     * @create etagere object
     */
    public Etagere(double hauteur, double largeur, double profondeur, int nb_etages,
            boolean estfermee, boolean piecedepasse, boolean perimetretriple) {
        setHauteur(hauteur);
        setLargeur(largeur);
        setProfondeur(profondeur);
        setNb_etages(nb_etages);
        setPiecedepasse(piecedepasse);
        setPerimetretriple(perimetretriple);
        this.estferme = estfermee;
        this.listeetages = new Etage[this.nb_etages];

        for (int i = 0; i < getNb_etages(); i++) {
            Etage unetage = new Etage(1.0 / this.getNb_etages());
            this.listeetages[i] = unetage;
        }
        this.Liste_piece = new ArrayList();
        this.Liste_Piece_Etage_Horizontale = new ArrayList();
        GenererPieces();

    }
    
    
    public String toStringMetrique() {
        String out = new String();
        for (Piece piece : this.Liste_piece) {
            out += piece.toStringMetrique() + "\n";
        }
        return out;
    }
    
    public String toStringImperial() {
        String out = new String();
        for (Piece piece : this.Liste_piece) {
            out += piece.toStringImperial()+ "\n";
        }
        return out;
    }
    
    /**
     * @return the hauteur
     */
    public double getHauteur() {
        return hauteur;
    }

    public void GenererPieces() {
        Liste_piece.clear();
        Liste_Piece_Etage_Horizontale.clear();
        if (isPerimetretriple()) {
            GenererPiecePerimetreBasTriple();
            GenererPiecesPerimetreHautTriple();
            GenererPiecesPerimetreCote();
        } else if (!(isPerimetretriple())) {
            GenererPiecesPerimetreBasDouble();
            GenererPiecsePerimetreHautDouble();
            GenererPiecesPerimetreCote();
        }
        GenererPiecesEtagesHorizontale();
//        GenerePiecesCaissonsHorizontale();
        if (estferme){
            retraitPourPanneauArriere();
        }
    }

    private void GenererPiecesPerimetreBasDouble() {
        Piece piecebas = new Piece(getEpaisseurDouble(), this.largeur - 2 * getEpaisseurDouble(), this.profondeur, "Piece du bas");
        piecebas.setDrawingcoin(new Coord_Coins(getEpaisseurDouble(), this.hauteur - getEpaisseurDouble(), 0));
        this.getListe_piece().add(piecebas);
    }

    private void GenererPiecePerimetreBasTriple() {
        Piece piecebas = new Piece(getEpaisseurTriple(), this.largeur - 2 * (getEpaisseurTriple()), this.profondeur, "Piece du bas 1");
        piecebas.setDrawingcoin(new Coord_Coins(getEpaisseurTriple(), this.hauteur - getEpaisseurTriple(), 0));
        this.getListe_piece().add(piecebas);

    }

    private void GenererPiecsePerimetreHautDouble() {
        if (isPiecedepasse()) {
            Piece piecehaut = new Piece(getEpaisseurDouble(), this.largeur, this.profondeur, "Piece du haut");
            
            piecehaut.setDrawingcoin(new Coord_Coins(0, 0, 0));

            this.getListe_piece().add(piecehaut);
        } else if (!(isPiecedepasse())) {
            Piece piecehaut = new Piece(getEpaisseurDouble(), this.largeur - 2 * getEpaisseurDouble(), this.profondeur, "Piece du haut");
            
            piecehaut.setDrawingcoin(new Coord_Coins(getEpaisseurDouble(), 0, 0));
            this.getListe_piece().add(piecehaut);
        }
    }

    private void GenererPiecesPerimetreHautTriple() {
        if (isPiecedepasse()) {
            Piece piecehaut = new Piece(getEpaisseurTriple(), this.largeur, this.profondeur, "Piece du haut1");
            piecehaut.setDrawingcoin(new Coord_Coins(0, 0, 0));
            Piece piecehaut1 = new Piece(getEpaisseurTriple(), this.largeur - 2 * (0.5 * 2.54), this.profondeur, "Piece du haut2");
            piecehaut1.setDrawingcoin(new Coord_Coins(getEpaisseurTriple(), getEpaisseurTriple(), 0));
            this.getListe_piece().add(piecehaut);
            this.getListe_piece().add(piecehaut1);

        } else if (!(isPiecedepasse())) {
            Piece piecehaut = new Piece(getEpaisseurTriple(), this.largeur - 2 * (getEpaisseurTriple()), this.profondeur, "Piece du haut 1");
            piecehaut.setDrawingcoin(new Coord_Coins(getEpaisseurTriple(), 0, 0));
            Piece piecehaut1 = new Piece(getEpaisseurTriple(), this.largeur - 2 * (getEpaisseurTriple()), this.profondeur, "Piece du haut 2");
            piecehaut1.setDrawingcoin(new Coord_Coins(getEpaisseurTriple(), getEpaisseurTriple(), 0));
            this.getListe_piece().add(piecehaut);
            this.getListe_piece().add(piecehaut1);
        }
    }

    private void GenererPiecesPerimetreCote() {
        double epaisseur = 0;
        if (isPerimetretriple()) {
            epaisseur = getEpaisseurTriple();
        } else if (!(isPerimetretriple())) {
            epaisseur = getEpaisseurDouble();
        }
        if (isPiecedepasse()) {
            Piece piecegauche = new Piece(this.hauteur - epaisseur, epaisseur, this.profondeur, "Piece de gauche");
            piecegauche.setDrawingcoin(new Coord_Coins(0, epaisseur, 0));
            Piece piecedroite = new Piece(this.hauteur - epaisseur, epaisseur, this.profondeur, "Piece de droite");
            this.Liste_piece.add(piecegauche);
            this.Liste_piece.add(piecedroite);
        } else if (!(isPiecedepasse())) {
            Piece piecegauche = new Piece(this.hauteur, epaisseur, this.profondeur, "Piece de gauche");
            piecegauche.setDrawingcoin(new Coord_Coins(0, 0, 0));
            Piece piecedroite = new Piece(this.hauteur, epaisseur, this.profondeur, "Piece de droite");
            piecedroite.setDrawingcoin(new Coord_Coins(this.largeur - epaisseur, 0, 0));
            this.getListe_piece().add(piecedroite);
            this.getListe_piece().add(piecegauche);
        }
    }

    /**
     * @param hauteur the hauteur to set
     */
    public void setHauteur(double hauteur) {
        this.hauteur = hauteur;
    }

    /**
     * @return the largeur
     */
    public double getLargeur() {
        return largeur;
    }

    /**
     * @param largeur the largeur to set
     */
    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }

    /**
     * @return the profondeur
     */
    public double getProfondeur() {
        return profondeur;
    }

    /**
     * @param profondeur the profondeur to set
     */
    public void setProfondeur(double profondeur) {
        this.profondeur = profondeur;
    }

    /**
     * @return the nb_etages
     */
    public int getNb_etages() {
        return nb_etages;
    }

    /**
     * @param nb_etages the nb_etages to set
     */
    public void setNb_etages(int nb_etages) {
        this.nb_etages = nb_etages;
    }

    /**
     *
     * @param h_relative
     */
    public void ajouteetage(double h_relative) {
        Etage newetage = new Etage(h_relative);
        for (int i = 0; i < getNb_etages(); i++) {
            getListeetages()[i].setHauteur_rel(getListeetages()[i].getHauteur_rel() - (h_relative / getNb_etages()));
        }
        setNb_etages(getNb_etages() + 1);
        Etage[] newlist = new Etage[getNb_etages()];
        for (int i = 0; i < getNb_etages(); i++) {
            if (i == getNb_etages() - 1) {
                newlist[i] = newetage;
            } else {
                newlist[i] = this.getListeetages()[i];
            }
        }
        this.setListeetages(newlist);

    }

    /**
     *
     * @param etageid
     */
    public void enleveetage(int etageid) {
        double h_relajouter = getListeetages()[etageid].getHauteur_rel();
        getListeetages()[etageid] = null;
        for (int i = 0; i < getNb_etages(); i++) {
            if (getListeetages()[i] != null) {
                getListeetages()[i].setHauteur_rel(getHauteur() + (h_relajouter / (getNb_etages() - 1)));
            }
        }
        Etage[] newlist = new Etage[getNb_etages() - 1];
        int j = 0;
        for (int i = 0; i < getNb_etages(); i++) {
            if (getListeetages()[i] == null) {

            } else {
                newlist[j] = this.getListeetages()[i];
                j++;
            }
        }
        this.setListeetages(newlist);
        setNb_etages(getNb_etages() - 1);

    }

    public void ModifieHauteurRelEtage(int indice, double New_h) {
        double difference = this.getListeetages()[indice].getHauteur_rel() - New_h;
        this.getListeetages()[indice].setHauteur_rel(New_h);
        if (indice == this.getListeetages().length - 1) {
            this.getListeetages()[indice - 1].setHauteur_rel(hauteur);

        } else if (0 == indice) {
            this.getListeetages()[1].setHauteur_rel(this.getListeetages()[1].getHauteur_rel() - difference);
        } else {

        }
    }

    /**
     * @return the listeetages
     */
    public Etage[] getListeetages() {
        return listeetages;
    }

    /**
     * @param listeetages the listeetages to set
     */
    public void setListeetages(Etage[] listeetages) {
        this.listeetages = listeetages;
    }

    /**
     * @return the piecedepasse
     */
    public boolean isPiecedepasse() {
        return piecedepasse;
    }

    /**
     * @param piecedepasse the piecedepasse to set
     */
    public void setPiecedepasse(boolean piecedepasse) {
        this.piecedepasse = piecedepasse;
    }

    /**
     * @return the perimetretriple
     */
    public boolean isPerimetretriple() {
        return perimetretriple;
    }

    /**
     * @param perimetretriple the perimetretriple to set
     */
    public void setPerimetretriple(boolean perimetretriple) {
        this.perimetretriple = perimetretriple;
    }

    /**
     * @return the estferme
     */
    public boolean isEstferme() {
        return estferme;
    }

    /**
     * @param estferme the estferme to set
     */
    public void setestferme(boolean estferme) {
        this.estferme = estferme;
    }

    private void GenererPiecesEtagesHorizontale() {
        int compteuretages = 0;
        if (isPerimetretriple()) {
            double yRef = 3 * getEpaisseurTriple();
            double hauteurdispo = this.getHauteur() - 6 * getEpaisseurTriple() - 3 * (this.getNb_etages() - 1) * getEpaisseurTriple();
            double xlargeur = this.largeur - 2 * getEpaisseurTriple();
            double xRef = getEpaisseurTriple();
            for (Etage etage : this.getListeetages()) {

                xRef = getEpaisseurTriple();
                double largeurdispo = this.getLargeur() - 6 * getEpaisseurTriple() - (etage.getListecaissons().length - 1) * 3 * getEpaisseurTriple();


                Piece pieceHorizontaleGauche = new Piece(etage.getHauteur_rel() * hauteurdispo, getEpaisseurTriple(), profondeur, "Piece gauche 1 Etage " + compteuretages);
                pieceHorizontaleGauche.setDrawingcoin(new Coord_Coins(getEpaisseurTriple(), yRef, 0));
                Piece pieceHorizontaleGauche1 = new Piece(etage.getHauteur_rel() * hauteurdispo, getEpaisseurTriple(), profondeur, "Piece gauche 2 Etage " + compteuretages);
                pieceHorizontaleGauche1.setDrawingcoin(new Coord_Coins(2.0 * getEpaisseurTriple(), yRef, 0));
                Piece pieceHorizontaleDroite = new Piece(etage.getHauteur_rel() * hauteurdispo, getEpaisseurTriple(), profondeur, "Piece Droite 1 Etage " + compteuretages);
                pieceHorizontaleDroite.setDrawingcoin(new Coord_Coins(this.largeur - 2.0 * getEpaisseurTriple(), yRef, 0));
                Piece pieceHorizontaleDroite1 = new Piece(etage.getHauteur_rel() * hauteurdispo, getEpaisseurTriple(), profondeur, "Piece Droite 2 Etage " + compteuretages);
                pieceHorizontaleDroite1.setDrawingcoin(new Coord_Coins(this.largeur - 3.0 * getEpaisseurTriple(), yRef, 0));

                Liste_piece.add(pieceHorizontaleDroite);
                Liste_piece.add(pieceHorizontaleDroite1);
                Liste_piece.add(pieceHorizontaleGauche);
                Liste_piece.add(pieceHorizontaleGauche1);
                if (compteuretages != getNb_etages() - 1) {

                    if(etage.getListecaissons().length==1){
                        Piece piece = new Piece(getEpaisseurTriple(), xlargeur, profondeur, "Piece bas Caisson 0 etage " + compteuretages);
                            piece.setDrawingcoin(new Coord_Coins(xRef, yRef+etage.getHauteur_rel()*hauteurdispo, 0));
                            Liste_piece.add(piece);
                    }
                    else{
                        for(int i = 0; i < etage.getListecaissons().length ; i++){
                            Piece piece = new Piece(getEpaisseurTriple(), etage.getListecaissons()[i].getLargeurRel()*
                                    largeurdispo, profondeur, "Piece bas Caisson " + i + " etage " + compteuretages);
                            piece.setDrawingcoin(new Coord_Coins(xRef, yRef+etage.getHauteur_rel()*hauteurdispo, 0));
                            Liste_piece.add(piece);

                        }
                            }
                    /*On Genere la piece du milieu(la plus longue) du montant horizontale*/
                    Piece piece = new Piece(getEpaisseurTriple(), xlargeur, profondeur, "Piece milieu Montant " + compteuretages);
                    piece.setDrawingcoin(new Coord_Coins(getEpaisseurTriple(), yRef + etage.getHauteur_rel() * hauteurdispo + getEpaisseurTriple(), 0));
                    compteuretages++;
                    yRef += etage.getHauteur_rel() * hauteurdispo + 3 * getEpaisseurTriple();
                    Liste_piece.add(piece);
                    getListe_Piece_Etage_Horizontale().add(piece);
                }
            }

        }
        if (!(isPerimetretriple())) {
            double yRef = 2 * getEpaisseurDouble();
            double hauteurdispo = this.getHauteur() - 4 * getEpaisseurDouble() - 3 * (this.getNb_etages() - 1) * getEpaisseurTriple();
            double xlargeur = this.largeur - 2 * getEpaisseurDouble();

            for (Etage etage : this.getListeetages()) {
                Piece pieceHorizontaleGauche = new Piece(etage.getHauteur_rel() * hauteurdispo, getEpaisseurDouble(), profondeur, "Piece gauche Etage " + compteuretages);
                pieceHorizontaleGauche.setDrawingcoin(new Coord_Coins(getEpaisseurDouble(), yRef, 0));
                Piece pieceHorizontaleDroite = new Piece(etage.getHauteur_rel() * hauteurdispo, getEpaisseurDouble(), profondeur, "Piece Droite Etage " + compteuretages);
                pieceHorizontaleDroite.setDrawingcoin(new Coord_Coins(largeur - 2 * getEpaisseurDouble(), yRef, 0));

                Liste_piece.add(pieceHorizontaleDroite);
                Liste_piece.add(pieceHorizontaleGauche);
                if (compteuretages != getNb_etages() - 1) {
                    Piece piece = new Piece(getEpaisseurTriple(), xlargeur, profondeur, "Piece milieu Montant etage " + compteuretages);
                    piece.setDrawingcoin(new Coord_Coins(getEpaisseurDouble(), yRef + etage.getHauteur_rel() * hauteurdispo + getEpaisseurTriple(), 0));
                    compteuretages++;
                    yRef += etage.getHauteur_rel() * hauteurdispo + 3 * getEpaisseurTriple();
                    Liste_piece.add(piece);
                    getListe_Piece_Etage_Horizontale().add(piece);
                }

            }
        }
    }

//    private void GenerePiecesCaissonsHorizontale(){
//        if(isPerimetretriple()){
//            double hauteurdispo = this.hauteur - (nb_etages-1)*3*epaisseurTriple-6*epaisseurTriple;
//            double xRef;
//            double yRef = 2*epaisseurTriple;
//            for(Etage etage : listeetages){
//                xRef = epaisseurTriple;
//                double largeurdisponible = this.largeur - 6 * epaisseurTriple - (etage.getListecaissons().length-1) * epaisseurTriple;
//                for(Caisson caisson : etage.getListecaissons()){
//                    Piece piece = new Piece(epaisseurTriple, caisson.getLargeur_rel()*largeurdisponible, profondeur, "Piece horizontale haut");
//                    piece.setDrawingcoin(new Coord_Coins(xRef, yRef, 0));
//                    Liste_piece.add(piece);
//                    xRef += caisson.getLargeur_rel()*largeurdisponible+epaisseurTriple;
//                }
//                yRef += etage.getHauteur_rel()*hauteurdispo+2*epaisseurTriple;
//            }
//            
//            
//        }
//        if(!(isPerimetretriple())){
//            
//        }
//    }
    
    //Si il y a présence d'un panneau arrièce, cette méthode s'assure de raccourcir certaines pièces pour accomoder les panneaux arrières dans les caissons.
    public void retraitPourPanneauArriere(){
        
        //On fait une liste de toutes les pièces qui doivent être moins profondes pour accomoder les panneaux arrières. 
        //Cette liste correspond à toutes les pièces sauf celles du périmètre extérieur, les pièces centrales des étages et les pièces centrales des caissons.
        
        List<Piece> listePieces = new ArrayList(Liste_piece);
        List<Piece> listePieces2 = new ArrayList(Liste_piece);
        listePieces2.clear();
        List<Piece> piecesCentralesEtages = Liste_Piece_Etage_Horizontale;
        

        //On sélectionne seulement les pièces qui doivent être modifiées
        for (Piece piece: listePieces){
            for(Piece piece2: piecesCentralesEtages){
                if (piece2.getNom()==piece.getNom()){
                    listePieces2.add(piece);
                    continue;
                }
            }
//faire un autre if pour enlever les pièces centrales des caissons
            String nom = piece.getNom();
            if(perimetretriple){            
                if(nom.contains("Piece du bas 1")||nom.contains("Piece du bas 2")||nom.contains("Piece du haut 1")||nom.contains("Piece du haut 2")
                        ||nom.contains("Piece de droite")||nom.contains("Piece de gauche")||nom.contains("Piece gauche 1")||nom.contains("Piece Droite 1")){
                    listePieces2.add(piece);
                }
            }
            else{//Si périmètre double
                if(nom.contains("Piece du bas")||nom.contains("Piece du haut")||nom.contains("Piece de droite")||nom.contains("Piece de gauche")){
                    listePieces2.add(piece);
                }                
            }
        }
        
        //On modifie la profondeur des pièces pour accomoder le panneau arrière dans chaque caisson.
        for (Piece piece: listePieces){
            boolean trouve = false;
            for (Piece piece2: listePieces2){
                if (piece.getNom() == piece2.getNom()){
                    trouve = true;
                    break;
                }
            }
            if (trouve == false){
                if(perimetretriple){
                    piece.setProfondeur(piece.getProfondeur() - epaisseurTriple);
                }
                else{
                    piece.setProfondeur(piece.getProfondeur() - epaisseurDouble);
                }                
            }
        }
    }    
    
}
