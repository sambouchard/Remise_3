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
public class Etagere implements java.io.Serializable {

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

    public List<MontantEtageHorizontal> getListeMontantHorizontal() {
        return ListeMontantHorizontal;
    }

    public void setListeMontantHorizontal(List<MontantEtageHorizontal> ListeMontantHorizontal) {
        this.ListeMontantHorizontal = ListeMontantHorizontal;
    }
    private List<MontantEtageHorizontal> ListeMontantHorizontal = new ArrayList();
    private List<MontantCaissonVertical> ListeMontantVertical = new ArrayList();

    public List<MontantCaissonVertical> getListeMontantVertical() {
        return ListeMontantVertical;
    }

    public void setListeMontantVertical(List<MontantCaissonVertical> ListeMontantVertical) {
        this.ListeMontantVertical = ListeMontantVertical;
    }
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
        for (Piece piece : this.Liste_Piece_Etage_Horizontale) {
            out += piece.toStringMetrique() + "\n";
        }
        return out;
    }

    public String toStringImperial() {
        String out = new String();
        for (Piece piece : this.Liste_piece) {
            out += piece.toStringImperial() + "\n";
        }
        for (Piece piece : this.Liste_Piece_Etage_Horizontale) {
            out += piece.toStringImperial() + "\n";
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
        ListeMontantVertical.clear();
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
        GenererPiecesEtages();
        GenerePiecesCaissonsVerticale();
        if (estferme) {
            retraitPourPanneauArriere();
        }
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
        if (this.getListeetages()[this.getNb_etages() - 1].getListecaissons().length == 0) {

        } else {

        }

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
        double xRef = epaisseurTriple;
        double yRef = 2 * epaisseurTriple;
        double largeurdispo = this.largeur - 6 * epaisseurTriple - 3 * epaisseurTriple * (this.getListeetages()[0].getListecaissons().length - 1);

        if (this.getListeetages()[0].getListecaissons().length == 1) {
            Piece piece = new Piece(epaisseurTriple, this.getListeetages()[0].getListecaissons()[0].getLargeurRel() * largeurdispo + 4 * epaisseurTriple, this.profondeur, "Piece Haut du caisson 0 de l'etage 1");
            piece.setDrawingcoin(new Coord_Coins(xRef, yRef, 0));
            this.getListe_piece().add(piece);
        } else {
            for (Caisson caisson : this.getListeetages()[0].getListecaissons()) {
                if (caisson.getId() == 0) {
                    Piece piece = new Piece(epaisseurTriple, caisson.getLargeurRel() * largeurdispo + 3 * epaisseurTriple, this.profondeur, "Piece Haut du caisson " + caisson.getId() + " de l'etage 1");
                    piece.setDrawingcoin(new Coord_Coins(xRef, yRef, 0));
                    this.getListe_piece().add(piece);
                    xRef += caisson.getLargeurRel() * largeurdispo + epaisseurTriple + 3 * epaisseurTriple;
                } else if (caisson.getId() == this.getListeetages()[0].getListecaissons().length - 1) {
                    Piece piece = new Piece(epaisseurTriple, caisson.getLargeurRel() * largeurdispo + 3 * epaisseurTriple, this.profondeur, "Piece Haut du caisson " + caisson.getId() + " de l'etage 1");
                    piece.setDrawingcoin(new Coord_Coins(xRef, yRef, 0));
                    this.getListe_piece().add(piece);
                } else {
                    Piece piece = new Piece(epaisseurTriple, caisson.getLargeurRel() * largeurdispo + 2 * epaisseurTriple, this.profondeur, "Piece Haut du caisson " + caisson.getId() + " de l'etage 1");
                    piece.setDrawingcoin(new Coord_Coins(xRef, yRef, 0));
                    this.getListe_piece().add(piece);
                    xRef += caisson.getLargeurRel() * largeurdispo + 3 * epaisseurTriple;
                }

            }
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
            piecedroite.setDrawingcoin(new Coord_Coins(this.largeur - epaisseur, epaisseur, 0));
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

    private void GenererPiecesEtages() {
        int compteuretages = 0;
        if (isPerimetretriple()) {
            double yRef = 3 * epaisseurTriple;
            double hauteurdispo = this.getHauteur() - 6 * epaisseurTriple - 3 * (this.getNb_etages() - 1) * epaisseurTriple;
            double xlargeur = this.largeur - 2 * epaisseurTriple;
            double xRef = epaisseurTriple;
            for (Etage etage : this.getListeetages()) {
                xRef = epaisseurTriple;

                double largeurdispo = this.getLargeur() - 6 * epaisseurTriple - (etage.getNb_Caisson() - 1) * 3 * epaisseurTriple;
                
                etage.setId(compteuretages);
                etage.setX1(xRef);
                etage.setX2(this.largeur - epaisseurTriple);
                etage.setY1(yRef);
                etage.setY2(yRef + etage.getHauteur_rel() * hauteurdispo);
                etage.setRect(xRef + 300, yRef + 300, this.largeur - 2 * epaisseurTriple, etage.getHauteur_rel() * hauteurdispo);
                if (etage.getId() != getNb_etages() - 1) {
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
                    int compteur_caisson = 0;
                    if (etage.getListecaissons().length == 1) {
                        Piece piece = new Piece(epaisseurTriple, xlargeur, profondeur, "Piece bas Caisson 0 etage " + compteuretages);
                        piece.setDrawingcoin(new Coord_Coins(xRef, yRef + etage.getHauteur_rel() * hauteurdispo, 0));
                        Liste_piece.add(piece);
                        etage.getListecaissons()[0].setRect(xRef + 2 * epaisseurTriple + 300, yRef + 300, etage.getListecaissons()[0].getLargeurRel()
                                * largeurdispo, etage.getHauteur_rel() * hauteurdispo);
                    } /*On genere les pieces du bas des caissons*/ else {
                        for (Caisson caisson : etage.getListecaissons()) {
                            caisson.setRect(xRef + 2 * epaisseurTriple + 300, yRef + 300, caisson.getLargeurRel()
                                    * largeurdispo, etage.getHauteur_rel() * hauteurdispo);
                            caisson.setId(compteur_caisson);
                            Piece piece = new Piece(epaisseurTriple, caisson.getLargeurRel()
                                    * largeurdispo, profondeur, "Piece bas Caisson " + compteur_caisson + " etage " + compteuretages);
                            piece.setDrawingcoin(new Coord_Coins(xRef, yRef + etage.getHauteur_rel() * hauteurdispo, 0));
                            Liste_piece.add(piece);
                            xRef += caisson.getLargeurRel() * largeurdispo + 3 * epaisseurTriple;
                            compteur_caisson++;

                        }
                    }
                    /*On Genere la piece du milieu(la plus longue) du montant horizontale*/
                    Piece piece = new Piece(epaisseurTriple, xlargeur, profondeur, "Piece milieu Montant " + compteuretages);
                    piece.setDrawingcoin(new Coord_Coins(epaisseurTriple, yRef + etage.getHauteur_rel() * hauteurdispo + epaisseurTriple, 0));
                    compteuretages++;
                    yRef += etage.getHauteur_rel() * hauteurdispo + 3 * epaisseurTriple;
                    Liste_piece.add(piece);
                    getListe_Piece_Etage_Horizontale().add(piece);
                } else {
                    Piece pieceHorizontaleGauche = new Piece(etage.getHauteur_rel() * hauteurdispo+2*epaisseurTriple,
                            epaisseurTriple, profondeur, "Piece gauche 1 Etage " + compteuretages);
                    pieceHorizontaleGauche.setDrawingcoin(new Coord_Coins(epaisseurTriple, yRef, 0));
                    Piece pieceHorizontaleGauche1 = new Piece(etage.getHauteur_rel() * hauteurdispo+epaisseurTriple,
                            epaisseurTriple, profondeur, "Piece gauche 2 Etage " + compteuretages);
                    pieceHorizontaleGauche1.setDrawingcoin(new Coord_Coins(2.0 * epaisseurTriple, yRef, 0));
                    Piece pieceHorizontaleDroite = new Piece(etage.getHauteur_rel() * hauteurdispo+2*epaisseurTriple,
                            epaisseurTriple, profondeur, "Piece Droite 1 Etage " + compteuretages);
                    pieceHorizontaleDroite.setDrawingcoin(new Coord_Coins(this.largeur - 2.0 * epaisseurTriple, yRef, 0));
                    Piece pieceHorizontaleDroite1 = new Piece(etage.getHauteur_rel() * hauteurdispo+epaisseurTriple,
                            epaisseurTriple, profondeur, "Piece Droite 2 Etage " + compteuretages);
                    pieceHorizontaleDroite1.setDrawingcoin(new Coord_Coins(this.largeur - 3.0 * epaisseurTriple, yRef, 0));

                    Liste_piece.add(pieceHorizontaleDroite);
                    Liste_piece.add(pieceHorizontaleDroite1);
                    Liste_piece.add(pieceHorizontaleGauche);
                    Liste_piece.add(pieceHorizontaleGauche1);
                    int compteur_caisson = 0;
                    if (etage.getListecaissons().length == 1) {
                        Piece piece = new Piece(epaisseurTriple, xlargeur, profondeur, "Piece bas Caisson 0 etage " + compteuretages);
                        piece.setDrawingcoin(new Coord_Coins(xRef, yRef + etage.getHauteur_rel() * hauteurdispo, 0));
                        Liste_piece.add(piece);
                        etage.getListecaissons()[0].setRect(xRef + 2 * epaisseurTriple + 300, yRef + 300, etage.getListecaissons()[0].getLargeurRel()
                                * largeurdispo, etage.getHauteur_rel() * hauteurdispo);
                    } else {
                        
                        for (Caisson caisson : etage.getListecaissons()) {
                            caisson.setRect(xRef + 2 * epaisseurTriple + 300, yRef + 300, caisson.getLargeurRel()
                                    * largeurdispo, etage.getHauteur_rel() * hauteurdispo);
                            caisson.setId(compteur_caisson);
                            if(caisson.getId()==0){
                                Piece piece = new Piece(epaisseurTriple, caisson.getLargeurRel()
                                        * largeurdispo+2*epaisseurTriple, profondeur, "Piece bas Caisson " + caisson.getId() + " etage " + etage.getId());
                                piece.setDrawingcoin(new Coord_Coins(xRef, yRef + etage.getHauteur_rel() * hauteurdispo, 0));
                                Liste_piece.add(piece);
                                xRef += caisson.getLargeurRel() * largeurdispo + 3 * epaisseurTriple;
                                compteur_caisson++;
                            }
                            else {    Piece piece = new Piece(epaisseurTriple, caisson.getLargeurRel()
                                        * largeurdispo, profondeur, "Piece bas Caisson " + compteur_caisson + " etage " + compteuretages);
                                piece.setDrawingcoin(new Coord_Coins(xRef, yRef + etage.getHauteur_rel() * hauteurdispo, 0));
                                Liste_piece.add(piece);
                                xRef += caisson.getLargeurRel() * largeurdispo + 3 * epaisseurTriple;
                                compteur_caisson++;
                            }

                        }
                    }
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
                etage.setId(compteuretages);
                etage.setX1(2 * epaisseurDouble);
                etage.setX2(this.largeur - epaisseurTriple);
                etage.setY1(yRef);
                etage.setY2(yRef + etage.getHauteur_rel() * hauteurdispo);
                etage.setRect(2 * epaisseurDouble, yRef, this.largeur - 2 * epaisseurDouble, etage.getHauteur_rel() * hauteurdispo);
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

    public double getEpaisseurTriple() {
        return epaisseurTriple;
    }

    public void setEpaisseurTriple(double epaisseurTriple) {
        this.epaisseurTriple = epaisseurTriple;
    }

    public double getEpaisseurDouble() {
        return epaisseurDouble;
    }

    public void setEpaisseurDouble(double epaisseurDouble) {
        this.epaisseurDouble = epaisseurDouble;
    }

    private void GenerePiecesCaissonsVerticale() {
        if (isPerimetretriple()) {
            double hauteurdispo = this.hauteur - (nb_etages - 1) * 3 * epaisseurTriple - 6 * epaisseurTriple;
            double xRef = 3 * epaisseurTriple;
            double yRef = 3 * epaisseurTriple;
            for (Etage etage : listeetages) {
                if (etage.getListecaissons().length != 1) {
                    xRef = 3 * epaisseurTriple;
                }
                double largeurdisponible = this.largeur - 6 * epaisseurTriple - (etage.getNb_Caisson() - 1) * 3 * epaisseurTriple;
                for (int i = 0; i < etage.getListecaissons().length - 1; i++) {
                    xRef += etage.getListecaissons()[i].getLargeurRel() * largeurdisponible;
                    Piece piece1 = new Piece(etage.getHauteur_rel() * hauteurdispo, epaisseurTriple, this.profondeur, "Piece gauche montant Vertical Caisson" + i + " - " + i + 1 + " etage " + etage.getId());
                    Piece piece2 = new Piece(etage.getHauteur_rel() * hauteurdispo + epaisseurTriple, epaisseurTriple, this.profondeur, "Piece milieu montant Vertical Caisson" + i + " - " + i + 1 + " etage " + etage.getId());
                    Piece piece3 = new Piece(etage.getHauteur_rel() * hauteurdispo, epaisseurTriple, this.profondeur, "Piece droite montant Vertical Caisson" + i + " - " + i + 1 + " etage " + etage.getId());
                    piece1.setDrawingcoin(new Coord_Coins(xRef, yRef, 0));
                    piece2.setDrawingcoin(new Coord_Coins(xRef + epaisseurTriple, yRef - epaisseurTriple, 0));
                    piece3.setDrawingcoin(new Coord_Coins(xRef + 2 * epaisseurTriple, yRef, 0));
                    Liste_piece.add(piece1);
                    Liste_piece.add(piece2);
                    Liste_piece.add(piece3);
                    MontantCaissonVertical montant = new MontantCaissonVertical(piece1, piece2, piece3, etage.getListecaissons()[i], etage.getListecaissons()[i+1],etage);
                    ListeMontantVertical.add(montant);
                    xRef += 3 * epaisseurTriple;
                }
                yRef += etage.getHauteur_rel() * hauteurdispo + 3 * epaisseurTriple;
            }

        }
        if (!(isPerimetretriple())) {
            double hauteurdispo = this.hauteur - (nb_etages - 1) * 3 * epaisseurTriple - 4 * epaisseurDouble;
            double xRef = 2 * epaisseurDouble;
            double yRef = 2 * epaisseurDouble;
            for (Etage etage : listeetages) {
                if (etage.getListecaissons().length != 1) {
                    xRef = 2 * epaisseurDouble;
                }
                double largeurdisponible = this.largeur - 4 * epaisseurDouble - (etage.getNb_Caisson() - 1) * 3 * epaisseurTriple;
                for (int i = 0; i < etage.getListecaissons().length - 1; i++) {
                    xRef += etage.getListecaissons()[i].getLargeurRel() * largeurdisponible;
                    Piece piece1 = new Piece(etage.getHauteur_rel() * hauteurdispo, epaisseurTriple, this.profondeur, "Piece gauche montant Vertical Caisson" + i + " - " + i + 1 + " etage " + etage.getId());
                    Piece piece2 = new Piece(etage.getHauteur_rel() * hauteurdispo + epaisseurDouble, epaisseurTriple, this.profondeur, "Piece milieu montant Vertical Caisson" + i + " - " + i + 1 + " etage " + etage.getId());
                    Piece piece3 = new Piece(etage.getHauteur_rel() * hauteurdispo, epaisseurTriple, this.profondeur, "Piece droite montant Vertical Caisson" + i + " - " + i + 1 + " etage " + etage.getId());
                    piece1.setDrawingcoin(new Coord_Coins(xRef, yRef, 0));
                    piece2.setDrawingcoin(new Coord_Coins(xRef + epaisseurTriple, yRef - epaisseurTriple, 0));
                    piece3.setDrawingcoin(new Coord_Coins(xRef + 2 * epaisseurTriple, yRef, 0));
                    Liste_piece.add(piece1);
                    Liste_piece.add(piece2);
                    Liste_piece.add(piece3);
                    MontantCaissonVertical montant = new MontantCaissonVertical(piece1, piece2, piece3, etage.getListecaissons()[i], etage.getListecaissons()[i+1],etage);
                    ListeMontantVertical.add(montant);
                    xRef += 3 * epaisseurTriple;
                }
                yRef += etage.getHauteur_rel() * hauteurdispo + 3 * epaisseurTriple;

            }
        }
    }

    public void retraitPourPanneauArriere() {

        //On fait une liste de toutes les pièces qui doivent être moins profondes pour accomoder les panneaux arrières. 
        //Cette liste correspond à toutes les pièces sauf celles du périmètre extérieur, les pièces centrales des étages et les pièces centrales des caissons.
        List<Piece> listePieces = new ArrayList(Liste_piece);
        List<Piece> listePieces2 = new ArrayList(Liste_piece);
        listePieces2.clear();
        List<Piece> piecesCentralesEtages = Liste_Piece_Etage_Horizontale;

        //On sélectionne seulement les pièces qui doivent être modifiées
        for (Piece piece : listePieces) {
            for (Piece piece2 : piecesCentralesEtages) {
                if (piece2.getNom() == piece.getNom()) {
                    listePieces2.add(piece);
                    continue;
                }
            }
//faire un autre if pour enlever les pièces centrales des caissons
            String nom = piece.getNom();
            if (perimetretriple) {
                if (nom.contains("Piece du bas 1") || nom.contains("Piece du bas 2") || nom.contains("Piece du haut 1") || nom.contains("Piece du haut 2")
                        || nom.contains("Piece de droite") || nom.contains("Piece de gauche") || nom.contains("Piece gauche 1") || nom.contains("Piece Droite 1")) {
                    listePieces2.add(piece);
                }
            } else {//Si périmètre double
                if (nom.contains("Piece du bas") || nom.contains("Piece du haut") || nom.contains("Piece de droite") || nom.contains("Piece de gauche")) {
                    listePieces2.add(piece);
                }
            }
        }

        //On modifie la profondeur des pièces pour accomoder le panneau arrière dans chaque caisson.
        for (Piece piece : listePieces) {
            boolean trouve = false;
            for (Piece piece2 : listePieces2) {
                if (piece.getNom() == piece2.getNom()) {
                    trouve = true;
                    break;
                }
            }
            if (trouve == false) {
                if (perimetretriple) {
                    piece.setProfondeur(piece.getProfondeur() - epaisseurTriple);
                } else {
                    piece.setProfondeur(piece.getProfondeur() - epaisseurDouble);
                }
            }
        }
    }
}
