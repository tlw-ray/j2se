package com.tlw.text;
import java.io.UnsupportedEncodingException;
import java.util.Random;
public class RandomChr {
    public static void main(String[] args) {
        //test1();
        test2();
    }
    public static void test1(){
        //测试:nextCHSChar();
        try {
            for (int i = 0; i < 100; i++)
                System.out.println(nextCHSChar());
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
    }
    public static void test2(){
        //测试:getRandomCHSString()
        String[] strs=getRandomCHSString(10,5);
        for(int i=0;i<strs.length;i++)
            System.out.println(strs[i]);
    }
    static Random random = new Random();
    static int randemCount=0xFEFE-0xA1A1;
    static int base1=0xF7-0xB0;
    static int base2=0xFE-0xA1;
    public static char nextCHSChar() throws UnsupportedEncodingException {
        //GB2312范围： 0xA1A1(41377) - 0xFEFE(65278)
        //汉字范围： 0xB0A1(45217) - 0xF7FE(63486)
        int i1 = 0xB0+random.nextInt(base1);
        int i2 = 0xA1+random.nextInt(base2);
        byte[] gbkBytes = {(byte) i1, (byte) i2};
        String str = new String(gbkBytes, "GBK");
        return str.charAt(0);
    }
    //count:返回String[]的length
    //maxLength:每个字符串不长于maxLength
    public static String[] getRandomCHSString(int count,int maxLength){
        String[] result=new String[count];
        try{
            for(int i=0;i<count;i++){
                int length=1+random.nextInt(maxLength);
                String tmp="";
                for(int j=0;j<length;j++){
                    tmp+=nextCHSChar();
                }
                result[i]=tmp;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
        return result;
    }
}
