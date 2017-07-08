package com.jp.solution.controller;

import com.jp.solution.data.DataPersistenceInterface;
import com.jp.solution.data.InMemoryDataPersistenceImpl;
import com.jp.solution.exception.ApplicationException;
import com.jp.solution.model.Sale;
import com.jp.solution.model.message.MessageType1;
import com.jp.solution.model.message.MessageType2;
import com.jp.solution.model.message.MessageType3;
import com.jp.solution.model.message.Operation;
import com.jp.solution.model.report.AdjustmentRecord;
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
    Assert.assertEquals(1,
        InMemoryDataPersistenceImpl.getInstance().getData(sale.getProductType()).getNumSales());
    Assert.assertEquals(10,
        InMemoryDataPersistenceImpl.getInstance().getData(sale.getProductType()).getTotalValue(),
        0);
  }

  @Test
  public void testRecordMultipleMessageType1Sale() throws Exception {
    Sale appleSale = new Sale("Apple", 10);
    LOGGER.debug(appleSale);
    MessageType1 message = new MessageType1(appleSale);
    messageProcessor.processMessage(message);
    Sale orangeSale = new Sale("Orange", 20);
    LOGGER.debug(orangeSale);
    MessageType1 orangeMessage = new MessageType1(orangeSale);
    messageProcessor.processMessage(orangeMessage);
    Assert.assertEquals(1,
        InMemoryDataPersistenceImpl.getInstance().getData(appleSale.getProductType())
            .getNumSales());
    Assert.assertEquals(10,
        InMemoryDataPersistenceImpl.getInstance().getData(appleSale.getProductType())
            .getTotalValue(),
        0);
    Assert.assertEquals(1,
        InMemoryDataPersistenceImpl.getInstance().getData(orangeSale.getProductType())
            .getNumSales());
    Assert.assertEquals(20,
        InMemoryDataPersistenceImpl.getInstance().getData(orangeSale.getProductType())
            .getTotalValue(),
        0);
  }

  @Test
  public void testRecordAMessageType2Sale() throws Exception {
    Sale sale = new Sale("Apple", 10);
    LOGGER.debug(sale);
    MessageType2 message = new MessageType2(sale, 2);
    messageProcessor.processMessage(message);
    Assert.assertEquals(2,
        InMemoryDataPersistenceImpl.getInstance().getData(sale.getProductType()).getNumSales());
    Assert.assertEquals(20,
        InMemoryDataPersistenceImpl.getInstance().getData(sale.getProductType()).getTotalValue(),
        0);
  }

  @Test
  public void testRecordMessageType1and2Sale() throws Exception {
    Sale sale = new Sale("Apple", 10);
    LOGGER.debug(sale);
    MessageType1 message = new MessageType1(sale);
    messageProcessor.processMessage(message);
    Sale sale2 = new Sale("Apple", 5);
    MessageType2 message2 = new MessageType2(sale2, 2);
    messageProcessor.processMessage(message2);
    Assert.assertEquals(3,
        InMemoryDataPersistenceImpl.getInstance().getData(sale.getProductType()).getNumSales());
    Assert.assertEquals(20,
        InMemoryDataPersistenceImpl.getInstance().getData(sale.getProductType()).getTotalValue(),
        0);
  }

  @Test
  public void testRecordAMessageType3SaleAddWithNoExistingData() throws Exception {
    Sale sale = new Sale("Apple", 10);
    LOGGER.debug(sale);
    MessageType3 message = new MessageType3(sale, Operation.ADD);
    messageProcessor.processMessage(message);
    Assert.assertEquals(0,
        InMemoryDataPersistenceImpl.getInstance().getData(sale.getProductType()).getNumSales());
    Assert.assertEquals(0,
        InMemoryDataPersistenceImpl.getInstance().getData(sale.getProductType()).getTotalValue(),
        0);
  }

  @Test
  public void testRecordAMessageType3SaleMultiplyWithNoExistingData() throws Exception {
    Sale sale = new Sale("Apple", 10);
    LOGGER.debug(sale);
    MessageType3 message = new MessageType3(sale, Operation.MULTIPLY);
    messageProcessor.processMessage(message);
    Assert.assertEquals(0,
        InMemoryDataPersistenceImpl.getInstance().getData(sale.getProductType()).getNumSales());
    Assert.assertEquals(0,
        InMemoryDataPersistenceImpl.getInstance().getData(sale.getProductType()).getTotalValue(),
        0);
  }

  @Test
  public void testRecordAMessageType3SaleSubtractWithNoExistingData() throws Exception {
    Sale sale = new Sale("Apple", 10);
    LOGGER.debug(sale);
    MessageType3 message = new MessageType3(sale, Operation.SUBTRACT);
    messageProcessor.processMessage(message);
    Assert.assertEquals(0,
        InMemoryDataPersistenceImpl.getInstance().getData(sale.getProductType()).getNumSales());
    Assert.assertEquals(0,
        InMemoryDataPersistenceImpl.getInstance().getData(sale.getProductType()).getTotalValue(),
        0);
  }

  @Test
  public void testRecordAMessageType3SaleAddWithExistingData() throws Exception {
    Sale sale = new Sale("Apple", 10);
    LOGGER.debug(sale);
    MessageType1 message = new MessageType1(sale);
    messageProcessor.processMessage(message);
    Sale sale2 = new Sale("Apple", 5);
    MessageType2 message2 = new MessageType2(sale2, 2);
    messageProcessor.processMessage(message2);
    Sale sale3 = new Sale("Apple", 1);
    MessageType3 message3 = new MessageType3(sale3, Operation.ADD);
    messageProcessor.processMessage(message3);
    Assert.assertEquals(3,
        InMemoryDataPersistenceImpl.getInstance().getData(sale.getProductType()).getNumSales());
    Assert.assertEquals(23,
        InMemoryDataPersistenceImpl.getInstance().getData(sale.getProductType()).getTotalValue(),
        0);
    AdjustmentRecord expectedAdjustmentRecord = new AdjustmentRecord(Operation.ADD, 1);
    Assert.assertEquals(expectedAdjustmentRecord,
        InMemoryDataPersistenceImpl.getInstance().getData(sale.getProductType()).getAdjustments()
            .get(0));
  }

  @Test
  public void testRecordAMessageType3SaleSubtractWithExistingData() throws Exception {
    Sale sale = new Sale("Apple", 10);
    LOGGER.debug(sale);
    MessageType1 message = new MessageType1(sale);
    messageProcessor.processMessage(message);
    Sale sale2 = new Sale("Apple", 5);
    MessageType2 message2 = new MessageType2(sale2, 2);
    messageProcessor.processMessage(message2);
    Sale sale3 = new Sale("Apple", 1);
    MessageType3 message3 = new MessageType3(sale3, Operation.SUBTRACT);
    messageProcessor.processMessage(message3);
    Assert.assertEquals(3,
        InMemoryDataPersistenceImpl.getInstance().getData(sale.getProductType()).getNumSales());
    Assert.assertEquals(17,
        InMemoryDataPersistenceImpl.getInstance().getData(sale.getProductType()).getTotalValue(),
        0);
    AdjustmentRecord expectedAdjustmentRecord = new AdjustmentRecord(Operation.SUBTRACT, 1);
    Assert.assertEquals(expectedAdjustmentRecord,
        InMemoryDataPersistenceImpl.getInstance().getData(sale.getProductType()).getAdjustments()
            .get(0));
  }

  @Test
  public void testRecordAMessageType3SaleMultiplyWithExistingData() throws Exception {
    Sale sale = new Sale("Apple", 10);
    LOGGER.debug(sale);
    MessageType1 message = new MessageType1(sale);
    messageProcessor.processMessage(message);
    Sale sale2 = new Sale("Apple", 5);
    MessageType2 message2 = new MessageType2(sale2, 2);
    messageProcessor.processMessage(message2);
    Sale sale3 = new Sale("Apple", 2);
    MessageType3 message3 = new MessageType3(sale3, Operation.MULTIPLY);
    messageProcessor.processMessage(message3);
    Assert.assertEquals(3,
        InMemoryDataPersistenceImpl.getInstance().getData(sale.getProductType()).getNumSales());
    Assert.assertEquals(40,
        InMemoryDataPersistenceImpl.getInstance().getData(sale.getProductType()).getTotalValue(),
        0);
    AdjustmentRecord expectedAdjustmentRecord = new AdjustmentRecord(Operation.MULTIPLY, 2);
    Assert.assertEquals(expectedAdjustmentRecord,
        InMemoryDataPersistenceImpl.getInstance().getData(sale.getProductType()).getAdjustments()
            .get(0));
  }

  @Test
  public void testAppPauseAfter50Messages() throws ApplicationException {
    Sale sale;
    MessageType1 messageType1;
    MessageType2 messageType2;
    MessageType3 messageType3;
    sale = new Sale("Grapes", 2);
    messageType1 = new MessageType1(sale);
    messageProcessor.processMessage(messageType1);
    for (int i = 0; i < 49; i++) {
      if (i % 2 == 0) {
        sale = new Sale("Apple", 10);
        messageType1 = new MessageType1(sale);
        messageProcessor.processMessage(messageType1);
      } else if (i % 3 == 0) {
        sale = new Sale("Orange", 5);
        messageType2 = new MessageType2(sale, 2);
        messageProcessor.processMessage(messageType2);
      } else {
        sale = new Sale("Grapes", 2);
        messageType3 = new MessageType3(sale, Operation.ADD);
        messageProcessor.processMessage(messageType3);
      }
    }
    // ensure app is paused
    Assert.assertTrue("App is not paused", messageProcessor.isMessageProcessingPaused());
    Assert.assertEquals("Incorrect message count", 50, dataPersistenceInterface.getNumberOfMessagesReceived());
    sale = new Sale("Apple", 10);
    messageType1 = new MessageType1(sale);
    messageProcessor.processMessage(messageType1);
    // The additional message should be ignored and not processed
    Assert.assertEquals("Additional message not ignored", 50, dataPersistenceInterface.getNumberOfMessagesReceived());
  }

}
