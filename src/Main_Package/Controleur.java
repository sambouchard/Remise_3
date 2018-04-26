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
    private double y_reference = AfficheurEtagere2D.yReference;
    private double x_reference = AfficheurEtagere2D.xReference;
    
    
    
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
    
    
    
    public void calculCoordEtage(){
        double ep = 10;
        double hauteur_disp;
        double largeur;
        double x_ref = this.x_reference;
        double y_ref = this.y_reference;
        if(this.getEtagere().isPerimetretriple() == true){
            largeur = this.getEtagere().getLargeur() - 6 * ep;
            y_ref += 3 * ep;
            x_ref += 3 * ep;
            hauteur_disp = this.getEtagere().getHauteur() - 6 * ep - this.getEtagere().getNb_etages()*3*ep;
            for( int i =0; i<this.getEtagere().getNb_etages(); i++){
                this.getEtagere().getListeetages()[i].setX1(x_ref);
                this.getEtagere().getListeetages()[i].setX2(x_ref + largeur);
                this.getEtagere().getListeetages()[i].setY1(x_ref);
                this.getEtagere().getListeetages()[i].setY2(y_ref+this.getEtagere().getListeetages()[i].getHauteur_rel()*hauteur_disp);
                y_ref += this.getEtagere().getListeetages()[i].getHauteur_rel()*hauteur_disp + 3 * ep;
                
            }
        }
        else{
            double x_ref1 = this.x_reference;
            double y_ref1 = this.y_reference;
            largeur = this.getEtagere().getLargeur() - 4 * ep;
            y_ref1 += 2 * ep;
            x_ref1 += 2 * ep;
            hauteur_disp = this.getEtagere().getHauteur() - 4 * ep - this.getEtagere().getNb_etages()*3*ep;
            for( int i =0; i<this.getEtagere().getNb_etages(); i++){
                this.getEtagere().getListeetages()[i].setX1(x_ref1);
                this.getEtagere().getListeetages()[i].setX2(x_ref1 + largeur);
                this.getEtagere().getListeetages()[i].setY1(y_ref1);
                this.getEtagere().getListeetages()[i].setY2(y_ref1+this.getEtagere().getListeetages()[i].getHauteur_rel()*hauteur_disp);
                y_ref1 += this.getEtagere().getListeetages()[i].getHauteur_rel()*hauteur_disp + 3 * ep;
                
            }
        
            
            
        }
    }

    
    public void calculCoordCaissons(){
        double ep = 10;
        double hauteur_disp;
        double largeur_dispo;
        double x_ref = this.x_reference;
        double y_ref = this.y_reference;
        if(this.getEtagere().isPerimetretriple() == true){
            x_ref += 3 * ep;
            y_ref += 3 * ep;
            hauteur_disp = this.getEtagere().getHauteur() - 6 * ep - this.getEtagere().getNb_etages()*3*ep;
            for( int i =0; i<this.getEtagere().getNb_etages(); i++){
                largeur_dispo = this.etagere.getLargeur()-6*ep - this.etagere.getListeetages()[i].getNb_Caisson()*3*ep;
                for (Caisson caisson : this.getEtagere().getListeetages()[i].getListecaissons()) {
                    caisson.setX1(x_ref);
                    caisson.setX2(x_ref + caisson.getLargeur_rel()*largeur_dispo);
                    caisson.setY1(y_ref);
                    caisson.setY2(y_ref + this.etagere.getListeetages()[i].getHauteur_rel()*hauteur_disp);
                    x_ref +=  caisson.getLargeur_rel()*largeur_dispo + 3 *ep;
                }
                y_ref += this.getEtagere().getListeetages()[i].getHauteur_rel()*hauteur_disp + 3 * ep;
                
            }
        }
        else{
            x_ref += 3 * ep;
            y_ref += 3 * ep;
            hauteur_disp = this.getEtagere().getHauteur() - 4 * ep - this.getEtagere().getNb_etages()*3*ep;
            for( int i =0; i<this.getEtagere().getNb_etages(); i++){
                largeur_dispo = this.etagere.getLargeur()-4*ep - this.etagere.getListeetages()[i].getNb_Caisson()*3*ep;
                for (Caisson caisson : this.getEtagere().getListeetages()[i].getListecaissons()) {
                    caisson.setX1(x_ref);
                    caisson.setX2(x_ref + caisson.getLargeur_rel()*largeur_dispo);
                    caisson.setY1(y_ref);
                    caisson.setY2(y_ref + this.etagere.getListeetages()[i].getHauteur_rel()*hauteur_disp);
                    x_ref +=  caisson.getLargeur_rel()*largeur_dispo + 3 *ep;
                }
                y_ref += this.getEtagere().getListeetages()[i].getHauteur_rel()*hauteur_disp + 3 * ep;
                
            }
        
            
            
        }
    }
    /**
     * @param etagere the etagere to set
     */
    public void setEtagere(Etagere etagere) {
        this.etagere = etagere;
        calculCoordEtage();
    }
    
    
    
}