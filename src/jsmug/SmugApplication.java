package jsmug;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import java.util.Arrays;
import jsmug.graphics.Camera;
import jsmug.utils.Array;

public class SmugApplication implements ApplicationListener {

    static final int LOGICS_PER_SEC = 60;
    
    static final int NUM_SCENES = 10;
    static final int NUM_CAMERAS = 10;
    
    public String name = "JSmug Game";
    public int width = 800;
    public int height = 600;
    public boolean fullscreen = false;
    
    public Scene scene;
    public Camera camera;

    int fpsCounter = 0;
    private float nextUpdateTime = 0.0f;
    private float lastRenderTime = 0.0f;
    private float lastFpsUpdate = 0.0f;
    private float logicLength = 1.0f / LOGICS_PER_SEC;

    private Array<Scene> scenes;
    private Array<Camera> cameras;
    
    private LwjglApplication application;

    public SmugApplication() {
    }

    public void run() {
        this.scenes = new Array(Scene.class, NUM_SCENES);
        this.cameras = new Array(Camera.class, NUM_CAMERAS);
        
        this.application = new LwjglApplication(this, this.name, this.width, this.height, this.fullscreen);

        Smug.input.init();
        Smug.graphics.init(this.width, this.height);
        Smug.audio.init();
        
        Scene scene = Smug.newScene();
        Camera camera = Smug.newCamera();
        camera.setScene(scene);
        
        this.addScene(scene);
        this.addCamera(camera);
    }

    @Override
    public void create() {
        this.start();
    }

    @Override
    public void resume() {
    }

    @Override
    public void render() {
        Smug.time.update(Gdx.graphics.getDeltaTime());
        float time = Smug.time.getTime();

        if (time - lastFpsUpdate >= 1.0f) {
            //System.out.print(this.name + ", fps: " + this.fpsCounter + "\n");
            this.fpsCounter = 0;
            this.lastFpsUpdate = time;
        }

        if (time >= this.nextUpdateTime + this.logicLength) {
            this.nextUpdateTime += this.logicLength;

            this.update();
        }


        Smug.graphics.render();
        Smug.audio.update(Gdx.graphics.getDeltaTime(), new double[]{1.0});
        this.lastRenderTime = time;
        this.fpsCounter++;
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void dispose() {
    }

    public void start() {
        System.out.print("Game Start\n");
    }

    public void update() {
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
