package com.tlw.eg.swing.dialog;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import com.tlw.util.UtilUi;


/**
@Author liwei.tang@magustek.com
@Since 2013-4-10

1.自定义JOptionPane的按钮
2.覆盖自定义按钮的快捷键事件
 */
public class JOptionPaneStopEnter extends JPanel{
	
	private static final long serialVersionUID = -8100553678313741599L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JOptionPaneStopEnter pane=new JOptionPaneStopEnter();
		UtilUi.showPack(pane, "");
	}
	
	JButton jbutton=new JButton("Hello");
	
	public JOptionPaneStopEnter(){
		add(jbutton);
		jbutton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane joptionPane=new JOptionPane(new JTextField(), JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION, null, new Object[]{"1","2"},null);
				final JDialog dlg=joptionPane.createDialog(JOptionPaneStopEnter.this, "");
				
				List<Component> jbuttons=UtilUi.getComponent(dlg, JButton.class);
				for(Component comp:jbuttons){
					JButton jbutton=(JButton)comp;
					if(jbutton.getText().equalsIgnoreCase("1")){
						jbutton.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "close");
						jbutton.getActionMap().put("close", new AbstractAction(){
							private static final long serialVersionUID = 203471154934460678L;
							@Override
							public void actionPerformed(ActionEvent e) {
								dlg.setVisible(false);
							}
						});
					}
				}
				
				dlg.setVisible(true);
			}
		});
	}
}
