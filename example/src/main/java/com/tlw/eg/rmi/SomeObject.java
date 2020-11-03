package com.tlw.eg.rmi;

import java.io.Serializable;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年4月15日
 * 复杂对象,RMI每次传回都是相同的
 */
public class SomeObject implements Serializable{

	private static final long serialVersionUID = -5544720173961508995L;

	transient String name="xxx";
	
	public double value;
	
	public SomeObject(){
		System.out.println("SomeObject created!");
		value=Math.random();
	}
	
	public double getValue() {
		return value;
	}



	public void setValue(double value) {
		this.value = value;
	}



	@Override
	public String toString() {
		return super.toString()+" [name=" + name + ", value=" + value + "]";
	}
	
}
