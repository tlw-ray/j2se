package com.tlw.net;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Properties;

public class HttpRequest {
    public static void main(String[] args) {
    }

    protected Socket client;
    protected BufferedOutputStream sender;
    protected BufferedInputStream receiver;
    protected ByteArrayInputStream byteStream;
    protected URL target;
    private int responseCode = -1;
    private String responseMessage = "";
    private String serverVersion = "";
    private Properties header = new Properties();
    public HttpRequest() {}

    public HttpRequest(String url) {
        GET(url);
    }

    /* GET方法根据URL，会请求文件、数据库查询结果、程序运行结果等多种内容 */
    public void GET(String url) {
        try {
            checkHTTP(url);
            openServer(target.getHost(), target.getPort());
            String cmd = "GET " + getURLFormat(target) + " HTTP/1.0\r\n"
                    + getBaseHeads() + "\r\n";
            sendMessage(cmd);
            receiveMessage();
        } catch (ProtocolException p) {
            p.printStackTrace();
            return;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return;
        } catch (IOException i) {
            i.printStackTrace();
            return;
        }
    }

    /*
     * HEAD方法只请求URL的元信息，不包括URL本身。若怀疑本机和服务器上的
     * 文件相同，用这个方法检查最快捷有效。
     */
    public void HEAD(String url) {
        try {
            checkHTTP(url);
            openServer(target.getHost(), target.getPort());
            String cmd = "HEAD " + getURLFormat(target) + " HTTP/1.0\r\n"
                    + getBaseHeads() + "\r\n";
            sendMessage(cmd);
            receiveMessage();
        } catch (ProtocolException p) {
            p.printStackTrace();
            return;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return;
        } catch (IOException i) {
            i.printStackTrace();
            return;
        }
    }

    /*
     * POST方法是向服务器传送数据，以便服务器做出相应的处理。例如网页上常用的
     * 提交表格。
     */
    public void POST(String url, String content) {
        try {
            checkHTTP(url);
            openServer(target.getHost(), target.getPort());
            String cmd = "POST " + getURLFormat(target)
                    + "HTTP / 1.0\r \n " + getBaseHeads();
            cmd += "Content-type: application/x-www-form-urlencoded\r\n";
            cmd += "Content-length: " + content.length() + "\r\n\r\n";
            cmd += content + "\r\n";
            sendMessage(cmd);
            receiveMessage();
        } catch (ProtocolException p) {
            p.printStackTrace();
            return;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return;
        } catch (IOException i) {

            i.printStackTrace();
            return;
        }
    }

    protected void checkHTTP(String url) throws ProtocolException {
        try {
            URL target = new URL(url);
            if (target == null ||
                    !target.getProtocol().toUpperCase().equals("HTTP"))
                throw new ProtocolException("这不是HTTP协议");
            this.target = target;
        } catch (MalformedURLException m) {
            throw new ProtocolException("协议格式错误");
        }
    }

    /*
     * 与Web服务器连接。若找不到Web服务器，InetAddress会引发UnknownHostException
     * 异常。若Socket连接失败，会引发IOException异常。
     */
    protected void openServer(String host, int port) throws
            UnknownHostException, IOException {
        header.clear();
        responseMessage = "";
        responseCode = -1;
        try {
            if (client != null)
                closeServer();
            if (byteStream != null) {
                byteStream.close();
                byteStream = null;
            }
            InetAddress address = InetAddress.getByName(host);
            client = new Socket(address, port == -1 ? 80 : port);
            sender = new BufferedOutputStream(client.getOutputStream());
            receiver = new BufferedInputStream(client.getInputStream());
        } catch (UnknownHostException u) {
            throw u;
        } catch (IOException i) {
            throw i;
        }
    }

    /* 关闭与Web服务器的连接 */
    protected void closeServer() throws IOException {
        if (client == null)
            return;
        try {
            client.close();
            sender.close();
            receiver.close();
        } catch (IOException i) {
            throw i;
        }
        client = null;
        sender = null;
        receiver = null;
    }

