package com.techelevator.nationalparks.model.jdbc;

import java.time.LocalDate;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.nationalparks.model.Reservation;
import com.techelevator.nationalparks.model.ReservationDAO;

public class JDBCReservationDAO implements ReservationDAO{
	private JdbcTemplate jdbcTemplate;

	public JDBCReservationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private Reservation mapRowToReservation(SqlRowSet result) {
		Reservation aReservation = new Reservation();
		
		aReservation.setReservationId(result.getLong("reservation_id"));
		aReservation.setSiteId(result.getLong("site_id"));
		aReservation.setName(result.getString("name"));
		
		//TODO decide how to handle LocalDate objects
		aReservation.setFromDate((LocalDate) result.getObject("from_date"));
		aReservation.setToDate((LocalDate) result.getObject("to_date"));
		aReservation.setCreateDate((LocalDate) result.getObject("create_date"));
		
		return aReservation;
	}
}
