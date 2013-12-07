package com.tlw.ui.alarm.model;
/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-3-30
@version:2009-3-30
Description:分页实现
 */
public abstract class AbstractPagger implements IPagger {
	int pageSize;
	int currentPage=1;
	public AbstractPagger(){}
	public AbstractPagger(int pageSize){
		this.pageSize=pageSize;
	}
	public boolean canNextPage() {
		if(currentPage<getPageCount()){
			return true;
		}else{
			return false;
		}
	}
	public boolean canPrePage() {
		if(currentPage==1){
			return false;
		}else{
			return true;
		}
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int pageIndex) {
		if(pageIndex<=1){
			currentPage=1;
		}else if(pageIndex>=getPageCount()){
			currentPage=getPageCount();
		}else{
			currentPage=pageIndex;
		}
	}
	public int getPageCount() {
		int pageCount=getRowCount()/pageSize;
		if(getRowCount()==0)return pageCount+1;
		if(getRowCount() % pageSize ==0)return pageCount;
		return pageCount+1;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize=pageSize;
	}
	//---附加方法，非接口描述的，非必备的，但可能会经常用到的方法
	public void firstPage(){
		this.currentPage=1;
	}
	public void lastPage(){
		this.currentPage=this.getPageCount();
	}
	public void nextPage(){
		this.setCurrentPage(this.getCurrentPage()+1);
	}
	public void prePage(){
		this.setCurrentPage(this.getCurrentPage()-1);
	}
}