package jsmug.physics;

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
	
}
