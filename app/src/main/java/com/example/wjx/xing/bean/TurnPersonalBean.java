package com.example.wjx.xing.bean;

import com.example.wjx.xing.db.LogTurnDepartment;

/**
 * Created by Administrator on 2017/5/18.
 */

public class TurnPersonalBean {
	private String name;
	private String fromDepartment;
	private String toDepartment;
	private String personalId;
	private String reason;
	private String time;

	public TurnPersonalBean() {
		super();
	}

	public TurnPersonalBean(LogTurnDepartment personal) {
		this();
		if (personal != null) {
			setPersonalId(personal.getId_personal());
			setReason(personal.getReason());
			setTime(personal.getTime());
		}
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFromDepartment() {
		return fromDepartment;
	}

	public void setFromDepartment(String fromDepartment) {
		this.fromDepartment = fromDepartment;
	}

	public String getToDepartment() {
		return toDepartment;
	}

	public void setToDepartment(String toDepartment) {
		this.toDepartment = toDepartment;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getPersonalId() {
		return personalId;
	}

	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	@Override
	public String toString() {
		return "TurnPersonalBean [name=" + name + ", fromDepartment=" + fromDepartment + ", toDepartment="
				+ toDepartment + ", personalId=" + personalId + ", reason=" + reason + ", time=" + time + "]";
	}


}
