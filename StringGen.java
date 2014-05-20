import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class StringGen {
   private static final char[] symbols = new char[36];

   static {
     for (int idx = 0; idx < 10; ++idx) {
       symbols[idx] = (char) ('0' + idx);
     }
     for (int idx = 10; idx < 36; ++idx) {
       symbols[idx] = (char) ('a' + idx - 10);
     }
   }

   private final Random random = new Random();

   //private final char[] buf;
   
   public StringGen() {
   }

   public String nextString(int length) {
      if (length < 1)
         throw new IllegalArgumentException("length < 1: " + length);
      
      char[] buf = new char[length];
      
      for (int idx = 0; idx < buf.length; ++idx) {
         buf[idx] = symbols[random.nextInt(symbols.length)];
      }
      return new String(buf);
   }
   
   public String asciiString() {
       String out = "";
       for (int i = 0; i < 256; i ++) {
           out += String.valueOf((char)i);
       }
       return out;
   }
   
   public String generateDate() {
      
      Date today = new Date();
      
      SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
      
      return ft.format(today);

   }
   
   private String dt() {
      
      Date d = new Date();
      
      SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmssS");
      
      return ft.format(d);

   }
   
   public String generateFilename() {
      String[] pfx = {"31264_%s.xml", "13720AX_%s.xml", "4329%s.xml"};
      return String.format(pfx[nextInt(3)], dt());
   }
   
   public int nextInt(int x) {
      return random.nextInt(x);
   }
}
