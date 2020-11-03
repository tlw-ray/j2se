package com.tlw.eg.jaxb;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年7月22日
 */
public class Ser2 {

	public static void main(String[] args) {
//		List<JButton> btns=new ArrayList<JButton>();
//		for(int i=0;i<100;i++){
//			JButton btn=new JButton(i+"name");
//			btns.add(btn);
//		}
		
		List l=new ArrayList();
		l.set(100, "aa");
		
//		Person p1=new Person(){
//			public void calc(){
//				System.out.println(getName());
//			}
//		};
//		List<Person> btns=new ArrayList<Person>();
//		for(int i=0;i<100;i++){
//			Person btn=new Person();
//			btns.add(btn);
//		}
//		
//		JAXB.marshal(btns, "aaa.xml");
//		
//		List list=JAXB.unmarshal(new File("aaa.xml"), List.class);
//		
//		for(Object obj:list){
//			System.out.println(list);
//		}
	}

}
