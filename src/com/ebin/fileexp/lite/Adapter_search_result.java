package com.ebin.fileexp.lite;

import java.io.File;
import java.util.List;

import com.dark.explorer.lite.ebinjoy.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


class Adapter_saerch_result extends ArrayAdapter<Item>{

	Context context;
	TextView name, path, size;
	ImageView icon;
	List<Item> result;
	
	public Adapter_saerch_result(Context context, int resource,List<Item> result) {
		super(context, resource, result);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.result = result;
	}


@Override
public View getView(int position, View convertView, ViewGroup parent) {
	// TODO Auto-generated method stub
	
	LayoutInflater la = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	View v = la.inflate(R.layout.listview_search, null);
    Item o = result.get(position);
	

	name = (TextView) v.findViewById(R.id.textView_saerch_name);
	path = (TextView) v.findViewById(R.id.textView2_search_path);
	size = (TextView) v.findViewById(R.id.textView3_search_size);
	icon = (ImageView) v.findViewById(R.id.imageView1_search_icon);
	
	
	
	name.setText(o.getName());
	path.setText(o.getPath());

	

	   
	File ff = new File(o.getPath());
	Dir_size_Human_Redable newobj = new Dir_size_Human_Redable();  //Changing byte to human readable format
	long f_size= ff.length();
	 	String ss = newobj.humanReadableByteCount(f_size, false);
	 	
	size.setText(ss);
	
	String uri = "drawable/" + o.getImage();
	int imageResource = context.getResources().getIdentifier(uri, null,context.getPackageName());
	Drawable image = context.getResources().getDrawable(imageResource);
icon.setImageDrawable(image);
	
	
	
	return v;
}
	
	
	
}