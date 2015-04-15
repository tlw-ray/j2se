package com.tlw.eg.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.geom.AffineTransform;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.tlw.util.UtilUi;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年1月19日
 */
public class AffineTransformLine extends JPanel implements ChangeListener{

	private static final long serialVersionUID = 4074292226813756792L;
	
	public static void main(String[] args) {
		AffineTransformLine pane=new AffineTransformLine();
		UtilUi.show(pane, 400, 300, "");

	}
	
	JLabel jlabelScaleX=new JLabel("ScaleX(-10, 10):");
	JSlider jsliderScaleX=new JSlider(-10, 10);
	JLabel jlabelScaleY=new JLabel("ScaleY(-10, 10):");
	JSlider jsliderScaleY=new JSlider(-10, 10);
	JLabel jlabelShearX=new JLabel("ShearX(-10, 10):");
	JSlider jsliderShearX=new JSlider(-10, 10);
	JLabel jlabelShearY=new JLabel("ShearY(-10, 10):");
	JSlider jsliderShearY=new JSlider(-10, 10);
	JLabel jlabelTranslationX=new JLabel("TranslationX(-10, 10):");
	JSlider jsliderTranslationX=new JSlider(-10, 10);
	JLabel jlabelTranslationY=new JLabel("TranslationY(-10, 10):");
	JSlider jsliderTranslationY=new JSlider(-10, 10);

	AffineTransform tx=new AffineTransform();
	
	public AffineTransformLine(){
		jsliderScaleX.addChangeListener(this);
		jsliderScaleY.addChangeListener(this);
		jsliderShearX.addChangeListener(this);
		jsliderShearY.addChangeListener(this);
		jsliderTranslationX.addChangeListener(this);
		jsliderTranslationY.addChangeListener(this);
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.weighty=1;
		gbc.insets=new Insets(5,5,5,5);
		gbc.anchor=GridBagConstraints.EAST;
		gbc.gridy=GridBagConstraints.RELATIVE;
		gbc.gridx=0;
		add(jlabelScaleX, gbc);
		add(jlabelScaleY, gbc);
		add(jlabelShearX, gbc);
		add(jlabelShearY, gbc);
		add(jlabelTranslationX, gbc);
		add(jlabelTranslationY, gbc);
		
		gbc.fill=GridBagConstraints.BOTH;
		gbc.weightx=1;
		gbc.gridx=1;
		add(jsliderScaleX, gbc);
		add(jsliderScaleY, gbc);
		add(jsliderShearX, gbc);
		add(jsliderShearY, gbc);
		add(jsliderTranslationX, gbc);
		add(jsliderTranslationY, gbc);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2=(Graphics2D)g;
		g2.transform(tx);
		g2.drawLine(50, 50, 150, 50);
		g2.drawLine(150, 50, 150, 150);
		g2.drawLine(150, 150, 50, 150);
		g2.drawLine(50, 150, 50, 50);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		int scaleX=jsliderScaleX.getValue();
		int scaleY=jsliderScaleY.getValue();
		int shearX=jsliderShearX.getValue();
		int shearY=jsliderShearY.getValue();
		int translateX=jsliderTranslationX.getValue();
		int translateY=jsliderTranslationY.getValue();
		tx.setTransform(scaleX, shearY, shearX, scaleY, translateX, translateY);
		repaint();
	}
}
