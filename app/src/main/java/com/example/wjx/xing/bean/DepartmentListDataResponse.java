package com.example.wjx.xing.bean;


import com.example.wjx.xing.db.TableDepartment;

import java.util.List;

public class DepartmentListDataResponse extends BaseDataResponse {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8645937851328318382L;

	private List<TableDepartment> list;

	public List<TableDepartment> getList() {
		return list;
	}
	public void setList(List<TableDepartment> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "DepartmentListDataResponse [list=" + list + "]";
	}
	
	
}
