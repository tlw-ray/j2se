package com.tlw.eg.thread.debug;

import java.util.EventObject;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年8月26日
 */
public class DebugStateEvent extends EventObject {

	private static final long serialVersionUID = -2196866106426633408L;
	
	DebugState from, to;

	public DebugStateEvent(Object source) {
		super(source);
	}

	public DebugState getFrom() {
		return from;
	}

	public void setFrom(DebugState from) {
		this.from = from;
	}

	public DebugState getTo() {
		return to;
	}

	public void setTo(DebugState to) {
		this.to = to;
	}
	
    public String toString() {
        return getClass().getName() + "[source=" + source + ", fromState="+from+", toState="+to+"]";
    }

}
