/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsmug.graphics;

import jsmug.GameObject;
import jsmug.Scene;
import jsmug.Smug;
import jsmug.physics.Collider;

import org.lwjgl.opengl.GL11;

/**
 *
 * @author anders
 */
public class Camera {

    private Scene scene = null;

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void render(com.badlogic.gdx.graphics.g2d.SpriteBatch batch) {
        for (GameObject gameObject : this.scene.getGameObjects()) {
            Drawable c = gameObject.getDrawable();
            if (c != null) {
                c.draw(batch);
            }
        }

        //if (debugMode) {
            GL11.glColor3f(0.0f, 1.0f, 0.0f);
            for (GameObject gameObject : this.scene.getGameObjects()) {
                GL11.glColor4f(0.5f, 1.0f, 0.5f, 0.7f);
                Collider c = gameObject.getCollider();
                if (c != null) {
                    c.drawDebug();
                }
                GL11.glColor4f(1.0f, 0.5f, 0.5f, 0.7f);
                Drawable d = gameObject.getDrawable();
                if (d != null) {
                    d.drawDebug();
                }

            }
        //}
    }
}
