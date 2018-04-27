/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

import UI.AfficheurEtagere2D;

/**
 * Singleton Controleur
 **/
public class Controleur {

    private Etagere etagere;
    private boolean mesureMetrique = true;
    private AfficheurEtagere2D afficheur;
    private static final Controleur instance = new Controleur();
    private Piece pieceSelectionner = null;

    public static Controleur getInstance() {
        return instance;
    }
    
    private Controleur() {}

    /**
     * @return the etagere
     */
    public Etagere getEtagere() {
        return etagere;
    }

    /**
     * @param etagere the etagere to set
     */
    public void setEtagere(Etagere etagere) {
        this.etagere = etagere;
        this.afficheur.redraw();
    }

    public void createNewEtagere(double hauteur, double largeur, double profondeur, int nb_etages,
            boolean estfermee, boolean piecedepasse, boolean perimetretriple) {
        Etagere e = new Etagere(hauteur, largeur, profondeur, nb_etages, estfermee, piecedepasse, perimetretriple);
        setEtagere(e);
    }

    public void styleBackground(boolean estferme) {
        getEtagere().setestferme(estferme);
    }

    public boolean getMesureMetrique() {
        return mesureMetrique;
    }
    
    public void setMesureMetrique(boolean isMetrique) {
        this.mesureMetrique = isMetrique;
    }
    
    public Piece getPieceSelectionner() {
        return pieceSelectionner;
    }
    
    public void setPieceSelectionner(Piece piece) {
        pieceSelectionner = piece;
    }
    
    /**
     * @param id
     */
    public void enleverEtage(int id) {
        if (id != -1) {
            getEtagere().enleveetage(id);
        }
    }

    public void enleveCaisson(int caissonid, int etageid) {
        if (caissonid != -1 || etageid != -1) {
            getEtagere().getListeetages()[etageid].SupprimeCaisson(caissonid);
        }
    }

    public void ajouteEtage(double h_rel) {
        getEtagere().ajouteetage(h_rel);

    }

    public void ajouteCaisson(double l_rel, int index) {
        getEtagere().getListeetages()[index].AjouteCaisson(l_rel);

    }

    /**
     *
     * @param largeur
     */
    public void setEtagereLargeur(double largeur) {
        getEtagere().setLargeur(largeur);
    }

    public void setEtagereHauteur(double hauteur) {
        getEtagere().setHauteur(hauteur);
    }

    public void setisTriple(boolean perimetretriple) {
        getEtagere().setPerimetretriple(perimetretriple);
    }

    /**
     * @return the afficheur
     */
    public AfficheurEtagere2D getAfficheur() {
        return afficheur;
    }

    /**
     * @param afficheur the afficheur to set
     */
    public void setAfficheur(AfficheurEtagere2D afficheur) {
        this.afficheur = afficheur;
    }
}
