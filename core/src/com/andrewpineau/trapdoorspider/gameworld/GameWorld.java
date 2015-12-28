package com.andrewpineau.trapdoorspider.gameworld;

import com.andrewpineau.trapdoorspider.GameData;
import com.andrewpineau.trapdoorspider.ObjectData;
import com.andrewpineau.trapdoorspider.gamehelpers.AssetLoader;
import com.andrewpineau.trapdoorspider.gamehelpers.ScoreKeeper;
import com.andrewpineau.trapdoorspider.gameobjects.Button;
import com.andrewpineau.trapdoorspider.gameobjects.Fireball;
import com.andrewpineau.trapdoorspider.gameobjects.Platform;
import com.andrewpineau.trapdoorspider.gameobjects.Spider;

public class GameWorld implements ObjectData{
	
	public enum GameState {
        Running,GameOver
    }

    GameState state;
    private int score;
    private boolean newHighScore;
    private ScoreKeeper scoreKeeper;

    private GameData data;
    private Spider spider;

    private Platform[] platforms;

    private Platform platform0;
    private Platform platform1;
    private Platform platform2;
    private Platform platform3;
    private Platform platform4;
    private Platform currentPlatform;

    private Fireball[] fireballs;

    private Fireball fireball0;
    private Fireball fireball1;
    private Fireball fireball2;
    private Fireball fireball3;
    private Fireball fireball4;

    //buttons
    private Button jumpButton;
    private Button dropButton;
    private Button shareButton;
    private Button playButton;
    private Button highScoresButton;


    ///CONSTRUCTOR///
    public GameWorld(GameData data) {

    	data.getActivityRequestHandler().showAds(false);

        state = GameState.Running;

        score = 0;

        //important game data is held here
        this.data = data;

        //game objects
        spider = new Spider(data.getGameWidth()/2, data.getCurrentPlatformY(), this);

        platform0 = new Platform(data.getGameWidth() / 2, data.getCurrentPlatformY() - data.getPlatformDistance(), this);
        platform1 = new Platform(data.getGameWidth() / 2, data.getCurrentPlatformY(), this);
        platform2 = new Platform(data.getGameWidth() / 2, data.getCurrentPlatformY() + data.getPlatformDistance(), this);
        platform3 = new Platform(data.getGameWidth() / 2, data.getCurrentPlatformY() + data.getPlatformDistance() * 2, this);
        platform4 = new Platform(data.getGameWidth() / 2, data.getCurrentPlatformY() + data.getPlatformDistance() * 3, this);

        //setting up platforms objects
        platforms = new Platform [5];
        platforms[0] = platform0;
        platforms[1] = platform1;
        platforms[2] = platform2;
        platforms[3] = platform3;
        platforms[4] = platform4;

        currentPlatform = platform1; //stores the platform that the spider will land on

        fireball0 = new Fireball(true, platform0, data);
        fireball1 = new Fireball(false, platform1, data);
        fireball2 = new Fireball(true, platform2, data);
        fireball3 = new Fireball(true, platform3, data);
        fireball4 = new Fireball(true, platform4, data);

        fireballs = new Fireball [platforms.length];
        fireballs[0] = fireball0;
        fireballs[1] = fireball1;
        fireballs[2] = fireball2;
        fireballs[3] = fireball3;
        fireballs[4] = fireball4;


        //buttons
        jumpButton = new Button(56, 650, 128, 128);
        dropButton = new Button(296, 650, 128, 128);
        shareButton = new Button(168, 350, 144, 80);
        highScoresButton = new Button(296, 460, 128, 128);
        playButton = new Button(56, 460, 128, 128);

        scoreKeeper = new ScoreKeeper();
    }

    ///UPDATE OBJECTS///
    public void update(float delta){
        if(state == GameState.Running){
            spider.update(delta);
            for (int i = 0; i < platforms.length; i++){
                platforms[i].update();
                //here we swap out current and next  platforms during the platform rotation
                if(platforms[i].getY() == data.getCurrentPlatformY()){
                    currentPlatform = platforms[i];
                }
            }
            //update platforms
            for(int i = 0; i < platforms.length; i++){
                platforms[i].update();
            }
            //update fireballs
            for (int i = 0; i < fireballs.length; i++){
                fireballs[i].update();
                if(fireballs[i].getHitbox().overlaps(spider.getHitbox())){
                    spider.setDead(true);
                }
            }
            if(spider.isDead()){
            	data.getActivityRequestHandler().showAds(true);
                state = GameState.GameOver;
                AssetLoader.gameOverSound.play(0.4f);
                newHighScore = scoreKeeper.checkScore(score);
                if(newHighScore){
                	data.getActivityRequestHandler().getGameCenterManager().reportScore("trapdoor_spider_leaderboard", score);
                }

            }
        }
    }

    public void updateHighScores(){


    }

    ///GET AND SET METHODS///
    public Spider getSpider() {
        return spider;
    }

    public void updateScore() {this.score++;}

    @Override
    public Platform getCurrentPlatform() {
        return currentPlatform;
    }

    @Override
    public GameData getGameData() {
        return data;
    }

    public Platform[] getPlatforms() {
        return platforms;
    }

    public Fireball[] getFireballs() {
        return fireballs;
    }

    public Button getJumpButton() {
        return jumpButton;
    }

    public Button getDropButton() {
        return dropButton;
    }

    public Button getShareButton() {
        return shareButton;
    }

    public Button getHighScoresButton() {
        return highScoresButton;
    }

    public int getScore() {
        return score;
    }

    public Button getPlayButton() {
        return playButton;
    }

    public boolean isNewHighScore() {
        return newHighScore;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    @SuppressWarnings("unused")
	private void nullify() {
        spider = null;
        platform0 = null;
        platform1 = null;
        platform2 = null;
        platform3 = null;
        platform4 = null;
        platforms = null;
        fireball0 = null;
        fireball1 = null;
        fireball2 = null;
        fireball3 = null;
        fireball4 = null;
        fireballs = null;
        jumpButton = null;
        dropButton = null;
        highScoresButton = null;
        shareButton = null;
        playButton = null;

        // Call garbage collector to clean up memory.
        System.gc();

    }


}
