package com.tlw.eg.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.TreeMap;
import java.util.Vector;


/**
@since 2010-5-20
@version 2010-5-20
@author 唐力伟 (tlw_ray@163.com)
尝试TreeMap,HashMap,Vector在直接取对象时的耗时。似乎都非常的快。。。
 */
public class IdentifySpeed {
	static final int START=1024;
	static final int COUNT=300000;
	static final int ID_COUNT=20;
	static HashSet<Integer> dataSource=new HashSet<Integer>();
	static HashMap<Integer,Integer> dataSourceHashMap=new HashMap<Integer,Integer>();
	static TreeMap<Integer,Integer> dataSourceTreeMap=new TreeMap<Integer,Integer>();
	static Vector<Integer> dataSourceVector=new Vector<Integer>();
//	static int[] dataSourceArray=new int[COUNT-START];
	static int[] needIdentifies=new int[]{1025,32768,65535,100000,150000,180000,190000,160000};
	static int[] identifies=new int[needIdentifies.length];
	
	static Random random=new Random();

	public static void main(String[] args) {
		int id=0;
		for(int i=START;i<COUNT;i++){
			dataSourceHashMap.put(i, id);
			dataSourceTreeMap.put(i, id);
//			dataSourceArray[i-START]=i;
			dataSourceVector.add(i);
			id++;
		}

		long t1=System.currentTimeMillis();
		for(int i=0;i<needIdentifies.length;i++){
			int needIdentify=needIdentifies[i];
			Integer identify=dataSourceHashMap.get(needIdentify);
			identifies[i]=identify;
		}
		long t2=System.currentTimeMillis();
		System.out.println("HashMap spend time:"+(t2-t1));
		showArray(identifies);
		
		t1=System.currentTimeMillis();
		for(int i=0;i<needIdentifies.length;i++){
			int needIdentify=needIdentifies[i];
			Integer identify=dataSourceTreeMap.get(needIdentify);
			identifies[i]=identify;
		}
		t2=System.currentTimeMillis();
		System.out.println("TreeMap spend time:"+(t2-t1));
		showArray(identifies);
		
		t1=System.currentTimeMillis();
		for(int i=0;i<needIdentifies.length;i++){
			int needIdentify=needIdentifies[i];
			Integer identify=dataSourceTreeMap.get(needIdentify);
			identifies[i]=identify;
		}
		t2=System.currentTimeMillis();
		System.out.println("TreeMap spend time:"+(t2-t1));
		showArray(identifies);
		
		t1=System.currentTimeMillis();
		for(int i=0;i<needIdentifies.length;i++){
			int needIdentify=needIdentifies[i];
			Integer identify=dataSourceVector.get(needIdentify);
			identifies[i]=identify;
		}
		t2=System.currentTimeMillis();
		System.out.println("Vector spend time:"+(t2-t1));
		showArray(identifies);
		
//		t1=System.currentTimeMillis();
//		for(int i=0;i<needIdentifies.length;i++){
//			int needIdentify=needIdentifies[i];
//			Integer identify=dataSourceTreeMap.get(needIdentify);
//			identifies[i]=identify;
//		}
//		t2=System.currentTimeMillis();
//		System.out.println("TreeMap spend time:"+(t2-t1));
//		showArray(identifies);
	}
	static void showArray(int[] is){
		for(int i=0;i<is.length;i++)System.out.print(is[i]+"\t");
		System.out.println();
	}
}
