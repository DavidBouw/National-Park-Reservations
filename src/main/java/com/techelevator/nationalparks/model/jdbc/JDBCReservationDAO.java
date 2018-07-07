package com.techelevator.nationalparks.model.jdbc;

import java.time.LocalDate;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.nationalparks.model.Reservation;
import com.techelevator.nationalparks.model.ReservationDAO;

public class JDBCReservationDAO implements ReservationDAO {
	private JdbcTemplate jdbcTemplate;

	public JDBCReservationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Reservation getReservationById(Long reservationId) {
		Reservation reservation = new Reservation();
		String sqlGetReservationById = "SELECT * FROM reservation WHERE reservation_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetReservationById, reservationId);
		if (!results.next()) {
			return null;
			//throw new Error("Did not find expected result");
		}
		reservation = mapRowToReservation(results);
		if (results.next()) {
			
			throw new Error("Found too many results");	
		}
		
		return reservation;
	}
	
	//TODO Not sure if throwing an error here will cause program to exit, might need a try/catch block
	//or loop this method inside a while loop and have it keep going until no error?
	public long createNewReservation(Long siteId, String name, LocalDate fromDate, LocalDate toDate) {
		String sqlInsertReservation = "INSERT INTO reservation ( site_id, name, from_date, to_date, create_date) "
				+ "VALUES(?, ?, ?, ?, ?) RETURNING reservation_id";
		LocalDate createDate = LocalDate.now();
		if ((fromDate.isBefore(createDate)) || (toDate.isBefore(createDate))) {
			throw new Error("You cannot book a date in the past");
		}
		long reservationId = jdbcTemplate.queryForObject(sqlInsertReservation, 
				Long.class, 
				siteId, 
				name, 
				fromDate, 
				toDate, 
				createDate);
		return reservationId;
	}

	public void deleteReservation(long reservationId) {
		String sqlDeleteReservation = "DELETE FROM reservation where reservation_id = ?";
		jdbcTemplate.update(sqlDeleteReservation, reservationId);
	}

	private Reservation mapRowToReservation(SqlRowSet result) {
		Reservation aReservation = new Reservation();
		
		aReservation.setReservationId(result.getLong("reservation_id"));
		aReservation.setSiteId(result.getLong("site_id"));
		aReservation.setName(result.getString("name"));
		aReservation.setFromDate(result.getDate("from_date").toLocalDate());
		aReservation.setToDate(result.getDate("to_date").toLocalDate());
		aReservation.setCreateDate(result.getDate("create_date").toLocalDate());
		
		return aReservation;
	}

	
}
