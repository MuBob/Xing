package com.example.wjx.xing.bean;

import java.util.List;

public class TurnPersonalListDataResponse extends BaseDataResponse {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8454518505513100649L;
	private List<TurnPersonalBean> list;

	public List<TurnPersonalBean> getList() {
		return list;
	}
	public void setList(List<TurnPersonalBean> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "PersonalListDataResponse [list=" + list + "]";
	}
	
	
}
