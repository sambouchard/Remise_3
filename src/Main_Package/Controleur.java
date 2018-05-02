/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

import UI.AfficheurEtagere2D;
import UI.GUI;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Singleton Controleur
 *
 */
public class Controleur {

    private Etagere etagere;
    private boolean mesureMetrique = true;
    private AfficheurEtagere2D afficheur;
    private static final Controleur instance = new Controleur();
    private Piece pieceSelectionner = null;
    private GUI Vue;

    private void didMutateEtagere() {
        UndoRedoStore.addMutation(etagere);
        this.afficheur.redraw();
    }

    public void undo() {
        try {
            Etagere e = UndoRedoStore.undo();
            this.etagere = e;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Cannot Undo anymore!", "Whoops",
                    JOptionPane.ERROR_MESSAGE);
        }
        this.setCaissonSelectionne(null);
        this.etageSelectionne = null;
        this.montantEtageHorizontalSelectionne = null;
        this.pieceSelectionner = null;
        this.MontantVerticalSelectionne = null;
        this.afficheur.redraw();
    }

    public void redo() {
        try {
            Etagere e = UndoRedoStore.redo();
            this.etagere = e;
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null, "Cannot Redo anymore!", "Whoops",
                    JOptionPane.ERROR_MESSAGE);
        }
        this.afficheur.redraw();
    }

    public Etage getEtageSelectionne() {
        return etageSelectionne;
    }

    public void setEtageSelectionne(Etage etageSelectionne) {
        this.etageSelectionne = etageSelectionne;
    }

    public Caisson getCaissonSelectionne() {
        return CaissonSelectionne;
    }

    public void setCaissonSelectionne(Caisson CaissonSelectionne) {
        this.CaissonSelectionne = CaissonSelectionne;
    }
    private Etage etageSelectionne = null;
    private Caisson CaissonSelectionne = null;
    private MontantCaissonVertical MontantVerticalSelectionne = null;
    private MontantEtageHorizontal montantEtageHorizontalSelectionne = null;

    public MontantEtageHorizontal getMontantEtageHorizontalSelectionne() {
        return montantEtageHorizontalSelectionne;
    }

    public void setMontantEtageHorizontalSelectionne(MontantEtageHorizontal montantEtageHorizontal) {
        this.montantEtageHorizontalSelectionne = montantEtageHorizontal;
        this.MontantVerticalSelectionne = null;
        this.afficheur.redraw();
    }

    public MontantCaissonVertical getMontantVerticalSelectionne() {
        return MontantVerticalSelectionne;
    }

    public void setMontantVerticalSelectionne(MontantCaissonVertical MontantVerticalSelectionne) {
        this.MontantVerticalSelectionne = MontantVerticalSelectionne;
        this.montantEtageHorizontalSelectionne = null;
        this.afficheur.redraw();
    }

    public boolean isAjouteetageMode() {
        return ajouteetageMode;
    }

    public void setAjouteetageMode(boolean ajouteetageMode) {
        this.ajouteetageMode = ajouteetageMode;

    }

    public boolean isAjouteCaissonMode() {
        return ajouteCaissonMode;
    }

    public void setAjouteCaissonMode(boolean ajouteCaissonMode) {
        this.ajouteCaissonMode = ajouteCaissonMode;
    }
    private boolean ajouteetageMode = false;
    private boolean ajouteCaissonMode = false;

    public Etagere getUndoEtagere() {
        return UndoEtagere;
    }

    private Etagere UndoEtagere;

    public GUI getVue() {
        return Vue;
    }

    public void setVue(GUI Vue) {
        this.Vue = Vue;
    }

    public static Controleur getInstance() {
        return instance;
    }

    private Controleur() {
    }

    /**
     * @return the etagere
     */
    public Etagere getEtagere() {
        return etagere;
    }

    public void setTriple(Boolean triple) {
        this.etagere.setPerimetretriple(triple);
        this.etagere.GenererPieces();
        this.afficheur.redraw();
    }

    public void setDepasse(Boolean depasse) {
        this.etagere.setPiecedepasse(depasse);
        this.etagere.GenererPieces();
        this.afficheur.redraw();
    }

    /**
     * @param etagere the etagere to set
     */
    public void setEtagere(Etagere etagere) {
        this.etagere = etagere;
        didMutateEtagere();
    }

    public void updatevuImperiale() {
        this.Vue.getHauteur_Textfield().setText(String.format("%.2f", this.etagere.getHauteur() / 2.54));
        this.Vue.getLargeur_TextField().setText(String.format("%.2f", this.etagere.getLargeur() / 2.54));
        this.Vue.getProfondeur_TextField().setText(String.format("%.2f", this.etagere.getProfondeur() / 2.54));
        this.Vue.getHauteurPieceSelecrtionneField().setText(String.format("%.2f", this.pieceSelectionner.getHauteur() / 2.54));
        this.Vue.getLargeurPieceSelecrtionneField().setText(String.format("%.2f", this.pieceSelectionner.getLargeur() / 2.54));
        this.Vue.getProfondeurPieceSelecrtionneField().setText(String.format("%.2f", this.pieceSelectionner.getProfondeur() / 2.54));
    }

    public void updatevue() {
        this.Vue.getHauteur_Textfield().setText(String.format("%.2f", this.etagere.getHauteur()));
        this.Vue.getLargeur_TextField().setText(String.format("%.2f", this.etagere.getLargeur()));
        this.Vue.getProfondeur_TextField().setText(String.format("%.2f", this.etagere.getProfondeur()));
        if (getEtageSelectionne() != null) {
            this.Vue.getHauteurRelEtage_Field().setText(String.valueOf(String.valueOf(BigDecimal.valueOf(Controleur.getInstance().getEtageSelectionne().getHauteur_rel())
                    .setScale(3, RoundingMode.HALF_UP)
                    .doubleValue())));
        }
        if (getCaissonSelectionne() != null) {
            this.Vue.getLargeurRelCaisson_Field().setText(String.valueOf(BigDecimal.valueOf(Controleur.getInstance().getCaissonSelectionne().getLargeurRel())
                    .setScale(3, RoundingMode.HALF_UP)
                    .doubleValue()));
        }

    }

    public void setLrelativeCaissonSelectionne(double newLrel) {
        if (CaissonSelectionne.getId() != etageSelectionne.getListecaissons().length - 1) {
            try {
                double difference = newLrel - CaissonSelectionne.getLargeurRel();
                Double newLrelCaissondroit = etageSelectionne.getListecaissons()[CaissonSelectionne.getId() + 1].getLargeurRel() - difference;
                if (newLrelCaissondroit < 0) {
                    throw new ArithmeticException("E est negatif!");
                } else {
                    CaissonSelectionne.setLargeurRel(newLrel);
                    etageSelectionne.getListecaissons()[CaissonSelectionne.getId() + 1].setLargeurRel(newLrelCaissondroit);
                    etagere.GenererPieces();
                    afficheur.redraw();
                }
            } catch (Exception ex) {
                System.out.println("error: e est negatif");
            }
        } else if (CaissonSelectionne.getId() == etageSelectionne.getListecaissons().length - 1) {
            try {
                double difference = newLrel - CaissonSelectionne.getLargeurRel();
                Double newLrelCaissondroit = etageSelectionne.getListecaissons()[CaissonSelectionne.getId() - 1].getLargeurRel() - difference;
                if (newLrelCaissondroit < 0) {
                    throw new ArithmeticException("E est negatif!");
                } else {
                    CaissonSelectionne.setLargeurRel(newLrel);
                    etageSelectionne.getListecaissons()[CaissonSelectionne.getId() - 1].setLargeurRel(newLrelCaissondroit);
                    etagere.GenererPieces();
                    afficheur.redraw();

                }
            } catch (Exception ex) {
                System.out.println("error: e est negatif");
            }
        }
    }

    public void setHrelativeEtageSelectionnee(double newHrel) {
        if (etageSelectionne.id != etagere.getListeetages().length - 1) {
            try {
                double difference = newHrel - etageSelectionne.getHauteur_rel();
                Double newHrelEtagedessous = etagere.getListeetages()[etageSelectionne.id + 1].getHauteur_rel() - difference;
                if (newHrelEtagedessous < 0) {
                    throw new ArithmeticException("E est negatif!");
                } else {
                    etageSelectionne.setHauteur_rel(newHrel);
                    etagere.getListeetages()[etageSelectionne.id + 1].setHauteur_rel(newHrelEtagedessous);
                    etagere.GenererPieces();
                    afficheur.redraw();

                }
            } catch (Exception ex) {
                System.out.println("error: e est negatif");
            }

        } else if (etageSelectionne.id == etagere.getListeetages().length - 1) {
            try {
                double difference = newHrel - etageSelectionne.getHauteur_rel();
                Double newHrelEtagedessous = etagere.getListeetages()[etageSelectionne.id - 1].getHauteur_rel() - difference;
                if (newHrelEtagedessous < 0) {
                    throw new ArithmeticException("E est negatif!");
                } else {
                    etageSelectionne.setHauteur_rel(newHrel);
                    etagere.getListeetages()[etageSelectionne.id - 1].setHauteur_rel(newHrelEtagedessous);
                    etagere.GenererPieces();
                    afficheur.redraw();

                }
            } catch (Exception ex) {
                System.out.println("error: e est negatif");
            }

        }
    }

    public void createNewEtagere(double hauteur, double largeur, double profondeur, int nb_etages,
            boolean estfermee, boolean piecedepasse, boolean perimetretriple) {
        Etagere e = new Etagere(hauteur, largeur, profondeur, nb_etages, estfermee, piecedepasse, perimetretriple);
        setEtagere(e);
    }

    public void styleBackground(boolean estferme) {
        getEtagere().setestferme(estferme);
    }

    public boolean getMesureMetrique() {
        return mesureMetrique;
    }

    public void setMesureMetrique(boolean isMetrique) {
        this.mesureMetrique = isMetrique;
    }

    public Piece getPieceSelectionner() {
        return pieceSelectionner;
    }

    public void setPieceSelectionner(Piece piece) {
        if (piece == null) {
            return;
        }
        pieceSelectionner = piece;
        if (mesureMetrique) {
            this.Vue.getLargeurPieceSelecrtionneField().setText(String.format("%.2f", piece.getLargeur()));
            this.Vue.getHauteurPieceSelecrtionneField().setText(String.format("%.2f",piece.getHauteur()));
            this.Vue.getProfondeurPieceSelecrtionneField().setText(String.format("%.2f",piece.getProfondeur()));
        } else {
            this.Vue.getLargeurPieceSelecrtionneField().setText(String.format("%.2f", piece.getLargeur() / 2.54));
            this.Vue.getHauteurPieceSelecrtionneField().setText(String.format("%.2f",piece.getHauteur() / 2.54));
            this.Vue.getProfondeurPieceSelecrtionneField().setText(String.format("%.2f",piece.getProfondeur() / 2.54));
        }
    }

    /**
     *
     * @param largeur
     */
    public void setEtagereLargeur(double largeur) {
        getEtagere().setLargeur(largeur);
        etagere.GenererPieces();
        didMutateEtagere();
    }

    public void setEtagereHauteur(double hauteur) {
        etagere.setHauteur(hauteur);
        etagere.GenererPieces();
        didMutateEtagere();
    }

    public void setEtagereProfondeur(double profondeur) {
        getEtagere().setProfondeur(profondeur);
        etagere.GenererPieces();
        didMutateEtagere();
    }

    /**
     * @return the afficheur
     */
    public AfficheurEtagere2D getAfficheur() {
        return afficheur;
    }

    /**
     * @param afficheur the afficheur to set
     */
    public void setAfficheur(AfficheurEtagere2D afficheur) {
        this.afficheur = afficheur;
    }

    /**
     *
     * @return A string containing a formated list of all the Pieces, used to
     * export to txt
     */
    public String getStrListePieces() {
        String out;
        if (mesureMetrique) {
            out = etagere.toStringMetrique();
        } else {
            out = etagere.toStringImperial();
        }
        return out;
    }

    public void getPlanDeCoupe(File file) {
        Export.genererPlanDeCoupe2(file);
    }

    public void AjouteEtage(Etage etage, double NewY) {
        double hdispo;
        etagere.setNb_etages(etagere.getNb_etages() + 1);
        if (etagere.isPerimetretriple()) {
            hdispo = etagere.getHauteur() - 6 * etagere.getEpaisseurTriple() - (etagere.getNb_etages() - 1) * 3 * etagere.getEpaisseurTriple();
        } else {
            hdispo = etagere.getHauteur() - 4 * etagere.getEpaisseurDouble() - (etagere.getNb_etages() - 1) * 3 * etagere.getEpaisseurTriple();
        }

        double oldhrel = etage.getHauteur_rel();
        Double newhrel = (NewY - etage.getY()) / hdispo;
        etage.setHauteur_rel(newhrel);
        Etage newetage = new Etage(oldhrel - newhrel);
        Etage[] newlistetage = new Etage[etagere.getNb_etages()];
        newlistetage[etage.getId() + 1] = newetage;
        newetage.setId(etage.getId() + 1);
        for (Etage etages : etagere.getListeetages()) {
            if (etages.getId() <= etage.getId()) {
                newlistetage[etages.getId()] = etages;
            } else if (etages.getId() >= newetage.getId()) {
                newlistetage[etages.getId() + 1] = etages;
            }
        }

        etagere.setListeetages(newlistetage);
        etagere.GenererPieces();
        setAjouteetageMode(false);
        didMutateEtagere();
    }

    public void AjouteCaisson(Caisson caisson, Etage etage, double NewX) {
        double largeur_dispo;
        etage.setNb_Caisson(etage.getNb_Caisson() + 1);
        double oldLrel = caisson.getLargeurRel();
        if (etagere.isPerimetretriple()) {
            largeur_dispo = etagere.getLargeur() - 6 * etagere.getEpaisseurTriple() + (etage.getNb_Caisson() - 1) * 3 * etagere.getEpaisseurTriple();
        } else {
            largeur_dispo = etagere.getLargeur() - 4 * etagere.getEpaisseurDouble() + (etage.getNb_Caisson() - 1) * 3 * etagere.getEpaisseurTriple();
        }
        double newLrel = (NewX - caisson.getX()) / largeur_dispo;
        caisson.setLargeurRel(newLrel);
        Caisson newcaisson = new Caisson(oldLrel - newLrel);
        Caisson[] newlistCaisson = new Caisson[etage.getNb_Caisson()];
        newlistCaisson[caisson.getId() + 1] = newcaisson;
        newcaisson.setId(caisson.getId() + 1);
        for (Caisson caissons : etage.getListecaissons()) {
            if (caissons.getId() <= caisson.getId()) {
                newlistCaisson[caissons.getId()] = caissons;
            } else if (caissons.getId() >= caisson.getId()) {
                newlistCaisson[caissons.getId() + 1] = caissons;
            }
        }

        etage.setListecaissons(newlistCaisson);
        etagere.GenererPieces();
        setAjouteCaissonMode(false);
        didMutateEtagere();
    }

    public void SupprimeMontantVertical() {
        MontantVerticalSelectionne.getEtageconteneur().setNb_Caisson(MontantVerticalSelectionne.getEtageconteneur().getNb_Caisson() - 1);
        MontantVerticalSelectionne.getCaisson_gauche().setLargeurRel(MontantVerticalSelectionne.getCaisson_gauche().getLargeurRel()
                + MontantVerticalSelectionne.getCaisson_droite().getLargeurRel());
        Caisson[] newlist = new Caisson[MontantVerticalSelectionne.getEtageconteneur().getNb_Caisson()];
        for (Caisson caisson : MontantVerticalSelectionne.getEtageconteneur().getListecaissons()) {
            if (caisson.getId() < MontantVerticalSelectionne.getCaisson_droite().getId()) {
                newlist[caisson.getId()] = caisson;
            } else if (caisson.getId() > MontantVerticalSelectionne.getCaisson_droite().getId()) {
                newlist[caisson.getId() - 1] = caisson;
            }
        }
        MontantVerticalSelectionne.getEtageconteneur().setListecaissons(newlist);
        etagere.GenererPieces();
        setMontantVerticalSelectionne(null);
        didMutateEtagere();
    }

    public void SupprimeMontantHorizontal() {
        this.etagere.setNb_etages(this.etagere.getNb_etages() - 1);
        montantEtageHorizontalSelectionne.getEtage_dessus().setHauteur_rel(montantEtageHorizontalSelectionne.getEtage_dessus().getHauteur_rel()
                + montantEtageHorizontalSelectionne.getEtage_dessous().getHauteur_rel());
        Etage[] newlistEtages = new Etage[this.etagere.getNb_etages()];
        for (Etage etage : this.etagere.getListeetages()) {
            if (etage.id < montantEtageHorizontalSelectionne.getEtage_dessous().getId()) {
                newlistEtages[etage.getId()] = etage;
            } else if (etage.id > montantEtageHorizontalSelectionne.getEtage_dessous().getId()) {
                newlistEtages[etage.getId() - 1] = etage;
            }
        }
        this.etagere.setListeetages(newlistEtages);
        etagere.GenererPieces();
        setMontantEtageHorizontalSelectionne(null);
        didMutateEtagere();
    }

    public void SupprimeEtage() {
        if(etagere.getNb_etages()>1){
            Etage[] newlist;
            
            if (etageSelectionne.getId() == 0) {
                etagere.setNb_etages(etagere.getNb_etages() - 1);
                newlist = new Etage[etagere.getNb_etages()];
                etagere.getListeetages()[etageSelectionne.id + 1].setHauteur_rel(etageSelectionne.getHauteur_rel() + etagere.getListeetages()[etageSelectionne.id + 1].getHauteur_rel());
                for (Etage etage : etagere.getListeetages()) {
                    if (etage.getId() < etageSelectionne.getId()) {
                        newlist[etage.getId()] = etage;
                    } else if (etage.id > etageSelectionne.getId()) {
                        newlist[etage.getId() - 1] = etage;
                    }
                }

            } else if (etageSelectionne.id == etagere.getNb_etages() - 1) {
                etagere.setNb_etages(etagere.getNb_etages() - 1);
                newlist = new Etage[etagere.getNb_etages()];
                etagere.getListeetages()[etageSelectionne.id - 1 ].setHauteur_rel(etageSelectionne.getHauteur_rel() + etagere.getListeetages()[etageSelectionne.id - 1].getHauteur_rel());
                for (Etage etage : etagere.getListeetages()) {
                    if (etage.getId() < etageSelectionne.getId()) {
                        newlist[etage.getId()] = etage;
                    } else if (etage.id > etageSelectionne.getId()) {
                        newlist[etage.getId() - 1] = etage;
                    }
                }
            } else {
                etagere.setNb_etages(etagere.getNb_etages() - 1);
                newlist = new Etage[etagere.getNb_etages()];
                etagere.getListeetages()[etageSelectionne.id + 1].setHauteur_rel(etageSelectionne.getHauteur_rel()/2 + etagere.getListeetages()[etageSelectionne.id + 1].getHauteur_rel());
                etagere.getListeetages()[etageSelectionne.id - 1].setHauteur_rel(etageSelectionne.getHauteur_rel()/2 + etagere.getListeetages()[etageSelectionne.id - 1].getHauteur_rel());
                for (Etage etage : etagere.getListeetages()) {
                    if (etage.getId() < etageSelectionne.getId()) {
                        newlist[etage.getId()] = etage;
                    } else if (etage.id > etageSelectionne.getId()) {
                        newlist[etage.getId() - 1] = etage;
                    }
                }

            }
            this.etagere.setListeetages(newlist);
            etagere.GenererPieces();
            setEtageSelectionne(null);
            setCaissonSelectionne(null);
            updatevue();
            didMutateEtagere();
        }

    }

    public void getSTL() {
        STLExporter exporter = new STLExporter(etagere);
        String modele = exporter.getSTL();

        JFileChooser explorer = new JFileChooser();
        int ack = explorer.showSaveDialog(null);
        if (JFileChooser.APPROVE_OPTION == ack) {
            try {
                FileWriter writer = new FileWriter(explorer.getSelectedFile() + ".stl");
                writer.write(modele);
                writer.close();
            } catch (Exception ex) {
                return;
            }
        }
    }

    public void getIndividualSTL() {
        STLExporter exporter = new STLExporter(etagere);
        ArrayList<String> STLpieces = exporter.getPiecesSTLs();

        JFileChooser explorer = new JFileChooser();
        int ack = explorer.showSaveDialog(null);

        if (JFileChooser.APPROVE_OPTION == ack) {
            try {
                for (int i = 0; i < STLpieces.size(); i++) {
                    int j = i + 1;
                    FileWriter writer = new FileWriter(explorer.getSelectedFile() + Integer.toString(j) + Controleur.getInstance().getEtagere().getListe_piece().get(i).getNom() + ".stl");
                    writer.write(STLpieces.get(i));
                    writer.close();
                }
            } catch (Exception ex) {
                return;
            }
        }
    }

}
