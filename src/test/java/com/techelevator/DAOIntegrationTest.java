package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.nationalparks.model.Campground;
import com.techelevator.nationalparks.model.Park;
import com.techelevator.nationalparks.model.Reservation;
import com.techelevator.nationalparks.model.Site;
import com.techelevator.nationalparks.model.jdbc.JDBCCampgroundDAO;
import com.techelevator.nationalparks.model.jdbc.JDBCParkDAO;
import com.techelevator.nationalparks.model.jdbc.JDBCReservationDAO;
import com.techelevator.nationalparks.model.jdbc.JDBCSiteDAO;

public class DAOIntegrationTest {

	private static SingleConnectionDataSource dataSource;

	// Campground variables
	private static final long TEST_CAMPGROUND_ID = 1000;
	private static final long TEST_PARK_ID = 1;
	private static final String TEST_CAMPGROUND_NAME = "Mars Colony";
	private static final String TEST_OPEN_FROM_MONTH = "09";
	private static final String TEST_OPEN_TO_MONTH = "12";

	// Site variables
	private static final long TEST_SITE_ID = 10000;
	private static final long TEST_SITE_NUMBER = 10000;
	private static final int TEST_MAX_OCCUPANCY = 50;
	private static final boolean TEST_ACCESSIBLE = true;
	private static final int TEST_MAX_RV_LENGTH = 40;
	private static final boolean TEST_UTILITIES = true;

	// Reservation variables
	private static final long TEST_RESERVATION_ID = 9000;
	private static final String TEST_RESERVATION_NAME = "The Manson Family";
	private static final LocalDate TEST_FROM_DATE = LocalDate.of(2020, 9, 9);
	private static final LocalDate TEST_TO_DATE = LocalDate.of(2020, 9, 13);
	private static final LocalDate TEST_CREATE_DATE = LocalDate.of(2020, 9, 2);
		
	private JDBCParkDAO parkDao;
	private JDBCCampgroundDAO campgroundDao;
	private JDBCSiteDAO siteDao;
	private JDBCReservationDAO reservationDao;

	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		dataSource.setAutoCommit(false);
	}

	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}

	@Before
	public void setup() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sqlInsertCampground = "INSERT INTO campground (campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee) "
				+ " VALUES (?, ?, ?, ?, ?,  '12.34'::float8::numeric::money)";
		jdbcTemplate.update(sqlInsertCampground, TEST_CAMPGROUND_ID, TEST_PARK_ID, TEST_CAMPGROUND_NAME,
				TEST_OPEN_FROM_MONTH, TEST_OPEN_TO_MONTH);

		String sqlInsertSite = "INSERT INTO site (site_id, campground_id, site_number, max_occupancy, accessible, max_rv_length, utilities) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sqlInsertSite, TEST_SITE_ID, TEST_CAMPGROUND_ID, TEST_SITE_NUMBER, TEST_MAX_OCCUPANCY,
				TEST_ACCESSIBLE, TEST_MAX_RV_LENGTH, TEST_UTILITIES);

		String sqlInsertReservation = "INSERT INTO reservation (reservation_id, site_id, name, from_date, to_date, create_date) "
				+ " VALUES (?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sqlInsertReservation, TEST_RESERVATION_ID, TEST_SITE_ID, TEST_RESERVATION_NAME,
				TEST_FROM_DATE, TEST_TO_DATE, TEST_CREATE_DATE);

		parkDao = new JDBCParkDAO(dataSource);
		campgroundDao = new JDBCCampgroundDAO(dataSource);
		siteDao = new JDBCSiteDAO(dataSource);
		reservationDao = new JDBCReservationDAO(dataSource);
	}

	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	protected DataSource getDataSource() {
		return dataSource;
	}

	@Test
	public void get_all_parks_test() {
		List<Park> parks = new ArrayList<Park>();
		parks = parkDao.getAllParks();
		assertNotNull(parks);
		assertTrue(parks.size() >= 1);
	}

	@Test
	public void get_all_campgrounds_test() {
		List<Campground> campgrounds = new ArrayList<Campground>();
		campgrounds = campgroundDao.getAllCampgrounds((long) 1);
		assertNotNull(campgrounds);
		assertTrue(campgrounds.size() >= 2);
	}

	@Test
	public void get_campground_by_id_test() {
		Campground campground = new Campground();
		campground = campgroundDao.getCampgroundById(1000L);
		assertNotNull(campground);
		assertEquals(campground.getName(), TEST_CAMPGROUND_NAME);
		assertEquals(campground.getCampgroundId(), TEST_CAMPGROUND_ID);
		assertEquals(campground.getDailyFee(), 12.34, 0.00);
		assertEquals(campground.getParkId(), TEST_PARK_ID);
	}

	@Test
	public void get_total_cost_of_stay_test() {
		double totalCost = campgroundDao.getTotalCostOfStay(1000, 1);
		assertEquals(totalCost, 12.34, 0.00);
		double totalCostTwoDays = campgroundDao.getTotalCostOfStay(1000, 2);
		assertEquals(totalCostTwoDays, 24.68, 0.00);
	}
	
	
	@Test
	public void create_new_reservation_test() {
		Reservation reservation = new Reservation();
		long nextId = reservationDao.createNewReservation(TEST_SITE_ID, TEST_RESERVATION_NAME, TEST_FROM_DATE,
				TEST_TO_DATE);
		reservation = reservationDao.getReservationById(nextId);
		assertNotNull(reservation);
		assertEquals(reservation.getName(), TEST_RESERVATION_NAME);
	}

	@Test
	public void get_reservation_by_id_test() {
		Reservation reservation = new Reservation();
		reservation = reservationDao.getReservationById(TEST_RESERVATION_ID);
		assertNotNull(reservation);
		assertEquals(reservation.getReservationId(), TEST_RESERVATION_ID);
	}

	@Test
	public void delete_reservation_test() {
		Reservation reservation = new Reservation();
		long nextId = reservationDao.createNewReservation(TEST_SITE_ID, TEST_RESERVATION_NAME, TEST_FROM_DATE,
				TEST_TO_DATE);
		reservation = reservationDao.getReservationById(nextId);
		assertNotNull(reservation);
		reservationDao.deleteReservation(nextId);
		assertNull(reservationDao.getReservationById(nextId));
	}

	@Test
	public void get_site_by_id_test() {
		Site site = new Site();
		site = siteDao.getSiteById(TEST_SITE_ID);
		assertNotNull(site);
		assertEquals(site.getSiteId(), TEST_SITE_ID);
	}

	@Test
	public void get_available_sites_test() {
		List<Site> availableSites = new ArrayList<Site>();
		availableSites = siteDao.getAvailableSites(TEST_CAMPGROUND_ID, TEST_FROM_DATE, TEST_TO_DATE);
		assertNotNull(availableSites);
		assertEquals(availableSites.size(), 0);
	}

}
