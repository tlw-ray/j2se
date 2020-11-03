package com.tlw.eg.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年2月28日
 */
public class HelloClient {
    public static void main(String args[]){ 
        try { 
            //在RMI服务注册表中查找名称为RHello的对象，并调用其上的方法 
            IHello rhello =(IHello) Naming.lookup("rmi://localhost:8888/RHello"); 
            System.out.println(rhello.helloWorld()); 
            System.out.println(rhello.sayHelloToSomeBody("熔岩"));
           
            //3次获取到的复杂对象地址不同，内容相同
            for(int i=0;i<3;i++){
            	 //set方法并不会影响到远端的状态
	            rhello.getSomeObject().setValue(100d);
	            System.out.println(rhello.getSomeObject());
            }
        } catch (NotBoundException e) { 
            e.printStackTrace(); 
        } catch (MalformedURLException e) { 
            e.printStackTrace(); 
        } catch (RemoteException e) { 
            e.printStackTrace();   
        } 
    } 
}
