/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

import java.awt.geom.Rectangle2D;

/**
 *
 * @author SABOU350
 */
public class Caisson {

    /**
     * @return the x1
     */
    public double getX1() {
        return x1;
    }

    /**
     * @param x1 the x1 to set
     */
    public void setX1(double x1) {
        this.x1 = x1;
    }

    /**
     * @return the x2
     */
    public double getX2() {
        return x2;
    }

    /**
     * @param x2 the x2 to set
     */
    public void setX2(double x2) {
        this.x2 = x2;
    }

    /**
     * @return the y1
     */
    public double getY1() {
        return y1;
    }

    /**
     * @param y1 the y1 to set
     */
    public void setY1(double y1) {
        this.y1 = y1;
    }

    /**
     * @return the y2
     */
    public double getY2() {
        return y2;
    }

    /**
     * @param y2 the y2 to set
     */
    public void setY2(double y2) {
        this.y2 = y2;
    }
    
    private Etage etageparent;
    private double largeur_rel;
    private Piece piecehaut;
    private double x1;
    private double x2;
    private double y1;
    private double y2;
    
    /**
     * @return the largeur_rel
     */
    public double getLargeur_rel() {
        return largeur_rel;
    }

    /**
     * @param largeur_rel the largeur_rel to set
     */
    public void setLargeur_rel(double largeur_rel) {
        this.largeur_rel = largeur_rel;
    }
    /**
     * 
     * @param h_rel
     */
    
    Caisson(double l_rel){
        this.largeur_rel = l_rel;
        
    }
    
    public Rectangle2D getRectangle() {
        Rectangle2D rect = new Rectangle2D.Double(getX1(), getY1(), getX2(), getY2());
        return rect;
    }
    
}