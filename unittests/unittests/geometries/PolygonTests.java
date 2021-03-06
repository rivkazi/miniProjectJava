/**
 * 
 */
package unittests.geometries;

import static org.junit.Assert.*;

import org.junit.Test;
import geometries.Intersectable.GeoPoint;
import geometries.*;
import primitives.*;

import java.util.List;

/**
 * Testing Polygons
 * @author Rivka Zizovi 207265711 and Efrat Anconina 322796749
 *
 */
public class PolygonTests {

    /**
     * Test method for
     * {@link geometries.Polygon#Polygon(Point3D...)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC02: Wrong vertices order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(0, 1, 0),
                    new Point3D(1, 0, 0), new Point3D(-1, 1, 1));
            fail("Constructed a polygon with wrong order of vertices");
        } catch (IllegalArgumentException e) {}

        // TC03: Not in the same plane
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 2, 2));
            fail("Constructed a polygon with vertices that are not in the same plane");
        } catch (IllegalArgumentException e) {}

        // TC04: Concave quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0.5, 0.25, 0.5));
            fail("Constructed a concave polygon");
        } catch (IllegalArgumentException e) {}

        // =============== Boundary Values Tests ==================

        // TC10: Vertix on a side of a quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0.5, 0.5));
            fail("Constructed a polygon with vertix on a side");
        } catch (IllegalArgumentException e) {}

        // TC11: Last point = first point
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0, 1));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

        // TC12: Collocated points
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 1, 0));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon pl = new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0),
                new Point3D(-1, 1, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals("Bad normal to trinagle", new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)));
    }

    /**
     * test method for the find intersection function between a ray and the polygon
     */
    @Test
    public void findIntsersections() {
        Polygon p = new Polygon(new Point3D(4.0, 4.0, 0.0), new Point3D(4.0, 4.0, 4.0), new Point3D(-4.0, 4.0, 4.0), new Point3D(-4.0, 4.0, 0.0));
        Point3D point;
        // ============ Equivalence Partitions Tests ==============

        //TC01 - ray intersects with polygon
        //refactoring to a list of geo points
        List<GeoPoint> result = p.findIntsersections(new Ray(new Point3D(1.0, -5.0, 3.0), new Vector(0.0, 3.0, 0.0)));
        point = new Point3D(1.0, 4.0, 3.0);
        assertEquals("ray not intersect", List.of(new GeoPoint(p, point)), result);

        //TC02- ray intersects with plane but outside the polygon against edge
        result = p.findIntsersections(new Ray(new Point3D(6.0, -1.0, 0.0), new Vector(0.0, 3.0, 0.0)));
        assertNull("ray intersect", result);

        //TC03- ray intersects with plane but outside the polygon against vertex
        result = p.findIntsersections(new Ray(new Point3D(5.0, 4.0, 4.0), new Vector(0.0, 3.0, 0.0)));
        assertNull("Ray intersect", result);

        // =============== Boundary Values Tests ==================

        //TC04- the ray begins before the plane on the edge of polygon
        result = p.findIntsersections(new Ray(new Point3D(4.0, 3.0, 0.0), new Vector(0.0, 1.0, 0.0)));
        assertNull("ray intersect", result);

        //TC05- the ray begins before the plane on vertex
        result = p.findIntsersections(new Ray(new Point3D(4.0, 3.0, 4.0), new Vector(0.0, 1.0, 0.0)));
        assertNull("ray intersect", result);

        //TC06- the ray begins before the plane on edge's continuation
        result = p.findIntsersections( new Ray(new Point3D(8.0, 2.0, 0.0), new Vector(0.0, 1.0, 0.0)));
        assertNull("ray intersect", result);
    }
}
