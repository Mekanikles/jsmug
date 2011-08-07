package jsmug;

import com.badlogic.gdx.InputProcessor;


public class InputHandler implements InputProcessor 
{
	private boolean[] keyStates = new boolean[300];
	private int mouseX;
	private int mouseY;
	
	public boolean getKey(int keycode)
	{
		return this.keyStates[keycode];
	}
	
	public int getMouseX() {return this.mouseX;}
	public int getMouseY() {return this.mouseY;}
	
	@Override
	public boolean keyDown(int keycode) 
	{
		this.keyStates[keycode] = true;
		return true;
	}

	@Override
	public boolean keyUp(int keycode) 
	{
		this.keyStates[keycode] = false;
		return true;
	}

	@Override
	public boolean keyTyped(char character) 
	{
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) 
	{
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button)
	{
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) 
	{
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) 
	{
		this.mouseX = x;
		this.mouseY = y;
		return true;
	}

	@Override
	public boolean scrolled(int amount) 
	{
		return false;
	}
}
