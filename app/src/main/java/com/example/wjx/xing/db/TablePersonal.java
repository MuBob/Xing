package com.example.wjx.xing.db;

public class TablePersonal extends BaseDataBase{
	private static final long serialVersionUID = 4765815430788924696L;
	public static final String TABLE_NAME="table_personal";
	public static final String COLUMN_UID="id";
	public static final String COLUMN_PASSWORD="pwd";
	public static final String COLUMN_NAME="name";
	public static final String COLUMN_SEX="sex";
	public static final String COLUMN_IDCARD="idcard";
	public static final String COLUMN_EMAIL="email";
	public static final String COLUMN_BIRTHDAY="birthday";
	public static final String COLUMN_HIGHRECORD="high_record";
	public static final String COLUMN_GRADUTESCHOOL="graduate_school";
	public static final String COLUMN_SKILLS_LEVEL="skill_level";
	public static final String COLUMN_IDDEPARTMENT="id_department";
	public static final String COLUMN_ROLE="role";
	public static final String COLUMN_TITLE="title";
	
	private String id;
	private String pwd;
	private String name;
	private String sex;
	private String idcard;
	private String email;
	private String birthday;
	private String high_record;
	private String graduate_school;
	private String skill_level;
	private String id_department;
	private String role;
	private String title;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getHigh_record() {
		return high_record;
	}
	public void setHigh_record(String high_record) {
		this.high_record = high_record;
	}
	public String getGraduate_school() {
		return graduate_school;
	}
	public void setGraduate_school(String graduate_school) {
		this.graduate_school = graduate_school;
	}
	public String getId_department() {
		return id_department;
	}
	public void setId_department(String id_department) {
		this.id_department = id_department;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSkill_level() {
		return skill_level;
	}
	public void setSkill_level(String skill_level) {
		this.skill_level = skill_level;
	}
	@Override
	public String toString() {
		return "TablePersonalDataBase [id=" + id + ", pwd=" + pwd + ", name=" + name + ", sex=" + sex + ", idcard="
				+ idcard + ", email=" + email + ", birthday=" + birthday + ", high_record=" + high_record
				+ ", graduate_school=" + graduate_school + ", skill_level=" + skill_level
				+ ", id_department=" + id_department + ", role=" + role + ", title=" + title + "]";
	}
}
