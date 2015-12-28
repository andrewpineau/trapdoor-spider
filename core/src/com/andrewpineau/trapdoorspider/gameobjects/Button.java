package com.andrewpineau.trapdoorspider.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Button {
	 	private Vector2 position;
	    private int width;
	    private int height;
	    private boolean clicked;

	    public Button(int x, int y, int width, int height) {
	        position = new Vector2(x, y);
	        this.width = width;
	        this.height = height;
	        this.clicked = false;
	    }

	    public float getX() {
	        return position.x;
	    }

	    public void setX(float x) {
	        this.position.x = x;
	    }

	    public float getY() {
	        return position.y;
	    }

	    public void setY(float y) {
	        this.position.y = y;
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

	    public boolean isClicked() {
	        return clicked;
	    }

	    public void setClicked(boolean clicked) {
	        this.clicked = clicked;
	    }



}
