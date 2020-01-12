package com.styrish.utils;

	   
	import java.io.BufferedReader;
import java.io.File;
	import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

	import org.apache.tika.exception.TikaException;
	import org.apache.tika.metadata.Metadata;
	import org.apache.tika.parser.ParseContext;
	import org.apache.tika.parser.html.HtmlParser;
	import org.apache.tika.sax.BodyContentHandler;
	import org.xml.sax.SAXException;

	public class ConvertToHTML {
	   public String htmlOtput() throws IOException,SAXException, TikaException {

	      //detecting the file type
	      BodyContentHandler handler = new BodyContentHandler();
	      
	      Metadata metadata = new Metadata();
	   
	     // FileInputStream inputstream = new FileInputStream(new File("/Users/ravirajput/Documents/styrish/documents/sample.html"));
	     
	      StringBuilder contentBuilder = new StringBuilder();
	      try {
	          BufferedReader in = new BufferedReader(new FileReader("/Users/ravirajput/Documents/styrish/documents/sample.html"));
	          String str;
	          while ((str = in.readLine()) != null) {
	              contentBuilder.append(str);
	          }
	          in.close();
	      } catch (IOException e) {
	      }
	      String content = contentBuilder.toString();

	    

	   
	      return content;
	   }                

        
	 

}
