package com.andrewpineau.trapdoorspider;

import com.andrewpineau.trapdoorspider.gamehelpers.AssetLoader;
import com.andrewpineau.trapdoorspider.screens.HomeScreen;
import com.badlogic.gdx.Game;



public class TSGame extends Game {
	
	private ActivityRequestHandler handler;
	
	public TSGame(){
		
	}
	
	public TSGame(ActivityRequestHandler handler){
		this.handler = handler;
	}

	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new HomeScreen(this, handler));
	}

}
