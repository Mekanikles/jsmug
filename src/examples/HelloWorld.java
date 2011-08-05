package examples;

import smug.*;


public class HelloWorld extends Game
{	
	public static void main(String[] argv)
	{
		Engine engine = new Engine(new HelloWorld());
	}
	
	public HelloWorld()
	{
		this.name = "Hello World";
		this.width = 800;
		this.height = 600;
		this.fullscreen = false;
	
	}
	
	public void start()
	{
			
	}
	
	public void update()
	{
	
	}
}
