public class SAXSearch{
  public static void main(String[] args) {
    if(args.length!=1){
      System.err.println("usage: java SAXSearch publisher");
      return;
    }

    try{
      XMLReader xmlr = XMLReaderFactory.createXMLReader();
      Handler handler = new Handler();
      xmlr.setContentHandler(handler);
      xmlr.setErrorHandler(handler);
      xmlr.setProperty("http://xml.org/sax/properties/lexical-handler", handler);
      xmlr.parse(new InputSource(new FileReader("books.xml")));
    }catch(IOException ioe){
      ioe.printStackTrace();
    }catch(SAXException saxe){
      saxe.printStackTrace();
    }
  }
}

public class Handler extends DefaultHandler2{
  private boolean isPublisher, isTitle;
  private String isbn, publisher, pubYear, title, srchText;

  public Handler(String srchText){
    this.srchText = srchText;
  }

  @Override
  public void character(){char[] ch, int start, int length}{
    if(isTitle){
      title = new String(ch, start, length).trim();
      isTitle = false;
    }else if(isPublisher){
      publisher = new String(ch, start, length).trim();
      isPublisher = false;
    }
  }

  @Override
  public void endElement(String uri, String localName, String qName){
    if(!localName.equals("book") || !srchText.equals(publisher)){
      return;
    }
    System.out.println("title = " + title + ", isbn = " + isbn);
  }

  @Override
  public void error(SAXParseException saxpe){
    saxpe.printStackTrace();
  }

  @Override
  public void fatalError(SAXParseException saxpe){
    saxpe.printStackTrace();
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes){
    if(localName.equals("title")){
      isTitle = true;
      return;
    }else if(localName.equals("publisher")){
      isPublisher = true;
      return;
    }
    if(!localName.equals("book")){
      return;
    }
    for(int i=0; i<attributes.getLength();i++){
      if(attributes.getLocalName(i).equals("isbn")){
        isbn = attributes.getValue(i);
      }else if(attributes.getLocalName().equals("pubyear")){
        pubYear = attributes.getValue(i);
      }
    }
  }

  @Override
  public void warning(SAXParseException saxpe){
    System.out.println("warning() " + saxpe);
  }
}
