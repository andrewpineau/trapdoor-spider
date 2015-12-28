package com.andrewpineau.trapdoorspider.gamehelpers;

import com.andrewpineau.trapdoorspider.ActivityRequestHandler;
import com.andrewpineau.trapdoorspider.gameworld.GameWorld;
import com.andrewpineau.trapdoorspider.screens.GameScreen;
import com.andrewpineau.trapdoorspider.screens.HighScoresScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class GameInputHandler implements InputProcessor{
	
	private Game game;
	private ActivityRequestHandler handler;
    private GameWorld world;
    private OrthographicCamera cam;
    private Rectangle jumpButtonArea;
    private Rectangle dropButtonArea;
    private Rectangle playButtonArea;
    private Rectangle highScoresButtonArea;
    private Vector3 touch;

    public GameInputHandler(GameWorld world, OrthographicCamera cam, ActivityRequestHandler handler) {
        this.game = world.getGameData().getGame();
        this.world = world;
        this.cam =cam; //cam used to translate screen coordinates to world coordinates
        this.handler = handler;
        jumpButtonArea = new Rectangle(world.getJumpButton().getX(), world.getJumpButton().getY(), world.getJumpButton().getWidth(), world.getJumpButton().getHeight());
        dropButtonArea = new Rectangle(world.getDropButton().getX(), world.getDropButton().getY(), world.getDropButton().getWidth(), world.getDropButton().getHeight());
        playButtonArea = new Rectangle(world.getPlayButton().getX(), world.getPlayButton().getY(), world.getPlayButton().getWidth(), world.getPlayButton().getHeight());
        highScoresButtonArea = new Rectangle(world.getHighScoresButton().getX(), world.getHighScoresButton().getY(), world.getHighScoresButton().getWidth(), world.getHighScoresButton().getHeight());
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        cam.unproject(touch); // translate screen coordinates to game world coordinates
        //if the game is running check for move buttong touch events
        if(world.getState() == GameWorld.GameState.Running){
            if(jumpButtonArea.contains(touch.x,touch.y)){
            	if(!world.getSpider().isDropping() && !world.getSpider().isJumping() ){
	                world.getSpider().jump();
	                AssetLoader.jumpSound.play(0.4f);
            	}
            } else if(dropButtonArea.contains(touch.x, touch.y)){
                if(!world.getCurrentPlatform().isOpen()) {
                    world.getCurrentPlatform().setOpen(true);
                    world.updateScore();
                    AssetLoader.openSound.play(0.4f);
                }
            }
        } else { // if the game is game over check for option button touch events
            if(playButtonArea.contains(touch.x,touch.y)){
                game.setScreen(new GameScreen(game, handler));
            } else if (highScoresButtonArea.contains(touch.x, touch.y)){
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
