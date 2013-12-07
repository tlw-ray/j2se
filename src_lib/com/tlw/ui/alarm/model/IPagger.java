package com.tlw.ui.alarm.model;
/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-3-30
@version:2009-3-30
Description:分页功能接口
 */
public interface IPagger {
	public boolean canNextPage();				//可以向后翻页，即未达到尾页
	public boolean canPrePage();				//可以向前翻页，即未达到首页
	public int getCurrentPage();				//获取当前页页码，起始页码为0；
	public void setCurrentPage(int pageIndex);	//设置当前页页码，当页码小于0时当前页码为0，当页码大于尾页页码时，当前页码为尾页页码。
	public int getPageSize();					//获取页面可包含的行数
	public void setPageSize(int pageSize);		//设置页面可包含的行数
	public int getRowCount();					//获得当前所有行数
	public int getPageCount();					//获得根据当前页面大小及所有行数可分的页数，最少可以分1页
}