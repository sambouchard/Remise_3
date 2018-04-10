/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

import static Main_Package.AfficheurEtagere2D.ep3;
import static Main_Package.Etagere.largeur;
import java.awt.geom.Rectangle2D;
import java.lang.IllegalStateException;

/**
 *
 * @author SABOU350
 */
public class Etage {

    /**
     * @return the x1
     */
    public double getX1() {
        return x1;
    }

    /**
     * @param x1 the x1 to set
     */
    public void setX1(double x1) {
        this.x1 = x1;
    }

    /**
     * @return the x2
     */
    public double getX2() {
        return x2;
    }

    /**
     * @param x2 the x2 to set
     */
    public void setX2(double x2) {
        this.x2 = x2;
    }

    /**
     * @return the y1
     */
    public double getY1() {
        return y1;
    }

    /**
     * @param y1 the y1 to set
     */
    public void setY1(double y1) {
        this.y1 = y1;
    }

    /**
     * @return the y2
     */
    public double getY2() {
        return y2;
    }

    /**
     * @param y2 the y2 to set
     */
    public void setY2(double y2) {
        this.y2 = y2;
    }
    
    public Rectangle2D getRectangle() {
        Rectangle2D rect = new Rectangle2D.Double(getX1(), getY1(), getX2(), getY2());
        return rect;
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
    private double hauteur_rel;
    private Caisson[] listecaissons;
    private double x1;
    private double x2;
    private double y1;
    private double y2;
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

    public void AjouteCaisson(double l_rel) {
        Caisson newcaisson;
        newcaisson = new Caisson(l_rel);

        Caisson[] newlist = new Caisson[getNb_Caisson() + 1];
        int j = 0;
        for (int i = 0; i < getNb_Caisson() + 1; i++) {
            if (i == getNb_Caisson()) {
                newlist[i] = newcaisson;
            } else {
                newlist[i] = this.getListecaissons()[i];
            }
        }
        setNb_Caisson(getNb_Caisson() + 1);
        this.setListecaissons(newlist);

    }
    
    /**
     *
     * @param indice
     */
    public void SupprimeCaisson(int indice) {
        double larg_rel = this.getListecaissons()[indice].getLargeur_rel();
        this.getListecaissons()[indice] = null;
        Caisson[] newlist = new Caisson[getNb_Caisson() - 1];
        int j = 0;
        for (int i = 0; i < getNb_Caisson(); i++) {
            if (this.getListecaissons()[i] != null) {
                this.getListecaissons()[i].setLargeur_rel(this.getListecaissons()[i].getLargeur_rel() + (larg_rel / (getNb_Caisson() - 1)));
                newlist[j] = this.getListecaissons()[i];
                j++;
            }

        }
        setNb_Caisson(getNb_Caisson() - 1);
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

}
