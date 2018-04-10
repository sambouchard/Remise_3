/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javax.swing.JOptionPane;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author charlesc
 */
public class ViewerPanel extends javax.swing.JPanel {
    
    /**
     * Creates new form Viewer
     */
    public ViewerPanel() {
        ElementsList = new ArrayList();
        // Added for testing
        ElementsList.add(etage1);
        ElementsList.add(etage2);
        initListeners();
    }

    private void initListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                super.mouseClicked(evt);
                for (Shape element: ElementsList) {
                    if (element.contains(evt.getPoint())) {
                        JOptionPane.showMessageDialog(null, "Clicked " + element.getClass().getName());
                    } else {
                        JOptionPane.showMessageDialog(null, "Clicked outside");
                    }
                }
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D context = (Graphics2D) g;
        ElementsList.forEach((element) -> {
            context.draw(element);
        });
    }
    
    // Shapes created for testing
    private final Shape etage1 = new Rectangle2D.Double(150, 150, 200, 150);
    private final Shape etage2 = new Rectangle2D.Double(350, 250, 500, 450);
    private final ArrayList<Shape> ElementsList;
}