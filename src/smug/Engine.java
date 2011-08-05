package smug;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class Engine implements ApplicationListener
{
	Game game;
	
	static final int LOGICS_PER_SEC = 60;
	
	int fpsCounter = 0;	

	double time = 0.0f;
	double nextUpdateTime = 0.0f;
	double lastRenderTime = 0.0f;
	double lastFpsUpdate = 0.0f;
	double logicLength = 1.0f / LOGICS_PER_SEC;
	
	public Engine(Game game)
	{
		this.game = game;
		new LwjglApplication(this, game.name, game.width, game.height, game.fullscreen);			
	}

	@Override
	public void create()
	{
		this.game.start();
	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render()
	{
		this.time += Gdx.graphics.getDeltaTime(); 

		if(this.time - lastFpsUpdate >= 1.0f)
		{
			System.out.print(this.game.name + ", fps: " + this.fpsCounter + "\n");
			this.fpsCounter = 0;
			this.lastFpsUpdate = this.time;
		}
		
		if(this.time >= this.nextUpdateTime + this.logicLength)
		{
			this.nextUpdateTime += this.logicLength;
			
			this.game.update();
		}
		
		this.lastRenderTime = this.time;
		this.fpsCounter++;	
	}

	@Override
	public void resize(int arg0, int arg1)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume()
	{
		// TODO Auto-generated method stub
		
	}
		
	
}
