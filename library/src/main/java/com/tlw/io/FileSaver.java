package com.tlw.io;


public class FileSaver {
    public static void main(String[] args){
        testGetFileName();
    }
    //参数：fileName,要保存的完整文件名,extend扩展名
    //功能：用来判断给定文件名是否以给定扩展名结尾，如果不是则加上;
    public static String getFileName(String fileName,String extend){
        if(extend.charAt(0)!='.')extend="."+extend;
        int extPoint=fileName.lastIndexOf(".");
        if(extPoint>=0){
            String extName = fileName.substring(extPoint);
            if (!extName.equalsIgnoreCase(extend)) {
                fileName += extend;
            }
        }else{
            fileName += extend;
        }
        return fileName;
    }
    public static void testGetFileName(){
        System.out.println(getFileName("D:\\aaa","bat"));
        System.out.println(getFileName("D:\\aaa.b","bat"));
        System.out.println(getFileName("D:\\aaa.ba","bat"));
        System.out.println(getFileName("D:\\aaa.bat","bat"));
        System.out.println(getFileName("D:\\aaa",".bat"));
        System.out.println(getFileName("D:\\aaa.b",".bat"));
        System.out.println(getFileName("D:\\aaa.ba",".bat"));
        System.out.println(getFileName("D:\\aaa.bat",".bat"));
    }
}
