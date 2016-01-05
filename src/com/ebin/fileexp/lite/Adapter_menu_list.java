package com.ebin.fileexp.lite;

import java.util.List;

import com.dark.explorer.lite.ebinjoy.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter_menu_list extends ArrayAdapter<String>{

	
	Context context;
	List objects, objects_im;
	TextView menu_name;
	ImageView menu_imv;
	String name;
	
	public Adapter_menu_list(Context context, int resource, List<String> objects,List<String> objects_im) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.objects = objects;
		this.objects_im = objects_im;
		
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		LayoutInflater la = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = la.inflate(R.layout.fexplorer_menu, null);
		menu_name = (TextView) v.findViewById(R.id.textView_menu);
		menu_imv = (ImageView) v.findViewById(R.id.imageView_menu);
		
		
		menu_name.setText(objects.get(position).toString());
		String uri_cancel_copy = "drawable/" +objects_im.get(position).toString();
		int imageResource_cancel_copy = context.getResources().getIdentifier(uri_cancel_copy, null,context.getPackageName());
		Drawable image_cancel_copy = context.getResources().getDrawable(imageResource_cancel_copy);
		menu_imv.setImageDrawable(image_cancel_copy);
	    
		
		
		return v;
	}

}
