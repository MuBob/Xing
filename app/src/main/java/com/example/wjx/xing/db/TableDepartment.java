package com.example.wjx.xing.db;

public class TableDepartment extends BaseDataBase{
	public static final String TABLE_NAME="table_department";
	public static final String COLUMN_ID="_id";
	public static final String COLUMN_UID="id";
	public static final String COLUMN_NAME="name";
	public static final String COLUMN_DESC="desc";
	
	private String id;
	private String name;
	private String desc;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "TableDepartment [id=" + id + ", name=" + name + ", desc=" + desc + "]";
	}
}
