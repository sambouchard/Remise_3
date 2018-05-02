/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author fjlac
 */
public class Feuille {
    
    double hauteur = 243.84;
    double largeur = 121.92;
    double hauteurdispo = hauteur;
    double largeurdispo = largeur;
    double hauteurdispo2e = hauteur;
    double largeurdispo2e = largeur;
    boolean premiereligneremplie = false;
    public List<Rectangle2D> listePieces = new ArrayList();
    public List<Rectangle2D> listeEspaceLibre = new ArrayList();
    public int indexactuel;
    public List<Piece> listepiecesphysiques = new ArrayList();
    public List<Rectangle2D> tempListeEspaceLibre = new ArrayList();
    public boolean isdouble = false;

    
    public Feuille(Boolean estdouble){
        Rectangle2D.Double vide = new Rectangle2D.Double(0,0,largeur,hauteur);
        listeEspaceLibre.add(vide);
        isdouble = estdouble;
    }
    
    //La méthode a été remplacée par ajouter2()
    public boolean ajouter(Rectangle2D rect){
        boolean fits = false;
        
        if(premiereligneremplie == false){
            if ((rect.getHeight()<=hauteurdispo && rect.getWidth()<=largeurdispo)){
                fits = true;
                Rectangle2D.Double plein = new Rectangle2D.Double(largeur-largeurdispo, hauteur-hauteurdispo, rect.getWidth(),rect.getHeight());
                listePieces.add(plein);
                Rectangle2D.Double vide = new Rectangle2D.Double(largeur-largeurdispo,rect.getHeight(),largeurdispo,hauteur-rect.getHeight());
                listeEspaceLibre.add(vide);
                largeurdispo -= plein.width;
            }
            else if((rect.getWidth()<=hauteurdispo && rect.getHeight()<=largeurdispo)){
                fits = true;
                Rectangle2D.Double plein = new Rectangle2D.Double(largeur-largeurdispo, hauteur-hauteurdispo, rect.getHeight(), rect.getWidth());
                listePieces.add(plein);
                Rectangle2D.Double vide = new Rectangle2D.Double(largeur-largeurdispo,rect.getHeight(),largeurdispo,hauteur-rect.getHeight());
                listeEspaceLibre.add(vide);
                largeurdispo -= plein.width;
            }
        }    
        else{
            for (int i=listeEspaceLibre.size()-1;i>=0;i--){
                if(i>indexactuel){
                    continue;
                }
                if (rect.getWidth()<=listeEspaceLibre.get(i).getWidth() && rect.getHeight()<=listeEspaceLibre.get(i).getHeight()){
                    fits = true;
                    Rectangle2D.Double plein = new Rectangle2D.Double(largeurdispo2e-rect.getWidth(), hauteur-rect.getHeight(), rect.getWidth(), rect.getHeight());
                    listePieces.add(plein);
                    largeurdispo2e-=plein.width;
                    for (int j=listeEspaceLibre.size()-1;j>i;j--){
                        listeEspaceLibre.get(j).setRect(listeEspaceLibre.get(j).getX(), listeEspaceLibre.get(j).getY(), listeEspaceLibre.get(j).getWidth(), listeEspaceLibre.get(j).getHeight()-plein.height);
                    }
                    listeEspaceLibre.get(i).setRect(listeEspaceLibre.get(i).getX(), listeEspaceLibre.get(i).getY(), listeEspaceLibre.get(i).getWidth()-plein.width, listeEspaceLibre.get(i).getHeight());
                    for (int k=0;k<i;k++){
                        listeEspaceLibre.get(k).setRect(listeEspaceLibre.get(k).getX(), listeEspaceLibre.get(k).getY(), listeEspaceLibre.get(k).getWidth()-plein.width, listeEspaceLibre.get(k).getHeight());
                    }                    
                    indexactuel = i;
                    break;
                }
                else if(rect.getWidth()<=listeEspaceLibre.get(i).getHeight() && rect.getHeight()<=listeEspaceLibre.get(i).getWidth()){
                    fits = true;
                    Rectangle2D.Double plein = new Rectangle2D.Double(largeurdispo2e-rect.getHeight(), hauteur-rect.getWidth(), rect.getHeight(), rect.getWidth());
                    listePieces.add(plein);
                    largeurdispo2e-=plein.width;
                    for (int j=listeEspaceLibre.size()-1;j>i;j--){
                        listeEspaceLibre.get(j).setRect(listeEspaceLibre.get(j).getX(), listeEspaceLibre.get(j).getY(), listeEspaceLibre.get(j).getWidth(), listeEspaceLibre.get(j).getHeight()-plein.height);
                    }
                    listeEspaceLibre.get(i).setRect(listeEspaceLibre.get(i).getX(), listeEspaceLibre.get(i).getY(), listeEspaceLibre.get(i).getWidth()-plein.width, listeEspaceLibre.get(i).getHeight());
                    for (int k=0;k<i;k++){
                        listeEspaceLibre.get(k).setRect(listeEspaceLibre.get(k).getX(), listeEspaceLibre.get(k).getY(), listeEspaceLibre.get(k).getWidth()-plein.width, listeEspaceLibre.get(k).getHeight());
                    }   
                    indexactuel = i;
                    break;
                }
            }
        }
        
        
        return fits;
    }
    
