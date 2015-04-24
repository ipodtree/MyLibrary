import java.io.*;

import org.w3c.dom.*;
import org.xml.sax.*;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.*;

import java.util.List;
import java.util.ArrayList;
public class deletexml {

	public PrintWriter out = null;

	
	public void parse(String _fichier,String x)
		throws SAXException, ParserConfigurationException, IOException, TransformerException {
		
		FileInputStream _xml_input_file = new FileInputStream(_fichier);
		
		parse(_xml_input_file,x);
	}

	public void parse(InputStream _xml_input_file,String x)
		throws SAXException, ParserConfigurationException, IOException, TransformerException {

		DocumentBuilderFactory _factory = DocumentBuilderFactory.newInstance();

		_factory.setIgnoringComments(true);
		
		DocumentBuilder _builder = _factory.newDocumentBuilder();

		Document doc = _builder.parse(_xml_input_file);
		
		Element root=doc.getDocumentElement();
		NodeList allChilds=root.getChildNodes();
	
			
			List<Node> list=new ArrayList<Node>();
	          for (int i = 0; i < allChilds.getLength() ; i++) {
				Node node = allChilds.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
				list.add(node);	}
				}


	          for (int i = 0; i < list.size();i++)
	          {Element bib_ref=(Element)list.get(i);
	          Element isbn = (Element) bib_ref.getElementsByTagName("ISBN").item(0);
	          if(isbn.getFirstChild().getNodeValue().equals(x)){
	          bib_ref.getParentNode().removeChild(bib_ref);}
	          }
	          
	          TransformerFactory transformerFactory=TransformerFactory.newInstance();
	          Transformer transformer=transformerFactory.newTransformer();
	          DOMSource domSource=new DOMSource(doc);
	          transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	          StreamResult result=new StreamResult(new FileOutputStream("bib.xml"));
	          transformer.transform(domSource, result);
	            
	    
	}
}

	



