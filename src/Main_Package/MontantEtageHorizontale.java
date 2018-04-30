/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

import java.util.List;

/**
 *
 * @author SABOU350
 */
public class MontantEtageHorizontale implements java.io.Serializable{

    private List<Piece> Liste_piece;
    private final Piece pieceMilieu;

    public MontantEtageHorizontale(Piece piecemilieu) {
        this.pieceMilieu = piecemilieu;

    }
    
    public Piece getPieceMilieu(){
        return pieceMilieu;
    }

}
