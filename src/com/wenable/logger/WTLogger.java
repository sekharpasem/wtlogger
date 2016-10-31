package com.wenable.logger;

import java.util.logging.Level;

import org.apache.log4j.Logger;

import com.wenable.logger.AppUtil.Environment;
import com.wenable.logger.AppUtil.LogMode;

/**
 * 
 * @author Shekar
 *
 */
public class WTLogger {
	/**
	 * 
	 */
	java.util.logging.Logger sysLogger = java.util.logging.Logger.getLogger(this.getClass().getName());
	/**
	 * log4j logger
	 */
	private Logger logger = null;
	/**
	 * properties read from wtlogger.properties file
	 */
	private static WTProperties properties = null;
	/**
	 * deployment environment mentioned in wtlogger.properties file
	 */
	private Environment environment = Environment.DEV;

	/**
	 * private constructor
	 */
	private WTLogger() {

	}

	/**
	 * instance block
	 */
	static {
		properties = new WTProperties();
	}

	/**
	 * 
	 * @param <T>
	 * @param clazz
	 */
	private <T> WTLogger(Class<T> clazz) {
		this.environment = properties.getEnvironment();
		sysLogger.log(Level.INFO, "Current wtlogger enviroment is " + environment);
		this.logger = Logger.getLogger(clazz);
	}

	/**
	 * 
	 * @param <T>
	 * @param clazz
	 */
	private <T> WTLogger(Class<T> clazz, Environment env) {
		this.environment = env;
		sysLogger.log(Level.INFO, "Current wtlogger enviroment is " + environment);
		this.logger = Logger.getLogger(clazz);
	}

	/**
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> WTLogger getLogger(Class<T> clazz) {
		return new WTLogger(clazz);
	}

	/**
	 * @param env
	 * @param clazz
	 * @return
	 */
	public static <T> WTLogger getLogger(Class<T> clazz, Environment env) {
		return new WTLogger(clazz, env);
	}

	/**
	 * 
	 * @param message
	 */
	public void info(Object message) {

		logger.info(message);
	}

	/**
	 * 
	 * @param message
	 */
	public void error(Object message) {
		logger.error(message);
	}

	/**
	 * 
	 * @param message
	 */
	public void debug(Object message) {
		logger.debug(message);
	}

	/**
	 * 
	 * @param message
	 * @param environment
	 */
	public void info(Object message, Environment environment) {
		switchEnvAndLog(message, environment, LogMode.INFO);
	}

	/**
	 * 
	 * @param message
	 * @param environment
	 */
	public void error(Object message, Environment environment) {
		logger.error(message);
	}

	/**
	 * 
	 * @param message
	 * @param environment
	 */
	public void debug(Object message, Environment environment) {
		logger.debug(message);
	}

	/**
	 * 
	 * @param message
	 * @param env
	 * @param mode
	 */
	private void switchEnvAndLog(Object message, Environment env, LogMode mode) {
		if (env.equals(environment)) {
			switch (mode) {
			case DEBUG:
				logger.debug(message);
				break;
			case ERROR:
				logger.error(message);
				break;
			case INFO:
				logger.info(message);
				break;
			case FATAL:
				logger.fatal(message);
				break;
			case TRACE:
				logger.trace(message);
				break;

			default:
				break;
			}
		}

	}
}
