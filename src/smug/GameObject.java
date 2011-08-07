package smug;

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
			
	public Vector getPosition()
	{
		return this.position;
	}
	
	public void move(Vector movement)
	{
		this.position.addEquals(movement);
	}
	
	public List<Component> getComponents()
	{
		return this.components;
	}
	
	public void addComponent(Component component)
	{
		component.setGameObject(this);
		this.components.add(component);
		component.onAttach();
	}

	public void removeComponent(Component component)
	{
		this.components.remove(component);
	}
	
	
}
