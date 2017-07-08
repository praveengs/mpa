package com.jp.solution.controller;

import com.jp.solution.common.AppConstants;
import com.jp.solution.data.DataPersistenceInterface;
import com.jp.solution.exception.ApplicationException;
import com.jp.solution.model.message.AbstractMessageType;
import com.jp.solution.model.message.MessageType1;
import com.jp.solution.model.message.MessageType2;
import com.jp.solution.model.message.MessageType3;
import com.jp.solution.model.report.AdjustmentRecord;
import com.jp.solution.model.report.SaleRecord;
import com.jp.solution.report.ReportGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

/**
 * Class to process incoming messages.
 *
 * @author praveen.nair, created on 05/07/2017.
 */
public class MessageProcessor {

  /**
   * The Constant LOGGER.
   */
  private static final Logger LOGGER = LogManager.getLogger(MessageProcessor.class);

  /**
   * The data persistence implementation instance.
   */
  private DataPersistenceInterface dataPersistenceInterface;

  /**
   * boolean to indicate if message processing is paused.
   */
  private boolean isMessageProcessingPaused;

  /**
   * Constructor to initialise the required components.
   *
   * @param dataPersistenceInterface the data persistence impl.
   */
  public MessageProcessor(DataPersistenceInterface dataPersistenceInterface) {
    setDataPersistenceInterface(dataPersistenceInterface);
  }

  /**
   * This method processes the incoming message, and manipulates the data if required.
   *
   * @param message the incoming message.
   */
  public void processMessage(AbstractMessageType message) throws ApplicationException {
    if (!isMessageProcessingPaused()) {
      // limit is not reached, so continue processing messages
      String saleTypeForMessage = message.getSale().getProductType();
      SaleRecord saleRecord = retrieveOrCreateSaleRecordForType(saleTypeForMessage);

      // handle each message type
      switch (message.getMessageType()) {
        case AppConstants.MESSAGE_TYPE_1:
          processMessageType1((MessageType1) message, saleRecord);
          break;
        case AppConstants.MESSAGE_TYPE_2:
          processMessageType2((MessageType2) message, saleRecord);
          break;
        case AppConstants.MESSAGE_TYPE_3:
          processMessageType3((MessageType3) message, saleRecord);
          break;
        default:
          LOGGER.error("Invalid message type encountered: " + message.getMessageType());
          throw new ApplicationException(
              ApplicationException.INVALID_MESSAGE_TYPE + ": " + message.getMessageType());
      }
      // Increment the number of messages received for this type
      saleRecord.setNumberOfMessages(saleRecord.getNumberOfMessages() + 1);
      // if the message limit has hit the maximum, stop processing any further messages.
      int numberOfMessagesReceived = getDataPersistenceInterface().getNumberOfMessagesReceived();
      if (numberOfMessagesReceived == AppConstants.FINAL_REPORT_MESSAGES_INTERVAL) {
        LOGGER.info("Application pausing...");
        setMessageProcessingPaused(true);
      }
      // print report if required.
      checkAndPrintReportsIfRequired(numberOfMessagesReceived);
    } else {
      // limit reached. stop processing messages.
      LOGGER.warn("Not processing any more messages as the limit is reached");
    }
  }

  /**
   * Check the number of messages received against the intermediate/final report message limit.
   *
   * @param numberOfMessagesReceived number of messages received
   */
  private void checkAndPrintReportsIfRequired(int numberOfMessagesReceived) {
    if (numberOfMessagesReceived % AppConstants.INTERMEDIATE_REPORT_MESSAGES_INTERVAL == 0) {
      LOGGER.info("Generating the report at " + numberOfMessagesReceived + " messages");
      ReportGenerator.generateIntermediateReport(getDataPersistenceInterface().getAllData());
    }
    if (numberOfMessagesReceived == AppConstants.FINAL_REPORT_MESSAGES_INTERVAL) {
      LOGGER.info("Generating the final report at " + numberOfMessagesReceived + " messages");
      ReportGenerator.generateFinalReport(getDataPersistenceInterface().getAllData());
    }
  }

  /**
   * Handle message type 3.
   *
   * This message in itself add anything to sales. But for each sale so far done(numofsales), it
   * adds/subtracts/multiplies (based on the operation) the value for each of those sales.
   */
  private void processMessageType3(MessageType3 message,
      SaleRecord saleRecord) {
    switch (message.getOperation()) {
      case ADD:
        saleRecord.setTotalValue(
            saleRecord.getTotalValue() + (saleRecord.getNumSales() * message.getSale()
                .getProductValue()));
        break;
      case MULTIPLY:
        saleRecord.setTotalValue(
            saleRecord.getTotalValue() * message.getSale()
                .getProductValue());
        break;
      case SUBTRACT:
        saleRecord.setTotalValue(
            saleRecord.getTotalValue() - (saleRecord.getNumSales() * message.getSale()
                .getProductValue()));
        break;
    }
    // keep track of the adjustments made for the final report
    saleRecord.getAdjustments()
        .add(new AdjustmentRecord(message.getOperation(), message.getSale().getProductValue()));
  }

  /**
   * Handle message type 2.
   *
   * Add number of occurrences * value to the total amount.
   * Add the number of occurrences to the number of sales.
   *
   * @param message the message type 2
   */
  private void processMessageType2(MessageType2 message,
      SaleRecord saleRecord) {
    saleRecord.setTotalValue(
        saleRecord.getTotalValue() + (message.getNumOccurances() * message.getSale()
            .getProductValue()));
    saleRecord.setNumSales(saleRecord.getNumSales() + message.getNumOccurances());
  }

  /**
   * Handle message type 1.
   * Add 1 to the number of sales
   * Add the amount (assumed to be in pence), to the total value.
   * Update the record.
   *
   * @param message message type 1 .
   */
  private void processMessageType1(MessageType1 message,
      SaleRecord saleRecord) {
    saleRecord.setTotalValue(saleRecord.getTotalValue() + message.getSale().getProductValue());
    saleRecord.setNumSales(saleRecord.getNumSales() + 1);
  }

  /**
   * Retrieve the sale record for the associated sale type. If one doesn't exist, create one and put
   * it into the data store. And subsequently modify the data.
   *
   * @param saleTypeForMessage the sale type.
   * @return the sale record, new one if one for the sale type doesn't exist.
   */
  private SaleRecord retrieveOrCreateSaleRecordForType(String saleTypeForMessage) {
    SaleRecord saleRecord = getDataPersistenceInterface().getData(saleTypeForMessage);
    if (saleRecord == null) {
      saleRecord = new SaleRecord(saleTypeForMessage, 0, 0, 0, new LinkedList<>());
      getDataPersistenceInterface().putData(saleTypeForMessage, saleRecord);
    }
    return saleRecord;
  }

  public DataPersistenceInterface getDataPersistenceInterface() {
    return dataPersistenceInterface;
  }

  public void setDataPersistenceInterface(DataPersistenceInterface dataPersistenceInterface) {
    this.dataPersistenceInterface = dataPersistenceInterface;
  }

  public boolean isMessageProcessingPaused() {
    return isMessageProcessingPaused;
  }

  public void setMessageProcessingPaused(boolean messageProcessingPaused) {
    isMessageProcessingPaused = messageProcessingPaused;
  }
}
