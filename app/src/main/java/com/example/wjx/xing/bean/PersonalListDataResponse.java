package com.example.wjx.xing.bean;


import com.example.wjx.xing.db.TablePersonal;

import java.util.List;

public class PersonalListDataResponse extends BaseDataResponse {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8454518505513100649L;
	private List<TablePersonal> list;

	public List<TablePersonal> getList() {
		return list;
	}
	public void setList(List<TablePersonal> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "PersonalListDataResponse [list=" + list + "]";
	}
	
	
}
