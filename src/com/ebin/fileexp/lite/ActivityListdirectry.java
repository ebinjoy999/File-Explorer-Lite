package com.ebin.fileexp.lite;


// copy
//mov   //del
//copy in same fol der error
//show is t to cancel current operation on long click aftr copy or move
//new folder 
//search
//intent open any file
//Add grid remember

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.Inflater;

import com.dark.explorer.lite.ebinjoy.R;
import com.ebin.fileexp.lite.a.Slsect_main_gridview_bg;
import com.ebin.fileexp.lite.image.viewer.Utils;



import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources.Theme;
import android.database.CursorJoiner.Result;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.media.audiofx.BassBoost.Settings;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ResourceAsColor") public class ActivityListdirectry extends Activity implements OnItemClickListener,OnClickListener, OnItemLongClickListener {
	
	RelativeLayout relative_la_bg;
	int exit1 =1, dir_remb;
	SharedPreferences grid, setting;
	 View child;
	String f_n = "";
	ProgressDialog kk;	 AlertDialog.Builder ad_m;
	 Image_load_thread im = new Image_load_thread();
	boolean copy_status=false, move_status=false;
	int f = 0,selected_s;
	String selsected_str;	
	List<Item> dirr_returned_selected ;
	LinearLayout  cloud_bar;
	boolean chk_visible = false;
	GridView gd;
	private File currentDir;
    private Adapter_main_grid_FileArray adapter;
    String it[];
	FrameLayout f_grid,f_list;
	CheckBox ch;
	TextView copy_place, move_place;
	EditText s_et ;
	ImageView grid_num ,refresh,cloud,search,settings;
ImageView delete, copy, move, rename, exit, menu;
ImageView home;
FrameLayout ckfm ;
LinearLayout f_gd, f_lv;
TextView t_path,menu_bottum_delete,menu_bottum_copy,menu_bottum_move,menu_bottum_reneme,menu_bottum_exit,menu_bottum_more;

ListView lv,lv_search_result ;

List<Item> dir;
List<Item> s_fils = new ArrayList<Item>(); 
LinearLayout lmenu;
LinearLayout srch_bar;
	



	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
         setContentView(R.layout.fexplorer_activity_listdirectry);
         
         menu_bottum_delete = (TextView) findViewById(R.id.textView_menu);
         menu_bottum_copy = (TextView) findViewById(R.id.text_copy);
         menu_bottum_move = (TextView) findViewById(R.id.text_move);
         menu_bottum_reneme = (TextView) findViewById(R.id.textView6);
         menu_bottum_exit = (TextView) findViewById(R.id.textView4);
         menu_bottum_more = (TextView) findViewById(R.id.textView5);
         
			SharedPreferences setting= getSharedPreferences("settings", 0);      
			 final SharedPreferences.Editor editor = setting.edit();
			
			 dir_remb = setting.getInt("directory_remember", 0);                //default 4   
			 
		       String colour = setting.getString("set_text_colour", "f_holo_light");
	

			if(colour.equals("f_holo_dark"))
			{
				menu_bottum_delete.setTextColor(Color.parseColor("#000000"));
		         menu_bottum_copy.setTextColor(Color.parseColor("#000000"));
		         menu_bottum_move.setTextColor(Color.parseColor("#000000"));
		         menu_bottum_reneme.setTextColor(Color.parseColor("#000000"));
		         menu_bottum_exit.setTextColor(Color.parseColor("#000000"));
		         menu_bottum_more.setTextColor(Color.parseColor("#000000"));
			}else if(colour.equals("f_holo_light")){
				menu_bottum_delete.setTextColor(Color.parseColor("#ffffff"));
		         menu_bottum_copy.setTextColor(Color.parseColor("#ffffff"));
		         menu_bottum_move.setTextColor(Color.parseColor("#ffffff"));
		         menu_bottum_reneme.setTextColor(Color.parseColor("#ffffff"));
		         menu_bottum_exit.setTextColor(Color.parseColor("#ffffff"));
		         menu_bottum_more.setTextColor(Color.parseColor("#ffffff"));
				
				
			    }
		

		
		relative_la_bg = (RelativeLayout) findViewById(R.id.sadasdasd);
		
	  gd=(GridView)findViewById(R.id.gv_folders);
     	lv = (ListView) findViewById(R.id.menu_list1);
		lv_search_result =  (ListView) findViewById(R.id.listView_search);
		
		f_gd = (LinearLayout) findViewById(R.id.grid_frame);
		f_lv = (LinearLayout) findViewById(R.id.farme_lisView_search);

		
		//SharedPreferences setting = getSharedPreferences("grid_s", 0);
		 grid = getSharedPreferences("grid_s", 0);                //retrieve saved value;
		 int n = grid.getInt("grid_row", 4);                //default 4       
	    gd.setNumColumns(n);
		
		copy_place = (TextView) findViewById(R.id.text_copy);
		move_place = (TextView) findViewById(R.id.text_move);
		
		cloud_bar = (LinearLayout)findViewById(R.id.cloud_or_menu_view);                
        //View child_cloud = getLayoutInflater().inflate(R.layout.fexplorer_cloud_bar, null);
    //  cloud_bar.addView(child_cloud);
      cloud_bar.setVisibility(View.INVISIBLE);
       
		lmenu = (LinearLayout)findViewById(R.id.ll_menus);
	   srch_bar = (LinearLayout)findViewById(R.id.srch1);
		
		
		
		home = (ImageView) findViewById(R.id.imageHome);
		home.setOnClickListener(this);
	    home.setBackgroundResource(R.drawable.imv_toggle_home);

		   
        grid_num = (ImageView) findViewById(R.id.imagegrid);
        grid_num.setOnClickListener(this);
        grid_num.setBackgroundResource(R.drawable.im_toggle_grid);
        
        refresh = (ImageView) findViewById(R.id.imagerefreash);
        refresh.setOnClickListener(this);
        refresh.setBackgroundResource(R.drawable.im_toggle_refresh);
        
        cloud = (ImageView) findViewById(R.id.imageCloud);
        cloud.setOnClickListener(this);
        cloud.setBackgroundResource(R.drawable.im_toggle_newfolder);
        
        search = (ImageView) findViewById(R.id.imagesearch);
        search.setOnClickListener(this);
        search.setBackgroundResource(R.drawable.im_toggle_search);
        
        settings = (ImageView) findViewById(R.id.imageSettings);
        settings.setOnClickListener(this);
        settings.setBackgroundResource(R.drawable.im_toggle_share);
       
        delete = (ImageView) findViewById(R.id.imageDelete);
        delete.setOnClickListener(this);
        delete.setBackgroundResource(R.drawable.im_toggle_delete);
        
        copy = (ImageView) findViewById(R.id.imageCopy);
        copy.setOnClickListener(this);
        move = (ImageView) findViewById(R.id.imageMove);
        move.setOnClickListener(this);
        
        
        rename = (ImageView) findViewById(R.id.imageRename);
        rename.setOnClickListener(this);
        rename.setBackgroundResource(R.drawable.im_toggle_rename);
        
        exit = (ImageView) findViewById(R.id.imageExit);
		exit.setOnClickListener(this);
		exit.setBackgroundResource(R.drawable.im_toggle_exit);
		
		 menu = (ImageView) findViewById(R.id.imageMenu);
	     menu.setOnClickListener(this);
	     menu.setBackgroundResource(R.drawable.im_toggle_more);
	     
	     t_path = (TextView) findViewById(R.id.tpath);
	     
//  
//	   LayoutInflater inf = getLayoutInflater();
//	   View v = inf.inflate(R.layout.file_view_list, null);
	     

    LayoutInflater li = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)  ;
 View v = li.inflate(R.layout.fexplorer_file_view_list, null);

	String	 bg_colour_main1 = setting.getString("bg_colour_main", "g_black");       
	if(!bg_colour_main1.equals("g_red")){
Slsect_main_gridview_bg obb = new Slsect_main_gridview_bg();
int nn = obb.get_main_linear_layout_bg(bg_colour_main1);
cloud_bar.setBackgroundDrawable(getResources().getDrawable(nn)); //gradient all effect work
	}else{cloud_bar.setBackgroundDrawable(getResources().getDrawable(R.drawable.gradient_un));  
	//gradient all effect work}
	}
	     
	     
	     
	 	//ckfm = (FrameLayout)v.findViewById(R.id.fame_chk_hide);
	     //try{
	    	//ch.setVisibility(View.INVISIBLE) ;
	    	 
	    // ch.setVisibility(View.GONE);
	 //    }catch(Exception exc){
	    	 
	  //   }
        gd.setOnItemClickListener(this);
		gd.setOnItemLongClickListener(this);
		

if(dir_remb==1){
	currentDir = new File(setting.getString("Remembered_dir", "/sdcard"));     
}else{
		currentDir = new File("/sdcard");
}
		
		
		
//		if(currentDir.isDirectory()){
//			
//		}else{
//			
//			currentDir = new File("/mnt");
//		}



		
      //  f_list.setVisibility(View.GONE);
//        f_list.setVisibility(View.VISIBLE);

		
	    cloud_bar.setVisibility(View.INVISIBLE); 
	    f_gd.setVisibility(View.VISIBLE); 
		f_lv.setVisibility(View.INVISIBLE); 
		srch_bar.setVisibility(View.INVISIBLE);          //Intially set to synchronise onBackpressed
   	 srch_bar.removeView(child);
         lmenu.setVisibility(View.VISIBLE);
         fill(currentDir); 
	}

	

	
	///////////////////////////////////////////       FILL               ////////////////////////////////////////////////////////////////
	
	
	@SuppressWarnings("deprecation")
	private void fill(File f) {
		// TODO Auto-generated method stub
//		Process p = null ;
//		try {
//			p = Runtime.getRuntime().exec("su -c" + " ls /data");
//		//	 DataOutputStream dos = new DataOutputStream(p.getOutputStream());
//			 BufferedReader dos = new BufferedReader(new InputStreamReader(p.getInputStream()));
//	      Toast.makeText(getApplicationContext(), dos.toString(), Toast.LENGTH_LONG).show();
//	        // dos.flush();
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}


        


		 setting = getSharedPreferences("settings", 0); 
		 final SharedPreferences.Editor editor = setting.edit();
     	editor.putString("Remembered_dir", currentDir.toString());   /// Saved for next restart
        editor.commit();
        
	String  bg_colour_main = setting.getString("bg_colour_main", "g_black");          
