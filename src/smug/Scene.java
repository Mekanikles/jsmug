package smug;

import java.util.LinkedList;
import java.util.List;

public class Scene
{
	List<GameObject> gameObjects = new LinkedList<GameObject>();
	
	public Scene()
	{
	}
	
	
	public void addGameObject(GameObject gameObject)
	{
		gameObjects.add(gameObject);
	}
	
	public void RemoveGameObject(GameObject gameObject)
	{
		gameObjects.remove(gameObject);
	}
	
	public List<GameObject> getGameObjects()
	{	
		return this.gameObjects;
	}
	
	
}
