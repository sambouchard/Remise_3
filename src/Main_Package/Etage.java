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
public class Etage {
    private float hauteur_rel;
    private Caisson[] listecaissons;
    private int Nb_Caisson;

    /**
     * @return the hauteur_rel
     */
    public float getHauteur_rel() {
        return hauteur_rel;
    }

    /**
     * @param hauteur_rel the hauteur_rel to set
     */
    public void setHauteur_rel(float hauteur_rel) {
        this.hauteur_rel = hauteur_rel;
    }

    public Etage(float h_rel) {
        this.hauteur_rel = h_rel;
    }
    /**
     * 
     */
    
    public void AjouteCaisson(){
        setNb_Caisson(getNb_Caisson()+1);
        
    }
    
    /**
     * 
     */
    public void SupprimeCaisson(){
        setNb_Caisson(getNb_Caisson()-1);
       
    }

    /**
     * @return the Nb_Caisson
     */
    public int getNb_Caisson() {
        return Nb_Caisson;
    }

    /**
     * @param Nb_Caisson the Nb_Caisson to set
     */
    public void setNb_Caisson(int Nb_Caisson) {
        this.Nb_Caisson = Nb_Caisson;
    }
    
    
    
    
}
