/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author fjlac
 */
public class Export extends JPanel {
    static double fac = 5;
    double xref = 0;
    double yref = 0;
    static List<Feuille> listeFeuilles = new ArrayList();
    static List<Piece> listePiecesAjoutees = new ArrayList();
    static int numerofeuille = 0;
    static double largeur = 121.92;
    static double hauteur = 243.84;
    static List<Piece> templistePiecesAjoutees = new ArrayList();
    static List<List<Piece>> listedelistepieces = new ArrayList();

    
    public void Export(){
        
    }
      
    public static void genererPanneauxArrieres(){
            //Créer des pièces 
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D rectangle2 = new Rectangle2D.Double(xref,yref,fac*121.92,fac*243.84);
        g2d.setBackground(Color.WHITE);
        g2d.setColor(Color.BLACK);
        g2d.draw(rectangle2);
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fill(rectangle2);
        g2d.setColor(Color.WHITE);
        Rectangle2D blanc = new Rectangle2D.Double(fac*121.92,yref,200,fac*243.84);
        g2d.draw(blanc);
        g2d.fill(blanc);
        int index = 0;
        int indextexte = 1;
        for(Rectangle2D rect: listeFeuilles.get(numerofeuille).listePieces){
            g2d.setColor(Color.BLACK);
            String label;
            String nom = listeFeuilles.get(numerofeuille).listepiecesphysiques.get(index).getNom();
            if (Controleur.getInstance().getMesureMetrique()){
                //Label en métrique
                label = String.format("%.2f", rect.getWidth()) + " x " + String.format("%.2f",rect.getHeight()) + " cm";
            }
            else{
                //label en impérial
                label = String.format( "%.2f", rect.getWidth()/2.54 ) + " x " + String.format("%.2f",rect.getHeight()/2.54) + " po";
            }

            rect.setRect(fac*rect.getX(), fac*rect.getY(), fac*rect.getWidth(), fac*rect.getHeight());
            g2d.draw(rect);
//            g2d.setColor(Color.RED);
//            g2d.fill(rect);
            if (rect.getWidth()<g.getFontMetrics().stringWidth(nom)|| rect.getWidth()<g.getFontMetrics().stringWidth(label)){
                g2d.drawString(Integer.toString(indextexte), (int) rect.getX()+g.getFontMetrics().getHeight(), (int) rect.getY()+g.getFontMetrics().getHeight());
                g2d.drawString(Integer.toString(indextexte)+ ": " + nom, (int)Math.ceil(largeur*fac) + g.getFontMetrics().getHeight(), (int) ((indextexte*2.5)*g.getFontMetrics().getHeight()));
                g2d.drawString(label, (int)Math.ceil(largeur*fac) + g.getFontMetrics().getHeight(), (int) ((indextexte*2.5+1)*g.getFontMetrics().getHeight()));
                indextexte ++;
            }
            else{
                g2d.drawString(nom, (int) rect.getX()+1, (int) rect.getY()+g.getFontMetrics().getHeight());
                g2d.drawString(label, (int) rect.getX()+1, (int) rect.getY()+2*g.getFontMetrics().getHeight());
            }
            index ++;
        }
        
        
    }    

