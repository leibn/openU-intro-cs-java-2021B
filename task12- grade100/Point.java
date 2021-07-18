/**
 * Point.java represents a 2-Dimensional point in the Euclidean space.
 *
 * @class: Point
 * @author : Daniel Leibner 
 * @authorId : #########
 * @version: 07/04/21
 */
public class Point {

    private double _x; // the xi's attributes value for point.
    private double _y; // the yi's attributes value for point.


    /**
     * after talk in the class in 06/04 i add this no args constructor
     * no args constructor to Point by x=0,y=0 parameters.
     */
    public Point() {
        _x = 0;
        _y = 0;
    }


    /**
     * Constructs a new Point by x,y parameters.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Point(double x, double y) {
        _x = x;
        _y = y;
    }

    /**
     * Copy constructor Creates a new Point identical to the given point.
     * @param other the Point to be copied.
     */
    public Point(Point other) {
        _x = other.getX() ;
        _y = other.getY() ;
    }

    /**
     * This method sets the x coordinate of the point.
     * @param num The new x coordinate.
     */
    public void setX(double num) {
        _x = num;
    }

    /**
     * This method sets the y coordinate of the point.
     * @param num The new y coordinate
     */
    public void setY(double num) {
        _y = num;
    }

    /**
     * Returns the x coordinate.
     * @return the x coordinate as double.
     */
    public double getX() {
        return _x;
    }

    /**
     * Returns the y coordinate.
     * @return the y coordinate as double.
     */
    public double getY() {
        return _y;
    }

    /**
     * Returns a string representation of this Point. The format of the string should be: (x,y) .
     * @return  A string representation of a Point object.
     */
    @Override
    public String toString() {
        return ("(" + _x + "," + _y + ")");
    }

    /**
     * Return true if the given point is equal to this point.
     * @param other The point we are checking equality with.
     * @return true iff the point other equals this point.
     */
    public boolean equals(Point other) {
        return ((other._x == _x) && (other._y == _y));
    }

    /**
     * Check if this point is above a received point.
     * @param other The point to check if this point is above.
     * @return True if this point is above the other point.
     */
    public boolean isAbove(Point other) {
        return (other._y < _y);
    }

    /**
     * Check if this point is below a received point.
     * @param other The point to check if this point is below.
     * @return True if this point is below the other point.
     */
    public boolean isUnder(Point other) {
        return other.isAbove(this);
    }

    /**
     * Check if this point is left of a received point.
     * @param other The point to check if this point is left of
     * @return True if this point is left of the other point

     */
    public boolean isLeft(Point other) {
        return (other._x > _x);
    }

    /**
     * Check if this point is right of a received point.
     * @param other The point to check if this point is right of
     * @return True if this point is right of the other point
     */
    public boolean isRight(Point other) {
        return other.isLeft(this);
    }

    /**
     * Check the distance between this point and a received point.
     * @param p The point to check the distance from
     * @return The distance between this point and a received point
     */
    public double distance(Point p) {
        return (Math.sqrt((Math.pow((_x - p._x), 2)) +
         (Math.pow((_y - p._y), 2))));
    }

    /**
     * Return number of quadrant or 0 if the point is on an axis.
     * @return an int representing the quadrant number.
     */
    public int quadrant() {
        final Point ZERO_CARTESIAN_POINT = new Point(0,0);//will let as determine the quadrant number.
        final  int quadrant1 = 1;
        final  int quadrant2 = 2;
        final  int quadrant3 = 3;
        final  int quadrant4 = 4;
        final  int quadrantNon = 0;

        if (this.isAbove(ZERO_CARTESIAN_POINT)) {
            if (this.isRight(ZERO_CARTESIAN_POINT)) {
                return quadrant1;
            } else if(this.isLeft((ZERO_CARTESIAN_POINT))) {
                return quadrant2;
            }
        } else if (ZERO_CARTESIAN_POINT.isAbove(this)) {
            if (this.isLeft((ZERO_CARTESIAN_POINT))) {
                return quadrant3;
            } else if (this.isRight(ZERO_CARTESIAN_POINT)) {
                return quadrant4;
            }
        } 
        return quadrantNon;//the point is on one of exist lines
    }

}
