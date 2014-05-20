package com.qa.visibletech.soap.client;

import java.io.ByteArrayOutputStream;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.Provider;
import javax.xml.ws.Service;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceProvider;

@ServiceMode(value = Service.Mode.PAYLOAD)

@WebServiceProvider()

public class SoapProvider implements Provider<Source>{

   @Override
   public Source invoke(Source msg) {
      
      // get payload
      StreamResult sr = new StreamResult();
      
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      
      sr.setOutputStream(out);
      
      try {
         
         Transformer trans = TransformerFactory.newInstance().newTransformer();
         
         trans.transform(msg, sr);
      }
      catch (TransformerException e) {
         
         e.printStackTrace();
      }
      return msg;
   }

}
