package com.tlw.eg.swing.combo;
/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-10-23
Description:
 ********************************/
import java.awt.AWTEvent;
import java.awt.ActiveEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.MenuComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

public class InternalFrameCombo extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private static final JDesktopPane glass = new JDesktopPane();
  
  public InternalFrameCombo(String title, JRootPane 
      rootPane, Component desktop) {
    super(title);

    // create opaque glass pane    
    glass.setOpaque(false);
    glass.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);

    // Attach mouse listeners
    MouseInputAdapter adapter = new MouseInputAdapter(){};
    glass.addMouseListener(adapter);
    glass.addMouseMotionListener(adapter);
   
    desktop.validate(); 
    try {
      setSelected(true);
    } catch (PropertyVetoException ignored) {
    }

    // Add modal internal frame to glass pane
    glass.add(this);

    // Change glass pane to our panel
    rootPane.setGlassPane(glass);

  }
  
    @Override
  public void setVisible(boolean value) {
    super.setVisible(value);
    // Show glass pane, then modal dialog
    if(glass != null)
        glass.setVisible(value);    
    if (value) {
      startModal();
    } else {
      stopModal();
    }
  }

  private synchronized void startModal() {
        
    try {
      if (SwingUtilities.isEventDispatchThread()) {
        EventQueue theQueue = 
          getToolkit().getSystemEventQueue();
        while (isVisible()) {
          AWTEvent event = theQueue.getNextEvent();
          Object source = event.getSource();
          if (event instanceof ActiveEvent) {              
            ((ActiveEvent)event).dispatch();
          } else if (source instanceof Component) {
              ((Component)source).dispatchEvent(event);                            
          } else if (source instanceof MenuComponent) {              
            ((MenuComponent)source).dispatchEvent(
              event);
          } else {
            System.out.println(
              "Unable to dispatch: " + event);
          }
        }
      } else {
        while (isVisible()) {
          wait();
        }
      }
    } catch (InterruptedException ignored) {
    }
  }
  
  private synchronized void stopModal() {
    notifyAll();
  }

  public static void main(String args[]) {
  
      final JFrame frame = new JFrame(
      "Modal Internal Frame");
    frame.setDefaultCloseOperation(
      JFrame.EXIT_ON_CLOSE);

    final JDesktopPane desktop = new JDesktopPane();
    
    ActionListener showModal = 
        new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        // Construct a message internal frame popup
        final JInternalFrame modal = 
          new InternalFrameCombo("Really Modal", 
          frame.getRootPane(), desktop);
          
        JTextField text = new JTextField("hello");
        JButton btn = new JButton("close");
        JComboBox cbo = new JComboBox(new String[]{"-01-", "-02-", "-03-", "-04-", "-05-", "-06-", "-07-", "-08-", "-09-", "-10-", "-11-", "-12-"});
        
        btn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        modal.setVisible(false);
                    }
                });
            
        
        cbo.setLightWeightPopupEnabled(false);
        //*!!!!
        try {					
        	Class<?> cls = Class.forName("javax.swing.PopupFactory");
        	Field field = cls.getDeclaredField("forceHeavyWeightPopupKey");
        	field.setAccessible(true);
        	cbo.putClientProperty(field.get(null), Boolean.TRUE);
        } catch (Exception e1) {e1.printStackTrace();}
		//*/
        Container iContent = modal.getContentPane();
        iContent.add(text, BorderLayout.NORTH);
        iContent.add(cbo, BorderLayout.CENTER);        
        iContent.add(btn, BorderLayout.SOUTH);
        //modal.setBounds(25, 25, 200, 100);
        modal.pack();
        modal.setVisible(true);     
        
      }
    };
    
    JInternalFrame jif = new JInternalFrame();
    jif.setSize(200, 200);
    desktop.add(jif);
    jif.setVisible(true);
    JButton button = new JButton("Open");
    button.addActionListener(showModal);

    Container iContent = jif.getContentPane();
    iContent.add(button, BorderLayout.CENTER);
    jif.setBounds(25, 25, 200, 100);
    jif.setVisible(true);
    
    Container content = frame.getContentPane();
    content.add(desktop, BorderLayout.CENTER);
    frame.setSize(500, 300);
    frame.setVisible(true);
  }
  
}

