import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class FileGen {
   FileOutputStream fop;
   File file;
   private String content;
   private String path;
   private StringGen str;
   
   public FileGen(String path) {
      this.path = path;
      this.content = Const.PAYLOAD;
      str = new StringGen();
   }

   public void generateFile() {
      String fileName = path + str.generateFilename();
      try {
         
         file = new File(fileName);
         
         fop = new FileOutputStream(file);
 
         // if file doesnt exists, then create it
         if (!file.exists()) {
            file.createNewFile();
         }
         
         String tx = String.format(content, str.nextString(6), str.generateDate(), 
               str.nextString(100), str.nextString(20), str.nextInt(100), str.nextInt(1000), 
               str.nextInt(5000), str.nextInt(100000), str.nextString(200), 
               str.nextString(20), str.nextString(20), str.nextString(1000000), 
               str.nextString(6));
 
         // get the content in bytes
         byte[] contentInBytes = tx.getBytes();
 
         fop.write(contentInBytes);
         fop.flush();
         fop.close();
 
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         try {
            if (fop != null) {
               fop.close();
            }
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }
}
