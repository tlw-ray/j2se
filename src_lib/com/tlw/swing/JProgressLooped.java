

package com.tlw.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoundedRangeModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
public class JProgressLooped extends JProgressBar {
	private static final long serialVersionUID = -7307742383751026892L;
	public JProgressLooped() {super();}
    public JProgressLooped(BoundedRangeModel newModel){super(newModel);}
    public JProgressLooped(int orient){super(orient);}
    public JProgressLooped(int min, int max){super(min,max);}
    public JProgressLooped(int orient, int min, int max){super(orient,min,max);}
    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.setSize(600,400);
        final JProgressLooped progresslooped = new JProgressLooped();
        frame.add(progresslooped,BorderLayout.NORTH);
        JPanel pan=new JPanel();
        frame.add(pan,BorderLayout.CENTER);
        pan.setLayout(new FlowLayout());
        JButton btnStart=new JButton("Start");
        JButton btnStop=new JButton("Stop");
        pan.add(btnStart);
        pan.add(btnStop);
        progresslooped.setStringPainted(true);
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                progresslooped.setString("开始");
                progresslooped.startProgress();
            }
        });
        btnStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                progresslooped.setString("结束");
                progresslooped.stopProgress();
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    DoProgress doProgress;
    synchronized public void startProgress(){
        stopProgress();
        if (doProgress == null) {
            doProgress = new DoProgress();
            doProgress.start();
        }
    }
    public void stopProgress(){
        if(doProgress!=null){
            doProgress.stopThread();
            doProgress=null;
        }
    }
    int sleepSpan=100;
    public int getSleepSpan(){
        return sleepSpan;
    }
    public void setSleepSpan(int sleep){
        sleepSpan=sleep;
    }
    class DoProgress extends Thread{
        boolean started=false;
        public void run(){
            started=true;
            while(true){
                for(int i=getMinimum();i<getMaximum();i++){
                    if(!started){
                        setValue(0);
                        return;
                    }
                    setValue(i);
                    try {
                        Thread.sleep(sleepSpan);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        public void startThread(){
            this.start();
        }
        public void stopThread(){
            started=false;
        }
    }
}
