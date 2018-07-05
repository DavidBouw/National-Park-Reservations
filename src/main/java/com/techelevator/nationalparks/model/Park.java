package com.techelevator.nationalparks.model;

import java.time.LocalDate;

public class Park {

	private long parkId;
	private String name;
	private String location;
	private LocalDate establishDate;
	private long area;
	private long visitors;
	private String description;

	public long getPark_id() {
		return this.parkId;
	}

	public void setPark_id(long park_id) {
		this.parkId = park_id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEstablishDate() {
		
		//TODO Implement String to LocalDate conversion here
		
		return this.establishDate;
	}

	public void setEstablishDate(String string) {
		this.establishDate = string;
	}

	public long getArea() {
		return this.area;
	}

	public void setArea(long area) {
		this.area = area;
	}

	public long getVisitors() {
		return this.visitors;
	}

	public void setVisitors(long visitors) {
		this.visitors = visitors;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		return name;
	}
}
