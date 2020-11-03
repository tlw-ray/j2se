package com.tlw.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-5-27
@version:2009-5-27
Description:用于测试和查阅界面的方法集,以及闪烁某个组件等
 */
public class UtilUi {
	public static JFrame show(Component comp,int width,int height,String title){
		JFrame f=new JFrame();
		f.getContentPane().add(comp,BorderLayout.CENTER);
		f.setSize(width,height);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setTitle(title);
		return f;
	}
	public static JFrame showPack(Component comp,String title){
		JFrame f=new JFrame();
		f.getContentPane().add(comp,BorderLayout.CENTER);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setTitle(title);
		return f;
	}
	/**
	 * 闪烁某个控件
	 * @param comp	要被闪烁的控件
	 * @param baseColor	组件基本颜色
	 * @param flashColor 组件闪烁颜色
	 * @param count 闪烁次数
	 * @param flashSpanMicrosecond 闪烁时间间隔，时间单位为毫秒
	 */
    public static void flashComponent(final Component comp,final Color baseColor,final Color flashColor,final int count,final int flashSpanMicrosecond){
        if(!comp.isOpaque()){
            String msg="开发期错误:需要闪烁的"+comp.getClass()+"类型对象"+comp.getName()+"是一个透明对象(isOpaque()=false)，闪烁可能不正常。";
            System.err.println(msg);
        }
        new Thread() {
            public void run() {
                //这里 使用baseColor比较安全，最终控件会恢复baseColor;
                //以前的版本中省略掉baseColor,由于控件不是线程安全的，
                //在多个线程同时闪烁控件时就会发生最终恢复颜色不正确的情况.
                Color orgColor;
                if(baseColor==null)
                     orgColor= comp.getBackground();
                else
                    orgColor=baseColor;
                for (int i = 0; i < count * 2; i++) {
                    Color col = comp.getBackground();
                    if(col==orgColor)
                        comp.setBackground(flashColor);
                    else
                        comp.setBackground(orgColor);
                    try {
                        Thread.sleep(flashSpanMicrosecond);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                comp.setBackground(orgColor);
            }
        }.start();
    }
	public static JMenu initUIManager(){
		UIManager.put("swing.boldMetal", Boolean.FALSE);			//默认外观禁用粗字体
		UIManager.put("swing.aatext", Boolean.TRUE);				//启用界面的反走样效果
		UIManager.put("awt.useSystemAAFontSettings", "LCD");		//启用LCD反走样效果
		UIManager.put("swing.useSystemFontSettings", Boolean.FALSE);//禁用系统字体设置，系统默认字体比较小，这个属性可以让swing界面的字体大一些
		ToolTipManager.sharedInstance().setInitialDelay(200);
		//所有可能被支持的用觉类名及名称
		final String[][] lafs={
				{"javax.swing.plaf.metal.MetalLookAndFeel","Metal"},
				{"com.sun.java.swing.plaf.motif.MotifLookAndFeel","Motif"},
				{"com.sun.java.swing.plaf.windows.WindowsLookAndFeel","Windows"},
				{"com.sun.java.swing.plaf.mac.MacLookAndFeel","Mac"},
				{"com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel","Nimbus"},
				{"com.sun.java.swing.plaf.gtk.GTKLookAndFeel","GTK"}
		};
		try{
			String sysLafName=UIManager.getSystemLookAndFeelClassName();
			sysLafName="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
			UIManager.getDefaults().clear();
			UIManager.setLookAndFeel(sysLafName);
			//windows汉字字体偏小加大一号
			if(sysLafName.equals(lafs[2][0]) && Locale.getDefault().getLanguage().equalsIgnoreCase(Locale.CHINA.getLanguage())){
			    Enumeration keys = UIManager.getDefaults().keys();
			    Font fnt = new Font("Dialog", 0, 12);
			    while (keys.hasMoreElements()) {
		            Object key = keys.nextElement();
		            Font oldFont = UIManager.getFont(key);
		            if (oldFont!=null)UIManager.put(key, fnt);
		        }
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		//---建立用觉选择菜单
		String lafClassName=UIManager.getLookAndFeel().getClass().getName();
		ButtonGroup bg=new ButtonGroup();
		final JMenu menuLaf=new JMenu("用觉");
		for(int i=0;i<lafs.length;i++){
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
    public static void setMetalNoBold(){
        //设定swing界面非bold字体样式。注意：需要在窗体界面加载前运行。适用于1.5以上JRE
        UIManager.put("swing.boldMetal", Boolean.FALSE);
    }
	public static void showErrorDlg(String info,Exception ex){
		if(ex!=null)info+="\n[异常信息:"+ex.getMessage()+"]";
		JOptionPane.showMessageDialog(null,info,"错误",JOptionPane.ERROR_MESSAGE);
		ex.printStackTrace();
	}
	public static Container getRootFrame(ActionEvent e){
		try{
	        JMenuItem menuItem = (JMenuItem) e.getSource();
	        JPopupMenu popupMenu = (JPopupMenu) menuItem.getParent();   
	        Component invoker = popupMenu.getInvoker(); 
	        JComponent invokerAsJComponent = (JComponent) invoker;   
	        Container topLevel = invokerAsJComponent.getTopLevelAncestor(); 
	        return topLevel;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	public static List<Component> getComponent(Container parent, Class<?> clazz){
		List<Component> result=new ArrayList<Component>();
		listComponent(parent, clazz, result);
		return result;
	}
	private static void listComponent(Container parent, Class<?> clazz, List<Component> result){
		if(parent.getClass()==clazz){
			result.add(parent);
		}
		for(Component comp: parent.getComponents()){
			if(comp instanceof Container){
				listComponent((Container)comp, clazz, result);
			}
		}
	}
}
