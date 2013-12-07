package com.tlw.ui.alarm;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-3-30
@version:2009-3-30
Description:
 */
public class UtilAlarm {
	public static void show(JComponent component,String title,int width,int height){
		JFrame f=new JFrame();
		f.setTitle(title);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(width, height);
		f.getContentPane().add(component,BorderLayout.CENTER);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	public static JMenu initUIManager(){
		//所有可能被支持的用觉类名及名称
		final String[][] lafs={
				{"javax.swing.plaf.metal.MetalLookAndFeel","Metal"},
				{"com.sun.java.swing.plaf.motif.MotifLookAndFeel","Motif"},
				{"com.sun.java.swing.plaf.windows.WindowsLookAndFeel","Windows"},
				{"com.sun.java.swing.plaf.mac.MacLookAndFeel","Mac"},
				{"com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel","Nimbus"},
				{"com.sun.java.swing.plaf.gtk.GTKLookAndFeel","GTK"}
		};
		
		//---根据当前java版本获得最适宜的用觉，如果java版本高于1.6则使用nimbus否则根据平台选用
		try{
			String javaVersion=System.getProperty("java.version");
			String[] vs=javaVersion.split("\\.");
			float version=Float.parseFloat(vs[0]+"."+vs[1]);
			if(version>=1.6){
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			}else{
				String sysLafName=UIManager.getSystemLookAndFeelClassName();
				UIManager.setLookAndFeel(sysLafName);
				//windows汉字字体偏小加大一号
				if(sysLafName.equals(lafs[2][0])){
				    Enumeration keys = UIManager.getDefaults().keys();
			        while (keys.hasMoreElements()) {
			            Object key = keys.nextElement();
			            Object value = UIManager.get(key);
			            if (value instanceof FontUIResource){
			            	FontUIResource fur=(FontUIResource)value;
			            	UIManager.put(key, new FontUIResource(fur.getFontName(),fur.getStyle(),fur.getSize()+1));
			            }
			        }
				}
			}
		}catch(Exception ex){}
		
		//---建立用觉选择菜单
		String lafClassName=UIManager.getLookAndFeel().getClass().getName();
		ButtonGroup bg=new ButtonGroup();
		final JMenu menuLaf=new JMenu("用觉");
		for(int i=0;i<5;i++){
			String className=lafs[i][0];
			JRadioButtonMenuItem menuItem=new JRadioButtonMenuItem(lafs[i][1]);
			//---根据是否支持用觉设定选项可用性
			try {
				Class.forName(className);
			} catch (ClassNotFoundException e2) {
				menuItem.setEnabled(false);
			}
			
			//---为菜单项添加Action行为
			menuItem.setActionCommand(lafs[i][0]);
			menuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					try {
						String currentLafName=e.getActionCommand();
						UIManager.setLookAndFeel(currentLafName);
						JRootPane rootPane=menuLaf.getRootPane();
						Component rootComponent=(Component)rootPane.getParent();
						SwingUtilities.updateComponentTreeUI(rootComponent);
					} catch (Exception e1) {
						e1.printStackTrace();
					} 
				}
			});
			menuLaf.add(menuItem);
			bg.add(menuItem);
			//为当前用觉在菜单中选中
			if(lafClassName.equals(className))menuItem.setSelected(true);
		}
		return menuLaf;
	}
}