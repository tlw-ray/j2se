package com.tlw.tools.encode.shower;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
//一个用于分析乱码的程序
/*
作者:tlw_ray@163.com
2006
*/
public class SlashCodeFace extends JApplet{
	private static final long serialVersionUID = -7629670575851105037L;
	public SlashCodeFace() {
         try {
             jbInit();
             myInit();
         } catch (Exception ex) {
             ex.printStackTrace();
         }
     }
     public void start(){

     }
     public static void main(String[] args) {
         Enumeration<Object> keys = UIManager.getDefaults().keys();
         while (keys.hasMoreElements()) {
             Object key = keys.nextElement();
             Object value = UIManager.get(key);
             if (value instanceof javax.swing.plaf.FontUIResource)
                 UIManager.put(key, new Font("宋体", Font.PLAIN, 12));
         }
         JFrame frame = new JFrame();
         frame.add(new SlashCodeFace());
         frame.setTitle("乱码分析器：");
         frame.setSize(650,400);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setLocationRelativeTo(null);
         frame.setVisible(true);
     }

     private void jbInit() throws Exception {
         jPanel1.setBackground(Color.white);
         jPanel1.setBorder(BorderFactory.createLineBorder(Color.black));
         jPanel1.setPreferredSize(new Dimension(180, 223));
         jPanel1.setLayout(borderLayout3);
         jPanel2.setLayout(borderLayout1);
         jPanel3.setLayout(borderLayout2);
         jLabel1.setBackground(Color.orange);
         jLabel1.setOpaque(true);
         jLabel1.setPreferredSize(new Dimension(110, 16));
         jLabel1.setText("字符集供选择");
         jPanel4.setLayout(verticalFlowLayout1);
         jLabel2.setText("乱码贴入此处：");
         jButton1.setBackground(new Color(200, 255, 200));
         jButton1.setText("解码");
         this.getContentPane().setLayout(borderLayout4);
         jEditorPane1.setContentType("text/html");
         jRadioButton1.setActionCommand("所有JAVA字符集");
         jRadioButton1.setText("所有JAVA支持的编码");
         jRadioButton2.setActionCommand("常用中文字符集");
         jRadioButton2.setSelected(true);
         jRadioButton2.setText("中国常用");
         jRadioButton3.setText("台湾常用");
         jRadioButton4.setText("日本常用");
         jRadioButton5.setText("韩国常用");
         jButton2.setText("帮助");
         jButton3.setText("关于");
         jPanel3.setBackground(SystemColor.control);
         jPanel3.setBorder(BorderFactory.createEtchedBorder());
         jPanel3.add(jLabel2, java.awt.BorderLayout.WEST);
         jPanel3.add(jTextField1, java.awt.BorderLayout.CENTER);
         this.getContentPane().add(jPanel5, java.awt.BorderLayout.SOUTH);
         jPanel5.add(jRadioButton1);
         jPanel5.add(jRadioButton2);
         jPanel5.add(jRadioButton3);
         jPanel5.add(jRadioButton4);
         jPanel5.add(jRadioButton5);
         jPanel5.add(jButton2);
         jPanel5.add(jButton3);
         this.getContentPane().add(jPanel1, java.awt.BorderLayout.WEST);
         jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);
         jPanel1.add(jLabel1, java.awt.BorderLayout.NORTH);
         jScrollPane1.getViewport().add(jPanel4);
         jPanel3.add(jButton1, java.awt.BorderLayout.EAST);
         this.getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);
         jPanel2.add(jPanel3, java.awt.BorderLayout.NORTH);
         jPanel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);
         jScrollPane2.getViewport().add(jEditorPane1);
         buttonGroup1.add(jRadioButton1);
         buttonGroup1.add(jRadioButton2);
         buttonGroup1.add(jRadioButton3);
         buttonGroup1.add(jRadioButton4);
         buttonGroup1.add(jRadioButton5);
     }

     JPanel jPanel1 = new JPanel();
     JPanel jPanel2 = new JPanel();
     JPanel jPanel3 = new JPanel();
     BorderLayout borderLayout1 = new BorderLayout();
     JScrollPane jScrollPane1 = new JScrollPane();
     JPanel jPanel4 = new JPanel();
     BorderLayout borderLayout3 = new BorderLayout();
     JLabel jLabel1 = new JLabel();
     VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
     JTextField jTextField1 = new JTextField();
     JLabel jLabel2 = new JLabel();
     BorderLayout borderLayout2 = new BorderLayout();
     JButton jButton1 = new JButton();
     BorderLayout borderLayout4 = new BorderLayout();
     JPanel jPanel5 = new JPanel();
     JScrollPane jScrollPane2 = new JScrollPane();
     JEditorPane jEditorPane1 = new JEditorPane();
     JRadioButton jRadioButton1 = new JRadioButton();
     JRadioButton jRadioButton2 = new JRadioButton();
     JRadioButton jRadioButton3 = new JRadioButton();
     JRadioButton jRadioButton4 = new JRadioButton();
     JRadioButton jRadioButton5 = new JRadioButton();
     ButtonGroup buttonGroup1 = new ButtonGroup();
     JButton jButton2 = new JButton();
     JButton jButton3 = new JButton();
     /***核心方法***/
     ArrayList<JCheckBox> al=new ArrayList<JCheckBox>();
     public static String[] encoderChs={"GB2312","ISO8859_1","UTF-8","UTF-16BE","UTF-16LE"};
     public static String[] encoderTw={"GB2312","ISO8859_1"};
     public static String[] encoderJp={"UTF-8","UTF-16BE","UTF-16LE"};
     public static String[] encoderKor={};
     private void initDecoders(String[] encoder){
         al.clear();
         jPanel4.removeAll();
         for(int i=0;i<encoder.length;i++){
             JCheckBox ck = new JCheckBox(encoder[i]);
             ck.setSelected(true);
             al.add(ck);
             jPanel4.add(ck);
         }
         jPanel4.updateUI();
     }
     private void initAllDecoders(){
         al.clear();
         jPanel4.removeAll();
         Iterator<String>
                 it = java.nio.charset.Charset.availableCharsets().keySet().
                      iterator();
         while (it.hasNext()) {
             JCheckBox ck = new JCheckBox(it.next());
             al.add(ck);
             jPanel4.add(ck);
         }
         jPanel4.updateUI();
     }
     private void myInit(){
         this.jButton1.addActionListener(new ActionListener(){//解码；
             public void actionPerformed(ActionEvent e) {decod();}});
         this.jButton2.addActionListener(new ActionListener(){//帮助；
             public void actionPerformed(ActionEvent e) {showHelp();}});
         this.jButton3.addActionListener(new ActionListener(){//关于：
             public void actionPerformed(ActionEvent e) {showAbout();}});
         this.jRadioButton1.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){initAllDecoders();}});
         this.jRadioButton2.addActionListener(new ActionListener() {//中国
             public void actionPerformed(ActionEvent e) {initDecoders(encoderChs);}});
         this.jRadioButton3.addActionListener(new ActionListener() {//台湾
             public void actionPerformed(ActionEvent e) {initDecoders(encoderTw);}});
         this.jRadioButton4.addActionListener(new ActionListener() {//日本
             public void actionPerformed(ActionEvent e) {initDecoders(encoderJp);}});
         this.jRadioButton5.addActionListener(new ActionListener() {//韩国
             public void actionPerformed(ActionEvent e) {initDecoders(encoderKor);}});
         initDecoders(encoderChs);
         jRadioButton3.setEnabled(false);
         jRadioButton4.setEnabled(false);
         jRadioButton5.setEnabled(false);
       }
     ArrayList<String> al2=new ArrayList<String>();
     public void decod(){
         al2.clear();
         String output="";
         String str=this.jTextField1.getText();
         for(int i=0;i<al.size();i++){
             JCheckBox ck=al.get(i);
             if(ck.isSelected()){
                 al2.add(ck.getText());
             }
         }
         if(al2.size()==0){
             jEditorPane1.setText("请先在左侧选中所有可能的编码方式！");
             return;
         }
         try {
             output+="<table width=100% border=1>";
              for (int i = 0; i < al2.size(); i++) {
                  for (int j = 0; j < al2.size(); j++) {
                	  String context=new String(str.getBytes(al2.get(i)),al2.get(j));
                      output+="<tr>";
                      output += "<td>以<font color=green>" + al2.get(i) + "</font> 解码</td><td>再以 <font color=green>" + al2.get(j) +"</font>编码</td><td>得到:<font color=red>"+
                              context+"</font></td><td>"+URLEncoder.encode(context,al2.get(j))+"</td>";
                      output+="</tr>";
                  }
                  output+="<hr>";
              }
              output+="</table>";
          } catch (UnsupportedEncodingException ex) {
              ex.printStackTrace();
          }
         jEditorPane1.setText(output);
     }
     private void showHelp(){JOptionPane.showMessageDialog(this,new JTextArea("经常在读取数据库或者JSP的时候出现乱码，可能很难猜测乱码是什么编码格式的。\n把乱码贴到这个程序中点解码，程序会用所有选中的字符集对乱码解码，再重新编码。\n最终得到一些内容，并显示解开乱码的方案。\n\n例如：偶用第三方组件读数据库输出中文部分全是乱码。\n其中有乱码“\u00C1\u00BC\u00CF\u00E7”想知道如何解开它，\n把它贴到程序中点‘解码’发现用ISO8859_1解码再用GBK编码得到了正常的汉字。\n那么对应JAVA可以通过以下代码对这段乱码解码\nString str=new String((new String(\"\u00C1\u00BC\u00CF\u00E7\")).getBytes(\"ISO8859_1\"),\"GBK\")\nstr就是乱码的内容\n乱码是由于系统把GBK编码的内容用ISO8859_1解开造成的这是最常见的一种情况。\n由于中文WINDOWS默认是GBK的。当然也有许多其他的情况，对付这类问题此软件也许能帮上忙。"));}
     private void showAbout(){JOptionPane.showMessageDialog(this,new JTextArea("关于作者\n网名:tlw_ray\n生于:1982\n来自:山东淄博\n职业:软件开发\nQQ:22348536\nE-MAIL:TLW_RAY@163.COM\n如果你在软件方面有好的想法和看法欢迎与我联系。"));}
}
/*注意：VerticalFlowLayout属于BORLAND的JBCL类库，本程序为方便起见直接引用了代码*/
class VerticalFlowLayout extends FlowLayout implements java.io.Serializable {
	private static final long serialVersionUID = 8820030116485958153L;
public static final int TOP    = 0;
   public static final int MIDDLE         = 1;
   public static final int BOTTOM         = 2;

