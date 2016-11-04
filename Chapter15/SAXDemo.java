import java.io.FileReader;
import java.io.IOException;
import java.io.File;

import java.util.Map;
import java.util.HashMap;

import org.xml.sax.XMLReader;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import org.xml.sax.helpers.XMLReaderFactory;

import org.xml.sax.ext.DefaultHandler2;

public class SAXDemo{
	public static void main(String[] args) throws SAXException, IOException{
		if(args.length<1 || args.length>2){
			System.err.println("usage: java SAXDemo xmlFile [v]");
			return;
		}
		XMLReader xmlr = XMLReaderFactory.createXMLReader();
		if(args.length==2 && args[1].equalsIgnoreCase("v")){
			xmlr.setFeature("http://xml.org/sax/features/validation", true);
		}
		xmlr.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
		
		Handler handler = new Handler();
		xmlr.setContentHandler(handler);
		xmlr.setDTDHandler(handler);
		xmlr.setEntityResolver(handler);
		xmlr.setErrorHandler(handler);
		xmlr.setProperty("http://xml.org/sax/properties/lexical-handler", handler);
		
		xmlr.parse(new InputSource(new FileReader(args[0])));
	}
}

class Handler extends DefaultHandler2{
	private Locator locator;
	
	private Map<String, String> dtdMapping;

	Handler(){
		super();
		// Improve the entity resolve efficiency through local dtd resource.
		File file = new File("recipeml.dtd");
		if(file.exists()&&file.isFile()){
			dtdMapping = new HashMap<String, String>();
			dtdMapping.put("-//FormatData//DTD RecipeML 0.5//EN", "recipeml.dtd"); 
			// the DTD file is in current directory
		}
	}

	@Override
	public void characters(char[] ch, int start, int length){
		System.out.print("characters() [");
		for(int i=start;i<start + length;i++){
			System.out.print(ch[i]);
		}
		System.out.println("]");
	}
	
	@Override
	public void comment(char[] ch, int start, int length){
		System.out.print("comment() [");
		for(int i=start;i<start + length;i++){
			System.out.print(ch[i]);
		}
		System.out.println("]");
	}
	
	@Override
	public void endCDATA(){
		System.out.println("endCDATA()");
	}
	
	@Override
	public void endDocument(){
		System.out.println("endDocument()");
	}
	
	@Override
	public void endDTD(){
		System.out.println("endDTD()");
	}
	
	@Override
	public void endElement(String uri, String localName, String qName){
		System.out.print("endElement() ");
		System.out.print("uri=" + uri);
		System.out.print(", localName=" + localName);
		System.out.println(", qName=" + qName);
	}
	
	@Override
	public void endEntity(String name){
		System.out.printf("endEntity() name=%s%n", name);
	}
	
	@Override
	public void endPrefixMapping(String prefix){
		System.out.printf("endPrefixMapping() prefix=%s%n", prefix);
	}
	
	@Override
	public void error(SAXParseException saxpe){
		System.out.println("error() " + saxpe);
	}
	
	@Override
	public void fatalError(SAXParseException saxpe){
		System.out.println("fatalError() " + saxpe);
	}
	
	@Override
	public void ignorableWhitespace(char[] ch, int start, int length){
		System.out.print("ignorableWhitespace() [");
		for(int i=start;i<start + length;i++){
			System.out.print(ch[i]);
		}
		System.out.println("]");
	}
	
	@Override
	public void notationDecl(String name, String publicId, String systemId){
		System.out.printf("notationDecl() name=%1$s, publicId=%2$s, systemId=%3$s%n", 			name, publicId, systemId);
	}
	
	@Override
	public void processingInstruction(String target, String data){
		System.out.printf("processingInstruction() target=%1$s, data=%2$s%n", 
		target, data);
	}
	
	@Override
	public InputSource resolveEntity(String publicId, String systemId){
		System.out.printf("resolveEntity() publicId=%1$s, systemId=%2$s%n", 
		publicId, systemId);
		// Do not perform a remapping.
		InputSource is = new InputSource();
		is.setPublicId(publicId);
		if(dtdMapping!=null&&dtdMapping.containsKey(publicId)){
			is.setSystemId(dtdMapping.get(publicId));
			System.out.println("obtaining cached local recipeml.dtd");
		}else{
			is.setSystemId(systemId);
		}
		return is; 
		// if you return null here, it's equavilent to request the parser to use system identifier.
	}
	
	@Override
	public void setDocumentLocator(Locator locator){
		this.locator = locator;
		System.out.println("setDocumentLocator() locator=" + locator);
	}
	
	@Override
	public void skippedEntity(String name){
		System.out.println("skippedEntity() name=" + name);
	}
	
	@Override
	public void startCDATA(){
		System.out.println("startCDATA()");
	}
	
	@Override
	public void startDocument(){	
		System.out.println("startDocument()");
	}
	
	@Override
	public void startDTD(String name, String publicId, String systemId){
		System.out.printf("startDTD() name=%1$s, publicId=%2$s, systemID=%3$s%n", 
					name, publicId, systemId);
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes){
		System.out.print("startElement() uri=" + uri);
		System.out.print(", localName=" + localName);
		System.out.println(", qName=" + qName);
		for(int i=0;i<attributes.getLength();i++){
			System.out.println("Attribute: " + attributes.getLocalName(i) + ", " + attributes.getValue(i));
		}
		// Explores the position of the element start.
		System.out.println("Column number=" + locator.getColumnNumber() + ", line number=" + locator.getLineNumber());
	}
	
	@Override
	public void startEntity(String name){
		System.out.println("startEntity() name=" + name);
	}
	
	@Override
	public void startPrefixMapping(String prefix, String uri){
		System.out.printf("startPrefixMapping() prefix=%1$s, uri=%2$s%n", prefix, uri);
	}
	
	@Override
	public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName){
		System.out.printf("unparsedEntityDecl() name=%1$s, publicId=%2$s, systemId=%3$s, notationName=%4$s%n");
	}
	
	@Override
	public void warning(SAXParseException saxpe){
		System.out.println("warning() " + saxpe);
	}
}
