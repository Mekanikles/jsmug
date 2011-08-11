package jsmug.graphics;

public class Graphics
{
	private static Graphics instance;
	public static Graphics getInstance() { if (instance == null) instance = new Graphics(); return instance;}

	int screenWidth;
	public int getScreenWidth() { return this.screenWidth;}
	int screenHeight;
	public int getScreenHeight() { return this.screenHeight;}
	
	
	private Renderer renderer;
	
	private Graphics()
	{
	}
	
	public void init(int screenWidth, int screenHeight)
	{
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		
		this.renderer = new Renderer(screenWidth, screenHeight);
	}
	
	public void render()
	{
		this.renderer.render();
	}
	
}
