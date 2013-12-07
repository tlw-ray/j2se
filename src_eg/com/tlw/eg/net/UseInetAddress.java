package com.tlw.eg.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2008-12-11
@version:2008-12-11
Descript:
 */
public class UseInetAddress {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			InetAddress address1=InetAddress.getByName("127.0.0.1");
			InetAddress address2=InetAddress.getByName("localhost");
			InetAddress address3=InetAddress.getByName("hg-ff189e2a0ed7.");
			InetAddress address4=InetAddress.getByName("hg-ff189e2a0ed7");
			System.out.println(address1.equals(address2));
			System.out.println(address2.equals(address3));
			System.out.println(address3.equals(address4));
			System.out.println(address1);
			System.out.println(address2);
			System.out.println(address3);
			System.out.println(address4);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
