package com.techelevator.nationalparks.model;

public class Site {

	private long siteId;
	private long campgroundId;
	private long siteNumber;
	private int maxOccupancy;
	private boolean accessible;
	private int maxRvLength;
	private boolean utilities;

	public long getSiteId() {
		return this.siteId;
	}

	public void setSiteId(long siteId) {
		this.siteId = siteId;
	}

	public long getCampgroundId() {
		return this.campgroundId;
	}

	public void setCampgroundId(long campgroundId) {
		this.campgroundId = campgroundId;
	}

	public long getSiteNumber() {
		return this.siteNumber;
	}

	public void setSiteNumber(long siteNumber) {
		this.siteNumber = siteNumber;
	}

	public int getMaxOccupancy() {
		return this.maxOccupancy;
	}

	public void setMaxOccupancy(int maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}

	public boolean isAccessible() {
		return this.accessible;
	}

	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}

	public int getMaxRvLength() {
		return this.maxRvLength;
	}

	public void setMaxRvLength(int maxRvLength) {
		this.maxRvLength = maxRvLength;
	}

	public boolean isUtilities() {
		return this.utilities;
	}

	public void setUtilities(boolean utilities) {
		this.utilities = utilities;
	}

	@Override
	public String toString() {
		return "";
	}
}
