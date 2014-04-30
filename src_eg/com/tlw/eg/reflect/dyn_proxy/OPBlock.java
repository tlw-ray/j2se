package com.tlw.eg.reflect.dyn_proxy;

public interface OPBlock {
	Object getValue(String fieldName);
	void setValue(String fieldName, Object value);
}
