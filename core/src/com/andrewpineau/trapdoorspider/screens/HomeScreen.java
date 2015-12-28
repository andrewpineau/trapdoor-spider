package com.andrewpineau.trapdoorspider.screens;

import com.andrewpineau.trapdoorspider.ActivityRequestHandler;
import com.andrewpineau.trapdoorspider.gamehelpers.AssetLoader;
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

public class HomeScreen implements Screen, InputProcessor{
	

    private Game game;
    private ActivityRequestHandler handler;
    private OrthographicCamera cam;
    private SpriteBatch batch;
    private Button playButton;
    private Button highScoresButton;
    private Rectangle playButtonArea;
    private Rectangle highScoreButtonArea;
    Vector3 touch;

    public HomeScreen(Game game, ActivityRequestHandler handler){
    	
    	handler.getGameCenterManager().login();
        this.game = game;
        this.handler = handler;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, 480, 800);

        Gdx.input.setInputProcessor(this);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);
        batch.enableBlending();

        playButton = new Button(56, 600, 128,128);
        highScoresButton = new Button(296, 600, 128, 128);

        playButtonArea = new Rectangle(playButton.getX(), playButton.getY(), playButton.getWidth(), playButton.getHeight());
        highScoreButtonArea = new Rectangle(highScoresButton.getX(), highScoresButton.getY(), highScoresButton.getWidth(), highScoresButton.getHeight());
        
        AssetLoader.mainSound.play(0.1f);
        

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
        // Disable transparency
        batch.draw(AssetLoader.homeBackground, 0,0, 480, 800);
        
        if(!handler.isLoggedIn() && !handler.getLoginFailed()){
        	AssetLoader.smallFont.draw(batch, "One Moment Please...",  20, 650);
        } else {
        //buttons
        batch.draw(AssetLoader.playButton, playButton.getX(), playButton.getY(), playButton.getWidth(), playButton.getHeight());
        batch.draw(AssetLoader.highScoreButton, highScoresButton.getX(), highScoresButton.getY(), highScoresButton.getWidth(), highScoresButton.getHeight());
        }
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
        if(handler.isLoggedIn() || handler.getLoginFailed()){
        	//if logged in or login failed
	        if(playButtonArea.contains(touch.x, touch.y)){
	            game.setScreen(new GameScreen(game, handler));
	        } else if (highScoreButtonArea.contains(touch.x, touch.y)){
	        	game.setScreen(new HighScoresScreen(game, handler));
	        
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
