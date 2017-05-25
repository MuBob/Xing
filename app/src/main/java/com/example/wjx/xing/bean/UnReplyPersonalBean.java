package com.example.wjx.xing.bean;


import com.example.wjx.xing.db.BaseDataBase;
import com.example.wjx.xing.db.LogLeave;
import com.example.wjx.xing.db.LogTrip;

public class UnReplyPersonalBean extends BaseDataBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7014098467514942318L;
	private String id;
	private String name;

	public UnReplyPersonalBean(LogTrip logTrip) {
		setId(logTrip.getId_apply());
	}
	public UnReplyPersonalBean(LogLeave logLeave) {
		setId(logLeave.getId_apply());
	}

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

	@Override
	public String toString() {
		return "UnReplyTripBean [id=" + id + ", name=" + name + "]";
	}

}
