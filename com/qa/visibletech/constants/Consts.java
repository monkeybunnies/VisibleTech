package com.qa.visibletech.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// Read this reference http://www.javapractices.com/topic/TopicAction.do?Id=2
// for best practice in java
public final class Consts {
   
   //  Server URI
   public static final String LOCALHOST = "http://localhost:8080/";
   
   public static final String QA        = "http://apvip.qa.viq:8080/";
   
   public static final String PREPROD   = "http://preprod-inside-V2APISearchDataGeo.preprod.viq:8080/";
   
   //  For Midas ingestion time   
   public static final long VERY_LONG_NAP = 1800000;
   
   public static final long LONG_NAP = 30000;
   
   public static final long SHORT_NAP = 500;
   
   //  Input file path and xml nodes name   
   public static final String INPUT_FILE_PATH = "/home/shuo/docs/";
   
   //  Midas ingestion path
   public static final String INGESTION_PATH = "/mnt/midas-sync-source/";
   
   public static final String MIDAS_LOG_PATH = "/var/log/midas/";
   
   public static final String MIDAS_LOG_NAME = "midas.log";
   
   public static final String DOCUMENT_TAG = "DocumentElement";
   
   public static final String CONTENT_TAG = "Content";
   
   public static final String CONTENT_ID_TAG = "ContentID";
   
   public static final String CONTENT_DOMAIN_TAG = "Domain";
   
   public static final String CONTENT_SITE_TAG = "Site";
   
   public static final String CONTENT_PERMALINK_TAG = "Permalink";
   
   public static final String CONTENT_PUBLISHDT_TAG = "PublishedOnDT";
   
   public static final String CONTENT_TITLE_TAG = "Title";
   
   public static final String CONTENT_AUTHOR_TAG = "Author";
   
   public static final String CONTENT_AUTHORLINK_TAG = "AuthorLink";
   
   public static final String CONTENT_BODY_TAG = "Body";
   
   public static final String CONTENT_SOURCEFILENAME_TAG = "SourceFileName";
   
   public static final String CONTENT_DESTINFILENAME_TAG = "DestinationFileName";
   
   public static final String CONTENT_LINKS_TAG = "Links";
   
   public static final String CONTENT_ATTRIB_TAG = "Attributes";
   
   public static final String CONTENT_FOUNDDT_TAG = "FoundDT";
   
   public static final String CONTENT_NORMALIZEDT_TAG = "NormalizeDT";
   
   public static final String CONTENT_COLLECTIONTYPE_TAG = "CollectionSourceType";
   
   public static final String CONTENT_COLLECTIONTYPES_TAG = "CollectionSourceTypes";
   
   public static final String CONTENT_COLLECTIONID_TAG = "CollectionSourceID";
   
   public static final String CONTENT_WORKSPACES_TAG = "Workspaces";
   
   public static final String CONTENT_WORKSPACEID_TAG = "WorkspaceID";
   
   public static final String CONTENT_CONVERSATIONID_TAG = "ConversationID";
   
   //  Log file fields name
   public static final String FATAL = "FATAL";
   
   public static final String ERROR = "ERROR";
   
   public static final String WARN  = "WARN";
   
   public static final String INFO  = "INFO";
   
   public static final String DEBUG = "DEBUG";
   
   //  Blob gen seeds
   public static final String[] OBJECTIVES = {"Mary", "Bob", "Matt", "Lucas", "Dave", "Sharon", 
      "Yong", "Melody", "Pam", "Jessie"};
   
   public static final String[] VERBS = {"pogo", "say", "sleep", "be", "brush", "hit", "hide", 
      "go", "see", "play"};
   
   public static final String[] ADJS = {"nervous", "shy", "good", "excellent", "worst", "poor", 
      "patient", "fantastic", "obnoxious", "failing"};
   
   public static final String[] URLS = {"http://www.zappa.rock.net", 
      "http://www.david.bowie.net", "http://www.OquirrhMountain.net", "http://www.dweezil.com", "https://www.disney.parks.com"};
   
   public static final String SFP = "E:\\3xIngestion\\Normalization\\Source\\";
   
   public static final String DFP = "E:\\3xIngestion\\Normalization\\Output\\";
   
   public static final String[] CLID = {"STS_34"};
   
   public static final String[] DATES_OLD = {"2013-07-01 00:00:00.000",
         "2013-06-30 24:00:00.000",
         "2013-06-30 23:59:59.000",
         "2013-04-01 00:00:00.000",
         "2013-04-15 23:59:59.000",
         "2013-03-01 00:00:00.000",
         "2013-01-01 00:00:00.000",
         "2013-08-16 00:00:00.000",
         "2013-07-16 00:00:00.000",
         "2013-06-15 24:00:00.000",
         "2013-03-15 23:59:59.000",
         "2013-02-01 00:00:00.000",
         "2013-01-01 00:00:00.000",
         "2013-05-01 00:00:00.000"};
   
   public static final String[] DATES_NEW = {"2013-10-19 23:59:59.999",
      "2013-10-30 24:00:00.000",
      "2013-10-25 23:59:59.000",
      "2013-10-20 00:00:00.000",
      "2013-10-29 23:59:59.000",
      "2013-10-26 00:00:00.000",
      "2013-10-22 00:00:00.000",
      "2013-11-05 00:00:00.000",
      "2013-11-10 00:00:00.000",
      "2013-11-15 24:00:00.000",
      "2013-11-15 23:59:59.000",
      "2013-11-17 00:00:00.000",
      "2013-11-16 00:00:00.000",
      "2013-11-09 00:00:00.000"};
   
   public static final String[] ACTION_CODE = {"add", "change", "delete"};

   public static final String CONTENTID = "0F0F0FFFFFFFFFFFFFFFFFFFFFFFFFFF";
   
   public static final Map<String, String> CL;
   static {
      Map<String, String> a = new HashMap<String, String>();
      
      a.put("STS_34", "34");
      a.put("STS_77", "77");
      a.put("STS_11", "11");
      a.put("STS_24", "24");
      a.put("STS_15", "15");
      a.put("STS_810", "810");
     
      CL = Collections.unmodifiableMap(a);
   }
   
   public static final String WORKSPACEID = "WS_810";

   public static final int LOW = 10;
   
   public static final int MEDIUM = 50;
   
   public static final int HIGH = 1000;
   
   // PRIVATE //
   
   /**
    The caller references the constants using <tt>Consts.EMPTY_STRING</tt>, 
    and so on. Thus, the caller should be prevented from constructing objects of 
    this class, by declaring this private constructor. 
   */
   private Consts(){
      //this prevents even the native class from 
      //calling this ctor as well :
      throw new AssertionError();
   }
}
