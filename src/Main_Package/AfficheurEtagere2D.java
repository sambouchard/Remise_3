/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;


/**
 *
 * @author SABOU350
 */
public class AfficheurEtagere2D extends JPanel  {

    private Etagere etagere;
    private boolean shouldClear;
    static public double xReference = 100;
    static public double yReference = 100;
    private double ep2 = 15; // Épaisseur d'une des deux planches du périmètre double
    static double ep3 = 10; // Épaisseur d'une des trois planches du périmètre triple.
    private double scale = 1.0;
    private AffineTransform tx = new AffineTransform();
    private Graphics2D g2d;

    private List<Rectangle2D.Double> rectliste = new ArrayList();

    public AfficheurEtagere2D() {
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                for(Piece piece: etagere.getListe_piece()){
                    if(piece.contains(me.getX(), me.getY())){
                        System.out.println(piece.getNom());
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
                
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                
            }

            @Override
            public void mouseEntered(MouseEvent me) {
               
            }

            @Override
            public void mouseExited(MouseEvent me) {
                
            }
        });
    }

    public void drawing() {
        getRectliste().clear();
        repaint();
    }

    /**
     *
     * @param e
     */
   
   

        //@Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {

                Point2D p1 = e.getPoint();
                Point2D p2 = null;
                try {
                    p2 = tx.inverseTransform(p1, null);
                } catch (NoninvertibleTransformException ex) {
                    return;
                }

                scale -= (0.1 * e.getWheelRotation());
                scale = Math.max(0.1, scale);

                tx.setToIdentity();
                tx.translate(p1.getX(), p1.getY());
                tx.scale(scale, scale);
                tx.translate(-p2.getX(), -p2.getY());
                
                
                g2d.setColor(Color.yellow);
                
                    for (Rectangle2D.Double rectangle : this.getRectliste()) {
                        System.out.println("Main_Package.AfficheurEtagere2D.mouseWheelMoved()");
                g2d.clearRect((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
                g2d.draw(tx.createTransformedShape(rectangle));
            }       
                
               //clearView(); 
               this.repaint();
                
                
            }
        }
        
        public void clearView() {
            shouldClear = true;    
            this.repaint();;
        }
    /**
     * 
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g2d = (Graphics2D) g;
        if (shouldClear) {
            g2d.dispose();
            shouldClear = false;
        }
        if (getEtagere().isPerimetretriple() == true) {
            paint_ptrible(g2d);
            paint_pbas_triple(g2d);

        } else if (getEtagere().isPerimetretriple() == false) {
            paint_pdouble(g2d);
            paint_pbas_double(g2d);

        }

        dessinerEtages(g2d);
        dessinerCaissons(g2d);
        for(Piece piece : this.etagere.getListe_piece()){
            piece.setRect((piece.getDrawingcoin().getCoord_x())*10+200, (piece.getDrawingcoin().getCoord_y())*10+200, piece.getLargeur()*10, piece.getHauteur()*10);
            g2d.setColor(Color.BLACK);
            g2d.draw(piece);
            g2d.setColor(Color.BLUE);
            g2d.fill(piece);
        }
        
        for (Rectangle2D.Double rectangle : this.getRectliste()) {
            g2d.setColor(Color.RED);
            g2d.fill(tx.createTransformedShape(rectangle));
            g2d.setColor(Color.BLACK);
            g2d.draw(rectangle);
                    
        }

    }

    public void paint_pbas_triple(Graphics2D g2d) {
        double ep = 10;
        Etage etagecourant = getEtagere().getListeetages()[getEtagere().getListeetages().length - 1];
        double l_courante_dispo = getEtagere().getLargeur() - 6 * ep - (etagecourant.getNb_Caisson() - 1) * 3 * ep;
        double longueur_piece;
        double start_y = yReference + getEtagere().getHauteur() - 2 * ep;
        Rectangle2D.Double bottom1 = new Rectangle2D.Double(xReference + ep, yReference + getEtagere().getHauteur() - ep, getEtagere().getLargeur() - 2 * ep, ep);
        getRectliste().add(bottom1);

        

        double plank_start_x = xReference + 2 * ep;
        for (int k = 0; k < etagecourant.getListecaissons().length; k++) {
            longueur_piece = etagecourant.getListecaissons()[k].getLargeur_rel() * l_courante_dispo;
            if (k == 0) {
                longueur_piece += ep;
                Rectangle2D.Double newrect = new Rectangle2D.Double(plank_start_x, start_y, longueur_piece, ep);
                Rectangle2D.Double newrect1 = new Rectangle2D.Double(plank_start_x + ep, start_y - ep, longueur_piece - ep, ep);
                
                getRectliste().add(newrect);
                getRectliste().add(newrect1);
                
                plank_start_x += longueur_piece + 3 * ep;
            } else if (k == etagecourant.getListecaissons().length - 1) {
                longueur_piece += ep;
                Rectangle2D.Double newrect = new Rectangle2D.Double(plank_start_x, start_y, longueur_piece, ep);
                Rectangle2D.Double newrect1 = new Rectangle2D.Double(plank_start_x, start_y - ep, longueur_piece - ep, ep);
                
                getRectliste().add(newrect);
                getRectliste().add(newrect1);
                
            } else {
                
                Rectangle2D.Double newrect = new Rectangle2D.Double(plank_start_x, start_y, longueur_piece, ep);
                Rectangle2D.Double newrect1 = new Rectangle2D.Double(plank_start_x, start_y - ep, longueur_piece, ep);
                getRectliste().add(newrect);
                getRectliste().add(newrect1);
                
                plank_start_x += longueur_piece + 3 * ep;

            }
        }

    }

    public void paint_pbas_double(Graphics2D g2d) {
        double ep = 10;
        Etage etagecourant = getEtagere().getListeetages()[getEtagere().getListeetages().length - 1];
        double l_disponible;
        double l_courante_dispo = getEtagere().getLargeur() - 4 * ep - (etagecourant.getNb_Caisson() - 1) * 3 * ep;
        double longueur_piece;
        double start_y = yReference + getEtagere().getHauteur() - 2 * ep;

        Rectangle2D.Double bottom1 = new Rectangle2D.Double(xReference + ep, yReference + getEtagere().getHauteur() - ep, getEtagere().getLargeur() - 2 * ep, ep);
        
        getRectliste().add(bottom1);

        double plank_start_x = xReference + 2 * ep;
        for (int k = 0; k < etagecourant.getListecaissons().length; k++) {
            longueur_piece = etagecourant.getListecaissons()[k].getLargeur_rel() * l_courante_dispo;
            if (k == 0) {
                
                Rectangle2D.Double newrect = new Rectangle2D.Double(plank_start_x, start_y, longueur_piece, ep);
                getRectliste().add(newrect);
                plank_start_x += longueur_piece + 3 * ep;
                
            } else if (k == etagecourant.getListecaissons().length - 1) {
                Rectangle2D.Double newrect = new Rectangle2D.Double(plank_start_x, start_y, longueur_piece, ep);
                
                getRectliste().add(newrect);
                
            } else {
                Rectangle2D.Double newrect = new Rectangle2D.Double(plank_start_x, start_y, longueur_piece, ep);
                
                getRectliste().add(newrect);
                
                plank_start_x += longueur_piece + 3 * ep;

            }
        }

    }

    public void paint_pdouble(Graphics2D g2d) {
        double ep = 10;
        double longueur_piece;

        if (this.getEtagere().isPiecedepasse() == true) {
            Rectangle2D.Double top1 = new Rectangle2D.Double(xReference, yReference, getEtagere().getLargeur(), ep);
            getRectliste().add(top1);
        } else if (this.getEtagere().isPiecedepasse() == false) {
            Rectangle2D.Double top1 = new Rectangle2D.Double(xReference + ep, yReference, getEtagere().getLargeur() - 2 * ep, ep);
            
            getRectliste().add(top1);
        }
        double start_y2 = yReference + ep;
        double start_x2 = xReference + ep;
        double largeur_disponible;
        largeur_disponible = this.getEtagere().getLargeur() - 4 * ep - (this.getEtagere().getListeetages()[0].getNb_Caisson() - 1) * 3 * ep;
        for (int i = 0; i < this.getEtagere().getListeetages()[0].getListecaissons().length; i++) {
            if (i == 0) {
                longueur_piece = this.getEtagere().getListeetages()[0].getListecaissons()[i].getLargeur_rel() * largeur_disponible + 2 * ep;
                Rectangle2D.Double newrect1 = new Rectangle2D.Double(start_x2, start_y2, longueur_piece, ep);
                
                getRectliste().add(newrect1);
                start_x2 += longueur_piece + ep;
                
            } else if (i == this.getEtagere().getListeetages()[0].getListecaissons().length - 1) {
                Rectangle2D.Double newrect1 = new Rectangle2D.Double(start_x2, start_y2, this.getEtagere().getListeetages()[0].getListecaissons()[i].getLargeur_rel() * largeur_disponible + 2 * ep, ep);
                
                getRectliste().add(newrect1);
                
                start_x2 += 3 * ep + this.getEtagere().getListeetages()[0].getListecaissons()[i].getLargeur_rel() * largeur_disponible;

            } else {
                Rectangle2D.Double newrect1 = new Rectangle2D.Double(start_x2, start_y2, this.getEtagere().getListeetages()[0].getListecaissons()[i].getLargeur_rel() * largeur_disponible + 2 * ep, ep);
                getRectliste().add(newrect1);
                start_x2 += 3 * ep + this.getEtagere().getListeetages()[0].getListecaissons()[i].getLargeur_rel() * largeur_disponible;

            }
        }
        g2d.setColor(Color.RED);
        if (this.getEtagere().isPiecedepasse() == true) {
            Rectangle2D.Double left1 = new Rectangle2D.Double(xReference, yReference + ep, ep, getEtagere().getHauteur() - ep);
            Rectangle2D.Double right1 = new Rectangle2D.Double(xReference + getEtagere().getLargeur() - ep, yReference + ep, ep, getEtagere().getHauteur() - ep);
            
            getRectliste().add(left1);
            getRectliste().add(right1);
            
        } else if (this.getEtagere().isPiecedepasse() == false) {
            Rectangle2D.Double left1 = new Rectangle2D.Double(xReference, yReference, ep, getEtagere().getHauteur());
            Rectangle2D.Double right1 = new Rectangle2D.Double(xReference + getEtagere().getLargeur() - ep, yReference, ep, getEtagere().getHauteur());
            
            getRectliste().add(left1);
            getRectliste().add(right1);
        }

        double h_disponible = this.getEtagere().getHauteur() - 4 * ep - (this.getEtagere().getNb_etages() - 1) * 3 * ep;
        double start_y = yReference + 2 * ep;
        g2d.setColor(Color.RED);
        for (int i = 0; i < this.getEtagere().getNb_etages(); i++) {
            if (i == this.getEtagere().getNb_etages() - 1) {
                Rectangle2D.Double newrect = new Rectangle2D.Double(xReference + ep, start_y, ep, (this.getEtagere().getListeetages()[i].getHauteur_rel() * h_disponible) + ep);
                
                getRectliste().add(newrect);
            } else {
                Rectangle2D.Double newrect = new Rectangle2D.Double(xReference + ep, start_y, ep, (this.getEtagere().getListeetages()[i].getHauteur_rel() * h_disponible));
                start_y += (this.getEtagere().getListeetages()[i].getHauteur_rel() * h_disponible) + 3 * ep;
                
                getRectliste().add(newrect);
            }

        }
        start_y = yReference + 2 * ep;
        for (int i = 0; i < this.getEtagere().getNb_etages(); i++) {
            if (i == this.getEtagere().getNb_etages() - 1) {
                Rectangle2D.Double newrect = new Rectangle2D.Double(xReference + getEtagere().getLargeur() - 2 * ep, start_y, ep, (this.getEtagere().getListeetages()[i].getHauteur_rel() * h_disponible) + ep);
                
                getRectliste().add(newrect);
                
            } else {
                Rectangle2D.Double newrect = new Rectangle2D.Double(xReference + getEtagere().getLargeur() - 2 * ep, start_y, ep, (this.getEtagere().getListeetages()[i].getHauteur_rel() * h_disponible));
                start_y += (this.getEtagere().getListeetages()[i].getHauteur_rel() * h_disponible) + 3 * ep;
                
                getRectliste().add(newrect);

            }

        }

    }

    public void paint_ptrible(Graphics2D g2d) {
        double ep = 10;
        double longueur_piece;

        g2d.setColor(Color.BLUE);

        if (this.getEtagere().isPiecedepasse() == true) {
            Rectangle2D.Double top1 = new Rectangle2D.Double(xReference, yReference, getEtagere().getLargeur(), ep);
            g2d.fill(top1);
            g2d.setColor(Color.BLACK);
            g2d.draw(top1);
            g2d.setColor(Color.BLUE);
        } else if (this.getEtagere().isPiecedepasse() == false) {
            Rectangle2D.Double top1 = new Rectangle2D.Double(xReference + ep, yReference, getEtagere().getLargeur() - 2 * ep, ep);
            g2d.fill(top1);
            g2d.setColor(Color.BLACK);
            g2d.draw(top1);
            g2d.setColor(Color.BLUE);
        }
        Rectangle2D.Double top2 = new Rectangle2D.Double(xReference + ep, yReference + ep, getEtagere().getLargeur() - 2 * ep, ep);
        double start_y2 = yReference + 2 * ep;
        double start_x2 = xReference + ep;
        double largeur_disponible = this.getEtagere().getLargeur() - 6 * ep - (this.getEtagere().getListeetages()[0].getNb_Caisson() - 1) * 3 * ep;
        for (int i = 0; i < this.getEtagere().getListeetages()[0].getListecaissons().length; i++) {
            if (i == 0) {
                longueur_piece = this.getEtagere().getListeetages()[0].getListecaissons()[i].getLargeur_rel() * largeur_disponible + 3 * ep;
                Rectangle2D.Double newrect1 = new Rectangle2D.Double(start_x2, start_y2, longueur_piece, ep);
                g2d.fill(newrect1);
                g2d.setColor(Color.BLACK);
                g2d.draw(newrect1);
                g2d.setColor(Color.BLUE);
                start_x2 += longueur_piece + ep;
            } else if (i == this.getEtagere().getListeetages()[0].getListecaissons().length - 1) {
                Rectangle2D.Double newrect1 = new Rectangle2D.Double(start_x2, start_y2, this.getEtagere().getListeetages()[0].getListecaissons()[i].getLargeur_rel() * largeur_disponible + 3 * ep, ep);
                g2d.fill(newrect1);
                g2d.setColor(Color.BLACK);
                g2d.draw(newrect1);
                g2d.setColor(Color.BLUE);
                start_x2 += 3 * ep + this.getEtagere().getListeetages()[0].getListecaissons()[i].getLargeur_rel() * largeur_disponible;

            } else {
                Rectangle2D.Double newrect1 = new Rectangle2D.Double(start_x2, start_y2, this.getEtagere().getListeetages()[0].getListecaissons()[i].getLargeur_rel() * largeur_disponible + 2 * ep, ep);
                g2d.fill(newrect1);
                g2d.setColor(Color.BLACK);
                g2d.draw(newrect1);
                g2d.setColor(Color.BLUE);
                start_x2 += 3 * ep + this.getEtagere().getListeetages()[0].getListecaissons()[i].getLargeur_rel() * largeur_disponible;

            }
        }

        g2d.fill(top2);

        g2d.setColor(Color.BLACK);

        g2d.draw(top2);
        g2d.setColor(Color.RED);
        double h_disponible = this.getEtagere().getHauteur() - 6 * ep - (this.getEtagere().getNb_etages() - 1) * 3 * ep;
        double start_y = yReference + 3 * ep;
        for (int i = 0; i < this.getEtagere().getNb_etages(); i++) {
            if (i == this.getEtagere().getNb_etages() - 1) {
                Rectangle2D.Double newrect = new Rectangle2D.Double(xReference + ep, start_y, ep, (this.getEtagere().getListeetages()[i].getHauteur_rel() * h_disponible) + 2 * ep);
                Rectangle2D.Double newrect2 = new Rectangle2D.Double(xReference + 2 * ep, start_y, ep, (this.getEtagere().getListeetages()[i].getHauteur_rel() * h_disponible) + ep);
                g2d.setColor(Color.RED);
                g2d.fill(newrect);
                g2d.fill(newrect2);
                g2d.setColor(Color.BLACK);
                g2d.draw(newrect);
                g2d.draw(newrect2);
            } else {
                Rectangle2D.Double newrect = new Rectangle2D.Double(xReference + ep, start_y, ep, (this.getEtagere().getListeetages()[i].getHauteur_rel() * h_disponible));
                Rectangle2D.Double newrect2 = new Rectangle2D.Double(xReference + 2 * ep, start_y, ep, (this.getEtagere().getListeetages()[i].getHauteur_rel() * h_disponible));
                start_y += (this.getEtagere().getListeetages()[i].getHauteur_rel() * h_disponible) + 3 * ep;
                g2d.setColor(Color.RED);
                g2d.fill(newrect);
                g2d.fill(newrect2);
                g2d.setColor(Color.BLACK);
                g2d.draw(newrect);
                g2d.draw(newrect2);

            }

        }
        start_y = yReference + 3 * ep;
        for (int i = 0; i < this.getEtagere().getNb_etages(); i++) {
            if (i == this.getEtagere().getNb_etages() - 1) {
                Rectangle2D.Double newrect = new Rectangle2D.Double(xReference + getEtagere().getLargeur() - 2 * ep, start_y, ep, (this.getEtagere().getListeetages()[i].getHauteur_rel() * h_disponible) + 2 * ep);
                Rectangle2D.Double newrect2 = new Rectangle2D.Double(xReference + getEtagere().getLargeur() - 3 * ep, start_y, ep, (this.getEtagere().getListeetages()[i].getHauteur_rel() * h_disponible) + ep);
                g2d.setColor(Color.RED);
                g2d.fill(newrect);
                g2d.fill(newrect2);
                g2d.setColor(Color.BLACK);
                g2d.draw(newrect);
                g2d.draw(newrect2);
            } else {
                Rectangle2D.Double newrect = new Rectangle2D.Double(xReference + getEtagere().getLargeur() - 2 * ep, start_y, ep, (this.getEtagere().getListeetages()[i].getHauteur_rel() * h_disponible));
                Rectangle2D.Double newrect2 = new Rectangle2D.Double(xReference + getEtagere().getLargeur() - 3 * ep, start_y, ep, (this.getEtagere().getListeetages()[i].getHauteur_rel() * h_disponible));
                start_y += (this.getEtagere().getListeetages()[i].getHauteur_rel() * h_disponible) + 3 * ep;
                g2d.setColor(Color.RED);
                g2d.fill(newrect);
                g2d.fill(newrect2);
                g2d.setColor(Color.BLACK);
                g2d.draw(newrect);
                g2d.draw(newrect2);

            }

        }
        g2d.setColor(Color.RED);
        if (this.getEtagere().isPiecedepasse() == true) {
            Rectangle2D.Double left1 = new Rectangle2D.Double(xReference, yReference + ep, ep, getEtagere().getHauteur() - ep);
            Rectangle2D.Double right1 = new Rectangle2D.Double(xReference + getEtagere().getLargeur() - ep, yReference + ep, ep, getEtagere().getHauteur() - ep);
            g2d.fill(left1);
            g2d.fill(right1);
            g2d.setColor(Color.BLACK);
            g2d.draw(right1);
            g2d.draw(left1);
            g2d.setColor(Color.RED);
        } else if (this.getEtagere().isPiecedepasse() == false) {
            Rectangle2D.Double left1 = new Rectangle2D.Double(xReference, yReference, ep, getEtagere().getHauteur());
            Rectangle2D.Double right1 = new Rectangle2D.Double(xReference + getEtagere().getLargeur() - ep, yReference, ep, getEtagere().getHauteur());
            g2d.fill(left1);
            g2d.fill(right1);
            g2d.setColor(Color.BLACK);
            g2d.draw(right1);
            g2d.draw(left1);
            g2d.setColor(Color.RED);
        }

    }

    public void drawPiecesfond() {

    }

    public void dessinerEtages(Graphics2D g2d) {
        double ep = 10;
        double h_disponible;
        double l_disponible;
        double l_courante_dispo;
        double longueur_piece;
        if (this.getEtagere().isPerimetretriple() == true) {
            h_disponible = this.getEtagere().getHauteur() - 6 * ep - (this.getEtagere().getNb_etages() - 1) * 3 * ep;
            l_disponible = this.getEtagere().getLargeur() - 6 * ep;
        } else {
            h_disponible = this.getEtagere().getHauteur() - 4 * ep - (this.getEtagere().getNb_etages() - 1) * 3 * ep;
            l_disponible = this.getEtagere().getLargeur() - 4 * ep;
        }
        double start_x = xReference + ep;
        double start_y;
        if (this.getEtagere().isPerimetretriple() == true) {
            start_y = yReference + 3 * ep;
        } else {
            start_y = yReference + 2 * ep;
        }
        Etage etagecourant;
        for (int i = 0; i < this.getEtagere().getNb_etages() - 1; i++) {

            start_y += (this.getEtagere().getListeetages()[i].getHauteur_rel() * h_disponible);
            for (int j = 0; j < 3; j++) {
                switch (j) {
                    case 0:
                        double plank_start_x = start_x;
                        etagecourant = this.getEtagere().getListeetages()[i];
                        l_courante_dispo = l_disponible - (etagecourant.getNb_Caisson() - 1) * 3 * ep;
                        for (int k = 0; k < etagecourant.getListecaissons().length; k++) {
                            longueur_piece = etagecourant.getListecaissons()[k].getLargeur_rel() * l_courante_dispo;
                            if (k == 0) {
                                if (getEtagere().isPerimetretriple() == true) {
                                    longueur_piece += ep * 2;
                                } else {
                                    longueur_piece += ep * 1;
                                }
                                Rectangle2D.Double newrect = new Rectangle2D.Double(plank_start_x, start_y, longueur_piece, ep);
                                getRectliste().add(newrect);
                                plank_start_x += longueur_piece + 3 * ep;
                            } else if (k == etagecourant.getListecaissons().length - 1) {
                                if (getEtagere().isPerimetretriple() == true) {
                                    longueur_piece += ep * 2;
                                } else {
                                    longueur_piece += ep * 1;
                                }

                                Rectangle2D.Double newrect = new Rectangle2D.Double(plank_start_x, start_y, longueur_piece, ep);
                                getRectliste().add(newrect);
                            } else {
                                
                                Rectangle2D.Double newrect = new Rectangle2D.Double(plank_start_x, start_y, longueur_piece, ep);
                                
                                getRectliste().add(newrect);;
                                plank_start_x += longueur_piece + 3 * ep;

                            }
                            
                        }
                        start_y += ep;
                        break;
                    case 1:
                        
                        Rectangle2D.Double newrect = new Rectangle2D.Double(start_x, start_y, this.getEtagere().getLargeur() - 2 * ep, ep);
                        getRectliste().add(newrect);
                        start_y += ep;
                        
                        break;
                    case 2:
                        double plank_start_x2 = start_x;
                        etagecourant = this.getEtagere().getListeetages()[i + 1];
                        l_courante_dispo = l_disponible - (etagecourant.getNb_Caisson() - 1) * 3 * ep;
                        for (int k = 0; k < etagecourant.getListecaissons().length; k++) {
                            longueur_piece = etagecourant.getListecaissons()[k].getLargeur_rel() * l_courante_dispo;
                            if (k == 0) {
                                if (getEtagere().isPerimetretriple() == true) {
                                    longueur_piece += ep * 3;
                                } else {
                                    longueur_piece += ep * 2;
                                }
                                Rectangle2D.Double newrect3 = new Rectangle2D.Double(plank_start_x2, start_y, longueur_piece, ep);
                                
                                getRectliste().add(newrect3);
                                
                                plank_start_x2 += longueur_piece + ep;
                            } else if (k == etagecourant.getListecaissons().length - 1) {
                                if (getEtagere().isPerimetretriple() == true) {
                                    longueur_piece += ep * 3;
                                } else {
                                    longueur_piece += ep * 2;
                                }
                                Rectangle2D.Double newrect3 = new Rectangle2D.Double(plank_start_x2, start_y, longueur_piece, ep);
                                
                               getRectliste().add(newrect3);
                            } else {

                                Rectangle2D.Double newrect3 = new Rectangle2D.Double(plank_start_x2, start_y, longueur_piece + 2 * ep, ep);
                                
                                getRectliste().add(newrect3);
                                plank_start_x2 += longueur_piece + 3 * ep;

                            }
                        }
                        start_y += ep;
                        break;
                    default:
                        break;
                }
            }

        }

    }

    public void dessinerCaissons(Graphics2D g2d) {
        double ep = 10;
        double etagesuperieur;
        double hauteurDisponible;
        double largeurDisponible;
        double xcourant;

        if (getEtagere().isPerimetretriple() == true) {
            etagesuperieur = yReference + 1.5 * ep3;
            hauteurDisponible = getEtagere().getHauteur() - 6 * ep - (getEtagere().getNb_etages() - 1) * (3 * ep);
        } else {
            etagesuperieur = yReference + ep3;
            hauteurDisponible = getEtagere().getHauteur() - 4 * ep - (getEtagere().getNb_etages() - 1) * (3 * ep);
        }

        double etagecourant = yReference + ep;

        for (int i = 0; i < getEtagere().getListeetages().length; i++) {

            Etage etage = getEtagere().getListeetages()[i];
            if (getEtagere().isPerimetretriple() == true) {
                largeurDisponible = getEtagere().getLargeur() - 6 * ep - (etage.getNb_Caisson() - 1) * (3 * ep);
                xcourant = xReference + 3 * ep;
            } else {
                largeurDisponible = getEtagere().getLargeur() - 4 * ep - (etage.getNb_Caisson() - 1) * (3 * ep);
                xcourant = xReference + 2 * ep;
            }

            //Si nous sommes dans l'étage du bas, on initialise etagecourant comme la valeur en y du centre de la planche du bas du périmètre.
            if (i == getEtagere().getListeetages().length - 1) {
                etagecourant = yReference + getEtagere().getHauteur() - ep3;
            } //Sinon, on place etage courant au centre de la planche constituant l'étage.
            else {
                etagecourant += (etage.getHauteur_rel()) * (hauteurDisponible) + 3 * ep3;
            }
            try {
                for (int j = 0; j < etage.getListecaissons().length - 1; j++) {
                    Caisson caisson = etage.getListecaissons()[j];
                    xcourant += (caisson.getLargeur_rel()) * largeurDisponible;
                    if (i == 0) {
                        if (getEtagere().isPerimetretriple() == true) {
                            if (i == getEtagere().getListeetages().length - 1) {
                                dessinerCaisson3PlanchesDessous(caisson, g2d, etagecourant, etagesuperieur, xcourant);
                            } else {
                                dessinerCaisson3Planches(caisson, g2d, etagecourant, etagesuperieur, xcourant);
                            }
                        } else {
                            if (i == getEtagere().getListeetages().length - 1) {
                                dessinerCaisson2PlanchesUnEtage(caisson, g2d, etagecourant, etagesuperieur, xcourant);
                            } else {
                                dessinerCaisson2PlanchesDessus(caisson, g2d, etagecourant, etagesuperieur, xcourant);
                            }
                        }
                    } else if (i == getEtagere().getListeetages().length - 1) {
                        if (getEtagere().isPerimetretriple() == true) {
                            dessinerCaisson3PlanchesDessous(caisson, g2d, etagecourant, etagesuperieur, xcourant);
                        } else {
                            dessinerCaisson2PlanchesDessous(caisson, g2d, etagecourant, etagesuperieur, xcourant);
                        }
                    } else {
                        dessinerCaisson3Planches(caisson, g2d, etagecourant, etagesuperieur, xcourant);
                    }
                    xcourant += 3 * ep;

                }
            } catch (NullPointerException e) {
                continue;
            }

            etagesuperieur = etagecourant;

        }
        //Affichage des rectangles.

        
    }

    public void dessinerCaisson3Planches(Caisson caisson, Graphics2D g2d, double etagecourant, double etagesuperieur, double xcourant) {
        
        Rectangle2D.Double centre = new Rectangle2D.Double(xcourant + ep3, etagesuperieur + ep3  , ep3, etagecourant - etagesuperieur - ep3);

        //rectangle de gauche
        Rectangle2D.Double gauche = new Rectangle2D.Double(xcourant, etagesuperieur + 4 * ep3 / 2, ep3, etagecourant - etagesuperieur - 2 * ep3);

        //rectangle de droite
        Rectangle2D.Double droite = new Rectangle2D.Double(xcourant + 2 * ep3, etagesuperieur + 4 * ep3 / 2, ep3, etagecourant - etagesuperieur - 2 * ep3);

        getRectliste().add(centre);
        getRectliste().add(gauche);
        getRectliste().add(droite);
    }

    public void dessinerCaisson3PlanchesDessous(Caisson caisson, Graphics2D g2d, double etagecourant, double etagesuperieur, double xcourant) {
        Rectangle2D.Double centre = new Rectangle2D.Double(xcourant + ep3, etagesuperieur + ep3  , ep3, etagecourant - etagesuperieur - ep3);

        //rectangle de gauche
        Rectangle2D.Double gauche = new Rectangle2D.Double(xcourant, etagesuperieur + 4 * ep3 / 2, ep3, etagecourant - etagesuperieur - 2 * ep3);

        //rectangle de droite
        Rectangle2D.Double droite = new Rectangle2D.Double(xcourant + 2 * ep3, etagesuperieur + 4 * ep3 / 2, ep3, etagecourant - etagesuperieur - 2 * ep3);

        getRectliste().add(centre);
        getRectliste().add(gauche);
        getRectliste().add(droite);
    }

    public void dessinerCaisson2PlanchesDessus(Caisson caisson, Graphics2D g2d, double etagecourant, double etagesuperieur, double xcourant) {
        Rectangle2D.Double centre = new Rectangle2D.Double(xcourant + ep3, etagesuperieur, ep3, etagecourant - etagesuperieur - ep3);

        //rectangle de gauche
        Rectangle2D.Double gauche = new Rectangle2D.Double(xcourant, etagesuperieur + ep3, ep3, etagecourant - etagesuperieur - ep2 - ep3 / 2);

        //rectangle de droite
        Rectangle2D.Double droite = new Rectangle2D.Double(xcourant + 2 * ep3, etagesuperieur + ep3, ep3, etagecourant - etagesuperieur - ep2 - ep3 / 2);

        getRectliste().add(centre);
        getRectliste().add(gauche);
        getRectliste().add(droite);
    }

    public void dessinerCaisson2PlanchesDessous(Caisson caisson, Graphics2D g2d, double etagecourant, double etagesuperieur, double xcourant) {
        Rectangle2D.Double centre = new Rectangle2D.Double(xcourant + ep3, etagesuperieur , ep3, etagecourant - etagesuperieur - ep3 / 2 + ep2 - ep3);

        //rectangle de gauche
        Rectangle2D.Double gauche = new Rectangle2D.Double(xcourant, etagesuperieur + 2 * ep3 / 2, ep3, etagecourant - etagesuperieur - ep3);

        //rectangle de droite
        Rectangle2D.Double droite = new Rectangle2D.Double(xcourant + 2 * ep3, etagesuperieur + 2 * ep3 / 2, ep3, etagecourant - etagesuperieur - ep3);

        getRectliste().add(centre);
        getRectliste().add(gauche);
        getRectliste().add(droite);
    }

    public void dessinerCaisson2PlanchesUnEtage(Caisson caisson, Graphics2D g2d, double etagecourant, double etagesuperieur, double xcourant) {
        Rectangle2D.Double centre = new Rectangle2D.Double(xcourant + ep3, etagesuperieur - ep3, ep3, etagecourant - etagesuperieur + ep2);
        Rectangle2D.Double gauche = new Rectangle2D.Double(xcourant, etagesuperieur, ep3, etagecourant - etagesuperieur - 1.5 * ep3 + ep2);
        Rectangle2D.Double droite = new Rectangle2D.Double(xcourant + 2 * ep3, etagesuperieur + ep2, ep3, etagecourant - etagesuperieur - 1.5 * ep3 + ep2);

        getRectliste().add(centre);
        getRectliste().add(gauche);
        getRectliste().add(droite);
    }

    /**
     * @return the rectliste
     */
    public List<Rectangle2D.Double> getRectliste() {
        return rectliste;
    }

    /**
     * @param rectliste the rectliste to set
     */
    public void setRectliste(List<Rectangle2D.Double> rectliste) {
        this.rectliste = rectliste;
    }

    /**
     * @return the etagere
     */
    public Etagere getEtagere() {
        return etagere;
    }

    /**
     * @param etagere the etagere to set
     */
    public void setEtagere(Etagere etagere) {
        this.etagere = etagere;
    }

}