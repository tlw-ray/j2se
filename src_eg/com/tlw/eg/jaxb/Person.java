package com.tlw.eg.jaxb;

import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

/**
@since 2012-8-28
@version 2012-8-28
@author 唐力伟 (tlw_ray@163.com)
 */
@XmlRootElement
public class Person {

	/**
	 * @param args
	 * @throws JAXBException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws Exception {
		Person p1=new Person();
		p1.setName("AAA");
		p1.setAge(14);
		JAXBContext jc = JAXBContext.newInstance(com.tlw.eg.jaxb.Person.class);
		Marshaller marshaller=jc.createMarshaller();
		marshaller.marshal(p1, System.out);
		
		System.out.println();
		
		Unmarshaller u = jc.createUnmarshaller();
		InputStream is=Person.class.getResourceAsStream("/tlw/example/jaxb/Person.xml");
		Person po = (Person) u.unmarshal(is);
		System.out.println(po);
		System.out.println("finish...");
		
	}
	String name;
	int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String toString(){
		return super.toString()+"[name="+name+", age="+age+"]";
	}
}