   //int align;
   int hgap;
   int vgap;
   boolean hfill;
   boolean vfill;

   /**
    * Construct a new VerticalFlowLayout with a middle alignemnt, and
    * the fill to edge flag set.
    */
   public VerticalFlowLayout() {
     this(TOP, 5, 5, true, false);
   }

   /**
    * Construct a new VerticalFlowLayout with a middle alignemnt.
    * @param fill the fill to edge flag
    */
   public VerticalFlowLayout(boolean hfill, boolean vfill){
     this(TOP, 5, 5, hfill, vfill);
   }

   /**
    * Construct a new VerticalFlowLayout with a middle alignemnt.
    * @param align the alignment value
    */
   public VerticalFlowLayout(int align) {
     this(align, 5, 5, true, false);
   }

   /**
    * Construct a new VerticalFlowLayout.
    * @param align the alignment value
    * @param fill the fill to edge flag
    */
   public VerticalFlowLayout(int align, boolean hfill, boolean vfill) {
     this(align, 5, 5, hfill, vfill);
   }

   /**
    * Construct a new VerticalFlowLayout.
    * @param align the alignment value
    * @param hgap the horizontal gap variable
    * @param vgap the vertical gap variable
    * @param fill the fill to edge flag
    */
   public VerticalFlowLayout(int align, int hgap, int vgap, boolean hfill, boolean vfill) {
     setAlignment(align);
     this.hgap = hgap;
     this.vgap = vgap;
     this.hfill = hfill;
     this.vfill = vfill;
   }

