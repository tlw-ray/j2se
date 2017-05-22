package com.tlw.eg.concurrency.res_pool;

public class Customer {
	static final ResourcePool pool=new ResourcePool();
	public static void main(String[] args) {
		int customerThreadCount=2;
		for(int i=0;i<customerThreadCount;i++){
			CustomerThread ct=new CustomerThread();
			ct.start();
		}
	}
	static class CustomerThread extends Thread{
		public void run(){
			while(true){
				try {
					ResourceObject ro=pool.getItem();
					ro.doSomething1("Thread-"+getId());
					pool.putItem(ro);
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
