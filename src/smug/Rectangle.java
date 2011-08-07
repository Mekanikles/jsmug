package smug;

public class Rectangle
{
	private Vector[] v = new Vector[2]; 
	
	public Rectangle(Vector v0, Vector v1)
	{ this.v[0] = new Vector(v0); this.v[1] = new Vector(v1); }
	
	public Rectangle(Vector v0, float width, float height)
	{ this.v[0] = new Vector(v0); this.v[1] = new Vector(v0.getX() + width, v0.getY() + height); }
	
	
	public Vector getV0() {return this.v[0];}
	public Vector getV1() {return this.v[1];}
	public void setV0(Vector v0) {this.v[0] = v0;}
	public void setV1(Vector v1) {this.v[1] = v1;}
	
	public Rectangle translate(Vector v)
	{
		this.v[0].addEquals(v);
		return this;
	}
	
	public Rectangle add(Vector v)
	{
		return new Rectangle(this.getV0().add(v), this.getV1().add(v));
	}
	
}
