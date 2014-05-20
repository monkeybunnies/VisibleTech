public class Main {

   /**
    * @param args
    * @throws InterruptedException 
    * 
    */
   public static void main(String[] args) {
      String input = args[0];
      
      FileGen fg = new FileGen(input);
      
      for (;;) {
         fg.generateFile();
         try {
            Thread.sleep(100);
         } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }

}
