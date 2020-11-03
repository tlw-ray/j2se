package com.tlw.eg.jmx.echo_mbean;
/**
 * @author liwei.tang@magustek.com
 * @since 2015年7月27日
 */
public class Echo implements EchoMBean {

	@Override
	public void print(String yourName) {
		System.out.println("Hi " + yourName + "!");   
	}

}
