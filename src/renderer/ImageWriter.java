package renderer;

import elements.Camera;
import geometries.Intersectable;
import primitives.Ray;
import scene.Scene;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.*;

/**
 * Image writer class combines accumulation of pixel color matrix and finally
 * producing a non-optimized jpeg image from this matrix. The class although is
 * responsible of holding image related parameters of View Plane - pixel matrix
 * size and resolution
 *
 * @author Rivka Zizovi 207265711 and Efrat Anconina 322796749
 */
public class ImageWriter {
    private double _imageWidth, _imageHeight;
    private int _nX, _nY;

    private static final String FOLDER_PATH = System.getProperty("user.dir") + "/images";

    private BufferedImage _image;
    private String _imageName;

    private Logger _logger = Logger.getLogger("ImageWriter");

    // ***************** Constructors ********************** //
    /**
     * Image Writer constructor accepting image name and View Plane parameters,
     *
     * @param imageName the name of png file
     * @param width     View Plane width in size units
     * @param height    View Plane height in size units
     * @param nX        amount of pixels by Width
     * @param nY        amount of pixels by height
     */
    public ImageWriter(String imageName, double width, double height, int nX, int nY) {
        _imageName = imageName;
        _imageWidth = width;
        _imageHeight = height;
        _nX = nX;
        _nY = nY;

        _image = new BufferedImage(_nX, _nY, BufferedImage.TYPE_INT_RGB);
    }

    // ***************** Getters/Setters ********************** //
    /**
     * View Plane width getter
     *
     * @return the width
     */
    public double getWidth() {
        return _imageWidth;
    }

    /**
     * View Plane height getter
     *
     * @return the height
     */
    public double getHeight() {
        return _imageHeight;
    }

    /**
     * View Plane Y axis resolution
     *
     * @return the amount of vertical pixels
     */
    public int getNy() {
        return _nY;
    }

    /**
     * View Plane X axis resolution
     *
     * @return the amount of horizontal pixels
     */
    public int getNx() {
        return _nX;
    }

    // ***************** Operations ******************** //

    /**
     * Function writeToImage produces unoptimized png file of the image according to
     * pixel color matrix in the directory of the project
     */
    public void writeToImage() {
        try {
            File file = new File(FOLDER_PATH + '/' + _imageName + ".png");
            ImageIO.write(_image, "png", file);
        } catch (IOException e) {
            _logger.log(Level.SEVERE, "I/O error", e);
        }
    }

    /**
     * The function writePixel writes a color of a specific pixel into pixel color
     * matrix
     *
     * @param xIndex X axis index of the pixel
     * @param yIndex Y axis index of the pixel
     * @param color  final color of the pixel
     */
    public void writePixel(int xIndex, int yIndex, Color color) {
        _image.setRGB(xIndex, yIndex, color.getRGB());
    }



    public static List<Intersectable.GeoPoint> miniProject(Scene s, ImageWriter IW){
        miniProject(s, IW, 0);



        List<Intersectable.GeoPoint> p = null;
        return p;
    }

    public static List<Intersectable.GeoPoint> miniProject(Scene s, ImageWriter IW, int level){
        List<Intersectable.GeoPoint> geoPoints = null;
        if(level==7)
            return geoPoints;
        Scene scene= s;
        Camera c = s.get_camera();
        Intersectable geos= scene.get_geometries();
        int nx= IW.getNx();
        int rnx= (int)Math.pow(2,level);
        int ny= IW.getNy();
        int rny= (int)Math.pow(2,level);
        double distance= scene.get_distance();
        Ray ray=null;
        List<Intersectable.GeoPoint> temp = null;
        for(int i=0; i<rnx; i++)
            for(int j=0; j<rny; j++){
                ray= c.constructRayThroughPixel(rnx, rny, j, i, distance,IW.getWidth(), IW.getHeight());
                temp= geos.findIntsersections(ray);
                if(temp==null)
                    break;



            }





        return geoPoints;
    }


}