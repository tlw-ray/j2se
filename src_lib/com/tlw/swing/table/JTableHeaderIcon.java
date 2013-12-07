package com.tlw.swing.table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import com.tlw.swing.JLabelUpDown;
import com.tlw.swing.Shower;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-9-7
@version:2009-9-7
Description:
 */
public class JTableHeaderIcon extends JPanel {
	private static final long serialVersionUID = 1341392719886757046L;
	public static void main(String[] args) {
		JTableHeaderIcon pane=new JTableHeaderIcon();
		Shower.show(pane);
	}
	JLabelUpDown jlabelUpDown=new JLabelUpDown();
	int currentColumn=-1;
	int state=JLabelUpDown.STATE_NONE;
	public JTableHeaderIcon(){
		final JTable table=new JTable();
		table.getTableHeader().setDefaultRenderer(new TableCellRendererIconed());
		table.getTableHeader().addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				int columnIdx=table.columnAtPoint(e.getPoint());
				if(currentColumn==columnIdx){
					switch(state){
					case JLabelUpDown.STATE_NONE:state=JLabelUpDown.STATE_DOWN;break;
					case JLabelUpDown.STATE_DOWN:state=JLabelUpDown.STATE_UP;break;
					default:state=JLabelUpDown.STATE_NONE;break;
					}
				}else{
					currentColumn=columnIdx;
					state=JLabelUpDown.STATE_DOWN;
				}
			}
		});
		table.setModel(new DefaultTableModel(new String[][]{{"aaa","bbb"},{"ccc","ddd"}},new String[]{"a","b"}));
		JScrollPane scroll=new JScrollPane(table);
		setLayout(new BorderLayout());
		add(scroll,BorderLayout.CENTER);
	}
	public void paint(Graphics g){
		super.paint(g);
	}
	class TableCellRendererIconed implements TableCellRenderer{
		private static final long serialVersionUID = -548756226551389910L;
		ImageIcon icon;
		public TableCellRendererIconed(){
			jlabelUpDown.setOpaque(true);
			jlabelUpDown.setToolTipText("点击为列排序.");
			jlabelUpDown.setBorder(BorderFactory.createEtchedBorder());
			jlabelUpDown.setHorizontalAlignment(0);
	        
			BufferedImage img=new BufferedImage(8,8,BufferedImage.TYPE_4BYTE_ABGR);
			Graphics g=img.getGraphics();
			g.setColor(Color.red);
			g.fillRect(0, 0,7,7);
			icon=new ImageIcon(img);
		}
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			if(table != null)
	        {
	            JTableHeader jtableheader = table.getTableHeader();
	            if(jtableheader != null)
	            {
	            	jlabelUpDown.setForeground(jtableheader.getForeground());
	            	jlabelUpDown.setBackground(jtableheader.getBackground());
	            	jlabelUpDown.setFont(jtableheader.getFont());
	            }
	        }
			jlabelUpDown.setText(value != null ? value.toString() : "");
			jlabelUpDown.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
			if(column==currentColumn){
				jlabelUpDown.setState(state);
			}else{
				jlabelUpDown.setState(JLabelUpDown.STATE_NONE);
			}
	        return jlabelUpDown;
		}
	}
}