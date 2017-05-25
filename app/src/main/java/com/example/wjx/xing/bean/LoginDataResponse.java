package com.example.wjx.xing.bean;

public class LoginDataResponse extends BaseDataResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1430175757135687807L;
	private String id;
	private String pwd;
	private String name;

	public LoginDataResponse() {
		super();
	}

	public LoginDataResponse(String id, String pwd) {
		super();
		this.id = id;
		this.pwd = pwd;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "LoginResponse [id=" + id + ", pwd=" + pwd + "]";
	}

}
