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
				Collider c = gameObject.getCollider();
				if (c != null)
				{
					Rectangle rect = ((BoxCollider)c).getRectangle().add(c.getGameObject().getPosition());
					GL11.glBegin(GL11.GL_LINE_LOOP);
					{
						GL11.glVertex2f(rect.getV0().getX(), rect.getV0().getY());
						GL11.glVertex2f(rect.getV1().getX(), rect.getV0().getY());
						GL11.glVertex2f(rect.getV1().getX(), rect.getV1().getY());
						GL11.glVertex2f(rect.getV0().getX(), rect.getV1().getY());
					}
					GL11.glEnd();
				}
			}
		}	
	}
}
