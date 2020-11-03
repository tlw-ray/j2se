package com.tlw.tools.laf;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.JTree;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;

import com.tlw.swing.Shower;


public class A01_JTreeLaf extends JTree{
	private static final long serialVersionUID = 4893204139333548534L;
	public static void main(String[] args){
		TreeMap<String,TreeSet<String>> componentPropertiesMap=getComponentPropertiesMap();
		Iterator<String> componentNames=componentPropertiesMap.keySet().iterator();
		DefaultMutableTreeNode treeNodeRoot=new DefaultMutableTreeNode("Look And Feel");
		while(componentNames.hasNext()){
			String componentName=componentNames.next();
			DefaultMutableTreeNode treeNodeComponent=new DefaultMutableTreeNode(componentName);
			treeNodeRoot.add(treeNodeComponent);
			Iterator<String> properties=componentPropertiesMap.get(componentName).iterator();
			while(properties.hasNext()){
				String propertyName=properties.next();
				DefaultMutableTreeNode treeNodeProperty=new DefaultMutableTreeNode(propertyName);
				treeNodeComponent.add(treeNodeProperty);
			}
		}
		
		JTree jtree=new JTree(treeNodeRoot);
		Shower.show(jtree);
		
		System.out.println(classTypes.size());
		for(Class<?> cls:classTypes){
			System.out.println(cls.getName());
		}
	}
	static TreeMap<String,TreeSet<String>> componentPropertiesMap=new TreeMap<String,TreeSet<String>>();
	static Set<Class<?>> classTypes=new HashSet<Class<?>>();
	public static TreeMap<String,TreeSet<String>> getComponentPropertiesMap(){
		UIDefaults uiDefaults=UIManager.getDefaults();
		Enumeration<Object> keys=uiDefaults.keys();
		while(keys.hasMoreElements()){
			Object obj=keys.nextElement();
			if(obj instanceof String){
				String uiKey=(String)obj;
				String[] uiKeySplit=uiKey.split("\\.");
				if(uiKeySplit.length==2){
					TreeSet<String> properties=componentPropertiesMap.get(uiKeySplit[0]);
					if(properties==null){
						properties=new TreeSet<String>();
						componentPropertiesMap.put(uiKeySplit[0], properties);
					}
					properties.add(uiKey);
					Object value=uiDefaults.get(uiKey);
					if(value!=null){
						classTypes.add(value.getClass());
					}
				}
			}
		}
		return componentPropertiesMap;
	}
}