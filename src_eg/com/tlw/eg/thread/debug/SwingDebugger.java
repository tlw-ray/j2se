package com.tlw.eg.thread.debug;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年8月27日
 */
public class SwingDebugger {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		final Debugger debugger=new Debugger();
		
		JFrame frame=new JFrame();
		frame.setLayout(new FlowLayout());
		
		final JButton runButton=new JButton("RUN");
		runButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				debugger.fire(DebugTrigger.DEBUG);
			}
		});
		
		final JButton stopButton=new JButton("STOP");
		stopButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				debugger.fire(DebugTrigger.STOP);
			}
		});
		
		final JButton pauseButton=new JButton("PAUSE");
		pauseButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				debugger.fire(DebugTrigger.PAUSE);
			}
		});
		
		final JButton nextButton=new JButton("NEXT");
		nextButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				debugger.fire(DebugTrigger.NEXT);
			}
		});
		
		final JLabel stateLabel=new JLabel();
		
		debugger.addStateChangeListener(new DebugStateChangedListener(){
			@Override
			public void stateChanged(DebugStateEvent stateEvent) {
				refreshUI(debugger, runButton, stopButton, pauseButton, nextButton, stateLabel);
			}
		});
		
		frame.add(runButton);
		frame.add(stopButton);
		frame.add(pauseButton);
		frame.add(nextButton);
		frame.add(stateLabel);
		refreshUI(debugger, runButton, stopButton, pauseButton, nextButton, stateLabel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setVisible(true);
	}
	private static void refreshUI(Debugger debugger, JButton runButton, JButton stopButton, JButton pauseButton, JButton nextButton, JLabel stateLabel) {
		//refresh GUI state
		runButton.setEnabled(debugger.canFire(DebugTrigger.DEBUG));
		stopButton.setEnabled(debugger.canFire(DebugTrigger.STOP));
		pauseButton.setEnabled(debugger.canFire(DebugTrigger.PAUSE));
		nextButton.setEnabled(debugger.canFire(DebugTrigger.NEXT));
		System.out.println(debugger.getDebugState().toString());
		stateLabel.setText("状态:"+debugger.getDebugState());
	}

}
