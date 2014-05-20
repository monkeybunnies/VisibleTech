package com.qa.visibletech.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

import com.qa.visibletech.constants.Consts;

/**
 * 
 * @author shuo
 *
 */
public class Utils {
   
   private static Utils utils = new Utils();
   
   public static MyFileFilter fileFilter;
   
   /* A private Constructor prevents any other 
    * class from instantiating.
    */
   private Utils() {
      
      fileFilter = new MyFileFilter();
   }
   
   /* Static 'instance' method */
   public static Utils getInstance() {
      return utils;
   }
   
   /**
    * 
    * @param input - input string to be shuffled
    * @return random shuffle of the characters in the input string
    */
   public String shuffle(String input) {
      
      List<Character> characters = new ArrayList<Character>();
      
      for(char c:input.toCharArray()){
         
          characters.add(c);
      }
      
      StringBuilder output = new StringBuilder(input.length());
      
      while(characters.size()!=0){
         
          int randPicker = (int)(Math.random()*characters.size());
          
          output.append(characters.remove(randPicker));
      }
      
      return output.toString();
   }
   
   public String filePathFormatter(String path, String name) {
      
      if (path.charAt(path.length() - 1) != '/') path = path + "/";
      
      return String.format("%s%s", path, name);
   }
   
   public void moveFile (String oldPath, String newPath, String fileName) throws Exception {
      
      try {
         
         File original = new File(oldPath);
         
         if ( !original.renameTo( new File(filePathFormatter(newPath, fileName)) ) )
            
            throw new Exception("move file from " + oldPath + 
                  
                  " to " + filePathFormatter(newPath, fileName) + " failed !");
      }
      catch (Exception e) {
         
         e.printStackTrace();
      }
   }
   
   public void moveFiles (String oldPath, String newPath, String[] fileNames) {
      
      for (int i = 0; i < fileNames.length; ++ i) {
      
         try {
            
            File original = new File(filePathFormatter(oldPath, fileNames[i]));
            
            if ( !original.renameTo( new File(filePathFormatter(newPath, fileNames[i])) ) )
               
               throw new Exception("move file from " + filePathFormatter(oldPath, fileNames[i]) + 
                     
                     " to " + filePathFormatter(newPath, fileNames[i]) + " failed !");
         }
         catch (Exception e) {
            
            e.printStackTrace();
         }
      }
   }
   
   public static class MyFileFilter implements FileFilter {

      @Override
      public boolean accept(File pathName) {
         // test whether the file is a directory, and
         // return false if it is.
         // reference http://stackoverflow.com/questions/4218422/get-the-number-of-files-in-a-folder-omitting-subfolders
         return ! pathName.isDirectory();
      }
   }
   
   public String getCurrentDateTime() {
      
      Date dNow = new Date();
      
      SimpleDateFormat dateFormat = (SimpleDateFormat) DateFormat.getInstance();
      dateFormat.applyPattern("yyyy-MM-dd HH:mm:ss.SSS");
      dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
      
      return dateFormat.format(dNow);
   }
   
   public String convertDateTime(Date date) {
      
      SimpleDateFormat dateFormat = (SimpleDateFormat) DateFormat.getInstance();
      dateFormat.applyPattern("yyyy-MM-dd HH:mm:ss.SSS");
      dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
      
      return dateFormat.format(date);
   }
   
   public String getCurrentDateTime(String pattern) {
      Date dNow = new Date();
      
      SimpleDateFormat dateFormat = (SimpleDateFormat) DateFormat.getInstance();
      
      dateFormat.applyPattern(pattern);
      
      dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
      
      return dateFormat.format(dNow);
   }

   public synchronized String getRandomPublishedDT() {
      Random random = new Random();
      
      return Consts.DATES_OLD[random.nextInt(Consts.DATES_OLD.length)];
   }

   public synchronized String generateFileName() {
      
      Date today = new Date();
      
      SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmssS");
      
      return String.format("%s.xml", ft.format(today));

   }
   
   public Map<String, String> getLanguages() {
      BufferedReader br = null;
      
      String line = "";
      
      String delimiter = ",";
      
      String path = "/home/shuo/workspace/PlatformTests/config/languages.csv";
      
      Map<String, String> maps = new HashMap<String, String> ();
      
      try {
         
         br = new BufferedReader(
               new InputStreamReader(
                     new FileInputStream(path), 
                     "UTF-8"));
         
         while ((line = br.readLine()) != null) {
            String[] languages = line.split(delimiter);
            
            maps.put(languages[0], languages[1]);
         }
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         if (br != null) {
            try {
               br.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      }
      return maps;
   }
}
