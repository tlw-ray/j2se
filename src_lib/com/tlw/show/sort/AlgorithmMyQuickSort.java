package com.tlw.show.sort;
/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-4-10
@version:2009-4-10
Description:
 */
public class AlgorithmMyQuickSort extends AlgorithmSort {
    public void sort(int[] data) throws Exception {
        quickSort(data,0,data.length-1);        
    }
    private void quickSort(int[] data,int i,int j) throws Exception{
        int pivotIndex=(i+j)/2;
        //swap
        swap(data,pivotIndex,j);
        int k=partition(data,i-1,j,data[j]);
        swap(data,k,j);
        if((k-i)>1) quickSort(data,i,k-1);
        if((j-k)>1) quickSort(data,k+1,j);
        
    }
    private int partition(int[] data, int l, int r,int pivot) throws Exception {
        do{
           while(data[++l]<pivot);
           while((r!=0)&&data[--r]>pivot);
           swap(data,l,r);
           increaseCalcCount();
        }while(l<r);
        swap(data,l,r);
        return l;
    }
}
