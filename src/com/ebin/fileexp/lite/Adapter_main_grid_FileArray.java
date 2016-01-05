package com.ebin.fileexp.lite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import javax.crypto.spec.IvParameterSpec;

import com.dark.explorer.lite.ebinjoy.R;
import com.ebin.fileexp.lite.a.Activity_fileexp_settings;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.os.AsyncTask;
import android.provider.MediaStore.Images.Thumbnails;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Adapter_main_grid_FileArray extends ArrayAdapter<Item> {

	
	List<Image_thumbnail> image_thumb = new ArrayList<Image_thumbnail>();

	
	ViewHolder view_holder ;
	CheckBox ch;
	GridView gd;
	Item o ;
	List<Item> dirr= new ArrayList<Item>();
	private Context c;
	private int id;
	private List<Item> items;
	boolean chk_visible;
	Bitmap sacled_bmp;
	 Bitmap bmp1;	
//  int[]  checked_array = new int[100];
	 View v ;
	 int  k;
	 int where_marked;
	 
	 
	public Adapter_main_grid_FileArray(Context context, int textViewResourceId, List<Item> objects, boolean chk_visible, int where_mark) {
		super(context, textViewResourceId, objects);
		c = context;
		id = textViewResourceId;
		items = objects;
		this.chk_visible = chk_visible;
		this.where_marked = where_mark;

	}

	
	public Item getItem(int i) {
		return items.get(i);
	}
	
	 
	public List<Item> select_returned(){
		
		return dirr;
	}
	
public List<Image_thumbnail> image_thumbnail(){
		
		return image_thumb;
	}
	
	
	//////////////////////////////////////              GET VIEW           ////////////////////////////////////////
	
	


	
	
	
	
	
	@Override
	public View getView( final int position, View convertView, ViewGroup parent) {
		
	
	//	if(convertView==null){

		LayoutInflater vi = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v = vi.inflate(R.layout.fexplorer_file_view_list, null);
		
//		}else{
//		v =  convertView;
//	}
	
		//	view_holder = (ViewHolder) v.getTag();

			
			

		
		 
         view_holder = new ViewHolder();
			view_holder.ivicon = (ImageView) v.findViewById(R.id.file_view_list_image);
			view_holder.name = (TextView) v.findViewById(R.id.txt_name);
			view_holder.item_size = (TextView) v.findViewById(R.id.txt_item);
			view_holder.ch = (CheckBox) v.findViewById(R.id.checkdi);
		         
			o = items.get(position);
			 
			SharedPreferences setting= c.getSharedPreferences("settings", 0);                //retrieve saved value;
			 final SharedPreferences.Editor editor = setting.edit();
		       String colour = setting.getString("set_text_colour", "f_holo_light");
	

			if(colour.equals("f_holo_dark"))
			{
						view_holder.name .setTextColor(Color.parseColor("#000000"));
						view_holder.item_size .setTextColor(Color.parseColor("#000000"));
			}else if(colour.equals("f_holo_light")){
				view_holder.name .setTextColor(Color.parseColor("#ffffff"));
				view_holder.item_size .setTextColor(Color.parseColor("#ffffff"));
				
				
			    }
						
			
			///////////////////////////////////                 THUMBNAIL                       /////////////////////////////////////
			
			
			if(o.getImage()=="a_image"){
				
   image_thumb.add(new Image_thumbnail(view_holder.ivicon, o.getPath()));
   
	String uri = "drawable/" + o.getImage();
	int imageResource = c.getResources().getIdentifier(uri, null,c.getPackageName());
	Drawable image = c.getResources().getDrawable(imageResource);
view_holder.ivicon.setImageDrawable(image);
			
					// TODO Auto-generated method stub
//					Bitmap bmp = BitmapFactory.decodeFile(o.getPath());
//					bmp1 = ThumbnailUtils.extractThumbnail(bmp, 50, 60);
//	             	view_holder.ivicon.setImageBitmap(bmp1);
			
			
			
//			
//			Bitmap bmp = BitmapFactory.decodeFile(o.getPath());
//			sacled_bmp = Bitmap.createScaledBitmap(bmp, 50, 60, true); 
//			imageCity.setImageBitmap(sacled_bmp);
//			
			
			
			//Bitmap  bitmap = decodeFile(o.getPath(), 20,	30);  //using previous function defined below as in image viewer scaling
		//	imageCity.setImageBitmap(bitmap);
				
				
			}else if(o.getImage()=="directory_up"){
				
				String uri = "drawable/" + o.getImage();
	       	int imageResource = c.getResources().getIdentifier(uri, null,c.getPackageName());
				Drawable image = c.getResources().getDrawable(imageResource);
			   view_holder.ivicon.setImageDrawable(image);

				
			}else{
			
			String uri = "drawable/" + o.getImage();
			int imageResource = c.getResources().getIdentifier(uri, null,c.getPackageName());
			Drawable image = c.getResources().getDrawable(imageResource);
		   view_holder.ivicon.setImageDrawable(image);
		
			}
		 
			
			
			
			
			
			
			
			String sss = o.getName();
			if(sss.length()>10){
			sss = sss.substring(0, 9);
			sss = sss + "...";}                                                 //Trimming Name 
			view_holder.name.setText(sss);
		 view_holder.item_size.setText(o.getData());
 	
		 
		/////////////////////////////////////       Setting visibility for the Checkbox   //////////////////////////////////////
		 
	
		 
			if(chk_visible==false){	
			try{	 
				view_holder.ch.setVisibility(View.INVISIBLE); 
                 view_holder.ivicon.setId(position);  	
             k =0;
			}catch(Exception exc){}
				 dirr = new ArrayList<Item>();
				 } else{
					 if(o.getImage().equals("directory_up"))	view_holder.ch.setVisibility(View.INVISIBLE); 
					 view_holder.ch.setId(position);
				
					 if(where_marked!=-1){
					 Item marked_item = items.get(where_marked);
						if(!dirr.contains(marked_item)) dirr.add(marked_item);}     //Setting the clicked item as marked
				 }
		 
			
			////////////////////////////////////////         Checking newely drawed check box previously checked              ///////////////////////////////////
			

			
			if(dirr.contains(o)){
      			view_holder.ch.setChecked(true); }
			
			
			
	///////////////////////////////////////                       CHECKED CHANGED                ///////////////////////////////////////////////////////////////////////
			
			
			
			
         view_holder.ch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					
		
					o = items.get(position);	
			 if(dirr.contains(o)){
			dirr.remove(o);  
						}else{
						 
							dirr.add(o);
							}
						
				
				}
			});
							
          
          
