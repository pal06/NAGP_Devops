package com.nagarro.automation.reporting;

/**
 * LoggerLoad  is for logging the details
 *
 * @author Palvika
 */

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerLoad {

	public static Logger config(String classname) {
		Logger log = Logger.getLogger(classname);
		String log_properties = System.getProperty("user.dir");
		log.info(log_properties);
		String log4jConfPath = log_properties + "/src/main/resources/Logger/log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		return log;
	}

	static {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		System.setProperty("current.date.time", dateFormat.format(new Date()));
	}

}
