/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_Package;

/**
 *
 * @author SABOU350
 */
public class Coord_Coins {
    private double coord_x;
    private double coord_y;
    private double coord_z;

    /**
     * @return the coord_x
     */
    public double getCoord_x() {
        return coord_x;
    }

    /**
     * @param coord_x the coord_x to set
     */
    public void setCoord_x(float coord_x) {
        this.coord_x = coord_x;
    }

    /**
     * @return the coord_y
     */
    public double getCoord_y() {
        return coord_y;
    }

    /**
     * @param coord_y the coord_y to set
     */
    public void setCoord_y(float coord_y) {
        this.coord_y = coord_y;
    }

    /**
     * @return the coord_z
     */
    public double getCoord_z() {
        return coord_z;
    }

    /**
     * @param coord_z the coord_z to set
     */
    public void setCoord_z(float coord_z) {
        this.coord_z = coord_z;
    }

    public Coord_Coins(double coord_x, double coord_y, double coord_z) {
        this.coord_x = coord_x;
        this.coord_y = coord_y;
        this.coord_z = coord_z;
    }
    
    
}
