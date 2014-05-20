package com.qa.visibletech.thread;

import java.util.concurrent.atomic.AtomicInteger;

import com.qa.visibletech.constants.Consts;
import com.qa.visibletech.enums.ContentSize;
import com.qa.visibletech.interfaces.XmlFileWriter;
import com.qa.visibletech.utils.Utils;
import com.qa.visibletech.utils.WriteVtFullXmlFile;

public class MixedVolumeGenerator implements Runnable {
   
   private volatile boolean isRunning = true;
   
   private AtomicInteger counter = new AtomicInteger();
   
   private XmlFileWriter writer;
   
   private volatile String writerType;
   
   public MixedVolumeGenerator() {
      
      writer = new WriteVtFullXmlFile(Consts.INPUT_FILE_PATH, 600, ContentSize.MIXED);
      
      writerType = "WriteVtFullXmlFile";
      
      counter.set(0);
   }
   
   public MixedVolumeGenerator(String docType) {
      
      writerType = docType;
      
      try {
         ClassLoader loader = ClassLoader.getSystemClassLoader();
         
         String className = String.format("com.qa.visibletech.utils.%s", docType);
         
         Class<?> writerClass = loader.loadClass(className);
         
         writer = (XmlFileWriter) writerClass.newInstance();
         
         counter.set(0);
      }
      catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
      catch (IllegalAccessException e) {
         e.printStackTrace();
      }
      catch (InstantiationException e) {
         e.printStackTrace();
      }
   }
   
   public int getPostCounter() {
      
      return counter.get();
   }

   public void setWriterType(String newType) {
      writerType = newType;
      
      try {
         ClassLoader loader = ClassLoader.getSystemClassLoader();
         
         String className = String.format("com.qa.visibletech.utils.%s", newType);
         
         Class<?> writerClass = loader.loadClass(className);
         
         writer = (XmlFileWriter) writerClass.newInstance();
         
         counter.set(0);
      }
      
      catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
      catch (IllegalAccessException e) {
         e.printStackTrace();
      }
      catch (InstantiationException e) {
         e.printStackTrace();
      }
   }

   public String getWriterType() {
      return writerType;
   }
   
   @Override
   public void run() {
      
      Thread t = Thread.currentThread();
      
      while (!t.isInterrupted() && isRunning) {

         writer.write(Utils.getInstance().generateFileName());
         
         counter.addAndGet(600);
         
         try {
            Thread.sleep(Consts.SHORT_NAP);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   }
   
   public void interrupt() {
      
      if (isRunning) 
         isRunning = false;
   }

}
