package com.example.wjx.xing.bean;


import com.example.wjx.xing.db.LogSignOut;

public class SignOutDataResponse extends BaseDataResponse{
	public SignOutDataResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SignOutDataResponse(LogSignOut dbBean) {
		super(dbBean);
		setSignOutBean(dbBean);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5658430071665639311L;
	private LogSignOut signOutBean;

	public LogSignOut getSignOutBean() {
		return signOutBean;
	}

	public void setSignOutBean(LogSignOut signOutBean) {
		this.signOutBean = signOutBean;
	}

	@Override
	public String toString() {
		return "SignOutDataResponse [signOutBean=" + signOutBean + "]";
	}
	

}
