package com.qa.visibletech.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.qa.visibletech.constants.Consts;

/**
 * 
 * @author shuo
 *
 */
public class InputFileParser {
   
   private File xmlFile;
   
   private DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
   
   private DocumentBuilder builder;
   
   private Document xmlDoc;
   
   //private Map<String, String> contentMap;
   
   /**
    * 
    * @param path - input file path
    * @param name - input file name
    * @throws ParserConfigurationException
    * @throws SAXException
    * @throws IOException
    */
   public InputFileParser (String path, String name) throws ParserConfigurationException, SAXException, IOException {
      
      xmlFile = new File(Utils.getInstance().filePathFormatter(path, name));
      
      builder = builderFactory.newDocumentBuilder();
      
      xmlDoc = builder.parse(xmlFile);
      
      //optional, but recommended
      //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
      xmlDoc.getDocumentElement().normalize();
      
   }
   
   /**
    * 
    * @return ingest document root node name
    */
   public String getRootNodeName () {
      
      return xmlDoc.getDocumentElement().getNodeName();
   }
   
   /**
    * 
    * @return number of content node in the xml file
    * 
    */
   public int getContentCount () {
      
      NodeList contentList = xmlDoc.getElementsByTagName(Consts.CONTENT_TAG);
      
      return contentList.getLength();
   }
   
   /**
    * 
    * @return map of (key, value) where key is the contentID and value is the content Body
    * 
    */
   public Map<String, String> getContentBody() {
      
      Map<String, String> contentMap = new HashMap<String, String> ();
      
      NodeList contentList = xmlDoc.getElementsByTagName(Consts.CONTENT_TAG);
      
      for (int i = 0; i < contentList.getLength(); ++ i) {
         
         Node contentNode = contentList.item(i);
         
         if (contentNode.getNodeType() == Node.ELEMENT_NODE) {
            
            Element e = (Element) contentNode;
            
            contentMap.put(e.getElementsByTagName(Consts.CONTENT_ID_TAG).item(0).getTextContent(), 
                  e.getElementsByTagName(Consts.CONTENT_BODY_TAG).item(0).getTextContent());
         }
      }
      return contentMap;
   }
   
   public void setContentBody(String bodyText) {
      
      NodeList contentList = xmlDoc.getElementsByTagName(Consts.CONTENT_TAG);
      
      for (int i = 0; i < contentList.getLength(); ++ i) {
         
         Node contentNode = contentList.item(i);
         
         if (contentNode.getNodeType() == Node.ELEMENT_NODE) {
            
            Element e = (Element) contentNode;
            
            e.getElementsByTagName(Consts.CONTENT_BODY_TAG).item(0).setTextContent(bodyText);
         }
      }
   }
   
   /**
    * 
    * @return map of (newId, oldId) content IDs
    */
   public Map<String, String> manipulatedContentIds() {
      
      Map<String, String> contentIdMap = new HashMap<String, String> ();
      
      NodeList contentList = xmlDoc.getElementsByTagName(Consts.CONTENT_TAG);
      
      for (int i = 0; i < contentList.getLength(); ++ i) {
         
         Node contentNode = contentList.item(i);
         
         if (contentNode.getNodeType() == Node.ELEMENT_NODE) {
            
            Element e = (Element) contentNode;
            
            String id    = e.getElementsByTagName(Consts.CONTENT_ID_TAG).item(0).getTextContent();
            
            // Generate a new content ID based on the old ID,
            // need to do this in order to let Midas re-process the content
            String newId = Utils.getInstance().shuffle(id);
            
            // Update the content ID to the new ID
            e.getElementsByTagName(Consts.CONTENT_ID_TAG).item(0).setTextContent(newId);
            
            contentIdMap.put(newId, id);
         }
      }
      return contentIdMap;
   }
}
