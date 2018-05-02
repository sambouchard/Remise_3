/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SABOU350
 */
public class MontantEtageHorizontal implements java.io.Serializable{
    private Etage Etage_dessus;
    private Etage Etage_dessous;
    private List<Piece> Liste_piece = new ArrayList();

    public Etage getEtage_dessus() {
        return Etage_dessus;
    }

    public void setEtage_dessus(Etage Etage_dessus) {
        this.Etage_dessus = Etage_dessus;
    }

    public Etage getIdEtage_dessous() {
        return Etage_dessous;
    }

    public void setEtage_dessous(int IdEtage_dessous) {
        this.Etage_dessous = Etage_dessous;
    }

    public List<Piece> getListe_piece() {
        return Liste_piece;
    }

    public void setListe_piece(List<Piece> Liste_piece) {
        this.Liste_piece = Liste_piece;
    }

    public MontantEtageHorizontal(Etage Etage_dessus, Etage Etage_dessous) {
        this.Etage_dessous = Etage_dessous;
        this.Etage_dessus = Etage_dessus;
    }
    public void addPiece(Piece piece){
        this.Liste_piece.add(piece);
    }

    public Etage getEtage_dessous() {
        return Etage_dessous;
    }

    public void setEtage_dessous(Etage Etage_dessous) {
        this.Etage_dessous = Etage_dessous;
    }
    
    public void applyDy(double dy) {
        Double totalY = Etage_dessous.getHeight() + Etage_dessus.getHeight();
        Double deltaRelatif = dy/totalY;
        Etage_dessous.setHauteur_rel(Etage_dessous.getHauteur_rel() - deltaRelatif);
        Etage_dessus.setHauteur_rel(Etage_dessus.getHauteur_rel() + deltaRelatif);
    }
}
