package com.qa.visibletech.thread;

import com.qa.visibletech.constants.Consts;
import com.qa.visibletech.enums.ContentSize;
import com.qa.visibletech.utils.Utils;
import com.qa.visibletech.utils.WriteVtFullXmlFile;

public class SmallVolumeGenerator implements Runnable {
   
   private final WriteVtFullXmlFile w = new WriteVtFullXmlFile(Consts.INPUT_FILE_PATH, 900, ContentSize.LOW);

   @Override
   public void run() {
      // TODO Auto-generated method stub
      
      while (true) {
         
         // generate large VTFull files
         w.write(Utils.getInstance().generateFileName());
         
         try {
            
            Thread.sleep(Consts.SHORT_NAP);
         } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      
   }

}
