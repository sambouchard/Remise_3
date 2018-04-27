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
                        System.out.println(piece.getMinX());
                        System.out.println(piece.getMaxX());
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
        this.etagere = new Etagere(10, 10, 10, 4, true,false,true);
    }

    public void drawing() {
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
        
        for(Piece piece : this.etagere.getListe_piece()){
            piece.setRect((piece.getDrawingcoin().getCoord_x())*10, (piece.getDrawingcoin().getCoord_y())*10, piece.getLargeur()*10, piece.getHauteur()*10 );
            g2d.setColor(Color.BLACK);
            g2d.draw(piece);
            
            
        }

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