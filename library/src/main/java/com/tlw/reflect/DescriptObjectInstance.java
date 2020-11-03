package com.tlw.reflect;
import java.lang.reflect.Method;
public class DescriptObjectInstance {
	public static void main(String[] args) {
		java.util.Date date=new java.util.Date();
		readInstanceGetMethod(date);
	}
	//*/列举实例的各项get方法可取得的值
	public static void readInstanceGetMethod(Object obj){
		if(obj==null){
			System.out.println("对象为空，没有get方法可调用查看。");
			return;
		}
		try {
			Method[] methods=obj.getClass().getMethods();
			System.out.println("****对象类名:"+obj.getClass().getName()+"*****");
			for(int i=0;i<methods.length;i++){
				Method method=methods[i];
				if(method.getName().indexOf("get")==0
						&& method.getParameterTypes().length==0){
					try{
						Object robj=method.invoke(obj, (Object[])null);
						System.out.println(method.getName()+"(){return "+robj+";}");
					}catch(Exception ex){
						System.out.println("运行方法"+method.getName()+"()时出现异常:"+ex.getMessage());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("******************************");
	}//*/
}
