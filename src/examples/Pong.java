package examples;

import jsmug.SmugApplication;
import jsmug.GameObject;
import jsmug.Smug;
import jsmug.graphics.Sprite;
import jsmug.graphics.Text;
import jsmug.graphics.Text.Anchor;
import jsmug.input.Input;
import jsmug.input.Input.Keys;
import jsmug.physics.Physics;
import jsmug.physics.BoxCollider;
import jsmug.resources.Resources;
import jsmug.utils.Vector;



public class Pong extends SmugApplication
{
	public static void main(String[] argv)
	{
                Smug.initialize();
		Smug.runGame(new Pong());
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
	private Text p1ScoreText;
	private int p1Points = 0;
	private GameObject p2Score;
	private Text p2ScoreText;
	private int p2Points = 0;
	
	
	private GameObject leftWall;
	private GameObject rightWall;
	private GameObject bottomWall;
	private GameObject topWall;
	
	private Vector ballSpeed;

        
        @Override
	public void create()
	{
		Smug.resources.setResourcePath("data/");

                this.paddles[0] = Smug.newGameObject(new Vector(16, 300));
		this.paddles[0].addComponent(new Sprite(16, 96));
		this.paddles[0].addComponent(new BoxCollider(16, 96));
		this.paddles[1] = Smug.newGameObject(new Vector(800 - 16, 300));
		this.paddles[1].addComponent(new Sprite(16, 96));
		this.paddles[1].addComponent(new BoxCollider(16, 96));
		this.ball = Smug.newGameObject(new Vector(400, 300));
		this.ball.addComponent(new Sprite(16, 16));
		this.ball.addComponent(new BoxCollider(16, 16));
		
		this.net = Smug.newGameObject(new Vector(400, 300));
		this.net.addComponent(new Sprite(8, 600));

                this.p1Score = Smug.newGameObject(new Vector(400 - 16, 600 - 64));
		this.p1ScoreText = (Text)this.p1Score.addComponent(
				new Text(Smug.resources.getFont("font"), "" + this.p1Points, 64.0f, Text.Anchor.BOTTOMRIGHT));

                this.p2Score = Smug.newGameObject(new Vector(400 + 16, 600 - 64));
		this.p2ScoreText = (Text)this.p2Score.addComponent(
				new Text(Smug.resources.getFont("font"), "" + this.p2Points, 64.0f, Text.Anchor.BOTTOMLEFT));
		
		this.topWall = Smug.newGameObject(new Vector(400, 600 + 32));
		this.topWall.addComponent(new BoxCollider(800, 64));
		this.bottomWall = Smug.newGameObject(new Vector(400, -32));
		this.bottomWall.addComponent(new BoxCollider(800, 64));
		this.leftWall = Smug.newGameObject(new Vector(-32, 300));
		this.leftWall.addComponent(new BoxCollider(64, 600));
		this.rightWall = Smug.newGameObject(new Vector(800 + 32, 300));
		this.rightWall.addComponent(new BoxCollider(64, 600));		
		
		double i = Math.random() * 360.0f;
		this.ballSpeed = new Vector(Math.cos(i) * 8.0f, Math.sin(i) * 4.0f);

                this.addGameObject(this.paddles[0]);
                this.addGameObject(this.paddles[1]);
                this.addGameObject(this.ball);
                this.addGameObject(this.net);
                this.addGameObject(this.p1Score);
                this.addGameObject(this.p2Score);
                this.addGameObject(this.topWall);
                this.addGameObject(this.bottomWall);
                this.addGameObject(this.leftWall);
                this.addGameObject(this.rightWall);
	}
	
	public void update()
	{
		if (Smug.input.getKey(Input.Keys.UP))
		{
			this.paddles[0].move(new Vector(0, 5));
		}
		if (Smug.input.getKey(Input.Keys.DOWN))
		{
			this.paddles[0].move(new Vector(0, -5));
		}
		
		if (Smug.input.getMousePositionY() > this.paddles[1].getPositionY())
		{
			this.paddles[1].move(new Vector(0, 5));
		}
		if (Smug.input.getMousePositionY() < this.paddles[1].getPositionY())
		{
			this.paddles[1].move(new Vector(0, -5));
		}
		
		this.ball.move(this.ballSpeed);
		
		
		for (GameObject paddle : this.paddles)
		{
			if (Smug.physics.collides(paddle, this.bottomWall))
				paddle.setPositionY(48);
			else if (Smug.physics.collides(paddle, this.topWall))
				paddle.setPositionY(600 - 48);	
		}	
		
		if (Smug.physics.collides(this.ball, this.bottomWall))
		{
			this.ball.setPositionY(8);
			this.ballSpeed.flipY();
		}
		else if (Smug.physics.collides(this.ball, this.topWall))
		{
			this.ball.setPositionY(600 - 8);
			this.ballSpeed.flipY();
		}
		
		
		if (Smug.physics.collides(this.paddles[0], this.ball))
		{
			this.ball.setPositionX(this.paddles[0].getPositionX() + 16);
			this.ballSpeed.flipX();
		}
		else if (Smug.physics.collides(this.paddles[1], this.ball))
		{
			this.ball.setPositionX(this.paddles[1].getPositionX() - 16);
			this.ballSpeed.flipX();
		}
		
		if (Smug.physics.collides(this.ball, this.leftWall))
		{
			double i = Math.random() * 360.0f;
			this.ballSpeed = new Vector(Math.cos(i) * 8.0f, Math.sin(i) * 4.0f);
			this.ball.setPosition(new Vector(400, 300));
			
			this.p2Points ++;
			this.p2ScoreText.setText(Integer.toString(this.p2Points));
		}
		else if (Smug.physics.collides(this.ball, this.rightWall))
		{
			double i = Math.random() * 360.0f;
			this.ballSpeed = new Vector(Math.cos(i) * 8.0f, Math.sin(i) * 4.0f);
			this.ball.setPosition(new Vector(400, 300));
			
			this.p1Points ++;
			this.p1ScoreText.setText(Integer.toString(this.p1Points));
		}
	}
        
        @Override
        public void dispose() {
                Smug.destroy();
        }
}
