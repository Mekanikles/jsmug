/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsmug;

import java.util.Locale;

/**
 *
 * @author anders
 */
public class Log {
    public static void warning(String fmt, Object... prms) {
        System.out.printf(Locale.US, fmt, prms);
        System.out.printf(Locale.US, "\n");
    }
}
