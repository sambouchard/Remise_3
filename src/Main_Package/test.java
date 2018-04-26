package Main_Package;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JFrame;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SABOU350
 */
public class test {
    public static void main(String[] args){
        Etagere etagere = new Etagere(300, 300, 300, 5, true,false,false);
        AfficheurEtagere2D afficheur = new AfficheurEtagere2D();
        afficheur.setEtagere(etagere);
        
            
            
            
        
        //etagere.getListeetages()[2].AjouteCaisson(50);
        JFrame Jf = new JFrame();
        Jf.setTitle("Test");
        Jf.setSize(6000,6000);
        Jf.setVisible(true);
        Jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Jf.add(afficheur);
        afficheur.drawing();
    }
    
}
