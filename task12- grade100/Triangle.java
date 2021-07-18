
/**
 * Triangle.java represents a triangle in the Euclidean space.
 * 
 * @class: Triangle
 * @author : Daniel Leibner
 * @authorId : #########
 * @version: 07/04/21
 */
public class Triangle {

    private Point _point1;
    private Point _point2;
    private Point _point3;
    static final double EPSILON = 0.001;

    /**
     * Construct a new Triangle (default constructor) from 3 default
     * Points:(1,0),(-1,0),(0,1)
     */
    public Triangle() {
        //using private method for simplify the other constructors
        //and for not repeating on code
        // the solution of using this is not good enough because we most assignee value
        // before check and change it for the given if the points pass(this is repeating on
        // assignment for nothing )
        setDefaultPoint();

    }


    /**
     * private method for set the Triangle points from 3 default
     * Points:(1,0),(-1,0),(0,1)
     */
    private void setDefaultPoint() {
        _point1 = new Point(1, 0);
        _point2 = new Point(-1, 0);
        _point3 = new Point(0, 1);
    }


    /**
     * Construct a new Triangle (from 3 points)
     *
     * @param p1 the first Point
     * @param p2 the second Point
     * @param p3 the third Point
     */
    public Triangle(Point p1, Point p2, Point p3) {
        if (isValid(p1, p2, p3)) {
            _point1 = new Point(p1);
            _point2 = new Point(p2);
            _point3 = new Point(p3);
        } else {
            setDefaultPoint();
        }
    }

