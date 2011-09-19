package jsmug.input;

import jsmug.graphics.Graphics;
import jsmug.utils.Vector;

import com.badlogic.gdx.Gdx;
import jsmug.Smug;

public class Input
{
	public class Keys
	{
		public static final int NUM1 = com.badlogic.gdx.Input.Keys.NUM_1;
		public static final int NUM2 = com.badlogic.gdx.Input.Keys.NUM_2;
		public static final int NUM3 = com.badlogic.gdx.Input.Keys.NUM_3;
		public static final int NUM4 = com.badlogic.gdx.Input.Keys.NUM_4;
		public static final int NUM5 = com.badlogic.gdx.Input.Keys.NUM_5;
		public static final int NUM6 = com.badlogic.gdx.Input.Keys.NUM_6;
		public static final int NUM7 = com.badlogic.gdx.Input.Keys.NUM_7;
		public static final int NUM8 = com.badlogic.gdx.Input.Keys.NUM_8;
		public static final int NUM9 = com.badlogic.gdx.Input.Keys.NUM_9;
		public static final int NUM0 = com.badlogic.gdx.Input.Keys.NUM_0;
		
		public static final int A = com.badlogic.gdx.Input.Keys.A;
		public static final int B = com.badlogic.gdx.Input.Keys.B;
		public static final int C = com.badlogic.gdx.Input.Keys.C;
		public static final int D = com.badlogic.gdx.Input.Keys.D;
		public static final int E = com.badlogic.gdx.Input.Keys.E;
		public static final int F = com.badlogic.gdx.Input.Keys.F;
		public static final int G = com.badlogic.gdx.Input.Keys.G;
		public static final int H = com.badlogic.gdx.Input.Keys.H;
		public static final int I = com.badlogic.gdx.Input.Keys.I;
		public static final int J = com.badlogic.gdx.Input.Keys.J;
		public static final int K = com.badlogic.gdx.Input.Keys.K;
		public static final int L = com.badlogic.gdx.Input.Keys.L;
		public static final int M = com.badlogic.gdx.Input.Keys.M;
		public static final int N = com.badlogic.gdx.Input.Keys.N;
		public static final int O = com.badlogic.gdx.Input.Keys.O;
		public static final int P = com.badlogic.gdx.Input.Keys.P;
		public static final int Q = com.badlogic.gdx.Input.Keys.Q;
		public static final int R = com.badlogic.gdx.Input.Keys.R;
		public static final int S = com.badlogic.gdx.Input.Keys.S;		
		public static final int T = com.badlogic.gdx.Input.Keys.T;
		public static final int U = com.badlogic.gdx.Input.Keys.U;
		public static final int V = com.badlogic.gdx.Input.Keys.V;
		public static final int X = com.badlogic.gdx.Input.Keys.X;
		public static final int Y = com.badlogic.gdx.Input.Keys.Y;
		public static final int Z = com.badlogic.gdx.Input.Keys.Z;
		public static final int LEFT = com.badlogic.gdx.Input.Keys.LEFT;
		public static final int RIGHT = com.badlogic.gdx.Input.Keys.RIGHT;
		public static final int DOWN = com.badlogic.gdx.Input.Keys.DOWN;
		public static final int UP = com.badlogic.gdx.Input.Keys.UP;
		public static final int CTRL = com.badlogic.gdx.Input.Keys.CONTROL_LEFT;
		public static final int ALT = com.badlogic.gdx.Input.Keys.ALT_LEFT;
		public static final int ALT_GR = com.badlogic.gdx.Input.Keys.ALT_RIGHT;
		public static final int SPACE = com.badlogic.gdx.Input.Keys.SPACE;
		
	}
	
	
	
	
	private InputHandler inputHandler;
	
	public Input()
	{
		this.inputHandler = new InputHandler();
	}

	public void init()
	{
		Gdx.input.setInputProcessor(this.inputHandler);
	}
	
	public boolean getKey(int key)
	{
		return this.inputHandler.getKey(key);
	}
	
	public Vector getMousePosition()
	{
		return new Vector(this.inputHandler.getMouseX(), Smug.graphics.getScreenHeight() - this.inputHandler.getMouseY());
	}
	
	public float getMousePositionX()
	{
		return this.inputHandler.getMouseX();
	}
	
	public float getMousePositionY()
	{
		return Smug.graphics.getScreenHeight() - this.inputHandler.getMouseY();
	}
}
