package com.tlw.eg.thread.debug;

import java.util.EventListener;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年8月26日
 */
public interface DebugStateChangedListener extends EventListener {
	
	public void stateChanged(DebugStateEvent stateEvent);
	
}
