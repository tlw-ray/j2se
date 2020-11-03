package com.tlw.net;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
public class HttpWrapper {
    public static void main(String[] args) {
        try {
            URL u = new URL(
                    "http://localhost/cs/WebSamples/原理/ShowRequest.aspx");
            URLConnection conn=u.openConnection();
            InputStream is=(InputStream)conn.getContent();
            InputStreamReader isr=new InputStreamReader(is,"gbk");
            java.io.BufferedReader br=new BufferedReader(isr);
            String line;
            while((line=br.readLine())!=null){
                System.out.println(line);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
