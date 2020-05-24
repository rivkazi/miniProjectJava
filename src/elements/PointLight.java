package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * Elements.PointLight class
 * @author Rivka Zizovi 207265711 and Efrat Anconina 322796749
 * this class repredent the source of light as a point light
 */
public class PointLight extends Light implements LightSource {
    //fields
    protected Point3D position;
    protected double kC;
    protected double kL;
    protected double kQ;

    /**
     * constructor to the point light source
     * @param color the color of the light source
     * @param p the position point of the source
     * @param kc factor of  attenuation because of the distance
     * @param kl factor of  attenuation because of the distance
     * @param kq factor of  attenuation because of the distance
     */
    public PointLight(Color color, Point3D p, double kc, double kl, double kq) {
        super(color);
        this.position = p;
        this.kC = kc;
        this.kL = kl;
        this.kQ = kq;
    }

    /**
     * method calculate the intensity of the light in the intersected point
     * @param p the intersected point
     * @return the intensity of the light in this point
     */
    @Override
    public Color getIntensity(Point3D p) {
        double d_squared = p.distanceSquared(this.position);
        double d = p.distance(this.position);
        return (_intensity.reduce(kC + kL * d + kQ * d_squared));
    }

    /**
     * this method calculate the vector from the light source to the intersected point
     * @param p the intersected point
     * @return the vector from the source to the point
     */
    @Override
    public Vector getL(Point3D p) {
        if (p.equals(position)) {
            return null;
        }
        return (p.subtract(position).normalize());
    }
}
