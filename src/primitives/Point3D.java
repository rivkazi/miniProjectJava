package primitives;

import java.util.Objects;

/**
 * Geometries.Point3D class
 * @author Rivka Zizovi 207265711 and Efrat Anconina 322796749
 * this class represents a point
 */
public class Point3D {
    //fields
    private Coordinate c1;//coordinate in the space
    private Coordinate c2;//coordinate in the space
    private Coordinate c3;//coordinate in the space

    //zero point
    public final static Point3D ZERO = new Point3D(0.0, 0.0, 0.0);

    /**
     * constructor with three arguments
     * @param _c1= coordinate
     * @param _c2= coordinate
     * @param _c3= coordinate
     */
    public Point3D(Coordinate _c1, Coordinate _c2, Coordinate _c3) {
        this.c1 = _c1;
        this.c2 = _c2;
        this.c3 = _c3;
    }

    /**
     * constructor with three arguments
     * @param d1 double value
     * @param d2 double value
     * @param d3 double value
     */
    public Point3D(double d1, double d2, double d3) {
        this(new Coordinate(d1), new Coordinate(d2), new Coordinate(d3));
    }

    /**
     * copy constructor
     * @param p an existing point
     */
    public Point3D(Point3D p) {
        this.c1 = p.c1;
        this.c2 = p.c2;
        this.c3 = p.c3;
    }

    /**
     * get method for the coordinate c1 field
     * @return the value of the coordinate c1 field
     */
    public Coordinate getC1() {
        return c1;
    }

    /**
     * get method for the coordinate c2 field
     * @return the value of the coordinate c2 field
     */
    public Coordinate getC2() {
        return c2;
    }

    /**
     * get method for the coordinate c3 field
     * @return the value of the coordinate c3 field
     */
    public Coordinate getC3() {
        return c3;
    }

    /**
     * get method for the zero field
     * @return the value of the zero field
     */
    public static Point3D getZERO() {
        return ZERO;
    }

    /**
     * implements addition of a vector to a point
     * @param vector= vector we want to add
     * @return the result of the addition as a new point
     */
    public Point3D add(Vector vector) {
        Point3D point = vector.getHead();
        return new Point3D(
                this.c1.get() + point.c1.get(),
                this.c2.get() + point.c2.get(),
                this.c3.get() + point.c3.get());
    }

    /**
     * implements subtraction two points
     * @param p= point we want to sub
     * @return the result of the subtraction as a vector
     */
    public Vector subtract(Point3D p) {
        return new Vector(new Point3D(
                this.c1.get() - p.c1.get(),
                this.c2.get() - p.c2.get(),
                this.c3.get() - p.c3.get()));
    }

    /**
     * this function calculates the distance squared between two points
     * @param p1= point
     * @return the distance squared
     */
    public double distanceSquared(Point3D p1) {
        return ((p1.c1.get() - this.c1.get()) * (p1.c1.get() - this.c1.get()) +
                (p1.c2.get() - this.c2.get()) * (p1.c2.get() - this.c2.get()) +
                (p1.c3.get() - this.c3.get()) * (p1.c3.get() - this.c3.get()));
    }

    /**
     * this function calculates the distance between two points
     * @param p1= point
     * @return the distance
     */
    public double distance(Point3D p1) {
        return Math.sqrt(distanceSquared(p1));
    }

    /**
     * implement equal method(comparing)
     * @param o Object
     * @return true or false value regarding their equality
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Point3D)) return false;
        Point3D point3D = (Point3D) o;
        return c1.equals(point3D.c1) &&
                c2.equals(point3D.c2) &&
                c3.equals(point3D.c3);
    }

    /**
     * implement to string method
     * @return string describes the object
     */
    @Override
    public String toString() {
        return "Point3D{" +
                "c1=" + c1 +
                ", c2=" + c2 +
                '}';
    }
}