Slsect_main_gridview_bg obb = new Slsect_main_gridview_bg();
	 int n = obb.get_main_linear_layout_bg(bg_colour_main);
	 relative_la_bg.setBackgroundDrawable(getResources().getDrawable(n)); //gradient all effect work
			
//	String uri = "@drawable/"+ bg_colour_main;
//	int gradent_res = getResources().getIdentifier(uri, null, getPackageName());
//Drawable res = getResources().getDrawable(gradent_res);
////relative_la_bg.setBackgroundDrawable(res);                 //Not work padding
	

		 
		 
//			String  bg_colour_menu = setting.getString("bg_colour_menu", "f_holo_dark");          
//				 int nn = obb.get_main_linear_layout_bg(bg_colour_menu);
//				 LinearLayout menu_top= (LinearLayout) findViewById(R.id.ll_menus);
//				 LinearLayout menu_buttom= (LinearLayout) findViewById(R.id.linearLayout1);
//					menu_top.setBackgroundColor(getResources().getColor(nn));
//					menu_buttom.setBackgroundColor(getResources().getColor(nn));

		
		 
		exit1 =1;
		
		File[]dirs = f.listFiles(); 
    	//listfiles()  - Returns an array of files contained in the directory represented by this file. 
    	//The result is null if this file is not a directory.
    	
    //	The actual file referenced by a File may or may not exist. It may also, despite the name File, be a directory or other non-regular file. 
   //This class provides limited functionality for getting/setting file permissions, file type, and last modified time. 

		 this.setTitle("Current Dir: "+f.getName()); //Cange tittle of current activity. Parent can change when want.

		 dir = new ArrayList<Item>();
		 List<Item>fls = new ArrayList<Item>(); 
		 
//		 A List is a collection which maintains an ordering for its elements. Every element in the List has an index. 
//		 Each element can thus be accessed by its index, with the first index being zero.
//		 Normally, Lists allow duplicate elements, as compared to Sets, where elements have to be unique
		
		 try{
			 for(File ff: dirs) //iterating over a list or an array. // for(declaration : expression) { }
				 //declaration - newly declared block variable. should compatible with array type access.
				 //its value will be available for current for block.Valu would be same as current array element.
				 //Expression - can array variable or method call return array. This loop through the array.
			 { 
				Date lastModDate = new Date(ff.lastModified()); 
				
				DateFormat formater = DateFormat.getDateTimeInstance();
				String date_modify = formater.format(lastModDate);
				
				if(ff.isDirectory()){
					
					
					File[] fbuf = ff.listFiles(); 
					int buf = 0;
					             if(fbuf != null){ 
						               buf = fbuf.length;
					                                      } 
					                             else buf = 0; 
					String num_item = String.valueOf(buf);
			
					if(buf == 0) num_item = num_item + " item";
					else num_item = num_item + " items";
					
					//String formated = lastModDate.toString();
					
					 setting = getSharedPreferences("settings", 0);  
					 String icon_name = setting.getString("folder_icon", "a_folder_white") ;
					 int n_c = setting.getInt("hidden_file_chk", 0);                
					 if(n_c==1){
					dir.add(new Item(ff.getName(),num_item,date_modify,ff.getAbsolutePath(),icon_name,0)); 
					 }else{
						       char[] ss =  ff.getName().toCharArray();
						 if(ss[0]=='.'){    }else{dir.add(new Item(ff.getName(),num_item,date_modify,ff.getAbsolutePath(),icon_name,0)); }
					 }
				}
				else
				{
					
					//Toast.makeText(getBaseContext(),""+ff.getName(), 10).show();
	
					//Toast.makeText(getBaseContext(),""+it[it.length-1].toString(), 10).show();
					
				String temp_name = ff.getName();
				Check_filetypes ch_fl = new Check_filetypes();
				String file_icon_name = ch_fl.find_extention(temp_name);
			  
				Dir_size_Human_Redable newobj = new Dir_size_Human_Redable();
				long f_size= ff.length();
	        	 	String ss = newobj.humanReadableByteCount(f_size, false);
					 int n_c = setting.getInt("hidden_file_chk", 0);                
					 if(n_c==1){
		   fls.add(new Item(ff.getName(),ss, date_modify, ff.getAbsolutePath(),file_icon_name,0));
					 }else{
					       char[] sss =  ff.getName().toCharArray();
					 if(sss[0]=='.'){    }else{ fls.add(new Item(ff.getName(),ss, date_modify, ff.getAbsolutePath(),file_icon_name,0));}
				 }
			
					}
				
				 
			 }
		 }catch(Exception e)
		 {    
			 
		 }
		 
		 
		 
		 
		 
		 Collections.sort(dir);
		 Collections.sort(fls);
		 // Collections contains static methods which operate on Collection classes
		 dir.addAll(fls);
		
		 
		 
		// if(!f.getName().equalsIgnoreCase(""))
		//	 dir.add(0,new Item("","","",f.getParent(),"directory_up",0));
		
		// public String getName () - Returns the name of the file or directory represented by this file.
		// public boolean equalsIgnoreCase (String string) 
		 //Compares the specified string to this string ignoring the case of the characters and returns true if they are equal.


		 adapter = new Adapter_main_grid_FileArray(ActivityListdirectry.this,R.layout.fexplorer_file_view_list,dir,chk_visible,-1);
		
  gd.setAdapter(adapter); 
	t_path.setText(currentDir.getPath().toString()); ////////
  
  
 }
  
		 //public void setListAdapter (ListAdapter adapter) - 	 Provide the cursor for the list view.

    
	
	@Override
	
	
	
	//////////////////////////////////////////           BACK PREASSED         ////////////////////////////////////////////////////////
	
	
	
	public void onBackPressed() {

if(chk_visible == true){
		   
			chk_visible = false;
		//fill(currentDir);
        	int index = gd.getFirstVisiblePosition();   //Finding position of grid View
       adapter = new Adapter_main_grid_FileArray(ActivityListdirectry.this,R.layout.fexplorer_file_view_list,dir,chk_visible,-1);
		 gd.setAdapter(adapter); 
	     	gd.setSelection(index);              // Setting that position back
		//super.onBackPressed();
		 
}else if(f_lv.isShown()){
			
			f_gd.setVisibility(View.VISIBLE); 
		f_lv.setVisibility(View.INVISIBLE); 
		
}else if(cloud_bar.isShown()){
		    
	cloud_bar.setVisibility(View.INVISIBLE); 
	
}else if(srch_bar.isShown()){
        	
	         srch_bar.setVisibility(View.INVISIBLE);
        	 srch_bar.removeView(child);
	         lmenu.setVisibility(View.VISIBLE);
		}
		
		//if(!((srch_bar.isShown())||(cloud_bar.isShown())||(f_lv.isShown())||(chk_visible == true))){
else {	
	
	String p_cur = currentDir.getParent();
	if(p_cur!=null){
		
	chk_visible = false;
				currentDir = new File(p_cur);
				fill(currentDir);
	      }else{exit1++; if(exit1==3){this.finish();} if(exit1==2) Toast.makeText(getApplicationContext(), "Press back to EXIT", Toast.LENGTH_LONG).show();} }
			
	
		
		
	
	
		
	}
	
	
	
	
	////////////////////////////////////         ON ITEM CLICK                  /////////////////////////////////////////////////////////////////////////


	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	
	if(im.getStatus()==AsyncTask.Status.RUNNING){ 
		im.cancel(true);
	}

		
	
	
		Item o = adapter.getItem(arg2);
		 String icon_name = setting.getString("folder_icon", "a_folder_white") ;
		if(o.getImage().equalsIgnoreCase(icon_name)){
				currentDir = new File(o.getPath());
				fill(currentDir);
		}else if(o.getImage().equalsIgnoreCase("directory_up")){
			
			chk_visible = false;
			currentDir = new File(o.getPath());
			fill(currentDir);
		}else if(o.getImage().equalsIgnoreCase("a_image")){  //Starting Image Viewer
		
		Bundle image_native_bu = new Bundle();
	Intent i_native_image = new Intent("com.image.viewer.FullScreenViewActivity");
	image_native_bu.putString("image_native_bu", ""+o.getPath());
	i_native_image.putExtras(image_native_bu);
	startActivity(i_native_image);
		}else if(o.getImage().equalsIgnoreCase("a_music")){  //Starting mp3 Viewer
		
		Bundle image_native_music = new Bundle();
	Intent i_native_music = new Intent("musicplayer.AndroidBuildingMusicPlayerActivity");
	image_native_music.putString("image_native_music", ""+o.getPath());
	i_native_music.putExtras(image_native_music);
	startActivity(i_native_music);
		}else if(o.getImage().equalsIgnoreCase("a_video")){  //Starting video Viewer
			
			Bundle image_native_video = new Bundle();
		Intent i_native_video = new Intent("com.ebin.fileexp.lite.vedio.player.VideoPlayerActivity");
		image_native_video.putString("image_native_video", ""+o.getPath());
		i_native_video.putExtras(image_native_video);
		startActivity(i_native_video);
			}else{
			
			String[] it=o.getName().split("\\.");
			String ext=it[it.length-1].toString();
			
			File sys_open = new File(o.getPath());
		
			try {
				FileOpen.openFile(getApplicationContext(), sys_open);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}

	}








