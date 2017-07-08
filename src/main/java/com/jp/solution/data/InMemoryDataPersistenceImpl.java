package com.jp.solution.data;

import com.jp.solution.model.report.SaleRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class will be used as a data cache for all sale types.
 *
 * @author praveen.nair, created on 04/07/2017.
 */
public class InMemoryDataPersistenceImpl implements DataPersistenceInterface {

  /**
   * The Constant LOGGER.
   */
  private static final Logger LOGGER = LogManager.getLogger(InMemoryDataPersistenceImpl.class);

  /**
   * using concurrent hash map to avoid possible threading problems, just pre-empting.
   */
  private ConcurrentHashMap<String, SaleRecord> saleRecordMap;

  private static InMemoryDataPersistenceImpl instance = new InMemoryDataPersistenceImpl();

  /**
   * To avoid accidental instantiation.
   */
  private InMemoryDataPersistenceImpl() {
    this.saleRecordMap = new ConcurrentHashMap<>(10);
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
  public void putData(String saleType, SaleRecord saleRecord) {
    getSaleRecordMap().put(saleType, saleRecord);
  }

  @Override
  public SaleRecord getData(String saleType) {
    return getSaleRecordMap().get(saleType);
  }

  @Override
  public Collection<SaleRecord> getAllData() {
    return getSaleRecordMap().values();
  }


  @Override
  public void clearData() {
    getSaleRecordMap().clear();
  }

  @Override
  public int getNumberOfMessagesReceived() {
    return getSaleRecordMap().values().stream().mapToInt(SaleRecord::getNumberOfMessages).sum();
  }

  public ConcurrentHashMap<String, SaleRecord> getSaleRecordMap() {
    return saleRecordMap;
  }

  public void setSaleRecordMap(ConcurrentHashMap<String, SaleRecord> saleRecordMap) {
    this.saleRecordMap = saleRecordMap;
  }
}
