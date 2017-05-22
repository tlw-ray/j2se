package com.tlw.eg.swing.paint;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;

import com.tlw.swing.Shower;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-6-23
@version:2009-6-23
Description:
 */
public class DrawLines extends JPanel implements MouseListener,MouseMotionListener{
	private static final long serialVersionUID = -5138578600687965094L;
	public static void main(String[] args) {
		DrawLines pane=new DrawLines();
		Shower.show( pane,800,600);
	}
	public static int fanwei=20;
	List<Integer> linePositions=new Vector<Integer>();
	int currentLineIndex=-1;
	public DrawLines(){
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	public void paint(Graphics g){
		super.paint(g);
		g.drawString("请从屏幕左侧20像素内拉拽鼠标。拽出的线是可以被再次拖动的。", 100, 100);
		for(int i=0;i<linePositions.size();i++){
			int xx=((Integer)linePositions.get(i)).intValue();
			g.drawLine(xx, 0, xx, getHeight());
		}
	}
	public void mouseClicked(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
		if(e.getX()<fanwei){
			linePositions.add(new Integer(e.getX()));
			currentLineIndex=linePositions.size()-1;
		}else{
			for(int i=0;i<linePositions.size();i++){
				int xx=((Integer)linePositions.get(i)).intValue();
				if(xx-fanwei<e.getX() && xx+fanwei>e.getX()){
					currentLineIndex=i;
					return;
				}
			}
		}
	}
	public void mouseReleased(MouseEvent e) {
		if(e.getX()<fanwei){
			linePositions.remove(currentLineIndex);
		}else{
			currentLineIndex=-1;
		}
	}
	public void mouseDragged(MouseEvent e) {
		if(currentLineIndex!=-1){
			linePositions.set(currentLineIndex, new Integer(e.getX()));
			repaint();
		}
	}
	public void mouseMoved(MouseEvent e) {
	}
}
