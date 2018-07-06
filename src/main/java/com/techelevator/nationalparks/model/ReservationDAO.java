package com.techelevator.nationalparks.model;

import java.sql.Date;

public interface ReservationDAO {
	public Reservation getReservationById(Long reservationId);

	public void createNewReservation(Long siteId, String name, Date fromDate,
		       	Date toDate);

	//This is not part of the spec. Implement it if there's time.
	public void deleteReservation(Long reservationId);
}
