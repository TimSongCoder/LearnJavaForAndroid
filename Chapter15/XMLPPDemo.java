import java.io.FileReader;
import java.io.IOException;

public class XMLPPDemo{
	
	public static void main(String[] args) throws IOException, XmlPullParserException{
		if(args.length!=1){
			System.err.println("usage: java XMLPPDemo xmlfile");
			return;
		}
		XmlPullParserFactory xmppFactory = XmlPullParserFactory.newInstance();
		xmppFactory.setNamespaceAware(true);
		xmlPullParser xmlpp = xmppFactory.newPullParser();
		
		xmlpp.setInput(new FileReader(args[0]));
		int eventType = xmlpp.getEventType();
		while(eventType!=XmlPullParser.END_DOCUMENT){
			switch(eventType){
			 	case XmlPullParser.START_DOCUMENT:
			 		System.out.println("Start document");
			 		break;
			 	case XmlPullParser.START_TAG:
			 		System.out.println("Start tag: " + xmlpp.getName());
			 		break;
			 	case XmlPullParser.TEXT:
			 		System.out.println("Text: " + xmlpp.getText());
			 		break;
			 	case XmlPullParser.ENT_TAG:
			 		System.out.println("End tag: " + xmlpp.getName());
			}
			eventType = xmlpp.next();
		}
	}
}
