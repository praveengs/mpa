package com.jp.solution.model.report;

import java.util.LinkedList;

/**
 * Record the sale and adjust values accordingly
 *
 * @author praveen.nair, created on 07/07/2017.
 */
public class SaleRecord {

  /**
   * The type of product for this sale record.
   */
  private String saleType;

  /**
   * The total value for this record
   */
  private double totalValue;

  /**
   * number of sales for this type
   */
  private int numSales;

  /**
   * The number of messages received for this product type.
   */
  private int numberOfMessages;

  /**
   * The list of adjustments done for this sale type.
   */
  private LinkedList<AdjustmentRecord> adjustments;

  /**
   * Sale record constructor.
   *
   * @param saleType sale type
   * @param totalValue the total value.
   * @param numSales num of sales recorded.
   * @param numberOfMessages number of messages received
   * @param adjustments the adjustment list.
   */
  public SaleRecord(String saleType, double totalValue, int numSales, int numberOfMessages,
      LinkedList<AdjustmentRecord> adjustments) {
    this.saleType = saleType;
    this.totalValue = totalValue;
    this.numSales = numSales;
    this.numberOfMessages = numberOfMessages;
    this.adjustments = adjustments;
  }

  public double getTotalValue() {
    return totalValue;
  }

  public void setTotalValue(double totalValue) {
    this.totalValue = totalValue;
  }

  public int getNumSales() {
    return numSales;
  }

  public void setNumSales(int numSales) {
    this.numSales = numSales;
  }

  public LinkedList<AdjustmentRecord> getAdjustments() {
    return adjustments;
  }

  public void setAdjustments(LinkedList<AdjustmentRecord> adjustments) {
    this.adjustments = adjustments;
  }

  public int getNumberOfMessages() {
    return numberOfMessages;
  }

  public void setNumberOfMessages(int numberOfMessages) {
    this.numberOfMessages = numberOfMessages;
  }

  public String getSaleType() {
    return saleType;
  }

  public void setSaleType(String saleType) {
    this.saleType = saleType;
  }

  @Override
  public String toString() {
    return "SaleRecord{" +
        "saleType='" + saleType + '\'' +
        ", totalValue=" + totalValue +
        ", numSales=" + numSales +
        ", numberOfMessages=" + numberOfMessages +
        ", adjustments=" + adjustments +
        '}';
  }

}
