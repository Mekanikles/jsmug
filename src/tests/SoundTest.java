/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import jsmug.Game;
import jsmug.Smug;
import jsmug.audio.Sound;

/**
 *
 * @author anders
 */
public class SoundTest extends Game {
    public Sound sfx;
    
    public void start()
    {
        Smug.resources.setResourcePath("data/");
        
        sfx = Smug.resources.getSound("pongnew.ogg");
        sfx.play();
    }
    
    public static void main(String argv[]) {
        Smug.initialize();
        Smug.runGame(new SoundTest());
    }
}
