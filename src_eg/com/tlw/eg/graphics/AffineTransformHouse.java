package com.tlw.eg.graphics;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AffineTransformHouse extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1623673450022056803L;

	MyCanvas canvas;

	JSlider sliderTransX, sliderTransY, sliderRotateTheta, sliderRotateX, sliderRotateY, sliderScaleX, sliderScaleY, sliderWidth;

	double transX = 0.0;

	double transY = 0.0;

	double rotateTheta = 0.0;

	double rotateX = 150.0;

	double rotateY = 150.0;

	double scaleX = 1.0;

	double scaleY = 1.0;

	float width = 1.0f;

	public AffineTransformHouse() {
		super(new BorderLayout());
		JPanel controlPanel = new JPanel(new GridLayout(3, 3));
		add(controlPanel, BorderLayout.NORTH);

		controlPanel.add(new JLabel("Translate(dx,dy): "));

		sliderTransX = setSlider(controlPanel, JSlider.HORIZONTAL, 0, 300, 150, 100, 50);
		sliderTransY = setSlider(controlPanel, JSlider.HORIZONTAL, 0, 300, 150, 100, 50);

		// To control rotation
		controlPanel.add(new JLabel("Rotate(Theta,ox,oy): "));
		sliderRotateTheta = setSlider(controlPanel, JSlider.HORIZONTAL, 0, 360, 0, 90, 45);

		JPanel subPanel = new JPanel();
		subPanel.setLayout(new GridLayout(1, 2));

		sliderRotateX = setSlider(subPanel, JSlider.HORIZONTAL, 0, 300, 150, 150, 50);

		sliderRotateY = setSlider(subPanel, JSlider.HORIZONTAL, 0, 300, 150, 150, 50);
		controlPanel.add(subPanel);

		// To control scaling
		controlPanel.add(new JLabel("Scale(sx,sy)x10E-2:"));

		sliderScaleX = setSlider(controlPanel, JSlider.HORIZONTAL, 0, 200, 100, 100, 10);

		sliderScaleY = setSlider(controlPanel, JSlider.HORIZONTAL, 0, 200, 100, 100, 10);

		// To control width of line segments
		JLabel label4 = new JLabel("Width Control:", JLabel.RIGHT);
		sliderWidth = new JSlider(JSlider.HORIZONTAL, 0, 20, 1);
		sliderWidth.setPaintTicks(true);
		sliderWidth.setMajorTickSpacing(5);
		sliderWidth.setMinorTickSpacing(1);
		sliderWidth.setPaintLabels(true);
		sliderWidth.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				width = sliderWidth.getValue();
				canvas.repaint();
			}
		});
		JPanel widthPanel = new JPanel();
		widthPanel.setLayout(new GridLayout(1, 2));
		widthPanel.add(label4);
		widthPanel.add(sliderWidth);
		add(widthPanel, BorderLayout.SOUTH);

		canvas = new MyCanvas();
		add(canvas, "Center");
	}

	public JSlider setSlider(JPanel panel, int orientation, int minimumValue, int maximumValue, int initValue, int majorTickSpacing, int minorTickSpacing) {
		JSlider slider = new JSlider(orientation, minimumValue, maximumValue, initValue);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(majorTickSpacing);
		slider.setMinorTickSpacing(minorTickSpacing);
		slider.setPaintLabels(true);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider tempSlider = (JSlider) e.getSource();

				if (tempSlider.equals(sliderTransX)) {
					transX = sliderTransX.getValue() - 150.0;
					canvas.repaint();
				} else if (tempSlider.equals(sliderTransY)) {
					transY = sliderTransY.getValue() - 150.0;
					canvas.repaint();
				} else if (tempSlider.equals(sliderRotateTheta)) {
					rotateTheta = sliderRotateTheta.getValue() * Math.PI / 180;
					canvas.repaint();
				} else if (tempSlider.equals(sliderRotateX)) {
					rotateX = sliderRotateX.getValue();
					canvas.repaint();
				} else if (tempSlider.equals(sliderRotateY)) {
					rotateY = sliderRotateY.getValue();
					canvas.repaint();
				} else if (tempSlider.equals(sliderScaleX)) {
					if (sliderScaleX.getValue() != 0.0) {
						scaleX = sliderScaleX.getValue() / 100.0;
						canvas.repaint();
					}
				} else if (tempSlider.equals(sliderScaleY)) {
					if (sliderScaleY.getValue() != 0.0) {
						scaleY = sliderScaleY.getValue() / 100.0;
						canvas.repaint();
					}
				}
			}
		});
		panel.add(slider);

		return slider;
	}

	class MyCanvas extends Canvas {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2052481364978309344L;

		public void paint(Graphics g) {
			Graphics2D g2D = (Graphics2D) g;

			g2D.translate(transX, transY);
			g2D.rotate(rotateTheta, rotateX, rotateY);
			g2D.scale(scaleX, scaleY);

			BasicStroke stroke = new BasicStroke(width);
			g2D.setStroke(stroke);

			drawHome(g2D);
		}

		public void drawHome(Graphics2D g2D) {
			Line2D line1 = new Line2D.Float(100f, 200f, 200f, 200f);
			Line2D line2 = new Line2D.Float(100f, 200f, 100f, 100f);
			Line2D line3 = new Line2D.Float(100f, 100f, 150f, 50f);
			Line2D line4 = new Line2D.Float(150f, 50f, 200f, 100f);
			Line2D line5 = new Line2D.Float(200f, 100f, 200f, 200f);
			Line2D line6 = new Line2D.Float(140f, 200f, 140f, 150f);
			Line2D line7 = new Line2D.Float(140f, 150f, 160f, 150f);
//			Line2D line8 = new Line2D.Float(160f, 150f, 160f, 200f);

			g2D.draw(line1);
			g2D.draw(line2);
			g2D.draw(line3);
			g2D.draw(line4);
			g2D.draw(line5);
			g2D.draw(line6);
			g2D.draw(line7);
//			g2D.draw(line8);
		}
	}

	public static void main(String[] a) {
		JFrame f = new JFrame();
		f.getContentPane().add(new AffineTransformHouse());
		f.setDefaultCloseOperation(1);
		f.setSize(700, 550);
		f.setVisible(true);
	}
}
