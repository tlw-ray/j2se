package com.tlw.show.sort;
/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-4-10
@version:2009-4-10
Description:
 */
public class AlgorithmInsertSort extends AlgorithmSort {
    /* (non-Javadoc)
     * @see org.rut.util.algorithm.SortUtil.Sort#sort(int[])
     */
    public void sort(int[] data) throws Exception {
        for(int i=1;i<data.length;i++){
            for(int j=i;(j>0)&&(data[j]<data[j-1]);j--){
                swap(data,j,j-1);
                increaseCalcCount();
            }
        }        
    }
}
