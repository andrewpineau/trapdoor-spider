package com.andrewpineau.trapdoorspider;

import com.andrewpineau.trapdoorspider.gameobjects.Platform;
import com.andrewpineau.trapdoorspider.gameobjects.Spider;

public interface ObjectData {
	public Spider getSpider();
    public Platform getCurrentPlatform();
    public GameData getGameData();
}
