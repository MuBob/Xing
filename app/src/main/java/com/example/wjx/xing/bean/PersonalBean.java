package com.example.wjx.xing.bean;


import com.example.wjx.xing.db.TablePersonal;

/**
 * Created by Administrator on 2017/5/18.
 */

public class PersonalBean {
	private String name;
	private String department;
	private String id;
	private String role;
	private String email;
	private String highRecord;
	private String title;
	private String password;
	private String birthday;
	private String sex;
	private double tripDays;
	private double leaveDays;
	private double absenteenismDays;

	public PersonalBean() {
		super();
	}

	public PersonalBean(TablePersonal personal) {
		this();
		if (personal != null) {
			setName(personal.getName());
			setId(personal.getId());
			setRole(personal.getRole());
			setEmail(personal.getEmail());
			setHighRecord(personal.getHigh_record());
			setTitle(personal.getTitle());
			setPassword(personal.getPwd());
			setBirthday(personal.getBirthday());
			setSex(personal.getSex());
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHighRecord() {
		return highRecord;
	}

	public void setHighRecord(String highRecord) {
		this.highRecord = highRecord;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public double getTripDays() {
		return tripDays;
	}

	public void setTripDays(double tripDays) {
		this.tripDays = tripDays;
	}

	public double getLeaveDays() {
		return leaveDays;
	}

	public void setLeaveDays(double leaveDays) {
		this.leaveDays = leaveDays;
	}

	public double getAbsenteenismDays() {
		return absenteenismDays;
	}

	public void setAbsenteenismDays(double absenteenismDays) {
		this.absenteenismDays = absenteenismDays;
	}

	@Override
	public String toString() {
		return "PersonalBean [name=" + name + ", department=" + department + ", id=" + id + ", role=" + role
				+ ", email=" + email + ", highRecord=" + highRecord + ", title=" + title + ", password=" + password
				+ ", birthday=" + birthday + ", sex=" + sex + ", tripDays=" + tripDays + ", leaveDays=" + leaveDays
				+ ", absenteenismDays=" + absenteenismDays + "]";
	}
	
}
