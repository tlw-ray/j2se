package com.tlw.eg.util.observer;

import java.util.Observable;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-8-18
@version:2009-8-18
Description:
 */
public class ObserverdString extends Observable {
	private String str="Hello";
	public String getString(){
		return str;
	}
	public void setString(String s){
		this.str=s;
		this.setChanged();
		this.notifyObservers(str);
	}
}
