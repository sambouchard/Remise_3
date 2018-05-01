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
//        Etagere etagere = new Etagere(100, 100, 100, 2, true, true, true);
//        etagere.GenererPieces();
//        try (PrintWriter writer1 = new PrintWriter("Etagere.stl", "UTF-8")) {
//            for (Piece piece : etagere.getListe_piece()) {
//                writer1.println(
//                    "solid test \n"
//                    +"   facet normal 0.000000 -1.000000 0.000000\n"
//                    + "     outer loop\n"
//                    + "       vertex -1.000000 -1.000000 -1.000000\n"
//                    + "       vertex 1.000000 -1.000000 -1.000000\n"
//                    + "       vertex 1.000000 -1.000000 1.000000\n"
//                    + "     endloop\n"
//                    + "   endfacet\n"
//                    + "endsolid\n"
//                );
//            }
//        }
        try (PrintWriter writer = new PrintWriter("Cube.stl", "UTF-8")) {
            /**
             * for(Piece piece :
             * Controleur.getInstance().getEtagere().getListe_piece()){ Mettre
             * le code ascii generant chacune des pieces en 3d
             * }**
             */
            writer.println("solid piece\n"
                    /*Face du fond*/
                    + "   facet normal 0.000000 1.000000 0.000000\n"
                    + "     outer loop\n"
                    + "       vertex 1.000000 1.000000 1.000000\n"
                    + "       vertex 1.000000 1.000000 -1.000000\n"
                    + "       vertex -1.000000 1.000000 -1.000000\n"
                    + "     endloop\n"
                    + "   endfacet\n"
                    + "   facet normal 0.000000 1.000000 0.000000\n"
                    + "     outer loop\n"
                    + "       vertex 1.000000 1.000000 1.000000\n"
                    + "       vertex -1.000000 1.000000 -1.000000\n"
                    + "       vertex -1.000000 1.000000 1.000000\n"
                    + "     endloop\n"
                    + "   endfacet\n"
                    /*Face du haut*/
                    + "   facet normal 0.000000 0.000000 1.000000\n"
                    + "     outer loop\n"
                    + "       vertex 1.000000 1.000000 1.000000\n"
                    + "       vertex -1.000000 1.000000 1.000000\n"
                    + "       vertex -1.000000 -1.000000 1.000000\n"
                    + "     endloop\n"
                    + "   endfacet\n"
                    + "   facet normal 0.000000 0.000000 1.000000\n"
                    + "     outer loop\n"
                    + "       vertex 1.000000 1.000000 1.000000\n"
                    + "       vertex -1.000000 -1.000000 1.000000\n"
                    + "       vertex 1.000000 -1.000000 1.000000\n"
                    + "     endloop\n"
                    + "   endfacet\n"
                    /*Face de droite*/
                    + "   facet normal 1.000000 0.000000 0.000000\n"
                    + "     outer loop\n"
                    + "       vertex 1.000000 1.000000 1.000000\n"
                    + "       vertex 1.000000 -1.000000 1.000000\n"
                    + "       vertex 1.000000 -1.000000 -1.000000\n"
                    + "     endloop\n"
                    + "   endfacet\n"
                    + "   facet normal 1.000000 0.000000 0.000000\n"
                    + "     outer loop\n"
                    + "       vertex 1.000000 1.000000 1.000000\n"
                    + "       vertex 1.000000 -1.000000 -1.000000\n"
                    + "       vertex 1.000000 1.000000 -1.000000\n"
                    + "     endloop\n"
                    + "   endfacet\n"
                    /*Face du bas*/
                    + "   facet normal 0.000000 0.000000 -1.000000\n"
                    + "     outer loop\n"
                    + "       vertex -1.000000 -1.000000 -1.000000\n"
                    + "       vertex 1.000000 1.000000 -1.000000\n"
                    + "       vertex 1.000000 -1.000000 -1.000000\n"
                    + "     endloop\n"
                    + "   endfacet\n"
                    + "   facet normal 0.000000 0.000000 -1.000000\n"
                    + "     outer loop\n"
                    + "       vertex -1.000000 -1.000000 -1.000000\n"
                    + "       vertex -1.000000 1.000000 -1.000000\n"
                    + "       vertex 1.000000 1.000000 -1.000000\n"
                    + "     endloop\n"
                    + "   endfacet\n"
                    /*Face de face*/
                    + "   facet normal 0.000000 -1.000000 0.000000\n"
                    + "     outer loop\n"
                    + "       vertex -1.000000 -1.000000 -1.000000\n"
                    + "       vertex 1.000000 -1.000000 -1.000000\n"
                    + "       vertex 1.000000 -1.000000 1.000000\n"
                    + "     endloop\n"
                    + "   endfacet\n"
                    + "   facet normal 0.000000 -1.000000 0.000000\n"
                    + "     outer loop\n"
                    + "       vertex -1.000000 -1.000000 -1.000000\n"
                    + "       vertex 1.000000 -1.000000 1.000000\n"
                    + "       vertex -1.000000 -1.000000 1.000000\n"
                    + "     endloop\n"
                    + "   endfacet\n"
                    /*Face de gauche*/
                    + "   facet normal -1.000000 0.000000 0.000000\n"
                    + "     outer loop\n"
                    + "       vertex -1.000000 -1.000000 -1.000000\n"
                    + "       vertex -1.000000 -1.000000 1.000000\n"
                    + "       vertex -1.000000 1.000000 1.000000\n"
                    + "     endloop\n"
                    + "   endfacet\n"
                    + "   facet normal -1.000000 0.000000 0.000000\n"
                    + "     outer loop\n"
                    + "       vertex -1.000000 -1.000000 -1.000000\n"
                    + "       vertex -1.000000 1.000000 1.000000\n"
                    + "       vertex -1.000000 1.000000 -1.000000\n"
                    + "     endloop\n"
                    + "   endfacet\n"
                    + "endsolid\n");
        }
    }

}
