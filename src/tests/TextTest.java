package tests;

import jsmug.Core;
import jsmug.Game;
import jsmug.GameObject;
import jsmug.Smug;
import jsmug.graphics.Text;
import jsmug.graphics.Text.Anchor;
import jsmug.resources.Resources;

public class TextTest extends Game
{
	private GameObject[] gos; 
	private float f = 0.0f;
	
	
	public static void main(String[] argv)
	{
		Smug.initialize();
		Smug.runGame(new TextTest());
	}
	
	public TextTest()
	{
		
	}
	
	public void start()
	{
		Smug.resources.setResourcePath("data/");
		this.gos = new GameObject[Anchor.values().length];
	
		for (Anchor i : Anchor.values())
		{
			this.gos[i.ordinal()] = new GameObject(16 + (i.ordinal() % 3) * 200.0f,600 - (50 + (float)(i.ordinal() / 3) * 200 + (i.ordinal() % 3) * 50.0f));
			this.gos[i.ordinal()].addComponent(new Text(Resources.getInstance().getFont("font"), "" + i, 32.0f, i));
		}	
	}
	
	public void update()
	{
		for (GameObject go : this.gos)
		{
			go.setRotation((float)Math.sin(f) * 7.5f);
			go.setScale(0.9f + (float)Math.sin(f) * 0.1f);
		}
		
		this.f += Smug.time.getDeltaTime();
	}
}
