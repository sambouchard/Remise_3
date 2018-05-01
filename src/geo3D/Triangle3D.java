/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geo3D;

import Main_Package.Coord_Coins;


/**
 *
 * @author charlesc
 */
public class Triangle3D {
    private Point3D p1;
    private Point3D p2;
    private Point3D p3;
    
    public Triangle3D() {
        super();
    }
    
    public Triangle3D(Coord_Coins c1, Coord_Coins c2, Coord_Coins c3) {
        this.p1 = new Point3D(c1);
        this.p2 = new Point3D(c2);
        this.p3 = new Point3D(c3);
    }
    
    public Triangle3D(Point3D p1, Point3D p2, Point3D p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }
    
    public Vector3D getNormal() {
        // See: https://www.khronos.org/opengl/wiki/Calculating_a_Surface_Normal
        Vector3D v1 = p1.vectorize();
        Vector3D v2 = p2.vectorize();
        Vector3D v3 = p3.vectorize();
        Vector3D U = v2.subtract(v1);
        Vector3D V = v3.subtract(v1);
        Vector3D N = new Vector3D();
        N.cross(U, V);
        return N;
    }
    
    public String getSTL() {
        String out = new String();
        out += getNormal().startSTLFacet();
        out += "outer" + AbstractXYZContainer.STL_VALUE_SEPERATOR + "loop\n";
        out += p1.toSTLVertex();
        out += p2.toSTLVertex();
        out += p3.toSTLVertex();
        out += "endloop\n";
        out = Vector3D.closeFacet(out);
        return out;
    }
}
