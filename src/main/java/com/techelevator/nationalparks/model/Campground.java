package com.techelevator.nationalparks.model;

public class Campground extends ParkData{

	private long campgroundId;
	private long parkId;
	private String name;
	private String openFromMonth;
	private String openToMonth;
	private double dailyFee;

	public long getCampgroundId() {
		return this.campgroundId;
	}

	public void setCampgroundId(long campgroundId) {
		this.campgroundId = campgroundId;
	}

	public long getParkId() {
		return this.parkId;
	}

	public void setParkId(long parkId) {
		this.parkId = parkId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOpenFromMonth() {
		return this.openFromMonth;
	}

	public void setOpenFromMonth(String openFromMonth) {
		this.openFromMonth = openFromMonth;
	}

	public String getOpenToMonth() {
		return this.openToMonth;
	}

	public void setOpenToMonth(String openToMonth) {
		this.openToMonth = openToMonth;
	}

	public double getDailyFee() {
		return this.dailyFee;
	}

	public void setDailyFee(double dailyFee) {
		this.dailyFee = dailyFee;
	}

	@Override
	public String toString() {
		return this.name;
	}
	
}
