package com.tlw.eg.loggin;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-6-18
@version:2009-6-18
Description:
 */
public class Example {
	Logger log=Logger.getLogger(getClass().getName());
	public static void main(String[] args) {
		Example e=new Example();
		e.doSth();
	}
	public void doSth(){
		log.log(Level.ALL,"ALL");
		log.log(Level.CONFIG,"CONFIG");
		log.log(Level.FINE,"FINE");
		log.log(Level.FINER,"FINER");
		log.log(Level.FINEST,"FINEST");
		log.log(Level.INFO,"INFO");
		log.log(Level.OFF,"OFF");
		log.log(Level.SEVERE,"SEVERE");
		log.log(Level.WARNING,"WARNING");
	}
}
