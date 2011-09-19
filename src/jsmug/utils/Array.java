/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsmug.utils;

import jsmug.Log;

/**
 *
 * @author anders
 */
public class Array<T extends Object> {
    public T[] elements = null;
    public int position = 0;
    public int capacity = 0;
    
    private int chunkSize = 0;
    private Class<T> clazz;
    
    public Array(Class<T> clazz, int chunkSize) {
        this.chunkSize = chunkSize;
        this.elements = (T[]) java.lang.reflect.Array.newInstance(clazz, chunkSize);
        this.capacity = chunkSize;
        this.clazz = clazz;
    }
    
    public void push(T element) {
        if(this.position == this.capacity) {
            Log.warning("Array expansion occured of type %s.", this.clazz.toString());
            this.expand();
        }
        
        this.elements[this.position] = element;
        this.position++;
    }
    
    public T pop() {
        if(this.position > 0) {
            T element = this.elements[this.position-1];
            this.position--;
            return element;
        } else {
            return null;
        }
    }
    
    public boolean insertAfter(T where, T element) {
        boolean found = false;
        T tmp;

        if(this.position == this.capacity) {
            Log.warning("Array expansion occured of type %s.", this.clazz.toString());
            this.expand();
        }
        
        for(int i=0; i <= this.position; i++) {
            if(found) {
                tmp = this.elements[i];
                this.elements[i] = element;
                element = tmp;
            }
            if(this.elements[i] == where) {
                found = true;
                this.position++;
            }
        }
            
        return found;
    }
    
    public boolean insertBefore(T where, T element) {
        boolean found = false;
        T tmp;

        if(this.position == this.capacity) {
            Log.warning("Array expansion occured of type %s.", this.clazz.toString());
            this.expand();
        }
        
        for(int i=0; i <= this.position; i++) {
            if(this.elements[i] == where) {
                found = true;
                this.position++;
            }

            if(found) {
                tmp = this.elements[i];
                this.elements[i] = element;
                element = tmp;
            }
        }
            
        return found;
    }
    
    public boolean remove(T element) {
        boolean found = false;

        for(int i=0; i < this.position-1; i++) {
            if(this.elements[i] == element) {
                found = true;
            }
            
            if(found) {
                this.elements[i] = this.elements[i+1];
            }
        }
        
        if(found) {
            this.elements[this.position-1] = null;
            this.position--;
        }
        
        return found;
    }
    
    private void expand() {
        this.elements = java.util.Arrays.copyOf(this.elements, this.elements.length+this.chunkSize);
    }
}
