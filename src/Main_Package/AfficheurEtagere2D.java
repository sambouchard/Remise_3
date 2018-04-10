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
import java.util.List;
import UI.UI_3Dtagere;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 *
 * @author SABOU350
 */
public class AfficheurEtagere2D extends JPanel {
    private boolean shouldClear = false; 
    private Etagere etagere;
    public static double xReference = 0;
    public static double yReference = 0;
    private double ep2 = 15; // Épaisseur d'une des deux planches du périmètre double
    static double ep3 = 10; // Épaisseur d'une des trois planches du périmètre triple
    private double magnificier;
    private List<Rectangle2D.Double> rectliste = new ArrayList();



    public AfficheurEtagere2D() {
    }
    
    public AfficheurEtagere2D(Etagere etagere) {
        this.etagere = etagere;
    }
    public void setEtagere(Etagere etagere) {
        this.etagere = etagere;

    }
    
    public void drawing() {
        rectliste.clear();
        repaint();
    }

    /**
     *
     * @param g
     */
    
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.isControlDown()) {
            if (e.getWheelRotation() < 0) {
                System.out.println("mouse wheel Up");
            } else {
                System.out.println("mouse wheel Down");
            }
        }
    }
    
    public void initListeners(UI_3Dtagere parent) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                super.mouseClicked(evt);
                
                for (Integer i = 0; i < etagere.getNb_etages(); i++) {
                    Etage currentEtage = etagere.getListeetages()[i];
                    if (currentEtage.getRectangle().contains(evt.getPoint())) {
                        parent.setSelectedEtage(currentEtage, i);
                    }
                    for (Integer j = 0; j < currentEtage.getNb_Caisson(); j++) {
                        Caisson currentCaisson = currentEtage.getListecaissons()[j];
                        if (currentCaisson.getRectangle().contains(evt.getPoint())) {
                            parent.setSelectedCaisson(currentCaisson, j);
                        }
                    }
                }
            }
        }
        );
    }
    
    public void clearView() {
        this.shouldClear = true;
        drawing();
    }
    /**
     * 
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        if (etagere != null) {
            Graphics2D g2d = (Graphics2D) g;
            if (shouldClear) {
                g2d.dispose();
                this.shouldClear = false;
                return;
            }
            if (etagere.isPerimetretriple() == true) {
                paint_ptrible(g2d);
                paint_pbas_triple(g2d);

            } else if (etagere.isPerimetretriple() == false) {
                paint_pdouble(g2d);
                paint_pbas_double(g2d);

            }

            dessinerEtages(g2d);
            dessinerCaissons(g2d);
            for (Rectangle2D.Double rect : rectliste) {
                g2d.setColor(Color.blue);
                g2d.fill(rect);
                g2d.setColor(Color.BLACK);
                g2d.draw(rect);
            }            
        }
    }

    public void paint_pbas_triple(Graphics2D g2d) {
        double ep = 10;
        Etage etagecourant = etagere.getListeetages()[etagere.getListeetages().length - 1];
        double l_courante_dispo = etagere.getLargeur() - 6 * ep - (etagecourant.getNb_Caisson() - 1) * 3 * ep;
        double longueur_piece;
        double start_y = yReference + etagere.getHauteur() - 2 * ep;
        Rectangle2D.Double bottom1 = new Rectangle2D.Double(xReference + ep, yReference + etagere.getHauteur() - ep, etagere.getLargeur() - 2 * ep, ep);
        rectliste.add(bottom1);

        

        double plank_start_x = xReference + 2 * ep;
        for (int k = 0; k < etagecourant.getListecaissons().length; k++) {
            longueur_piece = etagecourant.getListecaissons()[k].getLargeur_rel() * l_courante_dispo;
            if (k == 0) {
                longueur_piece += ep;
                Rectangle2D.Double newrect = new Rectangle2D.Double(plank_start_x, start_y, longueur_piece, ep);
                Rectangle2D.Double newrect1 = new Rectangle2D.Double(plank_start_x + ep, start_y - ep, longueur_piece - ep, ep);
                
                rectliste.add(newrect);
                rectliste.add(newrect1);
                
                plank_start_x += longueur_piece + 3 * ep;
            } else if (k == etagecourant.getListecaissons().length - 1) {
                longueur_piece += ep;
                Rectangle2D.Double newrect = new Rectangle2D.Double(plank_start_x, start_y, longueur_piece, ep);
                Rectangle2D.Double newrect1 = new Rectangle2D.Double(plank_start_x, start_y - ep, longueur_piece - ep, ep);
                
                rectliste.add(newrect);
                rectliste.add(newrect1);
                
            } else {
                
                Rectangle2D.Double newrect = new Rectangle2D.Double(plank_start_x, start_y, longueur_piece, ep);
                Rectangle2D.Double newrect1 = new Rectangle2D.Double(plank_start_x, start_y - ep, longueur_piece, ep);
                rectliste.add(newrect);
                rectliste.add(newrect1);
                
                plank_start_x += longueur_piece + 3 * ep;

            }
        }

    }

    public void paint_pbas_double(Graphics2D g2d) {
        double ep = 10;
        Etage etagecourant = etagere.getListeetages()[etagere.getListeetages().length - 1];
        double l_disponible;
        double l_courante_dispo = etagere.getLargeur() - 4 * ep - (etagecourant.getNb_Caisson() - 1) * 3 * ep;
        double longueur_piece;
        double start_y = yReference + etagere.getHauteur() - 2 * ep;

        Rectangle2D.Double bottom1 = new Rectangle2D.Double(xReference + ep, yReference + etagere.getHauteur() - ep, etagere.getLargeur() - 2 * ep, ep);
        
        rectliste.add(bottom1);

        double plank_start_x = xReference + 2 * ep;
        for (int k = 0; k < etagecourant.getListecaissons().length; k++) {
            longueur_piece = etagecourant.getListecaissons()[k].getLargeur_rel() * l_courante_dispo;
            if (k == 0) {
                
                Rectangle2D.Double newrect = new Rectangle2D.Double(plank_start_x, start_y, longueur_piece, ep);
                rectliste.add(newrect);
                plank_start_x += longueur_piece + 3 * ep;
                
            } else if (k == etagecourant.getListecaissons().length - 1) {
                Rectangle2D.Double newrect = new Rectangle2D.Double(plank_start_x, start_y, longueur_piece, ep);
                
                rectliste.add(newrect);
                
            } else {
                Rectangle2D.Double newrect = new Rectangle2D.Double(plank_start_x, start_y, longueur_piece, ep);
                
                rectliste.add(newrect);
                
                plank_start_x += longueur_piece + 3 * ep;

            }
        }

    }

    public void paint_pdouble(Graphics2D g2d) {
        double ep = 10;
        double longueur_piece;

        if (this.etagere.isPiecedepasse() == true) {
            Rectangle2D.Double top1 = new Rectangle2D.Double(xReference, yReference, etagere.getLargeur(), ep);
            rectliste.add(top1);
        } else if (this.etagere.isPiecedepasse() == false) {
            Rectangle2D.Double top1 = new Rectangle2D.Double(xReference + ep, yReference, etagere.getLargeur() - 2 * ep, ep);
            
            rectliste.add(top1);
        }
        double start_y2 = yReference + ep;
        double start_x2 = xReference + ep;
        double largeur_disponible;
        largeur_disponible = this.etagere.getLargeur() - 4 * ep - (this.etagere.getListeetages()[0].getNb_Caisson() - 1) * 3 * ep;
        for (int i = 0; i < this.etagere.getListeetages()[0].getListecaissons().length; i++) {
            if (i == 0) {
                longueur_piece = this.etagere.getListeetages()[0].getListecaissons()[i].getLargeur_rel() * largeur_disponible + 2 * ep;
                Rectangle2D.Double newrect1 = new Rectangle2D.Double(start_x2, start_y2, longueur_piece, ep);
                
                rectliste.add(newrect1);
                start_x2 += longueur_piece + ep;
                
            } else if (i == this.etagere.getListeetages()[0].getListecaissons().length - 1) {
                Rectangle2D.Double newrect1 = new Rectangle2D.Double(start_x2, start_y2, this.etagere.getListeetages()[0].getListecaissons()[i].getLargeur_rel() * largeur_disponible + 2 * ep, ep);
                
                rectliste.add(newrect1);
                
                start_x2 += 3 * ep + this.etagere.getListeetages()[0].getListecaissons()[i].getLargeur_rel() * largeur_disponible;

            } else {
                Rectangle2D.Double newrect1 = new Rectangle2D.Double(start_x2, start_y2, this.etagere.getListeetages()[0].getListecaissons()[i].getLargeur_rel() * largeur_disponible + 2 * ep, ep);
                rectliste.add(newrect1);
                start_x2 += 3 * ep + this.etagere.getListeetages()[0].getListecaissons()[i].getLargeur_rel() * largeur_disponible;

            }
        }
        g2d.setColor(Color.RED);
        if (this.etagere.isPiecedepasse() == true) {
            Rectangle2D.Double left1 = new Rectangle2D.Double(xReference, yReference + ep, ep, etagere.getHauteur() - ep);
            Rectangle2D.Double right1 = new Rectangle2D.Double(xReference + etagere.getLargeur() - ep, yReference + ep, ep, etagere.getHauteur() - ep);
            
            rectliste.add(left1);
            rectliste.add(right1);
            
        } else if (this.etagere.isPiecedepasse() == false) {
            Rectangle2D.Double left1 = new Rectangle2D.Double(xReference, yReference, ep, etagere.getHauteur());
            Rectangle2D.Double right1 = new Rectangle2D.Double(xReference + etagere.getLargeur() - ep, yReference, ep, etagere.getHauteur());
            
            rectliste.add(left1);
            rectliste.add(right1);
        }

        double h_disponible = this.etagere.getHauteur() - 4 * ep - (this.etagere.getNb_etages() - 1) * 3 * ep;
        double start_y = yReference + 2 * ep;
        g2d.setColor(Color.RED);
        for (int i = 0; i < this.etagere.getNb_etages(); i++) {
            if (i == this.etagere.getNb_etages() - 1) {
                Rectangle2D.Double newrect = new Rectangle2D.Double(xReference + ep, start_y, ep, (this.etagere.getListeetages()[i].getHauteur_rel() * h_disponible) + ep);
                
                rectliste.add(newrect);
            } else {
                Rectangle2D.Double newrect = new Rectangle2D.Double(xReference + ep, start_y, ep, (this.etagere.getListeetages()[i].getHauteur_rel() * h_disponible));
                start_y += (this.etagere.getListeetages()[i].getHauteur_rel() * h_disponible) + 3 * ep;
                
                rectliste.add(newrect);
            }

        }
        start_y = yReference + 2 * ep;
        for (int i = 0; i < this.etagere.getNb_etages(); i++) {
            if (i == this.etagere.getNb_etages() - 1) {
                Rectangle2D.Double newrect = new Rectangle2D.Double(xReference + etagere.getLargeur() - 2 * ep, start_y, ep, (this.etagere.getListeetages()[i].getHauteur_rel() * h_disponible) + ep);
                
                rectliste.add(newrect);
                
            } else {
                Rectangle2D.Double newrect = new Rectangle2D.Double(xReference + etagere.getLargeur() - 2 * ep, start_y, ep, (this.etagere.getListeetages()[i].getHauteur_rel() * h_disponible));
                start_y += (this.etagere.getListeetages()[i].getHauteur_rel() * h_disponible) + 3 * ep;
                
                rectliste.add(newrect);

            }

        }

    }

    public void paint_ptrible(Graphics2D g2d) {
        double ep = 10;
        double longueur_piece;

        g2d.setColor(Color.BLUE);

        if (this.etagere.isPiecedepasse() == true) {
            Rectangle2D.Double top1 = new Rectangle2D.Double(xReference, yReference, etagere.getLargeur(), ep);
            g2d.fill(top1);
            g2d.setColor(Color.BLACK);
            g2d.draw(top1);
            g2d.setColor(Color.BLUE);
        } else if (this.etagere.isPiecedepasse() == false) {
            Rectangle2D.Double top1 = new Rectangle2D.Double(xReference + ep, yReference, etagere.getLargeur() - 2 * ep, ep);
            g2d.fill(top1);
            g2d.setColor(Color.BLACK);
            g2d.draw(top1);
            g2d.setColor(Color.BLUE);
        }
        Rectangle2D.Double top2 = new Rectangle2D.Double(xReference + ep, yReference + ep, etagere.getLargeur() - 2 * ep, ep);
        double start_y2 = yReference + 2 * ep;
        double start_x2 = xReference + ep;
        double largeur_disponible = this.etagere.getLargeur() - 6 * ep - (this.etagere.getListeetages()[0].getNb_Caisson() - 1) * 3 * ep;
        for (int i = 0; i < this.etagere.getListeetages()[0].getListecaissons().length; i++) {
            if (i == 0) {
                longueur_piece = this.etagere.getListeetages()[0].getListecaissons()[i].getLargeur_rel() * largeur_disponible + 3 * ep;
                Rectangle2D.Double newrect1 = new Rectangle2D.Double(start_x2, start_y2, longueur_piece, ep);
                g2d.fill(newrect1);
                g2d.setColor(Color.BLACK);
                g2d.draw(newrect1);
                g2d.setColor(Color.BLUE);
                start_x2 += longueur_piece + ep;
            } else if (i == this.etagere.getListeetages()[0].getListecaissons().length - 1) {
                Rectangle2D.Double newrect1 = new Rectangle2D.Double(start_x2, start_y2, this.etagere.getListeetages()[0].getListecaissons()[i].getLargeur_rel() * largeur_disponible + 3 * ep, ep);
                g2d.fill(newrect1);
                g2d.setColor(Color.BLACK);
                g2d.draw(newrect1);
                g2d.setColor(Color.BLUE);
                start_x2 += 3 * ep + this.etagere.getListeetages()[0].getListecaissons()[i].getLargeur_rel() * largeur_disponible;

            } else {
                Rectangle2D.Double newrect1 = new Rectangle2D.Double(start_x2, start_y2, this.etagere.getListeetages()[0].getListecaissons()[i].getLargeur_rel() * largeur_disponible + 2 * ep, ep);
                g2d.fill(newrect1);
                g2d.setColor(Color.BLACK);
                g2d.draw(newrect1);
                g2d.setColor(Color.BLUE);
                start_x2 += 3 * ep + this.etagere.getListeetages()[0].getListecaissons()[i].getLargeur_rel() * largeur_disponible;

            }
        }

        g2d.fill(top2);

        g2d.setColor(Color.BLACK);

        g2d.draw(top2);
        g2d.setColor(Color.RED);
        double h_disponible = this.etagere.getHauteur() - 6 * ep - (this.etagere.getNb_etages() - 1) * 3 * ep;
        double start_y = yReference + 3 * ep;
        for (int i = 0; i < this.etagere.getNb_etages(); i++) {
            if (i == this.etagere.getNb_etages() - 1) {
                Rectangle2D.Double newrect = new Rectangle2D.Double(xReference + ep, start_y, ep, (this.etagere.getListeetages()[i].getHauteur_rel() * h_disponible) + 2 * ep);
                Rectangle2D.Double newrect2 = new Rectangle2D.Double(xReference + 2 * ep, start_y, ep, (this.etagere.getListeetages()[i].getHauteur_rel() * h_disponible) + ep);
                g2d.setColor(Color.RED);
                g2d.fill(newrect);
                g2d.fill(newrect2);
                g2d.setColor(Color.BLACK);
                g2d.draw(newrect);
                g2d.draw(newrect2);
            } else {
                Rectangle2D.Double newrect = new Rectangle2D.Double(xReference + ep, start_y, ep, (this.etagere.getListeetages()[i].getHauteur_rel() * h_disponible));
                Rectangle2D.Double newrect2 = new Rectangle2D.Double(xReference + 2 * ep, start_y, ep, (this.etagere.getListeetages()[i].getHauteur_rel() * h_disponible));
                start_y += (this.etagere.getListeetages()[i].getHauteur_rel() * h_disponible) + 3 * ep;
                g2d.setColor(Color.RED);
                g2d.fill(newrect);
                g2d.fill(newrect2);
                g2d.setColor(Color.BLACK);
                g2d.draw(newrect);
                g2d.draw(newrect2);

            }

        }
        start_y = yReference + 3 * ep;
        for (int i = 0; i < this.etagere.getNb_etages(); i++) {
            if (i == this.etagere.getNb_etages() - 1) {
                Rectangle2D.Double newrect = new Rectangle2D.Double(xReference + etagere.getLargeur() - 2 * ep, start_y, ep, (this.etagere.getListeetages()[i].getHauteur_rel() * h_disponible) + 2 * ep);
                Rectangle2D.Double newrect2 = new Rectangle2D.Double(xReference + etagere.getLargeur() - 3 * ep, start_y, ep, (this.etagere.getListeetages()[i].getHauteur_rel() * h_disponible) + ep);
                g2d.setColor(Color.RED);
                g2d.fill(newrect);
                g2d.fill(newrect2);
                g2d.setColor(Color.BLACK);
                g2d.draw(newrect);
                g2d.draw(newrect2);
            } else {
                Rectangle2D.Double newrect = new Rectangle2D.Double(xReference + etagere.getLargeur() - 2 * ep, start_y, ep, (this.etagere.getListeetages()[i].getHauteur_rel() * h_disponible));
                Rectangle2D.Double newrect2 = new Rectangle2D.Double(xReference + etagere.getLargeur() - 3 * ep, start_y, ep, (this.etagere.getListeetages()[i].getHauteur_rel() * h_disponible));
                start_y += (this.etagere.getListeetages()[i].getHauteur_rel() * h_disponible) + 3 * ep;
                g2d.setColor(Color.RED);
                g2d.fill(newrect);
                g2d.fill(newrect2);
                g2d.setColor(Color.BLACK);
                g2d.draw(newrect);
                g2d.draw(newrect2);

            }

        }
        g2d.setColor(Color.RED);
        if (this.etagere.isPiecedepasse() == true) {
            Rectangle2D.Double left1 = new Rectangle2D.Double(xReference, yReference + ep, ep, etagere.getHauteur() - ep);
            Rectangle2D.Double right1 = new Rectangle2D.Double(xReference + etagere.getLargeur() - ep, yReference + ep, ep, etagere.getHauteur() - ep);
            g2d.fill(left1);
            g2d.fill(right1);
            g2d.setColor(Color.BLACK);
            g2d.draw(right1);
            g2d.draw(left1);
            g2d.setColor(Color.RED);
        } else if (this.etagere.isPiecedepasse() == false) {
            Rectangle2D.Double left1 = new Rectangle2D.Double(xReference, yReference, ep, etagere.getHauteur());
            Rectangle2D.Double right1 = new Rectangle2D.Double(xReference + etagere.getLargeur() - ep, yReference, ep, etagere.getHauteur());
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
        if (this.etagere.isPerimetretriple() == true) {
            h_disponible = this.etagere.getHauteur() - 6 * ep - (this.etagere.getNb_etages() - 1) * 3 * ep;
            l_disponible = this.etagere.getLargeur() - 6 * ep;
        } else {
            h_disponible = this.etagere.getHauteur() - 4 * ep - (this.etagere.getNb_etages() - 1) * 3 * ep;
            l_disponible = this.etagere.getLargeur() - 4 * ep;
        }
        double start_x = xReference + ep;
        double start_y;
        if (this.etagere.isPerimetretriple() == true) {
            start_y = yReference + 3 * ep;
        } else {
            start_y = yReference + 2 * ep;
        }
        Etage etagecourant;
        for (int i = 0; i < this.etagere.getNb_etages() - 1; i++) {

            start_y += (this.etagere.getListeetages()[i].getHauteur_rel() * h_disponible);
            for (int j = 0; j < 3; j++) {
                switch (j) {
                    case 0:
                        double plank_start_x = start_x;
                        etagecourant = this.etagere.getListeetages()[i];
                        l_courante_dispo = l_disponible - (etagecourant.getNb_Caisson() - 1) * 3 * ep;
                        for (int k = 0; k < etagecourant.getListecaissons().length; k++) {
                            longueur_piece = etagecourant.getListecaissons()[k].getLargeur_rel() * l_courante_dispo;
                            if (k == 0) {
                                if (etagere.isPerimetretriple() == true) {
                                    longueur_piece += ep * 2;
                                } else {
                                    longueur_piece += ep * 1;
                                }
                                Rectangle2D.Double newrect = new Rectangle2D.Double(plank_start_x, start_y, longueur_piece, ep);
                                rectliste.add(newrect);
                                plank_start_x += longueur_piece + 3 * ep;
                            } else if (k == etagecourant.getListecaissons().length - 1) {
                                if (etagere.isPerimetretriple() == true) {
                                    longueur_piece += ep * 2;
                                } else {
                                    longueur_piece += ep * 1;
                                }

                                Rectangle2D.Double newrect = new Rectangle2D.Double(plank_start_x, start_y, longueur_piece, ep);
                                rectliste.add(newrect);
                            } else {
                                
                                Rectangle2D.Double newrect = new Rectangle2D.Double(plank_start_x, start_y, longueur_piece, ep);
                                
                                rectliste.add(newrect);;
                                plank_start_x += longueur_piece + 3 * ep;

                            }
                            
                        }
                        start_y += ep;
                        break;
                    case 1:
                        
                        Rectangle2D.Double newrect = new Rectangle2D.Double(start_x, start_y, this.etagere.getLargeur() - 2 * ep, ep);
                        rectliste.add(newrect);
                        start_y += ep;
                        
                        break;
                    case 2:
                        double plank_start_x2 = start_x;
                        etagecourant = this.etagere.getListeetages()[i + 1];
                        l_courante_dispo = l_disponible - (etagecourant.getNb_Caisson() - 1) * 3 * ep;
                        for (int k = 0; k < etagecourant.getListecaissons().length; k++) {
                            longueur_piece = etagecourant.getListecaissons()[k].getLargeur_rel() * l_courante_dispo;
                            if (k == 0) {
                                if (etagere.isPerimetretriple() == true) {
                                    longueur_piece += ep * 3;
                                } else {
                                    longueur_piece += ep * 2;
                                }
                                Rectangle2D.Double newrect3 = new Rectangle2D.Double(plank_start_x2, start_y, longueur_piece, ep);
                                
                                rectliste.add(newrect3);
                                
                                plank_start_x2 += longueur_piece + ep;
                            } else if (k == etagecourant.getListecaissons().length - 1) {
                                if (etagere.isPerimetretriple() == true) {
                                    longueur_piece += ep * 3;
                                } else {
                                    longueur_piece += ep * 2;
                                }
                                Rectangle2D.Double newrect3 = new Rectangle2D.Double(plank_start_x2, start_y, longueur_piece, ep);
                                
                               rectliste.add(newrect3);
                            } else {

                                Rectangle2D.Double newrect3 = new Rectangle2D.Double(plank_start_x2, start_y, longueur_piece + 2 * ep, ep);
                                
                                rectliste.add(newrect3);
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

        if (etagere.isPerimetretriple() == true) {
            etagesuperieur = yReference + 1.5 * ep3;
            hauteurDisponible = etagere.getHauteur() - 6 * ep - (etagere.getNb_etages() - 1) * (3 * ep);
        } else {
            etagesuperieur = yReference + ep3;
            hauteurDisponible = etagere.getHauteur() - 4 * ep - (etagere.getNb_etages() - 1) * (3 * ep);
        }

        double etagecourant = yReference + ep;

        for (int i = 0; i < etagere.getListeetages().length; i++) {

            Etage etage = etagere.getListeetages()[i];
            if (etagere.isPerimetretriple() == true) {
                largeurDisponible = etagere.getLargeur() - 6 * ep - (etage.getNb_Caisson() - 1) * (3 * ep);
                xcourant = xReference + 3 * ep;
            } else {
                largeurDisponible = etagere.getLargeur() - 4 * ep - (etage.getNb_Caisson() - 1) * (3 * ep);
                xcourant = xReference + 2 * ep;
            }

            //Si nous sommes dans l'étage du bas, on initialise etagecourant comme la valeur en y du centre de la planche du bas du périmètre.
            if (i == etagere.getListeetages().length - 1) {
                etagecourant = yReference + etagere.getHauteur() - ep3;
            } //Sinon, on place etage courant au centre de la planche constituant l'étage.
            else {
                etagecourant += (etage.getHauteur_rel()) * (hauteurDisponible) + 3 * ep3;
            }
            try {
                for (int j = 0; j < etage.getListecaissons().length - 1; j++) {
                    Caisson caisson = etage.getListecaissons()[j];
                    xcourant += (caisson.getLargeur_rel()) * largeurDisponible;
                    if (i == 0) {
                        if (etagere.isPerimetretriple() == true) {
                            if (i == etagere.getListeetages().length - 1) {
                                dessinerCaisson3PlanchesDessous(caisson, g2d, etagecourant, etagesuperieur, xcourant);
                            } else {
                                dessinerCaisson3Planches(caisson, g2d, etagecourant, etagesuperieur, xcourant);
                            }
                        } else {
                            if (i == etagere.getListeetages().length - 1) {
                                dessinerCaisson2PlanchesUnEtage(caisson, g2d, etagecourant, etagesuperieur, xcourant);
                            } else {
                                dessinerCaisson2PlanchesDessus(caisson, g2d, etagecourant, etagesuperieur, xcourant);
                            }
                        }
                    } else if (i == etagere.getListeetages().length - 1) {
                        if (etagere.isPerimetretriple() == true) {
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
        Rectangle2D.Double centre = new Rectangle2D.Double(xcourant + ep3, etagesuperieur + ep3 / 2, ep3, etagecourant - etagesuperieur - ep3);

        //rectangle de gauche
        Rectangle2D.Double gauche = new Rectangle2D.Double(xcourant, etagesuperieur + 3 * ep3 / 2, ep3, etagecourant - etagesuperieur - 2 * ep3);

        //rectangle de droite
        Rectangle2D.Double droite = new Rectangle2D.Double(xcourant + 2 * ep3, etagesuperieur + 3 * ep3 / 2, ep3, etagecourant - etagesuperieur - 2 * ep3);

        rectliste.add(centre);
        rectliste.add(gauche);
        rectliste.add(droite);
    }

    public void dessinerCaisson3PlanchesDessous(Caisson caisson, Graphics2D g2d, double etagecourant, double etagesuperieur, double xcourant) {
        Rectangle2D.Double centre = new Rectangle2D.Double(xcourant + ep3, etagesuperieur + ep3 / 2, ep3, etagecourant - etagesuperieur);

        //rectangle de gauche
        Rectangle2D.Double gauche = new Rectangle2D.Double(xcourant, etagesuperieur + 3 * ep3 / 2, ep3, etagecourant - etagesuperieur - ep3);

        //rectangle de droite
        Rectangle2D.Double droite = new Rectangle2D.Double(xcourant + 2 * ep3, etagesuperieur + 3 * ep3 / 2, ep3, etagecourant - etagesuperieur - ep3);

        rectliste.add(centre);
        rectliste.add(gauche);
        rectliste.add(droite);
    }

    public void dessinerCaisson2PlanchesDessus(Caisson caisson, Graphics2D g2d, double etagecourant, double etagesuperieur, double xcourant) {
        Rectangle2D.Double centre = new Rectangle2D.Double(xcourant + ep3, etagesuperieur, ep3, etagecourant - etagesuperieur - ep3);

        //rectangle de gauche
        Rectangle2D.Double gauche = new Rectangle2D.Double(xcourant, etagesuperieur + ep3, ep3, etagecourant - etagesuperieur - ep2 - ep3 / 2);

        //rectangle de droite
        Rectangle2D.Double droite = new Rectangle2D.Double(xcourant + 2 * ep3, etagesuperieur + ep3, ep3, etagecourant - etagesuperieur - ep2 - ep3 / 2);

        rectliste.add(centre);
        rectliste.add(gauche);
        rectliste.add(droite);
    }

    public void dessinerCaisson2PlanchesDessous(Caisson caisson, Graphics2D g2d, double etagecourant, double etagesuperieur, double xcourant) {
        Rectangle2D.Double centre = new Rectangle2D.Double(xcourant + ep3, etagesuperieur + ep3 / 2, ep3, etagecourant - etagesuperieur - ep3 / 2 + ep2);

        //rectangle de gauche
        Rectangle2D.Double gauche = new Rectangle2D.Double(xcourant, etagesuperieur + 3 * ep3 / 2, ep3, etagecourant - etagesuperieur - 1.5 * ep3 + ep2);

        //rectangle de droite
        Rectangle2D.Double droite = new Rectangle2D.Double(xcourant + 2 * ep3, etagesuperieur + 3 * ep3 / 2, ep3, etagecourant - etagesuperieur - 1.5 * ep3 + ep2);

        rectliste.add(centre);
        rectliste.add(gauche);
        rectliste.add(droite);
    }

    public void dessinerCaisson2PlanchesUnEtage(Caisson caisson, Graphics2D g2d, double etagecourant, double etagesuperieur, double xcourant) {
        Rectangle2D.Double centre = new Rectangle2D.Double(xcourant + ep3, etagesuperieur, ep3, etagecourant - etagesuperieur + ep2);
        Rectangle2D.Double gauche = new Rectangle2D.Double(xcourant, etagesuperieur + ep2, ep3, etagecourant - etagesuperieur - 1.5 * ep3 + ep2);
        Rectangle2D.Double droite = new Rectangle2D.Double(xcourant + 2 * ep3, etagesuperieur + ep2, ep3, etagecourant - etagesuperieur - 1.5 * ep3 + ep2);

        rectliste.add(centre);
        rectliste.add(gauche);
        rectliste.add(droite);
    }

}
