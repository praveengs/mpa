package com.jp.solution.model.report;

import java.util.LinkedList;

/**
 * Record the sale and adjust values accordingly
 * @author praveen.nair, created on 07/07/2017.
 */
public class SaleRecord {
  private double totalValue;
  private int numSales;
  private LinkedList<AdjustmentRecord> adjustments;

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

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("SaleRecord{");
    sb.append("totalValue=").append(totalValue);
    sb.append(", numSales=").append(numSales);
    sb.append(", adjustments=").append(adjustments);
    sb.append('}');
    return sb.toString();
  }
}
