package com.tlw.eg.swing.tree.filter;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.tlw.swing.jtree.UtilJTree;
import com.tlw.swing.tabletree.JTreeTable;
import com.tlw.util.UtilUi;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-7-14
@version:2009-7-14
Description:
 */
public class SchemaViewer extends JPanel implements DocumentListener{
	private static final long serialVersionUID = 631260683084392827L;
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		String plaf="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
		UIManager.setLookAndFeel(plaf);
		SchemaViewer viewer=new SchemaViewer();
		UtilUi.show(viewer,800,600, "OpenPlant Scheme浏览器");
	}
	Document doc=SchemaViewer.getSchemaDocument();
	TableModel4SchemaTree tmodel=new TableModel4SchemaTree(doc);
	JLabel jlabelFilter=new JLabel("Field名称过滤:");
	JTextField jtfFilter=new JTextField();
	JTreeTable jtableTree;

	public SchemaViewer(){
		//initialize layout
		jtableTree=new JTreeTable();
		jtableTree.setTreeTableModel(tmodel);
		initData();
		
		setLayout(new BorderLayout());
		JScrollPane jscrollTable=new JScrollPane(jtableTree);
		add(jscrollTable,BorderLayout.CENTER);
		
		JPanel jpanelNorth=new JPanel();
		jpanelNorth.setLayout(new BorderLayout());
		jpanelNorth.add(jlabelFilter,BorderLayout.WEST);
		jpanelNorth.add(jtfFilter,BorderLayout.CENTER);
		jpanelNorth.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		add(jpanelNorth,BorderLayout.NORTH);
		
		//initialize event
		jtfFilter.getDocument().addDocumentListener(this);
	}

	private void initData(){
		jtableTree.tree.setCellRenderer(new TreeCellRenderer4Xml());
		jtableTree.getColumnModel().getColumn(0).setPreferredWidth(400);
		UtilJTree.expendAll(jtableTree.tree);
		jtableTree.setRowHeight(25);
		//jtableTree.setShowGrid(true);
	}
	//filter change
	public void changedUpdate(DocumentEvent e) {filterChange();}
	public void insertUpdate(DocumentEvent e) {filterChange();}
	public void removeUpdate(DocumentEvent e) {filterChange();}
	public void filterChange(){
		String filter=jtfFilter.getText();
		Node node=doc.cloneNode(true);
		filterNode(node,filter);
		tmodel=new TableModel4SchemaTree(node);
		jtableTree.setTreeTableModel(tmodel);
		initData();
	}
	public void filterNode(Node node,String filter){
		NodeList nl=node.getChildNodes();
		Vector nodeForRemove=new Vector();
		for(int i=0;i<nl.getLength();i++){
			Node childNode=nl.item(i);
			if(childNode.getNodeName().equalsIgnoreCase("Field")){
				Node attrNode=childNode.getAttributes().getNamedItem("name");
				if(attrNode.getNodeValue().toUpperCase().indexOf(filter.toUpperCase())<0){
					nodeForRemove.add(childNode);
				}
			}else{
				filterNode(childNode,filter);
			}
		}
		for(int i=0;i<nodeForRemove.size();i++){
			node.removeChild((Node)nodeForRemove.get(i));
		}
	}
	public static Document getSchemaDocument(){
		InputStream is=SchemaViewer.class.getResourceAsStream("scheme.xml");
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		Document doc=null;
		try {
			doc = dbf.newDocumentBuilder().parse(is);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return doc;
	}
}