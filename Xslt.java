
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.*;
import java.net.URLEncoder;


class Xslt {
    public static void main ( String argv[] ) throws Exception {
	File stylesheet = new File("search.xsl");
	System.out.println("Enter Input string to search :\n");
	Scanner scanner = new Scanner(System.in);
	String input = scanner.nextLine();
	String url = "http://sandbox.api.ebaycommercenetwork.com/publisher/3.0/rest/GeneralSearch?apiKey=78b0db8a-0ee1-4939-a2f9-d3cd95ec0fcc&trackingId=7000610&categoryId=72&keyword="+URLEncoder.encode(input, "UTF-8")+"&numItems=20";
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	DocumentBuilder db = dbf.newDocumentBuilder();
	Document document = db.parse(url);
	StreamSource stylesource = new StreamSource(stylesheet);
	TransformerFactory tf = TransformerFactory.newInstance();
	Transformer transformer = tf.newTransformer(stylesource);
	DOMSource source = new DOMSource(document);
	StreamResult result = new StreamResult(System.out);
	transformer.transform(source,result);
    }
}