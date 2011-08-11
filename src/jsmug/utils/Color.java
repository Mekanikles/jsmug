package jsmug.utils;

public class Color
{
	public static Color white = new Color(1,1,1,1);
	public static Color black = new Color(0,0,0,1);
	
	private float[] v = new float[4];
	
	public Color(float r, float g, float b, float a)
	{v[0] = r; v[1] = g; v[2] = b; v[3] = a;}
	
	public Color(Color v)
	{this(v.v[0], v.v[1], v.v[2], v.v[3]);}
	

	public float getR() {return this.v[0];}
	public float getG() {return this.v[1];}
	public float getB() {return this.v[2];}
	public float getA() {return this.v[3];}
	
	public void setR(float r) {this.v[0] = r;}
	public void setG(float g) {this.v[1] = g;}
	public void setB(float b) {this.v[2] = b;}
	public void setA(float a) {this.v[3] = a;}
	
}
