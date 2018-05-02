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
    public void setDrawingcoin(Coord_Coins newdrawingcoin) {
        this.drawingcoin = newdrawingcoin;
        this.CoinFaceHautGauche = new Coord_Coins(newdrawingcoin.getCoord_x(), newdrawingcoin.getCoord_y(), newdrawingcoin.getCoord_z());
        this.CoinFaceHautDroit = new Coord_Coins(this.CoinFaceHautGauche.getCoord_x()+this.largeur, this.CoinFaceHautGauche.getCoord_y(),newdrawingcoin.getCoord_z() );
        this.CoinFaceBasGauche = new Coord_Coins(this.CoinFaceHautGauche.getCoord_x(), this.CoinFaceHautGauche.getCoord_y()+this.hauteur, newdrawingcoin.getCoord_z());
        this.CoinFaceBasDroit = new Coord_Coins(this.CoinFaceHautGauche.getCoord_x()+this.largeur, this.CoinFaceHautGauche.getCoord_y()+this.hauteur, newdrawingcoin.getCoord_z());
        this.CoinFondHautGauche = new Coord_Coins(CoinFaceHautGauche.getCoord_x(), CoinFaceHautGauche.getCoord_y(), newdrawingcoin.getCoord_z()+this.profondeur);
        this.CoinFondHautDroit = new Coord_Coins(CoinFaceHautDroit.getCoord_x(), CoinFaceHautDroit.getCoord_y(), newdrawingcoin.getCoord_z()+this.profondeur);
        this.CoinFondBasGauche = new Coord_Coins(CoinFaceBasGauche.getCoord_x(), CoinFaceBasGauche.getCoord_y(), newdrawingcoin.getCoord_z()+this.profondeur);
        this.CoinFondBasDroit = new Coord_Coins(CoinFaceBasDroit.getCoord_x(), CoinFaceBasDroit.getCoord_y(), newdrawingcoin.getCoord_z()+this.profondeur);
    }
    private double hauteur;
    private double largeur;
    private double profondeur;
    private String nom;
    private List<Coord_Coins> listecoins;
    private Coord_Coins drawingcoin;

    public Coord_Coins getCoinFaceHautGauche() {
        return CoinFaceHautGauche;
    }

    public void setCoinFaceHautGauche(Coord_Coins CoinFaceHautGauche) {
        this.CoinFaceHautGauche = CoinFaceHautGauche;
    }

    public Coord_Coins getCoinFaceHautDroit() {
        return CoinFaceHautDroit;
    }

    public void setCoinFaceHautDroit(Coord_Coins CoinFaceHautDroit) {
        this.CoinFaceHautDroit = CoinFaceHautDroit;
    }

    public Coord_Coins getCoinFaceBasDroit() {
        return CoinFaceBasDroit;
    }

    public void setCoinFaceBasDroit(Coord_Coins CoinFaceBasDroit) {
        this.CoinFaceBasDroit = CoinFaceBasDroit;
    }

    public Coord_Coins getCoinFaceBasGauche() {
        return CoinFaceBasGauche;
    }

    public void setCoinFaceBasGauche(Coord_Coins CoinFaceBasGauche) {
        this.CoinFaceBasGauche = CoinFaceBasGauche;
    }

    public Coord_Coins getCoinFondHautGauche() {
        return CoinFondHautGauche;
    }

    public void setCoinFondHautGauche(Coord_Coins CoinFondHautGauche) {
        this.CoinFondHautGauche = CoinFondHautGauche;
    }

    public Coord_Coins getCoinFondHautDroit() {
        return CoinFondHautDroit;
    }

    public void setCoinFondHautDroit(Coord_Coins CoinFondHautDroit) {
        this.CoinFondHautDroit = CoinFondHautDroit;
    }

    public Coord_Coins getCoinFondBasDroit() {
        return CoinFondBasDroit;
    }

    public void setCoinFondBasDroit(Coord_Coins CoinFondBasDroit) {
        this.CoinFondBasDroit = CoinFondBasDroit;
    }

    public Coord_Coins getCoinFondBasGauche() {
        return CoinFondBasGauche;
    }

    public void setCoinFondBasGauche(Coord_Coins CoinFondBasGauche) {
        this.CoinFondBasGauche = CoinFondBasGauche;
    }
    private Coord_Coins CoinFaceHautGauche;
    private Coord_Coins CoinFaceHautDroit;
    private Coord_Coins CoinFaceBasDroit;
    private Coord_Coins CoinFaceBasGauche;
    private Coord_Coins CoinFondHautGauche;
    private Coord_Coins CoinFondHautDroit;
    private Coord_Coins CoinFondBasDroit;
    private Coord_Coins CoinFondBasGauche;
    
    

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
