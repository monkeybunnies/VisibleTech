package com.qa.visibletech.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.qa.visibletech.constants.Consts;

/**
 * 
 * @author shuo
 *
 */
public class Ingestion {
   
   private String ingestionPath;
   
   private String logPath;
   
   private String logName;
   
   /**
    * 
    * @param path - path of midas ingestion
    * @param logPath - path of midas log file
    * @param logName - file name of midas log
    */
   public Ingestion (String path, String logPath, String logName) {
      
      this.ingestionPath = path;
      
      this.logPath = logPath;
      
      this.logName = logName;
   }
   
   /**
    * @param filePath - path of the source file
    * @param fileName - name of the source file
    * @return true if Midas ingestion succeeded, false if timeout and Midas ingestion failed
    */
   public boolean ingestFile (String filePath, String fileName) {
      
      try {
         
         Utils.getInstance().moveFile(filePath, ingestionPath, fileName);
         
      } catch (Exception e) {
         
         e.printStackTrace();
         
      }
      
      // Record start time
      long start = System.currentTimeMillis();
      
      while ( ! ( (System.currentTimeMillis() - start) > Consts.LONG_NAP ) ) {
         
         int nFiles = new File(ingestionPath).listFiles(Utils.fileFilter).length;
         
         // File has been ingested by Midas
         if (! (nFiles == 1) ) {
            try {
               
               Thread.sleep(Consts.SHORT_NAP);
            } 
            catch (InterruptedException e) {
               
               e.printStackTrace();
            }
         }
         else {
            
            return true;
         }
      }
      
      //  ingestion failed
      return false;
   }
   
   /**
    * 
    * @param filePath
    * @param fileName
    * @return
    */
   public boolean ingestFiles (String filePath, String[] fileNames) {
      
      try {
         
         Utils.getInstance().moveFiles(filePath, ingestionPath, fileNames);
         
      } catch (Exception e) {
         
         e.printStackTrace();
         
      }
      
      // Record start time
      long start = System.nanoTime();
      
      while ( ! ( (System.nanoTime() - start) > Consts.LONG_NAP ) ) {
         
         int nFiles = new File(ingestionPath).listFiles(Utils.fileFilter).length;
         
         // File has been ingested by Midas
         if (! (nFiles == 0) ) {
            try {
               
               Thread.sleep(Consts.SHORT_NAP);
            } 
            catch (InterruptedException e) {
               
               e.printStackTrace();
            }
         }
         else {
            
            return true;
         }
      }
      
      //  ingestion failed
      return false;
   }

   /**
    * 
    * @return if Midas has un-ingested files, return false. Otherwise return true.
    */
   public int getMidasStatus() {
      
      return new File(ingestionPath).listFiles(Utils.fileFilter).length;
      
   }
   
   public List<String> getMidasLogErrors() {
      
      List<String> errors = new ArrayList<String> ();
      
      String logFile = Utils.getInstance().filePathFormatter(logPath, logName);
      
      BufferedReader br = null;
      
      String line = "";
      
      String splitBy = " ";
      
      try {
         
         br = new BufferedReader(new FileReader(logFile));
         
         while ( (line = br.readLine()) != null ) {
            
            // use single space as separator
            String[] temp = line.split(splitBy);
            
            if (temp[2].equals(Consts.FATAL) || temp[2].equals(Consts.ERROR)) {
               
               errors.add(line);
            }
         }
      }
      
      catch (Exception e) {
         
         e.printStackTrace();
      }
      
      return errors;
   }
}
