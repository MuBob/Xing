package com.example.wjx.xing.db;

public class TableDepartment extends BaseDataBase{
	public static final String TABLE_NAME="table_department";
	public static final String COLUMN_UID="id";
	public static final String COLUMN_NAME="name";
	public static final String COLUMN_DESC="description";
	
	private String id;
	private String name;
	private String description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String desc) {
		this.description = desc;
	}
	@Override
	public String toString() {
		return "TableDepartment [id=" + id + ", name=" + name + ", desc=" + description + "]";
	}
}
