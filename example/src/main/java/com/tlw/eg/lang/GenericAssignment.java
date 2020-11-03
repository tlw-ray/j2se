package com.tlw.eg.lang;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年7月20日
 * 泛型a已被赋值，泛型b作为参数传递给了无泛型的c，问怎讲将a的值赋予c。
 */
public class GenericAssignment {

	public static void main(String[] args) {
		Item<Integer> a=new Item<Integer>();
		a.setValue(100);
		
//		Item<Integer> b=new Item<Integer>();
//		Type[] types=a.getClass().getGenericInterfaces();
//		for(Type type:types){
//			System.out.println(type);
//		}
		
		
//	Type	entityBeanType = ((Class) ((ParameterizedType) a.getClass()
//		        .getGenericSuperclass()).getActualTypeArguments()[0]);
//System.out.println(entityBeanType);
		
		//这里希望输出Integer,但是输出的是T
		for(TypeVariable<?> t: a.getClass().getTypeParameters()){
			System.out.println(t);
		}
		

//		Item<?> c=b;
//		c.getClass().getMethod("setValue", parameterTypes)
	}
	
	public static class Item<T>{
		T value;
		public T getValue(){
			return value;
		}
		public void setValue(T value){
			this.value=value;
		}
	}

}
