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
public class Etagere {
    private double hauteur;
    private double largeur;
    private double profondeur;
    private int nb_etages;
    private Etage[] listeetages;
    private boolean estferme;
    private boolean piecedepasse;
    private Piece piecegauche;
    private Piece piecedroite;
    private Piece piecehaut;
    private Piece piecebas;
    private Piece piecearriere;
    private boolean estepaisse;
    
    
    
    /**
     * @param hauteur
     * @param largeur
     * @param profondeur
     * @param nb_etages
     * @param estfermee
     * @create etagere object
     */
    public Etagere(double hauteur, double largeur, double profondeur, int nb_etages,boolean estfermee) {
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
        
       this.piecebas = new Piece(getProfondeur(),getLargeur(),1.5*2.54,false);
       this.piecehaut = new Piece(getProfondeur(),getLargeur(),1.5*2.54,false);
       this.piecegauche = new Piece(getHauteur(), getProfondeur(), 1.5*2.54, false);
       this.piecedroite = new Piece(getHauteur(), getProfondeur(), 1.5*2.54, false);

       
       /* this.piecehaut = new Piece();
        this.piecebas = new Piece();
        this.piecedroite = new Piece();
        this.piecegauche = new Piece();
        */
        
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
     * @return the profondeur
     */
    public double getProfondeur() {
        return profondeur;
    }

    /**
     * @param profondeur the profondeur to set
     */
    public void setProfondeur(double profondeur) {
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
    public void ajouteetage(double h_relative){
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
     * @param etageid
     */
    public void enleveetage(int etageid){
        double h_relajouter = listeetages[etageid].getHauteur_rel();
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
    
    public void ModifieHauteurRelEtage(int indice, double New_h){
        double difference = this.listeetages[indice].getHauteur_rel() - New_h;
        this.listeetages[indice].setHauteur_rel(New_h);
        if(indice == this.listeetages.length - 1){
                this.listeetages[indice - 1].setHauteur_rel(hauteur);
            
        }
        else if( 0 == indice) {
            this.listeetages[1].setHauteur_rel(this.listeetages[1].getHauteur_rel() - difference);
        }
        else{
            
        }
    }
    
    
}