///////////////////////////////////////////           ON  CLICK         /////////////////////////////////////////////////////////////////////
	 
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
//		ImageButton grid_num, home ,refresh,cloud,search,settings;
//		ImageButton delete, copy, move, rename, exit, menu;
		switch(v.getId()){
		
		case R.id.imagegrid: 	  grid_num.setBackgroundResource(R.drawable.im_toggle_grid);
			
			                                 
			                                     // TODO Auto-generated method stub
			                                     AlertDialog.Builder ad = new AlertDialog.Builder(ActivityListdirectry.this,AlertDialog.THEME_HOLO_DARK);
				                                 //ad.setTitle("ttt");
		                                        ad.setTitle("Select number of Column");
		                                      	final CharSequence[] items = {"2","3","4","5","6","8","10","12"};
			                                    ad.setItems(items, new DialogInterface.OnClickListener() {
			                                         public void onClick(DialogInterface dialog, int which) {
					                                            // TODO Auto-generated method stub
					                                         String toast = Integer.toString(which);
					                                         which = which +2;
					                                 	
					                                         //SharedPreferences setting = getSharedPreferences("grid_s", 0);
					                                 		grid = getSharedPreferences("grid_s", 0);
					                                 		SharedPreferences.Editor editor = grid.edit();
					                                 		
					                                 		editor.putInt("grid_row", which);   /// Saved for next restart
					                                 		editor.commit();
					                                 		
					                                 		
                                                             gd.setNumColumns(which);
                                                                                       }
			                                       });ad.show();
			                                    break;
			                                    
		case R.id.imageHome:         home.setBackgroundResource(R.drawable.imv_toggle_home);
			  chk_visible=false; currentDir = new File("/storage");
		                                                    if(currentDir.isDirectory()){
			
	                                                	}else{
			
			                                                   currentDir = new File("/mnt");
		                                                        }
                                                             fill(currentDir); 
                                                 break;
			
		case R.id.imagerefreash:  	fill(currentDir);
		                                               break;
			
		case R.id.imageCloud:	        //new folder
                                                 	//  LinearLayout lmenu = (LinearLayout)findViewById(R.id.ll_menus);
                                                   //    lmenu.setVisibility(View.INVISIBLE);
                                       
                                                  //  cloud_bar.animate();
                                        /*         if(cloud_bar.isShown())
                                                 {  cloud_bar.setVisibility(View.INVISIBLE); }
                                                 else  {cloud_bar.setVisibility(View.VISIBLE);
                                                      
                                                 }
                                                     */
			          
			          
			          AlertDialog.Builder  alert1 = new AlertDialog.Builder(ActivityListdirectry.this,AlertDialog.THEME_HOLO_DARK);
               	   alert1 .setTitle("Enter a new folder name");
               	    //alert.setMessage("");
               	    
               	    final EditText  fname = new EditText(this);  
               	      fname.setText("New folder");
               	      fname.setTextColor(Color.parseColor("#000000"));
               	    alert1.setView(fname);
              
               	   alert1 .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
               
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
						    Editable value = fname.getText();
                           f_n= value.toString();
                           
                           String folder = currentDir.toString();
 			              File folderr = new File(folder+"/"+f_n);
 			              boolean succes = false;
 			        if(!folderr.exists())  {     succes = folderr.mkdir(); } else { Toast.makeText(getApplicationContext(), "Destination Already Contain Same Folder", Toast.LENGTH_LONG).show(); }
 			              if(!succes) Toast.makeText(getApplicationContext(), "Error occured", Toast.LENGTH_LONG).show();  ;
                          
	              chk_visible = false;
	              fill(currentDir);
 			              
						}}).show();
		
                        
                
                                                          break;
			
			                               
			
		case R.id.imagesearch:      
		                                       	srch_bar.destroyDrawingCache();
			                                    srch_bar = (LinearLayout)findViewById(R.id.srch1);                
		                                            child = getLayoutInflater().inflate(R.layout.fexplorer_search_bar, null);
		                                           srch_bar.addView(child);
	                                             	  final LinearLayout lmenu = (LinearLayout)findViewById(R.id.ll_menus);
		                                             lmenu.setVisibility(View.INVISIBLE);
		                                             srch_bar.setVisibility(View.VISIBLE);
		                                             
		                                             s_et = (EditText) child.findViewById(R.id.tx11);
		                                             ImageView im_s = (ImageView) child.findViewById(R.id.ims);
		                                             im_s.setOnClickListener(new OnClickListener() {
														
														@Override
														public void onClick(View v) {
															// TODO Auto-generated method stub
													         String search_item = "";
													         s_fils = new ArrayList<Item>();
													         search_item = s_et.getText().toString();

													         if(!search_item.equals("")){
													        // Toast.makeText(getApplicationContext(), search_item, Toast.LENGTH_SHORT).show();
													      srch_bar.removeView(child);
													      lmenu.setVisibility(View.VISIBLE);
													      
													      File[]  s_res = currentDir.listFiles();
															// List<Item> s_fils = new ArrayList<Item>();  made global
											
																Check_filetypes ch_fl = new Check_filetypes();
										
																
																for(File s_f : s_res){
												                                         if(s_f.isFile()&&s_f.getName().toUpperCase().contains(search_item.toUpperCase())&&search_item!=""){
												                            	String temp_name = s_f.getName();
													                            String icon_name = ch_fl.find_extention(temp_name);
											                                    s_fils.add(new Item(s_f.getName(),s_f.length() + " Byte","", s_f.getAbsolutePath(),icon_name,0));
											             	                                             }
											                                                        }
																
																
																
						if(s_fils.size()!=0){										
																//gd.setVisibility(View.GONE);
															
																Adapter_saerch_result  are_search_result   ;
											
												are_search_result = new Adapter_saerch_result(getApplicationContext(), android.R.layout.simple_list_item_1, s_fils);
											
											
												lv_search_result.setAdapter(are_search_result);

												f_gd.setVisibility(View.INVISIBLE); 
												f_lv.setVisibility(View.VISIBLE);
												//lv_search_result.setVisibility(View.VISIBLE);
					
												// Hiding software after enter
												 InputMethodManager imm = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
												 imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,0);
												 
								ListView search_lv = (ListView) findViewById(R.id.listView_search);
								search_lv.setOnItemClickListener(new OnItemClickListener() {

									@Override
									public void onItemClick(
											AdapterView<?> arg0, View arg1,int arg2, long arg3) {
										// TODO Auto-generated method stub
										File serach_result_click = new File(s_fils.get(arg2).getPath());
                               	try {
	                        				FileOpen.openFile(getApplicationContext(), serach_result_click);    //Clicked on the Search result
	                        			} catch (IOException e) {
	                        				// TODO Auto-generated catch block
	                        				e.printStackTrace();
	                        			}
										
									}
								});
						                                }else{ Toast.makeText(getApplicationContext(), "OOPs!  No Match Found in this Folder", Toast.LENGTH_LONG).show();  InputMethodManager imm = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
														 imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,0);}
														       }
													         
														}
													});
		                                             
		                                    
		                                             
                                                     break;
			
		case R.id.imageSettings:    //sharehg
		
