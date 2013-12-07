package com.tlw.show.sort;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-4-10
@version:2009-4-10
Description:
 */
public class AlgorithmHeapSort extends AlgorithmSort {
    public void sort(int[] data) {
        MaxHeap h=new MaxHeap();
        h.init(data);
        for(int i=0;i<data.length;i++)
            h.remove();
        System.arraycopy(h.queue,1,data,0,data.length);
        pause();
    }

     class MaxHeap{
        void init(int[] data){
            this.queue=new int[data.length+1];
            for(int i=0;i<data.length;i++){
                queue[++size]=data[i];
                fixUp(size);
                increaseCalcCount();
            }
        }
        private int size=0;
        private int[] queue;
        public int get() {
            return queue[1];
        }
        public void remove() {
            swap(queue,1,size--);
            fixDown(1);
        }
        //fixdown
        private void fixDown(int k) {
            int j;
            while ((j = k << 1) <= size) {
            	increaseCalcCount();
                if (j < size && queue[j]<queue[j+1])
                    j++; 
                if (queue[k]>queue[j]) //不用交换
                    break;
                swap(queue,j,k);
                k = j;
            }
        }
        private void fixUp(int k) {
            while (k > 1) {
                int j = k >> 1;
                if (queue[j]>queue[k])
                    break;
                swap(queue,j,k);
                k = j;
            }
        }
    }
}
