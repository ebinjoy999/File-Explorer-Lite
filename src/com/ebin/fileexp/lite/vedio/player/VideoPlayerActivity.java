package com.ebin.fileexp.lite.vedio.player;

import java.util.ResourceBundle.Control;

import com.dark.explorer.lite.ebinjoy.*;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoPlayerActivity extends Activity {
	VideoView video_player_view;
	DisplayMetrics dm;
	SurfaceView sur_View;
	MediaController media_Controller;
	String spath;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_video_player);
		
		Bundle extras = getIntent().getExtras();
		if(extras != null){
		 spath = extras.getString("image_native_video");
		}
		getInit();
	}

	
	
	
	
	public void getInit() {
		video_player_view = (VideoView) findViewById(R.id.video_player_view);
		media_Controller = new MediaController(this);
		dm = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(dm);

	int height = dm.heightPixels;
	int width = dm.widthPixels;
//	int width =450;
//	int height =800;
	video_player_view.setMinimumWidth(width);
	video_player_view.setMinimumHeight(height);
		video_player_view.setMediaController(media_Controller);
		try{
			Toast.makeText(getApplicationContext(), spath, Toast.LENGTH_LONG).show();
		video_player_view.setVideoPath(spath);
		video_player_view.start();
		}catch(Exception exc){}
	}
}






