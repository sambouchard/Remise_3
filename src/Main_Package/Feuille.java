/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
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

    
    public void Feuille(){
        
    }
    
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
    
    
}
