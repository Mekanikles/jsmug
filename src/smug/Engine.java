package smug;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL41;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Engine
{
	static final int LOGICS_PER_SEC = 60;
	
	private static Engine instance;
	public static Engine getInstance() { if (instance == null) instance = new Engine(); return instance;}
	
	private Scene scene;
	private Game game;

	private Engine()
	{
		this.scene = new Scene();
	}
	
	public void runGame(Game game)
	{
		this.game = game;
		new LwjglApplication(this.game, game.name, game.width, game.height, game.fullscreen);		

		Input.getInstance().init();
		Renderer.getInstance().init(game.width, game.height);	
	}

	public Scene getScene()
	{
		return this.scene;
	}

}
