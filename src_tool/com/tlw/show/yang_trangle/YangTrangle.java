package com.tlw.show.yang_trangle;

import java.util.Vector;

/**
@since 2010-4-21
@version 2010-4-21
@author 唐力伟 (tlw_ray@163.com)
整齐的杨辉三角形，某公司面试题
 */
public class YangTrangle {
	public static void main(String[] args){
		
		printYong(10);
	}
	static int maxIntTextWidth=0;
	public static void printYong(int rowCount){
		Vector<Vector<Integer>> ans=new Vector<Vector<Integer>>();
		Integer i1=new Integer(1);
		for(int i=1;i<rowCount+1;i++){
			Vector<Integer> values=new Vector<Integer>();
			for(int j=0;j<i+1;j++){
				if(j==0 || j==i || i==0){
					values.add(i1);
				}else{
					int x=ans.get(i-2).get(j-1).intValue();
					int y=ans.get(i-2).get(j).intValue();
					Integer iv=new Integer(x+y);
					if(iv.toString().length()>maxIntTextWidth)maxIntTextWidth=iv.toString().length();
					values.add(iv);
				}
			}
			ans.add(values);
		}
		int maxTextLength=getVectorTextLength(ans.get(ans.size()-1));
		for(int i=0;i<ans.size();i++){
			Vector<Integer> currentInts=ans.get(i);
			int currentTextLength=getVectorTextLength(currentInts);
			int spaceCount=(maxTextLength-currentTextLength)/2;
			for(int j=0;j<spaceCount;j++){
				System.out.print(" ");
			}
			for(int j=0;j<currentInts.size();j++){
				outputInteger(currentInts.get(j));
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	public static int getVectorTextLength(Vector<Integer> vv){
		int result=0;
		for(int i=0;i<vv.size();i++){
			result+=maxIntTextWidth;
		}
		result+=vv.size()-1;
		return result;
	}
	public static void outputInteger(Integer value){
		String valueStr=value.toString();
		int spacePrefix=(maxIntTextWidth-valueStr.length())/2;
		char[] chars=new char[maxIntTextWidth];
		for(int i=0;i<spacePrefix;i++){
			if(i<spacePrefix){
				chars[i]=' ';
			}
		}
		for(int i=0;i<valueStr.length();i++){
			chars[spacePrefix+i]=valueStr.charAt(i);
		}
		for(int i=spacePrefix+valueStr.length();i<chars.length;i++){
			chars[i]=' ';
		}
		for(int i=0;i<maxIntTextWidth;i++){
			System.out.print(chars[i]);
		}
	}
}
