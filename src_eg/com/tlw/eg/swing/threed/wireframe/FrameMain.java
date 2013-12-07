package com.tlw.eg.swing.threed.wireframe;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

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
public class FrameMain extends javax.swing.JFrame {
	private static final long serialVersionUID = 8093928453224609432L;
	private JPanel paneNorth;
    private ThreeD threeD1;
    private JButton jbtnOpen;
    private JTextField jtfPath;
    private JLabel jLabel1;

    /**
    * Auto-generated main method to display this JFrame
     * @throws UnsupportedLookAndFeelException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws ClassNotFoundException 
    */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
    	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        FrameMain inst = new FrameMain();
        inst.setVisible(true);
    }
    
    public FrameMain() {
        super();
        initGUI();
        initEvent();
    }
    JFileChooser jfc=new JFileChooserRecoll();
    public void initEvent(){
    	//TODO 1.4 gcc 均不支持
//        FileNameExtensionFilter filter=new FileNameExtensionFilter("3D Model(*.obj,*.3ds,*.sh3d)","obj","3ds","sh3d");
//        jfc.setFileFilter(filter);
        jbtnOpen.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int sign=jfc.showOpenDialog(FrameMain.this);
                if(sign==JFileChooser.APPROVE_OPTION){
                    String modelFile=jfc.getSelectedFile().getPath();
                    jtfPath.setText(modelFile);
                    threeD1.mdname=modelFile;
                    threeD1.run();
                }
            }
        });
    }
    private void initGUI() {
        try {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            {//paneNorth
                paneNorth = new JPanel();
                BorderLayout paneNorthLayout = new BorderLayout();
                getContentPane().add(paneNorth, BorderLayout.NORTH);
                paneNorth.setLayout(paneNorthLayout);
                
                jLabel1 = new JLabel();
                paneNorth.add(jLabel1, BorderLayout.CENTER);
                jLabel1.setText("FilePath:");
                paneNorth.add(jLabel1,BorderLayout.WEST);
                
                jtfPath = new JTextField();
                paneNorth.add(jtfPath, BorderLayout.CENTER);
                
                jbtnOpen = new JButton();
                paneNorth.add(jbtnOpen, BorderLayout.EAST);
                jbtnOpen.setText("Open");
            }
            {//paneCenter
                threeD1 = new ThreeD();
                getContentPane().add(threeD1, BorderLayout.CENTER);
            }
            pack();
            setSize(400, 300);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
