package jsmug;

import java.util.List;
import java.util.LinkedList;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class TextDrawable extends Component
{
	private FontResource font;
	private char[] text;
	
	private List<Sprite> sprites = new LinkedList<Sprite>();
	private float scale = 1.0f;
	
	public TextDrawable(FontResource font, String text)
	{
		this.font = font;
		this.setText(text);
	}
	
	public void setText(String text)
	{
		this.sprites.clear();
		this.text = text.toCharArray();
		
		FontResource.Glyph glyph;
		Sprite sprite;

		for (char c : this.text)
		{
			glyph = this.font.getGlyph(c);
			sprite = new Sprite(this.font.getTexture(), (int)glyph.getSourceX(), (int)glyph.getSourceY(), (int)glyph.getWidth(), (int)glyph.getHeight());
			this.sprites.add(sprite);
		}
		this.positionGlyphs();
	}
	
	private void positionGlyphs()
	{
		float glyphX = 0;
		float glyphY = 0;
		for (Sprite sprite : this.sprites)
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
	
	public List<Sprite> getSprites()
	{
		return this.sprites;
	}	

}
