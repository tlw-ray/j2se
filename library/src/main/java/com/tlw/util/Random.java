/*
 * Created on 2008-3-7
 */
package com.tlw.util;

import java.util.Date;

public class Random {
    public static void main(String[] args){
        for(int i=1;i<10;i++){
            //System.out.println(nextInt(i));
            //System.out.println(nextDouble());
            System.out.println(nextStringZh(3,16));
            //System.out.println(nextStringAscII(3,8));
            
        }
    }
    static final java.util.Random random=new java.util.Random();
    public static int nextInt(int n){
        //System.out.println(n);
        return random.nextInt(n);
    }
    public static double nextDouble(){
        return random.nextDouble();
    }
    static final int zhStart=19968;
    static final int zhEnd=40869;
    static final int zhRange=zhEnd-zhStart;
    public static String nextStringZh(int minLength,int maxLength){
        int length=random.nextInt(maxLength-minLength)+minLength;;
        String result="";
        for(int i=0;i<length;i++){
            int charCode=random.nextInt(zhRange)+zhStart;
            result+=(char)charCode;
        }
        return result;
    }
    static final int ascStart=33;
    static final int ascEnd=125;
    static final int ascRange=ascEnd-ascStart;
    public static String nextStringAscII(int minLength,int maxLength){
        int length=random.nextInt(maxLength-minLength)+minLength;
        String result="";
        for(int i=0;i<=length;i++){
            int charCode=random.nextInt(ascRange)+ascStart;
            result+=(char)charCode;
        }
        return result;
    }
    static final long dateStart=0;
    static final long dateEnd=System.currentTimeMillis();
    public static Date nextDate(){
    	return new Date(random.nextLong());
    }
    
}
