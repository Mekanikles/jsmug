package jsmug.physics;

import java.util.LinkedList;

import jsmug.Component;
import jsmug.GameObject;
import jsmug.utils.Rectangle;

public class Physics
{
	private static Physics instance;
	public static Physics getInstance() { if (instance == null) instance = new Physics(); return instance;}
	
	private Physics()
	{
	}
	
	


	
	public boolean intersects(BoxCollider rc1, BoxCollider rc2)
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
		BoxCollider c1 = (BoxCollider)go1.getCollider();
		BoxCollider c2 = (BoxCollider)go2.getCollider();
				
		if (c1 != null && c2 != null)
		{
			if (this.intersects(c1, c2))
				return true;
		}
		
		return false;
	}
	
	
}
