package com.tlw.eg.reflect.dyn_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Vector;

public class ProxyList {

	public static void main(String[] args) {
		List<Object> items=new Vector<Object>();
		for(int i=0;i<10;i++){
			items.add(i);
		}
		
		List<Object> proxied=getList(items);
		proxied.add(1000);
	}
	
	@SuppressWarnings("unchecked")
	public static List<Object> getList(final List<Object> list) {
	    return (List<Object>) Proxy.newProxyInstance(
	    		ProxyList.class.getClassLoader(), 
	    		new Class[] { List.class },
	        new InvocationHandler() {
	            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
	                if ("add".equals(method.getName())) {
	                    throw new UnsupportedOperationException();
	                }else {
	                    return method.invoke(list, args);
	                }
	            }
	        });
	 } 
}
