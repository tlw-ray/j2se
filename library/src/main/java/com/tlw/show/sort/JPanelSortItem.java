package com.tlw.show.sort;

/*
 * @(#)SortItem.java	1.20 06/02/22
 * 
 * Copyright (c) 2006 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * -Redistribution of source code must retain the above copyright notice, this
 *  list of conditions and the following disclaimer.
 * 
 * -Redistribution in binary form must reproduce the above copyright notice, 
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 * 
 * Neither the name of Sun Microsystems, Inc. or the names of contributors may 
 * be used to endorse or promote products derived from this software without 
 * specific prior written permission.
 * 
 * This software is provided "AS IS," without a warranty of any kind. ALL 
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING
 * ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN MICROSYSTEMS, INC. ("SUN")
 * AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE
 * AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR ANY LOST 
 * REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, 
 * INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY 
 * OF LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE THIS SOFTWARE, 
 * EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * You acknowledge that this software is not designed, licensed or intended
 * for use in the design, construction, operation or maintenance of any
 * nuclear facility.
 */

/*
 * @(#)SortItem.java	1.20 06/02/22
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * A simple applet class to demonstrate a sort algorithm. You can specify a
 * sorting algorithm using the "alg" attribyte. When you click on the applet, a
 * thread is forked which animates the sorting algorithm.
 * 
 * @author James Gosling
 * @version 1.20, 02/22/06
 */
public class JPanelSortItem extends JPanel implements Runnable{
	private static final long serialVersionUID = 837464433917949773L;

	/**
	 * The thread that is sorting (or null).
	 */
	Thread kicker;

	/**
	 * The array that is being sorted.
	 */
	private int arr[];

	/**
	 * The high water mark.
	 */
	int h1 = -1;

	/**
	 * The low water mark.
	 */
	int h2 = -1;

	/**
	 * The name of the algorithm.
	 */
	String algName;

	/**
	 * The sorting algorithm (or null).
	 */
	AlgorithmSort algorithm;
	public JPanelSortItem(String algorithmClassName){
		setBorder(BorderFactory.createBevelBorder(1));
		try {
			algorithm=(AlgorithmSort)Class.forName(algorithmClassName).newInstance();
			algorithm.setParent(this);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Fill the array with random numbers from 0..n-1.
	 */
	int offset=40;
	/**
	 * Pause a while.
	 * 
	 * @see AlgorithmSort
	 */
	void pause() {
		pause(-1, -1);
	}
	/**
	 * Pause a while, and draw the high water mark.
	 * 
	 * @see AlgorithmSort
	 */
	void pause(int H1) {
		pause(H1, -1);
	}
	/**
	 * Pause a while, and draw the low&high water marks.
	 * 
	 * @see AlgorithmSort
	 */
	void pause(int H1, int H2) {
		h1 = H1;
		h2 = H2;
		if (kicker != null) {
			repaint();
		}
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}
	/**
	 * Paint the array of numbers as a list of horizontal lines of varying
	 * lengths.
	 */
	public void paint(Graphics g) {
		super.paint(g);
		if(arr!=null){
			int a[] = arr;
			int y = 0;
			int deltaY = 0, deltaX = 0, evenY = 0;//, evenX = 0;
	
			Dimension currentSize = getSize();
			int currentHeight = currentSize.height-offset;
			int currentWidth = currentSize.width;

			// Erase old lines
			g.setColor(getBackground());
			y = currentHeight - deltaY - 1;
			for (int i = a.length; --i >= 0; y -= 2) {
				g.drawLine(deltaX + arr[i], y, currentWidth, y);
			}
			// Draw new lines
			g.setColor(Color.black);
			y = currentHeight - deltaY - 1;
			for (int i = a.length; --i >= 0; y -= 2) {
				g.drawLine(deltaX, y, deltaX + arr[i], y);
			}
			if (h1 >= 0) {
				g.setColor(Color.red);
				y = deltaY + evenY + h1 * 2 + 1;
				g.drawLine(deltaX, y, deltaX + (int)getSize().getHeight(), y);
			}
			if (h2 >= 0) {
				g.setColor(Color.blue);
				y = deltaY + evenY + h2 * 2 + 1;
				g.drawLine(deltaX, y, deltaX + (int)getSize().getWidth(), y);
			}
		}
		String algorithmName=algorithm.getClass().getName();
		int fontHeight=10;
		g.drawString(algorithmName.substring(23), 30, getHeight()-offset+fontHeight);
		g.drawString("SwapCount:"+algorithm.getSwapCount(), 30, getHeight()-offset*2/3+fontHeight);
		g.drawString("CalcCount:"+algorithm.getCalcCount(), 30, getHeight()-offset/3+fontHeight);
	}
	/**
	 * Update without erasing the background.
	 */
	public void update(Graphics g) {
		paint(g);
	}
	/**
	 * Run the sorting algorithm. This method is called by class Thread once the
	 * sorting algorithm is started.
	 * 
	 * @see java.lang.Thread#run
	 * @see JPanelSortItem#mouseUp
	 */
	public void run() {
		try {
			algorithm.init();
			algorithm.sort(arr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * For a Thread to actually do the sorting. This routine makes sure we do
	 * not simultaneously start several sorts if the user repeatedly clicks on
	 * the sort item. It needs to be synchronized with the stop() method because
	 * they both manipulate the common kicker variable.
	 */
	public synchronized void startSort() {
		if (kicker == null || !kicker.isAlive()) {
			kicker = new Thread(this);
			kicker.start();
		}
	}
	/*---属性---*/
	public Thread getKicker() {
		return kicker;
	}
	public void setKicker(Thread kicker) {
		this.kicker = kicker;
	}
	public int getHighWaterMark() {
		return h1;
	}
	public void setHighWaterMark(int h1) {
		this.h1 = h1;
	}
	public int getLowWaterMark() {
		return h2;
	}
	public void setLowWaterMark(int h2) {
		this.h2 = h2;
	}
	public int[] getData() {
		return arr;
	}
	public void setData(int[] data) {
		algorithm.resetCalcCount();
		algorithm.resetSwapCount();
		arr = data;
		setHighWaterMark(-1);
		setLowWaterMark(-1);
	}
}