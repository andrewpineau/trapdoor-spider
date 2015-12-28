package com.andrewpineau.trapdoorspider.gameobjects;

import com.andrewpineau.trapdoorspider.gameworld.GameWorld;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Spider {

	 //data fields
    private Vector2 position;
    private Vector2 velocity;
    private int width;
    private int height;
    private int originY;

    private GameWorld world;

    private boolean dropping;
    private boolean jumping;
    private boolean dead;


    private Rectangle hitbox;

    //constructor
    public Spider(float x, float y, GameWorld world) {
        this.width = 110;
        this.height = 72;
        this.originY = (int)y - height;
        position = new Vector2(x - (width / 2), originY);
        velocity = new Vector2(0, 0);

        this.world = world;
        hitbox = new Rectangle(position.x + 30, position.y + 40, 50, 35);
    }

    //methods
    //methods
    public void update(float delta){

        //handles jumping
        position.y += velocity.y;
        //once the top of the spider hits the top of the game screen
        if (this.position.y <= 0 ){
            velocity.y = + 7 ;
        //once the spider returns to its original position
        } else if (this.position.y >= originY){
            velocity.y = 0;
            jumping = false;
        }

        if(world.getCurrentPlatform().isOpen()){
            this.setDropping(true);
        } else {
            this.setDropping(false);
        }

        //move hitbox with spider
        hitbox.set(position.x + 30, position.y + 35, 50, 35);
    }

    public void jump(){
        if(this.jumping == false){
            velocity.y = -7;
            this.jumping = true;
        }

    }



    public void onClick() {
        velocity.y = 0;
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

    public boolean isDropping() {
        return dropping;
    }

    public void setDropping(boolean dropping) {
        this.dropping = dropping;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }
}
