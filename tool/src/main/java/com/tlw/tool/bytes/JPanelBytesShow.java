package com.tlw.tool.bytes;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.swing.ButtonGroup;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.tlw.util.UtilUi;

/**
 * @author liwei.tang@magustek.com
 * @since 2014年12月16日
 */
public class JPanelBytesShow extends JPanel implements ActionListener{

	private static final long serialVersionUID = 6649041726527622463L;

	public static void main(String[] args) {
		UtilUi.initUIManager();
		JPanelBytesShow pane=new JPanelBytesShow();
		UtilUi.show(pane, 600, 400, "Java基础数据类型 二进制分析工具");
	}
	
	JLabel jlabelData=new JLabel("数据:");
	JTextField jtextFieldData=new JTextField();
	
	JLabel jlabelType=new JLabel("类型:");
	JRadioButton jradioButtonByte=new JRadioButton("byte");
	JRadioButton jradioButtonShort=new JRadioButton("short");
	JRadioButton jradioButtonInt=new JRadioButton("int");
	JRadioButton jradioButtonFloat=new JRadioButton("float");
	JRadioButton jradioButtonLong=new JRadioButton("long");
	JRadioButton jradioButtonDouble=new JRadioButton("double");
	
	JLabel jlabelEnding=new JLabel("字节序:");
	JRadioButton jradioButtonBig=new JRadioButton("Big-Endding");
	JRadioButton jradioButtonLittle=new JRadioButton("Little-Endding");
	
	JLabel jlabelByte=new JLabel("二进制:");
	JEditorPane jeditorPaneByte=new JEditorPane();
	
	public JPanelBytesShow(){
		//类型面板
		JPanel jpanelTypes=new JPanel();
		jpanelTypes.setLayout(new GridLayout(1, 6));
		jpanelTypes.add(jradioButtonByte);
		jpanelTypes.add(jradioButtonShort);
		jpanelTypes.add(jradioButtonInt);
		jpanelTypes.add(jradioButtonFloat);
		jpanelTypes.add(jradioButtonDouble);
		jpanelTypes.add(jradioButtonLong);
		
		//字节码面板
		JPanel jpanelEnding=new JPanel();
		jpanelEnding.setLayout(new GridLayout(1, 3));
		jpanelEnding.add(jradioButtonBig);
		jpanelEnding.add(jradioButtonLittle);
		
		//主界面
		setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.insets=new Insets(3,3,3,3);
		gbc.gridx=0;
		gbc.gridy=GridBagConstraints.RELATIVE;
		gbc.anchor=GridBagConstraints.EAST;
		add(jlabelData, gbc);
		add(jlabelType, gbc);
		add(jlabelEnding, gbc);
		add(jlabelByte, gbc);
		
		gbc.gridx=1;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbc.weightx=1;
		
		add(jtextFieldData, gbc);
		add(jpanelTypes, gbc);
		add(jpanelEnding, gbc);
		
		gbc.weighty=1;
		gbc.fill=GridBagConstraints.BOTH;
		add(new JScrollPane(jeditorPaneByte), gbc);
		
		ButtonGroup bgTypes=new ButtonGroup();
		bgTypes.add(jradioButtonByte);
		bgTypes.add(jradioButtonShort);
		bgTypes.add(jradioButtonInt);
		bgTypes.add(jradioButtonFloat);
		bgTypes.add(jradioButtonDouble);
		bgTypes.add(jradioButtonLong);
		jradioButtonInt.setSelected(true);
		
		ButtonGroup bgEnding=new ButtonGroup();
		bgEnding.add(jradioButtonBig);
		bgEnding.add(jradioButtonLittle);
		jradioButtonBig.setSelected(true);
		
		jradioButtonBig.setToolTipText("BIG_ENDIAN 用于java、PowerPC 、SPARC、Motorola等");
		jradioButtonLittle.setToolTipText("LITTLE-ENDIAN 用于x86、ia架构");
		jtextFieldData.setToolTipText("按回车改变");
		
		jradioButtonByte.addActionListener(this);
		jradioButtonShort.addActionListener(this);
		jradioButtonInt.addActionListener(this);
		jradioButtonFloat.addActionListener(this);
		jradioButtonDouble.addActionListener(this);
		jradioButtonLong.addActionListener(this);
		jradioButtonBig.addActionListener(this);
		jradioButtonLittle.addActionListener(this);
		jtextFieldData.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String data=jtextFieldData.getText();
		if(data==null || data.trim().equals("")){
			return;
		}
		
		ByteOrder bo;
		if(jradioButtonBig.isSelected()){
			bo=ByteOrder.BIG_ENDIAN;
		}else{
			bo=ByteOrder.LITTLE_ENDIAN;
		}
		
		String text="";
		if(jradioButtonByte.isSelected()){
			byte value=Byte.parseByte(data);
			text=byte2HexString(value);
		}else if(jradioButtonShort.isSelected()){
			short value=Short.parseShort(data);
			ByteBuffer bb=ByteBuffer.allocate(2);
			bb.order(bo);
			bb.asShortBuffer().put(value);
			byte[] bytes=bb.array();
			text=bytes2HexString(bytes);
		}else if(jradioButtonInt.isSelected()){
			int value=Integer.parseInt(data);
			ByteBuffer bb=ByteBuffer.allocate(4);
			bb.order(bo);
			bb.asIntBuffer().put(value);
			byte[] bytes=bb.array();
			text=bytes2HexString(bytes);
		}else if(jradioButtonFloat.isSelected()){
			float value=Float.parseFloat(data);
			ByteBuffer bb=ByteBuffer.allocate(4);
			bb.order(bo);
			bb.asFloatBuffer().put(value);
			byte[] bytes=bb.array();
			text=bytes2HexString(bytes);
		}else if(jradioButtonDouble.isSelected()){
			double value=Double.parseDouble(data);
			ByteBuffer bb=ByteBuffer.allocate(8);
			bb.order(bo);
			bb.asDoubleBuffer().put(value);
			byte[] bytes=bb.array();
			text=bytes2HexString(bytes);
		}else if(jradioButtonLong.isSelected()){
			long value=Long.parseLong(data);
			ByteBuffer bb=ByteBuffer.allocate(8);
			bb.order(bo);
			bb.asLongBuffer().put(value);
			byte[] bytes=bb.array();
			text=bytes2HexString(bytes);
		}
		jeditorPaneByte.setText(text);
	}
	
	public static String byte2HexString(byte b) {
		String hex = Integer.toHexString(b & 0xFF);
		if (hex.length() == 1) {
			hex = '0' + hex;
		}
		return hex.toUpperCase();
	}

	public static String bytes2HexString(byte[] bytes){
		StringBuilder result=new StringBuilder();
		for(byte byt:bytes){
			result.append(byte2HexString(byt)+" ");
		}
		return result.toString();
	}
}
