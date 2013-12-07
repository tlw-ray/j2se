package com.tlw.eg.image.gif;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

public class ImageView extends JComponent {
	private static final long serialVersionUID = -3002203912988620664L;
	private GifFrame frame;
    private int index;

    public ImageView(GifFrame frame, int index) {
        this.frame = frame;
        this.index = index;
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }

    protected void paintComponent(Graphics g) {
        Rectangle clip = g.getClipBounds();
        g.setColor(Color.BLACK);
        g.fillRect(clip.x, clip.y, clip.width, clip.height);
        g.setColor(Color.WHITE);
        Font font = getFont();
        FontMetrics fm = getFontMetrics(font);
        String text = "(" + index + ")";
        g.drawString(text, (getWidth() - fm.stringWidth(text)) / 2, 11);
        g.drawString("x:" + frame.x, 5, 22);
        g.drawString("y:" + frame.y, 5, 33);
        g.drawString("width:" + frame.width, 5, 44);
        g.drawString("height:" + frame.height, 5, 55);
        g.drawString("delayTime:" + frame.delayTime, 5, 66);
        g.drawString("disposalMethod:" + frame.disposalMethod, 5, 77);
        g.drawImage(frame.image, getWidth() - frame.width - 5, getHeight() / 2 - frame.height / 2, null);
    }

    public Dimension getPreferredSize() {
        return new Dimension(frame.width + 120, frame.height + 80);
    }
}