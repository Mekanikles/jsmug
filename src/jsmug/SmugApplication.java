package jsmug;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import java.util.Arrays;
import jsmug.graphics.Camera;
import jsmug.utils.Array;

public class SmugApplication {

    static final int LOGICS_PER_SEC = 60;
    
    static final int NUM_SCENES = 10;
    static final int NUM_CAMERAS = 10;

    public String name = "JSmug Game";
    public int width = 800;
    public int height = 600;
    public boolean fullscreen = false;
    
    
    public Scene scene;
    public Camera camera;

    private Array<Scene> scenes;
    private Array<Camera> cameras;
    
    public SmugApplication() {
        this.scenes = new Array(Scene.class, NUM_SCENES);
        this.cameras = new Array(Camera.class, NUM_CAMERAS);
        
        Scene scene = Smug.newScene();
        Camera camera = Smug.newCamera();
        camera.setScene(scene);
        
        this.addScene(scene);
        this.addCamera(camera);
    }

    public void create() {
    }
    
    public void update() {
    }
    
    public void dispose() {
    }
    
    public void addScene(Scene scene) {
        this.scenes.push(scene);
    }
    
    public void removeScene(Scene scene) {
        this.scenes.remove(scene);
    }
    
    public void addCamera(Camera camera) {
        this.cameras.push(camera);
    }
    
    public void addCameraAfter(Camera where, Camera camera) {
        this.cameras.insertAfter(where, camera);
    }
    
    public void addCameraBefore(Camera where, Camera camera) {
        this.cameras.insertBefore(where, camera);
    }
    
    public void removeCamera(Camera camera) {
        this.cameras.remove(camera);
    }
    
    public void bringCameraToFront(Camera camera) {
        this.cameras.remove(camera);
        this.cameras.push(camera);
    }
    
    public Camera[] getCameras() {
        return this.cameras.elements;
    }
    
    public void addGameObject(GameObject gameObject) {
        if(this.scenes.elements[0] != null) {
            this.scenes.elements[0].addGameObject(gameObject);
        }
    }
}
