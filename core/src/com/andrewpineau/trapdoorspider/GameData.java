package com.andrewpineau.trapdoorspider;

import com.badlogic.gdx.Game;

public interface GameData {
	
	public int getPlatformDistance();
    public int getHighestPlatformY();
    public int getLowestPlatformY();
    public int getGameWidth();
    public int getGameHeight();
    public int getCurrentPlatformY();
    public void setScore(int score);
    public Game getGame();
    public ActivityRequestHandler getActivityRequestHandler();

}
