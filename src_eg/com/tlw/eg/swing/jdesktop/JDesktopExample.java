package com.tlw.eg.swing.jdesktop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import com.tlw.util.UtilUi;


/**
@since 2010-1-13
@version 2010-1-13
@author 唐力伟 (tlw_ray@163.com)
 */
public class JDesktopExample extends JPanel {
	private static final long serialVersionUID = 7424430081443745133L;
	public static void main(String[] args) throws PropertyVetoException {
		UtilUi.initUIManager();
		JDesktopExample pane=new JDesktopExample();
		UtilUi.show(pane, 400, 300, "");
	}
	JDesktopPane jdesktop=new JDesktopPane();
	JInternalFrame jinternalFrame=new JInternalFrame();
	JInternalFrame palette=new JInternalFrame();
	JPanel jpanel=new JPanel();
	public JDesktopExample() throws PropertyVetoException{
		jinternalFrame.setSize(80, 80);
		jinternalFrame.setVisible(true);
		jinternalFrame.setSelected(true);
		jinternalFrame.setTitle("xixi");
		jinternalFrame.setResizable(true);
		jinternalFrame.setClosable(true);
		jinternalFrame.setMaximizable(true);
		jinternalFrame.setIconifiable(true);
		
		palette.setVisible(true);
		palette.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
		
		jpanel.setSize(400,300);
		jpanel.setBackground(Color.blue);

		jdesktop.add(jinternalFrame,new Integer(3));
//		jdesktop.add(palette,new Integer(2));
		jdesktop.add(jpanel,new Integer(1));
		
		setLayout(new BorderLayout());
		add(jdesktop,BorderLayout.CENTER);
		
		this.addComponentListener(new ComponentAdapter(){
			@Override
			public void componentResized(ComponentEvent e) {
				palette.setSize(e.getComponent().getSize());
			}
		});
	}
}