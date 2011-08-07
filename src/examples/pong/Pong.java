package examples.pong;

import jsmug.*;
import jsmug.Input.Keys;


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
	
	private GameObject[] paddles = new GameObject[2];
	
	private GameObject ball;
	
	private GameObject net;
	
	private GameObject p1Score;
	private TextDrawable p1ScoreText;
	private int p1Points = 0;
	private GameObject p2Score;
	private TextDrawable p2ScoreText;
	private int p2Points = 0;
	
	
	private GameObject leftWall;
	private GameObject rightWall;
	private GameObject bottomWall;
	private GameObject topWall;
	
	private Vector ballSpeed;
	
	
	public void start()
	{
		Resources.getInstance().setResourcePath("data/pong/");
		
		this.paddles[0] = new GameObject(new Vector(16, 300));
		this.paddles[0].addComponent(new TextureDrawable(16, 96));
		this.paddles[0].addComponent(new RectangleCollider(16, 96));
		this.paddles[1] = new GameObject(new Vector(800 - 16, 300));
		this.paddles[1].addComponent(new TextureDrawable(16, 96));
		this.paddles[1].addComponent(new RectangleCollider(16, 96));
		this.ball = new GameObject(new Vector(400, 300));
		this.ball.addComponent(new TextureDrawable(16, 16));
		this.ball.addComponent(new RectangleCollider(16, 16));
		
		this.net = new GameObject(new Vector(400, 300));
		this.net.addComponent(new TextureDrawable(8, 600));
		
		this.p1Score = new GameObject(new Vector(400 - 64, 600 - 64));
		this.p1ScoreText = (TextDrawable)this.p1Score.addComponent(new TextDrawable(Resources.getInstance().getFont("font"), "0"));
		this.p1ScoreText.setTextSize(64.0f);
		
		this.p2Score = new GameObject(new Vector(400 + 64, 600 - 64));
		this.p2ScoreText = (TextDrawable)this.p2Score.addComponent(new TextDrawable(Resources.getInstance().getFont("font"), "0"));
		this.p2ScoreText.setTextSize(64.0f);
		
		this.topWall = new GameObject(new Vector(400, 600 + 32));
		this.topWall.addComponent(new RectangleCollider(800, 64));
		this.bottomWall = new GameObject(new Vector(400, -32));
		this.bottomWall.addComponent(new RectangleCollider(800, 64));
		this.leftWall = new GameObject(new Vector(-32, 300));
		this.leftWall.addComponent(new RectangleCollider(64, 600));
		this.rightWall = new GameObject(new Vector(800 + 32, 300));
		this.rightWall.addComponent(new RectangleCollider(64, 600));		
		
		double i = Math.random() * 360.0f;
		this.ballSpeed = new Vector(Math.cos(i) * 8.0f, Math.sin(i) * 4.0f);
		
	}
	
	public void update()
	{
		if (Input.getInstance().getKey(Input.Keys.UP))
		{
			this.paddles[0].move(new Vector(0, 5));
		}
		if (Input.getInstance().getKey(Input.Keys.DOWN))
		{
			this.paddles[0].move(new Vector(0, -5));
		}
		
		if (Input.getInstance().getMousePositionY() > this.paddles[1].getPositionY())
		{
			this.paddles[1].move(new Vector(0, 5));
		}
		if (Input.getInstance().getMousePositionY() < this.paddles[1].getPositionY())
		{
			this.paddles[1].move(new Vector(0, -5));
		}
		
		this.ball.move(this.ballSpeed);
		
		
		for (GameObject paddle : this.paddles)
		{
			if (Physics.getInstance().collides(paddle, this.bottomWall))
				paddle.setPositionY(48);
			else if (Physics.getInstance().collides(paddle, this.topWall))
				paddle.setPositionY(600 - 48);	
		}	
		
		if (Physics.getInstance().collides(this.ball, this.bottomWall))
		{
			this.ball.setPositionY(8);
			this.ballSpeed.flipY();
		}
		else if (Physics.getInstance().collides(this.ball, this.topWall))
		{
			this.ball.setPositionY(600 - 8);
			this.ballSpeed.flipY();
		}
		
		
		if (Physics.getInstance().collides(this.paddles[0], this.ball))
		{
			this.ball.setPositionX(this.paddles[0].getPositionX() + 16);
			this.ballSpeed.flipX();
		}
		else if (Physics.getInstance().collides(this.paddles[1], this.ball))
		{
			this.ball.setPositionX(this.paddles[1].getPositionX() - 16);
			this.ballSpeed.flipX();
		}
		
		if (Physics.getInstance().collides(this.ball, this.leftWall))
		{
			double i = Math.random() * 360.0f;
			this.ballSpeed = new Vector(Math.cos(i) * 8.0f, Math.sin(i) * 4.0f);
			this.ball.setPosition(new Vector(400, 300));
			
			this.p2Points ++;
			this.p2ScoreText.setText(Integer.toString(this.p2Points));
		}
		else if (Physics.getInstance().collides(this.ball, this.rightWall))
		{
			double i = Math.random() * 360.0f;
			this.ballSpeed = new Vector(Math.cos(i) * 8.0f, Math.sin(i) * 4.0f);
			this.ball.setPosition(new Vector(400, 300));
			
			this.p1Points ++;
			this.p1ScoreText.setText(Integer.toString(this.p1Points));
		}
			
	}
}
