package jsmug;

import jsmug.audio.Audio;
import jsmug.audio.Audio;
import jsmug.graphics.Camera;
import jsmug.graphics.Graphics;
import jsmug.input.Input;
import jsmug.physics.Physics;
import jsmug.resources.Resources;
import jsmug.utils.Vector;

public class Smug
{
	public static SmugApplication app;
	public static Graphics graphics;
	public static Audio audio;
	public static Resources resources;
	public static Input input;
	public static Physics physics;
	public static Time time;
        public static Core core;

	private static boolean initialized = false;
	
	public static void initialize()
	{
		if (initialized)
			return;
		
		//app = SmugApplication.getInstance();
		graphics = new Graphics();
		resources = new Resources();
		input = new Input();
		physics = new Physics();
		time = new Time();
                audio = new Audio();
                core = new Core();
		
		initialized = true;
	}
	
        public static void destroy() {
            Smug.audio.finish();
        }
	
	public static void runGame(SmugApplication app)
	{
		if (!initialized)
			return;
		
                Smug.app = app;
                Smug.core.run(app.name, app.width, app.height, app.fullscreen);                
	}
        
        public static Scene newScene() {
            return new Scene();
        }
        
        public static Camera newCamera() {
            return new Camera();
        }
        
        public static GameObject newGameObject() {
            return new GameObject();
	}
	
	public static GameObject newGameObject(Vector position, float rotation, Vector scale) {
            return new GameObject(position, rotation, scale);
	}
	
	public static GameObject newGameObject(float posX, float posY) {
            return new GameObject(posX, posY);
        }
        
	public static GameObject newGameObject(Vector pos) {
            return new GameObject(pos);
        }
}
