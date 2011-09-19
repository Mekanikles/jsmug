package jsmug.graphics;

import jsmug.SmugApplication;
import jsmug.GameObject;
import jsmug.physics.BoxCollider;
import jsmug.physics.Collider;
import jsmug.utils.Rectangle;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import jsmug.Smug;

public class Renderer
{
	private com.badlogic.gdx.graphics.g2d.SpriteBatch batch;
	private boolean debugMode = true;
	
	public Renderer(int screenWidth, int screenHeight)
	{
		this.batch = new com.badlogic.gdx.graphics.g2d.SpriteBatch();
		this.batch.getProjectionMatrix().setToOrtho2D(0, 0, screenWidth, screenHeight);
	}

	public void render()
	{
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Gdx.gl11.glColor4f(0,1,0,1);
		batch.begin();
		batch.enableBlending();
		
                Camera[] cameras = Smug.app.getCameras();
                
                int i, imax;
                
                for(i=0, imax=cameras.length; i<imax && cameras[i] != null; i++) {
                    cameras[i].render(batch);
                }

                batch.end();
	}
}
