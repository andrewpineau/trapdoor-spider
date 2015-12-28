package com.andrewpineau.trapdoorspider.gameobjects;

import com.andrewpineau.trapdoorspider.GameData;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Fireball {
	
	//data fields
    private Vector2 position;
    private Vector2 velocity;
    private int width;
    private int height;

    private final int LEFT = 0;
    private final int RIGHT = 1;
    private enum Direction{
        LEFT, RIGHT
    }
    private Direction direction;
    private Platform platform;
    private GameData data;
    private boolean visible;

    private Rectangle hitbox;

    //constructors
    public Fireball() {
    }



    public Fireball(boolean visible, Platform platform, GameData data) {

        this.width = 40;
        this.height = 38;
        this.visible = visible;

        if (visible){
            position = new Vector2(getStartingX(), platform.getY() - height - 10);
            velocity = new Vector2(0, 0);
        } else {
            position = new Vector2(-60, -60);
            velocity = new Vector2(0, 0);
        }

        this.platform = platform;
        this.direction = getNewDirection();
        this.data = data;

        hitbox = new Rectangle(position.x, position.y, width, height);
    }


    //getters and setters


    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction generateDirection(){
        return Direction.LEFT;
    }

    public void setVisible(){
        position = new Vector2(getStartingX(), platform.getY() - height - 10);
        velocity = new Vector2(0, 0);
        visible = true;
    }

    //methods
    public void update() {

        //only update visible fireballs
        if(visible){
            if (this.direction == Direction.LEFT) {
                if(this.position.x <= -20){
                    this.position.x = data.getGameWidth() + 20;
                }
                velocity.x = -5;

            } else {
                if(this.position.x >= + data.getGameWidth() + 20){
                    this.position.x = - 20;
                }
                velocity.x = +5;
            }


            this.position.x += this.velocity.x;

            this.position.y = this.platform.getY() - height - 5;

            //change get new direction if platform is now off screen
            if (this.platform.getY() == this.data.getHighestPlatformY()) {
                this.direction = getNewDirection();
                this.position.x = getStartingX();
            }
        } else {
            if (this.platform.getY() == data.getHighestPlatformY()){
                this.setVisible();
            }
        }
        //move hitbox with fireball
        hitbox = new Rectangle(position.x + 7, position.y + 5, width - 13, height - 10);

    }


    public static Direction getNewDirection(){
        double i = Math.random();
        if(i < 0.5){
            return Direction.LEFT;
        } else {
            return Direction.RIGHT;
        }
    }

    //generates a starting x for a fireball which is a multiple of the possible speed
    public final int getStartingX(){
        int startX = 1;
        while(!(startX % 5 == 0)){
            startX = (int) (Math.random() * 480) + 1;
        }
        return startX;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

}
