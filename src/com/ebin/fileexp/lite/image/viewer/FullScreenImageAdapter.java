package com.ebin.fileexp.lite.image.viewer;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.dark.explorer.lite.ebinjoy.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class FullScreenImageAdapter extends PagerAdapter {
	Bitmap bitmap;
	private Activity _activity;
	private ArrayList<String> _imagePaths;
	private LayoutInflater inflater ;


	// constructor
	public FullScreenImageAdapter(Activity activity,ArrayList<String> imagePaths) {
		this._activity = activity;
		this._imagePaths = imagePaths;
	}

	@Override
	public int getCount() {
		return this._imagePaths.size();
	}

	@Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }
	
	@Override
 
	
	
	
	
	
	
	
	
	
	
	
	public Object instantiateItem(ViewGroup container, int position) {
		 
	
		ImageView imgDisplay;
		  final Button btnClose;


	    inflater = (LayoutInflater) _activity .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 View viewLayout = inflater.inflate(R.layout.image_layout_fullscreen_image, container, false);
		imgDisplay = (TouchImageView) viewLayout.findViewById(R.id.imgDisplay);
	   btnClose = (Button) viewLayout.findViewById(R.id.btnClose);
  btnClose.setVisibility(View.INVISIBLE);
        
        BitmapFactory.Options options = new BitmapFactory.Options();
       //options.inSampleSize=1; //otherwise show out of memmory and stops
        //options.inJustDecodeBounds=true;
       // options.inPreferredConfig = Bitmap.Config.ARGB_8888;
      
        
        
        //orginal//       bitmap = BitmapFactory.decodeFile(_imagePaths.get(position), options);
    	
        
        Utils obj3 = new Utils(_activity);
        int sreen_width =  obj3.getScreenWidth();
        int sreen_height =  obj3.getScreenHeight();   //above orginal line replace this scale
        bitmap = decodeFile(_imagePaths.get(position), sreen_width,	sreen_height);

         
         imgDisplay.setImageBitmap(bitmap);
        
  
        
        
        //	bitmap.recycle();
        //	bitmap = null;
        	//System.gc();

        
        // close button click event
         
         imgDisplay.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(btnClose.isShown()){
					btnClose.setVisibility(View.INVISIBLE);}else
				{
						btnClose.setVisibility(View.VISIBLE);
				}
				
			}
		});
         
         
        btnClose.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				_activity.finish();
				  
			}
		}); 

        ((ViewPager) container).addView(viewLayout);
     
      
		return viewLayout;
	}
	
	
	
	
	
	
	
	
	
	
	
	@Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
 
    }
	
	
	
	
	
	
	

	/*
	 * Resizing image size
	 */
	public static Bitmap decodeFile(String filePath, int WIDTH, int HIGHT) {
		try {

			File f = new File(filePath);

			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(new FileInputStream(f), null, o);

			final int REQUIRED_WIDTH = WIDTH;
			final int REQUIRED_HIGHT = HIGHT;
			int scale = 1;
			while (o.outWidth / scale / 2 >= REQUIRED_WIDTH
					&& o.outHeight / scale / 2 >= REQUIRED_HIGHT)
				scale *= 2;

			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}


}
