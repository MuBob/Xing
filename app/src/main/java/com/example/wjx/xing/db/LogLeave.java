package com.example.wjx.xing.db;

public class LogLeave extends BaseDataBase{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3877974455010614135L;
	public static final String TABLE_NAME="log_leave";
	public static final String COLUMN_ID="_id";
	public static final String COLUMN_UID="id_personal";
	public static final String COLUMN_TIME_APPLY="time_apply";
	public static final String COLUMN_DAY_START="day_start";
	public static final String COLUMN_DAY_END="day_end";
	public static final String COLUMN_DAYS="days";
	public static final String COLUMN_REASON="reason";
	public static final String COLUMN_PICTURE="picture";
	public static final String COLUMN_REPLY_TYPE="reply";
	public static final String COLUMN_REPLY_TIME="time_reply";
	public static final String COLUMN_REPLY_REASON="reason_reply";
	
	private String id_personal;
	private String time_apply;
	private String day_start;
	private String day_end;
	private double days;
	private String reason;
	private String picture;
	private int reply;
	private String time_reply;
	private String reason_reply;
	public String getId_personal() {
		return id_personal;
	}
	public void setId_personal(String id_personal) {
		this.id_personal = id_personal;
	}
	public String getTime_apply() {
		return time_apply;
	}
	public void setTime_apply(String time_apply) {
		this.time_apply = time_apply;
	}
	public String getDay_start() {
		return day_start;
	}
	public void setDay_start(String day_start) {
		this.day_start = day_start;
	}
	public String getDay_end() {
		return day_end;
	}
	public void setDay_end(String day_end) {
		this.day_end = day_end;
	}
	public double getDays() {
		return days;
	}
	public void setDays(double days) {
		this.days = days;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getReply() {
		return reply;
	}
	public void setReply(int reply) {
		this.reply = reply;
	}
	public String getTime_reply() {
		return time_reply;
	}
	public void setTime_reply(String time_reply) {
		this.time_reply = time_reply;
	}
	public String getReason_reply() {
		return reason_reply;
	}
	public void setReason_reply(String reason_reply) {
		this.reason_reply = reason_reply;
	}
	@Override
	public String toString() {
		return "LogLeave [id_personal=" + id_personal + ", time_apply=" + time_apply + ", day_start=" + day_start
				+ ", day_end=" + day_end + ", days=" + days + ", reason=" + reason + ", picture=" + picture + ", reply="
				+ reply + ", time_reply=" + time_reply + ", reason_reply=" + reason_reply + "]";
	}
}