//			List<Image_thumbnail> image_thumb_r ; ///////////////////////////////////////////////////////////////////////////
//        image_thumb_r = adapter.image_thumbnail();
//       
//        
//        
//     // im.execute(image_thumb_r);
//			//Asynchronous task can exicute on ly once as above...
//           // So we create new instance
//	
//        
//		new Image_load_thread().cancel(true);
//			new Image_load_thread().execute(image_thumb_r);
		                                 //Above for load image thumbnails.
                                                     dirr_returned_selected = adapter.select_returned();
                                                      if( dirr_returned_selected.isEmpty()){
           	 
                                                    	  AlertDialog.Builder addd = new AlertDialog.Builder(ActivityListdirectry.this);
                                                  		//ad.setTitle("ttt");
                                                  		    addd.setMessage("First select any FILE to share ");

                                                  		    	addd.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                                                  		      public void onClick(DialogInterface dialog, int arg1) {
                                                  		    			// TODO Auto-generated method stub
                                                  				System.out.println();
                                                  		    		}
                                                  		    	} );
                                                  		    	addd.show();
                                                  		    	
                                                  		  	chk_visible = true;
                            	                              //fill(currentDir);
                                  	                   	
                                  	                   	int index = gd.getFirstVisiblePosition();   //Finding position of grid View
                                  	          
                                  	                   	//View position_v = gd.getChildAt(0);
                                  	                  // 	int top = (position_v==null) ? 0 : (position_v.getTop()-gd.getPaddingTop());
                                  	                   	
                            	                                adapter = new Adapter_main_grid_FileArray(ActivityListdirectry.this,R.layout.fexplorer_file_view_list,dir,chk_visible,-1);
                            	                                //-1 meance invalid
                            	                               gd.setAdapter(adapter); 
                            	                               
                            	                             try{  	gd.setSelection(index);    }catch(Exception exc){}          // Setting that position back
                            	                               
                                                       }else{
                                                    	   File sh_of;
                                                    	   boolean dir = true;
                                                    	   ArrayList<Uri> uri =new ArrayList<Uri>();
                                                    	   
                                                    	   
                                                    	  for(int i = 0 ;i< dirr_returned_selected.size();i++){ 
                                                    		  Item sh_o = dirr_returned_selected.get(i);
                                                    	    sh_of = new File(sh_o.getPath());
                                                    		  if(sh_of.isDirectory()){ dir = false; Toast.makeText(getApplicationContext(), "Only Files can Share!", Toast.LENGTH_LONG).show();break;}
                                                              uri.add(Uri.fromFile(sh_of)); 
                                                    	     }
                                                    	  
                                                    	if(dir) { Intent intent = new Intent();
                                                    	  intent.setAction(Intent.ACTION_SEND_MULTIPLE);
                                                    	  intent.setType("*/*");
                                                    	  Toast.makeText(getApplicationContext(), "Select a suitable Medium for Share", Toast.LENGTH_LONG).show();
                                                    	  intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uri);
                                                    	  startActivity(intent);
                                                           	}
                                                       }

			                                              break;
			
		case R.id.imageDelete:      delete.setBackgroundResource(R.drawable.im_toggle_delete);
			                                                        dirr_returned_selected = adapter.select_returned();
			                                             if( dirr_returned_selected.isEmpty()){
			                                            	 
			                                           	  AlertDialog.Builder addd = new AlertDialog.Builder(ActivityListdirectry.this);
	                                                  		//ad.setTitle("ttt");
	                                                  		    addd.setMessage("First select any FILE to Delete ");

	                                                  		    	addd.setPositiveButton("OK",new DialogInterface.OnClickListener() {
	                                                  		      public void onClick(DialogInterface dialog, int arg1) {
	                                                  		    			// TODO Auto-generated method stub
	                                                  				System.out.println();
	                                                  		    		}
	                                                  		    	} );
	                                                  		    	addd.show();
	                                                  		    	
	                                                  		  	chk_visible = true;
	                            	                              //fill(currentDir);
	                                  	                   	
	                                  	                   	int index = gd.getFirstVisiblePosition();   //Finding position of grid View
	                                  	          
	                                  	                   	//View position_v = gd.getChildAt(0);
	                                  	                  // 	int top = (position_v==null) ? 0 : (position_v.getTop()-gd.getPaddingTop());
	                                  	                   	
	                            	                                adapter = new Adapter_main_grid_FileArray(ActivityListdirectry.this,R.layout.fexplorer_file_view_list,dir,chk_visible,-1);
	                            	                                //-1 meance invalid
	                            	                               gd.setAdapter(adapter); 
	                            	                               
	                            	                             try{  	gd.setSelection(index);    }catch(Exception exc){}          // Setting that position back
	                            	                               
	                                                 
			                                             }else{
			                                            	 
			                                            	 
	                                                       selected_s = dirr_returned_selected.size();
                                                          selsected_str = Integer.toString(selected_s);
                                                           Toast.makeText(getApplicationContext(), "SIZE  "+selsected_str, Toast.LENGTH_LONG).show(); 
      
                                                           AlertDialog.Builder  alert = new AlertDialog.Builder(ActivityListdirectry.this,AlertDialog.THEME_HOLO_DARK);
                                                    	  // alert .setTitle("Enter a new name");
                                                    	    alert.setMessage("Are you Sure to Delete (Can't Undo)?");
                                                    	    
                                                    	 //   final EditText  rname = new EditText(this); 
                                                    	    //alert.setView(rname);
                                                    	    
                                                    	   alert .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                    	        public void onClick(DialogInterface dialog, int whichButton) {

                                                    	        	//   kk = ProgressDialog.show(ActivityListdirectry.this, "","Deleting...", true);  //start progress bar
                                                    	        	
                                                                   Asy_delete kk_d = new Asy_delete(); 
                                                                  kk_d.execute(dirr_returned_selected);//Asynchronous task starts    
                                                                   
                                                                 
                                                                  chk_visible = false;
                                                            
                                                    	        }
                                                    	    });
                                                    	    
                                                    	   alert .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                    	        public void onClick(DialogInterface dialog, int whichButton) {
                                                    	            // Do nothing.
                                                    	        }
                                                    	    }).show();
                                                           
                                                           
                                                    	 
			
			                                             }
			                                                          break;
			
			
		case R.id.imageCopy:  			      dirr_returned_selected = adapter.select_returned();
                                                          if( dirr_returned_selected.isEmpty()){
       	 
                                                        	  AlertDialog.Builder addd = new AlertDialog.Builder(ActivityListdirectry.this);
                                                        		//ad.setTitle("ttt");
                                                        		    addd.setMessage("First select any FILE to copy ");

                                                        		    	addd.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                                                        		      public void onClick(DialogInterface dialog, int arg1) {
                                                        		    			// TODO Auto-generated method stub
                                                        				System.out.println();
                                                        		    		}
                                                        		    	} );
                                                        		    	addd.show();
                                                        		    	
                                                        		  	chk_visible = true;
                                  	                              //fill(currentDir);
                                        	                   	
                                        	                   	int index = gd.getFirstVisiblePosition();   //Finding position of grid View
                                        	          
                                        	                   	//View position_v = gd.getChildAt(0);
                                        	                  // 	int top = (position_v==null) ? 0 : (position_v.getTop()-gd.getPaddingTop());
                                        	                   	
                                  	                                adapter = new Adapter_main_grid_FileArray(ActivityListdirectry.this,R.layout.fexplorer_file_view_list,dir,chk_visible,-1);
                                  	                                //-1 meance invalid
                                  	                               gd.setAdapter(adapter); 
                                  	                               
                                  	                             try{  	gd.setSelection(index);    }catch(Exception exc){}          // Setting that position back
                                  	                               
                                                          }else{
			
			
			
			                                                       String uri_move_paste1 = "drawable/" +"c_paste";
	                                                        	int image_move1= getApplicationContext().getResources().getIdentifier(uri_move_paste1, null,getApplicationContext().getPackageName());
		                                                        Drawable image_move_paste1 = getApplicationContext().getResources().getDrawable(image_move1);
		                                                         move.setImageDrawable(image_move_paste1);
		                                                         move_place.setText("Paste");
		                                                         move.setId(500);
		
	                                                         	String uri_copy_cancel1 = "drawable/" +"c_cancel";
		                                                       int imageResource_cancel1 = getApplicationContext().getResources().getIdentifier(uri_copy_cancel1, null,getApplicationContext().getPackageName());
		                                                       Drawable image_copy_cancel1 = getApplicationContext().getResources().getDrawable(imageResource_cancel1);
		                                                        copy.setImageDrawable(image_copy_cancel1);
		                                                       copy_place.setText("Cancel");
			                                                  copy.setId(501);
			                                                  
			                                                  copy_status = true;
			                                                  chk_visible =false;


			                                                  int index = gd.getFirstVisiblePosition();   //Finding position of grid View
			                                                  fill(currentDir); //refresh ffgf
			                                               
			                                             adapter = new Adapter_main_grid_FileArray(ActivityListdirectry.this,R.layout.fexplorer_file_view_list,dir,chk_visible,-1);
			                                          	 gd.setAdapter(adapter); 
			                                             try{  	gd.setSelection(index);    }catch(Exception exc){}          // Setting that position back
			                                             
			  			                         ///         Toast.makeText(getBaseContext(),"Copy Contenet Added", Toast.LENGTH_LONG).show();   
			                                                  
			                                                  
			
                                                                }
			
			                                        break;
			
		case R.id.imageMove:     dirr_returned_selected = adapter.select_returned();
                                                            if( dirr_returned_selected.isEmpty()){
       	 
                                                          	  AlertDialog.Builder addd = new AlertDialog.Builder(ActivityListdirectry.this);
                                                        		//ad.setTitle("ttt");
                                                        		    addd.setMessage("First select any FILE to Move ");

                                                        		    	addd.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                                                        		      public void onClick(DialogInterface dialog, int arg1) {
                                                        		    			// TODO Auto-generated method stub
                                                        				System.out.println();
                                                        		    		}
                                                        		    	} );
                                                        		    	addd.show();
                                                        		    	
                                                        		  	chk_visible = true;
                                  	                              //fill(currentDir);
                                        	                   	
                                        	                   	int index = gd.getFirstVisiblePosition();   //Finding position of grid View
                                        	          
                                        	                   	//View position_v = gd.getChildAt(0);
                                        	                  // 	int top = (position_v==null) ? 0 : (position_v.getTop()-gd.getPaddingTop());
                                        	                   	
                                  	                                adapter = new Adapter_main_grid_FileArray(ActivityListdirectry.this,R.layout.fexplorer_file_view_list,dir,chk_visible,-1);
                                  	                                //-1 meance invalid
                                  	                               gd.setAdapter(adapter); 
                                  	                               
                                  	                             try{  	gd.setSelection(index);    }catch(Exception exc){}          // Setting that position back
                                  	                               
                                                         }else{
			
			
			                                   
			                                    move_status = true;
			                                    chk_visible =false;
			                                    
			                                    
			                                    int index = gd.getFirstVisiblePosition();   //Finding position of grid View
			                                    fill(currentDir); //refresh ffgf
			                                 
			                               adapter = new Adapter_main_grid_FileArray(ActivityListdirectry.this,R.layout.fexplorer_file_view_list,dir,chk_visible,-1);
			                            	 gd.setAdapter(adapter); 
			                               try{  	gd.setSelection(index);    }catch(Exception exc){}          // Setting that position back
			                                    
			                                    //Toast.makeText(getBaseContext(),"Move Contenet Added", Toast.LENGTH_LONG).show();
			                                    
			                                                    ///// changing layout
			                                    String uri_move = "drawable/" +"c_paste";
			                        			int image_move= getApplicationContext().getResources().getIdentifier(uri_move, null,getApplicationContext().getPackageName());
			                        			Drawable image_paste = getApplicationContext().getResources().getDrawable(image_move);
			                        			move.setImageDrawable(image_paste);
			                        			move_place.setText("Paste");
			                        			move.setId(500);
			                        			
			                        			String uri = "drawable/" +"c_cancel";
			                        			int imageResource = getApplicationContext().getResources().getIdentifier(uri, null,getApplicationContext().getPackageName());
			                        			Drawable image_cancel = getApplicationContext().getResources().getDrawable(imageResource);
			                        			copy.setImageDrawable(image_cancel);
			                        			copy_place.setText("Cancel");
                                                copy.setId(501);    
                                                
                                                              }
			                                     break;
			
		
		case 501:	        //Cancel  button dynamic id
			
			
			String uri_cancel_copy = "drawable/" +"c_copy";
			int imageResource_cancel_copy = getApplicationContext().getResources().getIdentifier(uri_cancel_copy, null,getApplicationContext().getPackageName());
			Drawable image_cancel_copy = getApplicationContext().getResources().getDrawable(imageResource_cancel_copy);
			copy.setImageDrawable(image_cancel_copy);
			copy_place.setText("Copy");
            copy.setId(R.id.imageCopy);      
            
            
            String uri_cancel_move = "drawable/" +"c_move";
			int image_cancel_move= getApplicationContext().getResources().getIdentifier(uri_cancel_move, null,getApplicationContext().getPackageName());
			Drawable image_cancel_move2= getApplicationContext().getResources().getDrawable(image_cancel_move);
			move.setImageDrawable(image_cancel_move2);
			move_place.setText("Move");
			move.setId(R.id.imageMove);
			
			move_status = false; copy_status = false; 
                                                                       break;               
                                                                       
                                                                       
                                                                       
		case 500:                      //paste button dynamic  id
			
			
            
                                                                             if( dirr_returned_selected.isEmpty()){
                                                                            	 
                                                                            	 
                                                                           
                                                                               }else {   	 
                                                                               
                                                                               
                                                                                            }
                                                                                             
                                                                            Item o_a= dirr_returned_selected.get(0);
                                                                            String str0 = "" ;
                                                                            String   str1 = currentDir.toString();
                                                                            str1 = str1 + "/";
                                                                           String[] iit=o_a.getPath().split("\\/");
                                         
                                                           					for(int i = 0; i < iit.length-1; i++)
                                                           		              str0 = str0 +  iit[i] + "/";
                                                           		
                                                                if(!str0.equals(str1)){             
                                                                                 if(copy_status==true){
                                                      	 
                                                                     //       kk = ProgressDialog.show(this, "","Copying...", true);  //start progress bar
                                                                          Asy_copy kk_c = new Asy_copy(); 
                                                                         kk_c.execute(dirr_returned_selected);//Asynchronous task starts
                                                                                     
                                                                                                  copy_status = false;
                                                                                              }
                                                                                 
                                                                                 if(move_status==true){
                                                                                     //  kk = ProgressDialog.show(this, "","Moving...", true);  //start progress bar
                                                                                        Asy_move kk_m = new Asy_move();
                                                                                       kk_m.execute(dirr_returned_selected); //Asynchronous task starts
                                                                                       
                                                                                  	   move_status = false;
                                                                                              }
                                                                }else{ Toast.makeText(getBaseContext(),"Can't Paste in same Folder", Toast.LENGTH_LONG).show();
                                                                copy_status = false;
                                                                move_status = false;
                                                                
                                                                }
                                                 
                                                                                 
                                          
                                                                             
                                                                 			String uri_cancel_copy7 = "drawable/" +"c_copy";
                                                                			int imageResource_cancel_copy7 = getApplicationContext().getResources().getIdentifier(uri_cancel_copy7, null,getApplicationContext().getPackageName());
                                                                			Drawable image_cancel_copy7 = getApplicationContext().getResources().getDrawable(imageResource_cancel_copy7);
                                                                			copy.setImageDrawable(image_cancel_copy7);
                                                                			copy_place.setText("Copy");
                                                                            copy.setId(R.id.imageCopy);      
                                                                            
                                                                            
                                                                            String uri_cancel_move8 = "drawable/" +"c_move";
                                                                			int image_cancel_move8= getApplicationContext().getResources().getIdentifier(uri_cancel_move8, null,getApplicationContext().getPackageName());
                                                                			Drawable image_cancel_move88= getApplicationContext().getResources().getDrawable(image_cancel_move8);
                                                                			move.setImageDrawable(image_cancel_move88);
                                                                			move_place.setText("Move");
                                                                			move.setId(R.id.imageMove);
                                                                			
                                                                			move_status = false; copy_status = false; 
                                                                   
                                                                			
                                                                	        int index = gd.getFirstVisiblePosition();   //Finding position of grid View
                                                                	        fill(currentDir); //refresh ffgf
                                                                	     
                                                                	   adapter = new Adapter_main_grid_FileArray(ActivityListdirectry.this,R.layout.fexplorer_file_view_list,dir,chk_visible,-1);
                                                                		 gd.setAdapter(adapter); 
                                                                	   try{  	gd.setSelection(index);    }catch(Exception exc){}          // Setting that position back
			
			
			
			                                                break;
			                                     
		case R.id.imageRename:	      dirr_returned_selected = adapter.select_returned();
		                                                  String rename;
                                                                    if( dirr_returned_selected.isEmpty()){
       	  
                                                                  	  AlertDialog.Builder addd = new AlertDialog.Builder(ActivityListdirectry.this);
                                                                		//ad.setTitle("ttt");
                                                                		    addd.setMessage("First select a FILE to Rename ");

                                                                		    	addd.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                                                                		      public void onClick(DialogInterface dialog, int arg1) {
                                                                		    			// TODO Auto-generated method stub
                                                                				System.out.println();
                                                                		    		}
                                                                		    	} );
                                                                		    	addd.show();
                                                                		    	
                                                                		  	chk_visible = true;
                                          	                              //fill(currentDir);
                                                	                   	
                                                	                   	int index1 = gd.getFirstVisiblePosition();   //Finding position of grid View
                                                	          
                                                	                   	//View position_v = gd.getChildAt(0);
                                                	                  // 	int top = (position_v==null) ? 0 : (position_v.getTop()-gd.getPaddingTop());
                                                	                   	
                                          	                                adapter = new Adapter_main_grid_FileArray(ActivityListdirectry.this,R.layout.fexplorer_file_view_list,dir,chk_visible,-1);
                                          	                                //-1 meance invalid
                                          	                               gd.setAdapter(adapter); 
                                          	                               
                                          	                             try{  	gd.setSelection(index1);    }catch(Exception exc){}          // Setting that position back
                                          	                               
                                                                         }else{         final TextView input = (TextView) findViewById(R.id.tx11);
			
                                                                                	AlertDialog.Builder  alert = new AlertDialog.Builder(ActivityListdirectry.this,AlertDialog.THEME_HOLO_DARK);
                                                                                	   alert .setTitle("Enter a new name");
                                                                                	    //alert.setMessage("");
                                                                                	    
                                                                                	    final EditText  rname = new EditText(this);  
                                                                                	    Item ooo = dirr_returned_selected.get(0);
                                                                                	      rname.setText(ooo.getName());
                                                                                	      rname.setTextColor(Color.parseColor("#000000"));
                                                                                	    alert.setView(rname);
                                                                                	    
                                                                                	   alert .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                                	        public void onClick(DialogInterface dialog, int whichButton) {
                                                                 Editable value = rname.getText();
                                                                  String rename11 = value.toString();
                                                                                	            
                                                                                	            
                                                                                	           int n =  dirr_returned_selected.size();
                                                                                	           if(n <= 1){
                                                                                	        	   Item ooo = dirr_returned_selected.get(0);
                                                                          
                                                                               					it=ooo.getName().split("\\.");
                                                             
                                                                               					String ext=it[it.length-1].toString();
                                                                               				//	String to_name = rename11 + "." + ext;
                                                                               					String to_name = rename11;   
                                                                               					
                                                                               					
                                                                               				         String Cpath = ooo.getPath();
                                                                               				         
                                                                               				         it  =null ;
                                                                               				         it = Cpath.split("\\/");
                                                                               				         
                                                                               				         String path_name = "";
                                                                               				        for(int i = 0; (i<it.length-1);i++)
                                                                               				        	path_name = path_name+ it[i] + "/";
                                                                               				        	
                                                                               				        String from_name = path_name + ooo.getName();
                                                                               				         to_name = path_name + to_name;
                                                                               				         
                                                                               				         File from = new File(from_name);
                                                                               				         File to = new File(to_name);
                                                                               				         from.renameTo(to);
                                                                               				         
                                                                               				         chk_visible = false;
                                                                               				       
                                                                               				         
                                                                               						
                                                                                         	        int index = gd.getFirstVisiblePosition();   //Finding position of grid View
                                                                                         	        fill(currentDir); //refresh ffgf
                                                                                         	     
                                                                                         	   adapter = new Adapter_main_grid_FileArray(ActivityListdirectry.this,R.layout.fexplorer_file_view_list,dir,chk_visible,-1);
                                                                                         		 gd.setAdapter(adapter); 
                                                                                         	   try{  	gd.setSelection(index);    }catch(Exception exc){}          // Setting that position back
                                                                               				         
                                                                               				        Toast.makeText(getBaseContext(),ooo.getName(), Toast.LENGTH_LONG).show();
                                                                               				         
                                                                               				         
                                                                                	           }
                                                                                	           else{
                                                                                	        	   
                                                                                	        	   
                                                                                	        	   
                                                                                	        	 	AlertDialog.Builder  alert = new AlertDialog.Builder(ActivityListdirectry.this,AlertDialog.THEME_HOLO_DARK);
                                                                                             	   alert .setMessage("Plz select one file at a time");
                                                                                             	    //alert.setMessage("");
                                                                                
                                                                                             	   alert .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                                             	        public void onClick(DialogInterface dialog, int whichButton) {
                                                                                             	      
                                                                                             	        
                                                                                             	        }}).show();
                                                                                	        	   
                                                                                	           }
                                                                           
                                                                              
                                                                                	        
                                                                             }
                                                                                	    });
                                                                                	    
                                                                                	   alert .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                                                	        public void onClick(DialogInterface dialog, int whichButton) {
                                                                                	            // Do nothing.
                                                                                	        }
                                                                                	    }).show();
                                                                                	   

                                                                                	 
                                                                                	 
                                                                                                 }
			
			                                                                      break;
		
		case R.id.imageExit:  this.finish();
		                                           break;
			
		case R.id.imageMenu:	
	
			                    if(!cloud_bar.isShown()){
			                              ArrayList <String> ar1 = new ArrayList<String>();
			                              ArrayList <String> ar2 = new ArrayList<String>();
		                                  ArrayAdapter<String>  are_menu ;
	
		                   
		                                  
		                  ar1.add("Settings");
		                  ar1.add("share");
		                   ar1.add("Refresh");
			                ar1.add("About");
			                
			                ar2.add("b_sett");
			                   ar2.add("b_settings");
			                   ar2.add("b_refresh");
				                ar2.add("b_about");
			
	
		are_menu = new Adapter_menu_list(getBaseContext(), android.R.layout.simple_list_item_1, ar1,ar2);
			
			           lv.setAdapter(are_menu);
			             cloud_bar.setVisibility(View.VISIBLE);
			   
				ListView menu_lv = (ListView) findViewById(R.id.menu_list1);
				menu_lv.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
						// TODO Auto-generated method stub
					if(arg2==0){
                    	Intent i_native_settings = new Intent("com.ebin.fileexp.lite.a.Activity_fileexp_settings");
                    	startActivity(i_native_settings);
                    	cloud_bar.setVisibility(View.INVISIBLE);
					}else if(arg2==1){
                    	  dirr_returned_selected = adapter.select_returned();
                          if( dirr_returned_selected.isEmpty()){

                        		 AlertDialog.Builder ad = new AlertDialog.Builder(ActivityListdirectry.this);
                        		//ad.setTitle("ttt");
                        		    ad.setMessage("First select any FILE to share ");

                        		    	ad.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                        		      public void onClick(DialogInterface dialog, int arg1) {
                        		    			// TODO Auto-generated method stub
                        				System.out.println();
                        		    		}
                        		    	} );
                        		    	ad.show();

                           }else{
                        	   File sh_of;
                        	   boolean dir = true;
                        	   ArrayList<Uri> uri =new ArrayList<Uri>();
                        	   
                        	   
                        	  for(int i = 0 ;i< dirr_returned_selected.size();i++){ 
                        		  Item sh_o = dirr_returned_selected.get(i);
                        	    sh_of = new File(sh_o.getPath());
                        		  if(sh_of.isDirectory()){ dir = false; Toast.makeText(getApplicationContext(), "Only Files can Share!", Toast.LENGTH_LONG).show();break;}
                                  uri.add(Uri.fromFile(sh_of)); 
                        	     }
                        	  
                        	if(dir) { Intent intent = new Intent();
                        	  intent.setAction(Intent.ACTION_SEND_MULTIPLE);
                        	  intent.setType("*/*");
                        	  Toast.makeText(getApplicationContext(), "Select a suitable Medium for Share", Toast.LENGTH_LONG).show();
                        	  intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uri);
                        	  startActivity(intent);
                               	}
                           }

                    	cloud_bar.setVisibility(View.INVISIBLE);
                    }else if(arg2==2){
                    	fill(currentDir);
                    	cloud_bar.setVisibility(View.INVISIBLE);
                    }else if(arg2==3){
                    	Intent i_native_help = new Intent("com.ebin.fileexp.lite.AboutyActivity");
                    	startActivity(i_native_help);
                    	cloud_bar.setVisibility(View.INVISIBLE);
                    }
						
					}
				});
			   
			   
			                                          }else{cloud_bar.setVisibility(View.INVISIBLE);}
		                                       break;
		
		
		}
		
		
	}


