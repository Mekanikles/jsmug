package jsmug.graphics;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import jsmug.Smug;
import jsmug.utils.Rectangle;

public class Text extends Drawable
{
	public enum Anchor
	{
		TOPLEFT, 
		TOPCENTER, 
		TOPRIGHT, 
		MIDDLELEFT, 
		MIDDLECENTER, 
		MIDDLERIGHT, 
		BOTTOMLEFT, 
		BOTTOMCENTER,
		BOTTOMRIGHT
	}
	
	private BitmapFont font;
	private char[] text;
	
	private com.badlogic.gdx.graphics.g2d.Sprite[] sprites;
	private float scale = 1.0f;
	
	private float offSetX;
	private float offSetY;
	private float width;
	private float height;
	private float kerning = 2;
	private Anchor anchor;
	
	public Text(BitmapFont font, String text, float size, Anchor anchor)
	{
		this.font = font;
		this.updateScale(size);
		this.anchor = anchor;
		this.setText(text);
	}
	public Text(BitmapFont font, String text, Anchor anchor)
	{
		this.font = font;
		this.anchor = anchor;
		this.setText(text);
	}
	
	public Text(BitmapFont font, String text, float size)
	{
		this(font, text, size, Anchor.BOTTOMLEFT);
	}
	
	public Text(BitmapFont font, String text)
	{
		this(font, text, Anchor.BOTTOMLEFT);
	}
	
	public void setAnchor(Anchor anchor)
	{
		this.anchor = anchor;
		this.updateOffset();
	}
	
	public void setText(String text)
	{
		this.text = text.toCharArray();
		
		this.loadSprites();
		this.positionGlyphs();
		this.updateOffset();
	}
	
	private void loadSprites()
	{
		this.sprites = new com.badlogic.gdx.graphics.g2d.Sprite[this.text.length];
		
		BitmapFont.Glyph glyph;
		com.badlogic.gdx.graphics.g2d.Sprite sprite;

		for (int i = 0; i < this.text.length; i++)
		{
			if (this.text[i] == '\n' || this.text[i] == ' ')
				continue;
			
			glyph = this.font.getGlyph(this.text[i]);
			
			sprite =new com.badlogic.gdx.graphics.g2d.Sprite(this.font.getTexture().getInternalTexture(),
						(int)glyph.getSourceX(), (int)glyph.getSourceY(), (int)glyph.getWidth(), (int)glyph.getHeight());
			this.sprites[i] = sprite;
		}		
	}
	
	private void positionGlyphs()
	{
		float glyphX = 0;
		float glyphY = 0;
		for (int i = 0; i < this.text.length; i++)
		{
			if (this.text[i] == '\n')
			{
				glyphY += this.font.getNativeSize() + this.kerning / this.scale;
				glyphX = 0;
				continue;
			}
			
			if (this.text[i] != ' ')
			{
				this.sprites[i].setScale(this.scale);
				this.sprites[i].setOrigin(-glyphX, -glyphY);
			}
			glyphX += this.sprites[i].getWidth() + this.kerning / this.scale;
		}
		
		glyphY += this.font.getNativeSize() + this.kerning / this.scale;
		this.width = glyphX * this.scale;
		this.height = glyphY * this.scale;
	}
	
	private void updateOffset()
	{
		switch (this.anchor)
		{
			case TOPLEFT:
			case TOPCENTER:
			case TOPRIGHT:
				this.offSetY = -this.height;
				break;
			case MIDDLELEFT:
			case MIDDLECENTER:
			case MIDDLERIGHT:
				this.offSetY = -this.height / 2;
				break;
			default:
				this.offSetY = 0;
		}
		switch (this.anchor)
		{
			case TOPRIGHT:
			case MIDDLERIGHT:
			case BOTTOMRIGHT:
				this.offSetX = -this.width;
				break;
			case TOPCENTER:
			case BOTTOMCENTER:
			case MIDDLECENTER:
				this.offSetX = -this.width / 2;
				break;
			default:
				this.offSetX = 0;
		}		
	}
	
	private void updateScale(float size)
	{
		this.scale = size / this.font.getNativeSize();
	}
	
	public void setTextSize(float size)
	{
		this.updateScale(size);
		this.positionGlyphs();
	}
	
	@Override
	void draw(SpriteBatch batch)
	{
		for (com.badlogic.gdx.graphics.g2d.Sprite sprite : this.sprites)
		{
			sprite.setPosition(this.getGameObject().getPositionX() + this.offSetX - sprite.getOriginX(), 
					this.getGameObject().getPositionY() + this.offSetY - sprite.getOriginY());
			sprite.draw(batch);
		}
	}

	public void drawDebug()
	{
		for (com.badlogic.gdx.graphics.g2d.Sprite sprite : this.sprites)
		{
			if (sprite == null)
					continue;
			
			Rectangle rect = new Rectangle(this.getGameObject().getPositionX() + this.offSetX - sprite.getOriginX() * sprite.getScaleX(), 
					this.getGameObject().getPositionY() + this.offSetY - sprite.getOriginY() * sprite.getScaleY(), 
					sprite.getWidth() * sprite.getScaleX(), 
					sprite.getHeight() * sprite.getScaleY());
			GL11.glBegin(GL11.GL_LINE_LOOP);
			{
				GL11.glVertex2f(rect.getV0().getX(), rect.getV0().getY());
				GL11.glVertex2f(rect.getV1().getX(), rect.getV0().getY());
				GL11.glVertex2f(rect.getV1().getX(), rect.getV1().getY());
				GL11.glVertex2f(rect.getV0().getX(), rect.getV1().getY());
			}
			GL11.glEnd();
		}
		
		GL11.glBegin(GL11.GL_LINES);
		{
			GL11.glVertex2f(this.getGameObject().getPositionX() - 4, this.getGameObject().getPositionY() + 4);
			GL11.glVertex2f(this.getGameObject().getPositionX() + 4, this.getGameObject().getPositionY() - 4);
			GL11.glVertex2f(this.getGameObject().getPositionX() + 4, this.getGameObject().getPositionY() + 4);
			GL11.glVertex2f(this.getGameObject().getPositionX() - 4, this.getGameObject().getPositionY() - 4);
		}
		GL11.glEnd();
		
	}
	
}
