package com.tlw.eg.swing.tray;
import java.awt.AWTException;
import java.awt.CheckboxMenuItem;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author k02
 */
public class ClientTray {

    private PopupMenu popup;
    private TrayIcon trayIcon;
    private SystemTray tray;

    public ClientTray() {
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray   is   not   supported ");
            return;
        }
        popup = new PopupMenu();
        trayIcon = new TrayIcon(createImage("2.gif", "tray   icon "));
        tray = SystemTray.getSystemTray();

        CheckboxMenuItem cb1 = new CheckboxMenuItem("Set   auto   size ");
        MenuItem cb2 = new MenuItem("change   Icon ");
        MenuItem exitItem = new MenuItem("Exit ");

        popup.add(cb1);
        popup.add(cb2);
        popup.addSeparator();
        popup.add(exitItem);

        trayIcon.setPopupMenu(popup);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon   could   not   be   added. ");
            return;
        }

        //new DesktopMonitorServer();

        trayIcon.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "This   dialog   box   is   run   from   System   Tray ");
            }
        });

        cb1.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                int cb1Id = e.getStateChange();
                if (cb1Id == ItemEvent.SELECTED) {
                    trayIcon.setImageAutoSize(true);
                } else {
                    trayIcon.setImageAutoSize(false);
                }
            }
        });

        cb2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                trayIcon.setImage(createImage("1.gif ", "tray   icon "));
            }
        });

        exitItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon);
                System.exit(0);
            }
        });
        //桌面监测
        //new DesktopMonitorServer();
    }

    protected Image createImage(String path, String description) {
        URL imageURL = ClientTray.class.getResource(path);

        if (imageURL == null) {
            System.err.println("Resource   not   found:   " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }

    public static void main(String[] args){
    	JFrame f=new JFrame();
    	f.setSize(600,300);
    	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	f.setVisible(true);
        new ClientTray();
    }
}
