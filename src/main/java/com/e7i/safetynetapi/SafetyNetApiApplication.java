package com.e7i.safetynetapi;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.e7i.safetynetapi.dao.DaoFactory;
import com.e7i.safetynetapi.data.UserDataFactory;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class SafetyNetApiApplication {
	
	private static final Logger logger = LogManager.getLogger("AppBoot");
	
	public static void main(String[] args) {

		SpringApplication.run(SafetyNetApiApplication.class, args);
	}
		
	@PostConstruct
	public void init() throws IOException {
		DaoFactory df = new DaoFactory();
		df.createFileFromUrl("https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json");
		df.createDao(new File("src/main/resources/data.json"));
		UserDataFactory.createUsersModel();
		logger.info("SafetyNetAPI Initialized");
	}
}