    protected String getURLFormat(URL target) {
        String spec = "http://" + target.getHost();
        if (target.getPort() != -1)
            spec += ":" + target.getPort();
        return spec += target.getFile();
    }

    /* 向Web服务器传送数据 */
    protected void sendMessage(String data) throws IOException {
        sender.write(data.getBytes(), 0, data.length());
        sender.flush();
    }

    /* 接收来自Web服务器的数据 */
    protected void receiveMessage() throws IOException {
        byte data[] = new byte[1024];
        int count = 0;
        int word = -1;
// 解析第一行
        while ((word = receiver.read()) != -1) {
            if (word == '\r' || word == '\n') {
                word = receiver.read();
                if (word == '\n')
                    word = receiver.read();
                break;
            }
            if (count == data.length)
                data = addCapacity(data);
            data[count++] = (byte) word;
        }
        String message = new String(data, 0, count);
        int mark = message.indexOf(32);
        serverVersion = message.substring(0, mark);
        //while (markresponseCode = Integer.parseInt(message.substring(mark + 1, mark += 4)));
        responseMessage = message.substring(mark, message.length()).trim();
        // 应答状态码和处理请读者添加
        switch (responseCode) {
            case 400:
                throw new IOException("错误请求");
            case 404:
                throw new FileNotFoundException(getURLFormat(target));
            case 503:
                throw new IOException("服务器不可用");
        }
        if (word == -1)
            throw new ProtocolException("信息接收异常终止");
        int symbol = -1;
        count = 0;
// 解析元信息
        while (word != '\r' && word != '\n' && word > -1) {
            if (word == '\t')
                word = 32;
            if (count == data.length)
                data = addCapacity(data);
            data[count++] = (byte) word;
            parseLine: {
                while ((symbol = receiver.read()) > -1) {
                    switch (symbol) {
                        case '\t':
                            symbol = 32;
                            break;
                        case '\r':
                        case '\n':
                            word = receiver.read();
                            if (symbol == '\r' && word == '\n') {
                                word = receiver.read();
                                if (word == '\r')
                                    word = receiver.read();
                            }
                            if (word == '\r' || word == '\n' || word > 32)
                                break parseLine;
                            symbol = 32;
                            break;
                    }
                    if (count == data.length)
                        data = addCapacity(data);
                    data[count++] = (byte) symbol;
                }
                word = -1;
            }
            message = new String(data, 0, count);
            mark = message.indexOf(':');
            //String key = null;
            //if (mark > 0)
            //    key = message.substring(0, mark);
            mark++;
            //while( markString value = message.substring(mark,message.length() );
            //header.put(key, value);
            count = 0;
        }
// 获得正文数据
        while ((word = receiver.read()) != -1) {
            if (count == data.length)
                data = addCapacity(data);
            data[count++] = (byte) word;
        }
        if (count > 0)
            byteStream = new ByteArrayInputStream(data, 0, count);
        data = null;
        closeServer();
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getServerVersion() {
        return serverVersion;
    }

    public InputStream getInputStream() {
        return byteStream;
    }

    public synchronized String getHeaderKey(int i) {
        if (i >= header.size())
            return null;
        Enumeration enumer = header.propertyNames();
        String key = null;
        for (int j = 0; j <= i; j++)
            key = (String)enumer.nextElement();
        return key;
    }

    public synchronized String getHeaderValue(int i) {
        if (i >= header.size())
            return null;
        return header.getProperty(getHeaderKey(i));
    }

    public synchronized String getHeaderValue(String key) {
        return header.getProperty(key);
    }

    protected String getBaseHeads() {
        String inf = "User-Agent: myselfHttp/1.0\r\n" +
                "Accept: www/source; text/html; image/gif; */*\r\n";
        return inf;
    }

    private byte[] addCapacity(byte rece[]) {
        byte temp[] = new byte[rece.length + 1024];
        System.arraycopy(rece, 0, temp, 0, rece.length);
        return temp;
    }
}
