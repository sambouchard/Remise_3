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
    private Vector3D normal;
    public Triangle3D() {
        super();
    }
    
    public Triangle3D(Coord_Coins c1, Coord_Coins c2, Coord_Coins c3, Vector3D normal) {
        this.p1 = new Point3D(c1);
        this.p2 = new Point3D(c2);
        this.p3 = new Point3D(c3);
        this.normal = normal;
    }
    
    public Triangle3D(Point3D p1, Point3D p2, Point3D p3, Vector3D normal) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.normal = normal;
    }
    
    public static Triangle3D[] generateTrianglesFromPts(Point3D hautGauche, Point3D hautDroite, Point3D basGauche, Point3D basDroite, Vector3D normal) {
        Triangle3D[] tArray = new Triangle3D[4];
        Triangle3D t1 = new Triangle3D(hautGauche, hautDroite, basGauche, normal.invert());
        tArray[0] = t1;
        
        Triangle3D t2 = new Triangle3D(hautDroite, basGauche, basDroite, normal.invert());
        tArray[1] = t2;
        
        Triangle3D t3 = new Triangle3D(hautGauche, hautDroite, basGauche, normal);
        tArray[2] = t3;
        
        Triangle3D t4 = new Triangle3D(hautDroite, basGauche, basDroite, normal);
        tArray[3] = t4;
        return tArray;
    }
    
    @Override
    public String toString() {
        String out = "Triangle: \n";
        out += "Vertex 1: " + p1.toString() + "\n";
        out += "Vertex 2: " + p2.toString() + "\n";
        out += "Vertex 3: " + p3.toString() + "\n";
        return out;
    }

    public Vector3D getNormal() {
        return normal;
    }

    public void setNormal(Vector3D normal) {
        this.normal = normal;
    }
    
    public Vector3D computeNormal() {
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
    public static void main(String []argv) {
        Point3D p1 = new Point3D(1, 1, 1);
        Point3D p2 = new Point3D(-1, 1, -1);
        Point3D p3 = new Point3D(-1, 1, 1);
        Point3D p4 = new Point3D(1, 1, -1);
        Triangle3D t = new Triangle3D(p1, p2, p3, Vector3D.VEC_NORMAL_BAS);
        Triangle3D t1 = new Triangle3D(p1, p4, p2, Vector3D.VEC_NORMAL_BAS);
        System.out.println(t.getSTL());
        System.out.println(t1.getSTL());
    }
}
