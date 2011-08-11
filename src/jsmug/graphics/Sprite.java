package jsmug.graphics;

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
}
