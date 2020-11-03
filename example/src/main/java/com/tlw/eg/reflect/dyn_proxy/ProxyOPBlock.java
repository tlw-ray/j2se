package com.tlw.eg.reflect.dyn_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyOPBlock {

	public static void main(String[] args) {
		OPBlock dummy=new OPBlockDummy();
		System.out.println(dummy.getValue("TEST"));
		OPBlock proxy=proxy(dummy);
		System.out.println(proxy.getValue("TEST"));
	}
	
	public static OPBlock proxy(final OPBlock block){
		return (OPBlock)Proxy.newProxyInstance(
				OPBlock.class.getClassLoader(), 
				new Class[]{ OPBlock.class },
				new InvocationHandler(){
					@Override
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						if(method.getName().equals("getValue")){
							String fieldName=(String)args[0];
							System.out.println(fieldName);
							return "Proxy changed..."+fieldName;
						}else {
		                    return method.invoke(block, args);
		                }
					}
			});
	}

}
