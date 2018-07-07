package com.techelevator.nationalparks.model.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.nationalparks.model.Site;
import com.techelevator.nationalparks.model.SiteDAO;


public class JDBCSiteDAO implements SiteDAO {
	private JdbcTemplate jdbcTemplate;

	public JDBCSiteDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Site getSiteById(long siteId) {
		Site site = new Site();
		String sqlGetSiteById = "SELECT * FROM site WHERE site_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetSiteById, siteId);
		if (!results.next()) {
			throw new Error("Did not find expected result");
		}
		site = mapRowToSite(results);
		if (results.next()) {
			throw new Error("Found too many results");
		}
		return site;
	}

	public List<Site> getAvailableSites(long campgroundId, LocalDate from_date, LocalDate to_date)  {
		ArrayList<Site> availableSites = new ArrayList<Site>();
		String sqlFindAllAvailableSites = " SELECT s.*\n" + 
				" FROM site s\n" + 
				" JOIN reservation r on r.site_id = s.site_id\n" + 
				" JOIN campground c on c.campground_id = s.campground_id\n" + 
				" WHERE (c.campground_id = ?)\n" + 
				" AND ((?,?) OVERLAPS (r.from_date, r.to_date) = false)";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindAllAvailableSites, campgroundId, from_date, to_date);
		while (results.next()) {
			Site theSite = mapRowToSite(results);
			availableSites.add(theSite);
		}
		return availableSites;
	}

	private Site mapRowToSite(SqlRowSet result)   {
		Site aSite = new Site();
		
		aSite.setSiteId(result.getLong("site_id"));
		aSite.setCampgroundId(result.getLong("campground_id"));
		aSite.setSiteNumber(result.getLong("site_number"));
		aSite.setMaxOccupancy(result.getInt("max_occupancy"));
		aSite.setAccessible(result.getBoolean("accessible"));
		aSite.setMaxRvLength(result.getInt("max_rv_length"));
		aSite.setUtilities(result.getBoolean("utilities"));
		
		return aSite;
	}
	
}
