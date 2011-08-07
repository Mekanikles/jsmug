package smug;

public class Vector
{
	private float[] v = new float[2];
	
	public static Vector zero = new Vector(0, 0);
	
	public Vector(float x, float y)
	{v[0] = x; v[1] = y;}
	
	public Vector(double x, double y)
	{this((float)x, (float)y);}
	
	public Vector(Vector v)
	{this(v.v[0], v.v[1]);}
	
	public float getX() {return v[0];}	
	public float getY() {return v[1];}
	public void setX(float x) {v[0] = x;}	
	public void setY(float y) {v[1] = y;}
	
	
	public Vector add(Vector v)
	{
		return new Vector(this.v[0] + v.v[0], this.v[1] + v.v[1]);
	}
	
	public Vector subtract(Vector v)
	{
		return new Vector(this.v[0] - v.v[0], this.v[1] - v.v[1]);
	}
	
	public Vector addEquals(Vector v)
	{
		this.v[0] += v.v[0]; this.v[1] += v.v[1]; return this;
	}
	
	public Vector subtractEquals(Vector v)
	{
		this.v[0] -= v.v[0]; this.v[1] -= v.v[1]; return this;
	}
	
	
	public Vector flipX()
	{
		this.v[0] = -this.v[0]; return this;
	}
	
	public Vector flipY()
	{
		this.v[1] = -this.v[1]; return this;
	}
	
}
