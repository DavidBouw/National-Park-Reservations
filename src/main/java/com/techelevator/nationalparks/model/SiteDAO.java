package com.techelevator.nationalparks.model;

import java.time.LocalDate;
import java.util.List;

public interface SiteDAO {

	public List<Site> getAvailableSites(Long campgroundId, LocalDate from_date,
			LocalDate to_date);
	
	public Site getSiteById(Long siteId);
}
