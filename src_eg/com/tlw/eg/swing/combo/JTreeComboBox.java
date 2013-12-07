package com.tlw.eg.swing.combo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.MenuSelectionManager;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.plaf.metal.MetalComboBoxUI;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;


import com.sun.java.swing.plaf.motif.MotifComboBoxUI;
import com.sun.java.swing.plaf.windows.WindowsComboBoxUI;
import com.tlw.util.UtilUi;

/**
 * <p>Title: OpenSwing</p>
 * <p>Description: 树形下拉列表框</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author  <a href="mailto:rockis@msn.com">zhanglei</a>
 *  && <a href="mailto:sunkingxie@hotmail.com">SunKing</a>
 * @version 1.0
 */
public class JTreeComboBox extends JComboBox{
	private static final long serialVersionUID = -2810331258629084776L;
	/**
     * 显示用的树
     */
    private JTree tree;

    public JTreeComboBox(){
        this(new JTree());
    }

    public JTreeComboBox(JTree tree){
        this.setTree(tree);
    }

    /**
     * 设置树
     * @param tree JTree
     */
    public void setTree(JTree tree){
        this.tree = tree;
        if(tree != null){
            this.setSelectedItem(tree.getSelectionPath());
            this.setRenderer(new JTreeComboBoxRenderer());
        }
        this.updateUI();
    }

    /**
     * 取得树
     * @return JTree
     */
    public JTree getTree(){
        return tree;
    }

    /**
     * 设置当前选择的树路径
     * @param o Object
     */
    public void setSelectedItem(Object o){
        tree.setSelectionPath((TreePath)o);
        getModel().setSelectedItem(o);
    }

    public void updateUI(){
        ComboBoxUI cui = (ComboBoxUI)UIManager.getUI(this);
        if(cui instanceof MetalComboBoxUI){
            cui = new MetalJTreeComboBoxUI();
        } else if(cui instanceof MotifComboBoxUI){
            cui = new MotifJTreeComboBoxUI();
        } else {
            cui = new WindowsJTreeComboBoxUI();
        }
        setUI(cui);
    }

    // UI Inner classes -- one for each supported Look and Feel
    class MetalJTreeComboBoxUI extends MetalComboBoxUI{
        protected ComboPopup createPopup(){
            return new TreePopup(comboBox);
        }
    }

    class WindowsJTreeComboBoxUI extends WindowsComboBoxUI{
        protected ComboPopup createPopup(){
            return new TreePopup(comboBox);
        }
    }

    class MotifJTreeComboBoxUI extends MotifComboBoxUI{
		private static final long serialVersionUID = -4228611346552057992L;

		protected ComboPopup createPopup(){
            return new TreePopup(comboBox);
        }
    }
    /**
     * <p>Title: OpenSwing</p>
     * <p>Description: 树形结构而来的DefaultListCellRenderer </p>
     * <p>Copyright: Copyright (c) 2004</p>
     * <p>Company: </p>
     * @author <a href="mailto:sunkingxie@hotmail.com">SunKing</a>
     * @version 1.0
     */
    class JTreeComboBoxRenderer extends DefaultListCellRenderer{
		private static final long serialVersionUID = 3021479676933087592L;

		public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus){
            if(value != null){
                TreePath path = (TreePath)value;
                TreeNode node = (TreeNode)path.getLastPathComponent();
                value = node;
                TreeCellRenderer r = tree.getCellRenderer();
                JLabel lb = (JLabel)r.getTreeCellRendererComponent(
                    tree, value, isSelected, false, node.isLeaf(), index,
                    cellHasFocus);
                return lb;
            }
            return super.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);
        }
    }

    /**
     * 测试
     */
    public static void main(String args[]){
        final JTreeComboBox box = new JTreeComboBox(new JTree());
        box.setPreferredSize(new Dimension(300, 21));
        final JButton btt = new JButton("Set As JFileTree");
        btt.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                box.setTree(new JTree());
                btt.setEnabled(false);
            }
        });
        UtilUi.show(box, 400, 300, "");
    }
}

/**
 * <p>Title: JTreeComboBox</p>
 * <p>Description: TreePopup</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author  <a href="mailto:rockis@msn.com">zhanglei</a>
 *  && <a href="mailto:sunkingxie@hotmail.com">SunKing</a>
 * @version 1.0
 */
class TreePopup extends JPopupMenu implements ComboPopup{
	private static final long serialVersionUID = 746499530009362446L;
	protected JTreeComboBox comboBox;
    protected JScrollPane scrollPane;

    protected MouseMotionListener mouseMotionListener;
    protected MouseListener mouseListener;
    private MouseListener treeSelectListener = new MouseAdapter(){
        public void mouseReleased(MouseEvent e){
            JTree tree = (JTree)e.getSource();
            TreePath tp = tree.getPathForLocation(e.getPoint().x,
                                                  e.getPoint().y);
            if(tp == null){
                return;
            }
            comboBox.setSelectedItem(tp);
            togglePopup();
            MenuSelectionManager.defaultManager().clearSelectedPath();
        }
    };

    public TreePopup(JComboBox comboBox){
        this.comboBox = (JTreeComboBox)comboBox;
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(new BorderLayout());
        setLightWeightPopupEnabled(comboBox.isLightWeightPopupEnabled());
        JTree tree = this.comboBox.getTree();
        if(tree != null){
            scrollPane = new JScrollPane(tree);
            scrollPane.setBorder(null);
            add(scrollPane, BorderLayout.CENTER);
            tree.addMouseListener(treeSelectListener);
        }
    }

    public void show(){
        updatePopup();
        show(comboBox, 0, comboBox.getHeight());
        comboBox.getTree().requestFocus();
    }

    public void hide(){
        setVisible(false);
        comboBox.firePropertyChange("popupVisible", true, false);
    }

    protected JList list = new JList();
    public JList getList(){
        return list;
    }

    public MouseMotionListener getMouseMotionListener(){
        if(mouseMotionListener == null){
            mouseMotionListener = new MouseMotionAdapter(){};
        }
        return mouseMotionListener;
    }

    public KeyListener getKeyListener(){
        return null;
    }

    public void uninstallingUI(){}

    /**
     * Implementation of ComboPopup.getMouseListener().
     *
     * @return a <code>MouseListener</code> or null
     * @see ComboPopup#getMouseListener
     */
    public MouseListener getMouseListener(){
        if(mouseListener == null){
            mouseListener = new InvocationMouseHandler();
        }
        return mouseListener;
    }

    protected void togglePopup(){
        if(isVisible()){
            hide();
        } else{
            show();
        }
    }

    protected void updatePopup(){
        setPreferredSize(new Dimension(comboBox.getSize().width, 200));
        Object selectedObj = comboBox.getSelectedItem();
        if(selectedObj != null){
            TreePath tp = (TreePath)selectedObj;
            ((JTreeComboBox)comboBox).getTree().setSelectionPath(tp);
        }
    }

    protected class InvocationMouseHandler extends MouseAdapter{
        public void mousePressed(MouseEvent e){
            if(!SwingUtilities.isLeftMouseButton(e) || !comboBox.isEnabled()){
                return;
            }
            if(comboBox.isEditable()){
                Component comp = comboBox.getEditor().getEditorComponent();
                if((!(comp instanceof JComponent)) ||
                   ((JComponent)comp).isRequestFocusEnabled()){
                    comp.requestFocus();
                }
            } else if(comboBox.isRequestFocusEnabled()){
                comboBox.requestFocus();
            }
            togglePopup();
        }
    }
}
