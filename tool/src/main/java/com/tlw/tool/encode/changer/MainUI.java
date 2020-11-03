package com.tlw.tool.encode.changer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-10-10
Description:目录下某类扩展名的文本文件编码批量转换
输入:
1.需要转换文件的目录
2.需要转换文件的扩展名
3.目标编码方式
4.开始转换按钮
5.选择目录按钮(可选)
输出：
要转换文件目录下的所有此扩展名文件，编码由原编码转换为目标编码。
注意:
1.由于转换目标将覆盖原文件，当点击开始转换按钮时提示先备份目录。
2.转换前先加载所有要转换的文件名及目录到，再执行转换并覆盖原文件。

features:
1.无论提供文件或者目录均可转换
2.当输入文件为:a.空文件没必要转换的b.由于无法解析源编码方式无法转换的 应给在转换结果中给予标识让用户有了解。
3.进度条多线程。
4.更加通用的UI不能局限JAVA本身
 ********************************/
public class MainUI extends JFrame{
	private static final long serialVersionUID = 4330059759487755996L;
	public static void main(String[] args) {
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		MainUI uc=new MainUI();
		uc.setAlwaysOnTop(true);
		uc.setVisible(true);
	}
	public MainUI(){
		initComponents();
		uiRender();
		initEvents();
	}
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		jlbDir = new JLabel();
		jtfDir = new JTextField();
		jbtnDirSelect = new JButton();
		jbtnConvert = new JButton();
		jlbEncode = new JLabel();
		jcomboEncode = new JComboBox();
		jlbExt = new JLabel();
		jlbExtHead = new JLabel();
		jtfExt = new JTextField();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridBagLayout());
		((GridBagLayout)contentPane.getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
		((GridBagLayout)contentPane.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0};
		((GridBagLayout)contentPane.getLayout()).columnWeights = new double[] {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0E-4};
		((GridBagLayout)contentPane.getLayout()).rowWeights = new double[] {1.0, 0.0, 0.0, 0.0, 1.0, 1.0E-4};

		//---- jlbDir ----
		jlbDir.setText("\u8f6c\u6362\u76ee\u5f55:");
		contentPane.add(jlbDir, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));
		contentPane.add(jtfDir, new GridBagConstraints(2, 1, 4, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));

		//---- jbtnDirSelect ----
		jbtnDirSelect.setText("...");
		contentPane.add(jbtnDirSelect, new GridBagConstraints(6, 1, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));

		//---- jbtnConvert ----
		jbtnConvert.setText("\u8f6c\u6362\uff01");
		contentPane.add(jbtnConvert, new GridBagConstraints(6, 2, 1, 2, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));

		//---- jlbEncode ----
		jlbEncode.setText("\u76ee\u6807\u7f16\u7801:");
		contentPane.add(jlbEncode, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));

		//---- jcomboEncode ----
		jcomboEncode.setModel(new DefaultComboBoxModel(TextFileAdapter.supportEncode));
		contentPane.add(jcomboEncode, new GridBagConstraints(2, 2, 3, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));

		//---- jlbExt ----
		jlbExt.setText("\u6269\u5c55\u540d:");
		contentPane.add(jlbExt, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));

		//---- jlbExtHead ----
		jlbExtHead.setText("*.");
		jlbExtHead.setBackground(Color.white);
		jlbExtHead.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(jlbExtHead, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));

		//---- jtfExt ----
		jtfExt.setText("java");
		contentPane.add(jtfExt, new GridBagConstraints(3, 3, 2, 1, 0.0, 0.0,
			GridBagConstraints.CENTER, GridBagConstraints.BOTH,
			new Insets(0, 0, 5, 5), 0, 0));
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JLabel jlbDir;
	private JTextField jtfDir;
	private JButton jbtnDirSelect;
	private JButton jbtnConvert;
	private JLabel jlbEncode;
	private JComboBox jcomboEncode;
	private JLabel jlbExt;
	private JLabel jlbExtHead;
	private JTextField jtfExt;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	private void uiRender(){
		//一些对UI的美化工作
		setTitle("编码格式转换工具");
		jlbExtHead.setOpaque(false);
		jlbExtHead.setBorder(jtfExt.getBorder());
		
		jcomboEncode.setSelectedItem("UTF-8");
		
		setSize(400,160);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	private void initEvents(){
		//按钮事件
		jbtnDirSelect.addActionListener(new ActionSelectDir());
		jbtnConvert.addActionListener(new ActionConvertEncode());
	}
	FileDialog dirChooser=new FileDialog(MainUI.this);
//	JDirChooser dirChooser=new JDirChooser(this,"请选择目录",true);
	ListShower listShower=new ListShower();
	class ActionSelectDir extends AbstractAction{
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {
			dirChooser.setLocationRelativeTo(MainUI.this);
			if(!jtfDir.getText().equals("")){
				File file=new File(jtfDir.getText());
				if(file.exists()){
					try {
//						dirChooser.setSelectFile(file);
						dirChooser.setDirectory(file.getPath());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
			dirChooser.setVisible(true);
			if(dirChooser.getDirectory()!=null)
				jtfDir.setText(dirChooser.getDirectory());
		}
	}
	class ActionConvertEncode extends AbstractAction{
		private static final long serialVersionUID = 1L;
		public void actionPerformed(ActionEvent e) {
			File file=new File(jtfDir.getText());
			
			//文件类型不能为空
			if(jtfExt.getText().equals("")){
				flashComponent(jtfExt, jtfExt.getBackground(), Color.PINK, 3, 500);
				return ;
			}
			
			//必须选择一个有效目录
			if(!file.exists()){
				flashComponent(jbtnDirSelect,jbtnDirSelect.getBackground(), Color.pink, 3, 500);
				flashComponent(jtfDir,jtfDir.getBackground(), Color.pink, 3, 500);
				return ;
			}
			
			//目录下应该包含此种类型的文件
			DirSpreader dirSpreader=new DirSpreader(file,jtfExt.getText());
			List<File> files=dirSpreader.getFileList();//<File>
			if(files.size()<=0){
				JOptionPane.showMessageDialog(MainUI.this, 
						"没有文件可以转换，选中的目录下没有任何扩展名为'"+jtfExt.getText()+"'的文件。", 
						"错误", JOptionPane.ERROR_MESSAGE);
				return;
			}else{
				//列举将要转换的文件清单
				listShower.setItems(files);
				int result=JOptionPane.showConfirmDialog(MainUI.this,listShower, 
						"将对如下文件进行编码转换:",JOptionPane.OK_CANCEL_OPTION);
				if(result==JOptionPane.OK_OPTION){
					String report="";
					int count=files.size();
					for(int i=0;i<count;i++){
						//对文件清单进行转换
						TextFileAdapter tf=new TextFileAdapter((File)files.get(i));
						tf.saveFile(jcomboEncode.getSelectedItem().toString());
						report+=tf.getFile().getName()+
							" (由"+tf.getFileEncoder()+"转换为:"
							+jcomboEncode.getSelectedItem()+")\n";
					}
					ReportShower reportShower=new ReportShower(report);
					JOptionPane.showMessageDialog(MainUI.this, reportShower);
				}
			}
		}
	}
	class ListShower extends JPanel{
		private static final long serialVersionUID = 1L;
		JList jList=new JList();
		JScrollPane jScroll=new JScrollPane(jList);
		public ListShower(){
			setLayout(new BorderLayout());
			add(jScroll,"Center");
			jList.setCellRenderer(new FileNameRender());
		}
		public void setItems(List<File> lst){//<File>
			jList.setListData(lst.toArray());
		}
		class FileNameRender extends DefaultListCellRenderer{
			private static final long serialVersionUID = 1L;
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);
				File file=(File)value;
				this.setText(file.getAbsolutePath());
				return this;
			}
			
		}
	}
	class ReportShower extends JPanel{
		private static final long serialVersionUID = 1L;
		private JTextArea jtaReport=new JTextArea();
		private JScrollPane scroll=new JScrollPane(jtaReport);
		public ReportShower(String report){
			jtaReport.setText(report);
			setLayout(new BorderLayout());
			add(scroll,"Center");
			setPreferredSize(new Dimension(250,300));
		}
	}
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
                    //System.out.println("Set Color "+comp.getBackground());
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
}
