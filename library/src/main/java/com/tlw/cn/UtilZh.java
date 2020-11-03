// Created on 2008-6-19
package com.tlw.cn;

public class UtilZh {
    /// 实现阿拉伯数字到大写中文的转换
    /// 没有对非法数字进行判别
    /// 作者：tlw_ray@163.com

    static final String stringChnNames="零一二三四五六七八九";
    //String StringChnNames="零壹贰叁肆伍陆柒捌玖";
    static final String stringNumNames="0123456789";
    private static char zh2num(char x)
    {
        return stringChnNames.charAt(stringNumNames.indexOf(x));
    }
    private static String changeIntegerBlockLessWan(String x)
    {
        String[] stringArrayLevelNames=new String[] {"","十","百","千"};
        String ret="";
        int i;
        for (i=x.length()-1;i>=0;i--)
            if (x.charAt(i)=='0')
               ret=zh2num(x.charAt(i))+ret;
            else
                ret=zh2num(x.charAt(i))+stringArrayLevelNames[x.length()-1-i]+ret;
        String str00=stringChnNames.charAt(0)+""+stringChnNames.charAt(0);
        while ((ret.indexOf(str00))>=0)
            ret=ret.replaceAll(str00, stringChnNames.charAt(0)+"");
        if (ret.charAt(ret.length()-1)==stringChnNames.charAt(0) 
                && ret.length()>1)
            ret=ret.substring(0,ret.length()-1);
        if (ret.length()>=2 && ret.substring(0,2)=="一十")
            ret=ret.substring(1);
        return ret;
    }
    private static String changeIntegerBlock(String x)
    {
        int len=x.length();
        String ret,temp;
        if (len<=4)
            ret=changeIntegerBlockLessWan(x);
        else if (len<=8)
        {
            ret=changeIntegerBlockLessWan(x.substring(0,len-4))+"万";
            temp=changeIntegerBlockLessWan(x.substring(len-4,x.length()));
            if (temp.indexOf("千")==-1 && temp!="")
                ret+="零"+temp;
            else
                ret+=temp;
        }
        else
        {
            ret=changeIntegerBlockLessWan(x.substring(0,len-8))+"亿";
            temp=changeIntegerBlockLessWan(x.substring(len-8,len-4));
            if (temp.indexOf("千")==-1 && temp!="")
                ret+="零"+temp;
            else
                ret+=temp;
            ret+="万";
            temp=changeIntegerBlockLessWan(x.substring(len-4,len));
            if (temp.indexOf("千")==-1 && temp!="")
                ret+="零"+temp;
            else
                ret+=temp;
        }
        ret.replaceAll("零万", "零");
        while(ret.indexOf("零零")>=0)ret=ret.replaceAll("零零", "零");
        while(ret.endsWith("零") && ret.length()>1)ret=ret.substring(0,ret.length()-1);
        return ret;
    }

    private static String changeFloatBlock(String x)
    {
        String ret="";
        for (int i=0;i<x.length();i++)
            ret+=zh2num(x.charAt(i));
        return ret;
    }
    public static String num2zh(int d){
    	return num2zh(new Integer(d));
    }
    public static String num2zh(double d){
    	return num2zh(new Double(d));
    }
    public static String num2zh(Number n){
        return num2zh(n+"");
    }
    private static String num2zh(String x)
    {
        if (x.length()==0)
            return "";
        String ret="";
        if (x.charAt(0)=='-'){
            ret="负";
            x=x.substring(1);
        }
        if (x.charAt(0)=='.')x="0"+x;
        if (x.endsWith("."))x=x.substring(0,x.length()-1);
        if (x.indexOf(".")>-1)
            ret+=changeIntegerBlock(x.substring(0,x.indexOf(".")))+"点"+changeFloatBlock(x.substring(x.indexOf(".")+1));
        else
            ret+=changeIntegerBlock(x);
        if(ret.startsWith("一十"))ret=ret.substring(1,ret.length());
        return ret;
    }
    public static String addYuanJiaoFen(float f){
        String str=num2zh(new Float(f));
        int idxYuan=str.indexOf("点");
        str=str.replaceAll("点","元");
        if(str.length()-idxYuan>1)
            str=str.substring(0,idxYuan+2)+"角"+str.substring(idxYuan+2);
        if(str.length()-idxYuan>3)
            str=str.substring(0,idxYuan+4)+"分"+str.substring(idxYuan+4);
        return str;
    }
}