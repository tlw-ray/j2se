package com.tlw.net;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.zip.GZIPInputStream;
public class HttpGetter {
    public static void main(String args[]) throws Exception {
        example1();
//        example2();
        //example3();
        //String html=getHtml("http://www.baidu.com","gb2312");
        //System.out.println(html);
    }
    public static void example1() throws Exception {
        //取得百度首页内容
        System.out.println("---------取得百度首页内容---------");
        String url,req;
        url="suggestion.baidu.com";
        req="/su?wd=q";
        //url="www.baidu.com";
        //req="/";
        byte[] byts=HttpGetter.get(url,req);
        java.io.ByteArrayInputStream in=new ByteArrayInputStream(byts);
        //java.util.zip.GZIPInputStream gin=new GZIPInputStream(in);
        java.io.InputStreamReader ir=new InputStreamReader(in,"GBK");
        java.io.BufferedReader br=new BufferedReader(ir);
        String line="";
        String result="";
        while((line=br.readLine())!=null){
            result+=line;
        }
        System.out.println(result);
    }
    public static void example2(){
        //取得GOOGLE EARTH地图服务图块,jpg图片本身就是压缩格式所以GZIP不敏感
        String strServer ="kh.google.com";
        String strPage ="/kh?v=3&t=ttr";
        System.out.println("---------取得GOOGLE EARTH地图服务图块---------");
        try {
            byte[] byts=get(strServer,strPage);
            ByteArrayInputStream bis=new ByteArrayInputStream(byts);
            BufferedReader br=new BufferedReader(new InputStreamReader(bis));
            String line;
            while((line=br.readLine())!=null){
                System.out.println(line);
            }
            System.out.println(byts.length);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void example3() throws Exception {
        //取得某网站html页面内容
        System.out.println("---------取得某网站html页面内容---------");
        byte[] byts = HttpGetter.get("www.woodpecker.org.cn", "/diveintopython/http_web_services/user_agent.html");
        java.io.ByteArrayInputStream in=new ByteArrayInputStream(byts);
        java.util.zip.GZIPInputStream gin=new GZIPInputStream(in);
        java.io.InputStreamReader ir=new InputStreamReader(gin,"GB2312");
        java.io.BufferedReader br = new BufferedReader(ir);
        String line = "";
        String result = "";
        while ((line = br.readLine()) != null) {
            result += line;
        }
        System.out.println(result);
    }
    //模拟ie的请求,取得页面数据，用stream;
    public static byte[] get(String strServer,String strPage){
        try {
            String hostname = strServer;
            int port = 80;
            InetAddress addr = InetAddress.getByName(hostname);
            System.out.println("addr:"+addr);
            System.out.println("HostAddress:"+addr.getHostAddress());
            System.out.println("HostName:"+addr.getHostName());
            Socket socket = new Socket(addr, port); //建立一个Socket

            //发送命令//模仿ie的请求
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream(), "UTF8"));
            wr.write("GET " + strPage + " HTTP/1.0\r\n");
            wr.write("Accept: */*\r\n");
            wr.write("Accept-Lanugage: zh-cn\r\n");
            wr.write("Accept-Encoding: gzip, deflate\r\n");
            wr.write("User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)\r\n");
            wr.write("HOST:" + strServer + "\r\n");
            wr.write("Connection: Keep-Alive\r\n");
            wr.write("\r\n");
            wr.flush();
            //接收返回的结果
            java.io.BufferedInputStream bis=new BufferedInputStream(socket.getInputStream());            	
            byte[] byt=new byte[1];
            while(bis.read(byt)>0){
                if(byt[0]=='\r'){
                   bis.read(byt);
                   if(byt[0]=='\n'){
                       bis.read(byt);
                       if(byt[0]=='\r'){
                           bis.read(byt);
                           if(byt[0]=='\n'){
                               System.out.println("Found End");
                               break;
                           }
                       }
                   }
                }
            };

            byte[] buf=new byte[512];
            java.io.ByteArrayOutputStream bos=new ByteArrayOutputStream();          
            while(bis.read(buf)>0){ 
                bos.write(buf);
                System.out.println(bos.toByteArray());
            }
            bos.flush();
            bos.close();
            wr.close();
            bis.close();
            return bos.toByteArray();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }
    public static String getHtml(String url,String encode) throws Exception {
        url=url.toLowerCase();
        if(url.startsWith("http://"))url=url.substring(7);
        System.out.println("url	"+url);
        int token=url.indexOf("/");
        String site=url;
        String page="/";
        if(token>0){
            site=url.substring(0,token);
            page=url.substring(token);
        }
        System.out.println("token	"+token);
        
        byte[] byts=HttpGetter.get(site,page);
        System.out.println("byts	"+byts);
        java.io.ByteArrayInputStream in=new ByteArrayInputStream(byts);
        java.util.zip.GZIPInputStream gin=new GZIPInputStream(in);
        java.io.InputStreamReader ir=new InputStreamReader(gin,"GB2312");
        java.io.BufferedReader br=new BufferedReader(ir);
        String line="";
        String result="";
        while((line=br.readLine())!=null){
            result+=line;
        }
        System.out.println(result);
        return result;
    }
}

