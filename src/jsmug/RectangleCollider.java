package jsmug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class RectangleCollider extends Component
{
	private Rectangle rect;
	
	public RectangleCollider(float width, float height)
	{
		this.rect = new Rectangle(new Vector(-width/2.0f, -height/2.0f), width, height);
	}
	
	public Rectangle getRectangle()
	{
		return rect;
	}
	
}
