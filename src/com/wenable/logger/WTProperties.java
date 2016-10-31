package com.wenable.logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;

import com.wenable.logger.AppUtil.Environment;

public class WTProperties {
	/**
	 * 
	 */
	java.util.logging.Logger sysLogger = java.util.logging.Logger.getLogger(this.getClass().getName());

	private Environment environment = Environment.DEV;

	WTProperties() {
		readProps();
	}

	void readProps() {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			String filename = "./wtlogger.properties";
			input = this.getClass().getClassLoader().getResourceAsStream(filename);
			if (input == null) {
				sysLogger.log(Level.SEVERE, "Sorry, unable to find " + filename);
				return;
			}

			// load a properties file from class path, inside static method
			prop.load(input);

			// setting properties
			if (prop != null) {
				String env = prop.getProperty("env");
				setEnvironment(Environment.valueOf(env));
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

}
