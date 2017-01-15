package com.badlogic.androidgames;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.badlogic.androidgames.framework.Audio;
import com.badlogic.androidgames.framework.FileIO;
import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input;
import com.badlogic.androidgames.framework.Screen;



public abstract class AndroidGame extends Activity implements Game {
AndroidFastRenderView renderView;
Graphics graphics;
Audio audio;
Input input;
FileIO fileIO;
Screen screen;
WakeLock wakeLock;

public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
requestWindowFeature(Window.FEATURE_NO_TITLE);
getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
WindowManager.LayoutParams.FLAG_FULLSCREEN);
boolean isLandscape = getResources().getConfiguration().orientation ==
Configuration.ORIENTATION_LANDSCAPE;
int frameBufferWidth = isLandscape ? 480 : 320;
int frameBufferHeight = isLandscape ? 320 : 480;
Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
frameBufferHeight, Config.RGB_565);
float scaleX = (float) frameBufferWidth
/ getWindowManager().getDefaultDisplay().getWidth();
float scaleY = (float) frameBufferHeight
/ getWindowManager().getDefaultDisplay().getHeight();
renderView = new AndroidFastRenderView(this, frameBuffer);
graphics = new AndroidGraphics(getAssets(), frameBuffer);
fileIO = new AndroidFileIO(getAssets());
audio = new AndroidAudio(this);
input = new AndroidInput(this, renderView, scaleX, scaleY);
screen = getStartScreen();
setContentView(renderView);
PowerManager powerManager = (PowerManager)
getSystemService(Context.POWER_SERVICE);
wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");

}
public void onResume() {
super.onResume();
wakeLock.acquire();
screen.resume();
renderView.resume();
}
public void onPause() {
super.onPause();
wakeLock.release();
renderView.pause();
screen.pause();
if (isFinishing())
	screen.dispose();
	}
public Input getInput() {
return input;
}

public FileIO getFileIO() {
return fileIO;
}

public Graphics getGraphics() {
return graphics;
}

public Audio getAudio() {
return audio;
}
public void setScreen(Screen screen) {
if (screen == null)
throw new IllegalArgumentException("Screen must not be null");
this.screen.pause();
this.screen.dispose();
screen.resume();
screen.update(0);
this.screen = screen;
}
public Screen getCurrentScreen() {
return screen;
}

public void onBackPressed() {
	  // return;
			final AlertDialog.Builder builder = new AlertDialog.Builder(this);
			super.onPause();
			wakeLock.release();
			renderView.pause();
			screen.pause();
			if (isFinishing())
				screen.dispose();
	  	   
	  	   builder.setMessage("Are you sure you want to quit the Game ?")
		        .setCancelable(false)
		        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int id) {
		            	Intent intent = new Intent(Intent.ACTION_MAIN);
		    			intent.addCategory(Intent.CATEGORY_HOME);
		    			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		    			startActivity(intent);
		    			System.runFinalizersOnExit(true);
		    			System.exit(0);
		            	/*Intent i = new Intent(AndroidGame.this, tryActvity.class);
		    			 finish();
		 		        startActivity(i);*/
			        
		            }
		        })
		        .setNegativeButton("No", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int id) {
		            	//super.onResume();
		            	wakeLock.acquire();
		            	screen.resume();
		            	renderView.resume();
		                 dialog.cancel();
		            }
		        });
		 AlertDialog alert = builder.create();
		 alert.show();
	}
}