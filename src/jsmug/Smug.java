package jsmug;

import jsmug.audio.Audio;
import jsmug.audio.OpenALAudio;
import jsmug.graphics.Graphics;
import jsmug.input.Input;
import jsmug.physics.Physics;
import jsmug.resources.Resources;

public class Smug
{
	public static Core core;
	public static Graphics graphics;
	public static Audio audio;
	public static Resources resources;
	public static Input input;
	public static Physics physics;
	public static Time time;

	private static boolean initialized = false;
	
	public static void initialize()
	{
		if (initialized)
			return;
		
		core = Core.getInstance();
		graphics = Graphics.getInstance();
		resources = Resources.getInstance();
		input = Input.getInstance();
		physics = Physics.getInstance();
		time = Time.getInstance();
                audio = new OpenALAudio();
		
		initialized = true;
	}
	
	
	public static void runGame(Game game)
	{
		if (!initialized)
			return;
		core.runGame(game);
	}
}
