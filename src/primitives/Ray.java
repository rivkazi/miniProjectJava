package primitives;

import java.util.Objects;

import static primitives.Util.isZero;

/**
 * Geometries.Ray class
 * @author Rivka Zizovi 207265711 and Efrat Anconina 322796749
 * this class represents a ray
 */
public class Ray {
    private static final double DELTA = 0.1;

    //fields
    private Vector dir; //vector represents the direction of the ray
    private Point3D _p0; //point represent the initial point of the ray

    /**
     * constructor with two arguments
     * @param dir  the vector of the ray
     * @param _p0 point to the ray
     */
    public Ray(Point3D _p0, Vector dir) {
        this._p0 = new Point3D(_p0);
        this.dir = new Vector(dir.normalize());
    }

    /**
     * copy constructor
     * @param r= an existing ray
     */
    public Ray(Ray r) {
        this._p0 = new Point3D(r._p0);
        this.dir = new Vector(r.dir);
    }

    /**
     * this function create a ray that it's initial point start after a "delta" distance
     * @param point the initial point of the ray
     * @param direction the direction of the ray
     * @param normal vector that is normal to the ray
     * this function help for checking intersection point in advenced level of the project
     *we adding the "delta" distance in the direction of the normal, the reason is to avoid the ray from being intersected with itself
     */
    public Ray(Point3D point, Vector direction, Vector normal) {
        //point + normal.scale(±DELTA)
        this.dir = new Vector(direction).normalized();

        double nv = normal.dotProduct(direction);

        Vector normalDelta = normal.scale((nv > 0 ? DELTA : -DELTA));
        this._p0 = point.add(normalDelta);
    }


    /**
     * get method for the point field
     * @return the value of the point field
     */
    public Point3D get_p0() {
        return _p0;
    }

    /**
     * get method for the vector field
     * @return the value of the vector field
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * implement equal method(comparing)
     * @param o Object
     * @return true or false value regarding their equality
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return Objects.equals(dir, ray.dir) &&
                Objects.equals(_p0, ray._p0);
    }

    /**
     * implement to string method
     * @return string describes the object
     */
    @Override
    public String toString() {
        return "Ray{" +
                "dir=" + dir +
                ", _p0=" + _p0 +
                '}';
    }

    /**
     * refactoring for calculating the value of a point on a ray
     * @param t= parameter who means the value of scalar
     * @return new point after the addition of the scalar multiplication
     */
    public Point3D getPoint(double t) {
        return isZero(t) ? _p0 : _p0.add(dir.scale(t));
    }
}
