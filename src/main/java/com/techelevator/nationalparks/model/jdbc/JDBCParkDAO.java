package com.techelevator.nationalparks.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.nationalparks.model.Campground;
import com.techelevator.nationalparks.model.Park;
import com.techelevator.nationalparks.model.ParkDAO;

public class JDBCParkDAO implements ParkDAO {
	private JdbcTemplate jdbcTemplate;

	public JDBCParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Park> getAllParks() {
		ArrayList<Park> parks = new ArrayList<Park>();
		String sqlGetAllParks = "SELECT * FROM park  ORDER BY park_id ASC";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllParks);
		while (results.next()) {
			Park thePark = mapRowToPark(results);
			parks.add(thePark);
		}
		return parks;
	}

	private Park mapRowToPark(SqlRowSet result) {
		Park aPark = new Park();

		aPark.setPark_id(result.getLong("park_id"));
		aPark.setName(result.getString("name"));
		aPark.setLocation(result.getString("location"));
		aPark.setEstablishDate(result.getString("establish_date"));
		aPark.setArea(result.getLong("area"));
		aPark.setVisitors(result.getLong("visitors"));
		aPark.setDescription(result.getString("description"));

		return aPark;
	}

}
