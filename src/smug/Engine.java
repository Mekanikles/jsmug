package smug;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Engine
{
	Game game;
	
	static final int LOGICS_PER_SEC = 60;
	
	
	private static Engine instance;
	public static Engine getInstance() { if (instance == null) instance = new Engine(); return instance;}
	
	private Scene scene;
	
	private SpriteBatch batch;
	
	public Engine()
	{
		this.scene = new Scene();
	}
	
	public void runGame(Game game)
	{
		this.game = game;
		new LwjglApplication(this.game, game.name, game.width, game.height, game.fullscreen);		
		
		batch.getProjectionMatrix().setToOrtho2D(0, 0, game.width, game.height);
	}

	public Scene getScene()
	{
		return this.scene;
	}
	
	
	public void render()
	{
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Gdx.gl11.glColor4f(0,1,0,1);
		batch.begin();
		batch.enableBlending();
		
		for (GameObject gameObject : scene.getGameObjects())
		{
			for (Component component : gameObject.getComponents())
			{
				if (component instanceof Drawable)
				{
					((Drawable)component).getSprite().draw(batch);
				}
			}
		}
	
		batch.end();	
	}	
}
