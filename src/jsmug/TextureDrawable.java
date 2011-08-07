package jsmug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;


public class TextureDrawable extends Component
{
	private Sprite sprite;
	
	public TextureDrawable(int sourceX, int sourceY, int width, int height, TextureResource texture)
	{
		this.sprite = new Sprite(texture.getTexture(), sourceX, sourceY, width, height);
		this.sprite.setOrigin(width / 2, height / 2);
	}
	
	public TextureDrawable(int width, int height, TextureResource texture)
	{
		this(0, 0, width, height, texture);
	}
	
	public TextureDrawable(int width, int height)
	{
		this(0, 0, width, height, new TextureResource());
	}
	
	public Sprite getSprite()
	{
		return this.sprite;
	}	
}
