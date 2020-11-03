package com.tlw.tools.laf;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.JColorChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.text.DefaultFormatterFactory;

public class A04JTextFieldColor extends JFormattedTextField {
	private static final long serialVersionUID = -2187678006900285075L;
	public static void main(String[] args) {
		A04JTextFieldColor tf=new A04JTextFieldColor();
		tf.setColumns(25);
		JFrame f=new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(400,300);
		f.setLayout(new FlowLayout());
		f.add(tf);
		f.setVisible(true);
	}
	public A04JTextFieldColor(){
		addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				Color c=JColorChooser.showDialog(A04JTextFieldColor.this, "", getForeground());
				if(c!=null){
					setValue(c);
					repaint();
				}
			}
		});
		addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setValue(A04JTextFieldColor.this.getValue());
			}
		});
		setValue(getForeground());
		setHorizontalAlignment(RIGHT);
		
		DefaultFormatterFactory dff=new DefaultFormatterFactory();
		dff.setDefaultFormatter(new AbstractFormatter(){
			private static final long serialVersionUID = -3656301734799723130L;
			@Override
			public Object stringToValue(String text) throws ParseException {
				if(text.equalsIgnoreCase(NULL))return null;
				return UtilColor.decode(text);
				
			}

			@Override
			public String valueToString(Object value) throws ParseException {
				if(value==null)return NULL;
				Color colorValue=(Color)value;
				return UtilColor.encode(colorValue);
			}
		});
		setFormatterFactory(dff);
	}
	public static String NULL="N/A";
	public static Color NULL_COLOR=Color.WHITE;
	public void paint(Graphics g){
		super.paint(g);
		Insets borderInsets=getBorder().getBorderInsets(this);
		int borderWidth=2;
		int borderSize=getHeight()-borderInsets.top-borderInsets.bottom;
		int boxSize=borderSize-borderWidth*2;
		
		Color colorValue=(Color) getValue();
		if(colorValue==null){
			g.setColor(NULL_COLOR);
		}else{
			g.setColor(colorValue);
		}
		//经photoShop校对截图drawRect方法绘制的高度多了一个像素，因此尺寸减1.
		g.drawRect(borderInsets.left, 
				borderInsets.top, 
				borderSize-1, 
				borderSize-1);
		g.fillRect(borderWidth+borderInsets.left, borderWidth+borderInsets.top, boxSize, boxSize);
	}
}