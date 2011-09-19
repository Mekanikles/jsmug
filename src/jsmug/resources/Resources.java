package jsmug.resources;

import java.util.HashMap;
import java.util.Map;

import jsmug.Smug;
import jsmug.audio.Sound;
import jsmug.graphics.BitmapFont;
import jsmug.graphics.Texture;

public class Resources
{
	private Map<String, Texture> textures = new HashMap<String, Texture>();
	private Map<String, BitmapFont> fonts = new HashMap<String, BitmapFont>();
	private Map<String, Sound> sounds = new HashMap<String, Sound>();
	
	String dataPath;
	
	String texturePath;
	String textureFileEnding;
	
	String fontPath;
	String fontFileEnding;
	
	public Resources()
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
        
        public Sound getSound(String name) {
		if (this.sounds.containsKey(name))
		{
			return this.sounds.get(name);
		}
		else
		{
			Sound sound = Smug.audio.newSound(name, 0);
			this.sounds.put(name, sound);
			return sound;
		}
        }
}
