package com.jp.solution.report;

import static org.junit.Assert.*;

import com.jp.solution.model.message.Operation;
import com.jp.solution.model.report.AdjustmentRecord;
import com.jp.solution.model.report.SaleRecord;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to test printing of reports.
 *
 * Created by prave_000 on 08/07/2017.
 */
public class ReportGeneratorTest {

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void generateIntermediateReport() throws Exception {
    Collection<SaleRecord> saleRecords = createSaleRecord();
    ReportGenerator.generateIntermediateReport(saleRecords);
  }

  private Collection<SaleRecord> createSaleRecord() {
    Collection<SaleRecord> saleRecords = new ArrayList<>(10);
    SaleRecord saleRecord1 = new SaleRecord("Apple", 10, 2, 2, new LinkedList<>());
    saleRecords.add(saleRecord1);
    SaleRecord saleRecord2 = new SaleRecord("Orange", 20, 4, 4, new LinkedList<>());
    saleRecords.add(saleRecord2);
    LinkedList<AdjustmentRecord> adjustmentRecords = new LinkedList<>();
    adjustmentRecords.add(new AdjustmentRecord(Operation.ADD, 10));
    adjustmentRecords.add(new AdjustmentRecord(Operation.SUBTRACT, 5));
    adjustmentRecords.add(new AdjustmentRecord(Operation.SUBTRACT, 5));
    SaleRecord saleRecord3 = new SaleRecord("Grapes", 20, 4, 4, adjustmentRecords);
    saleRecords.add(saleRecord3);
    return saleRecords;
  }

  @Test
  public void generateFinalReport() throws Exception {
    Collection<SaleRecord> saleRecords = createSaleRecord();
    ReportGenerator.generateFinalReport(saleRecords);
  }

}