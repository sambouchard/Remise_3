/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

import static Main_Package.AfficheurEtagere2D.ep3;
import static Main_Package.Etagere.largeur;
import java.lang.IllegalStateException;

/**
 *
 * @author SABOU350
 */
public class Etage {

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
    private double hauteur_rel;
    private Caisson[] listecaissons;
    private int Nb_Caisson;
    private double espacedisponible = 1;
    boolean debordement = false;

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
        AjouteCaisson(1);
    }

    /**
     *
     * @param l_rel
     */

    public void AjouteCaisson(double l_rel){
        espacedisponible -= l_rel + 3*ep3/(largeur-6*ep3);
        Caisson newcaisson;
        if(debordement == true){
            newcaisson = new Caisson(0);
            this.listecaissons[listecaissons.length-1].setLargeur_rel(l_rel);
            for(int i=0 ; i < getNb_Caisson()-1 ; i++){
                //this.listecaissons[i].setLargeur_rel(this.listecaissons[i].getLargeur_rel()*(1-(l_rel/(getNb_Caisson()*100))));
                this.listecaissons[i].setLargeur_rel((1-l_rel)*this.listecaissons[i].getLargeur_rel());
                
            }
            
        }
        //Premier débordement, on redistribue la largeur relative manquante du caisson du bout dans le reste des caissons existants.
        else if (espacedisponible<=0){
            
            newcaisson = new Caisson(0);
            for(int i=0 ; i < getNb_Caisson()-1 ; i++){
                //this.listecaissons[i].setLargeur_rel(this.listecaissons[i].getLargeur_rel()*(1-((l_rel-this.listecaissons[listecaissons.length-1].getLargeur_rel())/(getNb_Caisson()*100))));
                this.listecaissons[i].setLargeur_rel((1-(l_rel-this.listecaissons[listecaissons.length-1].getLargeur_rel()))*this.listecaissons[i].getLargeur_rel());
            }
            this.listecaissons[listecaissons.length-1].setLargeur_rel(l_rel);
            debordement = true;
        }
        else{
            newcaisson = new Caisson(espacedisponible);
            listecaissons[listecaissons.length-1].setLargeur_rel(l_rel);
            
        }

        Caisson[] newlist  = new Caisson[getNb_Caisson()+1];
        for(int i = 0; i < getNb_Caisson() ; i++){
            newlist[i] = this.listecaissons[i];
        }
        newlist[newlist.length-1] = newcaisson;
        setNb_Caisson(getNb_Caisson()+1);
        this.listecaissons = newlist;
        
    }
    
    /**
     * 
     * @param indice
     */
    public void SupprimeCaisson(int indice){
        if (indice==0 && getNb_Caisson() == 1){
            throw new IllegalStateException("Vous ne pouvez pas supprimer le seul caisson existant.");
        }
        
        try{
            double larg_rel = this.listecaissons[indice].getLargeur_rel();
            espacedisponible += larg_rel;
            if (espacedisponible>0){
                debordement = false;
            }
            Caisson [] newlist = new Caisson[getNb_Caisson()-1];
            if(debordement == true){
                this.listecaissons[indice] = null;
                
                int j = 0;
                for(int i = 0; i < getNb_Caisson() ; i++){
                    if(this.listecaissons[i] != null){
                        this.listecaissons[i].setLargeur_rel(this.listecaissons[i].getLargeur_rel()+(larg_rel/(getNb_Caisson()-1)));
                        newlist[j] = this.listecaissons[i];
                        j++;
                    }
                }
            }
            else{
                this.listecaissons[indice] = null;
                listecaissons[listecaissons.length-1].setLargeur_rel(espacedisponible);
                int j = 0;
                for(int i = 0; i < getNb_Caisson() ; i++){
                    if(this.listecaissons[i] != null){
                        newlist[j] = this.listecaissons[i];
                        j++;
                    }
                }
            }
            setNb_Caisson(getNb_Caisson()-1);
            this.listecaissons = newlist;            
        }
        catch(ArrayIndexOutOfBoundsException e){
            throw new IllegalStateException("Ce caisson n'existe pas");
        }

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
