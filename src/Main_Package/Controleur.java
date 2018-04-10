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
public class Controleur {

    /**
     * @return the etagere
     */
    public Etagere getEtagere() {
        return etagere;
    }
    private Etagere etagere;
    private boolean mesuremetrique;
    private AfficheurEtagere2D afficheur;
    private Piece ListePiece[];
    private double y_reference;
    private double x_reference;
    
    
    public Controleur(Etagere etagere, boolean mesuremetrique) {
        this.etagere = etagere;
        this.mesuremetrique = mesuremetrique;
        this.afficheur = new AfficheurEtagere2D(this.getEtagere());

    }
    /**
     * 
     */
    public void style_background(boolean estferme){
        getEtagere().setestferme(estferme);
    }
    public void setmesure(boolean isMetrique) {
        this.mesuremetrique = isMetrique;
    }
    public boolean getmesure() {
        return mesuremetrique;
    }
    /**
     * 
     */
    public void enleveetage(int id){
        getEtagere().enleveetage(id);
    /**
     * 
     */
    }
    public void enleveCaisson(int id){
        //TODO
    }
    public void ajouteEtage(double h_rel){
        getEtagere().ajouteetage(h_rel);
        
    }
    public void ajouteCaisson(double l_rel){
        //TODO
    }
    public void setEtagereLargeur(double largeur){
        getEtagere().setLargeur(largeur);
    }
    public void setEtagereHauteur(double hauteur){
        getEtagere().setHauteur(hauteur);
    }
    public void setisTriple(boolean perimetretriple) {
        getEtagere().setisTriple(perimetretriple);
    }
    /**
     * 
     * @param mesuremetrique 
     */
    Controleur(boolean mesuremetrique) {
        this.mesuremetrique = mesuremetrique;
    }
    
    /**
     * 
     */
    public void dessineEtagere(Etagere etagere){
        
    }
    
    public void setEtagere(Etagere etagere2){
        this.etagere = etagere2;
    }
    
}
