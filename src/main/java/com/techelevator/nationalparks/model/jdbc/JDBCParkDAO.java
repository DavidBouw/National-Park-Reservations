package com.techelevator.nationalparks.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.nationalparks.model.Park;
import com.techelevator.nationalparks.model.ParkDAO;

public class JDBCParkDAO implements ParkDAO{
	private JdbcTemplate jdbcTemplate;

	public JDBCParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
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
	
	@Override
	public List<Park> getAllParks() {
		ArrayList<Park> parks = new ArrayList<Park>();
		String sqlStatement = "SELECT * FROM park";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlStatement);
		
		while(results.next()) {
			parks.add(e)
		}
	}
}
