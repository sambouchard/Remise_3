package Main_Package;

/**
 *
 * @author SABOU350
 */
public class Coord_Coins implements java.io.Serializable{

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
    public void setCoord_x(double coord_x) {
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
    public void setCoord_y(double coord_y) {
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
    public void setCoord_z(double coord_z) {
        this.coord_z = coord_z;
    }

    public Coord_Coins(double coord_x, double coord_y, double coord_z) {
        this.coord_x = coord_x;
        this.coord_y = coord_y;
        this.coord_z = coord_z;
    }
    
}
