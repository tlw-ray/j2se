package com.tlw.eg.swing.progress;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-11-5
Description:先setOpaque(false);把组件设置为透明的
然后在paintComponent中使用setComposite设置一个alpha值,
再用颜色填充组件的整个区域, 这样组件就是半透明的, 
 ********************************/
public class ProgressPanel extends JPanel implements Runnable {
    private static final long serialVersionUID = 8850567012535151256L;
    
    private boolean           stopped          = false;
    private int               delay            = 500;
    
    float                     lineLength       = 50;
    float                     centerX;
    float                     centerY;
    final int                 countOfLines     = 10;
    final float               PI               = 3.14f;
    float                     smallRadius      = 50f;
    float                     largerRadius     = smallRadius + lineLength;
    float                     startX, startY;
    float                     endX, endY;
    float[]                   linesPoints;
    int                       currentLineIndex = 0;
    
    private Font              infoFont         = new Font(null, Font.BOLD, 24);
    private String            info;
    
    public ProgressPanel() {
        this.setOpaque(false); // 很重要, 设置Panel为透明的.
        
        // 计算线的坐标, 每条线有四个坐标.
        // 这些线在一个圆环上.起始坐标在小圆上, 终止坐标在大圆上.
        linesPoints = new float[countOfLines * 4];
        float delta = 2 * PI / linesPoints.length; // 线之间的角度差.
        for (int i = 0; i < linesPoints.length; i += 4) {
            float angle = i * delta;
            linesPoints[i + 0] = (float) (smallRadius * Math.cos(angle)); // StartX
            linesPoints[i + 1] = (float) (smallRadius * Math.sin(angle)); // StartY
            linesPoints[i + 2] = (float) (largerRadius * Math.cos(angle)); // EndX
            linesPoints[i + 3] = (float) (largerRadius * Math.sin(angle)); // EndY
        }
        
        this.setInfo("Hello...");
    }
    
    /**
     * 启动动画效果
     */
    public void start() {
        Component com = SwingUtilities.getRoot(this);
        
        if (com.getClass().equals(JFrame.class)) {
            ((JFrame) com).getGlassPane().setVisible(true);
        }
        
        new Thread(this).start();
    }
    
    /**
     * 停止动画
     */
    public void stop() {
        Component com = SwingUtilities.getRoot(this);
        
        if (com.getClass().equals(JFrame.class)) {
            ((JFrame) com).getGlassPane().setVisible(false);
        }
        stopped = true;
    }
    
    /**
     * 设置显示的信息
     * 
     * @param info
     */
    public void setInfo(String info) {
        this.info = info;
        this.repaint();
    }
    
    /**
     * 设置显示的字体
     * 
     * @param font
     */
    public void setInfoFont(Font font) {
        this.infoFont = font;
        this.repaint();
    }
    
    /**
     * 设置动画的缓迟时间
     * 
     * @param delay 动画缓迟时间
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }
    
    public void run() {
        try {
            while (!stopped) {
                currentLineIndex += 4;
                currentLineIndex %= linesPoints.length;
                this.repaint();
                
                Thread.sleep(delay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // 使用反锯齿技术.
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        // 计算圆环的中心坐标.
        int width = this.getWidth();
        int height = this.getHeight();
        centerX = width / 2;
        centerY = height / 2 - height / 8;
        
        // 设置Panel的透明度, 把Panel填充透明色, 与背景作对比.
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                                                    .1f));
        g2d.fillRect(0, 0, width, height);
        
        // 设置线的属性: 线宽, 线两端的样式.
        BasicStroke dashed = new BasicStroke(10, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND);
        g2d.setStroke(dashed);
        
        // 把所有的线段都画出来, 颜色浅一些.
        for (int i = 0; i < linesPoints.length; i += 4) {
            g2d.drawLine((int) (linesPoints[i + 0] + centerX),
                         (int) (linesPoints[i + 1] + centerY),
                         (int) (linesPoints[i + 2] + centerX),
                         (int) (linesPoints[i + 3] + centerY));
        }
        
        // 画当前线段, 颜色比其他线段深一些.
        g2d.setColor(g2d.getColor().darker().darker().darker().darker());
        g2d.drawLine((int) (linesPoints[currentLineIndex + 0] + centerX),
                     (int) (linesPoints[currentLineIndex + 1] + centerY),
                     (int) (linesPoints[currentLineIndex + 2] + centerX),
                     (int) (linesPoints[currentLineIndex + 3] + centerY));
        
        // 显示信息
        if (info != null) {
            g2d.setFont(infoFont);
            FontMetrics metrics = g2d.getFontMetrics();
            // 设置信息在界面中的位置.
            int infoPosX = (int) ((width - metrics.stringWidth(info)) / 2);
            int infoPosY = (int) (centerY + largerRadius + metrics.getHeight());
            g2d.drawString(info, infoPosX, infoPosY);
        }
        
    }
    
    private static void createGUIAndShow() {
        JFrame frame = new JFrame();
        
        ProgressPanel glassPane = new ProgressPanel();
        frame.setGlassPane(glassPane);
        glassPane.setDelay(200);
        glassPane.setInfo("Program is running...");
        glassPane.start();
        
        JTextArea area = new JTextArea();
        frame.add(area);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ProgressPanel.createGUIAndShow();
            }
        });
    }
}

