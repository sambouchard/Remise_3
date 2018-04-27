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

    
    
    
    public Controleur() {
        

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
        if (id==-1){
            return;
        }
        else {
            getEtagere().enleveetage(id);
        }
    /**
     * 
     */
    }
    public void enleveCaisson(int caissonid, int etageid){
        if (caissonid==-1 || etageid==-1){
            return;

        }
        else{
            getEtagere().getListeetages()[etageid].SupprimeCaisson(caissonid);
        }

    }
    public void ajouteEtage(double h_rel){
        getEtagere().ajouteetage(h_rel);
        
    }
    public void ajouteCaisson(double l_rel, int index){
        getEtagere().getListeetages()[index].AjouteCaisson(l_rel);
       
    }
      
    /**
     *
     * @param largeur
     */
    public void setEtagereLargeur(double largeur){
        getEtagere().setLargeur(largeur);
    }
    public void setEtagereHauteur(double hauteur){
        getEtagere().setHauteur(hauteur);
    }
    public void setisTriple(boolean perimetretriple) {
        getEtagere().setPerimetretriple(perimetretriple);
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
    
    

    /**
     * @param etagere the etagere to set
     */
    public void setEtagere(Etagere etagere) {
        this.etagere = etagere;

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