package jsmug;

import java.util.HashMap;

import jsmug.graphics.Drawable;
import jsmug.physics.Collider;
import jsmug.utils.Vector;

public class GameObject
{
	Vector position;

	private Drawable drawable;
	private Collider collider;

	private HashMap<Class, Script> scripts = new HashMap<Class, Script>();
	
	
	public GameObject()
	{
		this.position = Vector.zero;
		Core.getInstance().addGameObject(this);		
	}
	
	public GameObject(Vector position)
	{
		this.position = position;
		Core.getInstance().addGameObject(this);		
	}
	
	public void setPosition(Vector position) { this.position = position; }
	public void setPositionX(float x){ this.position.setX(x); }	
	public void setPositionY(float y){ this.position.setY(y); }	
	public Vector getPosition() { return this.position; }
	public float getPositionX() { return this.position.getX(); }	
	public float getPositionY() { return this.position.getY(); }	
	
	public void move(Vector movement)
	{
		this.position.addEquals(movement);
	}
	
	public Script getScript(Class c)
	{
		if (this.scripts.containsKey(c))
			return this.scripts.get(c);
		return null;
	}
	
	void removeComponent(Drawable drawable) { this.drawable = null;}
	void removeComponent(Collider collider) { this.drawable = null;}
	
	void removeComponent(Script script)
	{
		while (this.scripts.values().remove(script));
	}
	
	public Script addComponent(Script script)
	{
		Class c = script.getClass();
		System.out.print("c: " + c + "\n");
		for (Class hc :  this.scripts.keySet())
		{
			System.out.print("    hc: " + hc + "\n");
			if (hc.isAssignableFrom(c))
			{
				System.out.print("Error: script of type: " + hc + " is already added.");
				return null;
			}
		}
		
		while(true)
		{
			System.out.print("    while c: " + c + "\n");
			if (c.equals(Script.class))
				break;
				
			this.scripts.put(c, script);
			c = c.getSuperclass();
		}
		
		return script;
	}
	

	public Drawable addComponent(Drawable drawable)
	{
		if (this.drawable != null)
		{
			System.out.print("Error: remove old drawable before adding: " + this.drawable);
			return null;
		}
		this.drawable = drawable;
		((Component)drawable).attachTo(this);
		return drawable;
	}
	
	public Collider addComponent(Collider collider)
	{
		if (this.drawable != null)
			System.out.print("Error: remove old collider before adding: " + this.collider);
		this.collider = collider;
		((Component)collider).attachTo(this);
		return collider;
	}
	
	
	public Drawable getDrawable() { return this.drawable; }
	public Collider getCollider() { return this.collider; }
	
	
}
