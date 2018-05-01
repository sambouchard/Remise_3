/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

import geo3D.RectangularPrism;
import java.util.ArrayList;

/**
 *
 * @author charlesc
 */
public class STLExporter {
    private final Etagere etagere;
    
    public STLExporter(Etagere e) {
        this.etagere = e;
    }
    
    /**
     *
     * @return String of the STL file for the whole etagere
     */
    public String getSTL() {
        String out = new String();
        out += "solid etagere\n";
        for (Piece p : this.etagere.getListe_piece()) {
            RectangularPrism prism = new RectangularPrism(p);
            out += prism.getSTL();
        }
        out += "endsolid\n";
        return out;
    }
    
    /**
     *
     * @return An Array of Strings of STL files for each Piece of the Etagere
     */
    public ArrayList<String> getPiecesSTLs() {
        ArrayList<String> stlStrList = new ArrayList<>();
        for (Piece p: this.etagere.getListe_piece()) {
            String pieceStl = new String();
            RectangularPrism prism = new RectangularPrism(p);
            pieceStl += "solid " + p.getNom().replaceAll("\\s+","") + "\n";
            pieceStl += prism.getSTL();
            pieceStl += "endsolid\n";
            stlStrList.add(pieceStl);
        }
        return stlStrList;
    }
}
