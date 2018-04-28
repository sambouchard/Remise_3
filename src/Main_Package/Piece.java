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
public class Piece extends Rectangle2D.Double {

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
    private List<Coord_Coins> listecoins = new ArrayList();
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
     * @param Profondeur
     */
    public void setProfondeur(double Profondeur) {
        this.profondeur = profondeur;
    }

    /**
     *
     * @param hauteur
     * @param largeur
     * @param profondeur
     * @param estepaisse
     */
    public Piece(double hauteur, double largeur, double profondeur, String nom) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.profondeur = profondeur;
        this.nom = nom;
    }
    /**
     *
     */

}
