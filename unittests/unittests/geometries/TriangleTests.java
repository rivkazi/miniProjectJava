package unittests.geometries;

import geometries.Intersectable.GeoPoint;
import geometries.Triangle;
import geometries.Tube;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit tests for Geometries.Sphere class
 *
 * @author Rivka Zizovi 207265711 and Efrat Anconina 322796749
 */
public class TriangleTests {

    /**
     * Test method for {@link Triangle#getNormal(Point3D)} ()} (Geometries.Triangle)}.
     */
    @Test
    public void getNormal() {
        //    // ============ Equivalence Partitions Tests ==============
        //    // TC01: There is a simple single test here
        //    Triangle t = new Triangle(new Point3D(1,0,0), new Point3D(0, 1, 0), new Point3D(0, 0, 1));
        //    assertEquals("the normal of the triangle is:", (new Vector(-1,-1,-1)).normalize(), t.getNormal(new Point3D(1,0,0)));

        Triangle triangle = new Triangle(new Point3D(-1, 0, 0), new Point3D(1, 0, 0), new Point3D(0, 0, 1));
        Vector vector = new Vector(0, -1, 0).normalize();

        assertEquals(vector, triangle.getNormal(new Point3D(-1, 0, 0)));

        assertEquals(vector, triangle.getNormal(new Point3D(0, 0, 0)));

        assertEquals(vector, triangle.getNormal(new Point3D(0.5, 0, 0)));
    }

    /***
     * test method for the find intersection function between a ray and the triangle
     */
    @Test
    public void findIntsersections() {
        // ============ Equivalence Partitions Tests ==============
        Triangle t=new Triangle(new Point3D(0,0,0),new Point3D(3,0,0),new Point3D(0,0,3));
        // TC01: Inside the triangle
        Ray r=new Ray(new Point3D(1,1,1), new Vector(0,-2,0));
        //refactoring to a list of geo points
        List<GeoPoint> result = t.findIntsersections(r);
        assertEquals("Wrong number of points", 1, result.size());
        Point3D p1 = new Point3D(1, 0, 1);
        assertEquals("Ray intersect the triangle inside", List.of(new GeoPoint(t, p1)), result);

        // TC02: Outside against edge
        Ray r2=new Ray(new Point3D(1,1,1), new Vector(-2,0,0));
        assertNull("Ray intersect the triangle", t.findIntsersections(r2));

        // TC03: Outside against vertex
        Ray r3=new Ray(new Point3D(-1,-1,0), new Vector(-1,1,0));
        assertNull("Ray intersect the triangle", t.findIntsersections(r3));

        // =============== Boundary Values Tests ==================

        // **** Group: the ray begins "before" the plane

        // TC11: On edge
        Ray r11=new Ray(new Point3D(-1,-1,1), new Vector(2,2,2));
        assertNull("Ray intersect the triangle on the edge", t.findIntsersections(r11));

        // TC12: In vertex
        Ray r12=new Ray(new Point3D(-1,-1,3), new Vector(2,2,0));
        assertNull("Ray intersect the triangle in vertex", t.findIntsersections(r12));

        // TC13: On edge's continuation
        Ray r13=new Ray(new Point3D(-1,-1,5), new Vector(2,2,-2));
        assertNull("Ray intersect the triangle On edge's continuation", t.findIntsersections(r13));
    }
}