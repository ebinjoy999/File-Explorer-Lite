package com.ebin.fileexp.lite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

import android.os.Environment;

public class Move_Copy_Delete {

	
	// your sd card
	String sdCard = Environment.getExternalStorageDirectory().toString();
	//Toast.makeText(getBaseContext(),""+sdCard, 10).show();
	// the file to be moved or copied
	File sourceLocation = new File (sdCard + "/sample.txt");
	
	// make sure your target location folder exists!
	File targetLocation = new File (sdCard +"/MyNewFolder/sample.txt");

	// just to take note of the location sources
	//Log.v(TAG,"sourceLocation: " + sourceLocation);
//	Log.v(TAG,"targetLocation: " + targetLocation);
	
//////////////////////////////////////// COPY ///////////////////////////////////////////////	


    public static void copyFolder(File src, File dest) throws IOException{
 
               if(src.isDirectory()){
 
    		                                  //if directory not exists, create it
    	                                                	if(!dest.exists()){
    		                                                        dest.mkdir();
    		                               System.out.println("Directory copied from " + src + "  to " + dest);
    	                                                                               	}
                             
    		                                                     // /list all the directory contents
    	     String files[] = src.list();
 
    		for (String file : files) {
    		   //construct the src and dest file structure
    		   File srcFile = new File(src, file);
    		   File destFile = new File(dest, file);
    		   //recursive copy
    		   copyFolder(srcFile,destFile);
    		}
 
    	}else{
    		//if file, then copy it
    		//Use bytes stream to support all file types
    		InputStream in = new FileInputStream(src);
    	        OutputStream out = new FileOutputStream(dest); 
 
    	        byte[] buffer = new byte[1024];
 
    	        int length;
    	        //copy the file content in bytes 
    	        while ((length = in.read(buffer)) > 0){
    	    	   out.write(buffer, 0, length);
    	        }
 
    	        in.close();
    	        out.close();
    	        System.out.println("File copied from " + src + " to " + dest);
    	}
    }




////////////////////////////////////////       MOVE      /////////////////////////////////////////////////////////////

public boolean movefile(File sourceLocation,File targetLocation)
{
	
		
		// 1 = move the file, 2 = copy the file
		int actionChoice = 1;
		
		// moving the file to another directory
		if(actionChoice==1){
			
//			if(sourceLocation.renameTo(targetLocation)){
//		//		Log.v(TAG, "Move file successful.");  
//				return true;
//			}else{
//		//		Log.v(TAG, "Move file failed.");
//				return false;
//			}
		try{	
			FileInputStream fis = new FileInputStream(sourceLocation);
			FileOutputStream fos = new FileOutputStream(targetLocation);
		 FileChannel	in = fis.getChannel();
		 FileChannel out = fos.getChannel();
		 in.transferTo(0, in.size(), out);
			
			
			fis.close();
			fos.close();
			return true;
		}catch(Exception exc){}
		}
		return false;	
 }



///////////////////////////////////////////   DELETE    /////////////////////////////////////////

public boolean delete_file(File file_del){

	
	boolean status;

	if(file_del.isDirectory())
		  for(File child  :  file_del.listFiles())
			  delete_file(child);
	
	   status =   file_del.delete();
//	File file = new File(path);
//	
//	if(file.isDirectory()){
//		
//		String[] children = file.list();
//		for(int i = 0; i<children.length;i++){
//			new File(file , children[i]).delete();
//			return true;
//		}
//	}else{
//	boolean deleted = file.delete();
//	return deleted;
//	
//	}
//	return false;
   
return status;
           }



}

	

