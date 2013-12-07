/*
 * Created on 2006-10-8
 */
package com.tlw.serialize;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
//import javax.swing.tree.DefaultMutableTreeNode;
//import org.dom4j.*;

/**
 * <title>使用XML文件存取可序列化的对象的类</title> <description>提供保存和读取的方法</description>
 *
 * @author 殷晋 <copyright>清华大学汽车工程开发研究院@2005</copyright>
 * @version 1.0 2005-8-5 16:44:49
 */
public class Serializer {
    /**
     * 把java的可序列化的对象(实现Serializable接口)序列化保存到XML文件里面,如果想一次保存多个可序列化对象请用集合进行封装
     * 保存时将会用现在的对象原来的XML文件内容
     *
     * @param obj
     *            要序列化的可序列化的对象
     * @param fileName
     *            带完全的保存路径的文件名
     * @throws FileNotFoundException
     *             指定位置的文件不存在
     * @throws IOException
     *             输出时发生异常
     * @throws Exception
     *             其他运行时异常
     */
    public static void obj2Xml(Object obj, String fileName)
            throws FileNotFoundException, IOException, Exception {
        // 创建输出文件
        File fo = new File(fileName);
        // 文件不存在,就创建该文件
        //if (!fo.exists()) {
            // 先创建文件的目录
            //String path = fileName.substring(0, fileName.lastIndexOf('.'));
            //File pFile = new File(path);
            //pFile.mkdirs();
        //}
        // 创建文件输出流
        FileOutputStream fos = new FileOutputStream(fo);
        // 创建XML文件对象输出类实例
        XMLEncoder encoder = new XMLEncoder(fos);
        // 对象序列化输出到XML文件
        encoder.writeObject(obj);
        encoder.flush();
        // 关闭序列化工具
        encoder.close();
        // 关闭输出流
        fos.close();
    }
    /**
     * 读取由objSource指定的XML文件中的序列化保存的对象,返回的结果经过了List封装
     *
     * @param objSource
     *            带全部文件路径的文件全名
     * @return 由XML文件里面保存的对象构成的List列表(可能是一个或者多个的序列化保存的对象)
     * @throws FileNotFoundException
     *             指定的对象读取资源不存在
     * @throws IOException
     *             读取发生错误
     * @throws Exception
     *             其他运行时异常发生
     */
    public static List xml2Obj(String objSource) throws FileNotFoundException,
            IOException, Exception {
        List objList = new ArrayList();
        File fin = new File(objSource);
        FileInputStream fis = new FileInputStream(fin);
        XMLDecoder decoder = new XMLDecoder(fis);
        Object obj = null;
        try {
            while ((obj = decoder.readObject()) != null) {
                objList.add(obj);
            }
        } catch (Exception e) {
        }
        fis.close();
        decoder.close();
        return objList;
    }
    //对象持久为二进制
    public static void obj2Bin(String fileName,Object obj){
        try {
            java.io.FileOutputStream objfile = new java.io.FileOutputStream(
                    fileName);
            java.io.ObjectOutputStream p = new java.io.ObjectOutputStream(
                    objfile);
            p.writeObject(obj);
            p.flush();
            objfile.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    //从二进制文件读取对象
    public static Object bin2Obj(String fileName){
        FileInputStream objfile = null;
        try {
            objfile = new java.io.FileInputStream(fileName);
            ObjectInputStream q = new ObjectInputStream(objfile);
            Object obj = q.readObject();
            return obj;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static Document file2Xml(File file){
    	try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return null;
    }
    public static void xml2file(Document doc,String filename){
        try {
            OutputStream os = new FileOutputStream(filename);
            OutputStreamWriter osw=new OutputStreamWriter(os,"utf-8");
            osw.write(doc.getTextContent());
            osw.flush();
            osw.close();
            os.close();
        } catch (Exception e) {
            System.out.println("Xml数据持久化到文件时出错!");
            e.printStackTrace();
        }

    }
}