//		 o = items.get(position);
//		if (o != null) {
//			TextView t1 = (TextView) v.findViewById(R.id.txt_name);
//			TextView t2 = (TextView) v.findViewById(R.id.txt_item);
//
//
//			
//	
//			
//			/////////////////////////   CHECKED CHANGE          /////////////////////////////
//			
//		
//           ch = (CheckBox) v.findViewById(R.id.checkdi);
//
//           
//           ch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//				
//				@Override
//				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//					// TODO Auto-generated method stub
//				
//					 o = items.get(position);	
//					int t= buttonView.getId();
//					String ss= Integer.toString(t);
//		
//
//			 if(dirr.contains(o)){
//			dirr.remove(o);  
//						}else{
//						 
//							dirr.add(o);
//							}
//						
//				
//				}
//			});
//				
//			
//		 //if(position  > 10) {ch.setId(position);   }      ///       /////////////   ChechBox dynamic id set			
//		
//				
//			
//			
//			
//		                  	if (chk_visible && !(o.getImage().equals("directory_up"))) {
//			                                      	ch.setVisibility(View.VISIBLE);
//			                               	} else {
//				                                   ch.setVisibility(View.INVISIBLE);
//		                                      	}
//	
//		                  	
//		                  	
//	 final ImageView imageCity = (ImageView) v.findViewById(R.id.file_view_list_image);
//
//	 if(o.getImage()=="a_image"){
//	
//			
//			Bitmap bmp = BitmapFactory.decodeFile(o.getPath());
//				 bmp1 = ThumbnailUtils.extractThumbnail(bmp, 50, 60);
//             imageCity.setImageBitmap(bmp1);
//			
//			
////			
////			Bitmap bmp = BitmapFactory.decodeFile(o.getPath());
////			sacled_bmp = Bitmap.createScaledBitmap(bmp, 50, 60, true); 
////			imageCity.setImageBitmap(sacled_bmp);
////			
//			
//			
//			//	Bitmap  bitmap = decodeFile(o.getPath(), 20,	30);  //using previous function defined below
//		//	imageCity.setImageBitmap(bitmap);
//
//			
//
//			
//			
//		}else{
//			
//			String uri = "drawable/" + o.getImage();
//			int imageResource = c.getResources().getIdentifier(uri, null,c.getPackageName());
//			Drawable image = c.getResources().getDrawable(imageResource);
//			imageCity.setImageDrawable(image);
//		}
//			if (t1 != null){
//				
//				String ss = o.getName();
//				if(ss.length()>10){
//				ss = ss.substring(0, 9);
//				ss = ss + "...";}
//				t1.setText(ss);
//			}
//			if (t2 != null)
//				t2.setText(o.getData()); 
//
//		}
		
	return v;  
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	

	
	
public static class ViewHolder{
	
	ImageView ivicon;
	TextView name;
	TextView item_size;
	CheckBox ch;
}




}