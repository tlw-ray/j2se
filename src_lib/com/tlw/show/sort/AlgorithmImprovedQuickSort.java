package com.tlw.show.sort;
/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-4-10
@version:2009-4-10
Description:
 */
public class AlgorithmImprovedQuickSort extends AlgorithmSort {
    private static int MAX_STACK_SIZE=4096;
    private static int THRESHOLD=10;
    public void sort(int[] data) {
        int[] stack=new int[MAX_STACK_SIZE];
        
        int top=-1;
        int pivot;
        int pivotIndex,l,r;
        
        stack[++top]=0;
        stack[++top]=data.length-1;
        
        while(top>0){
            int j=stack[top--];
            int i=stack[top--];
            
            pivotIndex=(i+j)/2;
            pivot=data[pivotIndex];
            
            swap(data,pivotIndex,j);
            
            //partition
            l=i-1;
            r=j;
            do{
                while(data[++l]<pivot);
                while((r!=0)&&(data[--r]>pivot));
                swap(data,l,r);
            }while(l<r);
            increaseCalcCount();
            swap(data,l,r);
            swap(data,l,j);
            
            if((l-i)>THRESHOLD){
                stack[++top]=i;
                stack[++top]=l-1;
            }
            if((j-l)>THRESHOLD){
                stack[++top]=l+1;
                stack[++top]=j;
            }
            
        }
        insertSort(data);
    }
    /**
     * @param data
     * @throws Exception 
     */
    private void insertSort(int[] data) {
        for(int i=1;i<data.length;i++){
            for(int j=i;(j>0)&&(data[j]<data[j-1]);j--){
                swap(data,j,j-1);
                increaseCalcCount();
            }
        }       
    }
}
