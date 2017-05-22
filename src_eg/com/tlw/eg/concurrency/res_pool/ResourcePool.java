package com.tlw.eg.concurrency.res_pool;

import java.util.concurrent.Semaphore;

/**
 * @author liwei.tang@magustek.com
 * @since 2011-12-6
 * 
 */
public class ResourcePool {
	
	private static final int MAX_AVAILABLE = 30;
	
	private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);
	protected ResourceObject[] items = new ResourceObject[MAX_AVAILABLE];
	protected boolean[] used = new boolean[MAX_AVAILABLE];
	
	public ResourceObject getItem() throws InterruptedException {
		available.acquire();
		return getNextAvailableItem();
	}

	public void putItem(ResourceObject x) {
		if (markAsUnused(x))
			available.release();
	}

	protected synchronized ResourceObject getNextAvailableItem() {
		for (int i = 0; i < MAX_AVAILABLE; ++i) {
			if (!used[i]) {
				used[i] = true;
				if(items[i]==null){
					items[i]=new ResourceObject();
					items[i].name="Object-"+i;
				}
				return items[i];
			}
		}
		return null; // not reached
	}

	protected synchronized boolean markAsUnused(Object item) {
		for (int i = 0; i < MAX_AVAILABLE; ++i) {
			if (item == items[i]) {
				if (used[i]) {
					used[i] = false;
					return true;
				} else
					return false;
			}
		}
		return false;
	}
}