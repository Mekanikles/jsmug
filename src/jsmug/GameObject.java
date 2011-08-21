package jsmug;

import java.util.HashMap;

import jsmug.graphics.Drawable;
import jsmug.physics.Collider;
import jsmug.utils.Vector;

public class GameObject
{
	Vector position = new Vector(0.0f, 0.0f);
	float rotation = 0.0f;
	Vector scale = new Vector(1.0f, 1.0f);

	private Drawable drawable;
	private Collider collider;

	private HashMap<Class, Script> scripts = new HashMap<Class, Script>();
	
	public GameObject()
	{
		this.position = Vector.zero;
		Core.getInstance().addGameObject(this);		
	}
	
	public GameObject(Vector position, float rotation, Vector scale)
	{
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		Core.getInstance().addGameObject(this);		
	}
	
	public GameObject(float posX, float posY) {this(new Vector(posX, posY), 0, new Vector(0, 0));}
	public GameObject(Vector pos) {this(pos, 0, new Vector(0, 0));}
	
	
	public void setPosition(Vector position) { this.position = position; }
	public void setPositionX(float x){ this.position.setX(x); }	
	public void setPositionY(float y){ this.position.setY(y); }	
	public Vector getPosition() { return this.position; }
	public float getPositionX() { return this.position.getX(); }	
	public float getPositionY() { return this.position.getY(); }	
	
	public void setRotation(float angle) { this.rotation = angle; }
	public float getRotation() { return this.rotation; }
	
	public void setScale(Vector scale) {this.scale = scale;}
	public Vector getScale() {return this.scale; }
	public float getScaleX() {return this.scale.getX(); }
	public float getScaleY() {return this.scale.getY(); }
	public void setScale(float scale) {this.setScale(new Vector(scale, scale));}
	
	
	
	public void move(Vector movement)
	{
		this.position.addEquals(movement);
	}

	public void removeComponent(Drawable drawable) { this.drawable = null;}
	public void removeComponent(Collider collider) { this.drawable = null;}
	public void removeComponent(Script script)
	{
		while (this.scripts.values().remove(script));
	}
	
	public void removeComponent(Component component)
	{
		if (component instanceof Script)
			this.removeComponent((Script)component);
		else if (component instanceof Drawable)
			this.removeComponent((Drawable)component);
		else if (component instanceof Collider)
			this.removeComponent((Collider)component);
	}
	
	public Component getComponent(Class c)
	{
		if (!Component.class.isAssignableFrom(c))
			return null;
		
		if (Script.class.isAssignableFrom(c))
			return (Component)this.getScript(c);
		
		if (Drawable.class.isAssignableFrom(c))
			return this.getDrawable();
		
		if (Collider.class.isAssignableFrom(c))
			return this.getCollider();
		
		return null;
	}
	
	
	public Script getScript(Class c)
	{
		if (!Script.class.isAssignableFrom(c))
			return null;
		if (this.scripts.containsKey(c))
			return this.scripts.get(c);
		return null;
	}
	
	public Drawable getDrawable() { return this.drawable; }
	public Collider getCollider() { return this.collider; }
	
	public Script addComponent(Script script)
	{
		Class c = script.getClass();
		for (Class hc :  this.scripts.keySet())
		{
			System.out.print("    hc: " + hc + "\n");
			if (hc.isAssignableFrom(c))
			{
				System.out.print("Error: script of type: " + hc + " is already added.\n");
				return null;
			}
		}
		
		while(true)
		{
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
			System.out.print("Error: remove old drawable before adding: " + this.drawable + "\n");
			return null;
		}
		this.drawable = drawable;
		((Component)drawable).attachTo(this);
		return drawable;
	}
	
	public Collider addComponent(Collider collider)
	{
		if (this.drawable != null)
			System.out.print("Error: remove old collider before adding: " + this.collider + "\n");
		this.collider = collider;
		((Component)collider).attachTo(this);
		return collider;
	}
	
	
	
}
