import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;

import org.xml.sax.SAXException;

public class DOMDemo{

	public static void main(String[] args){
		if(args.length!=1){
			System.err.println("usage: java DOMDemo xmlfile");
			return;
		}
		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// configure the document builder factory
			dbf.setNamespaceAware(true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(args[0]);
			System.out.printf("version=%s, encoding=%s, standalone=%b%n", 
							doc.getXmlVersion(), doc.getXmlEncoding(), doc.getXmlStandalone());
			if(doc.hasChildNodes()){
				NodeList nl = doc.getChildNodes();
				for(int i=0;i<nl.getLength();i++){
					Node node = nl.item(i);
					if(node.getNodeType() == Node.ELEMENT_NODE){
						dumpElement((Element)node);
					}
				}
			}
		}catch(FactoryConfigurationError fce){
			fce.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}catch(ParserConfigurationException pce){
			pce.printStackTrace();
		}catch(SAXException se){
			se.printStackTrace();
		}
	}
	
	static void dumpElement(Element element){
		System.out.printf("Element: nodename=%s, localname=%s, prefix=%s, namespaceUri=%s%n", 
						element.getNodeName(), element.getLocalName(), element.getPrefix(), element.getNamespaceURI());
		NamedNodeMap nnm = element.getAttributes(); // dump attributes
		if(nnm!=null){
			for(int i=0;i<nnm.getLength();i++){
				Node node = nnm.item(i);
				Attr attr = (Attr)node;
				System.out.printf("  Attribute: %s = %s%n", attr.getName(), attr.getValue());
			}
		}
		if(element.hasChildNodes()){
			NodeList nl = element.getChildNodes();
			for(int i=0;i<nl.getLength();i++){
				Node node = nl.item(i);
				if(node instanceof Element){
					dumpElement((Element)node);
				}
			}
		}
	}
}
