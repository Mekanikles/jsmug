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
	
	public void setPosition(Vector position)
	{
		this.position = position;
	}
			
	
	public List<Component> getComponents()
	{
		return this.components;
	}
	
	public void addComponent(Component component)
	{
		this.components.add(component);
	}

	public void removeComponent(Component component)
	{
		this.components.remove(component);
	}
	
	
}
