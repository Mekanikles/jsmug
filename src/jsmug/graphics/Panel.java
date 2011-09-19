/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsmug.graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author anders
 */
public class Panel extends Drawable {
    com.badlogic.gdx.graphics.g2d.Sprite[] sprites;
    
    private float width;
    private float height;
    
    private int textureWidth;
    private int textureHeight;
    
    private int borderT;
    private int borderB;
    private int borderL;
    private int borderR;
    
    public Panel(Texture texture, int borderWidth) {
        this.sprites = new com.badlogic.gdx.graphics.g2d.Sprite[9];
        
        this.textureWidth = texture.getWidth() - 2*borderWidth;
        this.textureHeight = texture.getHeight() - 2*borderWidth;
        
        this.borderT = borderWidth;
        this.borderB = borderWidth;
        this.borderL = borderWidth;
        this.borderR = borderWidth;
        
        // Bottom left
        this.sprites[0] = new com.badlogic.gdx.graphics.g2d.Sprite(texture.getInternalTexture(), 
				0, this.textureHeight+borderWidth, borderWidth, borderWidth);

        // Bottom (repeating)
        this.sprites[1] = new com.badlogic.gdx.graphics.g2d.Sprite(texture.getInternalTexture(), 
				borderWidth, textureHeight+borderWidth, textureWidth, borderWidth);

        // Bottom right
        this.sprites[2] = new com.badlogic.gdx.graphics.g2d.Sprite(texture.getInternalTexture(), 
				textureWidth+borderWidth, textureHeight+borderWidth, borderWidth, borderWidth);

        // Middle left
        this.sprites[3] = new com.badlogic.gdx.graphics.g2d.Sprite(texture.getInternalTexture(), 
				0, borderWidth, borderWidth, textureHeight);

        // Middle (repeating)
        this.sprites[4] = new com.badlogic.gdx.graphics.g2d.Sprite(texture.getInternalTexture(), 
				borderWidth, borderWidth, textureWidth, textureHeight);

        // Middle right
        this.sprites[5] = new com.badlogic.gdx.graphics.g2d.Sprite(texture.getInternalTexture(), 
				textureWidth+borderWidth, borderWidth, borderWidth, textureHeight);

        // Upper left
        this.sprites[6] = new com.badlogic.gdx.graphics.g2d.Sprite(texture.getInternalTexture(), 
				0, 0, borderWidth, borderWidth);

        // Upper (repeating)
        this.sprites[7] = new com.badlogic.gdx.graphics.g2d.Sprite(texture.getInternalTexture(), 
				borderWidth, 0, textureWidth, borderWidth);

        // Upper right
        this.sprites[8] = new com.badlogic.gdx.graphics.g2d.Sprite(texture.getInternalTexture(), 
				textureWidth+borderWidth, 0, borderWidth, borderWidth);

        this.setWidth(100);
        this.setHeight(100);
    }
    
    @Override
    void draw(SpriteBatch batch) {
        this.sprites[0].setPosition(this.getGameObject().getPositionX(), this.getGameObject().getPositionY());
        this.sprites[0].draw(batch);

        this.sprites[1].setPosition(this.getGameObject().getPositionX() + this.borderL, this.getGameObject().getPositionY());
        this.sprites[1].draw(batch);
        
        this.sprites[2].setPosition(this.getGameObject().getPositionX() + this.width-this.borderR, this.getGameObject().getPositionY());
        this.sprites[2].draw(batch);

        this.sprites[3].setPosition(this.getGameObject().getPositionX(), this.getGameObject().getPositionY() + this.borderB);
        this.sprites[3].draw(batch);

        this.sprites[4].setPosition(this.getGameObject().getPositionX() + this.borderL, this.getGameObject().getPositionY() + this.borderB);
        this.sprites[4].draw(batch);
        
        this.sprites[5].setPosition(this.getGameObject().getPositionX() + this.width-this.borderR, this.getGameObject().getPositionY() + this.borderB);
        this.sprites[5].draw(batch);

        this.sprites[6].setPosition(this.getGameObject().getPositionX(), this.getGameObject().getPositionY() + this.height - this.borderT);
        this.sprites[6].draw(batch);

        this.sprites[7].setPosition(this.getGameObject().getPositionX() + this.borderL, this.getGameObject().getPositionY() + this.height - this.borderT);
        this.sprites[7].draw(batch);
        
        this.sprites[8].setPosition(this.getGameObject().getPositionX() + this.width-this.borderR, this.getGameObject().getPositionY() + this.height - this.borderT);
        this.sprites[8].draw(batch);
    }
    
    public float getWidth() {
        return this.width;
    }
    
    public void setWidth(float width) {
        this.width = width;
        
        this.sprites[1].setSize(width-this.borderL-this.borderR, this.sprites[1].getHeight());
        this.sprites[4].setSize(width-this.borderL-this.borderR, this.sprites[4].getHeight());
        this.sprites[7].setSize(width-this.borderL-this.borderR, this.sprites[7].getHeight());
    }
    
    public float getHeight() {
        return this.height;
    }
    
    public void setHeight(float height) {
        this.height = height;

        this.sprites[3].setSize(this.sprites[3].getWidth(), height - this.borderT - this.borderB);
        this.sprites[4].setSize(this.sprites[4].getWidth(), height - this.borderT - this.borderB);
        this.sprites[5].setSize(this.sprites[5].getWidth(), height - this.borderT - this.borderB);
    }
}
