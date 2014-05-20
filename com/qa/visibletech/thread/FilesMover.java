package com.qa.visibletech.thread;

import java.io.File;

import com.qa.visibletech.utils.Utils;

public class FilesMover implements Runnable{
   
   private final File targetFolder;
   
   private final File destinationFolder;
   
   private volatile boolean isRunning = true;
   
   public FilesMover (String targetPath, String destinationPath) {
      
      targetFolder      = new File(targetPath);
      
      destinationFolder = new File(destinationPath);
   }

   // Move all files from one folder to another folder
   // see link
   // http://stackoverflow.com/questions/16752025/recursively-moving-files-from-one-directory-to-another-only-partially-completes
   @Override
   public void run() {
      
      Thread t = Thread.currentThread();
      
      while (!t.isInterrupted() && isRunning) {
      
         File[] files = targetFolder.listFiles();
         
         if (files.length == 0) {
            isRunning = false;
            
            break;
         }
         
         for (int i = 0; i < files.length; ++ i) {
            
            try {
               Utils.getInstance().moveFile(files[i].getPath(), destinationFolder.getPath(), files[i].getName());
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      }
      
   }

}
