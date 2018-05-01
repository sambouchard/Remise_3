/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

import java.util.List;

/**
 *
 * @author charlesc
 */
public class STLExporter {
    private Etagere etagere;
    
    public STLExporter(Etagere e) {
        this.etagere = e;
    }
    
    @Override
    public String toString() {
        List<Piece> list = this.etagere.getListe_piece();
        for (Piece p : list) {
            System.out.println(p.getCoinFaceBasDroit());
            System.out.println(p.getCoinFaceBasGauche());
            System.out.println(p.getCoinFaceHautDroit());
            System.out.println(p.getCoinFaceHautGauche());
        }
        return "";
    }
}
