package com.jp.solution.report;

import com.jp.solution.model.report.SaleRecord;
import java.util.Collection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by prave_000 on 08/07/2017.
 */
public class ReportGenerator {

  /**
   * The Constant LOGGER.
   */
  private static final Logger LOGGER = LogManager.getLogger(ReportGenerator.class);

  /**
   * To avoid accidental instantiation.
   */
  private ReportGenerator() {
  }

  /**
   * Generate intermediate report.
   * This only prints out the number of sales for each product type and its total value.
   *
   * @param saleRecords sale records.
   */
  public static void generateIntermediateReport(Collection<SaleRecord> saleRecords) {
    String format = "%-20s%-10s%-10s%n";
    System.out.printf(format, "Sale Type", "# Sales", "Total Value of Sales");
    saleRecords.forEach(saleRecord -> {
      System.out.printf(format, saleRecord.getSaleType(), saleRecord.getNumSales(),
          saleRecord.getTotalValue());
    });
  }

  public static void generateFinalReport(Collection<SaleRecord> saleRecords) {
    String format = "%-10s%-10s%n";

    saleRecords.forEach(saleRecord -> {
      System.out.println("-------------------------------------------------------");
      System.out.println("Adjustments for Sale Type: " + saleRecord.getSaleType());

      if (saleRecord.getAdjustments().size() > 0) {
        System.out.printf(format, "Operation", "Amount");
        saleRecord.getAdjustments().forEach(adjustmentRecord -> {
          System.out.printf(format, adjustmentRecord.getOperation().name(),
              adjustmentRecord.getAdjustmentInPence(),
              saleRecord.getTotalValue());
        });

      } else {
        System.out.println("No adjustments made during the period");
      }
      System.out.println("-------------------------------------------------------");
    });
  }
}
