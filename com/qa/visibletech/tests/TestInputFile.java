package com.qa.visibletech.tests;

import org.testng.annotations.Test;

import com.qa.visibletech.constants.Consts;
import com.qa.visibletech.thread.FilesMover;
import com.qa.visibletech.thread.MixedVolumeGenerator;

public class TestInputFile {
   
   @Test
   public void testIngestNoTitle() {
      
/*      MixedVolumeGenerator dupContentID = new MixedVolumeGenerator("WriteDupContentIDVtFullXmlFile");
*/      
      MixedVolumeGenerator multiLang = new MixedVolumeGenerator("WriteMultipleLangVtFullXmlFile");
      
/*      MixedVolumeGenerator mixedLang = new MixedVolumeGenerator("WriteMixedLangVtFullXmlFile");
*/      
/*      Thread[] noTitle = {new Thread(new MixedVolumeGenerator("WriteNoTitleVtFullXmlFile")),
            new Thread(new MixedVolumeGenerator("WriteNoTitleVtFullXmlFile"))
      };
      
      Thread[] noBody = {new Thread(new MixedVolumeGenerator("WriteNoBodyVtFullXmlFile")),
            new Thread(new MixedVolumeGenerator("WriteNoBodyVtFullXmlFile"))
      };

      Thread[] noTitleAndBody = {new Thread(new MixedVolumeGenerator("WriteNoTitleNoBodyVtFullXmlFile")),
            new Thread(new MixedVolumeGenerator("WriteNoTitleNoBodyVtFullXmlFile"))
      };

      Thread[] mixed = {new Thread(new MixedVolumeGenerator("WriteMixedNoTitleOrBodyVtFullXmlFile")),
            new Thread(new MixedVolumeGenerator("WriteMixedNoTitleOrBodyVtFullXmlFile"))
      };*/
      
      Thread[] languages = {new Thread(multiLang),
            new Thread(multiLang)
      };

      for (int i = 0; i < 2; ++ i) {
         
/*         noTitle[i].start();
         
         noBody[i].start();
         
         noTitleAndBody[i].start();
         
         mixed[i].start();
*/         
         languages[i].start();
      }
      
      // Record start time
      long start = System.currentTimeMillis();
      
      while ( ! ( (System.currentTimeMillis() - start) > 60 ) ) {
         
         try {
            Thread.sleep(30000);
         } catch (InterruptedException e) {

            e.printStackTrace();
         }
      }
      
      for (int j = 0; j < 2; ++ j) {
         
/*         if (noTitle[j] != null) noTitle[j].interrupt();
         
         if (noBody[j] != null) noBody[j].interrupt();

         if (noTitleAndBody[j] != null) noTitleAndBody[j].interrupt();
         
         if (mixed[j] != null) mixed[j].interrupt();*/
 
         if (languages[j] != null) languages[j].interrupt();
      }
      
      System.out.println("Total number of posts generated : " + multiLang.getPostCounter());
      
      Thread moveFiles = new Thread(new FilesMover(Consts.INPUT_FILE_PATH, Consts.INGESTION_PATH));
      
      moveFiles.start();
      
   }
  
   
/*   @Test
   public void test2() {
      
      Thread[] high = {new Thread(new HighVolumeGenerator()), 
            new Thread(new HighVolumeGenerator()),
            new Thread(new HighVolumeGenerator()),
            new Thread(new HighVolumeGenerator()),
            new Thread(new HighVolumeGenerator()),
            new Thread(new HighVolumeGenerator())
      };
      
      Thread[] mixed = {new Thread(new MixedVolumeGenerator()),
            new Thread(new MixedVolumeGenerator()),
            new Thread(new MixedVolumeGenerator()),
            new Thread(new MixedVolumeGenerator()),
            new Thread(new MixedVolumeGenerator()),
            new Thread(new MixedVolumeGenerator())
      };
      
      Thread[] low = {new Thread(new SmallVolumeGenerator()),
            new Thread(new SmallVolumeGenerator()),
            new Thread(new SmallVolumeGenerator()),
            new Thread(new SmallVolumeGenerator()),
            new Thread(new SmallVolumeGenerator()),
            new Thread(new SmallVolumeGenerator())
      };
      
      for (int i = 0; i < 6; ++ i) {
         
         high[i].start();
         
         mixed[i].start();
         
         low[i].start();
         
      }
      
      // Record start time
      long start = System.currentTimeMillis();
      
      while ( ! ( (System.currentTimeMillis() - start) > 60 ) ) {
         
         try {
            Thread.sleep(30000);
         } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      
      for (int j = 0; j < 6; ++ j) {
         
         if (high[j] != null) high[j].interrupt();
         
         if (mixed[j] != null) mixed[j].interrupt();
         
         if (low[j] != null) low[j].interrupt();
      }
   }
*/
}
