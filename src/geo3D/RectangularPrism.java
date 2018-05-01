/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geo3D;

/**
 *
 * @author charlesc
 */
public class RectangularPrism {

    public RectangularPrism() {
    }
    
    public static void main(String[] args) {
        Point3D p1 = new Point3D(1, 1, 1);
        Point3D p2 = new Point3D(-1, 1, 1);
        Point3D p3 = new Point3D(-1, -1, 1);
        Triangle3D t = new Triangle3D(p1, p2, p3);
        System.out.println(t.getSTL());
    }
}