    public boolean ajouter2(Rectangle2D rect){
        boolean fits = false;
                
        for (Rectangle2D libre: listeEspaceLibre){
            if ((rect.getHeight()<=libre.getHeight() && rect.getWidth()<=libre.getWidth())){
                fits = true;
                Rectangle2D.Double plein = new Rectangle2D.Double(libre.getX(), libre.getY(), rect.getWidth(),rect.getHeight());
                listePieces.add(plein);
                this.espaceLibre(plein);
                break;
            }
            else if((rect.getWidth()<=libre.getHeight() && rect.getHeight()<=libre.getWidth())){
                fits = true;
                Rectangle2D.Double plein = new Rectangle2D.Double(libre.getX(), libre.getY(),rect.getHeight(),rect.getWidth());
                listePieces.add(plein);
                this.espaceLibre(plein);                
                break;
            }
            
        }

        return fits;
    }
    
    public void espaceLibre(Rectangle2D plein){
        tempListeEspaceLibre.clear();
        for (Rectangle2D libre: listeEspaceLibre){
            Rectangle2D intersect = libre.createIntersection(plein);
            if(intersect.getY()<0 || intersect.getX()<0 || intersect.getHeight()<0 || intersect.getWidth()<0){
                tempListeEspaceLibre.add(libre);
                continue;
            }
            else{
                if (libre.getY()!=intersect.getY()){
                    Rectangle2D libre1 = new Rectangle2D.Double(libre.getX(),libre.getY(),libre.getWidth(),intersect.getY()-libre.getY());
                    tempListeEspaceLibre.add(libre1);
                }
                if (libre.getX()!=intersect.getX()){
                    Rectangle2D libre2 = new Rectangle2D.Double(libre.getX(),libre.getY(),intersect.getX()-libre.getX(),libre.getHeight());
                    tempListeEspaceLibre.add(libre2);
                }
                if(intersect.getMaxX()!= libre.getMaxX()){
                    Rectangle2D libre3 = new Rectangle2D.Double(intersect.getMaxX(),libre.getY(),libre.getMaxX()-intersect.getMaxX(),libre.getHeight());
                    tempListeEspaceLibre.add(libre3);
                }
                if(intersect.getMaxY()!=libre.getMaxY()){
                    Rectangle2D libre4 = new Rectangle2D.Double(libre.getX(),intersect.getMaxY(),libre.getWidth(),libre.getMaxY()-intersect.getMaxY());
                    tempListeEspaceLibre.add(libre4);
                }
            }
        }
        Collections.sort(tempListeEspaceLibre, new Comparator<Rectangle2D>() {
            @Override
            public int compare(Rectangle2D p1, Rectangle2D p2) {
                double[] p1valeurs = {p1.getHeight(),p1.getWidth()};
                double[] p2valeurs = {p2.getHeight(),p2.getWidth()};
                Arrays.sort(p1valeurs);
                Arrays.sort(p2valeurs);
                int valeur = Double.compare(-p1valeurs[p1valeurs.length-1], -p2valeurs[p2valeurs.length-1]);
                if (valeur == 0){
                    return Double.compare(-p1valeurs[p1valeurs.length-2], -p2valeurs[p2valeurs.length-2]);
                }
                return valeur;
            }
        });

        listeEspaceLibre = new ArrayList(tempListeEspaceLibre);
    }
    
//    public static void main(String []argv){
//        Rectangle2D libre = new Rectangle2D.Double(0,0,10,10);
//        Rectangle2D libre2 = new Rectangle2D.Double(20,20,100,100);
//        Rectangle2D intersect = libre.createIntersection(libre2);
//        
//    }
}