    public static void genererPlanDeCoupe(File file){
        listeFeuilles.clear();
        listePiecesAjoutees.clear();
        List<Piece> listePieces = new ArrayList(Controleur.getInstance().getEtagere().getListe_piece());
        
        Collections.sort(listePieces, new Comparator<Piece>() {
            @Override
            public int compare(Piece p1, Piece p2) {
                double[] p1valeurs = {p1.getHauteur(),p1.getLargeur(),p1.getProfondeur()};
                double[] p2valeurs = {p2.getHauteur(),p2.getLargeur(),p2.getProfondeur()};
                Arrays.sort(p1valeurs);
                Arrays.sort(p2valeurs);
                return Double.compare(-p1valeurs[p1valeurs.length-1], -p2valeurs[p2valeurs.length-1]);
            }
        });
                   
        
        //Si périmètre double, on doit avoir deux épaisseurs distinctes pour le périmètre et pour le reste des pièces.
        if (!Controleur.getInstance().getEtagere().isPerimetretriple()){
            //Pièces du périmètre les identifier par leur nom
            List<Piece> listePerimetre = new ArrayList();
            for (Piece piece: listePieces){
                String nom = piece.getNom();
                if(piece.getHauteur() == Controleur.getInstance().getEtagere().getEpaisseurDouble() || piece.getLargeur() == Controleur.getInstance().getEtagere().getEpaisseurDouble()){
                    listePerimetre.add(piece);
                    listePieces.remove(piece);
                }
            }
            //Les pièces du périmètre double doivent être traitées séparément car leur épaisseur est différente.
            boolean feuillesremplies = false;
            while(feuillesremplies == false){

                Feuille feuille = new Feuille();
                listeFeuilles.add(feuille);
                for(int i=0;i<2;i++){
                    for(Piece piece: listePerimetre){

                        double[] valeurs = {piece.getHauteur(),piece.getLargeur(),piece.getProfondeur()};
                        Arrays.sort(valeurs);

                        Rectangle2D.Double rect = new Rectangle2D.Double(0,0,valeurs[1],valeurs[2]);
                        if(feuille.ajouter(rect)){
                            listePiecesAjoutees.add(piece);
                        }
                    }
                    feuille.premiereligneremplie=true;
                    feuille.indexactuel = feuille.listeEspaceLibre.size()-1;

                    for(Piece piece:listePiecesAjoutees){
                        listePerimetre.remove(piece);
                    }
                    
                    for (Piece pieceajoute:listePiecesAjoutees){
                        feuille.listepiecesphysiques.add(pieceajoute);
                    }
                    
                    listePiecesAjoutees.clear();
                    

                    if(listePerimetre.isEmpty()){
                        feuillesremplies = true;
                        break;
                    }
                }
            }            
            //On traite ensuite les pièces normales.
            feuillesremplies = false;
            while(feuillesremplies == false){

                Feuille feuille = new Feuille();
                listeFeuilles.add(feuille);
                for(int i=0;i<2;i++){
                    for(Piece piece: listePieces){

                        double[] valeurs = {piece.getHauteur(),piece.getLargeur(),piece.getProfondeur()};
                        Arrays.sort(valeurs);

                        Rectangle2D.Double rect = new Rectangle2D.Double(0,0,valeurs[1],valeurs[2]);
                        if(feuille.ajouter(rect)){
                            listePiecesAjoutees.add(piece);
                        }
                    }
                    feuille.premiereligneremplie=true;
                    feuille.indexactuel = feuille.listeEspaceLibre.size()-1;

                    for(Piece piece:listePiecesAjoutees){
                        listePieces.remove(piece);
                    }
                    for (Piece pieceajoute:listePiecesAjoutees){
                        feuille.listepiecesphysiques.add(pieceajoute);
                    }
                    listePiecesAjoutees.clear();

                    if(listePieces.isEmpty()){
                        feuillesremplies = true;
                        break;
                    }
                }
            }

        }
        else{//Pour les étagères au périmètre triple.
            boolean feuillesremplies = false;
            while(feuillesremplies == false){
                
                Feuille feuille = new Feuille();
                listeFeuilles.add(feuille);
                for(int i=0;i<2;i++){
                    for(Piece piece: listePieces){

                        double[] valeurs = {piece.getHauteur(),piece.getLargeur(),piece.getProfondeur()};
                        Arrays.sort(valeurs);

                        Rectangle2D.Double rect = new Rectangle2D.Double(0,0,valeurs[1],valeurs[2]);
                        if(feuille.ajouter(rect)){
                            listePiecesAjoutees.add(piece);
                        }
                    }
                    feuille.premiereligneremplie=true;
                    feuille.indexactuel = feuille.listeEspaceLibre.size()-1;

                    for(Piece piece:listePiecesAjoutees){
                        listePieces.remove(piece);
                    }
                    for (Piece pieceajoute:listePiecesAjoutees){
                        feuille.listepiecesphysiques.add(pieceajoute);
                    }
                    
                    listePiecesAjoutees.clear();

                    if(listePieces.isEmpty()){
                        feuillesremplies = true;
                        break;
                    }
                }
            }
        }
        Export export = new Export();
        export.setBackground(Color.WHITE);
        JFrame frame = new JFrame("Plan de coupe");
        frame.setBackground(Color.WHITE);
        frame.add(export);
        frame.setSize( (int)Math.ceil(largeur*fac)+200, (int)Math.ceil(hauteur*fac));
        //frame.setVisible(true);
        
        for (numerofeuille = 0;numerofeuille<listeFeuilles.size();numerofeuille++){
            export.save(numerofeuille, file);
        }
        
        
    }
    
    
    public void save(int numero, File file){
        BufferedImage image = new BufferedImage((int)Math.ceil(largeur*fac)+200, (int)Math.ceil(hauteur*fac), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        print(g2);
        g2.dispose();
        try { 
            int nombre = numero +1;
            ImageIO.write(image, "png", new File(file+ "Feuille" + nombre + ".png")); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
}
