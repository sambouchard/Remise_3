/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author SABOU350
 */
public class Etagere {
    private double hauteur;
    static public double largeur;
    private double profondeur;
    private int nb_etages;
    private Etage[] listeetages;
    private boolean estferme;
    private boolean piecedepasse;
    private List<Piece> Liste_piece;
    private boolean perimetretriple;
    private double epaisseurTriple = 0.5 * 2.54;
    private double epaisseurDouble = 0.75 * 2.54;
    
    
    
    /**
     * @param hauteur
     * @param largeur
     * @param profondeur
     * @param nb_etages
     * @param estfermee
     * @create etagere object
     */
    public Etagere(double hauteur, double largeur, double profondeur, int nb_etages,
            boolean estfermee,boolean piecedepasse,boolean perimetretriple) {
        setHauteur(hauteur);
        setLargeur(largeur);
        setProfondeur(profondeur);
        setNb_etages(nb_etages);
        setPiecedepasse(piecedepasse);
        setPerimetretriple(perimetretriple);
        this.estferme = estfermee;
        this.listeetages = new Etage[this.nb_etages];

        for(int i = 0; i < getNb_etages() ; i++){
            Etage unetage = new Etage(1.0/this.getNb_etages());
            this.listeetages[i] = unetage;   
        }
        this.Liste_piece = new ArrayList();
        GenererPieces();
        
        
        
        
    }

    /**
     * @return the hauteur
     */
    public double getHauteur() {
        return hauteur;
    }
    
    public void GenererPieces(){
        Liste_piece.clear();
        if(isPerimetretriple()){
            GenererPiecePerimetreBasTriple();
            GenererPiecesPerimetreHautTriple();
            GenererPiecesPerimetreCote();
        }
        else if(!(isPerimetretriple())){
            GenererPiecesPerimetreBasDouble();
            GenererPiecsePerimetreHautDouble();
            GenererPiecesPerimetreCote();
        }
        GenererPiecesEtagesHorizontale();
    }
    
    
    
    private void GenererPiecesPerimetreBasDouble(){
        Piece piecebas = new Piece(epaisseurDouble,this.largeur - 2 *epaisseurDouble,this.profondeur,"Piece du bas");
        this.Liste_piece.add(piecebas);     
    }
    
    
    private void GenererPiecePerimetreBasTriple(){
        Piece piecebas = new Piece(epaisseurTriple,this.largeur - 2* (epaisseurTriple),this.profondeur,"Piece du bas1");
        Piece pieceba2 = new Piece(epaisseurTriple,this.largeur - 4 *(epaisseurTriple),this.profondeur,"Piece du bas2");
        this.Liste_piece.add(piecebas);
        this.Liste_piece.add(pieceba2);

    }
    
    
    
    private void GenererPiecsePerimetreHautDouble(){
        if(isPiecedepasse()){
            Piece piecehaut = new Piece(epaisseurDouble,this.largeur,this.profondeur,"Piece du haut");
            this.Liste_piece.add(piecehaut);
        }
        else if(!(isPiecedepasse())){
            Piece piecehaut = new Piece(epaisseurDouble,this.largeur-2*epaisseurDouble,this.profondeur,"Piece du haut");
            this.Liste_piece.add(piecehaut);
        }
    }
    
    
    
    private void GenererPiecesPerimetreHautTriple(){
        if(isPiecedepasse()){
            Piece piecehaut = new Piece(epaisseurTriple,this.largeur,this.profondeur,"Piece du haut1");
            Piece piecehaut1 = new Piece(epaisseurTriple,this.largeur - 2*(0.5*2.54),this.profondeur,"Piece du haut2");
            this.Liste_piece.add(piecehaut);
            this.Liste_piece.add(piecehaut1);
            
        }
        else if(!(isPiecedepasse())){
            Piece piecehaut = new Piece(epaisseurTriple,this.largeur - 2*(epaisseurTriple),this.profondeur,"Piece du haut1");
            Piece piecehaut1 = new Piece(epaisseurTriple,this.largeur - 2*(epaisseurTriple),this.profondeur,"Piece du haut2");
            this.Liste_piece.add(piecehaut);
            this.Liste_piece.add(piecehaut1);
        }
    }
    
