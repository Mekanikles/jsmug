package smug;

public class Vector
{
	private float[] v = new float[2];
	
	public static Vector zero = new Vector(0, 0);
	
	public Vector(float x, float y)
	{
		v[0] = x; v[1] = y;
	}
	
	float getX() {return v[0];}	
	float getY() {return v[1];}
	
	
}
