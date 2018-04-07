/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author SABOU350
 */
public class AfficheurEtagere2D extends JPanel{
    private Etagere etagere;
    private Graphics2D graphics; 

    public AfficheurEtagere2D() { 
        
    }
    
    public void dessinerCaissons(){
        for(Etage etage: etagere.getListeetages()){
            for(Caisson caisson: etage.getListecaissons()){
                
            }
            
            
            
            
            
        }
        
        
        
        
        
    }
    
    
}
