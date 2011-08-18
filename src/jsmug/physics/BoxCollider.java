package jsmug.physics;

import org.lwjgl.opengl.GL11;

import jsmug.Component;
import jsmug.utils.Rectangle;
import jsmug.utils.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BoxCollider extends Collider
{
	private Rectangle rect;
	
	public BoxCollider(float width, float height)
	{
		this.rect = new Rectangle(new Vector(-width/2.0f, -height/2.0f), width, height);
	}
	
	public Rectangle getRectangle()
	{
		return rect;
	}
	
	public void drawDebug()
	{
		Rectangle rect = this.rect.add(getGameObject().getPosition());
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
