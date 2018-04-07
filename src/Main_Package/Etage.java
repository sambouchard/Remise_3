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


    private double hauteur_rel;
    private Caisson[] listecaissons;
    private int Nb_Caisson;

    
    /**
     * @return the hauteur_rel
     */
    public double getHauteur_rel() {
        return hauteur_rel;
    }

    /**
     * @param hauteur_rel the hauteur_rel to set
     */
    public void setHauteur_rel(double hauteur_rel) {
        this.hauteur_rel = hauteur_rel;
    }
    
    /**
     * 
     * @param h_rel 
     */
    Etage(double h_rel) {
        this.hauteur_rel = h_rel;
    }
    /**
     * 
     * @param l_rel
     */

    public void AjouteCaisson(double l_rel){
        Caisson newcaisson;
        newcaisson = new Caisson(l_rel);
        Caisson[] newlist  = new Caisson[getNb_Caisson()+1];
        int j = 0;
        for(int i = 0; i < getNb_Caisson()+1 ; i++){
            if(i == getNb_Caisson()){
                newlist[i] = newcaisson;
            }
            else{
                newlist[i] = this.getListecaissons()[i];
            }
        }
        setNb_Caisson(getNb_Caisson()+1);
        this.setListecaissons(newlist);
        
    }
    
    /**
     * 
     * @param indice
     */
    public void SupprimeCaisson(int indice){
        double larg_rel = this.getListecaissons()[indice].getLargeur_rel();
        this.getListecaissons()[indice] = null;
        Caisson [] newlist = new Caisson[getNb_Caisson()-1];
        int j = 0;
        for(int i = 0; i < getNb_Caisson() ; i++){
            if(this.getListecaissons()[i] != null){
                this.getListecaissons()[i].setLargeur_rel(this.getListecaissons()[i].getLargeur_rel()+(larg_rel/getNb_Caisson()-1));
                newlist[j] = this.getListecaissons()[i];
                j++;
            }
            
        }
        setNb_Caisson(getNb_Caisson()-1);
        this.setListecaissons(newlist);
        
       
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
    
    /**
     * @return the listecaissons
     */
    public Caisson[] getListecaissons() {
        return listecaissons;
    }

    /**
     * @param listecaissons the listecaissons to set
     */
    public void setListecaissons(Caisson[] listecaissons) {
        this.listecaissons = listecaissons;
    }
    
    
}
