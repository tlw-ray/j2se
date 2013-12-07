package com.tlw.tool.run;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.tlw.swing.JFileChooserRecoll;



/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
//通过反射，得到某个jar包中的所有可运行类，供选定运行。主要用来查看国外项目的范例包
//先看看网上有没有现成的
public class ExampleRunner extends javax.swing.JFrame {
	private static final long serialVersionUID = 8070360373429000840L;
	private JSplitPane jSplitPane1;
    private JButton btnAdd;
    private JPanel paneNorth;
    private JScrollPane jScrollPane2;
    private JList jList2;
    private JButton btnRun;
    private JList jList1;
    private JScrollPane jScrollPane1;
    private JPanel paneRight;
    private JPanel paneLeft;

    /**
    * Auto-generated main method to display this JFrame
    */
    public static void main(String[] args) {
        ExampleRunner inst = new ExampleRunner();
        inst.setVisible(true);
    }
    
    public ExampleRunner() {
        super();
        initGUI();
    }
    
    private void initGUI() {
        try {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            {
                jSplitPane1 = new JSplitPane();
                getContentPane().add(jSplitPane1, BorderLayout.CENTER);
                {
                    paneLeft = new JPanel();
                    BorderLayout paneLeftLayout = new BorderLayout();
                    paneLeft.setLayout(paneLeftLayout);
                    jSplitPane1.add(paneLeft, JSplitPane.RIGHT);
                    {
                        paneNorth = new JPanel();
                        paneLeft.add(paneNorth, BorderLayout.NORTH);
                        {
                            btnRun = new JButton();
                            paneNorth.add(btnRun);
                            btnRun.setText("Run Selected Main Function");
                        }
                        {
                            btnSave = new JButton();
                            paneNorth.add(btnSave);
                            btnSave.setText("Save Config!");
                        }
                    }
                    {
                        jScrollPane2 = new JScrollPane();
                        paneLeft.add(jScrollPane2, BorderLayout.CENTER);
                        {
                            jList2 = new JList();
                            jScrollPane2.setViewportView(jList2);
                        }
                    }
                    jSplitPane1.setDividerLocation(160);
                }
                {
                    paneRight = new JPanel();
                    BorderLayout paneRightLayout = new BorderLayout();
                    paneRight.setLayout(paneRightLayout);
                    jSplitPane1.add(paneRight, JSplitPane.LEFT);
                    {
                        btnAdd = new JButton();
                        paneRight.add(btnAdd, BorderLayout.NORTH);
                        btnAdd.setText("Add Jar");
                    }
                    {
                        jScrollPane1 = new JScrollPane();
                        paneRight.add(jScrollPane1, BorderLayout.CENTER);
                        {
                            jList1 = new JList();
                            jScrollPane1.setViewportView(jList1);
                        }
                    }
                }
            }
            pack();
            this.setSize(512, 300);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    JFileChooser jfc=new JFileChooserRecoll();
    private JButton btnSave;
    public void myInit(){
        /*
         jList1 : 显示添加进来的Jar包
         jList2 :　显示jList1中选中包中包含可运行main函数的类名
         btnAdd:调用文件选择器，添加jar包到jList1;
         btnRun:运行jList2中选中的类
         * */
        btnAdd.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
               //添加jar包
                
            }
        });
        btnRun.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
               //运行被选中的包含main函数的类
            }
        });
        btnRun.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
               //运行被选中的包含main函数的类
            }
        });
        jList1.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e) {
                //选中的jar包变化时，刷新jList2中显示当前选中jar包中包含main函数的类
                
            }
        });
        jList2.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                //双击运行选中的类
                if(e.getButton()==MouseEvent.BUTTON1 && e.getClickCount()==2){
                    
                }
            }
        });
    }
    public void runClass(String className){
        //以jList1中的jar作为classpath运行jList2中选中的class
        
    }
    public void saveCfg(){
        //保存当前配置到文件,以便下次可以打开
        
    }
    
}
