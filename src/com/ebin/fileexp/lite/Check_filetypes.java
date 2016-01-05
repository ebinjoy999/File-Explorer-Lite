package com.ebin.fileexp.lite;

public class Check_filetypes {

	String icon;
	
	public String find_extention(String name){
		
		String[] it=name.split("\\.");
		String ext=it[it.length-1].toString();
		
		if((ext.toUpperCase().equals("JPG".toUpperCase()))||(ext.toUpperCase().equals("PNG".toUpperCase()))||(ext.toUpperCase().equals("JPEG".toUpperCase())))
		{                      // only supported by native viewer  ("jpg", "jpeg","png");
			         icon =   "a_image";
		}else if((ext.toUpperCase().equals("APK".toUpperCase()))){
			 icon = "a_apk";
		} else if((ext.toUpperCase().equals("ZIP".toUpperCase()))||(ext.toUpperCase().equals("RAR".toUpperCase()))||(ext.toUpperCase().equals("TGZ".toUpperCase()))){
			icon = "a_archive_yellow";
		}else if((ext.toUpperCase().equals("DB".toUpperCase()))){
			icon = "a_database";
		}else if((ext.toUpperCase().equals("XLS".toUpperCase()))||(ext.toUpperCase().equals("XLSM".toUpperCase()))||(ext.toUpperCase().equals("NB".toUpperCase()))||(ext.toUpperCase().equals("ODS".toUpperCase()))){
			icon ="a_excel";
		}else if((ext.toUpperCase().equals("HTM".toUpperCase()))||(ext.toUpperCase().equals("HTML".toUpperCase()))){
			icon = "a_format_html";
		}else if((ext.toUpperCase().equals("MP3".toUpperCase()))){
			                   //native music captured
			icon ="a_music";
		}else if((ext.toUpperCase().equals("PDF".toUpperCase()))){
			icon ="a_pdf";
		}else if((ext.toUpperCase().equals("PPT".toUpperCase()))||(ext.toUpperCase().equals("PPTX".toUpperCase()))){
			icon ="a_powerpoint";
		}else if((ext.toUpperCase().equals("TXT".toUpperCase()))){
			icon ="a_text";
	 }else if((ext.toUpperCase().equals("MP4".toUpperCase()))||(ext.toUpperCase().equals("3GP".toUpperCase()))){
		 icon ="a_video";		
		}else if((ext.toUpperCase().equals("DOCX".toUpperCase()))||(ext.toUpperCase().equals("DOC".toUpperCase()))||(ext.toUpperCase().equals("DOCM".toUpperCase()))||(ext.toUpperCase().equals("ODT".toUpperCase()))){
			icon ="a_word";
		}else if(ext.toUpperCase().equals("AVI".toUpperCase())||(ext.toUpperCase().equals("MPEG".toUpperCase()))||(ext.toUpperCase().equals("MOV".toUpperCase()))){
			icon ="a_mov";
		}else if((ext.toUpperCase().equals("AAC".toUpperCase()))){
			icon ="a_aac";
		}else if((ext.toUpperCase().equals("BAK".toUpperCase()))){
			icon ="a_bak";
		}else if((ext.toUpperCase().equals("BAT".toUpperCase()))){
			icon ="a_bat";
		}else if((ext.toUpperCase().equals("BIN".toUpperCase()))){
			icon ="a_bin";
		}else if((ext.toUpperCase().equals("CP".toUpperCase()))||ext.toUpperCase().equals("CPP".toUpperCase())){
			icon ="a_cp";
		}else if((ext.toUpperCase().equals("CSS".toUpperCase()))){
			icon ="a_css";
		}else if((ext.toUpperCase().equals("DAT".toUpperCase()))){
			icon ="a_dat";
		}else if((ext.toUpperCase().equals("DLL".toUpperCase()))){
			icon ="a_dll";
		}else if((ext.toUpperCase().equals("EXE".toUpperCase()))){
			icon ="a_exe";
		}else if((ext.toUpperCase().equals("FLV".toUpperCase()))){
			icon ="a_flv";
		}else if((ext.toUpperCase().equals("GIF".toUpperCase()))){
			icon ="a_gif";
		}else if((ext.toUpperCase().equals("ISO".toUpperCase()))){
			icon ="a_iso";
		}else if((ext.toUpperCase().equals("JAVA".toUpperCase()))){
			icon ="a_java";
		}else if((ext.toUpperCase().equals("JS".toUpperCase()))){
			icon ="a_js";
		}else if((ext.toUpperCase().equals("SQL".toUpperCase()))){
			icon ="a_sql";
		}else if((ext.toUpperCase().equals("TTF".toUpperCase()))){
			icon ="a_ttf";
		}else if((ext.toUpperCase().equals("WAV".toUpperCase()))){
			icon ="a_wav";
		}else if((ext.toUpperCase().equals("TIF".toUpperCase()))){
			icon ="a_tif";
		}else
		{
			icon ="a_unknown";
		}
		return icon;
	}
	
	
	
	
	
}
