package com.tlw.eg.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2008-12-1
@version:2008-12-1
Descript:用来尝试正则表达式，后来觉得没有必要去写了，因为eclipse提供了正则表达式的替换功能。
 */
public class Regexer extends JFrame {
	private static final long serialVersionUID = -6094576455117772487L;
	public static void main(String[] args) {
		String origion="SIP/extensionnumber-uniquechannelid/aaa-1dfsadfadsf/dooooo-vSIP/meng9999.802-088c2d18";
		Pattern pattern=Pattern.compile("/[\\w\\.\\d]*-");
		Matcher match=pattern.matcher(origion);
		while(match.find()){
			System.out.println("FoundAt["+match.start()+"-"+match.end()+"]:\t"+origion.substring(match.start()+1,match.end()-1));
		}
	}
}
