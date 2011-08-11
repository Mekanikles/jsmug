package jsmug;


public abstract class Component
{
	GameObject gameObject;
	
	public GameObject getGameObject()
	{ return this.gameObject; }
	
	void attachTo(GameObject gameObject)
	{
		this.gameObject = gameObject;
		this.onAttach();
	}
	
	protected void onAttach()
	{}
	
	public void detach()
	{
		//this.gameObject.removeComponent(this.getClass());
	}
}
