/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Main_Package.Caisson;
import Main_Package.Controleur;
import Main_Package.Coord_Coins;
import Main_Package.MontantEtageHorizontal;
import Main_Package.Etage;
import Main_Package.MontantCaissonVertical;
import Main_Package.Piece;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.event.MouseAdapter;
import java.awt.geom.Rectangle2D;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SABOU350
 */
public class AfficheurEtagere2D extends JPanel {

    public void setRectFantomeHorizontal_1(Rectangle2D.Double RectFantomeHorizontal_1) {
        this.RectFantomeHorizontal_1 = RectFantomeHorizontal_1;
    }

    public void setRectFantomeHorizontal_2(Rectangle2D.Double RectFantomeHorizontal_2) {
        this.RectFantomeHorizontal_2 = RectFantomeHorizontal_2;
    }

    public void setRectFantomeHorizontal_3(Rectangle2D.Double RectFantomeHorizontal_3) {
        this.RectFantomeHorizontal_3 = RectFantomeHorizontal_3;
    }

    public void setRectFantomeVertical_1(Rectangle2D.Double RectFantomeVertical_1) {
        this.RectFantomeVertical_1 = RectFantomeVertical_1;
    }

    public void setRectFantomeVertical_2(Rectangle2D.Double RectFantomeVertical_2) {
        this.RectFantomeVertical_2 = RectFantomeVertical_2;
    }

    public void setRectFantomeVertical_3(Rectangle2D.Double RectFantomeVertical_3) {
        this.RectFantomeVertical_3 = RectFantomeVertical_3;
    }
    private Rectangle2D.Double RectFantomeHorizontal_1;
    private Rectangle2D.Double RectFantomeHorizontal_2;
    private Rectangle2D.Double RectFantomeHorizontal_3;
    private Rectangle2D.Double RectFantomeVertical_1;
    private Rectangle2D.Double RectFantomeVertical_2;
    private Rectangle2D.Double RectFantomeVertical_3;
    private final double scale;
    private Graphics2D g2d;
    private AffineTransform tx = new AffineTransform();

    Movingadapter ma = new Movingadapter();

