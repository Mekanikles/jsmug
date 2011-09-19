/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsmug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;

/**
 *
 * @author anders
 */
public class Debug {
    public static void println(String fmt, Object... prms) {
        System.out.printf(Locale.US, fmt, prms);
        System.out.printf(Locale.US, "\n");
    }
}
