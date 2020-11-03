package com.tlw.eg.swing.work;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.SwingWorker;

import com.tlw.util.UtilUi;

/**
 * @author liwei.tang@magustek.com
 * @since 2016年7月4日
 * 统计其中奇数的数量
 */
public class SampleWorker extends SwingWorker<Integer, Integer> {
	//在后台执行
	protected Integer doInBackground() throws Exception {
		System.out.println("doInBackground: " + Thread.currentThread().getName());
		int result = 0;
		for(int i=0;i<100;i++){
			if(i % 2 != 0){
				//分段处理会间接调用process方法
				publish(i);
				result++;
			}
			Thread.sleep(10);
		}
		return result;
	}
	
	//处理被publish的数据,在事件派发线程
	protected void process(List<Integer> paramList) {
		System.out.println("process: " + Thread.currentThread().getName());
		for(Integer value : paramList){
			System.out.println(value);
		}
	}

	//结束
	protected void done() {
		System.out.println("done...");
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		JButton btn = new JButton("HI");
		UtilUi.show(btn, 400, 300, "");
		SampleWorker worker = new SampleWorker();
		worker.execute();
		System.out.println("Get: " + worker.get() + "\t" + Thread.currentThread().getName());
	}

}
