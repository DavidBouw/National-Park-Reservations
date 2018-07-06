package com.techelevator.nationalparks.model.jdbc;

import java.time.LocalDate;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.nationalparks.model.Campground;
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
		reservation = mapRowToReservation(results);
		return reservation;
	}

	public void createNewReservation(Long siteId, String name, LocalDate fromDate, LocalDate toDate) {
		String sqlInsertReservation = "INSERT INTO reservation (reservation_id, site_id, name, from_date, to_date, create_date) "
				+ "VALUES(?, ?, ?. ?)";
		LocalDate createDate = LocalDate.now();
		long reservationId = getNextReservationId();

		jdbcTemplate.update(sqlInsertReservation, reservationId, siteId, name, fromDate, toDate, createDate);
	}

	public void deleteReservation(long reservationId) {
		String sqlDeleteReservation = "DELETE FROM reservation where reservation_id = ?";
		jdbcTemplate.update(sqlDeleteReservation, reservationId);
	}

	private long getNextReservationId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('reservation_reservation_id_seq')");
		if (nextIdResult.next()) {
			return nextIdResult.getLong(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new reservation");
		}
	}

	private Reservation mapRowToReservation(SqlRowSet result) {
		Reservation aReservation = new Reservation();

		aReservation.setReservationId(result.getLong("reservation_id"));
		aReservation.setSiteId(result.getLong("site_id"));
		aReservation.setName(result.getString("name"));

		// TODO decide how to handle LocalDate objects
		aReservation.setFromDate((LocalDate) result.getObject("from_date"));
		aReservation.setToDate((LocalDate) result.getObject("to_date"));
		aReservation.setCreateDate((LocalDate) result.getObject("create_date"));

		return aReservation;
	}
}
