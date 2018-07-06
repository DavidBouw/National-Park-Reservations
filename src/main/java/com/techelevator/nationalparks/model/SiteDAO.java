package com.techelevator.nationalparks.model;

import java.util.List;
import java.sql.Date;

public interface SiteDAO {

	public List<Site> getAvailableSites(Long campgroundId, Date from_date,
			Date to_date);
	
	public Site getSiteById(Long siteId);
}
