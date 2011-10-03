/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import java.util.logging.Level;
import java.util.logging.Logger;
import jsmug.GameObject;
import jsmug.Scene;
import jsmug.Smug;
import jsmug.SmugApplication;
import jsmug.graphics.Camera;
import jsmug.graphics.Panel;

/**
 *
 * @author anders
 */
public class Editor<T extends SmugApplication> extends SmugApplication {
    T application;
    Scene scene;
    Camera camera;
    
    public void initEditor() {
        Smug.resources.setResourcePath("data/");
        Panel p = new Panel(Smug.resources.getTexture("panel"), 2);
        p.setWidth(300);
        p.setHeight(120);

        GameObject panel = Smug.newGameObject();
        panel.addComponent(p);
        panel.setPositionX(10);
        panel.setPositionY(10);

        this.scene = Smug.newScene();
        this.camera = Smug.newCamera();
        this.camera.setScene(scene);
        
        this.scene.addGameObject(panel);
        
        this.application.addScene(this.scene);
        this.application.addCamera(this.camera);
    }
    
    public void loadApplication(Class<T> clazz) {
    }
}
