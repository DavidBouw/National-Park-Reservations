package com.techelevator.nationalparks;

import com.techelevator.nationalparks.model.*;
import com.techelevator.nationalparks.model.jdbc.*;

import javax.sql.DataSource;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.dbcp2.BasicDataSource;

public class NationalParksController {

	private static final String[] PARK_MENU = {"Select a Park for Further Details"};
	private static final String[] PARK_INFORMATION = {"%s\nLocation: %s\nEstablished: %s\n"
			+ "Area: %d sq km\nAnnual Visitors: %s\n\n%s"};
	private static final String[] PARK_OPTIONS = {"Select a Command", "1) View Campgrounds",
			"2) Search for Reservation", "3) Return to Previous Screen"};

	private static final String[] CAMPGROUND_MENU = {"%s\n\nNo.\tName\tOpen\tClose\tDaily Fee"};
	private static final String[] CAMPGROUND_OPTIONS = {"Select a Command", 
			"1) Search for Available Reservation", "2) Return to Previous Screen"};
	private static final String[] CAMPGROUND_PROMPT = {"Which campground (enter 0 to cancel)? "};
	private static final String[] CAMPGROUND_ARRIVAL_DATE = {"What is the arrival date? "};
	private static final String[] CAMPGROUND_DEPARTURE_DATE = {"What is the departure date? "};

	private static final String[] SITES_AVAILABLE = {"Results Matching Your Search Criteria"};
	private static final String[] SITES_COLUMNS = {"Site No.", "Max Occup.", "Accessible?", "Max RV Length", "Utility", "Cost"};

	private static final String[] RESERVATION_SITE_PROMPT = {"Which site should be reserved (enter 0 to cancel)? "};
	private static final String[] RESERVATION_NAME_PROMPT = {"What name should the reservation be made under? "};
	
	private ParkDAO parkDAO;
	private CampgroundDAO campgroundDAO;
	private SiteDAO siteDAO;
	private ReservationDAO reservationDAO;

	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		NationalParksController application = new NationalParksController(dataSource);
		application.run();
	}

	public NationalParksController(DataSource datasource) {
		parkDAO = new JDBCParkDAO(datasource);
		campgroundDAO = new JDBCCampgroundDAO(datasource);
		siteDAO = new JDBCSiteDAO(datasource);
		reservationDAO = new JDBCReservationDAO(datasource);
	}
	
	private void buildParkOptions() {
		List<Park> parks = new ArrayList<Park>();
		String[] options = new String[parks.size()];
		
		for(Park aPark : parks) {
			options[(int)aPark.getPark_id()] = String.format("%d) %s",
					aPark.getPark_id(), aPark.getName());
		}
	}
	
	public void run() {
		
	}

	private void displayMenuOptions(String[] options) {
		for(String option : options) {
			System.out.println(option);
		}
	}
}
