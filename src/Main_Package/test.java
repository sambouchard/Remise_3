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
        Etagere etagere = new Etagere(60, 60, 60, 4, true,false,true);
        AfficheurEtagere2D afficheur = new AfficheurEtagere2D();
        afficheur.setEtagere(etagere);
        JFrame Jf = new JFrame();
        Jf.setTitle("Test");
        Jf.setSize(1000,1000);
        Jf.setVisible(true);
        Jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Jf.add(afficheur);
        
    }
    
}
