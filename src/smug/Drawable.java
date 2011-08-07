package smug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;


public class Drawable extends Component
{
	private Sprite sprite;
	
	public Drawable(int sourceX, int sourceY, int width, int height, String textureName)
	{
		this.sprite = new Sprite(new Texture(Gdx.files.internal("data/" + textureName)), sourceX, sourceY, width, height);
		this.sprite.setOrigin(width / 2, height / 2);
	}
	
	public Sprite getSprite()
	{
		return this.sprite;
	}	
}
