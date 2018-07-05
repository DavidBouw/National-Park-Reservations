package com.techelevator.nationalparks.model;

import java.util.List;

public interface ParkDAO {

	public List<Park> getAllParks();
	
	public Campground getCampgroundById(Long campgroundId);	
}