////////////////////////////////////////////     LONG CLICK        /////////////////////////////////////////////////////////////
@Override




public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int arg2,long arg3) {
	// TODO Auto-generated method stub
//     CheckBox ch=(CheckBox)arg0.findViewById(R.id.checkdie);
//    
// 
//	
//	//ckfm.setVisibility(View.VISIBLE) ;
//	ch.setVisibility(View.VISIBLE);

	 Item o_o = adapter.getItem(arg2);
	if(!(copy_status==true||move_status==true)&&(!o_o.getImage().equals("directory_up")))
	{
	    
        // TODO Auto-generated method stub
 ad_m = new AlertDialog.Builder(ActivityListdirectry.this,AlertDialog.THEME_HOLO_DARK);
        //ad.setTitle("ttt");
      // ad_m.setTitle("Select Option");
 
 String icon_name = setting.getString("folder_icon", "a_folder_white") ;

 final CharSequence[] items_s;
 if(o_o.getImage().equalsIgnoreCase(icon_name)){

	 if(o_o.getName().startsWith(".")){
		 final CharSequence[] items = {"Select and Mark","Properties","Share", "Copy","Move","delete","UnHide"};
	 items_s = items;
	 }else{
		 final CharSequence[] items = {"Select and Mark","Properties","Share", "Copy","Move","delete","Hide"};
		 items_s = items;
	 }
	 
 }else{
	 
	 if(o_o.getName().startsWith(".")){
	 final CharSequence[] items = {"Select and Mark","Properties","Share", "Copy","Move","delete","UnHide","Open by System","Open by Other"};
	 items_s = items;
	 }else{
		 final CharSequence[] items = {"Select and Mark","Properties","Share", "Copy","Move","delete","Hide","Open by System","Open by Other"};
		 items_s = items;
	 }
 }
 

       ad_m.setItems(items_s, new DialogInterface.OnClickListener() {
            
    
    	   
    	   public void onClick(DialogInterface dialog, int which) {
                       // TODO Auto-generated method stub
           
    		   switch(which){
            	
            	case 0 :     //mark
            	                   	chk_visible = true;
      	                              //fill(currentDir);
            	                   	
            	                   	int index = gd.getFirstVisiblePosition();   //Finding position of grid View
            	          
            	                   	//View position_v = gd.getChildAt(0);
            	                  // 	int top = (position_v==null) ? 0 : (position_v.getTop()-gd.getPaddingTop());
            	                   	
      	                                adapter = new Adapter_main_grid_FileArray(ActivityListdirectry.this,R.layout.fexplorer_file_view_list,dir,chk_visible,arg2);
      	                               gd.setAdapter(adapter); 
      	                               
      	                             try{  	gd.setSelection(index);    }catch(Exception exc){}          // Setting that position back
      	                               
      	                               
                                       break;
           
            	case 7 :        // by sys 
            		                 Item o_o = adapter.getItem(arg2);
	                                      File file_sys=new File(o_o.getPath());
	                                  	try {
	                        				FileOpen.openFile(getApplicationContext(), file_sys);
	                        			} catch (IOException e) {
	                        				// TODO Auto-generated catch block
	                        				e.printStackTrace();
	                        			}
            		
            		             break;
            	case 8 :     // by other
            		               Item o = adapter.getItem(arg2);
            		              File file=new File(o.getPath());
            	                   Uri uri = Uri.fromFile(file);
            	                   Intent intent = new Intent(Intent.ACTION_VIEW);
            	                   intent.setDataAndType(uri, "*/*");
            	                   intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
            	                   getApplicationContext().startActivity(intent);
            		
            		
            		              break;
            		              
            	case 6 : //hide
            		
            		                o_o = adapter.getItem(arg2);
            		       
            		            	
            		            	   String Cpath = o_o.getPath();
                 				         
                 				         it  =null ;
                 				         it = Cpath.split("\\/");
                 				         
                 				         String path_name = "";
                 				        for(int i = 0; (i<it.length-1);i++)
                 				        	path_name = path_name+ it[i] + "/";

                 				        
                       if(o_o.getName().startsWith(".")){      				 
                    	   
                 				       String to_name= o_o.getName().replaceFirst(".", "");
                 				        String from_name = path_name + o_o.getName();
                 				         to_name = path_name + to_name;
                 				         
                 				         File from = new File(from_name);
                 				         File to = new File(to_name);
                 				         
                 				         
         
                             		              				         
                 				         from.renameTo(to);
                 				         if(!to.exists())
                 				        	   Toast.makeText(getApplicationContext(), "Access denied", Toast.LENGTH_LONG).show(); 
                 				         
                 				         
                 				        int inde1x = gd.getFirstVisiblePosition();   //Finding position of grid View
                             	        fill(currentDir); //refresh ffgf
                             	     
                             	   adapter = new Adapter_main_grid_FileArray(ActivityListdirectry.this,R.layout.fexplorer_file_view_list,dir,chk_visible,-1);
                             		 gd.setAdapter(adapter); 
                             	   try{  	gd.setSelection(inde1x);    }catch(Exception exc){}          // Setting that position back
                   				         
                 				         
            		             }else{                 String to_name= "." + o_o.getName();
          				                                      String from_name = path_name + o_o.getName();
        				                                         to_name = path_name + to_name; 		    
        				                                         File from = new File(from_name);
        				                 				         File to = new File(to_name);
        				                 				         
        				                 				         
        				                 				         
                     		              				         
        				                 				         from.renameTo(to);
        				                 				        if(!to.exists())
        				                 				        	   Toast.makeText(getApplicationContext(), "Access denied", Toast.LENGTH_LONG).show(); 
        				                 				        
        				                 				        int inde1x = gd.getFirstVisiblePosition();   //Finding position of grid View
        				                             	        fill(currentDir); //refresh ffgf
        				                             	     
        				                             	   adapter = new Adapter_main_grid_FileArray(ActivityListdirectry.this,R.layout.fexplorer_file_view_list,dir,chk_visible,-1);
        				                             		 gd.setAdapter(adapter); 
        				                             	   try{  	gd.setSelection(inde1x);    }catch(Exception exc){}          // Setting that position back
        				                   				           }
            		            	 
            		            	 
            		             break;
            	case 1:     //propertied
            		
            		          Item o_o_o = adapter.getItem(arg2);
                                     File f_o = new File(o_o_o.getPath());
                                     
                                     if(f_o.isFile()){
                                    	 
                                  	   
                     	        	 	AlertDialog.Builder  alert = new AlertDialog.Builder(ActivityListdirectry.this,AlertDialog.THEME_HOLO_DARK);
                                  	 Dir_size_Human_Redable newobj = new Dir_size_Human_Redable();
										//  alert .setMessage("Properties");
                                  	  //  alert.setMessage("Size        :"  + o_o_o.getData());
                                                   File temp_f = new File(o_o_o.getPath());
                                  	 long f_size= temp_f.length();
                     	        	 	String ss = newobj.humanReadableByteCount(f_size, false);
                     	        	 	
                                  	  final CharSequence[] items = {"Size             :  "+ss+" ("+f_size+" Bytes)","Path          :   "+ o_o_o.getPath() ,"Name         :  "+o_o_o.getName() ,"Type         :  "+"File"};
                                      alert.setItems(items, null);
                                  	   alert .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                  	        public void onClick(DialogInterface dialog, int whichButton) {


                                  	        
                                  	        }});  alert.show();
                                    	 
                                     }else{
                                   
                       Asy_fold_properties asy_folder_prop = new Asy_fold_properties();
                       asy_folder_prop.execute(o_o_o);
                                    	 
                                    	 
                                     }
            		                 break;
            		                 
            	case 2: //share
            	    
            	            	List<Item> cu_share= new ArrayList<Item>();
                                Item o_cu_share= adapter.getItem(arg2);
                                cu_share .add(o_cu_share);
                                dirr_returned_selected  =  cu_share;

                  	               File sh_of;
                  	               boolean diir = true;
                  	               ArrayList<Uri> iuri =new ArrayList<Uri>();
                  	   
                  	   
                  	                    for(int i = 0 ;i< dirr_returned_selected.size();i++){ 
                  		                        Item sh_o = dirr_returned_selected.get(i);
                  	                           sh_of = new File(sh_o.getPath());
                  		                         if(sh_of.isDirectory()){ diir = false; Toast.makeText(getApplicationContext(), "Only Files can Share!", Toast.LENGTH_LONG).show();break;}
                                                 iuri.add(Uri.fromFile(sh_of)); 
                  	     
                  	  
                  	                     if(diir) { Intent iintent = new Intent();
                  	                               iintent.setAction(Intent.ACTION_SEND_MULTIPLE);
                                               	  iintent.setType("*/*");
                  	                              Toast.makeText(getApplicationContext(), "Select a suitable Medium for Share", Toast.LENGTH_LONG).show();
                  	                              iintent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, iuri);
                  	                             startActivity(iintent);
                         	                       }
                                                   }

                        	cloud_bar.setVisibility(View.INVISIBLE);
            		                   break;
            		          
            		          
     case 3: //copy
            		 
                                     List<Item> cu_copy= new ArrayList<Item>();
                                     Item o_cu_copy= adapter.getItem(arg2);
                                      cu_copy .add(o_cu_copy);
	                                    dirr_returned_selected  =  cu_copy; //Assigned to global copy variable
	              
	              
                                             String uri_move_paste1 = "drawable/" +"c_paste";
             	                              int image_move1= getApplicationContext().getResources().getIdentifier(uri_move_paste1, null,getApplicationContext().getPackageName());
                                             Drawable image_move_paste1 = getApplicationContext().getResources().getDrawable(image_move1);
                                              move.setImageDrawable(image_move_paste1);
                                               move_place.setText("Paste");
                                               move.setId(500);

              	                          String uri_copy_cancel1 = "drawable/" +"c_cancel";
                                          int imageResource_cancel1 = getApplicationContext().getResources().getIdentifier(uri_copy_cancel1, null,getApplicationContext().getPackageName());
                                          Drawable image_copy_cancel1 = getApplicationContext().getResources().getDrawable(imageResource_cancel1);
                                           copy.setImageDrawable(image_copy_cancel1);
                                           copy_place.setText("Cancel");
                                           copy.setId(501);
                                            copy_status = true;
                                            // chk_visible =false;
                                        	int index1 = gd.getFirstVisiblePosition();   //Finding position of grid View
                                      	  fill(currentDir); //refresh ffgf

                                      	adapter = new Adapter_main_grid_FileArray(ActivityListdirectry.this,R.layout.fexplorer_file_view_list,dir,chk_visible,-1);
                                      	gd.setAdapter(adapter); 
                                      	try{  	gd.setSelection(index1);    }catch(Exception exc){}          // Setting that position back
                                            ///         Toast.makeText(getBaseContext(),"Copy Contenet Added", Toast.LENGTH_LONG).show();   
               
                  break;
                  
   
            	
    case 4:   //move
    
    	
        List<Item> cu_move= new ArrayList<Item>();
        Item o_cu_move= adapter.getItem(arg2);
         cu_move .add(o_cu_move);
           dirr_returned_selected  =  cu_move; //Assigned to global move variable
            		
                    move_status = true;
                  //  chk_visible =false;
                  	int index11 = gd.getFirstVisiblePosition();   //Finding position of grid View
                	  fill(currentDir); //refresh ffgf

                	adapter = new Adapter_main_grid_FileArray(ActivityListdirectry.this,R.layout.fexplorer_file_view_list,dir,chk_visible,-1);
                	gd.setAdapter(adapter); 
                	try{  	gd.setSelection(index11);    }catch(Exception exc){}          // Setting that position back
                    //Toast.makeText(getBaseContext(),"Move Contenet Added", Toast.LENGTH_LONG).show();
                    
                                    ///// changing layout
                    String uri_move = "drawable/" +"c_paste";
        			int image_move= getApplicationContext().getResources().getIdentifier(uri_move, null,getApplicationContext().getPackageName());
        			Drawable image_paste = getApplicationContext().getResources().getDrawable(image_move);
        			move.setImageDrawable(image_paste);
        			move_place.setText("Paste");
        			move.setId(500);
        			
        			String uuri = "drawable/" +"c_cancel";
        			int imageResource = getApplicationContext().getResources().getIdentifier(uuri, null,getApplicationContext().getPackageName());
        			Drawable image_cancel = getApplicationContext().getResources().getDrawable(imageResource);
        			copy.setImageDrawable(image_cancel);
        			copy_place.setText("Cancel");
                    copy.setId(501);    
            		
            		            break;
            		            
            		            
            	case 5://delete
            		
            		
            		 
                        final List<Item> cu_delete = new ArrayList<Item>();
                        Item o_cu_delete= adapter.getItem(arg2);
                        cu_delete .add(o_cu_delete);

                             AlertDialog.Builder  alert = new AlertDialog.Builder(ActivityListdirectry.this,AlertDialog.THEME_HOLO_DARK);
             	            // alert .setTitle("Enter a new name");
             	             alert.setMessage("Are you Sure to Delete (Can't Undo)?");
             	    
             	             //   final EditText  rname = new EditText(this); 
             	                //alert.setView(rname);
             	    
             	         alert .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
             	        public void onClick(DialogInterface dialog, int whichButton) {

             	      //  	   kk = ProgressDialog.show(ActivityListdirectry.this, "","Deleting...", true);  //start progress bar
             	        	
                            Asy_delete kk_d = new Asy_delete(); 
                           kk_d.execute( cu_delete);//Asynchronous task starts    
                            
                          
                                         chk_visible = false;
                     
             	                      }
             	                            });
             	    
             	                     alert .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
             	                     public void onClick(DialogInterface dialog, int whichButton) {
             	                        // Do nothing.
             	                                  }
             	                           }).show();
                    
            		        break;
                      }
                           
            
                      }
          });ad_m.show();
         
		
	}else{Toast.makeText(getBaseContext(), "First Complete current operation", Toast.LENGTH_LONG).show();}
		

	
	
	
	
	//Toast.makeText(getBaseContext(), o.getPath().toString(), Toast.LENGTH_LONG).show();
	
	return true;
}





