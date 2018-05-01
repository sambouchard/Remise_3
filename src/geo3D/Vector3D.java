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
public class Vector3D extends AbstractXYZContainer {
    
    public Vector3D() {
        super();
    }
    
    public Vector3D(Coord_Coins coin) {
        super(coin);
    }

    public Vector3D(double x, double y, double z) {
        super(x, y, z);
    }
    
    /**
     *
     * @param v Vector to subtract from this vector
     * @return Returns the current vector after subtraction 
     */
    public Vector3D subtract(Vector3D v) {
        double newX  = this.getX() - v.getX();
        double newY  = this.getY() - v.getY();
        double newZ  = this.getZ() - v.getZ();
        this.setX(newX);
        this.setY(newY);
        this.setZ(newZ);
        return this;
    }
    
    /**
     *
     * @param s Scalar to subtract from vector
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
     * Sets the following vector to be the cross product of v1 and v2
     * @param v1 First Vector
     * @param v2 Second Vector
     */
    // c1 = a2 b3 - a3 b2
    // c2 = a3 b1 - a1 b3
    // c3 = a1 b2 - a2 b1
    public final void cross(Vector3D v1, Vector3D v2) {
        double crossedX = v1.getY() * v2.getZ() - v1.getZ() * v2.getY();
        double crossedY = v1.getZ() * v2.getX() - v1.getX() * v2.getZ();
        double crossedZ = v1.getX() * v2.getY() - v1.getY() * v2.getX();
        this.setX(crossedX);
        this.setY(crossedY);
        this.setZ(crossedZ);
    }

    /**
     *
     * @return String formatted for STL facet's normal vector 
     * REMEMBER TO CLOSE THE FACET WITH closeFacet()
     */
    public String startSTLFacet() {
        String out = new String();
        out += "facet" + AbstractXYZContainer.STL_VALUE_SEPERATOR + "normal" + AbstractXYZContainer.STL_VALUE_SEPERATOR;
        out += super.toString() + "\n";
        return out;
    }
    
    /**
     *
     * @param stlFacet Closes a facet created with toSTLFacet
     * @return STL Ready Facet (As string)
     */
    public static String closeFacet(String stlFacet) {
        return stlFacet + "endfacet\n";
    }
}
