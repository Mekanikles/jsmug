/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jsmug.Debug;
import jsmug.Game;
import jsmug.Smug;
import jsmug.SmugApplication;
import jsmug.audio.Sound;
import jsmug.audio.WaveFloatChannel;

/**
 *
 * @author anders
 */
public class SoundTest extends SmugApplication {
    public Sound sfx;
    
    public void start()
    {
        Smug.resources.setResourcePath("data/");
        
        sfx = Smug.resources.getSound("pongnew.wav");
        sfx.play();
    }
    
    @Override
    public void dispose()
    {
        Smug.audio.finish();
    }

    public static void main(String argv[]) {
        Smug.initialize();
        Smug.runGame(new SoundTest());
    }
}
