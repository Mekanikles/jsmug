package jsmug.resources;

import java.util.HashMap;
import java.util.Map;

import jsmug.graphics.BitmapFont;
import jsmug.graphics.Texture;

public class Resources
{
	private static Resources instance;
	public static Resources getInstance() { if (instance == null) instance = new Resources(); return instance;}
	
	private Map<String, Texture> textures = new HashMap<String, Texture>();
	private Map<String, BitmapFont> fonts = new HashMap<String, BitmapFont>();
	
	String dataPath;
	
	String texturePath;
	String textureFileEnding;
	
	String fontPath;
	String fontFileEnding;
	
	private Resources()
	{
		this.dataPath = "data/";
		this.texturePath = "";
		this.textureFileEnding = ".png";
		
		this.fontPath = "";
		this.fontFileEnding = ".png";
	}
	
	public void setResourcePath(String path)
	{
		this.dataPath = path;
	}
	
	public Texture getTexture(String name)
	{
		if (this.textures.containsKey(name))
		{
			return this.textures.get(name);
		}
		else
		{
			Texture texture = new Texture(this.dataPath + this.texturePath + name + textureFileEnding);
			this.textures.put(name, texture);
			return texture;
		}
	}
	
	public BitmapFont getFont(String name)
	{
		if (this.fonts.containsKey(name))
		{
			return this.fonts.get(name);
		}
		else
		{
			BitmapFont font = new BitmapFont(this.dataPath + this.fontPath + name + fontFileEnding);
			this.fonts.put(name, font);
			return font;
		}
	}
}
