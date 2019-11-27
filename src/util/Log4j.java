package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Log4j��
 * 
 * @author chenshaolei 2019��11��27�� ����2:48:17
 */
public class Log4j {

	/**
	 * ��ʼ��Logger
	 */
	Log4j() {
		// ����Log4j��־
	}

	/**
	 * д��INFO�������־
	 * 
	 * @param info
	 */
	public static void LoggerINFO(String info) {
		Logger logger = LogManager.getLogger(Class.class);
		logger.info(info);
	}

	/**
	 * д��ERROE�������־
	 * 
	 * @param error
	 */
	public static void LoggerERROR(String error) {
		Logger logger = LogManager.getLogger(Class.class);
		logger.error(error);
	}

	/**
	 * д��WARN�������־
	 * 
	 * @param warn
	 */
	public static void LoggerWARN(String warn) {
		Logger logger = LogManager.getLogger(Class.class);
		logger.warn(warn);
	}
}
