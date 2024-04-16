package dev.wsollers.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogFactory {

  public static Logger getLogger(Class<?> clazz) {
    return LoggerFactory.getLogger(clazz);
  }

  public static void testLogger() {
    Logger logger = getLogger(LogFactory.class);
    logger.error("Test log statement");
  }
}
