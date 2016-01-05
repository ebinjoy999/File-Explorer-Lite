package com.ebin.fileexp.lite.a;

import com.dark.explorer.lite.ebinjoy.R;


public class Slsect_main_gridview_bg {

	public int get_main_linear_layout_bg(String selected_holo){
		
		if(selected_holo.equalsIgnoreCase("g_red")){
			return R.drawable.gradient_red;
		}else if(selected_holo.equalsIgnoreCase("g_sky_blue")){
			return R.drawable.gradient_un;
		}else if(selected_holo.equalsIgnoreCase("g_black")){
			return R.drawable.gradient_black;
		}else if(selected_holo.equalsIgnoreCase("g_blue")){
			return R.drawable.gradient_blue;
		}else if(selected_holo.equalsIgnoreCase("g_gold_lite")){
			return R.drawable.gradient_bro;
		}else if(selected_holo.equalsIgnoreCase("g_red_ball")){
			return R.drawable.gradient_red_ball;
		}else if(selected_holo.equalsIgnoreCase("g_orange")){
			return R.drawable.gradient_orange;
		
		}
		
		return android.R.color.background_dark;
		
	}
	
}
