package jsmug.graphics;

import jsmug.utils.Rectangle;

public class BitmapFont
{
	public class Glyph
	{
		private Rectangle sourceRect;
		
		public Glyph(float x, float y, float width, float height)
		{
			this.sourceRect = new Rectangle(x, y, width, height);
		}
		
		public float getSourceX() { return this.sourceRect.getV0().getX(); }
		public float getSourceY() { return this.sourceRect.getV0().getY(); }
		public float getWidth() { return this.sourceRect.getWidth(); }
		public float getHeight() { return this.sourceRect.getHeight(); }	
	}
	
	private Texture texture;
	static final int glyphCount = 128;
	private Glyph[] glyphs = new Glyph[glyphCount];
	private float nativeSize;
	
	public BitmapFont(String path)
	{
		texture = new Texture(path);
		
		float gw = texture.getWidth() / 16.0f;
		float gh = texture.getHeight() / 8.0f;
		this.nativeSize = gh;
		for (int i = 0; i < glyphCount; i++)
		{
			this.glyphs[i] = new Glyph((i % 16) * gw, (i / 16) * gh, gw, gh);
		}
	}

	public Texture getTexture()
	{
		return this.texture;
	}
	
	public Glyph getGlyph(char c)
	{
		return this.glyphs[(int)c - 32];
	}
	
	public float getNativeSize()
	{
		return this.nativeSize;
	}
	
}
