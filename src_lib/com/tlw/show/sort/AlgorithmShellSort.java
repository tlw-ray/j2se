package com.tlw.show.sort;
/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-4-10
@version:2009-4-10
Description:
 */
public class AlgorithmShellSort extends AlgorithmSort {
	   public void sort(int[] data) throws Exception {
	        for(int i=data.length/2;i>2;i/=2){
	            for(int j=0;j<i;j++){
	                insertSort(data,j,i);
	                increaseCalcCount();
	            }
	        }
	        insertSort(data,0,1);
	    }
	    private void insertSort(int[] data, int start, int inc) throws Exception {
	        for(int i=start+inc;i<data.length;i+=inc){
	            for(int j=i;(j>=inc)&&(data[j]<data[j-inc]);j-=inc){
	                swap(data,j,j-inc);
	                increaseCalcCount();
	            }
	        }
	    }

}
