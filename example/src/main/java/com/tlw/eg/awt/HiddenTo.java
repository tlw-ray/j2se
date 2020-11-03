package com.tlw.eg.awt;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*使组件可以动画方式隐藏到另一个界面组件
 通常用来一个按钮点击后，使一个面板逐渐缩小，并隐藏到按钮的位置，以表示此按钮控制此面板的显隐。
 作者:tlw_ray@163.com
 2007-8-6
 */
public class HiddenTo {
    public Rectangle originalRect;
    public void changeVisible(Component pane, Component onOff) {
        int step = 1; //一次变化几个像素
        int left1, top1, right1, bottom1;
        if (pane.isVisible()) {
            left1 = pane.getX();
            top1 = pane.getY();
            right1 = pane.getX() + pane.getWidth();
            bottom1 = pane.getY() + pane.getHeight();
        } else {
            left1 = originalRect.x;
            top1 = originalRect.y;
            right1 = originalRect.x + originalRect.width;
            bottom1 = originalRect.y + originalRect.height;
        }

        int left2 = onOff.getX();
        int top2 = onOff.getY();
        int right2 = onOff.getX() + onOff.getWidth();
        int bottom2 = onOff.getY() + onOff.getHeight();

        int lDis = Math.abs(left1 - left2); //左距离
        int tDis = Math.abs(top1 - top2); //上距离
        int rDis = Math.abs(right1 - right2); //右距离
        int bDis = Math.abs(bottom1 - bottom2); //下距离

        double averDis = (lDis + tDis + rDis + bDis) / 4.0; //平均距离
        double averStep = averDis / (step * 1.0); //平均步数

        double leftStep = lDis / averStep;
        double topStep = tDis / averStep;
        double rightStep = rDis / averStep;
        double bottomStep = bDis / averStep;

        if (left1 > left2)
            leftStep = -leftStep;
        if (top1 > top2)
            topStep = -topStep;
        if (right1 > right2)
            rightStep = -rightStep;
        if (bottom1 > bottom2)
            bottomStep = -bottomStep;
        originalRect = pane.getBounds();
        double cleft, ctop, cright, cbottom;
        if (pane.isVisible()) {
            //doHidden
            cleft = left1;
            ctop = top1;
            cright = right1;
            cbottom = bottom1;

            for (int i = 0; i < averStep; i++) {
                cleft += leftStep;
                ctop += topStep;
                cright += rightStep;
                cbottom += bottomStep;
                pane.setBounds((int) cleft, (int) ctop, (int) (cright - cleft),
                               (int) (cbottom - ctop));
            }
            pane.setVisible(false);
        } else {
            //doShow
            cleft = left2;
            ctop = top2;
            cright = right2;
            cbottom = bottom2;
            pane.setVisible(true);
            for (int i = 0; i < averStep; i++) {
                cleft -= leftStep;
                ctop -= topStep;
                cright -= rightStep;
                cbottom -= bottomStep;
                pane.setBounds((int) cleft, (int) ctop, (int) (cright - cleft),
                               (int) (cbottom - ctop));
            }
        }
    }
    //Example
    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setLayout(null);
        final Panel pane1 = new Panel();
        pane1.setBackground(Color.pink);
        pane1.setBounds(200, 200, 300, 150);

        final Button btn = new Button("close");
        btn.setBounds(20, 20, 40, 22);
        btn.addActionListener(new ActionListener() {
            HiddenTo ht = new HiddenTo();
            public void actionPerformed(ActionEvent e) {
                ht.changeVisible(pane1, btn);
            }
        });
        new com.tlw.eg.awt.MouseDrager(btn);
        new com.tlw.eg.awt.MouseDrager(pane1);
        frame.add(btn);
        frame.add(pane1);
        frame.setSize(600, 400);
        frame.setVisible(true);
    }
}