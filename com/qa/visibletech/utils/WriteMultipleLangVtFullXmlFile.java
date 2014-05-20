package com.qa.visibletech.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.qa.visibletech.constants.Consts;
import com.qa.visibletech.enums.ContentSize;
import com.qa.visibletech.interfaces.XmlFileWriter;
 
public class WriteMultipleLangVtFullXmlFile extends WriteVtFullXmlFile implements XmlFileWriter {

   public WriteMultipleLangVtFullXmlFile(String path, int nodeCount, ContentSize bodyLength) {
      
      super(path, nodeCount, bodyLength);
   
   }
   
   public WriteMultipleLangVtFullXmlFile() {
      super(Consts.INPUT_FILE_PATH, 600, ContentSize.MIXED);
   }

   public void write (String fileName) {
      
      Random rand = new Random();

      try {
        
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
       
        // root element - DocumentElement
        Document doc = docBuilder.newDocument();
      
        Element documentElement = doc.createElement(Consts.DOCUMENT_TAG);
        
        doc.appendChild(documentElement);
        
        if (volume == ContentSize.LOW) {
           // for loop to add content elements
           for (int i = 0; i < nodeCount; ++ i) {
              
              setContentElement(fileName, doc, documentElement, Consts.LOW);
           }
        }
        else if (volume == ContentSize.MEDIUM) {
           // for loop to add content elements
           for (int i = 0; i < nodeCount; ++ i) {
              
              setContentElement(fileName, doc, documentElement, Consts.MEDIUM);
           }
        }
        else if (volume == ContentSize.HIGH) {
           // for loop to add content elements
           for (int i = 0; i < nodeCount; ++ i) {
              
              setContentElement(fileName, doc, documentElement, Consts.HIGH);
           }
        }
        else if (volume == ContentSize.MIXED) {
           // for loop to add content elements
           for (int i = 0; i < nodeCount; ++ i) {
              
              setContentElement(fileName, doc, documentElement, rand.nextInt((Consts.HIGH - Consts.LOW) + 1) + Consts.LOW);
           }
        }

        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        
        Transformer transformer = transformerFactory.newTransformer();
        
        DOMSource source = new DOMSource(doc);
        
        StreamResult result = new StreamResult(new File(Utils.getInstance().filePathFormatter(filePath, fileName)));
       
        transformer.transform(source, result);
       
      } 
      catch (ParserConfigurationException pce) {
         pce.printStackTrace();
      } 
      catch (TransformerException tfe) {
      
         tfe.printStackTrace();
      }
   }
   
   private synchronized void setContentElement (String fileName, Document doc, Element documentElement, int contentSize) {
      
      RandomString rs = new RandomString(32);
      
      Random ri = new Random();
      
      int d = Consts.URLS.length;
      
      //  add content element
      Element content = doc.createElement(Consts.CONTENT_TAG);
      
      documentElement.appendChild(content);

      Element contentId = doc.createElement(Consts.CONTENT_ID_TAG);
      
      //  set contentID
      contentId.setTextContent(rs.nextString());
      
      content.appendChild(contentId);
      
      Element domain = doc.createElement(Consts.CONTENT_DOMAIN_TAG);
      
      // set Domain
      domain.setTextContent(Consts.URLS[ri.nextInt(d)]);
      
      content.appendChild(domain);
      
      Element site = doc.createElement(Consts.CONTENT_SITE_TAG);
      
      // set Site
      site.setTextContent(Consts.URLS[ri.nextInt(d)]);
      
      content.appendChild(site);
      
      Element permalink = doc.createElement(Consts.CONTENT_PERMALINK_TAG);
      
      // set Permalink
      permalink.setTextContent(Consts.URLS[ri.nextInt(d)]);
      
      content.appendChild(permalink);
      
      Element pubDate = doc.createElement(Consts.CONTENT_PUBLISHDT_TAG);
      
      pubDate.setTextContent(Utils.getInstance().getRandomPublishedDT());
      
      content.appendChild(pubDate);
      
      Element title = doc.createElement(Consts.CONTENT_TITLE_TAG);
      
      title.setTextContent(rs.nextString());
      
      content.appendChild(title); 
      
      Element author = doc.createElement(Consts.CONTENT_AUTHOR_TAG);
      
      author.setTextContent(rs.nextString());
      
      content.appendChild(author);
      
      Element authorLink = doc.createElement(Consts.CONTENT_AUTHORLINK_TAG);
      
      authorLink.setTextContent(Consts.URLS[ri.nextInt(d)]);
      
      content.appendChild(authorLink);
      
      Element body = doc.createElement(Consts.CONTENT_BODY_TAG);
      
      body.setTextContent(getRandomLanguageText());
      
      content.appendChild(body);
      
      Element srcFile = doc.createElement(Consts.CONTENT_SOURCEFILENAME_TAG);
      
      srcFile.setTextContent(String.format("%s%s", Consts.SFP, fileName));
      
      content.appendChild(srcFile);
      
      Element destFile = doc.createElement(Consts.CONTENT_DESTINFILENAME_TAG);
      
      destFile.setTextContent(String.format("%s%s", Consts.DFP, fileName));
      
      content.appendChild(destFile);
      
      Element links = doc.createElement(Consts.CONTENT_LINKS_TAG);
      
      content.appendChild(links);
      
      Element attribs = doc.createElement(Consts.CONTENT_ATTRIB_TAG);
      
      content.appendChild(attribs);
      
      Element foundDate = doc.createElement(Consts.CONTENT_FOUNDDT_TAG);
      
      foundDate.setTextContent(Utils.getInstance().getCurrentDateTime());
      
      content.appendChild(foundDate);
      
      Element normDate = doc.createElement(Consts.CONTENT_NORMALIZEDT_TAG);
      
      normDate.setTextContent(Utils.getInstance().getCurrentDateTime());
      
      content.appendChild(normDate);
      
      Element cltSrcType = doc.createElement(Consts.CONTENT_COLLECTIONTYPE_TAG);
      
      cltSrcType.setTextContent(Consts.CL.get(Consts.CLID[0]));
      
      content.appendChild(cltSrcType);
      
      Element cltSrcTypes = doc.createElement(Consts.CONTENT_COLLECTIONTYPES_TAG);
      
      content.appendChild(cltSrcTypes);
      
      Element cltSrcId = doc.createElement(Consts.CONTENT_COLLECTIONID_TAG);
      
      cltSrcId.setTextContent(Consts.CLID[0]);
      
      cltSrcTypes.appendChild(cltSrcId);
      
      Element workspaces = doc.createElement(Consts.CONTENT_WORKSPACES_TAG);
      
      content.appendChild(workspaces);
      
      Element workspaceId = doc.createElement(Consts.CONTENT_WORKSPACEID_TAG);
      
      workspaceId.setTextContent(Consts.WORKSPACEID);
      
      workspaces.appendChild(workspaceId);
      
      Element cvstId = doc.createElement(Consts.CONTENT_CONVERSATIONID_TAG);
      
      cvstId.setTextContent(rs.nextString());
      
      content.appendChild(cvstId);
   }
   
   private synchronized String getRandomLanguageText() {
      Map<String, String> map = Utils.getInstance().getLanguages();
      
      String text = "";
      
      if (map == null || map.size() == 0) {
         System.out.println("Something wrong, language csv file hasn't been parsed correctly.");
         
         return text;
      }
      
      Random random = new Random();
      
      List<String> keys = new ArrayList<String>(map.keySet());
      
      String randomKey = keys.get(random.nextInt(keys.size()));
      
      text = map.get(randomKey);
      
      return text;
   }
}