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
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

    public MontantCaissonVertical getMontantVerticalSelectionne() {
        return MontantVerticalSelectionne;
    }

    public void setMontantVerticalSelectionne(MontantCaissonVertical MontantVerticalSelectionne) {
        this.MontantVerticalSelectionne = MontantVerticalSelectionne;
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

    public void setUndoEtagere() {
        Etagere newetaGere = this.etagere;
        this.UndoEtagere = newetaGere;
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
        this.afficheur.redraw();
    }

    public void updatevuImperiale() {
        this.Vue.getHauteur_Textfield().setText(String.valueOf(BigDecimal.valueOf(this.etagere.getHauteur() / 2.54)
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue()));
        this.Vue.getLargeur_TextField().setText(String.valueOf(BigDecimal.valueOf(this.etagere.getLargeur() / 2.54)
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue()));
        this.Vue.getProfondeur_TextField().setText(String.valueOf(BigDecimal.valueOf(this.etagere.getProfondeur() / 2.54)
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue()));
    }

    public void updatevue() {
        this.Vue.getHauteur_Textfield().setText(String.valueOf(this.etagere.getHauteur()));
        this.Vue.getLargeur_TextField().setText(String.valueOf(this.etagere.getLargeur()));
        this.Vue.getProfondeur_TextField().setText(String.valueOf(this.etagere.getProfondeur()));
        if (getEtageSelectionne() != null) {
            this.Vue.getHauteurRelEtage_Field().setText(String.valueOf(String.valueOf(BigDecimal.valueOf(Controleur.getInstance().getEtageSelectionne().getHauteur_rel())
                    .setScale(3, RoundingMode.HALF_UP)
                    .doubleValue())));
        }
        if(getCaissonSelectionne()!=null){
            this.Vue.getLargeurRelCaisson_Field().setText(String.valueOf(BigDecimal.valueOf(Controleur.getInstance().getCaissonSelectionne().getLargeurRel())
                    .setScale(3, RoundingMode.HALF_UP)
                    .doubleValue()));
        }

    }
    public void setLrelativeCaissonSelectionne(double newLrel){
        if(CaissonSelectionne.getId() != etageSelectionne.getListecaissons().length-1){
            try {
                double difference = newLrel - CaissonSelectionne.getLargeurRel();
                Double newLrelCaissondroit = etageSelectionne.getListecaissons()[CaissonSelectionne.getId()+ 1].getLargeurRel() - difference;
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
        }
        else if(CaissonSelectionne.getId() == etageSelectionne.getListecaissons().length-1){
            try {
                double difference = newLrel - CaissonSelectionne.getLargeurRel();
                Double newLrelCaissondroit = etageSelectionne.getListecaissons()[CaissonSelectionne.getId()- 1].getLargeurRel() - difference;
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
        pieceSelectionner = piece;
        this.Vue.getLargeurPieceSelecrtionneField().setText(String.valueOf(piece.getLargeur()));
        this.Vue.getHauteurPieceSelecrtionneField().setText(String.valueOf(piece.getHauteur()));
        this.Vue.getProfondeurPieceSelecrtionneField().setText(String.valueOf(piece.getProfondeur()));
    }

    

    public void enleveCaisson(int caissonid, int etageid) {
        if (caissonid != -1 || etageid != -1) {
            getEtagere().getListeetages()[etageid].SupprimeCaisson(caissonid);
        }
    }

    public void ajouteCaisson(double l_rel, int index) {
        getEtagere().getListeetages()[index].AjouteCaisson(l_rel);

    }

    /**
     *
     * @param largeur
     */
    public void setEtagereLargeur(double largeur) {
        getEtagere().setLargeur(largeur);
        etagere.GenererPieces();
    }

    public void setEtagereHauteur(double hauteur) {
        setUndoEtagere();
        etagere.setHauteur(hauteur);
        etagere.GenererPieces();
    }

    public void setEtagereProfondeur(double profondeur) {
        getEtagere().setProfondeur(profondeur);
        etagere.GenererPieces();
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

    public void undo() {
        if (this.UndoEtagere != null) {
            this.setEtagere(this.UndoEtagere);
            afficheur.redraw();
        } else {
            System.out.println("Undo etagere nexiste pas");
        }
    }

    public void sauvegarderEtagere() {
        String nom = (String) JOptionPane.showInputDialog(null, "Nommez votre étagère");

        if (!nom.isEmpty()) {
            try {
                FileOutputStream fileout = new FileOutputStream("etageresSauvegardees\\" + nom + ".ser");
                ObjectOutputStream out = new ObjectOutputStream(fileout);
                out.writeObject(etagere);
                out.close();
                fileout.close();
            } catch (IOException i) {
                JOptionPane.showMessageDialog(null, "L'étagère n'a pas pu être sauvegardée.");
            }
        }

    }

    public void chargerEtagere() {

        File etageresSauvegardees = new File("etageresSauvegardees\\");

        String[] listeEnregistrees = etageresSauvegardees.list();

        String nom = (String) JOptionPane.showInputDialog(null, "Choisissez votre étagère.", "Charger une étagère", JOptionPane.PLAIN_MESSAGE, null, listeEnregistrees, null);
        if (!nom.isEmpty()) {
            try {
                FileInputStream fileIn = new FileInputStream("etageresSauvegardees\\" + nom);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                Etagere etagerechargee = (Etagere) in.readObject();
                setEtagere(etagerechargee);
                in.close();
                fileIn.close();
            } catch (IOException | ClassNotFoundException i) {
                JOptionPane.showMessageDialog(null, "L'étagère n'a pas pu être chargée.");
            }
        }
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
        Export.genererPlanDeCoupe(file);
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
            }
            else if(etages.getId()>=newetage.getId()){
                newlistetage[etages.getId()+1] = etages;
            }
        }

        etagere.setListeetages(newlistetage);
        etagere.GenererPieces();
        setAjouteetageMode(false);
        afficheur.redraw();

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
        double newLrel = (NewX-caisson.getX())/largeur_dispo;
        caisson.setLargeurRel(newLrel);
        Caisson newcaisson = new Caisson(oldLrel-newLrel);
        Caisson [] newlistCaisson = new Caisson[etage.getNb_Caisson()];
        newlistCaisson[caisson.getId()+1] = newcaisson;
        newcaisson.setId(caisson.getId()+1);
        for(Caisson caissons:etage.getListecaissons()){
            if(caissons.getId()<=caisson.getId()){
                newlistCaisson[caissons.getId()] = caissons;
            }
            else if(caissons.getId() >= caisson.getId()){
                newlistCaisson[caissons.getId()+1] = caissons;
            }
        }
        
        etage.setListecaissons(newlistCaisson);
        etagere.GenererPieces();
        setAjouteCaissonMode(false);
        afficheur.redraw();
    }
    
    public void SupprimeMontantVertical(){
        MontantVerticalSelectionne.getEtageconteneur().setNb_Caisson(MontantVerticalSelectionne.getEtageconteneur().getNb_Caisson()-1);
        MontantVerticalSelectionne.getCaisson_gauche().setLargeurRel(MontantVerticalSelectionne.getCaisson_gauche().getLargeurRel()+
                MontantVerticalSelectionne.getCaisson_droite().getLargeurRel());
        Caisson[] newlist = new Caisson[MontantVerticalSelectionne.getEtageconteneur().getNb_Caisson()];
        for(Caisson caisson: MontantVerticalSelectionne.getEtageconteneur().getListecaissons()){
            if(caisson.getId() < MontantVerticalSelectionne.getCaisson_droite().getId()){
                newlist[caisson.getId()] = caisson;
            }
            else if(caisson.getId()>MontantVerticalSelectionne.getCaisson_droite().getId()){
                newlist[caisson.getId()-1] = caisson;
            }
        }
        MontantVerticalSelectionne.getEtageconteneur().setListecaissons(newlist);
        etagere.GenererPieces();
        setMontantVerticalSelectionne(null);
        afficheur.redraw();
    }
}
