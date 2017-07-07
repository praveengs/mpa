package com.jp.solution.controller;

import com.jp.solution.data.DataPersistenceInterface;
import com.jp.solution.data.InMemoryDataPersistenceImpl;
import com.jp.solution.model.Sale;
import com.jp.solution.model.message.MessageType1;
import com.jp.solution.model.message.MessageType2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for message processing.
 * 
 * @author praveen.nair, created on 05/07/2017.
 */
public class MessageProcessorTest {

  /**
   * The Constant LOGGER.
   */
  private static final Logger LOGGER = LogManager.getLogger(MessageProcessorTest.class);


  private MessageProcessor messageProcessor;
  private DataPersistenceInterface dataPersistenceInterface;

  @Before
  public void setUp() throws Exception {
    dataPersistenceInterface = InMemoryDataPersistenceImpl.getInstance();
    messageProcessor = new MessageProcessor(dataPersistenceInterface);
    dataPersistenceInterface.clearData();
  }

  @After
  public void tearDown() throws Exception {
    dataPersistenceInterface.clearData();
    messageProcessor = null;
  }

  @Test
  public void testRecordAMessageType1Sale() throws Exception {
    Sale sale = new Sale("Apple", 10);
    LOGGER.debug(sale);
    MessageType1 message = new MessageType1(sale);
    messageProcessor.processMessage(message);
    Assert.assertEquals(1, InMemoryDataPersistenceImpl.getInstance().getData(sale.getProductType()).getNumSales());
    Assert.assertEquals(10, InMemoryDataPersistenceImpl.getInstance().getData(sale.getProductType()).getTotalValue(), 0);
  }

  /*@Test
  public void testRecordAMessageType2Sale() throws Exception {
    Sale sale = new Sale("Apple", 10);
    LOGGER.debug(sale);
    MessageType2 message = new MessageType2(sale, 2);
    Sale expectedSale = new Sale("Apple", 10);
    MessageType2 expectedMessage = new MessageType2(expectedSale, 2);
    messageProcessor.processMessage(message);
    Assert.assertEquals(1, InMemoryDataPersistenceImpl.getInstance().getSize());
    Assert.assertTrue(InMemoryDataPersistenceImpl.getInstance().getAllData().get(0).equals(expectedMessage));
  }*/

}
