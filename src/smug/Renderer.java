package smug;

import org.lwjgl.opengl.GL11;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Renderer
{
	private static Renderer instance;
	public static Renderer getInstance() { if (instance == null) instance = new Renderer(); return instance;}
		
	private SpriteBatch batch;
	private boolean debugMode = true;

	
	int screenWidth;
	int screenHeight;
	public int getScreenWidth() { return this.screenWidth;}
	public int getScreenHeight() { return this.screenHeight;}
	
	
	private Renderer()
	{
	}
	
	public void init(int screenWidth, int screenHeight)
	{
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		
		this.batch = new SpriteBatch();
		this.batch.getProjectionMatrix().setToOrtho2D(0, 0, screenWidth, screenHeight);
	}
	
	
	public void render()
	{
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Gdx.gl11.glColor4f(0,1,0,1);
		batch.begin();
		batch.enableBlending();
		
		for (GameObject gameObject : Engine.getInstance().getScene().getGameObjects())
		{
			for (Component component : gameObject.getComponents())
			{
				if (component instanceof Drawable)
				{
					Sprite sprite = ((Drawable)component).getSprite();
					Vector pos = ((Drawable)component).getGameObject().getPosition();
					sprite.setPosition(pos.getX() - sprite.getOriginX(), pos.getY() - sprite.getOriginY());
					sprite.draw(batch);
				}
			}
		}
	
		batch.end();	
		
		if (debugMode)
		{
			GL11.glColor3f(0.0f, 1.0f, 0.0f);
			for (GameObject gameObject : Engine.getInstance().getScene().getGameObjects())
			{
				for (Component component : gameObject.getComponents())
				{
					if (component instanceof RectangleCollider)
					{
						Rectangle rect = ((RectangleCollider)component).getRectangle().add(component.getGameObject().getPosition());
						GL11.glBegin(GL11.GL_LINE_LOOP);
						{
							GL11.glVertex2f(rect.getV0().getX(), rect.getV0().getY());
							GL11.glVertex2f(rect.getV1().getX(), rect.getV0().getY());
							GL11.glVertex2f(rect.getV1().getX(), rect.getV1().getY());
							GL11.glVertex2f(rect.getV0().getX(), rect.getV1().getY());
						}
						GL11.glEnd();
					}
				}
			}
		}	
	}
}
