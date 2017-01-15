package com.badlogic.androidgames;

import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.AndroidGame;
import android.app.Activity;
import android.os.Bundle;

public class GameActivity extends AndroidGame {
    /** Called when the activity is first created. */
   // @Override
   /* public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }*/
    
	public Screen getStartScreen() {
		return  new LoadingScreen(this); 
		}
}