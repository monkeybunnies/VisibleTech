package com.qa.visibletech.utils;

import java.util.Random;

public class RandomString {
   
   private static final char[] symbols = new char[16];

   static {
      
     for (int idx = 0; idx < 10; ++idx)
       symbols[idx] = (char) ('0' + idx);
     for (int idx = 10; idx < 16; ++idx)
       symbols[idx] = (char) ('A' + idx - 10);
   }

   private final Random random = new Random();

   private final char[] buf;

   public RandomString(int length)
   {
     if (length < 1)
       throw new IllegalArgumentException("length < 1: " + length);
     buf = new char[length];
   }

   public String nextString()
   {
     for (int idx = 0; idx < buf.length; ++idx) 
       buf[idx] = symbols[random.nextInt(symbols.length)];
     return new String(buf);
   }

}
