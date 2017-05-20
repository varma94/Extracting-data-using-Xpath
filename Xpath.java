import javax.xml.xpath.*;
import org.xml.sax.InputSource;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.util.*;
import java.net.URLEncoder;

class Xpath {

    static void print ( Node e ) {
	if (e instanceof Text)
	    System.out.print(((Text) e).getData());
	else {
	    NodeList c = e.getChildNodes();
	    System.out.print("<"+e.getNodeName());
	    NamedNodeMap attributes = e.getAttributes();
	    for (int i = 0; i < attributes.getLength(); i++)
		System.out.print(" "+attributes.item(i).getNodeName()
				 +"=\""+attributes.item(i).getNodeValue()+"\"");
	    System.out.print(">");
	    for (int k = 0; k < c.getLength(); k++)
		print(c.item(k));
	    System.out.print("</"+e.getNodeName()+">");
	}
    }

    static void eval ( String query, String url ) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder = factory.newDocumentBuilder();
	Document doc = builder.parse(url);
	XPathFactory xpathFactory = XPathFactory.newInstance();
    XPath xpath = xpathFactory.newXPath();
	XPathExpression expr = xpath.compile(query);
	NodeList result = (NodeList) xpath.evaluate(query,doc,XPathConstants.NODESET);
	System.out.println("\nXPath query: "+query+"\n");
	for (int i = 0; i < result.getLength(); i++)
	    print(result.item(i));
	System.out.println();
    }

    public static void main ( String[] args ) throws Exception {
    Scanner scanner = new Scanner(System.in);
    String ch = "y";
    do{
    System.out.println("Enter product you want to search:\n");
    String product = scanner.nextLine();
    System.out.println("\n\n Enter the keyword you want to serach in the xml data: \n");
    String searchWord = scanner.nextLine();
      System.out.println("\n\nFull description of all products which have rating greater than 2");
      eval("//categories/category/items/product[rating/rating>2]/fullDescription","http://sandbox.api.ebaycommercenetwork.com/publisher/3.0/rest/GeneralSearch?apiKey=78b0db8a-0ee1-4939-a2f9-d3cd95ec0fcc&trackingId=7000610&categoryId=72&keyword="+URLEncoder.encode(product, "UTF-8")+"&numItems=20");
      System.out.println("\n\nPrint the name and the minimum price of all products whose name contains the word "+searchWord);
      eval("//categories/category/items/product[contains(translate(name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),\'"+searchWord+"\')]/name | //categories/category/items/product[contains(name,\'"+searchWord+"\')]/minPrice","http://sandbox.api.ebaycommercenetwork.com/publisher/3.0/rest/GeneralSearch?apiKey=78b0db8a-0ee1-4939-a2f9-d3cd95ec0fcc&trackingId=7000610&categoryId=72&keyword="+URLEncoder.encode(product, "UTF-8")+"&numItems=20");
	  System.out.println("\n\nPrint the names of all products whose name contains the word "+searchWord+" and the price is between $1 and $3000, inclusive.");
	  eval("//categories/category/items/product[contains(translate(name,'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),\'"+searchWord+"\') and minPrice>=1 and minPrice<=3000]/name","http://sandbox.api.ebaycommercenetwork.com/publisher/3.0/rest/GeneralSearch?apiKey=78b0db8a-0ee1-4939-a2f9-d3cd95ec0fcc&trackingId=7000610&categoryId=72&keyword="+URLEncoder.encode(product, "UTF-8")+"&numItems=20");
    System.out.println("Do you want to continue y|n ?");
    ch = scanner.nextLine();
    }while( !ch.equalsIgnoreCase("n"));
    }
}
