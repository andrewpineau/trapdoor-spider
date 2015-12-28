package com.andrewpineau.trapdoorspider.screens;


import com.andrewpineau.trapdoorspider.ActivityRequestHandler;
import com.andrewpineau.trapdoorspider.gamehelpers.AssetLoader;
import com.andrewpineau.trapdoorspider.gamehelpers.ScoreKeeper;
import com.andrewpineau.trapdoorspider.gameobjects.Button;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class HighScoresScreen implements Screen, InputProcessor{

    private Game game;
    private ActivityRequestHandler handler;
    private OrthographicCamera cam;
    private SpriteBatch batch;
    private Vector3 touch;
    private Button homeButton;
    private Button playButton;
    private Button leaderboardButton;
    private Rectangle homeButtonArea;
    private Rectangle playButtonArea;
    private Rectangle leaderboardButtonArea;
    private ScoreKeeper scoreKeeper;
    private int[] scores;


    public HighScoresScreen(Game game, ActivityRequestHandler handler) {

        this.game = game;
        this.handler = handler;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, 480, 800);

        Gdx.input.setInputProcessor(this);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);
        batch.enableBlending();

        playButton = new Button(56, 650, 128,128);
        homeButton = new Button(296, 650, 128, 128);
        leaderboardButton = new Button(99, 300, 282, 80);
        

        playButtonArea = new Rectangle(playButton.getX(), playButton.getY(), playButton.getWidth(), playButton.getHeight());
        homeButtonArea = new Rectangle(homeButton.getX(), homeButton.getY(), homeButton.getWidth(), homeButton.getHeight());
        leaderboardButtonArea = new Rectangle(leaderboardButton.getX(), leaderboardButton.getY(), leaderboardButton.getWidth(), leaderboardButton.getHeight());

        scoreKeeper = new ScoreKeeper();
        scores = scoreKeeper.readHighScores();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //black background to prevent flickering
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        // Begin SpriteBatch
        batch.begin();

        batch.draw(AssetLoader.highScoreBackground, 0,0, 480, 800);
        if(handler.isLoggedIn()){
	        //display high scores
	        AssetLoader.smallFont.draw(batch, "1. ", 195, 130);
	        AssetLoader.smallFont.draw(batch, Integer.toString(scores[0]), 240, 130);
	        AssetLoader.smallFont.draw(batch, "2. ", 195, 160);
	        AssetLoader.smallFont.draw(batch, Integer.toString(scores[1]), 240, 160);
	        AssetLoader.smallFont.draw(batch, "3. ", 195, 190);
	        AssetLoader.smallFont.draw(batch, Integer.toString(scores[2]), 240, 190);
	        AssetLoader.smallFont.draw(batch, "4. ", 195, 220);
	        AssetLoader.smallFont.draw(batch, Integer.toString(scores[3]), 240, 220);
	        AssetLoader.smallFont.draw(batch, "5. ", 195, 250);
	        AssetLoader.smallFont.draw(batch, Integer.toString(scores[4]), 240, 250);
        
        	batch.draw(AssetLoader.leaderboardButton, leaderboardButton.getX(), leaderboardButton.getY(), leaderboardButton.getWidth(), leaderboardButton.getHeight());
        } else {
        	//display high scores - placed lower if no leaderboard button
	        AssetLoader.smallFont.draw(batch, "1. ", 195, 190);
	        AssetLoader.smallFont.draw(batch, Integer.toString(scores[0]), 240, 190);
	        AssetLoader.smallFont.draw(batch, "2. ", 195, 220);
	        AssetLoader.smallFont.draw(batch, Integer.toString(scores[1]), 240, 220);
	        AssetLoader.smallFont.draw(batch, "3. ", 195, 250);
	        AssetLoader.smallFont.draw(batch, Integer.toString(scores[2]), 240, 250);
	        AssetLoader.smallFont.draw(batch, "4. ", 195, 280);
	        AssetLoader.smallFont.draw(batch, Integer.toString(scores[3]), 240, 280);
	        AssetLoader.smallFont.draw(batch, "5. ", 195, 310);
	        AssetLoader.smallFont.draw(batch, Integer.toString(scores[4]), 240, 310);
        }
        
      //buttons
        batch.draw(AssetLoader.playButton, playButton.getX(), playButton.getY(), playButton.getWidth(), playButton.getHeight());
        batch.draw(AssetLoader.homeButton, homeButton.getX(), homeButton.getY(), homeButton.getWidth(), homeButton.getHeight());
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        cam.unproject(touch); // translate screen coordinates to game world coordinates
        if(playButtonArea.contains(touch.x, touch.y)){
            game.setScreen(new GameScreen(game, handler));
        } else if(homeButtonArea.contains(touch.x, touch.y)){
        	handler.showAds(false);
            game.setScreen(new HomeScreen(game, handler));
        } else if(leaderboardButtonArea.contains(touch.x, touch.y)){
        	if(handler.isLoggedIn()){
        		handler.getGameCenterManager().showLeaderboardView(handler.getLeaderboardID());
        	}
        	
        }
        return true;
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


}
