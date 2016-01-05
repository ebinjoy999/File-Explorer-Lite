package com.ebin.fileexp.lite;


import com.dark.explorer.lite.ebinjoy.R;
import com.ebin.fileexp.lite.a.Slsect_main_gridview_bg;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.Window;
import android.widget.RelativeLayout;

public class AboutyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_about_activity);
        
        SharedPreferences  setting = getSharedPreferences("settings", 0);            
   	 String bg_colour_main = setting.getString("bg_colour_main", "g_red");          
       	RelativeLayout r_gradient = (RelativeLayout) findViewById(R.id.relative_layout_about);
   	 Slsect_main_gridview_bg obb = new Slsect_main_gridview_bg();
   int nn = obb.get_main_linear_layout_bg(bg_colour_main);
   r_gradient.setBackgroundDrawable(getResources().getDrawable(nn)); //gradient all effect work
   	
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.abouty, menu);
        return true;
    }
    
    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    	this.finish();
    }
}
