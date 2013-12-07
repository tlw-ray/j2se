package com.tlw.eg.awt.event;

import java.awt.EventQueue;
import java.util.EventListener;
import java.util.EventObject;

import javax.swing.event.EventListenerList;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-12-3
@version:2009-12-3
Description:
 */
public class CustomEventExample {
	public static void main(String[] args){
	    MyClass c = new MyClass();
	    // Register for MyEvents from c
	    c.addMyEventListener(new MyEventListener() {
	        public void myEventOccurred(MyEvent evt) {
	            // MyEvent was fired
	        	System.out.println(evt);
	        	System.out.println(EventQueue.getCurrentEvent());
	        }
	    });
	    System.out.println(EventQueue.getCurrentEvent());
	    c.fireMyEvent(new MyEvent(c));
	    System.out.println(EventQueue.getCurrentEvent());
	}
}
// Declare the event. It must extend EventObject.
class MyEvent extends EventObject {
	private static final long serialVersionUID = 1614504127958884285L;
	public MyEvent(Object source) {
        super(source);
    }
}
// Declare the listener class. It must extend EventListener.
// A class must implement this interface to get MyEvents.
interface MyEventListener extends EventListener {
    public void myEventOccurred(MyEvent evt);
}

// Add the event registration and notification code to a class.
class MyClass {
    // Create the listener list
    protected EventListenerList listenerList =new EventListenerList();
    // This methods allows classes to register for MyEvents
    public void addMyEventListener(MyEventListener listener) {
        listenerList.add(MyEventListener.class, listener);
    }

    // This methods allows classes to unregister for MyEvents
    public void removeMyEventListener(MyEventListener listener) {
        listenerList.remove(MyEventListener.class, listener);
    }

    // This private class is used to fire MyEvents
    void fireMyEvent(MyEvent evt) {
        Object[] listeners = listenerList.getListenerList();
        // Each listener occupies two elements - the first is the listener class
        // and the second is the listener instance
        for (int i=0; i<listeners.length; i+=2) {
            if (listeners[i]==MyEventListener.class) {
                ((MyEventListener)listeners[i+1]).myEventOccurred(evt);
            }
        }
    }
} 