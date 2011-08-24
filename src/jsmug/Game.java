package jsmug;

import jsmug.graphics.Graphics;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

public class Game implements ApplicationListener
{
	public String name = "JSmug Game";
	public int width = 800;
	public int height = 600;
	public boolean fullscreen = false;
	
	int fpsCounter = 0;	

	private float nextUpdateTime = 0.0f;
	private float lastRenderTime = 0.0f;
	private float lastFpsUpdate = 0.0f;
	private float logicLength = 1.0f / Core.LOGICS_PER_SEC;
	
	
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
		Time.getInstance().update(Gdx.graphics.getDeltaTime()); 
		float time = Time.getInstance().getTime();
		
		if(time - lastFpsUpdate >= 1.0f)
		{
			//System.out.print(this.name + ", fps: " + this.fpsCounter + "\n");
			this.fpsCounter = 0;
			this.lastFpsUpdate = time;
		}
		
		if(time >= this.nextUpdateTime + this.logicLength)
		{
			this.nextUpdateTime += this.logicLength;
			
			this.update();
		}
		
		
		Graphics.getInstance().render();
                Smug.audio.update(Gdx.graphics.getDeltaTime(), new double[]{1.0});
		this.lastRenderTime = time;
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
