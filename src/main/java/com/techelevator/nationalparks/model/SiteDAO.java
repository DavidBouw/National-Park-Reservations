package com.techelevator.nationalparks.model;

import java.time.LocalDate;
import java.util.List;
import java.sql.Date;

public interface SiteDAO {

	public List<Site> getAvailableSites(long campgroundId, LocalDate from_date,
			LocalDate to_date);
	
	public Site getSiteById(long siteId);
}
