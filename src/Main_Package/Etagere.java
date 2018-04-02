/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;
import java.util.Arrays;

/**
 *
 * @author SABOU350
 */
public class Etagere {
    private float hauteur;
    private float largeur;
    private float profondeur;
    private int nb_etages;
    private Etage[] listeetages;
    private boolean estferme;
    private Piece piecegauche;
    private Piece piecedroite;
    private Piece piecehaut;
    private Piece piecebas;
    private boolean estepaisse;
    
    
    
    /**
     * @param hauteur
     * @param largeur
     * @param profondeur
     * @param nb_etages
     * @param estfermee
     * @create etagere object
     */
    public Etagere(float hauteur, float largeur, float profondeur, int nb_etages,boolean estfermee) {
        setHauteur(hauteur);
        setLargeur(largeur);
        setProfondeur(profondeur);
        setNb_etages(nb_etages);
        this.estferme = estfermee;
        this.listeetages = new Etage[this.nb_etages];
        
        for(int i = 0; i < getNb_etages() ; i++){
            Etage unetage = new Etage(100/getNb_etages());
            this.listeetages[i] = unetage;   
        }
        
       /* this.piecehaut = new Piece();
        this.piecebas = new Piece();
        this.piecedroite = new Piece();
        this.piecegauche = new Piece();
        */
        
    }

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
     * @return the profondeur
     */
    public float getProfondeur() {
        return profondeur;
    }

    /**
     * @param profondeur the profondeur to set
     */
    public void setProfondeur(float profondeur) {
        this.profondeur = profondeur;
    }

    /**
     * @return the nb_etages
     */
    public int getNb_etages() {
        return nb_etages;
    }

    /**
     * @param nb_etages the nb_etages to set
     */
    public void setNb_etages(int nb_etages) {
        this.nb_etages = nb_etages;
    }
    
    /**
     * 
     * @param h_relative
     */
    public void ajouteetage(float h_relative){
        Etage newetage = new Etage(h_relative);
        for( int i = 0; i < getNb_etages(); i++){
            listeetages[i].setHauteur_rel(listeetages[i].getHauteur_rel()-(h_relative/getNb_etages())); 
        }
        setNb_etages(getNb_etages()+1);
        Etage[] newlist = new Etage[getNb_etages()];
        for( int i = 0; i < getNb_etages(); i++){
            if(i == getNb_etages()-1){
                newlist[i] = newetage;
            }
            else{
                newlist[i]  = this.listeetages[i];
            }
        }
        this.listeetages = newlist;
        
   
    }
    
    /**
     * 
     */
    public void enleveetage(int etageid){
        float h_relajouter = listeetages[etageid].getHauteur_rel();
        listeetages[etageid] = null;
        for( int i = 0; i < getNb_etages() ; i++){
            if( listeetages[i] != null){
                listeetages[i].setHauteur_rel(getHauteur()+(h_relajouter/getNb_etages()-1));
            }
        }
        Etage[] newlist = new Etage[getNb_etages()-1];
        int j = 0;
        for( int i = 0; i < getNb_etages() ; i++){
            if(listeetages[i] == null){
               
            }
            else{
                newlist[j] = this.listeetages[i];
                j++;
            }
        }
        this.listeetages = newlist;
        setNb_etages(getNb_etages()-1);

        
    }
    
    
    
}
