package jsmug;

import jsmug.graphics.Graphics;
import jsmug.input.Input;

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

public class Core
{
	static final int LOGICS_PER_SEC = 60;
	
	private static Core instance;
	public static Core getInstance() { if (instance == null) instance = new Core(); return instance;}
	
	private Scene scene;
	private Game game;

	private Core()
	{
		this.scene = new Scene();
	}
	
	public void runGame(Game game)
	{
		this.game = game;
		new LwjglApplication(this.game, game.name, game.width, game.height, game.fullscreen);		

		Input.getInstance().init();
		Graphics.getInstance().init(game.width, game.height);
                Smug.audio.init();
	}

	public Scene getScene()
	{
		return this.scene;
	}
	
	public void addGameObject(GameObject gameObject)
	{
		this.scene.addGameObject(gameObject);
	}

}
