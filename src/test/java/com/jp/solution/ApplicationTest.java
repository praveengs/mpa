package com.jp.solution;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author praveen.nair, created on 04/07/2017.
 */
public class ApplicationTest {

  /**
   * The Constant LOGGER.
   */
  private static final Logger LOGGER = LogManager.getLogger(ApplicationTest.class);

  @Before
  public void setUp() throws Exception {}

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testRecordASale() {}

  @Test
  public void testProcessSingleMessage() {}

  @Test
  public void testLogReportAfter10thMessage() {}

  @Test
  public void testAppPauseAfter50Messages() {}

  @Test
  public void testAppReportAfter50Messages() {}

}
