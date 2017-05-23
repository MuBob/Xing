package com.example.wjx.xing.db;

public class LogSignIn extends BaseDataBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1346640688604534700L;
	public static final String TABLE_NAME="log_sign_in";
	public static final String COLUMN_ID="_id";
	public static final String COLUMN_UID="id_personal";
	public static final String COLUMN_TIME="time1";
	public static final String COLUMN_PLACE="place1";
	public static final String COLUMN_TIME2="time2";
	public static final String COLUMN_PLACE2="place2";
	public static final String COLUMN_COUNT="count";
	
	private String id_personal;
	private String time1;
	private String place1;
	private String time2;
	private String place2;
	private int count;
	public String getId_personal() {
		return id_personal;
	}
	public void setId_personal(String id_personal) {
		this.id_personal = id_personal;
	}
	public String getTime1() {
		return time1;
	}
	public void setTime1(String time1) {
		this.time1 = time1;
	}
	public String getPlace1() {
		return place1;
	}
	public void setPlace1(String place1) {
		this.place1 = place1;
	}
	public String getTime2() {
		return time2;
	}
	public void setTime2(String time2) {
		this.time2 = time2;
	}
	public String getPlace2() {
		return place2;
	}
	public void setPlace2(String place2) {
		this.place2 = place2;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "LogSignIn [id_personal=" + id_personal + ", time1=" + time1 + ", place1=" + place1 + ", time2=" + time2
				+ ", place2=" + place2 + ", count=" + count + "]";
	}
}
