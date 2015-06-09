package com.tlw.eg.awt.event;

import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;

import javax.swing.JPanel;

import com.tlw.util.UtilUi;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年5月30日
 */
public class HierarchyBounds {

	public static void main(String[] args) {
		JPanel jpanel=new JPanel();
		jpanel.addHierarchyBoundsListener(new HierarchyBoundsListener(){
			@Override
			public void ancestorMoved(HierarchyEvent e) {
//				System.out.println(e);
			}
			@Override
			public void ancestorResized(HierarchyEvent e) {
				System.out.println(e);
			}
		});
		UtilUi.show(jpanel, 600, 400, "");
	}

}
