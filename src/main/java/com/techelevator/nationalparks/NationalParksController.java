package com.techelevator.nationalparks;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class NationalParksController {

	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		NationalParksController application = new NationalParksController(dataSource);
		application.run();
	}

	public NationalParksController(DataSource datasource) {
		// create your DAOs here
	}
	
	public void run() {
		
	}
}
