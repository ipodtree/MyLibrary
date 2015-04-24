import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.*;
import org.xml.sax.*;

import javax.xml.parsers.*;

public class parser {
	List<String> listisbn=new ArrayList<String>();
	List<String> listyear=new ArrayList<String>();
	List<String> listtitle=new ArrayList<String>();
	List<String> listauthor=new ArrayList<String>();
	
	public PrintWriter out = null;


	public void parse(String _fichier)
		throws SAXException, ParserConfigurationException, IOException {


		FileInputStream _xml_input_file = new FileInputStream(_fichier);

		parse(_xml_input_file);
	}

	public void parse(InputStream _xml_input_file)
		throws SAXException, ParserConfigurationException, IOException {

		DocumentBuilderFactory _factory = DocumentBuilderFactory.newInstance();
		_factory.setIgnoringComments(true);
		DocumentBuilder _builder = _factory.newDocumentBuilder();
		Document doc = _builder.parse(_xml_input_file);

		
		
		
		
		Element root=doc.getDocumentElement();
		NodeList allChilds=root.getChildNodes();
			
		for (int i = 0; i < allChilds.getLength() ; i++) {
			Node node = allChilds.item(i);
			if (allChilds.item(i).getNodeName()=="bib_ref")
				{Node node2 = allChilds.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element bib_ref = (Element) node2;
					
					Element isbn = (Element) bib_ref.getElementsByTagName("ISBN").item(0);
				    if(isbn.getChildNodes().getLength()!=0)
				    {String Isbn = isbn.getFirstChild(). getNodeValue();
					listisbn.add(Isbn);}
				    else {listisbn.add("");}
				    
				    Element year = (Element) bib_ref.getElementsByTagName("year").item(0);
				    if(year.getChildNodes().getLength()!=0)
				    {String Year = year.getFirstChild(). getNodeValue();
					listyear.add(Year);}
				    else {listyear.add("");}
					
					Element title = (Element) bib_ref.getElementsByTagName("title").item(0);
					if(title.getChildNodes().getLength()!=0)
					{String Title = title.getFirstChild(). getNodeValue();
					listtitle.add(Title);}
				    else {listtitle.add("");}
					
					Element author = (Element) bib_ref.getElementsByTagName("author").item(0);
					if(author.getChildNodes().getLength()!=0)
					{String Author = author.getFirstChild(). getNodeValue();
					listauthor.add(Author);}
				    else {listauthor.add("");}
					
				  }
				}
			  }
		    }
}
