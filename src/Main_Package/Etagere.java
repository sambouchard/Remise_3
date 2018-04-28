/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author SABOU350
 */
public class Etagere {

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
    }

    private void GenererPiecesPerimetreBasDouble() {
        Piece piecebas = new Piece(epaisseurDouble, this.largeur - 2 * epaisseurDouble, this.profondeur, "Piece du bas");
        piecebas.setDrawingcoin(new Coord_Coins(epaisseurDouble, this.hauteur - epaisseurDouble, 0));
        this.getListe_piece().add(piecebas);
    }

    private void GenererPiecePerimetreBasTriple() {
        Piece piecebas = new Piece(epaisseurTriple, this.largeur - 2 * (epaisseurTriple), this.profondeur, "Piece du bas 1");
        piecebas.setDrawingcoin(new Coord_Coins(epaisseurTriple, this.hauteur - epaisseurTriple, 0));
        this.getListe_piece().add(piecebas);

    }

    private void GenererPiecsePerimetreHautDouble() {
        if (isPiecedepasse()) {
            Piece piecehaut = new Piece(epaisseurDouble, this.largeur, this.profondeur, "Piece du haut");
            
            piecehaut.setDrawingcoin(new Coord_Coins(0, 0, 0));

            this.getListe_piece().add(piecehaut);
        } else if (!(isPiecedepasse())) {
            Piece piecehaut = new Piece(epaisseurDouble, this.largeur - 2 * epaisseurDouble, this.profondeur, "Piece du haut");
            
            piecehaut.setDrawingcoin(new Coord_Coins(epaisseurDouble, 0, 0));
            this.getListe_piece().add(piecehaut);
        }
    }

    private void GenererPiecesPerimetreHautTriple() {
        if (isPiecedepasse()) {
            Piece piecehaut = new Piece(epaisseurTriple, this.largeur, this.profondeur, "Piece du haut1");
            piecehaut.setDrawingcoin(new Coord_Coins(0, 0, 0));
            Piece piecehaut1 = new Piece(epaisseurTriple, this.largeur - 2 * (0.5 * 2.54), this.profondeur, "Piece du haut2");
            piecehaut1.setDrawingcoin(new Coord_Coins(epaisseurTriple, epaisseurTriple, 0));
            this.getListe_piece().add(piecehaut);
            this.getListe_piece().add(piecehaut1);

        } else if (!(isPiecedepasse())) {
            Piece piecehaut = new Piece(epaisseurTriple, this.largeur - 2 * (epaisseurTriple), this.profondeur, "Piece du haut 1");
            piecehaut.setDrawingcoin(new Coord_Coins(epaisseurTriple, 0, 0));
            Piece piecehaut1 = new Piece(epaisseurTriple, this.largeur - 2 * (epaisseurTriple), this.profondeur, "Piece du haut 2");
            piecehaut1.setDrawingcoin(new Coord_Coins(epaisseurTriple, epaisseurTriple, 0));
            this.getListe_piece().add(piecehaut);
            this.getListe_piece().add(piecehaut1);
        }
    }

    private void GenererPiecesPerimetreCote() {
        double epaisseur = 0;
        if (isPerimetretriple()) {
            epaisseur = epaisseurTriple;
        } else if (!(isPerimetretriple())) {
            epaisseur = epaisseurDouble;
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
            double yRef = 3 * epaisseurTriple;
            double hauteurdispo = this.getHauteur() - 6 * epaisseurTriple - 3 * (this.getNb_etages() - 1) * epaisseurTriple;
            double xlargeur = this.largeur - 2 * epaisseurTriple;
            double xRef = epaisseurTriple;
            for (Etage etage : this.getListeetages()) {

                xRef = epaisseurTriple;
                double largeurdispo = this.getLargeur() - 6 * epaisseurTriple - (etage.getListecaissons().length - 1) * 3 * epaisseurTriple;


                Piece pieceHorizontaleGauche = new Piece(etage.getHauteur_rel() * hauteurdispo,
                        epaisseurTriple, profondeur, "Piece gauche 1 Etage " + compteuretages);
                pieceHorizontaleGauche.setDrawingcoin(new Coord_Coins(epaisseurTriple, yRef, 0));
                Piece pieceHorizontaleGauche1 = new Piece(etage.getHauteur_rel() * hauteurdispo,
                        epaisseurTriple, profondeur, "Piece gauche 2 Etage " + compteuretages);
                pieceHorizontaleGauche1.setDrawingcoin(new Coord_Coins(2.0 * epaisseurTriple, yRef, 0));
                Piece pieceHorizontaleDroite = new Piece(etage.getHauteur_rel() * hauteurdispo,
                        epaisseurTriple, profondeur, "Piece Droite 1 Etage " + compteuretages);
                pieceHorizontaleDroite.setDrawingcoin(new Coord_Coins(this.largeur - 2.0 * epaisseurTriple, yRef, 0));
                Piece pieceHorizontaleDroite1 = new Piece(etage.getHauteur_rel() * hauteurdispo,
                        epaisseurTriple, profondeur, "Piece Droite 2 Etage " + compteuretages);
                pieceHorizontaleDroite1.setDrawingcoin(new Coord_Coins(this.largeur - 3.0 * epaisseurTriple, yRef, 0));

                Liste_piece.add(pieceHorizontaleDroite);
                Liste_piece.add(pieceHorizontaleDroite1);
                Liste_piece.add(pieceHorizontaleGauche);
                Liste_piece.add(pieceHorizontaleGauche1);
                if (compteuretages != getNb_etages() - 1) {

                    if(etage.getListecaissons().length==1){
                        Piece piece = new Piece(epaisseurTriple, xlargeur, profondeur, "Piece bas Caisson 0 etage " + compteuretages);
                            piece.setDrawingcoin(new Coord_Coins(xRef, yRef+etage.getHauteur_rel()*hauteurdispo, 0));
                            Liste_piece.add(piece);
                    }
                    else{
                        for(int i = 0; i < etage.getListecaissons().length ; i++){
                            Piece piece = new Piece(epaisseurTriple, etage.getListecaissons()[i].getLargeurRel()*
                                    largeurdispo, profondeur, "Piece bas Caisson " + i + " etage " + compteuretages);
                            piece.setDrawingcoin(new Coord_Coins(xRef, yRef+etage.getHauteur_rel()*hauteurdispo, 0));
                            Liste_piece.add(piece);

                        }
                            }
                    /*On Genere la piece du milieu(la plus longue) du montant horizontale*/
                    Piece piece = new Piece(epaisseurTriple, xlargeur, profondeur, "Piece milieu Montant " + compteuretages);
                    piece.setDrawingcoin(new Coord_Coins(epaisseurTriple, yRef + etage.getHauteur_rel() * hauteurdispo + epaisseurTriple, 0));
                    compteuretages++;
                    yRef += etage.getHauteur_rel() * hauteurdispo + 3 * epaisseurTriple;
                    Liste_piece.add(piece);
                    getListe_Piece_Etage_Horizontale().add(piece);
                }
            }

        }
        if (!(isPerimetretriple())) {
            double yRef = 2 * epaisseurDouble;
            double hauteurdispo = this.getHauteur() - 4 * epaisseurDouble - 3 * (this.getNb_etages() - 1) * epaisseurTriple;
            double xlargeur = this.largeur - 2 * epaisseurDouble;

            for (Etage etage : this.getListeetages()) {
                Piece pieceHorizontaleGauche = new Piece(etage.getHauteur_rel() * hauteurdispo,
                        epaisseurDouble, profondeur, "Piece gauche Etage " + compteuretages);
                pieceHorizontaleGauche.setDrawingcoin(new Coord_Coins(epaisseurDouble, yRef, 0));
                Piece pieceHorizontaleDroite = new Piece(etage.getHauteur_rel() * hauteurdispo,
                        epaisseurDouble, profondeur, "Piece Droite Etage " + compteuretages);
                pieceHorizontaleDroite.setDrawingcoin(new Coord_Coins(largeur - 2 * epaisseurDouble, yRef, 0));

                Liste_piece.add(pieceHorizontaleDroite);
                Liste_piece.add(pieceHorizontaleGauche);
                if (compteuretages != getNb_etages() - 1) {
                    Piece piece = new Piece(epaisseurTriple, xlargeur, profondeur, "Piece milieu Montant etage " + compteuretages);
                    piece.setDrawingcoin(new Coord_Coins(epaisseurDouble, yRef + etage.getHauteur_rel() * hauteurdispo + epaisseurTriple, 0));
                    compteuretages++;
                    yRef += etage.getHauteur_rel() * hauteurdispo + 3 * epaisseurTriple;
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
}
