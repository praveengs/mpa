package com.jp.solution.data;

import com.jp.solution.model.report.SaleRecord;
import java.util.LinkedList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the in memory data persistence.
 *
 * Created by prave_000 on 08/07/2017.
 */
public class InMemoryDataPersistenceImplTest {

  private DataPersistenceInterface dataPersistenceInterface;

  @Before
  public void setUp() throws Exception {
    dataPersistenceInterface = InMemoryDataPersistenceImpl.getInstance();
    dataPersistenceInterface.clearData();
  }

  @After
  public void tearDown() throws Exception {
    dataPersistenceInterface.clearData();
  }

  @Test
  public void putData() throws Exception {
    SaleRecord appleSaleRecord = new SaleRecord("Apple", 0, 0, 0, new LinkedList<>());
    dataPersistenceInterface.putData("Apple", appleSaleRecord);
    Assert.assertEquals(1, dataPersistenceInterface.getAllData().size());
  }

  @Test
  public void getData() throws Exception {
    SaleRecord appleSaleRecord = new SaleRecord("Apple", 0, 0, 0, new LinkedList<>());
    dataPersistenceInterface.putData("Apple", appleSaleRecord);
    Assert.assertEquals(appleSaleRecord, dataPersistenceInterface.getData("Apple"));
  }

  @Test
  public void clearData() throws Exception {
    SaleRecord appleSaleRecord = new SaleRecord("Apple", 0, 0, 0, new LinkedList<>());
    dataPersistenceInterface.putData("Apple", appleSaleRecord);
    dataPersistenceInterface.clearData();
    Assert.assertEquals(0, dataPersistenceInterface.getAllData().size());
  }

  @Test
  public void getNumberOfMessagesReceivedWhenNoMessages() throws Exception {
    Assert.assertEquals(0, dataPersistenceInterface.getNumberOfMessagesReceived());
  }

  @Test
  public void getNumberOfMessagesReceived() throws Exception {
    SaleRecord appleSaleRecord = new SaleRecord("Apple", 0, 0, 1, new LinkedList<>());
    dataPersistenceInterface.putData("Apple", appleSaleRecord);
    SaleRecord orangeSaleRecord = new SaleRecord("Orange", 0, 0, 3, new LinkedList<>());
    dataPersistenceInterface.putData("Orange", orangeSaleRecord);
    Assert.assertEquals(4, dataPersistenceInterface.getNumberOfMessagesReceived());
  }

}