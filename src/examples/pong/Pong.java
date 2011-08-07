package examples.pong;

import smug.*;
import smug.Input.Keys;


public class Pong extends Game
{	
	public static void main(String[] argv)
	{
		Engine.getInstance().runGame(new Pong());
	}
	
	public Pong()
	{
		this.name = "Pong";
		this.width = 800;
		this.height = 600;
		this.fullscreen = false;
	}
	
	private GameObject paddle1;
	private GameObject paddle2;
	
	private GameObject ball;
	
	private Vector ballSpeed;
	
	
	public void start()
	{
		this.paddle1 = new GameObject(new Vector(16, 300));
		this.paddle1.addComponent(new Drawable(0, 0, 32, 96, "pong.png"));
		this.paddle1.addComponent(new RectangleCollider(32, 96));
		this.paddle2 = new GameObject(new Vector(800 - 16, 300));
		this.paddle2.addComponent(new Drawable(32, 0, 32, 96, "pong.png"));
		this.paddle2.addComponent(new RectangleCollider(32, 96));
		this.ball = new GameObject(new Vector(400, 300));
		this.ball.addComponent(new Drawable(64, 0, 32, 32, "pong.png"));
		this.ball.addComponent(new RectangleCollider(32, 32));
		
		double i = Math.random() * 360.0f;
		this.ballSpeed = new Vector(Math.cos(i) * 8.0f, Math.sin(i) * 4.0f);
	}
	
	public void update()
	{
		if (Input.getInstance().getKey(Input.Keys.UP))
		{
			this.paddle1.move(new Vector(0, 5));
		}
		if (Input.getInstance().getKey(Input.Keys.DOWN))
		{
			this.paddle1.move(new Vector(0, -5));
		}
		
		if (Input.getInstance().getMousePosition().getY() > this.paddle2.getPosition().getY())
		{
			this.paddle2.move(new Vector(0, 5));
		}
		if (Input.getInstance().getMousePosition().getY() < this.paddle2.getPosition().getY())
		{
			this.paddle2.move(new Vector(0, -5));
		}
		
		
		
		this.ball.move(this.ballSpeed);
		
		if (this.paddle1.getPosition().getY() < 48)
			this.paddle1.setPosition(new Vector(this.paddle1.getPosition().getX(), 48));
		if (this.paddle1.getPosition().getY() > 600 - 48)
			this.paddle1.setPosition(new Vector(this.paddle1.getPosition().getX(), 600 - 48));				
		
		if (this.paddle2.getPosition().getY() < 48)
			this.paddle2.setPosition(new Vector(this.paddle2.getPosition().getX(), 48));
		if (this.paddle2.getPosition().getY() > 600 - 48)
			this.paddle2.setPosition(new Vector(this.paddle2.getPosition().getX(), 600 - 48));				
		
		if (this.ball.getPosition().getY() < 16)
			this.ballSpeed.flipY();
		if (this.ball.getPosition().getY() > 600 - 16)
			this.ballSpeed.flipY();
		
		if (Physics.getInstance().collides(this.paddle1, this.ball))
			this.ballSpeed.flipX();

		if (Physics.getInstance().collides(this.paddle2, this.ball))
			this.ballSpeed.flipX();
		
		if (this.ball.getPosition().getX() < 0)
		{
			double i = Math.random() * 360.0f;
			this.ballSpeed = new Vector(Math.cos(i) * 8.0f, Math.sin(i) * 4.0f);
			this.ball.setPosition(new Vector(400, 300));
		}
		if (this.ball.getPosition().getX() > 800)
		{
			double i = Math.random() * 360.0f;
			this.ballSpeed = new Vector(Math.cos(i) * 8.0f, Math.sin(i) * 4.0f);
			this.ball.setPosition(new Vector(400, 300));
		}
			
	}
}
