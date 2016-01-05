package com.ebin.fileexp.lite.image.viewer;


import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import com.dark.explorer.lite.ebinjoy.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class FullScreenViewActivity extends Activity{

	private Utils utils;
	private FullScreenImageAdapter adapter;
	private ViewPager viewPager;
	String spath;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

    	
    	
		setContentView(R.layout.image_activity_fullscreen_view);
		Bundle extras = getIntent().getExtras();
		if(extras != null){
		 spath = extras.getString("image_native_bu");
		}

	
		
String path="";	
String[]  tt = spath.split("\\/");
for(int i = 0; (i<tt.length-1); i++)
	path = path+tt[i]+"/";
	
	Toast.makeText(getBaseContext(), path, Toast.LENGTH_LONG).show();

	ArrayList<String> filePaths = new ArrayList<String>();

	//File directory = new File("/storage/sdcard1");
	File directory = new File(path);
	// check for directory
	if (directory.isDirectory()) {
	                                              	// getting list of file paths
	                        	             File[] listFiles = directory.listFiles();
                                                 // Check for count
	             	if (listFiles.length > 0) {

			                               // loop through all files
			                            for (int i = 0; i < listFiles.length; i++) {
                                                        	// get file path
				                           String filePath = listFiles[i].getAbsolutePath();

				                            // check for supported file extension
				                              if (IsSupportedFile(filePath)) {
				                                      	// Add image path to array list
					                                      filePaths.add(filePath);
			                                                                   	}
			                                                                                           }
		                  } 

	} 


	filePaths.remove(spath);
filePaths.add(0, spath);












viewPager = (ViewPager) findViewById(R.id.pager);
        utils = new Utils(getApplicationContext());

		/////Intent i = getIntent();
	/////	int position = i.getIntExtra("position", 0);

		adapter = new FullScreenImageAdapter(FullScreenViewActivity.this,filePaths); ///filepath contain all file list

		viewPager.setAdapter(adapter);

		// displaying selected image first
	////viewPager.setCurrentItem(position);
	}
	
	
	private boolean IsSupportedFile(String filePath) {
		String ext = filePath.substring((filePath.lastIndexOf(".") + 1),filePath.length());

		if (AppConstant.FILE_EXTN
				.contains(ext.toLowerCase(Locale.getDefault())))
			return true;
		else
			return false;

	}
	
}
