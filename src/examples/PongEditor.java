/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import editor.Editor;
import jsmug.Smug;

/**
 *
 * @author anders
 */
public class PongEditor {

    public static void main(String[] argv) {
        Smug.initialize();
        Editor editor = new Editor();
        Smug.runGame(editor);
        editor.loadApplication(Pong.class);
        Smug.destroy();
    }
}
