package com.andrewpineau.trapdoorspider.gameobjects;

import com.andrewpineau.trapdoorspider.ObjectData;
import com.badlogic.gdx.math.Vector2;

public class Platform {
	
	 //data fields
    private Vector2 position;
    private Vector2 velocity;
    private int width;
    private int height;
    private int startingPositionY;
    private boolean open;
    private ObjectData data;

    public Platform(int x, int y, ObjectData data) {
        this.width = 480;
        this.height = 65;
        this.startingPositionY = y; // this variable holds position of the last resting point of the platform
        this.position = new Vector2(x - (width / 2), y);
        this.velocity = new Vector2(0, 0);
        this.open = false;
        this.data = data;
    }

    public float getX() {

        return position.x;
    }

    public float getY() {

        return position.y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void update(){
    	//platform moves up 
        if(data.getCurrentPlatform().isOpen() && !data.getSpider().isJumping() && this.position.y > this.startingPositionY - data.getGameData().getPlatformDistance()){
            velocity.y = -5;
        } else {
            velocity.y = 0;
            startingPositionY = (int)position.y;
        }
        //reset platform after it reaches the top
        if(this.getY() <= data.getGameData().getHighestPlatformY() ){
            this.position.y = data.getGameData().getLowestPlatformY();
            this.setOpen(false);
        }
        position.y += velocity.y;

    }

}
