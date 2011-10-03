/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsmug;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

/**
 *
 * @author anders
 */
public class Core implements ApplicationListener {

    static final int LOGICS_PER_SEC = 60;
    
    static final int NUM_SCENES = 10;
    static final int NUM_CAMERAS = 10;
    
    int fpsCounter = 0;
    private float nextUpdateTime = 0.0f;
    private float lastRenderTime = 0.0f;
    private float lastFpsUpdate = 0.0f;
    private float logicLength = 1.0f / LOGICS_PER_SEC;

    private LwjglApplication application;

    private String name;
    private int width;
    private int height;
    private boolean fullscreen;
    
    public Core() {
    }

    public void run(String name, int width, int height, boolean fullscreen) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.fullscreen = fullscreen;

        this.application = new LwjglApplication(this, this.name, this.width, this.height, this.fullscreen);

        Smug.input.init();
        Smug.graphics.init(this.width, this.height);
        Smug.audio.init();
    }

    @Override
    public void create() {
        Smug.app.create();
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

            Smug.app.update();
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
        Smug.app.dispose();
    }
}
