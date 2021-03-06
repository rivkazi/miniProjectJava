package scene;

import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * Scene class which represent scene with geometries
 *
 * @author Rivka Zizovi 207265711 and Efrat Anconina 322796749
 */
public class Scene {
    //fields
    private String _name; //the name of the scene
    private Color _background; //the background of the scene
    private AmbientLight _ambientLight; //the ambient light of the scene
    private Geometries _geometries; //the geometries collection of the scene
    private Camera _camera; //the camera of the scene
    private double _distance; //the distance of the view plane from the camera of the scene
    private List<LightSource> _lights; //the collection of lights sources of the scene

    /**
     * constructor for the scene
     * @param name the name of the scene
     */
    public Scene(String name) {
        this._name = name;
        this._geometries = new Geometries();
        this._lights = new LinkedList<LightSource>();
    }

    /**
     * get method for the name field
     * @return the value of the name field
     */
    public String get_name() {
        return _name;
    }

    /**
     * get method for the ambient light field
     * @return the value of the ambient light field
     */
    public AmbientLight get_ambientLight() {
        return _ambientLight;
    }

    /**
     * get method for the background color field
     * @return the value of the background color field
     */
    public Color get_background() {
        return _background;
    }

    /**
     * get method for the camera object field
     * @return the value of the camera object field
     */
    public Camera get_camera() {
        return _camera;
    }

    /**
     * get method for the distance field
     * @return the value of the distance field
     */
    public double get_distance() {
        return _distance;
    }

    /**
     * get method for the geometries field
     * @return the collection of the geometries
     */
    public Geometries get_geometries() {
        return _geometries;
    }

    /**
     * get method for the lifht collection field
     * @return the collection of the light sources
     */
    public List<LightSource> get_lights() {
        return _lights;
    }

    /**
     * set method for the background color field
     * @param _background= the value of the background color
     */
    public void set_background(Color _background) {
        this._background = _background;
    }

    /**
     * set method for the ambient light field
     * @param _ambientLight= the value of the ambient light field
     */
    public void set_ambientLight(AmbientLight _ambientLight) {
        this._ambientLight = _ambientLight;
    }

    /**
     * set method for the camera object field
     * @param _camera= the value of the camera object field
     */
    public void set_camera(Camera _camera) {
        this._camera = _camera;
    }

    /**
     * set method for the distance field
     * @param _distance= the value of the distance field
     */
    public void set_distance(double _distance) {
        this._distance = _distance;
    }

    /**
     * this function enable adding geometry to a list
     * @param Geometries list of geometries
     */
    public void addGeometries(Intersectable... Geometries) {
            _geometries.add(Geometries);
    }

    /**
     * this method enable adding light sources to the collection
     * @param light list of light sources
     */
    public void addLights(LightSource... light) {
        if(light!=null){
            _lights= new LinkedList<>();
        }
       for(LightSource l: light)
            this._lights.add(l);
    }
}