   /**
      * Gets the horizontal gap between components.
      * @return      the horizontal gap between components.
      * @see         java.awt.FlowLayout#setHgap
      * @since       JDK1.1
      */
   public int getHgap() {
     return hgap;
   }

   /**
    * Sets the horizontal gap between components.
    */
   public void setHgap(int hgap) {
     super.setHgap(hgap);
     this.hgap = hgap;
   }

   /**
    * Gets the vertical gap between components.
    */
   public int getVgap() {
     return vgap;
   }

   /**
    * Sets the vertical gap between components.
    */
   public void setVgap(int vgap) {
     super.setVgap(vgap);
     this.vgap = vgap;
   }

   /**
    * Returns the preferred dimensions given the components
    * in the target container.
    * @param target the component to lay out
    */
   public Dimension preferredLayoutSize(Container target) {
     Dimension tarsiz = new Dimension(0, 0);

     for (int i = 0 ; i < target.getComponentCount(); i++) {
       Component m = target.getComponent(i);
       if (m.isVisible()) {
         Dimension d = m.getPreferredSize();
         tarsiz.width = Math.max(tarsiz.width, d.width);
         if (i > 0) {
           tarsiz.height += vgap;
         }
         tarsiz.height += d.height;
       }
     }
     Insets insets = target.getInsets();
     tarsiz.width += insets.left + insets.right + hgap*2;
     tarsiz.height += insets.top + insets.bottom + vgap*2;
     return tarsiz;
   }

