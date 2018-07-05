package com.techelevator.nationalparks.model.jdbc;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.nationalparks.model.Site;
import com.techelevator.nationalparks.model.SiteDAO;

public class JDBCSiteDAO implements SiteDAO{
	private JdbcTemplate jdbcTemplate;

	public JDBCSiteDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private Site mapRowToSite(SqlRowSet result) {
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
