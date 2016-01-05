package com.ebin.fileexp.lite;

import android.widget.ImageView;

public class Image_thumbnail {

	ImageView  imm;
	String image_path;
	
	public Image_thumbnail(ImageView imm, String image_path){
		this.image_path = image_path;
		this.imm = imm;
	}
	
	public ImageView getImageView(){
		return imm;
	
	}
	
	
	public String getPath_i(){
		return image_path;
	
	}
	
}
