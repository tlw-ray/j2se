package com.tlw.tool.googlearth.img;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

//用于读取矩形的资源文件
//Google Earth的资源文件为256*256的png图像,每幅含8行8列,32*32的图标
public class ImageIncise extends JFrame{
	private static final long serialVersionUID = -4733203614036109045L;
	public ImageIncise() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    String inputExt=".png";//资源文件扩展名
    String inputImg="F:\\ico\\google\\res\\palette-";//资源文件基础名
    String outputExt=".png";//输出文件扩展名
    String outputDir="E:\\app.gis\\GHGIS2007-05-10\\SDE83Editor\\src\\tlw\\mogis\\res";//输出路径
    JPanel paneCenter = new JPanel();
    JLabel jLabel1 = new JLabel();
    GridBagLayout gridBagLayout1 = new GridBagLayout();
    Border border1 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,
            Color.white, Color.white, new Color(103, 101, 98),
            new Color(148, 145, 140));
    JLabel jLabel2 = new JLabel();
    Border border2 = BorderFactory.createBevelBorder(BevelBorder.RAISED,
            Color.white, Color.white, new Color(103, 101, 98),
            new Color(148, 145, 140));
    JPanel paneNorth = new JPanel();
    JComboBox jComboBox1 = new JComboBox();
    ButtonGroup buttonGroup1 = new ButtonGroup();
    ButtonGroup buttonGroup2 = new ButtonGroup();
    FlowLayout flowLayout1 = new FlowLayout();
    public static void main(String[] args) {
        ImageIncise i=new ImageIncise();
        i.setVisible(true);
    }
    BufferedImage bimg=new BufferedImage(32,32,BufferedImage.TYPE_4BYTE_ABGR);
    ImageIcon cico;
    BufferedImage cimg;
    int bigImgSize=260;
    int smallImgSize=38;
    private void jbInit() throws Exception {
        setSize(256+20,288+40);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jComboBox1.setPreferredSize(new Dimension(160, 23));
        paneNorth.setLayout(flowLayout1);
        flowLayout1.setVgap(0);
        this.getContentPane().add(paneCenter, java.awt.BorderLayout.CENTER);
        paneCenter.setLayout(gridBagLayout1);
        jLabel1.setBorder(border1);
        jLabel1.setMaximumSize(new Dimension(bigImgSize, bigImgSize));
        jLabel1.setMinimumSize(new Dimension(bigImgSize, bigImgSize));
        jLabel1.setPreferredSize(new Dimension(bigImgSize, bigImgSize));
        jLabel2.setBorder(border2);
        jLabel2.setMaximumSize(new Dimension(smallImgSize, smallImgSize));
        jLabel2.setMinimumSize(new Dimension(smallImgSize, smallImgSize));
        jLabel2.setPreferredSize(new Dimension(smallImgSize, smallImgSize));
        paneCenter.add(jLabel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
                , GridBagConstraints.CENTER, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 0, 0));
        this.getContentPane().add(paneNorth, java.awt.BorderLayout.NORTH);
        paneNorth.add(jComboBox1);
        paneNorth.add(jLabel2);
        jLabel1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                int x=e.getX()-(e.getX()%32);
                int y=e.getY()-(e.getY()%32);
                System.out.println("X:"+x+" Y:"+y);

                BufferedImage img=new BufferedImage(32,32,BufferedImage.TYPE_4BYTE_ABGR);
                Graphics g=img.createGraphics();
                g.drawImage(cimg.getSubimage(x,y,32,32),0,0,32,32,ImageIncise.this);
                jLabel2.setIcon(new ImageIcon(img));
            }
        });
        DefaultComboBoxModel model=new DefaultComboBoxModel();
        for(int i=2;i<6;i++){
            model.addElement(i);
        }
        jComboBox1.setModel(model);
        jComboBox1.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                refreshImg((Integer)e.getItem());
            }
        });
        refreshImg(2);
    }
    void refreshImg(int i){
        cico=new ImageIcon(inputImg+i+inputExt);
        cimg=new BufferedImage(256,256,BufferedImage.TYPE_4BYTE_ABGR);
        cimg.getGraphics().drawImage(cico.getImage(),0,0,256,256,this);
        jLabel1.setIcon(cico);
    }
}
