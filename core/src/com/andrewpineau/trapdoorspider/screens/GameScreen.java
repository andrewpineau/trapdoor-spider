package com.andrewpineau.trapdoorspider.screens;

import com.andrewpineau.trapdoorspider.ActivityRequestHandler;
import com.andrewpineau.trapdoorspider.GameData;
import com.andrewpineau.trapdoorspider.gamehelpers.GameInputHandler;
import com.andrewpineau.trapdoorspider.gameworld.GameRenderer;
import com.andrewpineau.trapdoorspider.gameworld.GameWorld;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen, GameData{
	private Game game;
	private ActivityRequestHandler handler;
    private GameWorld world;
    private GameRenderer renderer;
    private float screenWidth;
    private float screenHeight;
    private float gameWidth;
    private float gameHeight;
    private float runTime = 0;

    //important settings
    public final int CURRENT_PLATFORM_Y = 180; //This the lowest the spider will go and where a closed current platform stops
    public final int PLATFORM_DISTANCE = 230; //the constant distance between the y coordinates of 2 platforms
    public final int HIGHEST_PLATFORM_Y = CURRENT_PLATFORM_Y - PLATFORM_DISTANCE * 2;
    public final int LOWEST_PLATFORM_Y = CURRENT_PLATFORM_Y  + PLATFORM_DISTANCE * 3;

    public GameScreen(Game game, ActivityRequestHandler handler) {

        this.game = game;
        this.handler = handler;
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        gameWidth = 480;
        gameHeight = screenHeight / (screenWidth/ gameWidth);

        world = new GameWorld(this);
        renderer = new GameRenderer(world, (int)gameHeight);

        Gdx.input.setInputProcessor(new GameInputHandler(world, renderer.getCam(), this.handler));
    }


	@Override
    public void show() {

    }

    @Override
    public void render(float delta) {//game loop
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
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
        world = null;
        renderer = null;
    }

    @Override
    public int getPlatformDistance() {
        return PLATFORM_DISTANCE;
    }

    @Override
    public int getHighestPlatformY() {
        return HIGHEST_PLATFORM_Y;
    }

    @Override
    public int getLowestPlatformY() {
        return LOWEST_PLATFORM_Y;
    }

    @Override
    public int getGameWidth() {
        return (int)gameWidth;
    }

    @Override
    public int getGameHeight() {
        return (int)gameHeight;
    }

    @Override
    public int getCurrentPlatformY() {
        return CURRENT_PLATFORM_Y;
    }

    @Override
    public void setScore(int score) {
        //TODO
    }

    @Override
    public Game getGame() {
        return game;
    }


	@Override
	public ActivityRequestHandler getActivityRequestHandler() {
		return handler;
	}

}
