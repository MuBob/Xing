package com.example.wjx.xing.bean;

import java.util.Date;

public class SelfInfoRecordDataResponse extends BaseDataResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5432354265423924066L;
	private String id;
	private String highRecord;
	private String graduateSchool;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHighRecord() {
		return highRecord;
	}
	public void setHigh_record(String highRecord) {
		this.highRecord = highRecord;
	}
	public String getGraduateSchool() {
		return graduateSchool;
	}
	public void setGraduate_school(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}
	@Override
	public String toString() {
		return "SelfInfoRecordDataResponse [id=" + id + ", highRecord=" + highRecord + ", graduateSchool="
				+ graduateSchool + "]";
	}
	
}
