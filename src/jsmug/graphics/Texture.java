package jsmug.graphics;

import jsmug.utils.Color;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;

public class Texture
{
	private com.badlogic.gdx.graphics.Texture texture;
	private static com.badlogic.gdx.graphics.Texture dummyTexture;
	private static com.badlogic.gdx.graphics.Texture nullTexture;

	public Texture(String path)
	{
		try
		{
			this.texture = new com.badlogic.gdx.graphics.Texture(Gdx.files.internal(path));
		}
		catch (com.badlogic.gdx.utils.GdxRuntimeException e)
		{
			this.texture = getDummyTexture();
		}
	}
	
	public Texture(int width, int height, Color color)
	{
		this.texture = createBlankTexture(width, height, color);
	}
	
	public Texture()
	{
		this.texture = getNullTexture();
	}
	
	public int getWidth() { return this.texture.getWidth(); }
	public int getHeight() { return this.texture.getHeight(); }
	
	private static com.badlogic.gdx.graphics.Texture createBlankTexture(int width, int height, Color color)
	{
		com.badlogic.gdx.graphics.Texture texture;
		Gdx2DPixmap pixmap = new Gdx2DPixmap(width, height, Gdx2DPixmap.GDX2D_FORMAT_RGBA8888);
		pixmap.clear(com.badlogic.gdx.graphics.Color.rgba8888(color.getR(), color.getG(), color.getB(), color.getA()));
		texture = new com.badlogic.gdx.graphics.Texture(pixmap.getWidth(), pixmap.getHeight(), Format.RGBA8888);
		texture.bind();
        Gdx.gl.glTexImage2D(GL10.GL_TEXTURE_2D, 0, pixmap.getGLInternalFormat(), pixmap.getWidth(), 
        		pixmap.getHeight(), 0, pixmap.getGLFormat(), pixmap.getGLType(), pixmap.getPixels());
        Gdx.gl.glTexParameterf( GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT ); 
        Gdx.gl.glTexParameterf( GL10.GL_TEXTURE_2D,  GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT );	
        return texture;
	}
	
	private static com.badlogic.gdx.graphics.Texture getNullTexture()
	{
		if (nullTexture == null)
		{
			nullTexture = createBlankTexture(16, 16, Color.white); 
		} 
		
        return nullTexture;
	}
	
	private static com.badlogic.gdx.graphics.Texture getDummyTexture()
	{
		if (dummyTexture == null)
		{
			Gdx2DPixmap pixmap = new Gdx2DPixmap(64, 64, Gdx2DPixmap.GDX2D_FORMAT_RGBA8888);
			
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					pixmap.fillRect(i * 8 + 0, j * 8 + 0, 4, 4, com.badlogic.gdx.graphics.Color.rgba8888(1, 0, 1, 0.7f));
					pixmap.fillRect(i * 8 + 4, j * 8 + 0, 4, 4, com.badlogic.gdx.graphics.Color.rgba8888(0.5f, 0.5f, 0.5f, 1.0f));
					pixmap.fillRect(i * 8 + 4, j * 8 + 4, 4, 4, com.badlogic.gdx.graphics.Color.rgba8888(1, 0, 1, 0.7f));
					pixmap.fillRect(i * 8 + 0, j * 8 + 4, 4, 4, com.badlogic.gdx.graphics.Color.rgba8888(0.5f, 0.5f, 0.5f, 1.0f));
				}
			}
			
			dummyTexture = new com.badlogic.gdx.graphics.Texture(pixmap.getWidth(), pixmap.getHeight(), Format.RGBA8888);
			dummyTexture.bind();
            Gdx.gl.glTexImage2D(GL10.GL_TEXTURE_2D, 0, pixmap.getGLInternalFormat(), pixmap.getWidth(), 
            		pixmap.getHeight(), 0, pixmap.getGLFormat(), pixmap.getGLType(), pixmap.getPixels());
            Gdx.gl.glTexParameterf( GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT ); 
            Gdx.gl.glTexParameterf( GL10.GL_TEXTURE_2D,  GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT );
		} 
		
        return dummyTexture;
	}

	com.badlogic.gdx.graphics.Texture getInternalTexture()
	{
		return this.texture;
	}
}
