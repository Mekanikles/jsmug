/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import jsmug.utils.Array;

/**
 *
 * @author anders
 */
public class ArrayTest {
	public static void main(String[] argv)
	{
            Array<Integer> array = new Array(Integer.class, 20);
            Integer i1 = 1;
            Integer i2 = 2;
            Integer i3 = 3;
            Integer i4 = 4;
            Integer i5 = 5;
            Integer i6 = 6;
            Integer i7 = 7;
            
            array.push(i1);
            array.push(i2);
            array.push(i3);
            array.push(i4);
            array.insertAfter(i2, i7);
            array.insertBefore(i2, i5);
            array.remove(i5);
            
            Integer i;
            while((i = array.pop()) != null) {
                System.out.println(i);
            }
        }
}
