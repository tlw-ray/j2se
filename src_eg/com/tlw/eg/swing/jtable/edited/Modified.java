package com.tlw.eg.swing.jtable.edited;
/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-12-28
@version:2009-12-28
Description:
 */
public class Modified{
	private Object valueOriginal;
	private Object valueChanged;
	public Object getValueOriginal() {
		return valueOriginal;
	}

	public void setValueOriginal(Object valueOriginal) {
		this.valueOriginal = valueOriginal;
	}

	public Object getValueChanged() {
		return valueChanged;
	}

	public void setValueChanged(Object valueChanged) {
		this.valueChanged = valueChanged;
	}
	public boolean isModified(){
		return !(valueChanged==valueOriginal || valueChanged.equals(valueOriginal));
	}
	public String toString(){
		return super.toString()+"[Original="+valueOriginal.toString()+",Changed="+valueChanged.toString()+"]";
	}
}