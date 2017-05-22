package com.tlw.eg.swing.work;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import com.tlw.util.UtilUi;

/**
 * @author liwei.tang@magustek.com
 * @since 2016年6月30日
 */
public class StepWorker extends SwingWorker<Void, Integer> {

	@Override
	protected Void doInBackground() throws Exception {
		System.out.println("doInBackground...");
		for(int i=0;i<100;i++){
			setProgress(i);
			Thread.sleep(100);
		}
		return null;
	}
	
	static StepWorker worker;
	
	public static void main(String[] args){
		final JProgressBar progressBar = new JProgressBar(0, 100);
		final JButton doButton = new JButton("GO");
		final JButton cancelButton = new JButton("Cancel");
		JPanel pane = new JPanel();
		pane.add(progressBar);
		pane.add(doButton);
		pane.add(cancelButton);
		
		doButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(worker == null){
					worker = new StepWorker();
					worker.addPropertyChangeListener(new PropertyChangeListener(){
						@Override
						public void propertyChange(PropertyChangeEvent e) {
							if("progress".equals(e.getPropertyName())){
								progressBar.setValue((Integer)e.getNewValue());
							}else if("state".equals(e.getPropertyName())){
								if(e.getNewValue() == SwingWorker.StateValue.DONE){
									worker = null;
								}
							}
						}
					});
					worker.execute();
				}
			}
		});
		
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(worker != null){
					worker.cancel(true);
					worker = null;
				}
			}
		});
		
		UtilUi.show(pane, 400, 300, "");
	}

}
