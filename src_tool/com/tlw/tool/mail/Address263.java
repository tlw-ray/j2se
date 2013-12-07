package com.tlw.tool.mail;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-7-6
@version:2009-7-6
Description:将263右侧的XML格式通讯录转化为CSV格式
 */
public class Address263 {
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		URL url=ClassLoader.getSystemResource("tlw/tool/mail/Address263.xml");
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db=dbf.newDocumentBuilder();
		Document doc=db.parse(url.getPath());
		FileWriter fw=new FileWriter("D:\\263通讯录.csv");
		fw.write("部门,邮箱地址,姓名\n");
		iterat(doc.getChildNodes(),fw);
		fw.flush();
		fw.close();
	}
	public static void iterat(NodeList nl,Writer writer) throws IOException{
		for(int i=0;i<nl.getLength();i++){
			Node node=nl.item(i);
			if(node.getNodeName().equalsIgnoreCase("info")){
				if(node.getAttributes().getNamedItem("type").getNodeValue().equalsIgnoreCase("child")){
					Node nodeId=node.getAttributes().getNamedItem("id");
					Node nodeName=node.getAttributes().getNamedItem("name");
					String dept=node.getParentNode().getAttributes().getNamedItem("name").getNodeValue();
					String id="";
					String name="";
					if(nodeId!=null)id+=nodeId.getNodeValue();
					if(nodeName!=null)name+=nodeName.getNodeValue();
					writer.write(dept+","+id+","+name+"\n");
				}
			}
			iterat(node.getChildNodes(),writer);
		}
	}
}