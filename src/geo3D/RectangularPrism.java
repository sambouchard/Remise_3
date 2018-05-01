/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geo3D;

import Main_Package.Piece;
import java.util.List;

/**
 *
 * @author charlesc
 */
public class RectangularPrism {
    private Point3D PtFaceHautGauche;
    private Point3D PtFaceHautDroit;
    private Point3D PtFaceBasDroit;
    private Point3D PtFaceBasGauche;
    private Point3D PtFondHautGauche;
    private Point3D PtFondHautDroit;
    private Point3D PtFondBasDroit;
    private Point3D PtFondBasGauche;
    private List<Triangle3D> triangleArray;
            
    public RectangularPrism() {
    }

    public RectangularPrism(Point3D PtFaceHautGauche, Point3D PtFaceHautDroit, Point3D PtFaceBasDroit, Point3D PtFaceBasGauche, Point3D PtFondHautGauche, Point3D PtFondHautDroit, Point3D PtFondBasDroit, Point3D PtFondBasGauche) {
        this.PtFaceHautGauche = PtFaceHautGauche;
        this.PtFaceHautDroit = PtFaceHautDroit;
        this.PtFaceBasDroit = PtFaceBasDroit;
        this.PtFaceBasGauche = PtFaceBasGauche;
        this.PtFondHautGauche = PtFondHautGauche;
        this.PtFondHautDroit = PtFondHautDroit;
        this.PtFondBasDroit = PtFondBasDroit;
        this.PtFondBasGauche = PtFondBasGauche;
        generateListOfTriangles();
    }
    
    
    public RectangularPrism(Piece p) {
        this.PtFaceHautGauche = new Point3D(p.getCoinFaceHautGauche());
        this.PtFaceHautDroit = new Point3D(p.getCoinFaceHautDroit());
        this.PtFaceBasDroit = new Point3D(p.getCoinFaceBasDroit());
        this.PtFaceBasGauche = new Point3D(p.getCoinFaceBasGauche());
        this.PtFondHautGauche = new Point3D(p.getCoinFondHautGauche());
        this.PtFondHautDroit = new Point3D(p.getCoinFondHautDroit());
        this.PtFondBasDroit = new Point3D(p.getCoinFondBasDroit());
        this.PtFondBasGauche = new Point3D(p.getCoinFondBasGauche());
        generateListOfTriangles();
    }
    
    public List<Triangle3D> getTriangleArray() {
        return this.triangleArray;
    }
    
    /**
     *
     * @return STL ready string containing all facets of the piece
     */
    public String getSTL() {
        String out = new String();
        for (Triangle3D t : this.triangleArray) {
            out += t.getSTL();
        }
        return out;
    }
    
    private void addTriangles(Triangle3D[] tArray) {
        for (Triangle3D t : tArray) {
            this.triangleArray.add(t);
        }
    }
    
    private void generateListOfTriangles() { 
        // Face
        addTriangles(Triangle3D.generateTrianglesFromPts(PtFaceHautGauche, PtFaceHautDroit, PtFaceBasGauche, PtFaceBasDroit));
        
        // Coter gauche
        addTriangles(Triangle3D.generateTrianglesFromPts(PtFaceHautGauche, PtFondHautGauche, PtFaceBasGauche, PtFondBasGauche));
        
        // Fond
        addTriangles(Triangle3D.generateTrianglesFromPts(PtFondHautDroit, PtFondHautGauche, PtFondBasDroit, PtFondBasGauche));
        
        // Coter droite
        addTriangles(Triangle3D.generateTrianglesFromPts(PtFaceHautDroit, PtFondHautDroit, PtFaceBasDroit, PtFondBasDroit));
        
        // Haut
        addTriangles(Triangle3D.generateTrianglesFromPts(PtFondHautGauche, PtFondHautDroit, PtFaceHautGauche, PtFaceHautDroit));
        
        // Bas
        addTriangles(Triangle3D.generateTrianglesFromPts(PtFaceBasGauche, PtFaceBasDroit, PtFondBasGauche, PtFondBasDroit));
    }
}
