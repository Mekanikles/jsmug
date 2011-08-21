package jsmug;

public class Time
{
	private static Time instance;
	public static Time getInstance() { if (instance == null) instance = new Time(); return instance;}
	
	
	
	private double time;
	private double deltaTime;
	
	private Time() {}
	
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