class Image_load_thread extends AsyncTask<List<Image_thumbnail>, Bitmap_imageView, Bitmap_imageView>{

	
	List<Image_thumbnail> para;
	 Image_thumbnail i_o;
	Bitmap bmp,bmp1;
	
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	para = new ArrayList<Image_thumbnail>();
	}
	
	@Override
	protected Bitmap_imageView doInBackground(List<Image_thumbnail>... params) {
		// TODO Auto-generated method stub
		
		para = params[0];
		 int size = para.size();
		 Bitmap_imageView bimv=null;
		 
		  for(int i=0; i<size;i++){
		
	       i_o = para.get(i);
	      bmp = BitmapFactory.decodeFile(i_o.getPath_i());
	      bmp1= ThumbnailUtils.extractThumbnail(bmp, 50, 60);
	   
	       bimv = new Bitmap_imageView(i_o.getImageView(),bmp1);
	
	      publishProgress(bimv);
	  
	      
	     } 
				
		
		return bimv;
	}

	@Override
	protected void onProgressUpdate(Bitmap_imageView... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
		Bitmap_imageView result = values[0];
		result.im_v.setImageBitmap(result.bit_map);
	}
	
@Override
protected void onPostExecute(Bitmap_imageView result) {
	// TODO Auto-generated method stub
	super.onPostExecute(result);

//	result.im_v.setImageBitmap(result.bit_map);
}
	
	

	}

