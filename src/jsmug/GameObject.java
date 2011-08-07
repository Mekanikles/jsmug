package jsmug;

import java.util.LinkedList;
import java.util.List;

public class GameObject
{
	Vector position;
	
	List<Component> components = new LinkedList<Component>();
	
	public GameObject()
	{
		this.position = Vector.zero;
		Engine.getInstance().getScene().addGameObject(this);		
	}
	
	public GameObject(Vector position)
	{
		this.position = position;
		Engine.getInstance().getScene().addGameObject(this);		
	}
	
	public void setPosition(Vector position)
	{
		this.position = position;
	}
	
	public void setPositionX(float x)
	{
		this.position.setX(x);
	}	
	public void setPositionY(float y)
	{
		this.position.setY(y);
	}	
	
	
	public Vector getPosition()
	{
		return this.position;
	}
	
	public float getPositionX()
	{
		return this.position.getX();
	}	
	public float getPositionY()
	{
		return this.position.getY();
	}	
	
	public void move(Vector movement)
	{
		this.position.addEquals(movement);
	}
	
	public List<Component> getComponents()
	{
		return this.components;
	}
	
	public Component addComponent(Component component)
	{
		component.setGameObject(this);
		this.components.add(component);
		component.onAttach();
		return component;
	}

	public Component removeComponent(Component component)
	{
		this.components.remove(component);
		return component;
	}
	
	
}
