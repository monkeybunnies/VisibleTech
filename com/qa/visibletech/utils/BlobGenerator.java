package com.qa.visibletech.utils;

import java.util.Random;

import com.qa.visibletech.constants.Consts;

import simplenlg.framework.NLGFactory;
import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.realiser.english.*;

public class BlobGenerator {
   
   public BlobGenerator() { }
   
   public String generateText(int length) {
      
      Lexicon lexicon = Lexicon.getDefaultLexicon();
      
      NLGFactory nlgFactory = new NLGFactory(lexicon);
      
      Random rn = new Random();
      
      int objLen = Consts.OBJECTIVES.length;
      
      int vbLen  = Consts.VERBS.length;
      
      int adjLen = Consts.ADJS.length;
      
      String output = "";
      
      for (int i = 0; i < length; i = i + 3) {
         
         SPhraseSpec p = nlgFactory.createClause(Consts.OBJECTIVES[rn.nextInt(objLen)], 
               Consts.VERBS[rn.nextInt(vbLen)], 
               Consts.ADJS[rn.nextInt(adjLen)]);
         
         Realiser r = new Realiser(lexicon);
         
         String s = r.realiseSentence(p);
         
         output = output + s;
      }
      
      return output;
   }
   
   

}
