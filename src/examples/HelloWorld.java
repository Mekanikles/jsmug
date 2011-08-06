package examples;

import smug.*;


public class HelloWorld extends Game
{	
	public static void main(String[] argv)
	{
		Engine.getInstance().runGame(new HelloWorld());
	}
	
	public HelloWorld()
	{
		this.name = "Hello World";
		this.width = 800;
		this.height = 600;
		this.fullscreen = false;
	}
	
	private GameObject paddle1;
	private GameObject paddle2;
	
	private GameObject ball;
	
	
	public void start()
	{
		this.paddle1 = new GameObject();
		this.paddle1.addComponent(new Drawable(new Vector(32, 96)));
		this.paddle2 = new GameObject();
		this.paddle2.addComponent(new Drawable(new Vector(32, 96)));
		this.ball = new GameObject();
		this.ball.addComponent(new Drawable(new Vector(32, 32)));
	}
	
	
	public void update()
	{
		
	}
}
