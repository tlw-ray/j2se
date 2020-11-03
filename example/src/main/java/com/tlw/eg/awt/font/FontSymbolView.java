
package com.tlw.eg.awt.font;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.tlw.swing.JFileChooserRecoll;
import com.tlw.swing.NavList;


public class FontSymbolView extends JFrame{
	private static final long serialVersionUID = 2284887341768320494L;
	public static void main(String[] args) {
        FontSymbolView s = new FontSymbolView();
        //s
        s.setVisible(true);
    }
    public FontSymbolView(){
        try {
            jbInit();
            myInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    private void jbInit() throws Exception {
        jButton3.setBackground(Color.lightGray);
        jButton3.setForeground(Color.orange);
        jButton3.setMargin(new Insets(1, 1, 1, 1));
        jButton3.setText("open");
        jButton2.setForeground(Color.orange);
        jButton1.setForeground(Color.orange);
        jFormattedTextField2.setMaximumSize(new Dimension(30, 24));
        jFormattedTextField2.setMinimumSize(new Dimension(30, 24));
        jFormattedTextField2.setPreferredSize(new Dimension(30, 24));
        jFormattedTextField2.setText("500");
        jFormattedTextField1.setMaximumSize(new Dimension(30, 24));
        jFormattedTextField1.setMinimumSize(new Dimension(30, 24));
        jFormattedTextField1.setPreferredSize(new Dimension(30, 24));
        jFormattedTextField1.setText("0");
        jPanel1.setLayout(flowLayout1);
        flowLayout1.setAlignment(FlowLayout.LEFT);
        flowLayout1.setHgap(0);
        flowLayout1.setVgap(0);
        jLabel2.setText("from:");
        jLabel3.setText("to:");
        jButton1.setBackground(Color.lightGray);
        jButton1.setMargin(new Insets(1, 1, 1, 1));
        jButton1.setText("show");
        jLabel4.setText("size:");
        jCheckBox1.setOpaque(false);
        jCheckBox1.setText("Bold");
        jCheckBox2.setOpaque(false);
        jCheckBox2.setText("Italic");
        jButton2.setBackground(Color.lightGray);
        jButton2.setMargin(new Insets(1, 1, 1, 1));
        jButton2.setText("export");
        jPanel2.setBorder(border1);
        jPanel2.setPreferredSize(new Dimension(180, 151));
        jPanel2.setLayout(borderLayout1);
        jLabel1.setBackground(Color.orange);
        jLabel1.setForeground(Color.darkGray);
        jLabel1.setOpaque(true);
        jLabel1.setText("font:");
        jPanel1.setBackground(SystemColor.activeCaptionBorder);
        jButton4.setBackground(Color.lightGray);
        jButton4.setForeground(Color.orange);
        jButton4.setMargin(new Insets(1, 1, 1, 1));
        jButton4.setText("color");
        jPanel1.add(jLabel2);
        jPanel1.add(jFormattedTextField1);
        jPanel1.add(jLabel3);
        jPanel1.add(jFormattedTextField2);
        jPanel1.add(jLabel4);
        jPanel1.add(jSpinner1);
        jPanel1.add(jCheckBox1);
        jPanel1.add(jCheckBox2);
        jPanel1.add(jButton1);
        jPanel1.add(jButton3);
        jPanel1.add(jButton4);
        jPanel1.add(jButton2);
        this.add(paneSouth,"South");
        paneSouth.add(chLabel);
        jScrollPane1.getViewport().add(jTable1);
        this.getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.add(jList1, java.awt.BorderLayout.CENTER);
        jPanel2.add(jLabel1, java.awt.BorderLayout.NORTH);
        this.getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        this.getContentPane().add(jPanel2, java.awt.BorderLayout.WEST);

    }
    Font font;
    Font[] fonts=GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
    DefaultTableModel tableModel=new DefaultTableModel();
    SpinnerNumberModel spModel=new SpinnerNumberModel(50,0,300,1);
    FontSymbolRender tableRender=new FontSymbolRender();
    public void myInit(){
        //InitData
        jSpinner1.setModel(spModel);
        String[] fontNames=new String[fonts.length];
        for(int i=0;i<fonts.length;i++){fontNames[i]=fonts[i].getFontName();}
        jList1.setStrings(fontNames);
        jList1.getJList().setSelectedIndex(0);
        this.jFormattedTextField1.setText("33");
        this.jFormattedTextField2.setText("300");
        showFontSymbol();
        //InitEvent
        jList1.getJList().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e) {
                showFontSymbol();
            }
        });
        this.jCheckBox1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showFontSymbol();
            }
        });
        this.jCheckBox2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showFontSymbol();
            }
        });
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //show
                showFontSymbol();
            }
        });
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //export
                if(fcr.showSaveDialog(FontSymbolView.this)==JFileChooser.APPROVE_OPTION){
                    fcr.save();
                    try {
                        String enter="\r\n";
                        String fileSelect=fcr.getSelectedFile().getPath()+".html";
                        String filename=fcr.getSelectedFile().getName();
                        String imgDir=fileSelect.substring(0,fileSelect.lastIndexOf(filename))+"img";
                        File dir=new File(imgDir);
                        if(!dir.exists())dir.mkdir();
                        System.out.println(imgDir);
                        FileWriter fw=new FileWriter(new File(fileSelect));
                        fw.write("<html><head></head><body><table border=1 bordercolor=blue width=200>"+enter);
                        for(int i=0;i<tableModel.getRowCount();i++){
                            String ch=tableModel.getValueAt(i,0).toString();
                            String row="<tr><td>"+
                                       ch+"</td><td><img src=img/"+
                                       ch+".png ></td></tr>"+enter;
                            fw.write(row);
                            makeImage(Integer.parseInt(ch),imgDir+"\\"+ch+".png");
                        }
                        fw.write("</table></body></html>");
                        fw.flush();
                        fw.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //open
            }
        });
        jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Color
                if(JOptionPane.showConfirmDialog(FontSymbolView.this,jcc,"选择符号颜色",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
                    jButton4.setForeground(jcc.getColor());
                    showFontSymbol();
                }
            }
        });

        //init UI
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,400);
    }
    public void showFontSymbol(){
        int from=Integer.parseInt(jFormattedTextField1.getText());
        int to=Integer.parseInt(jFormattedTextField2.getText());
        //font=fonts[];
        jButton4.setForeground(jcc.getColor());
        boolean isBold=this.jCheckBox1.isSelected();
        boolean isItalic=this.jCheckBox2.isSelected();
        String fontName= jList1.getJList().getSelectedValue().toString();
        int fontSize=spModel.getNumber().intValue();
        int fontStyle=Font.PLAIN;
        if(isBold)fontStyle+=Font.BOLD;
        if(isItalic)fontStyle+=Font.ITALIC;
        font=new Font(fontName,fontStyle,fontSize);
        chLabel.setFont(font);
        Vector<Vector<String>> rows=new Vector<Vector<String>>();
        for(int i=from;i<=to;i++){
            Vector<String> strs=new Vector<String>();
            strs.add(i+"");
            strs.add(String.valueOf((char)i));
            strs.add("");
            rows.add(strs);
        }
        String[] cols=new String[]{"ID","符号","描述"};
        Vector<String> colv=new Vector<String>(Arrays.asList(cols));
        tableModel.setDataVector(rows,colv);
        jTable1.setModel(tableModel);
        TableColumn colID=jTable1.getColumnModel().getColumn(0);
        TableColumn colSym=jTable1.getColumnModel().getColumn(1);
        //TableColumn colDescrip=jTable1.getColumnModel().getColumn(2);
        colID.setPreferredWidth(10);
        colID.setCellEditor(null);
        colSym.setPreferredWidth(10);
        colSym.setCellRenderer(tableRender);
        colSym.setCellEditor(null);
        jTable1.setRowHeight(font.getSize()+4);
    }
    BufferedImage imgTmp=new BufferedImage(100,100,BufferedImage.TYPE_3BYTE_BGR);
    void makeImage(int chid,String path){
        char ch=(char)chid;
        Graphics g=imgTmp.createGraphics();
        g.setFont(font);
        FontMetrics fm=g.getFontMetrics();
        int absWidth=fm.charWidth(ch);
        int absHeight=fm.getAscent()-fm.getLeading()-fm.getDescent();

        BufferedImage bimg=new BufferedImage(absWidth,absHeight,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d=bimg.createGraphics();
        g2d.setPaintMode();
        //Color bgColor=new Color(0,0,0,0);
        Color bgColor=Color.white;
        g2d.setColor(bgColor);
        g2d.fillRect(0,0,absWidth,absHeight);

        g2d.setColor(jcc.getColor());
        g2d.setFont(font);
        String s=String.valueOf((char)ch);
        g2d.drawString(s,0,absHeight);
        try {
            ImageIO.write(bimg, "PNG", new File(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    JPanel jPanel1 = new JPanel();
    FlowLayout flowLayout1 = new FlowLayout();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JButton jButton1 = new JButton();
    JLabel jLabel4 = new JLabel();
    JSpinner jSpinner1 = new JSpinner();
    JCheckBox jCheckBox1 = new JCheckBox();
    JCheckBox jCheckBox2 = new JCheckBox();
    JButton jButton2 = new JButton();
    JScrollPane jScrollPane1 = new JScrollPane();
    JButton jButton3 = new JButton();
    JTable jTable1 = new JTable();
    JFormattedTextField jFormattedTextField1 = new JFormattedTextField();
    JFormattedTextField jFormattedTextField2 = new JFormattedTextField();
    JPanel jPanel2 = new JPanel();
    Border border1 = BorderFactory.createEtchedBorder(EtchedBorder.RAISED,
            Color.white, new Color(148, 145, 140));
    BorderLayout borderLayout1 = new BorderLayout();
    JLabel jLabel1 = new JLabel();
    NavList jList1 = new NavList();
    JButton jButton4 = new JButton();
    JColorChooser jcc=new JColorChooser(Color.black);
    JFileChooserRecoll fcr=new JFileChooserRecoll(JFileChooserRecoll.DefaultConfigFileName);
    JPanel paneSouth=new JPanel();
    JLabel chLabel=new JLabel("欢迎,Hello!");
    class FontSymbolRender extends DefaultTableCellRenderer{
		private static final long serialVersionUID = 3026392742577093327L;

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
            Component comp=super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
            comp.setFont(font);
            setBorder(BorderFactory.createEtchedBorder());
            setHorizontalAlignment(SwingConstants.CENTER);
            setVerticalAlignment(SwingConstants.CENTER);
            if(isSelected)
                this.setForeground(Color.red);
            else
                this.setForeground(jcc.getColor());
            return comp;
        }
    }
}
