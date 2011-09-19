/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import jsmug.Smug;
import jsmug.SmugApplication;

/**
 *
 * @author anders
 */
public class Editor extends SmugApplication {
    public static void main(String[] argv)
    {
        Smug.initialize();
        Smug.runGame(new Editor());
    }	
    
    public Editor()
    {
        this.name = "Smug Editor";
        this.width = 800;
        this.height = 600;
        this.fullscreen = false;
    }
    
    public void update() 
    {
        
    }
}
