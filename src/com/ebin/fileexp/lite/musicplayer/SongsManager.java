 package com.ebin.fileexp.lite.musicplayer;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

public class SongsManager {
	// SDCard Path
 String MEDIA_PATH = new String("/sdcard/");
	private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
	String filename="";
	boolean first = false;
	// Constructor
	public SongsManager(){
		
	}
	
	public SongsManager(String path, String filename) {
		MEDIA_PATH = path;
		this.filename=filename;
		// TODO Auto-generated constructor stub
	}

	/**
	 * Function to read all mp3 files from sdcard
	 * and store the details in ArrayList
	 * */
	public ArrayList<HashMap<String, String>> getPlayList(){
		
		
		File home = new File(MEDIA_PATH);

		if (home.listFiles(new FileExtensionFilter()).length > 0) {
			
      for (File file : home.listFiles(new FileExtensionFilter())) {
				HashMap<String, String> song = new HashMap<String, String>();
				if(filename.equals(file.getName())){first = true;} /////////////////////////////////////////////////////////
				song.put("songTitle", file.getName().substring(0, (file.getName().length() - 4)));
				song.put("songPath", file.getPath());
				
				// Adding each song to SongList ///      check whether clicked song is added to first position
			if(first==true){first = false;  songsList.add(0, song); }else{	songsList.add(song); }
			                           }
	                        	}
		// return songs list array
		return songsList;
	}
	
	/**
	 * Class to filter files which are having .mp3 extension
	 * */
	class FileExtensionFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			return (name.endsWith(".mp3") || name.endsWith(".MP3"));
		}
	}
}
