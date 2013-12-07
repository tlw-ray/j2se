/*
 * Created on 2006-11-8
 */
package com.tlw.io;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
public class ReaderWriter {
    public static String readToString(byte[] bytes,String encodeName) throws IOException{
        InputStream is=new ByteArrayInputStream(bytes);
        return readToString(is,encodeName);
    }
    public static String readToString(InputStream is,String encodeName) throws IOException{
        InputStreamReader isr = new InputStreamReader(is,encodeName);
        BufferedReader br=new BufferedReader(isr);
        String str="";
        String line=br.readLine();
        while(line!=null){
            str+=line;
            line=br.readLine();
        }
        br.close();
        isr.close();
        return str;
    }
}
