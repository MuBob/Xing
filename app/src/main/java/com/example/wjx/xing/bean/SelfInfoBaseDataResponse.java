package com.example.wjx.xing.bean;


import com.example.wjx.xing.db.BaseDataBase;

public class SelfInfoBaseDataResponse extends BaseDataResponse {
	
	public SelfInfoBaseDataResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SelfInfoBaseDataResponse(BaseDataBase dbBean) {
		super(dbBean);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1562951043899664433L;
	private String id;
	private String name;
	private String sex;
	private String idCard;
	private String eMail;
	private String birthday;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	@Override
	public String toString() {
		return "SelfInfoBaseDataResponse [id=" + id + ", name=" + name + ", sex=" + sex + ", idCard=" + idCard
				+ ", eMail=" + eMail + ", birthday=" + birthday + "]";
	}
	
}
