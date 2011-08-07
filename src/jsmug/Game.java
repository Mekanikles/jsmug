package jsmug;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

public class Game implements ApplicationListener
{
	public String name = "JSmug Game";
	public int width = 800;
	public int height = 600;
	public boolean fullscreen = false;
	
	int fpsCounter = 0;	

	private double time = 0.0f;
	private double nextUpdateTime = 0.0f;
	private double lastRenderTime = 0.0f;
	private double lastFpsUpdate = 0.0f;
	private double logicLength = 1.0f / Engine.LOGICS_PER_SEC;
	
	
	@Override
	public void create()
	{
		this.start();
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
			System.out.print(this.name + ", fps: " + this.fpsCounter + "\n");
			this.fpsCounter = 0;
			this.lastFpsUpdate = this.time;
		}
		
		if(this.time >= this.nextUpdateTime + this.logicLength)
		{
			this.nextUpdateTime += this.logicLength;
			
			this.update();
		}
		
		
		Renderer.getInstance().render();
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
	
	public void start()
	{
		System.out.print("Game Start\n");
	}

	public void update()
	{	
	}
}
