package com.jp.solution.data;

import com.jp.solution.model.report.SaleRecord;

import java.util.Collection;

/**
 * Interface that can be used for implementing the data persistence.
 *
 * @author praveen.nair, created on 05/07/2017.
 */
public interface DataPersistenceInterface {

  /**
   * Put the data into the data store.
   *
   * @param saleType the key to persist data for.
   * @param saleRecord the data to persist
   */
  void putData(String saleType, SaleRecord saleRecord);

  /**
   * Get all the data from the data store.
   *
   * @param saleType the key for which the data is required.
   * @return data for saleType.
   */
  SaleRecord getData(String saleType);

  /**
   * Get all the data from the data store.
   *
   * @return list of data.
   */
  Collection<SaleRecord> getAllData();

  /**
   * Clear the data from the data store.
   */
  void clearData();

  /**
   * return the count of messages received so far.
   *
   * @return the number of messages received so far.
   */
  int getNumberOfMessagesReceived();

}
