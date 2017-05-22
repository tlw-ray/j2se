package com.tlw.eg.swing.popup;
/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-10-23
Description:
 ********************************/
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.text.DefaultEditorKit;
 
public class JTextFieldPopup extends JFrame implements MouseListener
{
	private static final long serialVersionUID = 1L;
	JPopupMenu popup;
 
	public JTextFieldPopup()
	{
		popup = new JPopupMenu();
		popup.add( new DefaultEditorKit.CutAction() );
		popup.add( new DefaultEditorKit.CopyAction() );
		popup.add( new DefaultEditorKit.PasteAction() );
 
		JTextField textField = new JTextField("Right Click For Popup");
		textField.addMouseListener( this );
		getContentPane().add(textField);
	}
 
	public void mouseReleased(MouseEvent e)
	{
		if (e.isPopupTrigger())
		{
			popup.show(e.getComponent(), e.getX(), e.getY());
		}
	}
 
	public void mousePressed(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
 
	public static void main(String[] args)
	{
		JFrame frame = new JTextFieldPopup();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.pack();
		frame.setLocationRelativeTo( null );
		frame.setVisible(true);
	 }
}

