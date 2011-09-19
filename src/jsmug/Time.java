package jsmug;

public class Time
{
	private double time;
	private double deltaTime;
	
	public void init()
	{
		this.time = 0.0;
		this.deltaTime = 0.0;
	}
	
	public float getTime() {return (float)this.time; }
	public float getDeltaTime() {return (float)this.deltaTime; }
	
	
	public void update(float deltaTime)
	{
		this.time += (double)deltaTime;
		this.deltaTime = (double)deltaTime;

	}
}
