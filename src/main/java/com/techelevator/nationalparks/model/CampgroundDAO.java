package com.techelevator.nationalparks.model;

import java.util.List;

public interface CampgroundDAO {

	public List<Campground> getAllCampgrounds(Long parkId);

	public Campground getCampgroundById(Long campgroundId);


//TODO: Bonus section asks to search all campgrounds in a park with the user provided from and to_dates.
		
	
}