    private void GenererPiecesPerimetreCote(){
        double epaisseur = 0;
        if(isPerimetretriple()){
            epaisseur = epaisseurTriple;
        }
        else if(!(isPerimetretriple())){
            epaisseur = epaisseurDouble;
        }
        if(isPiecedepasse()){
            Piece piecegauche = new Piece(this.hauteur - epaisseur, epaisseur, this.profondeur, "Piece de gauche");
            Piece piecedroite = new Piece(this.hauteur - epaisseur, epaisseur, this.profondeur, "Piece de droite");
            this.Liste_piece.add(piecegauche);
            this.Liste_piece.add(piecedroite);
        }
        else if(!(isPiecedepasse())){
            Piece piecegauche = new Piece(this.hauteur, epaisseur, this.profondeur, "Piece de gauche");
            Piece piecedroite = new Piece(this.hauteur, epaisseur, this.profondeur, "Piece de droite");
            this.Liste_piece.add(piecedroite);
            this.Liste_piece.add(piecegauche);
        }
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
            getListeetages()[i].setHauteur_rel(getListeetages()[i].getHauteur_rel()-(h_relative/getNb_etages())); 
        }
        setNb_etages(getNb_etages()+1);
        Etage[] newlist = new Etage[getNb_etages()];
        for( int i = 0; i < getNb_etages(); i++){
            if(i == getNb_etages()-1){
                newlist[i] = newetage;
            }
            else{
                newlist[i]  = this.getListeetages()[i];
            }
        }
        this.setListeetages(newlist);
        
   
    }
    
    /**
     * 
     * @param etageid
     */
    public void enleveetage(int etageid){
        double h_relajouter = getListeetages()[etageid].getHauteur_rel();
        getListeetages()[etageid] = null;
        for( int i = 0; i < getNb_etages() ; i++){
            if( getListeetages()[i] != null){
                getListeetages()[i].setHauteur_rel(getHauteur()+(h_relajouter/(getNb_etages()-1)));
            }
        }
        Etage[] newlist = new Etage[getNb_etages()-1];
        int j = 0;
        for( int i = 0; i < getNb_etages() ; i++){
            if(getListeetages()[i] == null){
               
            }
            else{
                newlist[j] = this.getListeetages()[i];
                j++;
            }
        }
        this.setListeetages(newlist);
        setNb_etages(getNb_etages()-1);

        
    }
    
    public void ModifieHauteurRelEtage(int indice, double New_h){
        double difference = this.getListeetages()[indice].getHauteur_rel() - New_h;
        this.getListeetages()[indice].setHauteur_rel(New_h);
        if(indice == this.getListeetages().length - 1){
            this.getListeetages()[indice - 1].setHauteur_rel(hauteur);
            
        }
        else if( 0 == indice) {
            this.getListeetages()[1].setHauteur_rel(this.getListeetages()[1].getHauteur_rel() - difference);
        }
        else{
            
        }
    }

    /**
     * @return the listeetages
     */
    public Etage[] getListeetages() {
        return listeetages;
    }

    /**
     * @param listeetages the listeetages to set
     */
    public void setListeetages(Etage[] listeetages) {
        this.listeetages = listeetages;
    }

    /**
     * @return the piecedepasse
     */
    public boolean isPiecedepasse() {
        return piecedepasse;
    }

    /**
     * @param piecedepasse the piecedepasse to set
     */
    public void setPiecedepasse(boolean piecedepasse) {
        this.piecedepasse = piecedepasse;
    }

    /**
     * @return the perimetretriple
     */
    public boolean isPerimetretriple() {
        return perimetretriple;
    }

    /**
     * @param perimetretriple the perimetretriple to set
     */
    public void setPerimetretriple(boolean perimetretriple) {
        this.perimetretriple = perimetretriple;
    }

    /**
     * @return the estferme
     */
    public boolean isEstferme() {
        return estferme;
    }

    /**
     * @param estferme the estferme to set
     */
    public void setestferme(boolean estferme) {
        this.estferme = estferme;
    }

    private void GenererPiecesEtagesHorizontale() {
        int compteuretages = 0;
        if(isPerimetretriple()){
             for(int i = 0; i < this.getNb_etages() - 1; i++){ 
                 
            }
            
        }
        if(!(isPerimetretriple())){
            for(int i = 0; i < this.getNb_etages() - 1; i++){ 
            
            }
        }
    }  
}

