package com.tlw.eg.thread.debug;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.EventListenerList;

/**
 * @author liwei.tang@magustek.com
 * @since 2016年1月27日
 */
public class Debugger implements Runnable {
	
	protected DebugState oldState = DebugState.TERMINATED;
	protected DebugState debugState = DebugState.TERMINATED;

	protected Thread guiThread;
	
	protected Thread thread;
	
	protected EventListenerList eventListenerList = new EventListenerList();
	
	protected long[] steps = new long[]{1000, 1500, 2000, 1000, 500, 1000};
	
	protected List<Long> breakPoints = new ArrayList<Long>();
	
	@Override
	public void run() {
		// DEBUG线程启动宣称进入DebugState.DEBUGGING状态
		postStateChangedEvent();

		for (long step : steps) {
			if (breakPoints.contains(step) && debugState == DebugState.DEBUGGING) {
				// 该cat处于断点中，进入挂起态
				debugState = DebugState.SUSPEND;
			}
			if (debugState == DebugState.TERMINATED) {
				postStateChangedEvent();
				break;
			} else if (debugState == DebugState.SUSPEND) {
				if (guiThread != null) {
					notifyGUIThread();
				}

				// 宣称进入静止态
				postStateChangedEvent();
				// 挂起
				synchronized (this) {
					try {
						System.out.println("before wait...");
						wait();
						System.out.println("after wait...");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// 挂起结束
				if (debugState == DebugState.TERMINATED) {
					postStateChangedEvent();
					break;
				} else if (debugState == DebugState.NEXTING) {
					// 宣称进入nextStep态
					postStateChangedEvent();
					nextStep(step);
					// 从挂起态进入停止态,唤醒GUI线程
					if (guiThread != null) {
						notifyGUIThread();
					}
					oldState = DebugState.NEXTING;
					debugState = DebugState.SUSPEND;
				} else if (debugState == DebugState.DEBUGGING) {
					// 宣称进入运算态
					postStateChangedEvent();
					// 挂起结束后执行下一个cat
					nextStep(step);
					// 从挂起态进入停止态,唤醒GUI线程
					if (guiThread != null) {
						notifyGUIThread();
					}
				} else {
					postStateChangedEvent();
					throw new RuntimeException("Error state..." + debugState);
				}
			} else if (debugState == DebugState.DEBUGGING) {
				// 处于运行状态
				// TODO 这里是耗时操作，执行过程中debugState的状态不能被改变
				// （暂时是通过回调状态控制界面按钮可用性解决，如果是多线程程序会出问题）
				nextStep(step);
				// 从挂起态进入停止态,唤醒GUI线程
				if (guiThread != null) {
					notifyGUIThread();
				}
			} else {
				throw new RuntimeException("Error state:" + debugState);
			}

		}

		System.out.println("Run finish...");

		if (guiThread != null) {
			notifyGUIThread();
		}
		// 发出事件变化消息
		postLastStateChangeEvent();
	}
	
	private void postLastStateChangeEvent(){
		if(debugState != DebugState.TERMINATED){
			debugState = DebugState.TERMINATED;
			postStateChangedEvent();
		}
	}
	
	private void doStart() {
		
		oldState = debugState;
		debugState = DebugState.DEBUGGING;
		
		thread = new Thread(this);
		thread.setName(thread.getName() + "-ModelDebugger");
		thread.start();
	}

	private void doStop() {
		
		oldState = debugState;
		debugState = DebugState.TERMINATED;

		// 如果从DebugState.SUSPEND态进入需要先唤醒，否则后边的thread.join不会被执行
		synchronized (this) {
			notify();
		}

		// 等待线程退出
		if (thread.isAlive()) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void doPause() {
		
		oldState = debugState;
		debugState = DebugState.SUSPEND;

		// 这里阻塞一下GUI线程感受会更好
		guiThread = Thread.currentThread();
		synchronized (guiThread) {
			try {
				guiThread.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void doResume() {
		
		oldState = debugState;
		debugState = DebugState.DEBUGGING;
		
		synchronized (this) {
			notify();
		}
	}

	private void doStepOver() {
		
		oldState = debugState;
		debugState = DebugState.NEXTING;
		
		synchronized (this) {
			notify();
		}
	}

	public void fire(DebugTrigger trigger){
		switch (debugState) {
		case TERMINATED:
			switch (trigger) {
			case DEBUG:
				doStart();
				return;
			default:
				// throw new FireTriggerException(debugState, trigger);
				return;
			}
		case DEBUGGING:
			switch (trigger) {
			case PAUSE:
				doPause();
				return;
			case STOP:
				doStop();
				return;
			default:
				// throw new FireTriggerException(debugState, trigger);
				return;
			}
		case SUSPEND:
			switch (trigger) {
			case DEBUG:
				doResume();
				return;
			case NEXT:
				doStepOver();
				return;
			case STOP:
				doStop();
				return;
			default:
				// throw new FireTriggerException(debugState, trigger);
				return;
			}
		default:
			// throw new UnsupportTriggerException(trigger);
			return;
		}
	}
	
	public boolean canFire(DebugTrigger trigger){
		switch (debugState) {
		case TERMINATED:
			switch (trigger) {
			case DEBUG:
				return true;
			default:
				return false;
			}
		case DEBUGGING:
			switch (trigger) {
			case PAUSE:
			case STOP:
				return true;
			default:
				return false;
			}
		case SUSPEND:
			switch (trigger) {
			case DEBUG:
			case NEXT:
			case STOP:
				return true;
			default:
				return false;
			}
		default:
			return false;
		}
	}
	
	public DebugState getDebugState() {
		return debugState;
	}

	protected void postStateChangedEvent() {
		
		DebugStateEvent event = new DebugStateEvent(this);
		event.setFrom(oldState);
		event.setTo(debugState);

		for (DebugStateChangedListener listener : eventListenerList.getListeners(DebugStateChangedListener.class)) {
			try {
				listener.stateChanged(event);
			} catch (Throwable ex) {
				ex.printStackTrace();
			}
		}
		
	}

	public void addStateChangeListener(DebugStateChangedListener listener) {
		eventListenerList.add(DebugStateChangedListener.class, listener);
	}

	public void removeStateChangeListener(DebugStateChangedListener listener) {
		eventListenerList.remove(DebugStateChangedListener.class, listener);
	}
	
	protected void notifyGUIThread() {
		synchronized (guiThread) {
			guiThread.notify();
		}
	}
	
	private void nextStep(long sleepValue){
		System.out.println("next step: "+sleepValue);
		try {
			Thread.sleep(sleepValue);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