class Bitmap_imageView{  
	
	Bitmap bit_map;  ImageView im_v;  
	public Bitmap_imageView(ImageView imageView, Bitmap bmp1) {
	
	bit_map = bmp1;
	im_v = imageView;

	} 
     }




public static final int DIALOG_COPY_PROGRESS = 0;          
public static final int DIALOG_MOVE_PROGRESS = 1;        
public static final int DIALOG_DELETE_PROGRESS = 2;
private ProgressDialog mProgressDialog;
protected Dialog onCreateDialog(int id) {
    switch (id) {
	case DIALOG_COPY_PROGRESS:
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setMessage("Coping files..");
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		mProgressDialog.setCancelable(false);
		mProgressDialog.show();
		return mProgressDialog;
		
	case DIALOG_MOVE_PROGRESS:
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setMessage("Moving files..");
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		mProgressDialog.setCancelable(false);
		mProgressDialog.show();
		return mProgressDialog;		
		
	case DIALOG_DELETE_PROGRESS:
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setMessage("Deleting files..");
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		mProgressDialog.setCancelable(false);
		mProgressDialog.show();
		return mProgressDialog;				

	default:
		return null;
    }
}







class Asy_copy extends AsyncTask<List<Item>, String, String>{

@Override
protected void onPreExecute() {
	super.onPreExecute();
	showDialog(DIALOG_COPY_PROGRESS);
}

@Override
protected String doInBackground(List<Item>... params) {
	int count;
	File c_p_d = null;
try {

	List<Item> dir_c;
	dir_c =  params[0];
	  int s = dir_c.size();

        for(int i=0;i<s;i++){ 
        Item ooo = dir_c.get(i);
        //Toast.makeText(getApplicationContext(), ooo.getPath(), Toast.LENGTH_LONG).show(); 
           Move_Copy_Delete co = new Move_Copy_Delete();
           File c_p = new File(ooo.getPath());
           String copy_1 = currentDir.toString() +"/"+ooo.getName();
           c_p_d = new File(copy_1);
 //        Toast.makeText(getApplicationContext(), copy_1, Toast.LENGTH_LONG).show(); 
           co.copyFolder(c_p, c_p_d);
           
       	publishProgress(""+(int)((i*100)/s));
           
        }


} catch (Exception e) {}
return c_p_d.getAbsolutePath();


}


protected void onProgressUpdate(String... progress) {
	 Log.d("ANDRO_ASYNC",progress[0]);
	 mProgressDialog.setProgress(Integer.parseInt(progress[0]));
}

@Override
protected void onPostExecute(String unused) {
	dismissDialog(DIALOG_COPY_PROGRESS);
int index = gd.getFirstVisiblePosition();   //Finding position of grid View
  fill(currentDir); //refresh ffgf

adapter = new Adapter_main_grid_FileArray(ActivityListdirectry.this,R.layout.fexplorer_file_view_list,dir,chk_visible,-1);
gd.setAdapter(adapter); 
try{  	gd.setSelection(index);    }catch(Exception exc){}          // Setting that position back

if(!(new File(unused).exists())){
	
	  AlertDialog.Builder addd = new AlertDialog.Builder(ActivityListdirectry.this);
	//ad.setTitle("ttt");
	    addd.setMessage("Sorry Access denied");

	    	addd.setPositiveButton("OK",new DialogInterface.OnClickListener() {
	      public void onClick(DialogInterface dialog, int arg1) {
	    			// TODO Auto-generated method stub
			System.out.println();
	    		}
	    	} );
	    	addd.show();
}
}



}









