package jsmug;

import java.util.HashMap;
import java.util.Map;

public class Resources
{
	private static Resources instance;
	public static Resources getInstance() { if (instance == null) instance = new Resources(); return instance;}
	
	private Map<String, TextureResource> textures = new HashMap<String, TextureResource>();
	private Map<String, FontResource> fonts = new HashMap<String, FontResource>();
	
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
	
	public TextureResource getTexture(String name)
	{
		if (this.textures.containsKey(name))
		{
			return this.textures.get(name);
		}
		else
		{
			TextureResource texture = new TextureResource(this.dataPath + this.texturePath + name + textureFileEnding);
			this.textures.put(name, texture);
			return texture;
		}
	}
	
	public FontResource getFont(String name)
	{
		if (this.fonts.containsKey(name))
		{
			return this.fonts.get(name);
		}
		else
		{
			FontResource font = new FontResource(this.dataPath + this.fontPath + name + fontFileEnding);
			this.fonts.put(name, font);
			return font;
		}
	}
}
