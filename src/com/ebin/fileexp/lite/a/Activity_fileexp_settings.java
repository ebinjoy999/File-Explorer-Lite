package com.ebin.fileexp.lite.a;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dark.explorer.lite.ebinjoy.R;
import com.dark.explorer.lite.ebinjoy.R.layout;
import com.dark.explorer.lite.ebinjoy.R.menu;
import com.ebin.fileexp.lite.ActivityListdirectry;
import com.ebin.fileexp.lite.Adapter_main_grid_FileArray;
import com.ebin.fileexp.lite.Dir_size_Human_Redable;
import com.ebin.fileexp.lite.FileOpen;
import com.ebin.fileexp.lite.Item;
import com.ebin.fileexp.lite.ActivityListdirectry.Asy_delete;
import com.ebin.fileexp.lite.ActivityListdirectry.Asy_fold_properties;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Activity_fileexp_settings extends Activity {
	SharedPreferences setting ;
	 int n,dir_rem;
	 String icon_name, bg_colour_main, text_colour;
	 CheckBox ch_h,ch_dir_remb ;
	 
	 ImageView folder_imv,image_main_background,image_menu_background, text_colour_imv;
	 
	 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		 setting = getSharedPreferences("settings", 0);                //retrieve saved value;
		 final SharedPreferences.Editor editor = setting.edit();
		 
		setContentView(R.layout.activity_activity_fileexp_settings);
	
		ch_dir_remb= (CheckBox) findViewById(R.id.CheckBox_remember_dir);
		ch_h = (CheckBox) findViewById(R.id.set_hidden_files);
		folder_imv = (ImageView) findViewById(R.id.set_folder_icon_imgView);
		image_main_background = (ImageView) findViewById(R.id.set_bag_colour_imageButton1);
	
		text_colour_imv = (ImageView) findViewById(R.id.ImageView_text_colour);
		
		LinearLayout lnv = (LinearLayout) findViewById(R.id.set_linearlayout_foldericon);
		LinearLayout set_main_bacground = (LinearLayout) findViewById(R.id.set_linearlayout_background_main_clour);
	
		LinearLayout set_text_colour   = (LinearLayout) findViewById(R.id.set_linearlayout_text_colour);		
		
		
		
		
		//SharedPreferences setting = getSharedPreferences("grid_s", 0);


		 
		 n = setting.getInt("hidden_file_chk", 0);                //default 4       
		 dir_rem = setting.getInt("directory_remember", 0);                //default 4       
		 icon_name = setting.getString("folder_icon", "a_folder_green") ;
		 bg_colour_main = setting.getString("bg_colour_main", "g_black");          
	
        text_colour = setting.getString("set_text_colour", "f_holo_light");
		 
        
        
        
    	RelativeLayout r_gradient = (RelativeLayout) findViewById(R.id.relateve_settings);
	 Slsect_main_gridview_bg obb = new Slsect_main_gridview_bg();
int nn = obb.get_main_linear_layout_bg(bg_colour_main);
r_gradient.setBackgroundDrawable(getResources().getDrawable(nn)); //gradient all effect work
	
		 if(n==1){ ch_h.setChecked(true);}else{ch_h.setChecked(false);}
		 if(dir_rem==1){ ch_dir_remb.setChecked(true);}else{ch_dir_remb.setChecked(false);}

		 
		 
        	String uri1 = "drawable/" + icon_name;
    	int imageResource1 = getApplicationContext().getResources().getIdentifier(uri1, null,getApplicationContext().getPackageName());
    	Drawable image1 = getApplicationContext().getResources().getDrawable(imageResource1);
    folder_imv.setImageDrawable(image1);	 
    
    String uri11 = "drawable/" + bg_colour_main;
	int imageResource11 = getApplicationContext().getResources().getIdentifier(uri11, null,getApplicationContext().getPackageName());
	Drawable image11 = getApplicationContext().getResources().getDrawable(imageResource11);
image_main_background.setImageDrawable(image11);	 

	 
		 

String uri12 = "drawable/" + text_colour;
int imageResource12 = getApplicationContext().getResources().getIdentifier(uri12, null,getApplicationContext().getPackageName());
Drawable image12 = getApplicationContext().getResources().getDrawable(imageResource12);
text_colour_imv.setImageDrawable(image12);	
		 





set_text_colour.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		AlertDialog.Builder ad_m = new AlertDialog.Builder(Activity_fileexp_settings.this,AlertDialog.THEME_HOLO_DARK);
       //ad.setTitle("ttt");
     // ad_m.setTitle("Select Option");
    	final CharSequence[] items = {"Black","White"};
     
    	
    	ad_m.setItems(items, new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int which) {
                      // TODO Auto-generated method stub
          
   		   switch(which){
           	case 0 :    
        	                   	editor.putString("set_text_colour", "f_holo_dark");   /// Saved for next restart
        		                editor.commit();
        		           	String uri = "drawable/" +"f_holo_dark";
        		       	int imageResource = getApplicationContext().getResources().getIdentifier(uri, null,getApplicationContext().getPackageName());
        		       	Drawable image = getApplicationContext().getResources().getDrawable(imageResource);
        		       	text_colour_imv.setImageDrawable(image);
        		                break;
           	case 1:
           		
               	editor.putString("set_text_colour", "f_holo_light");   /// Saved for next restart
                editor.commit();
             	String uri1 = "drawable/" +"f_holo_light";
       	     int imageResource1 = getApplicationContext().getResources().getIdentifier(uri1, null,getApplicationContext().getPackageName());
            	Drawable image1 = getApplicationContext().getResources().getDrawable(imageResource1);
            	text_colour_imv.setImageDrawable(image1);
           		
           		
           		            break;
                      
                      
                                }
                                                                               }
         });ad_m.show();
  
                                                             	}
	                                                                });


