package com.spring.CovidTracker.Model;

public class DailyData {
	
	private String country;
	private String state;
	private int newCases;
	private int prevDayCase;
	
	
	
	
	@Override
	public String toString() {
		return "DailyData [country=" + country + ", state=" + state + ", newCases=" + newCases + ", prevDayCase="
				+ prevDayCase + "]";
	}
	public DailyData(String country, String state, int newCases, int prevDayCase) {
		super();
		this.country = country;
		this.state = state;
		this.newCases = newCases;
		this.prevDayCase = prevDayCase;
	}
	
	public DailyData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getNewCases() {
		return newCases;
	}
	public void setNewCases(int newCases) {
		this.newCases = newCases;
	}
	public int getPrevDayCase() {
		return prevDayCase;
	}
	public void setPrevDayCase(int prevDayCase) {
		this.prevDayCase = prevDayCase;
	}
	
	

}
