/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

/**
 *
 * @author SABOU350
 */
public class Piece {
    private double hauteur;
    private double largeur;
    private double epaisseur;
    private boolean estepaisse;
    private Coord_Coins[] listecoins;
   

    
    
    /**
     * @return the hauteur
     */
    public double getHauteur() {
        return hauteur;
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
     * @return the epaisseur
     */
    public double getEpaisseur() {
        return epaisseur;
    }

    /**
     * @param epaisseur the epaisseur to set
     */
    public void setEpaisseur(double epaisseur) {
        this.epaisseur = epaisseur;
    }

    /**
     *
     * @param hauteur
     * @param largeur
     * @param epaisseur
     * @param estepaisse
     */
    public Piece(double hauteur, double largeur, double epaisseur, boolean estepaisse) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.epaisseur = epaisseur;
        this.estepaisse = estepaisse;
    }
    /**
     * 
     */
    

    
}
