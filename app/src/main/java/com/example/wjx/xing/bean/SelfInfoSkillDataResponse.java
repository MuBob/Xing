package com.example.wjx.xing.bean;

import java.util.Map;

public class SelfInfoSkillDataResponse extends BaseDataResponse {

	public SelfInfoSkillDataResponse(Map<String, String> list) {
		setMap(list);
	}

	/**
	* 
	*/
	private static final long serialVersionUID = 8901239358437203242L;
	private Map<String, String> skillLevel;
	@Override
	public String toString() {
		return "SelfInfoSkillDataResponse [map=" + skillLevel + "]";
	}

	public Map<String, String> getMap() {
		return skillLevel;
	}

	public void setMap(Map<String, String> list) {
		this.skillLevel = list;
	}
	
}
