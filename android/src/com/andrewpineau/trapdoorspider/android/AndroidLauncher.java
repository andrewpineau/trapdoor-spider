package com.andrewpineau.trapdoorspider.android;

import android.os.Bundle;

import com.andrewpineau.trapdoorspider.TSGame;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new TSGame(), config);
	}
	
	// Add everything below here too
	public void onResume() {
	    super.onResume();
	}

	public void onPause() {
	    super.onPause();
	}
}
