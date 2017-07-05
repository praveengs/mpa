package com.jp.solution.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * This class will be used as a data cache for all sale types.
 *
 * @author praveen.nair, created on 04/07/2017.
 */
public class InMemoryDataPersistenceImpl<AbstractMessageType> implements DataPersistenceInterface<AbstractMessageType> {
  /**
   * The Constant LOGGER.
   */
  private static final Logger LOGGER = LogManager.getLogger(InMemoryDataPersistenceImpl.class);

  /**
   * using concurrent hash map to avoid possible threading problems, just pre-empting.
   */
  private List<AbstractMessageType> messageList;

  private static InMemoryDataPersistenceImpl instance = new InMemoryDataPersistenceImpl();

  public InMemoryDataPersistenceImpl() {
    this.messageList = new ArrayList<>(10);
  }

  /**
   * Return the singleton instance of the class.
   * 
   * @return instance
   */
  public static InMemoryDataPersistenceImpl getInstance() {
    return instance;
  }

  @Override
  public void putData(AbstractMessageType message) {
    getMessageList().add(message);
  }

  @Override
  public List<AbstractMessageType> getAllData() {
    return getMessageList();
  }

  @Override
  public int getSize() {
    return getMessageList().size();
  }

  @Override
  public void clearData() {
    getMessageList().clear();
  }

  public List<AbstractMessageType> getMessageList() {
    return messageList;
  }

  public void setMessageList(List<AbstractMessageType> messageList) {
    this.messageList = messageList;
  }
}
