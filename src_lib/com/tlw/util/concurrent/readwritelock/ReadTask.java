package com.tlw.util.concurrent.readwritelock;
/**
@since 2011-7-11
@author 唐力伟 (liwei.tang@magustek.com)
 */
public class ReadTask extends Thread{
	  //logic bean
	  private ReadWriteLockLogic readWriteLockOperator;
	 
	  //读者
	  private String reader;

	  public ReadTask(ReadWriteLockLogic readWriteLockOperator, String reader) {
	    this.readWriteLockOperator = readWriteLockOperator;
	    this.reader = reader; 
	  }

	  // 执行任务
	  public void run() {
	    if(this.readWriteLockOperator != null){
	      try {
	        while(!isInterrupted()){
	          Thread.sleep(200);
	          System.out.println(reader + " read:" + Thread.currentThread().toString() + " : " + this.readWriteLockOperator.read());
	        }
	      } catch (Exception e) {
	        // TODO: handle exception
	      }
	    }
	  }
}
