package com.cognizant.ormlearn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

@SpringBootApplication
public class OrmLearnApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
	private static CountryService countryService;
	
	public static void main(String[] args) throws CountryNotFoundException {
		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
		LOGGER.info("Inside main");
		
		countryService = context.getBean(CountryService.class);
		/*
		testGetAllCountries();
		testFindCountryByCode();
		testAddCountry();
		testUpadateCountry();
		testDeleteCountry();
		*/
		testFindByNameLike();
		testStartsWith();
	}
	
	public static void testGetAllCountries() {
		LOGGER.info("Start");
		List<Country> countries = countryService.getAllCountries();
		LOGGER.debug("countries={}", countries);
		LOGGER.info("End");
	}

	public static void testFindCountryByCode() throws CountryNotFoundException {
		LOGGER.info("Start");
		Country country = countryService.findCountryByCode("IN");
		LOGGER.debug("Country:{}", country);
		LOGGER.info("End");
	}
	
	public static void testAddCountry() throws CountryNotFoundException{
		LOGGER.info("Start");
		Country country1 = new Country();
		country1.setCode("NC");
		country1.setName("New Country");
		countryService.addCountry(country1);
		Country country2 = countryService.findCountryByCode("NC");
		LOGGER.debug("Country:{}", country2);
		LOGGER.info("End");
	}
	
	public static void testUpadateCountry() throws CountryNotFoundException {
		LOGGER.info("Start");
		countryService.updateCountry("NC", "Newly Created Country");
		LOGGER.info("NC updated");
		Country country = countryService.findCountryByCode("NC");
		LOGGER.debug("Country:{}", country);
		LOGGER.info("End");
	}
	
	public static void testDeleteCountry() {
		LOGGER.info("Start");
		countryService.deleteCountry("NC");
		LOGGER.info("NC deleted");
		testGetAllCountries();
		LOGGER.info("End");
	}
	
	public static void testFindByNameLike() {
		LOGGER.info("Start");
		List<Country> countries = countryService.findByNameLike("%ou%");
		LOGGER.debug("Country:{}", countries);
		LOGGER.info("End");
	}
	
	public static void testStartsWith() {
		LOGGER.info("Start");
		List<Country> countries = countryService.startsWith('Z');
		LOGGER.debug("Country:{}", countries);
		LOGGER.info("End");
	}
}
