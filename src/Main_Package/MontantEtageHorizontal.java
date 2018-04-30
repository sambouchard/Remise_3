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
    private int IdEtage_dessus;
    private int IdEtage_dessous;
    private List<Piece> Liste_piece = new ArrayList();

    public MontantEtageHorizontal(int newidEtage_dessus, int newIdEtage_dessous) {
        this.IdEtage_dessous = newIdEtage_dessous;
        this.IdEtage_dessus = newidEtage_dessus;
    }
    public void addPiece(Piece piece){
        this.Liste_piece.add(piece);
    }
    
}