    public AfficheurEtagere2D() {
        this.scale = 1.0;
        this.addMouseMotionListener(ma);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                Point2D pointInEtagereCoordSpace;
                try {
                    pointInEtagereCoordSpace = tx.inverseTransform(me.getPoint(), null);
                } catch (NoninvertibleTransformException ex) {
                    Logger.getLogger(AfficheurEtagere2D.class.getName()).log(Level.SEVERE, null, ex);
                    return;
                }
                for(MontantCaissonVertical montant:Controleur.getInstance().getEtagere().getListeMontantVertical()){
                    for(Piece piece : montant.getListe_pieces()){
                        if(piece.contains(pointInEtagereCoordSpace)){
                            Controleur.getInstance().setMontantVerticalSelectionne(montant);
                        }
                    }
                }
                for(MontantEtageHorizontal montant : Controleur.getInstance().getEtagere().getListeMontantHorizontal()){
                    for(Piece piece :montant.getListe_piece()){
                        if(piece.contains(pointInEtagereCoordSpace)){
                            Controleur.getInstance().setMontantEtageHorizontalSelectionne(montant);
                        }
                    }
                }
                for (Piece piece : Controleur.getInstance().getEtagere().getListe_piece()) {
                    if (piece.contains(pointInEtagereCoordSpace)) {
                        Controleur.getInstance().setPieceSelectionner(piece);
                        System.out.println(piece.getNom());
                    }
                }
                for (Etage etage : Controleur.getInstance().getEtagere().getListeetages()) {
                    if (etage.contains(pointInEtagereCoordSpace)) {
                        Controleur.getInstance().setEtageSelectionne(etage);
                        Controleur.getInstance().updatevue();
                    }
                }
                for(Etage etage : Controleur.getInstance().getEtagere().getListeetages()){
                    for(Caisson caisson : etage.getListecaissons()){
                        if(caisson.contains(pointInEtagereCoordSpace)){
                            Controleur.getInstance().setCaissonSelectionne(caisson);
                            Controleur.getInstance().updatevue();
                        }
                    }
                }
                if (Controleur.getInstance().isAjouteCaissonMode()) {
                    for(Etage etage:Controleur.getInstance().getEtagere().getListeetages()){
                        for(Caisson caisson : etage.getListecaissons()){
                            if(caisson.contains(RectFantomeVertical_1.getX(), RectFantomeVertical_1.getY())){
                                Controleur.getInstance().AjouteCaisson(caisson, etage, RectFantomeVertical_1.getX());
                            }
                        }
                    
                }

                }
                if (Controleur.getInstance().isAjouteetageMode()) {
                    for(Etage etage: Controleur.getInstance().getEtagere().getListeetages()){
                        if(etage.contains(RectFantomeHorizontal_1)){
                            Controleur.getInstance().AjouteEtage(etage, RectFantomeHorizontal_1.getY());
                        }
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent m) {

            }

            @Override
            public void mouseReleased(MouseEvent me) {
                Controleur.getInstance().setMontantEtageHorizontalSelectionne(null);
                Controleur.getInstance().setMontantVerticalSelectionne(null);
            }

            @Override
            public void mouseEntered(MouseEvent me) {

            }

            @Override
            public void mouseExited(MouseEvent me) {

            }
        });
        this.addMouseWheelListener(new ZoomHandler());
        this.addMouseListener(ma);

    }

    public void redraw() {
        repaint();
    }

    private class ZoomHandler implements MouseWheelListener {

        double scale = 1.0;

        @Override
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

                AfficheurEtagere2D.this.revalidate();
                AfficheurEtagere2D.this.repaint();
            }
        }
    }

    private class Movingadapter extends MouseAdapter {
        private double prevDraggedX;
        private double prevDraggedY;

        @Override
        public void mousePressed(MouseEvent e) {
            Point2D p2;
            try {
                p2 = tx.inverseTransform(e.getPoint(), null);
            } catch (NoninvertibleTransformException ex) {
                return;
            }
            prevDraggedX = p2.getX();
            prevDraggedY = p2.getY();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            Point2D mouseInZoomedCoordSpace;
             try {
                mouseInZoomedCoordSpace = tx.inverseTransform(e.getPoint(), null);
            } catch (NoninvertibleTransformException ex) {
                Logger.getLogger(AfficheurEtagere2D.class.getName()).log(Level.SEVERE, null, ex);
                mouseInZoomedCoordSpace = e.getPoint();
            }
            if (Controleur.getInstance().getEtagere() != null) {
                if (Controleur.getInstance().isAjouteetageMode()) {
                    Controleur.getInstance().getAfficheur().setRectFantomeHorizontal_1(new Rectangle2D.Double(mouseInZoomedCoordSpace.getX(), mouseInZoomedCoordSpace.getY(),
                            Controleur.getInstance().getEtagere().getLargeur()-20, 0.5 * 2.5));
                    Controleur.getInstance().getAfficheur().setRectFantomeHorizontal_2(new Rectangle2D.Double(mouseInZoomedCoordSpace.getX(), mouseInZoomedCoordSpace.getY() + 0.5 * 2.5,
                            Controleur.getInstance().getEtagere().getLargeur()-20, 0.5 * 2.5));
                    Controleur.getInstance().getAfficheur().setRectFantomeHorizontal_3(new Rectangle2D.Double(mouseInZoomedCoordSpace.getX(), mouseInZoomedCoordSpace.getY() + 2 * (0.5 * 2.5),
                            Controleur.getInstance().getEtagere().getLargeur()-20, 0.5 * 2.5));
                    repaint();

                }
                if (Controleur.getInstance().isAjouteCaissonMode()) {
                    Controleur.getInstance().getAfficheur().setRectFantomeVertical_1(new Rectangle2D.Double(mouseInZoomedCoordSpace.getX(), mouseInZoomedCoordSpace.getY(),
                            0.5 * 2.5, Controleur.getInstance().getEtagere().getHauteur()/4));
                    Controleur.getInstance().getAfficheur().setRectFantomeVertical_2(new Rectangle2D.Double(mouseInZoomedCoordSpace.getX() + 0.5 * 2.5, mouseInZoomedCoordSpace.getY() - 0.5 * 2.5,
                            0.5 * 2.5, Controleur.getInstance().getEtagere().getHauteur()/4 + 0.5 * 2.5));
                    Controleur.getInstance().getAfficheur().setRectFantomeVertical_3(new Rectangle2D.Double(mouseInZoomedCoordSpace.getX() + 2 * (0.5 * 2.5), mouseInZoomedCoordSpace.getY(),
                            0.5 * 2.5, Controleur.getInstance().getEtagere().getHauteur()/4));
                    repaint();

                }

            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Point2D p2;
            try {
                p2 = tx.inverseTransform(e.getPoint(), null);
            } catch (NoninvertibleTransformException ex) {
                return;
            }
            double dx = p2.getX() - prevDraggedX;
            double dy = p2.getY() - prevDraggedY;
            prevDraggedX = p2.getX();
            prevDraggedY = p2.getY();
            
            if(Controleur.getInstance().getMontantVerticalSelectionne() == null && Controleur.getInstance().getMontantEtageHorizontalSelectionne() == null){
                for (Piece piece : Controleur.getInstance().getEtagere().getListe_Piece_Etage_Horizontale()) {
                    
                    if (piece.contains(p2)) {
                        piece.getDrawingcoin().setCoord_y(piece.getDrawingcoin().getCoord_y() + dy);
                        repaint();
                        break;
                    }
                }
                for (Etage etage : Controleur.getInstance().getEtagere().getListeetages()) {
                    etage.x += dx;
                    etage.y += dy;
                    for(Caisson caisson: etage.getListecaissons()){
                        caisson.x += dx;
                        caisson.y += dy;
                    }
                }
                for (Piece piece : Controleur.getInstance().getEtagere().getListe_piece()) {
                    piece.getDrawingcoin().setCoord_x(piece.getDrawingcoin().getCoord_x() + dx);
                    piece.getDrawingcoin().setCoord_y(piece.getDrawingcoin().getCoord_y() + dy);

                }
            } else {
                if(Controleur.getInstance().getMontantVerticalSelectionne() != null){
                    Controleur.getInstance().getMontantVerticalSelectionne().applyDx(dx); 
                   
                }
                if (Controleur.getInstance().getMontantEtageHorizontalSelectionne() != null) {
                    Controleur.getInstance().getMontantEtageHorizontalSelectionne().applyDy(dy);
                }
                Controleur.getInstance().getEtagere().GenererPieces();
                Controleur.getInstance().getAfficheur().redraw();
            }
            Controleur.getInstance().getAfficheur().redraw();

        }

    }

    /**
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g2d = (Graphics2D) g;
        if (Controleur.getInstance().getEtagere() != null) {
            for (Etage etage : Controleur.getInstance().getEtagere().getListeetages()) {
                g2d.setColor(Color.BLUE);
                for(Caisson caisson: etage.getListecaissons()){
                    g2d.fill(tx.createTransformedShape(caisson));
                }
//                g2d.fill(tx.createTransformedShape(etage));
            }
            if(Controleur.getInstance().getMontantEtageHorizontalSelectionne() != null){
                for(Piece piece: Controleur.getInstance().getMontantEtageHorizontalSelectionne().getListe_piece()){
                    g2d.setColor(Color.RED);
                    g2d.fill(tx.createTransformedShape(piece));
                }
            }
            if(Controleur.getInstance().getMontantVerticalSelectionne()!= null){
                for(Piece piece:Controleur.getInstance().getMontantVerticalSelectionne().getListe_pieces() ){
                    g2d.setColor(Color.RED);
                    g2d.fill(tx.createTransformedShape(piece));
                }
            }

            Controleur.getInstance().getEtagere().getListe_piece().stream().map((piece) -> {
                piece.setRect((piece.getDrawingcoin().getCoord_x())+ 300, (piece.getDrawingcoin().getCoord_y())+300, piece.getLargeur(), piece.getHauteur());
                return piece;
            }).map((piece) -> {
                g2d.setColor(Color.BLACK);
                return piece;
            }).forEachOrdered((piece) -> {
                g2d.draw(tx.createTransformedShape(piece));
            });
            if (Controleur.getInstance().isAjouteetageMode()) {
                g2d.setColor(Color.BLACK);
                g2d.draw(tx.createTransformedShape(RectFantomeHorizontal_1));
                g2d.draw(tx.createTransformedShape(RectFantomeHorizontal_2));
                g2d.draw(tx.createTransformedShape(RectFantomeHorizontal_3));
                for (Etage etage : Controleur.getInstance().getEtagere().getListeetages()) {
                    if (etage.contains(this.RectFantomeHorizontal_1)) {
                        g2d.setColor(Color.GREEN);
                        g2d.fill(tx.createTransformedShape(this.RectFantomeHorizontal_1));
                        g2d.fill(tx.createTransformedShape(this.RectFantomeHorizontal_2));
                        g2d.fill(tx.createTransformedShape(this.RectFantomeHorizontal_3));
                        break;
                    } else {
                        g2d.setColor(Color.RED);
                        g2d.fill(tx.createTransformedShape(this.RectFantomeHorizontal_1));
                        g2d.fill(tx.createTransformedShape(this.RectFantomeHorizontal_2));
                        g2d.fill(tx.createTransformedShape(this.RectFantomeHorizontal_3));
                    }

                }
            }
            if (Controleur.getInstance().isAjouteCaissonMode()) {
                for (Etage etage : Controleur.getInstance().getEtagere().getListeetages()){
                    if(etage.contains(this.RectFantomeVertical_1.getX(), this.RectFantomeVertical_1.getY())){
                        this.RectFantomeVertical_1.setRect(this.RectFantomeVertical_1.getX(), this.RectFantomeVertical_1.getY(), this.RectFantomeVertical_1.getWidth(), 
                                etage.getHauteur_rel()*Controleur.getInstance().getEtagere().getHauteur()+0.5*2.54);
                        this.RectFantomeVertical_2.setRect(this.RectFantomeVertical_2.getX(), this.RectFantomeVertical_2.getY(), this.RectFantomeVertical_2.getWidth(), 
                                etage.getHauteur_rel()*Controleur.getInstance().getEtagere().getHauteur()+ 0.5 * 2.54);
                        this.RectFantomeVertical_3.setRect(this.RectFantomeVertical_3.getX(), this.RectFantomeVertical_3.getY(), this.RectFantomeVertical_3.getWidth(), 
                                etage.getHauteur_rel()*Controleur.getInstance().getEtagere().getHauteur()+ 0.5 * 2.54);
                        g2d.setColor(Color.BLACK);
                        g2d.draw(tx.createTransformedShape(this.RectFantomeVertical_1));
                        g2d.draw(tx.createTransformedShape(this.RectFantomeVertical_2));
                        g2d.draw(tx.createTransformedShape(this.RectFantomeVertical_3));
                        g2d.setColor(Color.GREEN);
                        g2d.fill(tx.createTransformedShape(this.RectFantomeVertical_1));
                        g2d.fill(tx.createTransformedShape(this.RectFantomeVertical_2));
                        g2d.fill(tx.createTransformedShape(this.RectFantomeVertical_3));
                        break;
                        
                    }
                    else{
                        g2d.setColor(Color.BLACK);
                        g2d.draw(tx.createTransformedShape(this.RectFantomeVertical_1));
                        g2d.draw(tx.createTransformedShape(this.RectFantomeVertical_2));
                        g2d.draw(tx.createTransformedShape(this.RectFantomeVertical_3));
                        g2d.setColor(Color.RED);
                        g2d.fill(tx.createTransformedShape(this.RectFantomeVertical_1));
                        g2d.fill(tx.createTransformedShape(this.RectFantomeVertical_2));
                        g2d.fill(tx.createTransformedShape(this.RectFantomeVertical_3));
                    }
                }

            }
        }
    }
}
