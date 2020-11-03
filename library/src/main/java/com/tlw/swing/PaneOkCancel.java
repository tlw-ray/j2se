// Created on 2008-7-9
package com.tlw.swing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PaneOkCancel extends JPanel {
    private static final long serialVersionUID = -4247749495097334247L;
    public static void main(String[] args) {
        PaneOkCancel pane=new PaneOkCancel();
        Shower.show(pane,200,60);
    }
    public PaneOkCancel(){
    	initComponents();
    }
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Open Source Project license - unknown
		jbtnOk = new JButton();
		jbtnCancel = new JButton();

		//======== this ========
		setLayout(new GridBagLayout());
		((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0, 0, 0};
		((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0};
		((GridBagLayout)getLayout()).columnWeights = new double[] {1.0, 1.0, 1.0, 1.0E-4};
		((GridBagLayout)getLayout()).rowWeights = new double[] {1.0, 1.0E-4};

		//---- jbtnOk ----
		jbtnOk.setText("OK");
		add(jbtnOk, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
			GridBagConstraints.EAST, GridBagConstraints.NONE,
			new Insets(0, 0, 0, 5), 0, 0));

		//---- jbtnCancel ----
		jbtnCancel.setText("Cancel");
		add(jbtnCancel, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
			GridBagConstraints.WEST, GridBagConstraints.NONE,
			new Insets(0, 0, 0, 0), 0, 0));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Open Source Project license - unknown
	private JButton jbtnOk;
	private JButton jbtnCancel;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
    public Action getActionOk(){return jbtnOk.getAction();}
    public void setActionOk(Action action){jbtnOk.setAction(action);}
    public Action getActionCancel(){return jbtnCancel.getAction();}
    public void setActionCancel(Action action){jbtnCancel.setAction(action);}
}
