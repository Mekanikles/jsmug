package jsmug.graphics;

import java.util.List;
import java.util.LinkedList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import jsmug.Smug;
import jsmug.utils.Rectangle;

public class Text extends Drawable
{
	private BitmapFont font;
	private char[] text;
	
	private List<com.badlogic.gdx.graphics.g2d.Sprite> sprites = new LinkedList<com.badlogic.gdx.graphics.g2d.Sprite>();
	private float scale = 1.0f;
	
	public Text(BitmapFont font, String text)
	{
		this.font = font;
		this.setText(text);
	}
	
	public void setText(String text)
	{
		this.sprites.clear();
		this.text = text.toCharArray();
		
		BitmapFont.Glyph glyph;
		com.badlogic.gdx.graphics.g2d.Sprite sprite;

		for (char c : this.text)
		{
			glyph = this.font.getGlyph(c);
			sprite =new com.badlogic.gdx.graphics.g2d.Sprite(this.font.getTexture().getInternalTexture(),
						(int)glyph.getSourceX(), (int)glyph.getSourceY(), (int)glyph.getWidth(), (int)glyph.getHeight());
			this.sprites.add(sprite);
		}
		this.positionGlyphs();
	}
	
	private void positionGlyphs()
	{
		float glyphX = 0;
		float glyphY = 0;
		for (com.badlogic.gdx.graphics.g2d.Sprite sprite : this.sprites)
		{
			sprite.setScale(this.scale);
			sprite.setOrigin(-glyphX * this.scale, -glyphY * this.scale);
			glyphX += sprite.getWidth();
		}
	}
	
	public void setTextSize(float size)
	{
		this.scale = size / this.font.getNativeSize();
		
		this.positionGlyphs();
	}
	
	@Override
	void draw(SpriteBatch batch)
	{
		for (com.badlogic.gdx.graphics.g2d.Sprite sprite : this.sprites)
		{
			sprite.setPosition(this.getGameObject().getPositionX(), this.getGameObject().getPositionY());
			sprite.draw(batch);
		}
	}

}
