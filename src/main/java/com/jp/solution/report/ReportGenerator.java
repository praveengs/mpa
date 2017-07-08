package com.jp.solution.report;

import com.jp.solution.model.report.SaleRecord;
import java.util.Collection;

/**
 * This class exposes static methods that can be used to print reports.
 *
 * Created by prave_000 on 08/07/2017.
 */
public class ReportGenerator {

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

  /**
   * Method to generate the final report. This only includes the adjustments made to the sales
   * for each sale type.
   *
   * @param saleRecords the sale records.
   */
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
