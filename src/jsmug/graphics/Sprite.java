package jsmug.graphics;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import jsmug.GameObject;
import jsmug.Smug;
import jsmug.utils.Rectangle;

public class Sprite extends Drawable
{
	com.badlogic.gdx.graphics.g2d.Sprite sprite;

	public Sprite(Texture texture, int sourceX, int sourceY, int width, int height)
	{	
		this.sprite = new com.badlogic.gdx.graphics.g2d.Sprite(texture.getInternalTexture(), 
				sourceX, sourceY, width, height);
		this.sprite.setOrigin(width / 2, height / 2);
	}

	public Sprite(Texture texture, Rectangle source)
	{
		this(new Texture(), (int)source.getX(), (int)source.getY(), 
				(int)source.getWidth(), (int)source.getHeight());
	}
	
	public Sprite(int width, int height, Texture texture)
	{
		this(texture, 0, 0, width, height);
	}
	
	public Sprite(int width, int height)
	{
		this(new Texture(), 0, 0, width, height);
	}
	
	
	public void setOrigin(float width, float height)
	{
		this.sprite.setOrigin(width / 2, height / 2);
	}
	
	
	public void setScale(float scale)
	{
		this.sprite.setScale(scale);
	}
	
	public float getWidth()
	{
		return this.sprite.getWidth();
	}
	
	public float getHeight()
	{
		return this.sprite.getHeight();
	}

	@Override
	void draw(SpriteBatch batch)
	{
		this.sprite.setPosition(this.getGameObject().getPositionX() - this.sprite.getOriginX(), 
				this.getGameObject().getPositionY() - this.sprite.getOriginY());
		this.sprite.draw(batch);
	}
	
	public void drawDebug()
	{
		Rectangle rect = new Rectangle(this.getGameObject().getPositionX()  - this.sprite.getOriginX(), 
				this.getGameObject().getPositionY()  - this.sprite.getOriginY(), 
				this.sprite.getWidth() * this.sprite.getScaleX(), 
				this.sprite.getHeight() * this.sprite.getScaleY());
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