ch_dir_remb.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 if(ch_dir_remb.isChecked()){
            	editor.putInt("directory_remember", 1);   /// Saved for next restart
                editor.commit();
			 }else{
		          	editor.putInt("directory_remember", 0);   /// Saved for next restart
	                editor.commit();
	                }
	}
});

ch_h.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 if(ch_h.isChecked()){
            	editor.putInt("hidden_file_chk", 1);   /// Saved for next restart
                editor.commit();
			 }else{
		          	editor.putInt("hidden_file_chk", 0);   /// Saved for next restart
	                editor.commit();
	                }
	}
});





set_main_bacground.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		AlertDialog.Builder ad_m = new AlertDialog.Builder(Activity_fileexp_settings.this,AlertDialog.THEME_HOLO_DARK);
       //ad.setTitle("ttt");
     // ad_m.setTitle("Select Option");
    	final CharSequence[] items = {"Gradient Black", "Gradient Blue","Gradient Brown","Gradient Orange","Gradient Red Circle","Gradient Red ","Gradient Blue Light"};
     
    	
    	ad_m.setItems(items, new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int which) {
                      // TODO Auto-generated method stub
          
   		   switch(which){
           	case 0 :    
        	                   	editor.putString("bg_colour_main", "g_black");   /// Saved for next restart
        		                editor.commit();
        		           	String uri = "drawable/" +"g_black";
        		       	int imageResource = getApplicationContext().getResources().getIdentifier(uri, null,getApplicationContext().getPackageName());
        		       	Drawable image = getApplicationContext().getResources().getDrawable(imageResource);
        		       	image_main_background.setImageDrawable(image);
        		                break;
           	case 1:  
           	                    	editor.putString("bg_colour_main", "g_blue");   /// Saved for next restart
                                    editor.commit();
           	                       String uri1 = "drawable/" +"g_blue";
                                   	int imageResource1 = getApplicationContext().getResources().getIdentifier(uri1, null,getApplicationContext().getPackageName());
       	                         Drawable image1 = getApplicationContext().getResources().getDrawable(imageResource1);
       	                            image_main_background.setImageDrawable(image1);
	                                 break;
           	case 2:  
           	      	editor.putString("bg_colour_main", "g_gold_lite");
                      editor.commit();
     		           	String uri2 = "drawable/" +"g_gold_lite";
        		       	int imageResource2 = getApplicationContext().getResources().getIdentifier(uri2, null,getApplicationContext().getPackageName());
        		       	Drawable image2 = getApplicationContext().getResources().getDrawable(imageResource2);
        		       	image_main_background.setImageDrawable(image2);
                      break;
                      
           	case 3:  
           		        editor.putString("bg_colour_main", "g_orange");
                      editor.commit();
     		           	String uri3 = "drawable/" +"g_orange";
        		       	int imageResource3 = getApplicationContext().getResources().getIdentifier(uri3, null,getApplicationContext().getPackageName());
        		       	Drawable image3 = getApplicationContext().getResources().getDrawable(imageResource3);
        		       	image_main_background.setImageDrawable(image3);
                      break;
                      
           	case 4:  
           		editor.putString("bg_colour_main", "g_red_ball");
                      editor.commit();
     		           	String uri4 = "drawable/" +"g_red_ball";
        		       	int imageResource4 = getApplicationContext().getResources().getIdentifier(uri4, null,getApplicationContext().getPackageName());
        		       	Drawable image4= getApplicationContext().getResources().getDrawable(imageResource4);
        		       	image_main_background.setImageDrawable(image4);
                      break;
                      
           	case 5:  
           		     editor.putString("bg_colour_main", "g_red");
                      editor.commit();
     		           	String uri5 = "drawable/" +"g_red";
        		       	int imageResource5 = getApplicationContext().getResources().getIdentifier(uri5, null,getApplicationContext().getPackageName());
        		       	Drawable image5 = getApplicationContext().getResources().getDrawable(imageResource5);
        		       	image_main_background.setImageDrawable(image5);
                      break;
           	case 6:  
           		editor.putString("bg_colour_main", "g_sky_blue");
                      editor.commit();
     		           	String uri6 = "drawable/" +"g_sky_blue";
        		       	int imageResource6 = getApplicationContext().getResources().getIdentifier(uri6, null,getApplicationContext().getPackageName());
        		       	Drawable image6 = getApplicationContext().getResources().getDrawable(imageResource6);
        		       	image_main_background.setImageDrawable(image6);
                      break;
  

                      
                      
                                }
                                                                               }
         });ad_m.show();
  
                                                             	}
	                                                                });





		 
		 lnv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder ad_m = new AlertDialog.Builder(Activity_fileexp_settings.this,AlertDialog.THEME_HOLO_DARK);
		        //ad.setTitle("ttt");
		      // ad_m.setTitle("Select Option");
		     	final CharSequence[] items = {"Yellow-1","Yellow-2","Blue-1","Green","Purple","Black","White","Rain-Bow","Black-2"};
		      
		     	
		     	ad_m.setItems(items, new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int which) {
		                       // TODO Auto-generated method stub
		           
		    		   switch(which){
		            	case 0 :    
                 	                   	editor.putString("folder_icon", "a_folder_yellow");   /// Saved for next restart
                 		                editor.commit();
                 		           	String uri = "drawable/" +"a_folder_yellow";
                 		       	int imageResource = getApplicationContext().getResources().getIdentifier(uri, null,getApplicationContext().getPackageName());
                 		       	Drawable image = getApplicationContext().getResources().getDrawable(imageResource);
                 		       folder_imv.setImageDrawable(image);
                 		                break;
		            	case 1:  
  	                                 	editor.putString("folder_icon", "a_folder_yellow2");   /// Saved for next restart
  		                                 editor.commit();
  		              		           	String uri1 = "drawable/" +"a_folder_yellow2";
  		                 		       	int imageResource1 = getApplicationContext().getResources().getIdentifier(uri1, null,getApplicationContext().getPackageName());
  		                 		       	Drawable image1 = getApplicationContext().getResources().getDrawable(imageResource1);
  		                 		       folder_imv.setImageDrawable(image1);
  		                                 break;
		            	case 2:  
                           	editor.putString("folder_icon", "a_folder_blue");   /// Saved for next restart
                               editor.commit();
              		           	String uri2 = "drawable/" +"a_folder_blue";
                 		       	int imageResource2 = getApplicationContext().getResources().getIdentifier(uri2, null,getApplicationContext().getPackageName());
                 		       	Drawable image2 = getApplicationContext().getResources().getDrawable(imageResource2);
                 		       folder_imv.setImageDrawable(image2);
                               break;
                               
		            	case 3:  
                           	editor.putString("folder_icon", "a_folder_green");   /// Saved for next restart
                               editor.commit();
              		           	String uri3 = "drawable/" +"a_folder_green";
                 		       	int imageResource3 = getApplicationContext().getResources().getIdentifier(uri3, null,getApplicationContext().getPackageName());
                 		       	Drawable image3 = getApplicationContext().getResources().getDrawable(imageResource3);
                 		       folder_imv.setImageDrawable(image3);
                               break;
                               
		            	case 4:  
                           	editor.putString("folder_icon", "a_folder_purple");   /// Saved for next restart
                               editor.commit();
              		           	String uri4 = "drawable/" +"a_folder_purple";
                 		       	int imageResource4 = getApplicationContext().getResources().getIdentifier(uri4, null,getApplicationContext().getPackageName());
                 		       	Drawable image4= getApplicationContext().getResources().getDrawable(imageResource4);
                 		       folder_imv.setImageDrawable(image4);
                               break;
                               
		            	case 5:  
                           	editor.putString("folder_icon", "a_folder_black");   /// Saved for next restart
                               editor.commit();
              		           	String uri5 = "drawable/" +"a_folder_black";
                 		       	int imageResource5 = getApplicationContext().getResources().getIdentifier(uri5, null,getApplicationContext().getPackageName());
                 		       	Drawable image5 = getApplicationContext().getResources().getDrawable(imageResource5);
                 		       folder_imv.setImageDrawable(image5);
                               break;
		            	case 6:  
                           	editor.putString("folder_icon", "a_folder_white");   /// Saved for next restart
                               editor.commit();
              		           	String uri6 = "drawable/" +"a_folder_white";
                 		       	int imageResource6 = getApplicationContext().getResources().getIdentifier(uri6, null,getApplicationContext().getPackageName());
                 		       	Drawable image6 = getApplicationContext().getResources().getDrawable(imageResource6);
                 		       folder_imv.setImageDrawable(image6);
                               break;
		            	case 7:  
                           	editor.putString("folder_icon", "a_folder_rain");   /// Saved for next restart
                               editor.commit();
              		           	String uri7 = "drawable/" +"a_folder_rain";
                 		       	int imageResource7 = getApplicationContext().getResources().getIdentifier(uri7, null,getApplicationContext().getPackageName());
                 		       	Drawable image7 = getApplicationContext().getResources().getDrawable(imageResource7);
                 		       folder_imv.setImageDrawable(image7);
                               break;
		            	case 8:  
                           	editor.putString("folder_icon", "a_folder_black2");   /// Saved for next restart
                               editor.commit();
              		           	String uri8 = "drawable/" +"a_folder_black2";
                 		       	int imageResource8 = getApplicationContext().getResources().getIdentifier(uri8, null,getApplicationContext().getPackageName());
                 		       	Drawable image8 = getApplicationContext().getResources().getDrawable(imageResource8);
                 		       folder_imv.setImageDrawable(image8);
                               break;                              
                                         }
		                                                                                }
		          });ad_m.show();
		   
	                                                                  	}
	     	                                                                });
                	
	
	
	}


}
