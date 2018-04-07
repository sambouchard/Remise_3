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
public class Caisson {
    
    private Etage etageparent;
    private double largeur_rel;
    private Piece piecehaut;
    
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
    
}
