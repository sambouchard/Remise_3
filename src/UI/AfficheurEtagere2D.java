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

import Main_Package.Controleur;
import Main_Package.Etage;
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
                for (Piece piece : Controleur.getInstance().getEtagere().getListe_piece()) {
                    if (piece.contains(pointInEtagereCoordSpace)) {
                        Controleur.getInstance().setPieceSelectionner(piece);
                    }
                }
                for (Etage etage: Controleur.getInstance().getEtagere().getListeetages()){
                    if(etage.contains(pointInEtagereCoordSpace)){
                        Controleur.getInstance().setEtageSelectionne(etage);
                        System.out.println(etage.getId());
                        Controleur.getInstance().updatevue();
                    }
                }
                if (Controleur.getInstance().isAjouteCaissonMode()) {

                }
                if (Controleur.getInstance().isAjouteetageMode()) {

                }
            }

            @Override
            public void mousePressed(MouseEvent m) {

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

        private int x;

        private int y;

        @Override
        public void mousePressed(MouseEvent e) {
            x = e.getX();
            y = e.getY();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if (Controleur.getInstance().getEtagere() != null) {
                if (Controleur.getInstance().isAjouteetageMode()) {
                    Controleur.getInstance().getAfficheur().setRectFantomeHorizontal_1(new Rectangle2D.Double(e.getX(), e.getY(),
                            Controleur.getInstance().getEtagere().getLargeur(), 0.5 * 2.5));
                    Controleur.getInstance().getAfficheur().setRectFantomeHorizontal_2(new Rectangle2D.Double(e.getX(), e.getY() + 0.5 * 2.5,
                            Controleur.getInstance().getEtagere().getLargeur(), 0.5 * 2.5));
                    Controleur.getInstance().getAfficheur().setRectFantomeHorizontal_3(new Rectangle2D.Double(e.getX(), e.getY() + 2 * (0.5 * 2.5),
                            Controleur.getInstance().getEtagere().getLargeur(), 0.5 * 2.5));
                    repaint();

                }
                if (Controleur.getInstance().isAjouteCaissonMode()) {
                    Controleur.getInstance().getAfficheur().setRectFantomeVertical_1(new Rectangle2D.Double(e.getX(), e.getY(),
                            0.5 * 2.5, 40));
                    Controleur.getInstance().getAfficheur().setRectFantomeVertical_2(new Rectangle2D.Double(e.getX() + 0.5 * 2.5, e.getY() - 0.5 * 2.5,
                            0.5 * 2.5, 40 + 0.5 * 2.5));
                    Controleur.getInstance().getAfficheur().setRectFantomeVertical_3(new Rectangle2D.Double(e.getX() + 2 * (0.5 * 2.5), e.getY(),
                            0.5 * 2.5, 40));
                    repaint();

                }

            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            int dx = e.getX() - x;
            int dy = e.getY() - y;

            for (Piece piece : Controleur.getInstance().getEtagere().getListe_Piece_Etage_Horizontale()) {
                Point2D p1 = e.getPoint();
                Point2D p2 = null;
                try {
                    p2 = tx.inverseTransform(p1, null);
                } catch (NoninvertibleTransformException ex) {
                    return;
                }
                if (piece.contains(p2)) {
                    piece.getDrawingcoin().setCoord_y(piece.getDrawingcoin().getCoord_y() + dy);
                    repaint();
                    break;
                }
            }
            for (Piece piece : Controleur.getInstance().getEtagere().getListe_piece()) {
                piece.getDrawingcoin().setCoord_x(piece.getDrawingcoin().getCoord_x() + dx / 10);
                piece.getDrawingcoin().setCoord_y(piece.getDrawingcoin().getCoord_y() + dy / 10);
                
            }
            for(Etage etage: Controleur.getInstance().getEtagere().getListeetages()){
                etage.x += dx / 10;
                etage.y += dx / 10;
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
            for(Etage etage: Controleur.getInstance().getEtagere().getListeetages()){
                g2d.setColor(Color.RED);
                g2d.fill(tx.createTransformedShape(etage));
            }

            Controleur.getInstance().getEtagere().getListe_piece().stream().map((piece) -> {
                piece.setRect((piece.getDrawingcoin().getCoord_x()), (piece.getDrawingcoin().getCoord_y()), piece.getLargeur(), piece.getHauteur());
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
                g2d.setColor(Color.RED);
                g2d.fill(tx.createTransformedShape(this.RectFantomeHorizontal_1));
                g2d.fill(tx.createTransformedShape(this.RectFantomeHorizontal_2));
                g2d.fill(tx.createTransformedShape(this.RectFantomeHorizontal_3));
            }
            if (Controleur.getInstance().isAjouteCaissonMode()) {
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
