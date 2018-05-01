/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geo3D;

import Main_Package.Coord_Coins;
import java.text.DecimalFormat;

/**
 *
 * @author charlesc
 * Abstract class of any object that contains X Y Z coordinates/values
 */
abstract public class AbstractXYZContainer {
    // Properties
    private double x;
    private double y;
    private double z;
    public static DecimalFormat STL_NUMBER_FORMATTER = new DecimalFormat("#.000000");
    public static String STL_VALUE_SEPERATOR = " ";
    
    public AbstractXYZContainer() {};

    public AbstractXYZContainer(Coord_Coins coin) {
        this.x = coin.getCoord_x();
        this.y = coin.getCoord_y();
        this.z = coin.getCoord_z();
    }
    
    public AbstractXYZContainer(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    // Getters & Setters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
    
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    public void setZ(double z) {
        this.z = z;
    }
    
    public boolean equals(AbstractXYZContainer c) {
        boolean xEq = (this.x == c.getX());
        boolean yEq = (this.y == c.getY());
        boolean zEq = (this.z == c.getZ());
        return (xEq && yEq && zEq);
    }

    @Override
    public String toString() {
        return STL_NUMBER_FORMATTER.format(this.x) + STL_VALUE_SEPERATOR + 
               STL_NUMBER_FORMATTER.format(this.y) + STL_VALUE_SEPERATOR + 
               STL_NUMBER_FORMATTER.format(this.z);
    }
    
    abstract public void subtract(double s);
    
}
