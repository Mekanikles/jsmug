package jsmug;

import java.util.LinkedList;

public class Physics
{
	private static Physics instance;
	public static Physics getInstance() { if (instance == null) instance = new Physics(); return instance;}
	
	private Physics()
	{
	}
	
	


	
	public boolean intersects(RectangleCollider rc1, RectangleCollider rc2)
	{
		Rectangle rect1 = rc1.getRectangle().add(rc1.getGameObject().getPosition());
		Rectangle rect2 = rc2.getRectangle().add(rc2.getGameObject().getPosition());

		if (rect1.getV1().getX() < rect2.getV0().getX())
			return false;
		if (rect1.getV0().getX() > rect2.getV1().getX())
			return false;
		if (rect1.getV1().getY() < rect2.getV0().getY())
			return false;
		if (rect1.getV0().getY() > rect2.getV1().getY())
			return false;
		
		return true;
	}
	
	public boolean collides(GameObject go1, GameObject go2)
	{
		LinkedList<RectangleCollider> colls1 = new LinkedList<RectangleCollider>();
		LinkedList<RectangleCollider> colls2 = new LinkedList<RectangleCollider>();
		
		for (Component component : go1.getComponents())
		{
			if (component instanceof RectangleCollider)
				colls1.add((RectangleCollider)component);  
		}
		for (Component component : go2.getComponents())
		{
			if (component instanceof RectangleCollider)
				colls2.add((RectangleCollider)component);  
		}
		
		for (RectangleCollider rc1 : colls1)
		{
			for (RectangleCollider rc2 : colls2)
			{
				if (this.intersects(rc1, rc2))
					return true;
			}	
		}
		
		return false;
	}
	
	
}
