package com.example.wjx.xing.bean;


import java.util.List;

public class PersonalListDataResponse extends BaseDataResponse {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8454518505513100649L;
	private List<PersonalBean> list;

	public List<PersonalBean> getList() {
		return list;
	}
	public void setList(List<PersonalBean> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "PersonalListDataResponse [list=" + list + "]";
	}
	
	
}