   /**
    * Returns the minimum size needed to layout the target container
    * @param target the component to lay out
    */
   public Dimension minimumLayoutSize(Container target) {
     Dimension tarsiz = new Dimension(0, 0);

     for (int i = 0 ; i < target.getComponentCount(); i++) {
       Component m = target.getComponent(i);
       if (m.isVisible()) {
           Dimension d = m.getMinimumSize();
           tarsiz.width = Math.max(tarsiz.width, d.width);
           if (i > 0) {
             tarsiz.height += vgap;
           }
           tarsiz.height += d.height;
       }
     }
     Insets insets = target.getInsets();
     tarsiz.width += insets.left + insets.right + hgap*2;
     tarsiz.height += insets.top + insets.bottom + vgap*2;
     return tarsiz;
   }

   public void setVerticalFill(boolean vfill) {
     this.vfill = vfill;
   }

   public boolean getVerticalFill() {
     return vfill;
   }

   public void setHorizontalFill(boolean hfill) {
     this.hfill = hfill;
   }

   public boolean getHorizontalFill() {
     return hfill;
   }

   /**
          * places the components defined by first to last within the target
          * container using the bounds box defined
    * @param target the container
    * @param x the x coordinate of the area
    * @param y the y coordinate of the area
    * @param width the width of the area
    * @param height the height of the area
    * @param first the first component of the container to pl
    * ace
    * @param last the last component of the container to place
    */
   private void placethem(Container target, int x, int y, int width, int height,
                                        int first, int last) {
     int align = getAlignment();
     if ( align == MIDDLE )
       y += height   / 2;
     if ( align == BOTTOM )
       y += height;

     for (int i = first ; i < last ; i++) {
       Component m = target.getComponent(i);
         Dimension md = m.getSize();
       if (m.isVisible()) {
         int px = x + (width-md.width)/2;
         m.setLocation(px, y);
         y += vgap + md.height;
       }
     }
   }

   /**
    * Lays out the container.
    * @param target the container to lay out.
    */
   public void layoutContainer(Container target) {
     Insets insets = target.getInsets();
     int maxheight = target.getSize().height - (insets.top + insets.bottom + vgap*2);
     int maxwidth = target.getSize().width - (insets.left + insets.right + hgap*2);
     int numcomp = target.getComponentCount();
     int x = insets.left + hgap;
     int y = 0   ;
     int colw = 0, start = 0;

     for (int i = 0 ; i < numcomp ; i++) {
       Component m = target.getComponent(i);
       if (m.isVisible()) {
         Dimension d = m.getPreferredSize();
         // fit last component to remaining height
         if ((this.vfill) && (i == (numcomp-1))) {
           d.height = Math.max((maxheight - y), m.getPreferredSize().height);
         }

         // fit componenent size to container width
         if ( this.hfill ) {
           m.setSize(maxwidth, d.height);
           d.width = maxwidth;
         }
         else {
           m.setSize(d.width, d.height);
         }

         if ( y   + d.height > maxheight ) {
           placethem(target, x, insets.top + vgap, colw, maxheight-y, start, i);
           y = d.height;
           x += hgap + colw;
           colw = d.width;
           start = i;
         }
         else {
           if ( y > 0 )
             y += vgap;
            y += d.height;
            colw = Math.max(colw, d.width);
         }
       }
     }
     placethem(target, x, insets.top + vgap, colw, maxheight - y, start, numcomp);
   }
}

