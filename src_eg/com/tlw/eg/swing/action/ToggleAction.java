package com.tlw.eg.swing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JToolBar;

import com.tlw.util.UtilUi;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年6月25日
 * 三种状态按规则切换的例子
 */
public class ToggleAction{

	static PlayAction playAction=new PlayAction();
	static PauseAction pauseAction=new PauseAction();
	static StopAction stopAction=new StopAction();
	
	public static void main(String[] args) {
		JToolBar toolbar=new JToolBar();
		toolbar.add(playAction);
		toolbar.add(pauseAction);
		toolbar.add(stopAction);
		refreshStatus(status);
		UtilUi.showPack(toolbar, "");
	}
	
	static enum Status{
		PLAYING, PAUSED, STOPED
	}
	
	static Status status=Status.STOPED;
	
	public static void refreshStatus(Status status){
		switch(status){
		case PLAYING:
			playAction.setEnabled(false);
			pauseAction.setEnabled(true);
			stopAction.setEnabled(true);
			break;
		case PAUSED:
			playAction.setEnabled(true);
			pauseAction.setEnabled(false);
			stopAction.setEnabled(true);
			break;
		case STOPED:
			playAction.setEnabled(true);
			pauseAction.setEnabled(false);
			stopAction.setEnabled(false);
			break;
		}
	}
	
	static class PlayAction extends AbstractAction{
		private static final long serialVersionUID = -5480932154982782555L;
		public PlayAction(){
			putValue(NAME, "Play");
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			status=Status.PLAYING;
			refreshStatus(status);
		}
	}
	
	static class PauseAction extends AbstractAction{
		private static final long serialVersionUID = -5480932154982782555L;
		public PauseAction(){
			putValue(NAME, "Pause");
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			status=Status.PAUSED;
			refreshStatus(status);
		}
	}
	
	static class StopAction extends AbstractAction{
		private static final long serialVersionUID = -5480932154982782555L;
		public StopAction(){
			putValue(NAME, "Stop");
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			status=Status.STOPED;
			refreshStatus(status);
		}
	}

}
