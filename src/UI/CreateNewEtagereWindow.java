/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Main_Package.Controleur;
import Main_Package.Etagere;
import java.awt.Color;

/**
 *
 * @author SABOU350
 */
public class CreateNewEtagereWindow extends javax.swing.JFrame {

    /**
     * Creates new form CreateNewEtagereWindow
     */
    public CreateNewEtagereWindow() {
        initComponents();
        getContentPane().setBackground(Color.WHITE); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup2 = new javax.swing.ButtonGroup();
        group_perimetre = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Hauteur_field = new javax.swing.JTextField();
        Largeur_field = new javax.swing.JTextField();
        Profondeur_field = new javax.swing.JTextField();
        Mesuremetrique_radio = new javax.swing.JRadioButton();
        Mesureimp_radio = new javax.swing.JRadioButton();
        Hauteur_label = new javax.swing.JLabel();
        Largeur_label = new javax.swing.JLabel();
        Profondeur_label = new javax.swing.JLabel();
        CreateEtagereButton = new javax.swing.JButton();
        Perimetredouble_radio = new javax.swing.JRadioButton();
        Perimetretriple_radio = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Maison Neue", 0, 14)); // NOI18N
        jLabel1.setText("Hauteur");

        jLabel2.setFont(new java.awt.Font("Maison Neue", 0, 14)); // NOI18N
        jLabel2.setText("Largeur");

        jLabel3.setFont(new java.awt.Font("Maison Neue", 0, 14)); // NOI18N
        jLabel3.setText("Profondeur");

        Hauteur_field.setFont(new java.awt.Font("Maison Neue", 0, 14)); // NOI18N
        Hauteur_field.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                Hauteur_fieldCaretUpdate(evt);
            }
        });
        Hauteur_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Hauteur_fieldActionPerformed(evt);
            }
        });
        Hauteur_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Hauteur_fieldKeyTyped(evt);
            }
        });

        Largeur_field.setFont(new java.awt.Font("Maison Neue", 0, 14)); // NOI18N
        Largeur_field.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                Largeur_fieldCaretUpdate(evt);
            }
        });
        Largeur_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Largeur_fieldActionPerformed(evt);
            }
        });
        Largeur_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Largeur_fieldKeyTyped(evt);
            }
        });

        Profondeur_field.setFont(new java.awt.Font("Maison Neue", 0, 14)); // NOI18N
        Profondeur_field.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                Profondeur_fieldCaretUpdate(evt);
            }
        });
        Profondeur_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Profondeur_fieldActionPerformed(evt);
            }
        });
        Profondeur_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Profondeur_fieldKeyTyped(evt);
            }
        });

        buttonGroup2.add(Mesuremetrique_radio);
        Mesuremetrique_radio.setFont(new java.awt.Font("Maison Neue", 0, 14)); // NOI18N
        Mesuremetrique_radio.setSelected(true);
        Mesuremetrique_radio.setText("Mesure métrique");
        Mesuremetrique_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Mesuremetrique_radioItemStateChanged(evt);
            }
        });
        Mesuremetrique_radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mesuremetrique_radioActionPerformed(evt);
            }
        });

        buttonGroup2.add(Mesureimp_radio);
        Mesureimp_radio.setFont(new java.awt.Font("Maison Neue", 0, 14)); // NOI18N
        Mesureimp_radio.setText("Mesure impériale");
        Mesureimp_radio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Mesureimp_radioItemStateChanged(evt);
            }
        });
        Mesureimp_radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mesureimp_radioActionPerformed(evt);
            }
        });

        Hauteur_label.setFont(new java.awt.Font("Maison Neue", 0, 14)); // NOI18N
        Hauteur_label.setText("cm");

        Largeur_label.setFont(new java.awt.Font("Maison Neue", 0, 14)); // NOI18N
        Largeur_label.setText("cm");

        Profondeur_label.setFont(new java.awt.Font("Maison Neue", 0, 14)); // NOI18N
        Profondeur_label.setText("cm");

        CreateEtagereButton.setBackground(new java.awt.Color(76, 175, 80));
        CreateEtagereButton.setFont(new java.awt.Font("Maison Neue", 1, 18)); // NOI18N
        CreateEtagereButton.setForeground(new java.awt.Color(255, 255, 255));
        CreateEtagereButton.setText("Créer");
        CreateEtagereButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 0, true));
        CreateEtagereButton.setBorderPainted(false);
        CreateEtagereButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CreateEtagereButton.setEnabled(false);
        CreateEtagereButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CreateEtagereButtonMouseClicked(evt);
            }
        });

        group_perimetre.add(Perimetredouble_radio);
        Perimetredouble_radio.setFont(new java.awt.Font("Maison Neue", 0, 14)); // NOI18N
        Perimetredouble_radio.setSelected(true);
        Perimetredouble_radio.setLabel("Périmètre triple");
        Perimetredouble_radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Perimetredouble_radioActionPerformed(evt);
            }
        });

        group_perimetre.add(Perimetretriple_radio);
        Perimetretriple_radio.setFont(new java.awt.Font("Maison Neue", 0, 14)); // NOI18N
        Perimetretriple_radio.setLabel("Périmetre double");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(Mesuremetrique_radio)
                    .addComponent(Mesureimp_radio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Largeur_field, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(Largeur_label))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Profondeur_field)
                                .addGap(18, 18, 18)
                                .addComponent(Profondeur_label))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Hauteur_field)
                                .addGap(18, 18, 18)
                                .addComponent(Hauteur_label)))
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Perimetredouble_radio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Perimetretriple_radio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(52, 52, 52))))
            .addGroup(layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(CreateEtagereButton, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Hauteur_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Hauteur_label)))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Largeur_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Largeur_label))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Profondeur_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Profondeur_label))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Mesuremetrique_radio)
                    .addComponent(Perimetredouble_radio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Mesureimp_radio)
                    .addComponent(Perimetretriple_radio))
                .addGap(18, 18, 18)
                .addComponent(CreateEtagereButton, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Hauteur_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Hauteur_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Hauteur_fieldActionPerformed

    private void Profondeur_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Profondeur_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Profondeur_fieldActionPerformed

    private void Largeur_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Largeur_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Largeur_fieldActionPerformed

    private void Mesuremetrique_radioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mesuremetrique_radioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Mesuremetrique_radioActionPerformed

    private void Mesuremetrique_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Mesuremetrique_radioItemStateChanged
        if (Mesuremetrique_radio.isSelected() == true) {
            Largeur_label.setText("cm");
            Hauteur_label.setText("cm");
            Profondeur_label.setText("cm");
            Controleur.getInstance().setMesureMetrique(true);
        }
    }//GEN-LAST:event_Mesuremetrique_radioItemStateChanged

    private void Mesureimp_radioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Mesureimp_radioItemStateChanged
        if (Mesureimp_radio.isSelected() == true) {
            Largeur_label.setText("po");
            Hauteur_label.setText("po");
            Profondeur_label.setText("po");
            Controleur.getInstance().setMesureMetrique(false);
        }
    }//GEN-LAST:event_Mesureimp_radioItemStateChanged

    private void Hauteur_fieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Hauteur_fieldKeyTyped
        char key = evt.getKeyChar();
        if (key != '.') {
            if (!(Character.isDigit(key))) {
                evt.consume();
            }
        }
    }//GEN-LAST:event_Hauteur_fieldKeyTyped

    private void Largeur_fieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Largeur_fieldKeyTyped
        char key = evt.getKeyChar();
        if (key != '.') {
            if (!(Character.isDigit(key))) {
                evt.consume();
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_Largeur_fieldKeyTyped

    private void Profondeur_fieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Profondeur_fieldKeyTyped
        char key = evt.getKeyChar();
        if (key != '.') {
            if (!(Character.isDigit(key))) {
                evt.consume();
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_Profondeur_fieldKeyTyped

    private void Profondeur_fieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_Profondeur_fieldCaretUpdate
        Profondeur_field.setBackground(Color.white);
        if (!(Hauteur_field.getText().equals("")) && !(Largeur_field.getText().equals("")) && !(Profondeur_field.getText().equals(""))) {
            CreateEtagereButton.setEnabled(true);
        } else {
            CreateEtagereButton.setEnabled(false);
        }
        if(DoesValueGoOverThreshold(Profondeur_field.getText())) {
            Profondeur_field.setBackground(Color.red);
            CreateEtagereButton.setEnabled(false);
        }
    }//GEN-LAST:event_Profondeur_fieldCaretUpdate

    private void Largeur_fieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_Largeur_fieldCaretUpdate
        Largeur_field.setBackground(Color.white);
        if (!(Hauteur_field.getText().equals("")) && !(Largeur_field.getText().equals("")) && !(Profondeur_field.getText().equals(""))) {
            CreateEtagereButton.setEnabled(true);
        } else {
            CreateEtagereButton.setEnabled(false);
        }
        if(DoesValueGoOverThreshold(Largeur_field.getText())) {
            Largeur_field.setBackground(Color.red);
            CreateEtagereButton.setEnabled(false);
        }
    }//GEN-LAST:event_Largeur_fieldCaretUpdate

    private void Hauteur_fieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_Hauteur_fieldCaretUpdate
        Hauteur_field.setBackground(Color.white);
        if (!(Hauteur_field.getText().equals("")) && !(Largeur_field.getText().equals("")) && !(Profondeur_field.getText().equals(""))) {
            CreateEtagereButton.setEnabled(true);
        } else {
            CreateEtagereButton.setEnabled(false);
        }
        if(DoesValueGoOverThreshold(Hauteur_field.getText())) {
            Hauteur_field.setBackground(Color.red);
            CreateEtagereButton.setEnabled(false);
        }
    }//GEN-LAST:event_Hauteur_fieldCaretUpdate
    private boolean DoesValueGoOverThreshold(String inputValue) {
        if (inputValue.length() == 0) {
            return false;
        }
        Double value = Double.parseDouble(inputValue);
        if (!Mesuremetrique_radio.isSelected()) {
            value *= 2.54;
        }
        return (value > Etagere.MAX_SIZE);
    }
    private void CreateEtagereButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CreateEtagereButtonMouseClicked
        if (CreateEtagereButton.isEnabled()) {
            boolean isInMeters = Mesuremetrique_radio.isSelected();
            Controleur.getInstance().setMesureMetrique(isInMeters);
            if (isInMeters) {
                Controleur.getInstance().createNewEtagere(Double.parseDouble(this.Hauteur_field.getText()), Double.parseDouble(this.Largeur_field.getText()),
                    Double.parseDouble(this.Profondeur_field.getText()), 1, false, true, Perimetredouble_radio.isSelected());
            } else {
                Controleur.getInstance().createNewEtagere(Double.parseDouble(this.Hauteur_field.getText()) * 2.54, Double.parseDouble(this.Largeur_field.getText()) * 2.54,
                    Double.parseDouble(this.Profondeur_field.getText()) * 2.54, 1, false, true, Perimetredouble_radio.isSelected());
            }
            
            Controleur.getInstance().updatevue();
            Controleur.getInstance().setAjouteetageMode(false);
            Controleur.getInstance().setAjouteCaissonMode(false);
            Controleur.getInstance().setCaissonSelectionne(null);
            Controleur.getInstance().setEtageSelectionne(null);
//            Controleur.getInstance().setPieceSelectionner(null);
            this.setVisible(false);
            this.remove(this);
        }
    }//GEN-LAST:event_CreateEtagereButtonMouseClicked

    private void Perimetredouble_radioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Perimetredouble_radioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Perimetredouble_radioActionPerformed

    private void Mesureimp_radioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mesureimp_radioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Mesureimp_radioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CreateNewEtagereWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateNewEtagereWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateNewEtagereWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateNewEtagereWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateNewEtagereWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CreateEtagereButton;
    private javax.swing.JTextField Hauteur_field;
    private javax.swing.JLabel Hauteur_label;
    private javax.swing.JTextField Largeur_field;
    private javax.swing.JLabel Largeur_label;
    private javax.swing.JRadioButton Mesureimp_radio;
    private javax.swing.JRadioButton Mesuremetrique_radio;
    private javax.swing.JRadioButton Perimetredouble_radio;
    private javax.swing.JRadioButton Perimetretriple_radio;
    private javax.swing.JTextField Profondeur_field;
    private javax.swing.JLabel Profondeur_label;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup group_perimetre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
