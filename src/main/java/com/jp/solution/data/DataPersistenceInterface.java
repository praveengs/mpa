package com.jp.solution.data;

import java.util.List;

/**
 * Interface that can be used for implementing the data persistence.
 * 
 * @author praveen.nair, created on 05/07/2017.
 */
public interface DataPersistenceInterface<T> {
  /**
   * Put the data into the data store.
   * 
   * @param t the data to persist
   */
  void putData(T t);

  /**
   * Get all the data from the data store.
   *
   * @return list of data.
   */
  List<T> getAllData();

  /**
   * Get the size of the data stored so far.
   * 
   * @return size of the data.
   */
  int getSize();

  /**
   * Clear the data from the data store.
   */
  void clearData();

}
