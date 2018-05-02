/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

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
    
    public static String getSTLForPiece(Piece piece) {
        String out;
        out = "solid " + piece.getNom() + "\n"
                    /*Face face*/
            +"   facet normal 0.000000 -1.000000 0.000000\n"
            + "     outer loop\n"
            + "       vertex " + piece.getCoinFaceBasGauche().getCoord_x() + " " +  piece.getCoinFaceBasGauche().getCoord_z()+ " " + (-piece.getCoinFaceBasGauche().getCoord_y()) + "\n"
            + "       vertex " + piece.getCoinFaceBasDroit().getCoord_x() + " " +  piece.getCoinFaceBasDroit().getCoord_z()+ " " + (-piece.getCoinFaceBasDroit().getCoord_y()) + "\n"
            + "       vertex " + piece.getCoinFaceHautGauche().getCoord_x() + " " +  piece.getCoinFaceHautGauche().getCoord_z()+ " " + (-piece.getCoinFaceHautGauche().getCoord_y()) + "\n"
            + "     endloop\n"
            + "   endfacet\n"
            + "   facet normal 0.000000 -1.000000 0.000000\n"
            + "     outer loop\n"
            + "       vertex " + piece.getCoinFaceHautGauche().getCoord_x() + " " +  piece.getCoinFaceHautGauche().getCoord_z()+ " " + (-piece.getCoinFaceHautGauche().getCoord_y()) + "\n"
            + "       vertex " + piece.getCoinFaceBasDroit().getCoord_x() + " " +  piece.getCoinFaceBasDroit().getCoord_z()+ " " + (-piece.getCoinFaceBasDroit().getCoord_y()) + "\n"
            + "       vertex " + piece.getCoinFaceHautDroit().getCoord_x() + " " +  piece.getCoinFaceHautDroit().getCoord_z()+ " " + (-piece.getCoinFaceHautDroit().getCoord_y()) + "\n"
            + "     endloop\n"
            + "   endfacet\n"
            /*FaceFond*/
            +"   facet normal 0.000000 1.000000 0.000000\n"
            + "     outer loop\n"
            + "       vertex " + piece.getCoinFondBasGauche().getCoord_x() + " " +  piece.getCoinFondBasGauche().getCoord_z()+ " " + (-piece.getCoinFondBasGauche().getCoord_y()) + "\n"
            + "       vertex " + piece.getCoinFondBasDroit().getCoord_x() + " " +  piece.getCoinFondBasDroit().getCoord_z()+ " " + (-piece.getCoinFondBasDroit().getCoord_y()) + "\n"
            + "       vertex " + piece.getCoinFondHautGauche().getCoord_x() + " " +  piece.getCoinFondHautGauche().getCoord_z()+ " " + (-piece.getCoinFondHautGauche().getCoord_y()) + "\n"
            + "     endloop\n"
            + "   endfacet\n"
            + "   facet normal 0.000000 1.000000 0.000000\n"
            + "     outer loop\n"
            + "       vertex " + piece.getCoinFondHautGauche().getCoord_x() + " " +  piece.getCoinFondHautGauche().getCoord_z()+ " " + (-piece.getCoinFondHautGauche().getCoord_y()) + "\n"
            + "       vertex " + piece.getCoinFondBasDroit().getCoord_x() + " " +  piece.getCoinFondBasDroit().getCoord_z()+ " " + (-piece.getCoinFondBasDroit().getCoord_y()) + "\n"
            + "       vertex " + piece.getCoinFondHautDroit().getCoord_x() + " " +  piece.getCoinFondHautDroit().getCoord_z()+ " " + (-piece.getCoinFondHautDroit().getCoord_y()) + "\n"
            + "     endloop\n"
            + "   endfacet\n"
             /*FaceHaut*/
            +"   facet normal 0.000000 0.000000 1.000000\n"
            + "     outer loop\n"
            + "       vertex " + piece.getCoinFaceHautGauche().getCoord_x() + " " +  piece.getCoinFaceHautGauche().getCoord_z()+ " " + (-piece.getCoinFaceHautGauche().getCoord_y()) + "\n"
            + "       vertex " + piece.getCoinFaceHautDroit().getCoord_x() + " " +  piece.getCoinFaceHautDroit().getCoord_z()+ " " + (-piece.getCoinFaceHautDroit().getCoord_y()) + "\n"
            + "       vertex " + piece.getCoinFondHautGauche().getCoord_x() + " " +  piece.getCoinFondHautGauche().getCoord_z()+ " " + (-piece.getCoinFondHautGauche().getCoord_y()) + "\n"
            + "     endloop\n"
            + "   endfacet\n"
            + "   facet normal 0.000000 0.000000 1.000000\n"
            + "     outer loop\n"
            + "       vertex " + piece.getCoinFondHautGauche().getCoord_x() + " " +  piece.getCoinFondHautGauche().getCoord_z()+ " " + (-piece.getCoinFondHautGauche().getCoord_y()) + "\n"
            + "       vertex " + piece.getCoinFaceHautDroit().getCoord_x() + " " +  piece.getCoinFaceHautDroit().getCoord_z()+ " " + (-piece.getCoinFaceHautDroit().getCoord_y()) + "\n"
            + "       vertex " + piece.getCoinFondHautDroit().getCoord_x() + " " +  piece.getCoinFondHautDroit().getCoord_z()+ " " + (-piece.getCoinFondHautDroit().getCoord_y()) + "\n"
            + "     endloop\n"
            + "   endfacet\n"
            /*FaceBas*/
            +"   facet normal 0.000000 0.000000 -1.000000\n"
            + "     outer loop\n"
            + "       vertex " + piece.getCoinFaceBasGauche().getCoord_x() + " " +  piece.getCoinFaceBasGauche().getCoord_z()+ " " + (-piece.getCoinFaceBasGauche().getCoord_y()) + "\n"
            + "       vertex " + piece.getCoinFaceBasDroit().getCoord_x() + " " +  piece.getCoinFaceBasDroit().getCoord_z()+ " " + (-piece.getCoinFaceBasDroit().getCoord_y()) + "\n"
            + "       vertex " + piece.getCoinFondBasGauche().getCoord_x() + " " +  piece.getCoinFondBasGauche().getCoord_z()+ " " + (-piece.getCoinFondBasGauche().getCoord_y()) + "\n"
            + "     endloop\n"
            + "   endfacet\n"
            + "   facet normal 0.000000 0.000000 -1.000000\n"
            + "     outer loop\n"
            + "       vertex " + piece.getCoinFondBasGauche().getCoord_x() + " " +  piece.getCoinFondBasGauche().getCoord_z()+ " " + (-piece.getCoinFondBasGauche().getCoord_y()) + "\n"
            + "       vertex " + piece.getCoinFaceBasDroit().getCoord_x() + " " +  piece.getCoinFaceBasDroit().getCoord_z()+ " " + (-piece.getCoinFaceBasDroit().getCoord_y()) + "\n"
            + "       vertex " + piece.getCoinFondBasDroit().getCoord_x() + " " +  piece.getCoinFondBasDroit().getCoord_z()+ " " + (-piece.getCoinFondBasDroit().getCoord_y()) + "\n"
            + "     endloop\n"
            + "   endfacet\n"
            /*Facegauche*/
            +"   facet normal -1.000000 0.000000 0.000000\n"
            + "     outer loop\n"
            + "       vertex " + piece.getCoinFaceBasGauche().getCoord_x() + " " +  piece.getCoinFaceBasGauche().getCoord_z()+ " " + (-piece.getCoinFaceBasGauche().getCoord_y()) + "\n"
            + "       vertex " + piece.getCoinFondBasGauche().getCoord_x() + " " +  piece.getCoinFondBasGauche().getCoord_z()+ " " + (-piece.getCoinFondBasGauche().getCoord_y()) + "\n"
            + "       vertex " + piece.getCoinFaceHautGauche().getCoord_x() + " " +  piece.getCoinFaceHautGauche().getCoord_z()+ " " + (-piece.getCoinFaceHautGauche().getCoord_y()) + "\n"
            + "     endloop\n"
            + "   endfacet\n"
            + "   facet normal -1.000000 0.000000 .000000\n"
            + "     outer loop\n"
            + "       vertex " + piece.getCoinFaceHautGauche().getCoord_x() + " " +  piece.getCoinFaceHautGauche().getCoord_z()+ " " + (-piece.getCoinFaceHautGauche().getCoord_y()) + "\n"
            + "       vertex " + piece.getCoinFondBasGauche().getCoord_x() + " " +  piece.getCoinFondBasGauche().getCoord_z()+ " " + (-piece.getCoinFondBasGauche().getCoord_y()) + "\n"
            + "       vertex " + piece.getCoinFondHautGauche().getCoord_x() + " " +  piece.getCoinFondHautGauche().getCoord_z()+ " " + (-piece.getCoinFondHautGauche().getCoord_y()) + "\n"
            + "     endloop\n"
            + "   endfacet\n"
            /*FaceDroit*/
           +"   facet normal 1.000000 0.000000 0.000000\n"
            + "     outer loop\n"
            + "       vertex " + piece.getCoinFaceBasDroit().getCoord_x() + " " +  piece.getCoinFaceBasDroit().getCoord_z()+ " " + (-piece.getCoinFaceBasDroit().getCoord_y()) + "\n"
            + "       vertex " + piece.getCoinFondBasDroit().getCoord_x() + " " +  piece.getCoinFondBasDroit().getCoord_z()+ " " + (-piece.getCoinFondBasDroit().getCoord_y()) + "\n"
            + "       vertex " + piece.getCoinFaceHautDroit().getCoord_x() + " " +  piece.getCoinFaceHautDroit().getCoord_z()+ " " + (-piece.getCoinFaceHautDroit().getCoord_y()) + "\n"
            + "     endloop\n"
            + "   endfacet\n"
            + "   facet normal 1.000000 0.000000 .000000\n"
            + "     outer loop\n"
            + "       vertex " + piece.getCoinFaceHautDroit().getCoord_x() + " " +  piece.getCoinFaceHautDroit().getCoord_z()+ " " + (-piece.getCoinFaceHautDroit().getCoord_y()) + "\n"
            + "       vertex " + piece.getCoinFondBasDroit().getCoord_x() + " " +  piece.getCoinFondBasDroit().getCoord_z()+ " " + (-piece.getCoinFondBasDroit().getCoord_y()) + "\n"
            + "       vertex " + piece.getCoinFondHautDroit().getCoord_x() + " " +  piece.getCoinFondHautDroit().getCoord_z()+ " " + (-piece.getCoinFondHautDroit().getCoord_y()) + "\n"
            + "     endloop\n"
            + "   endfacet\n"
            + "endsolid\n";
        
     return out;
    }
    
   public String getSTL() {
     String out = new String();
     for (Piece piece : this.etagere.getListe_piece()) {
            out += STLExporter.getSTLForPiece(piece);
        }
     return out;
    }
   
    /**
     *
     * @return An Array of Strings of STL files for each Piece of the Etagere
     */
    public ArrayList<String> getPiecesSTLs() {
        ArrayList<String> stlStrList = new ArrayList<>();
        for (Piece p: this.etagere.getListe_piece()) {
            String stl = STLExporter.getSTLForPiece(p);
            stlStrList.add(stl);
        }
        return stlStrList;
    }
}
