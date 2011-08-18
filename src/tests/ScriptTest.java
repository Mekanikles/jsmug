package tests;

import jsmug.Game;
import jsmug.GameObject;
import jsmug.Script;
import jsmug.Smug;
import examples.Pong;

public class ScriptTest extends Game
{

	public class Script1 extends Script
	{
		public void Update()
		{
			System.out.print("Script1 update");
		}
	}
	
	public class Script2 extends Script
	{
		public void Update()
		{
			System.out.print("Script2 update");
		}
	}	
	
	public class Script3 extends Script2
	{
		public void Update()
		{
			System.out.print("Script3 update");
		}
	}	
	
	public static void main(String[] argv)
	{
		new ScriptTest();
	}
	
	public ScriptTest()
	{
		GameObject go = new GameObject();
		
		go.addComponent(new Script1());
		go.addComponent(new Script2());
		go.addComponent(new Script3());
	
		go.removeComponent(go.getScript(Script1.class));
		
		
		
		
		
	}
	
}
