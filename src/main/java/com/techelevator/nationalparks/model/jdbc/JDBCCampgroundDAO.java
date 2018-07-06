package com.techelevator.nationalparks.model.jdbc;

import java.sql.Date;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.nationalparks.model.CampgroundDAO;
import com.techelevator.nationalparks.model.Campground;

public class JDBCCampgroundDAO implements CampgroundDAO{
	private JdbcTemplate jdbcTemplate;

	public JDBCCampgroundDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private Campground mapRowToCampground(SqlRowSet result) {
		Campground aCampground = new Campground();
		
		aCampground.setCampgroundId(result.getLong("campground_id"));
		aCampground.setParkId(result.getLong("park_id"));
		aCampground.setName(result.getString("name"));
		aCampground.setOpenFromMonth(result.getString("open_from_mm"));
		aCampground.setOpenToMonth(result.getString("open_to_mm"));
		aCampground.setDailyFee(result.getDouble("daily_fee"));
		
		return aCampground;
	}
}
