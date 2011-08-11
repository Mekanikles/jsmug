package jsmug;

import jsmug.graphics.Graphics;
import jsmug.input.Input;
import jsmug.physics.Physics;
import jsmug.resources.Resources;

public class Smug
{
	public static Core core;
	public static Graphics graphics;
	//public static Audio audio;
	public static Resources resources;
	public static Input input;
	public static Physics physics;
	
	public static void runGame(Game game)
	{
		core = Core.getInstance();
		graphics = Graphics.getInstance();
		resources = Resources.getInstance();
		input = Input.getInstance();
		physics = Physics.getInstance();
		
		core.runGame(game);
	}
	
	
}
