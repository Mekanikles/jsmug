package jsmug.graphics;

import jsmug.Core;
import jsmug.GameObject;
import jsmug.physics.BoxCollider;
import jsmug.physics.Collider;
import jsmug.utils.Rectangle;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;

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
		
		for (GameObject gameObject : Core.getInstance().getScene().getGameObjects())
		{
			Drawable c = gameObject.getDrawable();
			if (c != null)
				c.draw(this.batch);
		}
		
		batch.end();	
		
		if (debugMode)
		{
			GL11.glColor3f(0.0f, 1.0f, 0.0f);
			for (GameObject gameObject : Core.getInstance().getScene().getGameObjects())
			{
				GL11.glColor4f(0.5f, 1.0f, 0.5f, 0.7f);
				Collider c = gameObject.getCollider();
				if (c != null)
					c.drawDebug();
				GL11.glColor4f(1.0f, 0.5f, 0.5f, 0.7f);
				Drawable d = gameObject.getDrawable();
				if (d != null)
					d.drawDebug();
				
			}
		}	
	}
}
