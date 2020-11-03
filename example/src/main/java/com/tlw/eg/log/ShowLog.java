package com.tlw.eg.log;

import java.util.logging.Level;
import java.util.logging.Logger;

/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-10-14
Description:
 ********************************/
public class ShowLog {
	Logger log=Logger.getLogger(this.getClass().getName());
	public static void main(String[] args){
		ShowLog sl=new ShowLog();
		sl.showLog();
	}
	public void showLog(){
		/*
		log.setFilter(new Filter(){
			@Override
			public boolean isLoggable(LogRecord record) {
				return true;
			}});
			*/
		log.setLevel(Level.ALL);
		log.log(Level.ALL,"ALL="+Level.ALL);
		log.log(Level.CONFIG,"CONFIG="+Level.CONFIG);
		log.log(Level.FINEST,"Finest="+Level.FINEST);
		log.log(Level.FINER,"FINER="+Level.FINER);
		log.log(Level.FINE,"FINE="+Level.FINE);
		log.log(Level.INFO,"INFO="+Level.INFO);
		log.log(Level.OFF,"OFF="+Level.OFF);
		log.log(Level.SEVERE,"SEVERE="+Level.SEVERE);
		log.log(Level.WARNING,"WARNING="+Level.WARNING);
	}
}
