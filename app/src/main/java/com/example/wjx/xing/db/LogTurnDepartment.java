package com.example.wjx.xing.db;

public class LogTurnDepartment extends BaseDataBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4668040046178262354L;
	public static final String TABLE_NAME="log_turn_department";
	public static final  String COLUMN_ID="_id";
	public static final  String COLUMN_UID="id_personal";
	public static final  String COLUMN_DID_FROM="id_department_from";
	public static final  String COLUMN_DID_TO="id_department_to";
	public static final  String COLUMN_REASON="reason";
	public static final  String COLUMN_TIME="time";

	private String _id;
	private String id_personal;
	private String id_department_from;
	private String id_department_to;
	private String reason;
	private String time;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getId_personal() {
		return id_personal;
	}
	public void setId_personal(String id_personal) {
		this.id_personal = id_personal;
	}
	public String getId_department_from() {
		return id_department_from;
	}
	public void setId_department_from(String id_department_from) {
		this.id_department_from = id_department_from;
	}
	public String getId_department_to() {
		return id_department_to;
	}
	public void setId_department_to(String id_department_to) {
		this.id_department_to = id_department_to;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "LogTurnDepartment [_id=" + _id + ", id_personal=" + id_personal + ", id_department_from="
				+ id_department_from + ", id_department_to=" + id_department_to + ", reason=" + reason + ", time="
				+ time + "]";
	}
	
}
