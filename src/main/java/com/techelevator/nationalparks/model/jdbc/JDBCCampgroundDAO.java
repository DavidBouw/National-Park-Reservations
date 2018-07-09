package com.techelevator.nationalparks.model.jdbc;


import java.util.ArrayList;
import java.util.List;

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
	
	public List<Campground> getAllCampgrounds(Long parkId){
		ArrayList<Campground> campgrounds = new ArrayList<>();
		String sqlGetAllCampgrounds= 
		"SELECT * FROM campground WHERE park_id = ? ORDER BY campground_id ASC";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllCampgrounds, parkId);
		while(results.next()) {
			Campground theCampground = mapRowToCampground(results);
			campgrounds.add(theCampground);
		}
		return campgrounds;
	}
	
	public Campground getCampgroundById(Long campgroundId) {
		Campground campground = new Campground();
		String sqlGetCampgroundById= 
		"SELECT * FROM campground WHERE campground_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetCampgroundById, campgroundId);
		if (!results.next()) {
			throw new Error("Did not find expected result");
		}
		campground = mapRowToCampground(results);
		if (results.next()) {
			throw new Error("Found too many results");
		}
		return campground;
	}
	
	public double getTotalCostOfStay(long campgroundId, long stayLength) {
		double totalCost;
		String sqlGetPrice = "SELECT daily_fee*? as total_cost\n" + 
				" FROM campground\n" + 
				" WHERE campground_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetPrice, stayLength, campgroundId);
		if (!results.next()) {
			throw new Error("Did not find expected result");
		}
		totalCost = mapRowToTotalCost(results);
		if (results.next()) {
			throw new Error("Found too many results");
		}
		return totalCost;
	}
	
	
	private double mapRowToTotalCost(SqlRowSet result) {
		double totalCost = result.getDouble("total_cost");
		return totalCost;	
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
