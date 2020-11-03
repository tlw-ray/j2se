package com.tlw.ui.alarm.model;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-3-31
@version:2009-3-31
Description:
 */
public class PaggerHelper {
	AbstractPagger pagger;
	int stepSize=5;
	JLabel jlabelPageInfo;
	ActionNextPage nextPage=new ActionNextPage();
	ActionPrePage prePage=new ActionPrePage();
	ActionNextPages nextPages=new ActionNextPages();
	ActionPrePages prePages=new ActionPrePages();
	ActionFirstPage firstPage=new ActionFirstPage();
	ActionLastPage lastPage=new ActionLastPage();
	public PaggerHelper(AbstractPagger pagger){this.pagger=pagger;}
	public void setStepSize(int stepSize){this.stepSize=stepSize;}
	public int getStepSize(){return stepSize;}
	public void setJLabelPageInfo(JLabel jlabelInfo){jlabelPageInfo=jlabelInfo;}
	public Action getActionNextPage(){return nextPage;}
	public Action getActionPrePage(){return prePage;}
	public Action getActionNextPages(){return nextPages;}
	public Action getActionPrePages(){return prePages;}
	public Action getActionFirstPage(){return firstPage;}
	public Action getActionLastPage(){return lastPage;}
	public JLabel getPageInfoLabel(){
		if(jlabelPageInfo==null){
			jlabelPageInfo=new JLabel();
		}
		return jlabelPageInfo;
	}
	public void refreshPageInfo(){
		if(jlabelPageInfo!=null){
			jlabelPageInfo.setText(pagger.getCurrentPage()+" / "+pagger.getPageCount());
		}
	}
	class ActionNextPage extends AbstractAction{
		private static final long serialVersionUID = -5473378442773592267L;
		public ActionNextPage(){putValue(Action.NAME,">");putValue(Action.SHORT_DESCRIPTION,"下一页");}
		public void actionPerformed(ActionEvent e) {pagger.nextPage();refreshPageInfo();}
	}
	class ActionPrePage extends AbstractAction{
		private static final long serialVersionUID = -4907521023221659720L;
		public ActionPrePage(){putValue(Action.NAME,"<");putValue(Action.SHORT_DESCRIPTION,"上一页");}
		public void actionPerformed(ActionEvent e){pagger.prePage();refreshPageInfo();}
	}
	class ActionLastPage extends AbstractAction{
		private static final long serialVersionUID = 5999264050940714747L;
		public ActionLastPage(){putValue(Action.NAME,">|");putValue(Action.SHORT_DESCRIPTION,"最后一页");}
		public void actionPerformed(ActionEvent e){pagger.lastPage();refreshPageInfo();}
	}
	class ActionFirstPage extends AbstractAction{
		private static final long serialVersionUID = -7042486093827150419L;
		public ActionFirstPage(){putValue(Action.NAME,"|<");putValue(Action.SHORT_DESCRIPTION,"第一页");}
		public void actionPerformed(ActionEvent e){pagger.firstPage();refreshPageInfo();}
	}
	class ActionNextPages extends AbstractAction{
		private static final long serialVersionUID = 6819635073555189749L;
		public ActionNextPages(){putValue(Action.NAME,">>");putValue(Action.SHORT_DESCRIPTION,"下五页");}
		public void actionPerformed(ActionEvent e){pagger.setCurrentPage(pagger.getCurrentPage()+stepSize);refreshPageInfo();}
	}
	class ActionPrePages extends AbstractAction{
		private static final long serialVersionUID = 2821079379377718441L;
		public ActionPrePages(){putValue(Action.NAME,"<<");putValue(Action.SHORT_DESCRIPTION,"上五页");}
		public void actionPerformed(ActionEvent e){pagger.setCurrentPage(pagger.getCurrentPage()-stepSize);refreshPageInfo();}
	}
}
