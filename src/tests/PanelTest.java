package tests;

import jsmug.SmugApplication;
import jsmug.GameObject;
import jsmug.Smug;
import jsmug.graphics.Panel;
import jsmug.graphics.Text;
import jsmug.graphics.Text.Anchor;
import jsmug.resources.Resources;
import jsmug.utils.Vector;

public class PanelTest extends SmugApplication
{
        private GameObject panel;
        
	public static void main(String[] argv)
	{
		Smug.initialize();
		Smug.runGame(new PanelTest());
                Smug.destroy();
	}
	
	public PanelTest()
	{
	}
	
	public void start()
	{
		Smug.resources.setResourcePath("data/");
                
                Panel p = new Panel(Smug.resources.getTexture("panel"), 2);
                p.setWidth(300);
                p.setHeight(120);
                
                this.panel = Smug.newGameObject();
                this.panel.addComponent(p);
                this.panel.setPositionX(10);
                this.panel.setPositionY(10);
                
                this.addGameObject(this.panel);
        }
	
	public void update()
	{
	}

        
        @Override
        public void dispose()
        {
            Smug.audio.finish();
        }

}
