package com.example.wjx.xing.bean;


import com.example.wjx.xing.db.LogSignIn;

public class SignInDataResponse extends BaseDataResponse{
	public SignInDataResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SignInDataResponse(LogSignIn dbBean) {
		super(dbBean);
		setSignInBean(dbBean);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5658430071665639311L;
	private LogSignIn signInBean;

	public LogSignIn getSignInBean() {
		return signInBean;
	}

	public void setSignInBean(LogSignIn signInBean) {
		this.signInBean = signInBean;
	}

	@Override
	public String toString() {
		return "SignInDataResponse [signBean=" + signInBean + "]";
	}
	

}
