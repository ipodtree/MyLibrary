import java.io.*;


import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.IOException;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
 
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
public class addxml {

	public PrintWriter out = null;

	
	public void parse(String _fichier,String a,String b,String c,String d)
		throws SAXException, ParserConfigurationException, IOException, TransformerException {
		
		FileInputStream _xml_input_file = new FileInputStream(_fichier);
		
		parse(_xml_input_file,a,b,c,d);
	}

	public void parse(InputStream _xml_input_file,String a,String b,String c,String d)
		throws SAXException, ParserConfigurationException, IOException, TransformerException {

		DocumentBuilderFactory _factory = DocumentBuilderFactory.newInstance();

		_factory.setIgnoringComments(true);
		
		DocumentBuilder _builder = _factory.newDocumentBuilder();

		Document doc = _builder.parse(_xml_input_file);
		
		Element root=doc.getDocumentElement();
		Element bib_ref=doc.createElement("bib_ref");
		root.appendChild(bib_ref);
		
		Element ISBN=doc.createElement("ISBN");
		ISBN.setTextContent(a);;
		Element year=doc.createElement("year");
		year.setTextContent(b);
		Element title=doc.createElement("title");
		title.setTextContent(c);
		Element author=doc.createElement("author");
		author.setTextContent(d);
		
		bib_ref.appendChild(ISBN);
		bib_ref.appendChild(year);
		bib_ref.appendChild(title);
		bib_ref.appendChild(author);
		
		
	          
	          TransformerFactory transformerFactory=TransformerFactory.newInstance();
	          Transformer transformer=transformerFactory.newTransformer();
	          DOMSource domSource=new DOMSource(doc);
	          transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	          StreamResult result=new StreamResult(new FileOutputStream("bib.xml"));
	          transformer.transform(domSource, result);
	            
	    
	}
}

	



