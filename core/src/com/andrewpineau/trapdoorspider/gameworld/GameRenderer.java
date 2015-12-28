package com.andrewpineau.trapdoorspider.gameworld;

import com.andrewpineau.trapdoorspider.gamehelpers.AssetLoader;
import com.andrewpineau.trapdoorspider.gameobjects.Button;
import com.andrewpineau.trapdoorspider.gameobjects.Fireball;
import com.andrewpineau.trapdoorspider.gameobjects.Platform;
import com.andrewpineau.trapdoorspider.gameobjects.Spider;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameRenderer {
	
	private GameWorld world;
    private OrthographicCamera cam;

    private SpriteBatch batch;

    //game objects
    private Spider spider;

    private Platform platform0;
    private Platform platform1;
    private Platform platform2;
    private Platform platform3;
    private Platform platform4;

    private Fireball fireball0;
    private Fireball fireball1;
    private Fireball fireball2;
    private Fireball fireball3;
    private Fireball fireball4;

    private Button jumpButton;
    private Button dropButton;
    private Button playButton;
    private Button highScoresButton;
    //private Button shareButton;

    //game assets
    private TextureRegion currentSpiderSprite;


    //constr.
    public GameRenderer(GameWorld world, int gameHeight){
        this.world = world;

        cam = new OrthographicCamera();
        cam.setToOrtho(true, 480, 800);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);

        initGameObjects();
    }

    private void initGameObjects(){

        spider = world.getSpider();

        platform0 = world.getPlatforms()[0];
        platform1 = world.getPlatforms()[1];
        platform2 = world.getPlatforms()[2];
        platform3 = world.getPlatforms()[3];
        platform4 = world.getPlatforms()[4];

        fireball0 = world.getFireballs()[0];
        fireball1 = world.getFireballs()[1];
        fireball2 = world.getFireballs()[2];
        fireball3 = world.getFireballs()[3];
        fireball4 = world.getFireballs()[4];


        jumpButton = world.getJumpButton();
        dropButton = world.getDropButton();
        playButton = world.getPlayButton();
        highScoresButton = world.getHighScoresButton();
        //shareButton = world.getShareButton();

    }

    public void render(float runTime){

        //black background to prevent flickering
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Begin SpriteBatch
        batch.begin();

        batch.disableBlending();

        batch.draw(AssetLoader.gameBackground, 0,0, 480, 800);

        batch.enableBlending();

        setCurrentSpiderSprite();

        batch.draw(currentSpiderSprite, spider.getX(), spider.getY(), spider.getWidth(), spider.getHeight());

        batch.draw((platform0.isOpen() ? AssetLoader.platformOpen: AssetLoader.platformClose), platform0.getX(), platform0.getY(), platform0.getWidth(), platform0.getHeight());
        batch.draw((platform1.isOpen() ? AssetLoader.platformOpen: AssetLoader.platformClose), platform1.getX(), platform1.getY(), platform1.getWidth(), platform1.getHeight());
        batch.draw((platform2.isOpen() ? AssetLoader.platformOpen: AssetLoader.platformClose), platform2.getX(), platform2.getY(), platform2.getWidth(), platform2.getHeight());
        batch.draw((platform3.isOpen() ? AssetLoader.platformOpen: AssetLoader.platformClose), platform3.getX(), platform3.getY(), platform3.getWidth(), platform3.getHeight());
        batch.draw((platform4.isOpen() ? AssetLoader.platformOpen: AssetLoader.platformClose), platform4.getX(), platform4.getY(), platform4.getWidth(), platform4.getHeight());

        batch.draw(AssetLoader.fireballAnim.getKeyFrame(runTime), fireball0.getX(), fireball0.getY(), fireball0.getWidth(), fireball0.getHeight());
        batch.draw(AssetLoader.fireballAnim.getKeyFrame(runTime), fireball1.getX(), fireball1.getY(), fireball1.getWidth(), fireball1.getHeight());
        batch.draw(AssetLoader.fireballAnim.getKeyFrame(runTime), fireball2.getX(), fireball2.getY(), fireball2.getWidth(), fireball2.getHeight());
        batch.draw(AssetLoader.fireballAnim.getKeyFrame(runTime), fireball3.getX(), fireball3.getY(), fireball3.getWidth(), fireball3.getHeight());
        batch.draw(AssetLoader.fireballAnim.getKeyFrame(runTime), fireball4.getX(), fireball4.getY(), fireball4.getWidth(), fireball4.getHeight());

        //whats drawn below depends on whether the game is running or game over
        if(world.getState() == GameWorld.GameState.Running){
            batch.draw(AssetLoader.jumpButton, jumpButton.getX(), jumpButton.getY(), jumpButton.getWidth(), jumpButton.getHeight());
            batch.draw(AssetLoader.dropButton, dropButton.getX(), dropButton.getY(), dropButton.getWidth(), dropButton.getHeight());
            AssetLoader.largeFont.draw(batch, Integer.toString(world.getScore()), 20, 40);
        } else {
            batch.draw(AssetLoader.playButton, playButton.getX(), playButton.getY(), playButton.getWidth(), playButton.getHeight());
            batch.draw(AssetLoader.highScoreButton, highScoresButton.getX(), highScoresButton.getY(), highScoresButton.getWidth(), highScoresButton.getHeight());
            //batch.draw(AssetLoader.shareButton, shareButton.getX(), shareButton.getY(), shareButton.getWidth(), shareButton.getHeight());
            batch.draw(AssetLoader.gameOverText, 60, 30, 357, 37);
            AssetLoader.smallFont.draw(batch, "Your score is " + Integer.toString(world.getScore()), 60, 300);
            if(world.isNewHighScore()){
                batch.draw(AssetLoader.newHighScoreText, 78, 240, 323, 22 );
            }
        }
        // End SpriteBatch
        batch.end();
    }

    public OrthographicCamera getCam() {
        return cam;
    }
    
    //spider sprite changes based on its state
    public void setCurrentSpiderSprite(){
        if(spider.isDead()){
            currentSpiderSprite = AssetLoader.spiderDead;
        } else if (spider.isDropping()){
            currentSpiderSprite = AssetLoader.spiderDrop;
        } else if (spider.isJumping()){
            currentSpiderSprite = AssetLoader.spiderJump;
        } else {
            currentSpiderSprite = AssetLoader.spiderStand;
        }
    }

}
