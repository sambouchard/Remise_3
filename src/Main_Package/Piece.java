/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SABOU350
 */
public class Piece extends Rectangle2D.Double implements java.io.Serializable {
    public static double IMPERIAL_SCALING_FACTOR = 0.393701;    // 1cm = 0.39 inches
    public static String IMPERIAL_UNIT = "in.";
    public static String METRIC_UNIT = "cm";
    
    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the drawingcoin
     */
    public Coord_Coins getDrawingcoin() {
        return drawingcoin;
    }

    /**
     * @param drawingcoin the drawingcoin to set
     */
    public void setDrawingcoin(Coord_Coins drawingcoin) {
        this.drawingcoin = drawingcoin;
    }
    private double hauteur;
    private double largeur;
    private double profondeur;
    private String nom;
    private List<Coord_Coins> listecoins;
    private Coord_Coins drawingcoin;

    public void ajouteCoin(double x, double y, double z) {
        Coord_Coins coin = new Coord_Coins(x, y, z);
        listecoins.add(coin);
    }

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
    public double getProfondeur() {
        return profondeur;
    }

    /**
     * @param profondeur
     */
    public void setProfondeur(double profondeur) {
        this.profondeur = profondeur;
    }

    /**
     *
     * @param hauteur
     * @param largeur
     * @param profondeur
     * @param nom
     */
    public Piece(double hauteur, double largeur, double profondeur, String nom) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.profondeur = profondeur;
        this.nom = nom;
        this.listecoins = new ArrayList();
    }

    /**
     *
     * @return 
     */

    public String toStringMetrique() {
        String out = new String();
         out += this.nom + ":\t" +
                this.hauteur + METRIC_UNIT + " x " +
                this.largeur + METRIC_UNIT + " x " +
                this.profondeur + METRIC_UNIT;
        return out;
    }
    
    /**
     *
     * @return
     */
    public String toStringImperial() {
        String out = new String();
         out += this.nom + ":\t" +
                this.hauteur * IMPERIAL_SCALING_FACTOR + IMPERIAL_UNIT + " x " +
                this.largeur * IMPERIAL_SCALING_FACTOR + IMPERIAL_UNIT + " x " +
                this.profondeur * IMPERIAL_SCALING_FACTOR + IMPERIAL_UNIT;
        return out;
    }
}
