package com.tlw.eg.calc;

import java.io.StreamTokenizer;

/**
@since 2011-2-22
@author 唐力伟 (tlw_ray@163.com QQ:22348536)
TODO:递归下降解释器
 */
public class Interpreter {
	public static void main(String[] args) {
		String input="1+2*2-(6/2-1)*3+5";//=1+4-(3-1)*3+5=5-2*3+5=10-6=4;
		Interpreter interpreter=new Interpreter();
		interpreter.setExpression(input);
		interpreter.run();
	}
	String expression;
	StreamTokenizer streamTokenizer;
	private double factor(){
		return 0;
	}
	private double term(){
		return 0;
	}
	private double expression(){
		return 0;	
	}
	public void run(){
		
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
}
