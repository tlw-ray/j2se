package com.tlw.eg.thread.dateformat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DateForamtThreadUnsafe {

	public static void main(String[] args) throws Exception {

	    final DateFormat format = new SimpleDateFormat("yyyyMMdd");

	    Callable<Date> task = new Callable<Date>(){
	        public Date call() throws Exception {
	            return format.parse("20101022");
	        }
	    };

	    //pool with 5 threads
	    ExecutorService exec = Executors.newFixedThreadPool(5);
	    List<Future<Date>> results = new ArrayList<Future<Date>>();

	    //perform 10 date conversions
	    for(int i = 0 ; i < 10 ; i++){
	        results.add(exec.submit(task));
	    }
	    exec.shutdown();

	    //look at the results
	    for(Future<Date> result : results){
	        System.out.println(result.get());
	    }
	}
}
