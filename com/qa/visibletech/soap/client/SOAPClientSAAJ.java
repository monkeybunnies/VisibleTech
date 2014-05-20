package com.qa.visibletech.soap.client;
import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

import com.qa.visibletech.constants.Consts;

/**
 * http://stackoverflow.com/questions/15940234/how-to-do-a-soap-web-service-call-from-java-class
 * Two approach of soap request, 1) wsimport 2) soap client, see above for pros and cons.
 * @author shuo
 * 
 *
 */
public class SOAPClientSAAJ {
   /**
    * Starting point for the SAAJ - SOAP Client Testing
    */
   
   public void makeSOAPCall () {
      try {
         
         // Create SOAP Connection
         SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
         SOAPConnection soapConnection = soapConnectionFactory.createConnection();

         // Send SOAP Message to SOAP Server
         String url = "http://tomcat01.preprod.vt.net:8080/searchservice/SearchService";
         SOAPMessage soapResponse = soapConnection.call(createEmptySOAPMessage(), url);

         // Process the SOAP Response
         printSOAPResponse(soapResponse);

         soapConnection.close();
      }
      catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   public SOAPMessage createEmptySOAPMessage() throws SOAPException
    {
       SOAPMessage msg = MessageFactory.newInstance().createMessage();
       /* 
        * Here, different ways of accessing the different parts of a 
        * SOAP message are shown. First going via the SOAP part and 
        * the SOAP envelope... 
        */ 
       SOAPPart part         = msg.getSOAPPart();
       SOAPEnvelope envelope = part.getEnvelope();
       SOAPBody body         = envelope.getBody();
       SOAPHeader header     = envelope.getHeader();
       
       System.out.println("The long way:");  
       System.out.println(" The SOAP part: " + part);  
       System.out.println(" The SOAP envelope: " + envelope);  
       System.out.println(" The SOAP body: " + body);  
       System.out.println(" The SOAP header: " + header);  
 
       /* A faster way... */  
       body = msg.getSOAPBody();  
       header = msg.getSOAPHeader();  
 
       System.out.println("The short way:");  
       System.out.println(" The SOAP body: " + body);  
       System.out.println(" The SOAP header: " + header);  
 
       return msg;
    }

    public static SOAPMessage createSOAPRequest() throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI = String.format(Consts.PREPROD, "");

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("example", serverURI);

        /*
        Constructed SOAP Request Message:
        <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:example="http://ws.cdyne.com/">
            <SOAP-ENV:Header/>
            <SOAP-ENV:Body>
                <example:VerifyEmail>
                    <example:email>mutantninja@gmail.com</example:email>
                    <example:LicenseKey>123</example:LicenseKey>
                </example:VerifyEmail>
            </SOAP-ENV:Body>
        </SOAP-ENV:Envelope>
         */

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("VerifyEmail", "example");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("email", "example");
        soapBodyElem1.addTextNode("mutantninja@gmail.com");
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("LicenseKey", "example");
        soapBodyElem2.addTextNode("123");

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", serverURI  + "VerifyEmail");

        soapMessage.saveChanges();

        /* Print the request message */
        System.out.print("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }

    /**
     * Method used to print the SOAP Response
     */
    private static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        System.out.print("\nResponse SOAP Message = ");
        StreamResult result = new StreamResult(System.out);
        transformer.transform(sourceContent, result);
    }

}