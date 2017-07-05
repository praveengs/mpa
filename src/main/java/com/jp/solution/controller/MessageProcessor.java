package com.jp.solution.controller;

import com.jp.solution.common.AppConstants;
import com.jp.solution.data.DataPersistenceInterface;
import com.jp.solution.data.InMemoryDataPersistenceImpl;
import com.jp.solution.exception.ApplicationException;
import com.jp.solution.model.Sale;
import com.jp.solution.model.message.AbstractMessageType;
import com.jp.solution.model.message.MessageType1;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
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
    switch (message.getMessageType()) {
      case AppConstants.MESSGE_TYPE_1:
        processMessageType1((MessageType1) message);
        break;
      case AppConstants.MESSGE_TYPE_2:
        break;
      case AppConstants.MESSGE_TYPE_3:
        break;
      default:
        LOGGER.error("Invalid message type encountered: " + message.getMessageType());
        throw new ApplicationException(ApplicationException.INVALID_MESSAGE_TYPE + ": " + message.getMessageType());
    }
  }

  /**
   * Handle message type 1.
   * 
   * @param message message type 1 .
   */
  private void processMessageType1(MessageType1 message) {
    getDataPersistenceInterface().putData(message);
  }

  public DataPersistenceInterface getDataPersistenceInterface() {
    return dataPersistenceInterface;
  }

  public void setDataPersistenceInterface(DataPersistenceInterface dataPersistenceInterface) {
    this.dataPersistenceInterface = dataPersistenceInterface;
  }
}