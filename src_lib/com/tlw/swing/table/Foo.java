package com.tlw.swing.table;
/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2008-12-6
@version:2008-12-6
Descript: 普通的包含属性的类。
 */
class Foo {
	private int id;
	private String name;
	private double value;
	public Foo(){}
	public Foo(int id,String name,double value){
		this.id=id;
		this.name=name;
		this.value=value;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
}
