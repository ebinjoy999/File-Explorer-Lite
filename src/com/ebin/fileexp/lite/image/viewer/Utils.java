package com.ebin.fileexp.lite.image.viewer;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

public class Utils {

	private Context _context;

	// constructor
	public Utils(Context context) {
		this._context = context;
	}

	
	
	
	


	/*
	 * getting screen width
	 */
	public int getScreenWidth() {
		int columnWidth;
		WindowManager wm = (WindowManager) _context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		
		

		final Point point = new Point();
		try {
			display.getSize(point);
		} catch (java.lang.NoSuchMethodError ignore) { // Older device
			point.x = display.getWidth();
			point.y = display.getHeight();
		}
		columnWidth = point.x;
		return columnWidth;
	}
	
	
	
	public int getScreenHeight() {
		int columnHeight;
		WindowManager wm = (WindowManager) _context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		
		

		final Point point = new Point();
		try {
			display.getSize(point);
		} catch (java.lang.NoSuchMethodError ignore) { // Older device
			point.x = display.getWidth();
			point.y = display.getHeight();
		}
		columnHeight = point.y;
		return columnHeight;
	}
}
