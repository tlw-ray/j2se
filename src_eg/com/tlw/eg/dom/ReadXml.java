package com.tlw.eg.dom;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-4-30
@version:2009-4-30
Description:
 */
public class ReadXml {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);//通过这里配置，可以使xml对命名空间敏感
		DocumentBuilder db=dbf.newDocumentBuilder();
		String fileUri=ClassLoader.getSystemClassLoader().getResource("tlw/example/dom/NewFile.xml").toString();
		System.out.println(fileUri);
		Document doc=db.parse(fileUri);
		doc.setXmlStandalone(false);
		
		OutputStreamWriter outWriter =new OutputStreamWriter(System.out, "GBK");
		DOMDemo demo=new DOMDemo(new PrintWriter(outWriter, true));
		Node node=null;
		
		node=doc.getElementsByTagName("xpdl:FormalParameters").item(0);
		showNode(node);

		demo.echo(doc);
	}
	public static void showNode(Node node){
		System.out.println("----------------");
		if(node!=null){
			System.out.println("NodeName:"+node.getNodeName());
			System.out.println("NodeValue:"+node.getNodeValue());
			System.out.println("Prefix:"+node.getPrefix());
			System.out.println("NameSpaceURI:"+node.getNamespaceURI());
		}else{
			System.err.println("Node is null!");
		}
	}
}
