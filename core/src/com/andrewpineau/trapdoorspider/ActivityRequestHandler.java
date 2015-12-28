package com.andrewpineau.trapdoorspider;

import org.robovm.bindings.gamecenter.GameCenterManager;

public interface ActivityRequestHandler {
	
	public void showAds(boolean show);
	
	public GameCenterManager getGameCenterManager();
	
	public boolean isLoggedIn();
	
	public boolean getLoginFailed();
	
	public String getLeaderboardID();


}
