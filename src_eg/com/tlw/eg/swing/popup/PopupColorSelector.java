package com.tlw.eg.swing.popup;
/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-10-23
Description:
 ********************************/
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
 
public class PopupColorSelector {
 
   void makeUI() {
 
      final JPanel panel = new JPanel();
      final JColorChooser chooser = new JColorChooser();
      final JPopupMenu menu = new JPopupMenu();
      menu.add(chooser);
 
      panel.addMouseListener(new MouseAdapter() {

         @Override
         public void mousePressed(MouseEvent e) {
            showChooser(e);
         }
 
         @Override
         public void mouseReleased(MouseEvent e) {
            showChooser(e);
         }
 
         private void showChooser(MouseEvent e) {
            if (e.isPopupTrigger()) {
               menu.show(panel, e.getX(), e.getY());
            }
         }
      });
 
      chooser.getSelectionModel().
              addChangeListener(new ChangeListener() {
 
         @Override
         public void stateChanged(ChangeEvent e) {
            panel.setBackground(chooser.getColor());
         }
      });
 
      JFrame frame = new JFrame("Right click for color chooser");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(400, 400);
      frame.add(panel);
      frame.setVisible(true);
   }
 
   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
 
         @Override
         public void run() {
            new PopupColorSelector().makeUI();
         }
      });
   }
}
