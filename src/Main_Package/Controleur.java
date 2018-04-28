/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

import UI.AfficheurEtagere2D;
import UI.GUI;
import java.awt.Component;
import javax.swing.JLabel;
import sun.security.util.SecurityConstants;

/**
 * Singleton Controleur
 *
 */
public class Controleur {

    private Etagere etagere;
    private boolean mesureMetrique = true;
    private AfficheurEtagere2D afficheur;
    private static final Controleur instance = new Controleur();
    private Piece pieceSelectionner = null;
    private GUI Vue;
    private boolean ajouteetageMode;
    private boolean ajouteCaissonMode;

    public Etagere getUndoEtagere() {
        return UndoEtagere;
    }

    public void setUndoEtagere() {
        Etagere newetaGere = this.etagere;
        this.UndoEtagere = newetaGere;
    }
    private Etagere UndoEtagere;

    public GUI getVue() {
        return Vue;
    }

    public void setVue(GUI Vue) {
        this.Vue = Vue;
    }

    public static Controleur getInstance() {
        return instance;
    }

    private Controleur() {
    }

    /**
     * @return the etagere
     */
    public Etagere getEtagere() {
        return etagere;
    }

    public void setTriple(Boolean triple) {
        this.etagere.setPerimetretriple(triple);
        this.etagere.GenererPieces();
        this.afficheur.redraw();
    }
    public void setDepasse(Boolean depasse){
        this.etagere.setPiecedepasse(depasse);
        this.etagere.GenererPieces();
        this.afficheur.redraw();
    }



    /**
     * @param etagere the etagere to set
     */
    public void setEtagere(Etagere etagere) {
        this.etagere = etagere;
        this.afficheur.redraw();
    }

    public void updatevue() {
        this.Vue.getHauteur_Textfield().setText(String.valueOf(this.etagere.getHauteur()));
        this.Vue.getLargeur_TextField().setText(String.valueOf(this.etagere.getLargeur()));
        this.Vue.getProfondeur_TextField().setText(String.valueOf(this.etagere.getProfondeur()));
        
        
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
        this.Vue.getLargeurPieceSelecrtionneField().setText(String.valueOf(piece.getLargeur()));
        this.Vue.getHauteurPieceSelecrtionneField().setText(String.valueOf(piece.getHauteur()));
        this.Vue.getProfondeurPieceSelecrtionneField().setText(String.valueOf(piece.getProfondeur()));
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
        etagere.GenererPieces();
    }

    public void setEtagereHauteur(double hauteur) {
        setUndoEtagere();
        etagere.setHauteur(hauteur);
        etagere.GenererPieces();
    }
    public void setEtagereProfondeur(double profondeur) {
        getEtagere().setProfondeur(profondeur);
        etagere.GenererPieces();
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
    
    public void undo(){
        if(this.UndoEtagere != null){
            this.setEtagere(this.UndoEtagere);
            afficheur.redraw();
        }
        else{
            System.out.println("Undo etagere nexiste pas");
        }
    }
            
}
