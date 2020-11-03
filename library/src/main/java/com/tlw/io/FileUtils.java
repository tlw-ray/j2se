/*
 * Created on 2006-9-7
 */
package com.tlw.io;
import java.io.File;
public class FileUtils {
    public static String getExtractFileName(File file){
        return file.getName().substring(0, file.getName().lastIndexOf('.'));
    }
    public static String getExtractFileName(String filename){
        int idx_a=filename.lastIndexOf('\\');
        int idx_b=filename.lastIndexOf('/');
        int idx_c=filename.lastIndexOf('.');
        int idx_d=Math.max(idx_a, idx_b);
        if(idx_c>idx_d){
            return filename.substring(idx_d+1,idx_c);
        }else{
            return filename.substring(idx_d+1);
        }
    }
    public static String getExtendFileName(File file){
        return file.getName().substring(file.getName().lastIndexOf('.')+1);
    }
    /**************测试:**************************/
    private static void testGetExtractFileName(){
        String a="C:\\aaa\\bbb\\ccc.ddd";
        String b="C:/a/b/c.d/e.f";
        String c="C:/a\\b/c\\d.e/f.g";
        String d="C:/a\\b/c\\d";
        System.out.println(getExtractFileName(a));
        System.out.println(getExtractFileName(b));
        System.out.println(getExtractFileName(c));
        System.out.println(getExtractFileName(d));
    }
    public static void main(String[] args){
        testGetExtractFileName();
    }
}
