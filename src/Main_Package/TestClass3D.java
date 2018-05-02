/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author sam
 */
public class TestClass3D {

    /**
     *
     * @param args
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        /**
         * for(Piece piece :
         * Controleur.getInstance().getEtagere().getListe_piece()){ Mettre le
         * code ascii generant chacune des pieces en 3d
        }**
         */
        Etagere etagere = new Etagere(50, 50,10, 3, true, true, true);
        etagere.GenererPieces();
        try (PrintWriter writer1 = new PrintWriter("Etagere.stl", "UTF-8")) {
            for (Piece piece : etagere.getListe_piece()) {
                writer1.println(
                    "solid " +piece.getNom() + "\n"
                            /*Face face*/
                    +"   facet normal 0.000000 -1.000000 0.000000\n"
                    + "     outer loop\n"
                    + "       vertex " + piece.getCoinFaceBasGauche().getCoord_x() + " " +  piece.getCoinFaceBasGauche().getCoord_z()+ " " + piece.getCoinFaceBasGauche().getCoord_y() + "\n"
                    + "       vertex " + piece.getCoinFaceBasDroit().getCoord_x() + " " +  piece.getCoinFaceBasDroit().getCoord_z()+ " " + piece.getCoinFaceBasDroit().getCoord_y() + "\n"
                    + "       vertex " + piece.getCoinFaceHautGauche().getCoord_x() + " " +  piece.getCoinFaceHautGauche().getCoord_z()+ " " + piece.getCoinFaceHautGauche().getCoord_y() + "\n"
                    + "     endloop\n"
                    + "   endfacet\n"
                    + "   facet normal 0.000000 -1.000000 0.000000\n"
                    + "     outer loop\n"
                    + "       vertex " + piece.getCoinFaceHautGauche().getCoord_x() + " " +  piece.getCoinFaceHautGauche().getCoord_z()+ " " + piece.getCoinFaceHautGauche().getCoord_y() + "\n"
                    + "       vertex " + piece.getCoinFaceBasDroit().getCoord_x() + " " +  piece.getCoinFaceBasDroit().getCoord_z()+ " " + piece.getCoinFaceBasDroit().getCoord_y() + "\n"
                    + "       vertex " + piece.getCoinFaceHautDroit().getCoord_x() + " " +  piece.getCoinFaceHautDroit().getCoord_z()+ " " + piece.getCoinFaceHautDroit().getCoord_y() + "\n"
                    + "     endloop\n"
                    + "   endfacet\n"
                    /*FaceFond*/
                    +"   facet normal 0.000000 1.000000 0.000000\n"
                    + "     outer loop\n"
                    + "       vertex " + piece.getCoinFondBasGauche().getCoord_x() + " " +  piece.getCoinFondBasGauche().getCoord_z()+ " " + piece.getCoinFondBasGauche().getCoord_y() + "\n"
                    + "       vertex " + piece.getCoinFondBasDroit().getCoord_x() + " " +  piece.getCoinFondBasDroit().getCoord_z()+ " " + piece.getCoinFondBasDroit().getCoord_y() + "\n"
                    + "       vertex " + piece.getCoinFondHautGauche().getCoord_x() + " " +  piece.getCoinFondHautGauche().getCoord_z()+ " " + piece.getCoinFondHautGauche().getCoord_y() + "\n"
                    + "     endloop\n"
                    + "   endfacet\n"
                    + "   facet normal 0.000000 1.000000 0.000000\n"
                    + "     outer loop\n"
                    + "       vertex " + piece.getCoinFondHautGauche().getCoord_x() + " " +  piece.getCoinFondHautGauche().getCoord_z()+ " " + piece.getCoinFondHautGauche().getCoord_y() + "\n"
                    + "       vertex " + piece.getCoinFondBasDroit().getCoord_x() + " " +  piece.getCoinFondBasDroit().getCoord_z()+ " " + piece.getCoinFondBasDroit().getCoord_y() + "\n"
                    + "       vertex " + piece.getCoinFondHautDroit().getCoord_x() + " " +  piece.getCoinFondHautDroit().getCoord_z()+ " " + piece.getCoinFondHautDroit().getCoord_y() + "\n"
                    + "     endloop\n"
                    + "   endfacet\n"
                     /*FaceHaut*/
                    +"   facet normal 0.000000 0.000000 1.000000\n"
                    + "     outer loop\n"
                    + "       vertex " + piece.getCoinFaceHautGauche().getCoord_x() + " " +  piece.getCoinFaceHautGauche().getCoord_z()+ " " + piece.getCoinFaceHautGauche().getCoord_y() + "\n"
                    + "       vertex " + piece.getCoinFaceHautDroit().getCoord_x() + " " +  piece.getCoinFaceHautDroit().getCoord_z()+ " " + piece.getCoinFaceHautDroit().getCoord_y() + "\n"
                    + "       vertex " + piece.getCoinFondHautGauche().getCoord_x() + " " +  piece.getCoinFondHautGauche().getCoord_z()+ " " + piece.getCoinFondHautGauche().getCoord_y() + "\n"
                    + "     endloop\n"
                    + "   endfacet\n"
                    + "   facet normal 0.000000 0.000000 1.000000\n"
                    + "     outer loop\n"
                    + "       vertex " + piece.getCoinFondHautGauche().getCoord_x() + " " +  piece.getCoinFondHautGauche().getCoord_z()+ " " + piece.getCoinFondHautGauche().getCoord_y() + "\n"
                    + "       vertex " + piece.getCoinFaceHautDroit().getCoord_x() + " " +  piece.getCoinFaceHautDroit().getCoord_z()+ " " + piece.getCoinFaceHautDroit().getCoord_y() + "\n"
                    + "       vertex " + piece.getCoinFondHautDroit().getCoord_x() + " " +  piece.getCoinFondHautDroit().getCoord_z()+ " " + piece.getCoinFondHautDroit().getCoord_y() + "\n"
                    + "     endloop\n"
                    + "   endfacet\n"
                    /*FaceBas*/
                    +"   facet normal 0.000000 0.000000 -1.000000\n"
                    + "     outer loop\n"
                    + "       vertex " + piece.getCoinFaceBasGauche().getCoord_x() + " " +  piece.getCoinFaceBasGauche().getCoord_z()+ " " + piece.getCoinFaceBasGauche().getCoord_y() + "\n"
                    + "       vertex " + piece.getCoinFaceBasDroit().getCoord_x() + " " +  piece.getCoinFaceBasDroit().getCoord_z()+ " " + piece.getCoinFaceBasDroit().getCoord_y() + "\n"
                    + "       vertex " + piece.getCoinFondBasGauche().getCoord_x() + " " +  piece.getCoinFondBasGauche().getCoord_z()+ " " + piece.getCoinFondBasGauche().getCoord_y() + "\n"
                    + "     endloop\n"
                    + "   endfacet\n"
                    + "   facet normal 0.000000 0.000000 -1.000000\n"
                    + "     outer loop\n"
                    + "       vertex " + piece.getCoinFondBasGauche().getCoord_x() + " " +  piece.getCoinFondBasGauche().getCoord_z()+ " " + piece.getCoinFondBasGauche().getCoord_y() + "\n"
                    + "       vertex " + piece.getCoinFaceBasDroit().getCoord_x() + " " +  piece.getCoinFaceBasDroit().getCoord_z()+ " " + piece.getCoinFaceBasDroit().getCoord_y() + "\n"
                    + "       vertex " + piece.getCoinFondBasDroit().getCoord_x() + " " +  piece.getCoinFondBasDroit().getCoord_z()+ " " + piece.getCoinFondBasDroit().getCoord_y() + "\n"
                    + "     endloop\n"
                    + "   endfacet\n"
                    /*Facegauche*/
                    +"   facet normal -1.000000 0.000000 0.000000\n"
                    + "     outer loop\n"
                    + "       vertex " + piece.getCoinFaceBasGauche().getCoord_x() + " " +  piece.getCoinFaceBasGauche().getCoord_z()+ " " + piece.getCoinFaceBasGauche().getCoord_y() + "\n"
                    + "       vertex " + piece.getCoinFondBasGauche().getCoord_x() + " " +  piece.getCoinFondBasGauche().getCoord_z()+ " " + piece.getCoinFondBasGauche().getCoord_y() + "\n"
                    + "       vertex " + piece.getCoinFaceHautGauche().getCoord_x() + " " +  piece.getCoinFaceHautGauche().getCoord_z()+ " " + piece.getCoinFaceHautGauche().getCoord_y() + "\n"
                    + "     endloop\n"
                    + "   endfacet\n"
                    + "   facet normal -1.000000 0.000000 .000000\n"
                    + "     outer loop\n"
                    + "       vertex " + piece.getCoinFaceHautGauche().getCoord_x() + " " +  piece.getCoinFaceHautGauche().getCoord_z()+ " " + piece.getCoinFaceHautGauche().getCoord_y() + "\n"
                    + "       vertex " + piece.getCoinFondBasGauche().getCoord_x() + " " +  piece.getCoinFondBasGauche().getCoord_z()+ " " + piece.getCoinFondBasGauche().getCoord_y() + "\n"
                    + "       vertex " + piece.getCoinFondHautGauche().getCoord_x() + " " +  piece.getCoinFondHautGauche().getCoord_z()+ " " + piece.getCoinFondHautGauche().getCoord_y() + "\n"
                    + "     endloop\n"
                    + "   endfacet\n"
                    /*FaceDroit*/
                   +"   facet normal 1.000000 0.000000 0.000000\n"
                    + "     outer loop\n"
                    + "       vertex " + piece.getCoinFaceBasDroit().getCoord_x() + " " +  piece.getCoinFaceBasDroit().getCoord_z()+ " " + piece.getCoinFaceBasDroit().getCoord_y() + "\n"
                    + "       vertex " + piece.getCoinFondBasDroit().getCoord_x() + " " +  piece.getCoinFondBasDroit().getCoord_z()+ " " + piece.getCoinFondBasDroit().getCoord_y() + "\n"
                    + "       vertex " + piece.getCoinFaceHautDroit().getCoord_x() + " " +  piece.getCoinFaceHautDroit().getCoord_z()+ " " + piece.getCoinFaceHautDroit().getCoord_y() + "\n"
                    + "     endloop\n"
                    + "   endfacet\n"
                    + "   facet normal 1.000000 0.000000 .000000\n"
                    + "     outer loop\n"
                    + "       vertex " + piece.getCoinFaceHautDroit().getCoord_x() + " " +  piece.getCoinFaceHautDroit().getCoord_z()+ " " + piece.getCoinFaceHautDroit().getCoord_y() + "\n"
                    + "       vertex " + piece.getCoinFondBasDroit().getCoord_x() + " " +  piece.getCoinFondBasDroit().getCoord_z()+ " " + piece.getCoinFondBasDroit().getCoord_y() + "\n"
                    + "       vertex " + piece.getCoinFondHautDroit().getCoord_x() + " " +  piece.getCoinFondHautDroit().getCoord_z()+ " " + piece.getCoinFondHautDroit().getCoord_y() + "\n"
                    + "     endloop\n"
                    + "   endfacet\n"
                            
                    + "endsolid\n"
                );
            }
        
        }
    }

}