public class Asy_move extends AsyncTask<List<Item>, String, String>{

    File c_p;
    File c_p_d;
    
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		showDialog(DIALOG_MOVE_PROGRESS);
	}

	@Override
	protected String doInBackground(List<Item>... params) {
		// TODO Auto-generated method stub
        
		List<Item> o_m = params[0];
		 int s = o_m.size();
		 Item ooo = o_m.get(0);
	      try{
              for(int i=0;i<s;i++){ 
           ooo = o_m.get(i);
              //Toast.makeText(getApplicationContext(), ooo.getPath(), Toast.LENGTH_LONG).show(); 
                 Move_Copy_Delete coo = new Move_Copy_Delete();
                c_p = new File(ooo.getPath());
                 String copy_1 = currentDir.toString() +"/"+ooo.getName();
                 c_p_d = new File(copy_1);
              // Toast.makeText(getApplicationContext(), copy_1, Toast.LENGTH_LONG).show(); 
                 coo.copyFolder(c_p, c_p_d);
                   if(c_p_d.exists())         coo.delete_file(c_p);     // copy and then delete
                	publishProgress(""+(int)((i*100)/s));
                    }
            }catch(Exception exc){

                             }
	return c_p.getAbsolutePath();
	}

	
	
	protected void onProgressUpdate(String... progress) {
		 Log.d("ANDRO_ASYNC",progress[0]);
		 mProgressDialog.setProgress(Integer.parseInt(progress[0]));
	}

	@Override
	protected void onPostExecute(String unused) {
		dismissDialog(DIALOG_MOVE_PROGRESS);
	int index = gd.getFirstVisiblePosition();   //Finding position of grid View
	  fill(currentDir); //refresh ffgf

	adapter = new Adapter_main_grid_FileArray(ActivityListdirectry.this,R.layout.fexplorer_file_view_list,dir,chk_visible,-1);
	gd.setAdapter(adapter); 
	try{  	gd.setSelection(index);    }catch(Exception exc){}          // Setting that position back
	  // Toast.makeText(getApplicationContext(), c_p.toString(), Toast.LENGTH_LONG).show(); 
	//  Toast.makeText(getApplicationContext(),c_p_d.toString(), Toast.LENGTH_LONG).show(); 
	
	
	if((new File(unused).exists())){
		
		  AlertDialog.Builder addd = new AlertDialog.Builder(ActivityListdirectry.this);
  		//ad.setTitle("ttt");
  		    addd.setMessage("Sorry Access denied");

  		    	addd.setPositiveButton("OK",new DialogInterface.OnClickListener() {
  		      public void onClick(DialogInterface dialog, int arg1) {
  		    			// TODO Auto-generated method stub
  				System.out.println();
  		    		}
  		    	} );
  		    	addd.show();
	}
	}
	
	
	
}






public class Asy_delete extends AsyncTask<List<Item>, String, String>{

	boolean status;

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		showDialog(DIALOG_DELETE_PROGRESS);
	}
	
	@Override
	protected String doInBackground(List<Item>... params) {
		// TODO Auto-generated method stub

List <Item> dir_d = params[0];
int s = dir_d.size();
Item ooo = dir_d.get(0);
		   try{
          	  
                  for(int i=0;i<s;i++){ 
               	  ooo = dir_d.get(i);
               	   Move_Copy_Delete del = new Move_Copy_Delete();
               	   
               		
               		File file_del = new File(ooo.getPath());
               		
                           status =   del.delete_file(file_del);
                           if(!(status==true)) return ooo.getPath();
             //     Toast.makeText(getApplicationContext(), "Deleted"+ooo.getPath(), Toast.LENGTH_LONG).show(); 
                       	publishProgress(""+(int)((i*100)/s));
                                         }
              }catch(Exception exc){
     	  
                 }
		   return ooo.getPath();
		
		  // if((status==true)) {return "0" ;}  else {return "1";}
	}
	
	protected void onProgressUpdate(String... progress) {
		 Log.d("ANDRO_ASYNC",progress[0]);
		 mProgressDialog.setProgress(Integer.parseInt(progress[0]));
	}

	@Override
	protected void onPostExecute(String unused) {
		
		dismissDialog(DIALOG_DELETE_PROGRESS);
	int index = gd.getFirstVisiblePosition();   //Finding position of grid View
	  fill(currentDir); //refresh ffgf

	adapter = new Adapter_main_grid_FileArray(ActivityListdirectry.this,R.layout.fexplorer_file_view_list,dir,chk_visible,-1);
	gd.setAdapter(adapter); 
	try{  	gd.setSelection(index);    }catch(Exception exc){}          // Setting that position back
	

	if((new File(unused).exists())){
		
		  AlertDialog.Builder addd = new AlertDialog.Builder(ActivityListdirectry.this);
    		//ad.setTitle("ttt");
    		    addd.setMessage("Sorry Access denied");

    		    	addd.setPositiveButton("OK",new DialogInterface.OnClickListener() {
    		      public void onClick(DialogInterface dialog, int arg1) {
    		    			// TODO Auto-generated method stub
    				System.out.println();
    		    		}
    		    	} );
    		    	addd.show();
	}
	
	}
	
	
}






public class Asy_fold_properties extends AsyncTask<Item, String, Result>{

	Item asy_file_prop;
 	long sizee;
 	 String sss;
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();


	
	}
	
	@Override
	protected Result doInBackground(Item... params) {
		// TODO Auto-generated method stub
		
		asy_file_prop = params[0];
        File f_o = new File(asy_file_prop.getPath());
		
   	 Dir_size_Human_Redable newobj = new Dir_size_Human_Redable();
 sizee = newobj.dirSize(f_o);
	sss = newobj.humanReadableByteCount(sizee, false);
	
		
		return null;
	}
	
	
	
	@Override
	protected void onPostExecute(Result result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	
 	 	AlertDialog.Builder  alert = new AlertDialog.Builder(ActivityListdirectry.this,AlertDialog.THEME_HOLO_DARK);
    	 //  alert .setMessage("Properties");
    	  //  alert.setMessage("Size        :"  + o_o_o.getData());
    	  final CharSequence[] items = {"Size             :  "+sss+" ("+sizee+" Bytes)","Path          :   "+ asy_file_prop.getPath() ,"Name         :  "+asy_file_prop.getName() ,"Type         :  "+"Folder"};
        alert.setItems(items, null);
    	   alert .setPositiveButton("OK", new DialogInterface.OnClickListener() {
    	        public void onClick(DialogInterface dialog, int whichButton) {


    	        
    	        }});  alert.show();
    	        
		
	}
	
}

public LinearLayout get_main_linear_layout_bg(){
	
	
	
	return cloud_bar;
	
}

}

