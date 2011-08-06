package smug;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Drawable extends Component
{
	private Sprite sprite;
	
	public Drawable(Vector size)
	{
		this.sprite = new Sprite(null, (int)size.getX(), (int)size.getY());
	}
	
	public Sprite getSprite()
	{
		return this.sprite;
	}	
}