    /**
     * Construct a new Triangle (from 6 reals)
     *
     * @param x1 the x coordinate of the first Point
     * @param y1 the y coordinate of the first Point
     * @param x2 the x coordinate of the second Point
     * @param y2 the y coordinate of the second Point
     * @param x3 the x coordinate of the third Point
     * @param y3 the y coordinate of the third Point
     */
    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        if (isValid(new Point(x1, y1), new Point(x2, y2), new Point(x3, y3))) {
            _point1 = new Point(x1, y1);
            _point2 = new Point(x2, y2);
            _point3 = new Point(x3, y3);
        } else {
            setDefaultPoint();
        }
    }


    /**
     * Copy constructor Creates a new triangle identical to the given triangle
     *
     * @param other the triangle to be copied
     */
    public Triangle(Triangle other) {
        _point1 = new Point(other._point1);
        _point2 = new Point(other._point2);
        _point3 = new Point(other._point3);
    }


    /**
     * This method returns the triangle's first point
     *
     * @return The first point of the triangle
     */
    public Point getPoint1() {
        return new Point(_point1);
    }

    /**
     * This method returns the triangle's second point
     *
     * @return The second point of the triangle
     */
    public Point getPoint2() {
        return new Point(_point2);
    }

    /**
     * This method returns the triangle's third point
     *
     * @return The third point of the triangle
     */
    public Point getPoint3() {
        return new Point(_point3);
    }

    /**
     * This method sets the first point of the triangle.
     *
     * @param p The new first point
     */
    public void setPoint1(Point p) {
        if (isValid(p, _point2, _point3)) {
            _point1 = new Point(p);
        }
    }

    /**
     * This method sets the second point of the triangle.
     *
     * @param p The new second point
     */
    public void setPoint2(Point p) {
        if (isValid(_point1, p, _point3)) {
            _point2 = new Point(p);
        }
    }

    /**
     * This method sets the third point of the triangle.
     *
     * @param p The third point
     */
    public void setPoint3(Point p) {
        if (isValid(_point1, _point2, p)) {
            _point3 = new Point(p);
        }
    }

    /**
     * This method checks if 3 points make a valid triangle. A triangle is valid if
     * no sum of any two sides(represented by a,b,c) equals the third side (with precision EPSILON)
     *
     * @param p1 the first point
     * @param p2 the second point
     * @param p3 the third point
     * @return true if the triangle(p1,p2,p3) is valid
     */
    public static boolean isValid(Point p1, Point p2, Point p3) {
        double sideA = p1.distance(p2); //first side
        double sideB = p2.distance(p3);  //second side
        double sideC = p1.distance(p3);  //third side
        return (!equalsToEpsilon(sideA + sideB, sideC)) && (!equalsToEpsilon(sideA + sideC, sideB)) && (!equalsToEpsilon(sideB + sideC, sideA));
    }


    /**
     * this method calc the length of the side between _point1 _point2.
     *
     * @return the distance in double of side A
     */
    private double sideA() {
        return (_point1.distance(_point2));
    }

    /**
     * this method calc the length of the side between _point2 _point3 .
     *
     * @return the distance in double of side B
     */
    private double sideB() {
        return (_point2.distance(_point3));
    }


    /**
     * this method calc the length of the side between _point3 _point1 .
     *
     * @return the distance in double of side C
     */
    private double sideC() {
        return (_point3.distance(_point1));
    }


    /**
     * this method compere between 2 double with epsilon accuracy
     *
     * @param numA first double to compere
     * @param numB second double to compere
     * @return true if the 2 double's equals with epsilon accuracy
     */
    private static boolean equalsToEpsilon(double numA, double numB) {
        return (EPSILON > Math.abs(numA - numB));
    }



    /**
     * This method returns a String representation of the Triangle. The format of
     * the string should be: {(x1,y1),(x2,y2),(x3,y3)}
     *
     * @return a String representation of the triangle
     */
    @Override
    public String toString() {
        return ("{" + _point1 + "," + _point2 + "," + _point3 + "}");
    }

    /**
     * This method returns the triangle's perimeter.
     *
     * @return the triangle's perimeter
     */
    public double getPerimeter() {
        return (sideA() + sideB() + sideC());
    }

    /**
     * This method returns the triangle's area (using Heron's formula).
     *
     * @return the triangle's area
     */
    public double getArea() {
        double halfPerimeter = getPerimeter() / 2.0; //half of the perimeter
        return (Math.sqrt(halfPerimeter * (halfPerimeter - sideA()) *
                (halfPerimeter - sideB()) * (halfPerimeter - sideC())));
    }

    /**
     * This method returns true if the triangle is an isosceles triangle(with
     * precision EPSILON).
     *
     * @return true if the triangle is an isosceles triangle
     */
    public boolean isIsosceles() {
        //using or operator for cover all the options of pear sides in triangle
        return ((equalsToEpsilon(sideA(), sideB())) ||
                (equalsToEpsilon(sideA(), sideC())) ||
                (equalsToEpsilon(sideB(), sideC())));

    }

    /**
     * this method return the second power of given number.
     *
     * @param num the number to power by 2
     * @return the the second power of num
     */
    private static double secondPower(double num) {
        return Math.pow(num, 2);
    }


    /**
     * This method returns true if the triangle is a right-angled triangle.
     *
     * @return true if the triangle is a right-angled triangle.
     */
    public boolean isPythagorean() {
        //using or operator for cover all the options of pear sides in triangle
        //checking by the formula of Pythagoras
        return ((equalsToEpsilon((secondPower(sideA()) + secondPower(sideB())), secondPower(sideC()))) ||
                (equalsToEpsilon((secondPower(sideC()) + secondPower(sideB())), secondPower(sideA()))) ||
                (equalsToEpsilon((secondPower(sideA()) + secondPower(sideC())), secondPower(sideB()))));
    }

    /**
     * This method returns true if the triangle is in a given circle.
     *
     * @param x the x value of the point which is the center of the circle
     * @param y the y value of the point which is the center of the circle
     * @param r the radius of the circle
     * @return true if the triangle is in a given circle.
     */
    public boolean isContainedInCircle(double x, double y, double r) {

        final Point center = new Point(x, y);
        double p1ToCenter = _point1.distance(center); //distance point1 from the center of the circle
        double p2ToCenter = _point2.distance(center); //distance point2 from the center of the circle
        double p3ToCenter = _point3.distance(center); //distance point3 from the center of the circle
        //will check if the radius is bigger than the distance between each vertices of the triangle to center of circle
        // using the and operator for the next programmer will see the action in the simplest way
        return ((EPSILON + r > p1ToCenter) && (EPSILON + r > p2ToCenter) && (EPSILON + r > p3ToCenter));
    }


    /**
     * This private method returns true if p1 is under p2 and false otherwise.
     * and if there no lower point the result will be determine by which is lefter in those 2 points.
     *
     * @param p1 point number 1
     * @param p2 point number 2
     * @return true if 1 is under p2 or the point in same height but p1 is left then p2.
     */
    private static boolean isLowestLeftThen(Point p1, Point p2) {
        if (p1.isUnder(p2)) {
            return true;
        } else if (p2.isUnder(p1)) {
            return false;
        } else//the two points in the same height
            return p1.isLeft(p2);
    }

    /**
     * This method returns the lowest(left if same lowest height to 2 points) vertex of the triangle.
     *
     * @return the lowest (left if same lowest height to 2 points) vertex
     */
    public Point lowestPoint() {
        if (isLowestLeftThen(_point1, _point2)) { //p1<p2
            if (isLowestLeftThen(_point1, _point3)) { //p1<p3
                return new Point(_point1);
            } else { //p1<p2 && !(p1<p3)
                return new Point(_point3);
            }
        } else if (isLowestLeftThen(_point2, _point3)) {//from else !(p1<p2), from if p2<p3
            return new Point(_point2);
        } else { //from first if  !(p1<p2) from first else if !(p2<p3)
            return new Point(_point3);
        }

    }

    /**
     * This private method returns true if p1 is above p2 and false otherwise.
     * if there no highest point the result will be determine by which is lefter in those 2 points.
     *
     * @param p1 point number 1
     * @param p2 point number 2
     * @return true if 1 is above p2 or the point in same height but p1 is left then p2.
     */
    private static boolean isHighestLeftThen(Point p1, Point p2) {
        if (p1.isAbove(p2)) {
            return true;
        } else if (p2.isAbove(p1)) {
            return false;
        } else return p1.isLeft(p2);//the two points in the same height
    }


    /**
     * This method returns the highest(left if same highest height to 2 points) vertex of the triangle.
     *
     * @return the highest Vertex (left if same highest height to 2 points)
     */
    public Point highestPoint() {
        if (isHighestLeftThen(_point1, _point2)) { //p1>p2
            if (isHighestLeftThen(_point1, _point3)) { //p1>p3
                return new Point(_point1);
            } else { //!(p1>p3)=>(p3 is higher or in same height but left then p1
                return new Point(_point3);
            }
        } else if (isHighestLeftThen(_point2, _point3)) { //from first if !(p1>p2) from first else if p2>p3
            return new Point(_point2);
        } else { //from first if !(p1>p2) from first else if !(p2>p3)
            return new Point(_point3);
        }
    }

    /**
     * This method returns true if the triangle is entirely in one quadrant and not on the axis.
     *
     * @return true if the triangle is entirely in one quadrant
     */
    public boolean isLocated() {
        final int AXIS_REPRESENT = 0;
        //check if is a point on exis lines and if not check that the rest to dots in the same quadrant
        return (AXIS_REPRESENT != _point1.quadrant()) && //checks that no point on the ex's lines
                (_point1.quadrant() == _point2.quadrant()) &&
                (_point1.quadrant() == _point3.quadrant());
    }

    /**
     * Check if this triangle is completely above a received triangle.
     *
     * @param other the triangle to check if this triangle is above
     * @return true if this triangle is above the other triangle
     */
    public boolean isAbove(Triangle other) {
        //if the lowest apex in this triangle is above the highest apex in the other triangle,
        //So for sure "this" triangle is above "other" triangle.
        return lowestPoint().isAbove(other.highestPoint());
    }

    /**
     * Check if this triangle is completely below a received triangle.
     *
     * @param other the triangle to check if this triangle is below
     * @return true if this triangle is below the other triangle
     */
    public boolean isUnder(Triangle other) {
        return other.isAbove(this);
    }

    /**
     * Check if the given triangle is equal to this triangle.
     *
     * @param other the triangle we are checking equality with
     * @return true if the triangle other is equal to this triangle
     */
    public boolean equals(Triangle other) {
        //returning true gust if all three points are the same!!
        return ((_point1.equals(other._point1)) &&
                (_point2.equals(other._point2)) &&
                (_point3.equals(other._point3)));
    }

    /**
     * Check if the given triangle is congruent to this triangle.
     * every triangle have 9 option to be so.
     * @param other the triangle we are checking congruency with
     * @return true if the triangle other is congruent to this triangle
     */
    public boolean isCongruent(Triangle other) {
        //saving sides of other triangle to compere it after by the side,side,side sentence.
        double otherSideA = other._point1.distance(other._point2);
        double otherSideB = other._point2.distance(other._point3);
        double otherSideC = other._point3.distance(other._point1);
        //complex checking by 3 steps. for compere all "this" sides to "other" sides.
        //3 steps:    sideX=Side{A,B,C}
        //step 1 : will check if otherSideA is equals to  side(X) in this triangle
        //step 2 : will check if otherSideB is equals to  side(x+1) &&
        //          if otherSideC is equals to side(x+2) in this triangle
        //step 3 : will check if otherSideC is equals to  side(x+1) &&
        //          if otherSideB is equals to  side(x+2) in this triangle
        if(equalsToEpsilon( sideA() , otherSideA)) {
            if ((equalsToEpsilon(sideB() , otherSideB)) && (equalsToEpsilon(sideC() , otherSideC))) {
                return true;
            }else if ((equalsToEpsilon(sideB() , otherSideC)) && (equalsToEpsilon(sideC() ,otherSideB))) {
                return true;
            }
        } else if (equalsToEpsilon(sideB(), otherSideA)){
                if((equalsToEpsilon(sideC() , otherSideB)) && (equalsToEpsilon(sideA() , otherSideC))) {
                    return true;
                }else if ((equalsToEpsilon(sideC() , otherSideC)) && (equalsToEpsilon(sideA() ,otherSideB))) {
                    return true;
                }
        } else if (equalsToEpsilon(sideC() ,otherSideA)) {
            if((equalsToEpsilon(sideA() , otherSideB)) && (equalsToEpsilon(sideB() , otherSideC))){
                return true;
            }else if ((equalsToEpsilon(sideB() ,otherSideB)) && (equalsToEpsilon(sideA(), otherSideC))) {
                return true;
            }
        }
        return false;


    }
}













