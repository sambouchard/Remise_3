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
 * I have to implement this because javaFx won't compile
 * Thanks for letting us know javaFx is so much better for 3D arithmetic ....
 */
public class Point3D extends AbstractXYZContainer {
    
    public Point3D() {
        super();
    }

    public Point3D(Coord_Coins coin) {
        super(coin);
    }

    public Point3D(double x, double y, double z) {
        super(x, y, z);
    }
    
    /**
     *
     * @param s Scalar to subtract from this point
     */
    @Override
    public void subtract(double s) {
        double newX  = this.getX() - s;
        double newY  = this.getY() - s;
        double newZ  = this.getZ() - s;
        this.setX(newX);
        this.setY(newY);
        this.setZ(newZ);
    }
    
    /**
     *
     * @param p point to subtract from this point
     * @return Returns the current point after subtraction
     */
    public Point3D subtract(Point3D p) {
        double newX  = this.getX() - p.getX();
        double newY  = this.getY() - p.getY();
        double newZ  = this.getZ() - p.getZ();
        this.setX(newX);
        this.setY(newY);
        this.setZ(newZ);
        return this;
    }
    
    /**
     *
     * @return Point transformed to a Vector3D
     */
    public Vector3D vectorize() {
        return new Vector3D(this.getX(), this.getY(), this.getZ());
    }
    
    public String toSTLVertex() {
        return "vertex" + AbstractXYZContainer.STL_VALUE_SEPERATOR + super.toString() + "\n"; //To change body of generated methods, choose Tools | Templates.
    }
}
