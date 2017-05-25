package com.example.wjx.xing.db;

public class TableSkill extends BaseDataBase {
	public final static String TABLE_NAME = "table_skill";
	public final static String COLUMN_ID = "id";
	public final static String COLUMN_NAME = "name";
	public final static String COLUMN_MAX_LEVEL = "max_level";
	private static final long serialVersionUID = 8901239358437203242L;
	private String id;
	private String name;
	private String max_level;

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

	public String getMax_level() {
		return max_level;
	}

	public void setMax_level(String max_level) {
		this.max_level = max_level;
	}

	@Override
	public String toString() {
		return "TableSkillDataBase [id=" + id + ", name=" + name + ", max_level=" + max_level + "]";
	}

}
