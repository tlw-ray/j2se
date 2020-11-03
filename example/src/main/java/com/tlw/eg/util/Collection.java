package com.tlw.eg.util;

import java.util.ArrayDeque;
import java.util.Iterator;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-7-2
@version:2009-7-2
Description:
 */
public class Collection {
	public static void main(String[] args) {
		ArrayDeque ad=new ArrayDeque(10);
		for(int i=0;i<20;i++){
			ad.addFirst(i+"");
		}
		Iterator it=ad.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
}
