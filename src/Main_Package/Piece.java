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
    private float hauteur;
    private float largeur;
    private float epaisseur;
    private boolean estepaisse;
    private Coord_Coins[] listecoins;
   

    
    
    /**
     * @return the hauteur
     */
    public float getHauteur() {
        return hauteur;
    }

    /**
     * @param hauteur the hauteur to set
     */
    public void setHauteur(float hauteur) {
        this.hauteur = hauteur;
    }

    /**
     * @return the largeur
     */
    public float getLargeur() {
        return largeur;
    }

    /**
     * @param largeur the largeur to set
     */
    public void setLargeur(float largeur) {
        this.largeur = largeur;
    }

    /**
     * @return the epaisseur
     */
    public float getEpaisseur() {
        return epaisseur;
    }

    /**
     * @param epaisseur the epaisseur to set
     */
    public void setEpaisseur(float epaisseur) {
        this.epaisseur = epaisseur;
    }

    public Piece(float hauteur, float largeur, float epaisseur, boolean estepaisse) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.epaisseur = epaisseur;
        this.estepaisse = estepaisse;
    }
    /**
     * 
     */
    

    
}
