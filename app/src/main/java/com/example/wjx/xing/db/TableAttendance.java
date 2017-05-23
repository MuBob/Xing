package com.example.wjx.xing.db;

public class TableAttendance extends BaseDataBase{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3332586558812595474L;
	public static final String TABLE_NAME="table_attendance";
	public static final String COLUMN_ID="_id";
	public static final String COLUMN_UID="id_personal";
	public static final String COLUMN_DAYS_TRIP="days_trip";
	public static final String COLUMN_DAYS_LEAVE="days_leave";
	public static final String COLUMN_DAYS_ABSENTEEISM="days_absenteeism";
	
	private String id_personal;
	private double days_trip;
	private double days_leave;
	private double days_absenteeism;
	public String getId_personal() {
		return id_personal;
	}
	public void setId_personal(String id_personal) {
		this.id_personal = id_personal;
	}
	public double getDays_trip() {
		return days_trip;
	}
	public void setDays_trip(double days_trip) {
		this.days_trip = days_trip;
	}
	public double getDays_leave() {
		return days_leave;
	}
	public void setDays_leave(double days_leave) {
		this.days_leave = days_leave;
	}
	public double getDays_absenteeism() {
		return days_absenteeism;
	}
	public void setDays_absenteeism(double days_absenteeism) {
		this.days_absenteeism = days_absenteeism;
	}
	@Override
	public String toString() {
		return "TableAttendance [id_personal=" + id_personal + ", days_trip=" + days_trip + ", days_leave=" + days_leave
				+ ", days_absenteeism=" + days_absenteeism + "]";
	}
}
